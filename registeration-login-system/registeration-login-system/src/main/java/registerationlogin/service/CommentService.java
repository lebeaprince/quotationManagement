package registerationlogin.service;

import java.util.List;

import registerationlogin.dto.CommentDTO;
import registerationlogin.entity.Comment;

public interface CommentService {
    void saveComment(CommentDTO dto);

    Comment findById(Long email);

    List<CommentDTO>findAllComments();
}
