package registerationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import registerationlogin.entity.Comment;

public interface CommentRepository  extends JpaRepository<Comment,Long>  {

}
