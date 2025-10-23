package registerationlogin.service;

import java.util.List;

import registerationlogin.dto.AttachmentDTO;
import registerationlogin.entity.Attachment;

public interface AttachmentService {
    void saveAttachment(AttachmentDTO dto);

    Attachment findById(Long email);

    List<AttachmentDTO>findAllAttachments();
}
