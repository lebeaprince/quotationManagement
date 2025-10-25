(function(){
  'use strict';

  function select(viewId){
    return document.getElementById(viewId);
  }

  function activate(viewId){
    document.querySelectorAll('.spa-view').forEach(function(node){
      node.classList.remove('active');
    });
    var target = select(viewId);
    if(target){
      target.classList.add('active');
    }
  }

  function parseHash(){
    var h = window.location.hash || window.SPA_DEFAULT_ROUTE || '#/quotations/list';
    if(!h.startsWith('#/')) return { entity: 'quotations', action: 'list' };
    var parts = h.substring(2).split('/');
    return { entity: parts[0] || 'quotations', action: parts[1] || 'list', id: parts[2] };
  }

  function route(){
    var r = parseHash();
    switch(r.entity){
      case 'quotations':
        switch(r.action){
          case 'list':
            activate('view-quotations-list');
            break;
          case 'upload':
            activate('view-quotations-upload');
            break;
          case 'review':
            // Expect r.id; if missing, fallback to list
            if(r.id){
              openReviewModal(r.id);
            }
            activate('view-quotations-list');
            break;
          default:
            activate('view-placeholder');
        }
        break;
      case 'users':
        switch(r.action){
          case 'list':
            activate('view-users-list');
            fetchUsersList();
            break;
          default:
            activate('view-placeholder');
        }
        break;
      default:
        activate('view-placeholder');
    }
  }

  function fetchUsersList(){
    var container = document.getElementById('users-list-container');
    if(!container) return;
    container.innerHTML = '<div class="text-muted">Loading…</div>';
    fetch('/users', { headers: { 'X-Requested-With': 'SPA' } })
      .then(function(resp){ return resp.text(); })
      .then(function(html){
        // Extract the table from server-rendered users.html
        var tmp = document.createElement('div');
        tmp.innerHTML = html;
        var table = tmp.querySelector('table');
        if(table){
          container.innerHTML = '';
          container.appendChild(table);
        } else {
          container.innerHTML = '<div class="text-danger">Failed to load users list.</div>';
        }
      })
      .catch(function(){
        container.innerHTML = '<div class="text-danger">Failed to load users list.</div>';
      });
  }

  function openReviewModal(id){
    var modalBody = document.getElementById('reviewModalBody');
    if(!modalBody) return;
    modalBody.innerHTML = '<div class="text-muted">Loading…</div>';
    fetch('/quotation/' + encodeURIComponent(id) + '/review', { headers: { 'X-Requested-With': 'SPA' } })
      .then(function(resp){ return resp.text(); })
      .then(function(html){
        // Insert the main content from approval.html into modal body
        var tmp = document.createElement('div');
        tmp.innerHTML = html;
        var table = tmp.querySelector('table');
        var forms = tmp.querySelectorAll('form');
        var content = document.createElement('div');
        if(table) content.appendChild(table);
        forms.forEach(function(f){ content.appendChild(f); });
        if(content.childNodes.length === 0){
          modalBody.innerHTML = '<div class="text-danger">Failed to load review.</div>';
        } else {
          modalBody.innerHTML = '';
          modalBody.appendChild(content);
        }
        // Show modal
        var modalEl = document.getElementById('reviewModal');
        if(window.bootstrap && modalEl){
          var modal = new bootstrap.Modal(modalEl);
          modal.show();
        }
      })
      .catch(function(){
        modalBody.innerHTML = '<div class="text-danger">Failed to load review.</div>';
      });
  }

  // Enhance clicks on Review buttons to use modal route
  function delegateReviewLinks(){
    document.addEventListener('click', function(e){
      var a = e.target.closest('a');
      if(!a) return;
      var href = a.getAttribute('href') || '';
      var match = href.match(/^\/quotation\/(\d+)\/review$/);
      if(match){
        e.preventDefault();
        window.location.hash = '#/quotations/review/' + match[1];
      }
    });
  }

  window.addEventListener('hashchange', route);
  document.addEventListener('DOMContentLoaded', function(){
    delegateReviewLinks();
    route();
  });
})();
