package registerationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import registerationlogin.entity.Attachment;

public interface AttachmentRepository   extends JpaRepository<Attachment,Long> {

}
