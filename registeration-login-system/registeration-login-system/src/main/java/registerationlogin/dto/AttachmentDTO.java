package registerationlogin.dto;

import java.sql.Date;

import org.apache.tomcat.util.http.fileupload.FileItem;

import jakarta.validation.constraints.NotEmpty;
import registerationlogin.entity.Attachment;

public class AttachmentDTO {
    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String Name;
		
	    private Long CommentId;
	    private Date DateCreated;
	    private String CreatedBy;
	    private java.io.File File;
	    
		public AttachmentDTO(Attachment qtt) {
			// TODO Auto-generated constructor stub
			this.id = qtt.getId();
			this.Name = qtt.getName();
			this.CommentId = qtt.getCommentId();
			this.DateCreated = qtt.getDateCreated();
			this.CreatedBy = qtt.getCreatedBy();
			this.File = qtt.getFile();
		}
		/**
		 * @return the id
		 */
		public Long getId() {
			return id;
		}
		/**
		 * @param id the id to set
		 */
		public void setId(Long id) {
			this.id = id;
		}
		/**
		 * @return the commentId
		 */
		public Long getCommentId() {
			return CommentId;
		}
		/**
		 * @param commentId the commentId to set
		 */
		public void setCommentId(Long commentId) {
			CommentId = commentId;
		}
		/**
		 * @return the dateCreated
		 */
		public Date getDateCreated() {
			return DateCreated;
		}
		/**
		 * @param dateCreated the dateCreated to set
		 */
		public void setDateCreated(Date dateCreated) {
			DateCreated = dateCreated;
		}
		/**
		 * @return the createdBy
		 */
		public String getCreatedBy() {
			return CreatedBy;
		}
		/**
		 * @param createdBy the createdBy to set
		 */
		public void setCreatedBy(String createdBy) {
			CreatedBy = createdBy;
		}
		/**
		 * @return the file
		 */
		public java.io.File getFile() {
			return File;
		}
		/**
		 * @param file the file to set
		 */
		public void setFile(java.io.File file) {
			File = file;
		}
		
		public String getName() {
			return Name;
		}
		/**
		 * @param file the file to set
		 */
		public void setName(String file) {
			Name = file;
		}	
}
