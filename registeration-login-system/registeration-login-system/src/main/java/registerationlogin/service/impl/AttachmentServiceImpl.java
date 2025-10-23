package registerationlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import registerationlogin.dto.AttachmentDTO;
import registerationlogin.dto.CommentDTO;
import registerationlogin.entity.Attachment;
import registerationlogin.entity.Comment;
import registerationlogin.repository.AttachmentRepository;
import registerationlogin.service.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService{

	private AttachmentRepository repo;

	public AttachmentServiceImpl(AttachmentRepository srepo){
		this.repo = srepo;
	}
	
	@Override
	public void saveAttachment(AttachmentDTO dto) {
		// TODO Auto-generated method stub
		Attachment att = new Attachment(dto);
		this.repo.save(att);
	}

	@Override
	public Attachment findById(Long email) {
		// TODO Auto-generated method stub
		return this.repo.getReferenceById(email);
	}

	@Override
	public List<AttachmentDTO> findAllAttachments() {
		// TODO Auto-generated method stub
		List<Attachment>  pros= this.repo.findAll();
		List<AttachmentDTO> proDto = new ArrayList<>();
		for(Attachment qtt : pros){
			AttachmentDTO dto = new AttachmentDTO(qtt);
			proDto.add(dto);
		}
		return proDto;
	}

}
