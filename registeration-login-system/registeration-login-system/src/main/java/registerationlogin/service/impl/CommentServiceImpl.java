package registerationlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import registerationlogin.dto.CommentDTO;

import registerationlogin.entity.Comment;

import registerationlogin.repository.CommentRepository;
import registerationlogin.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService{

	private CommentRepository repo;

	public CommentServiceImpl(CommentRepository srepo){
		this.repo = srepo;
	}
	
	@Override
	public void saveComment(CommentDTO dto) {
		
		Comment com = new Comment(dto);
		this.repo.save(com);
	}

	@Override
	public Comment findById(Long email) {
		
		return this.repo.getReferenceById(email);
	}

	@Override
	public List<CommentDTO> findAllComments() {
		
		List<Comment>  pros= this.repo.findAll();
		List<CommentDTO> proDto = new ArrayList<>();
		for(Comment qtt : pros){
			CommentDTO dto = new CommentDTO(qtt);
			proDto.add(dto);
		}
		return proDto;
	}

}
