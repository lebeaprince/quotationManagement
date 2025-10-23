package registerationlogin.entity;

import java.io.Serializable;
import java.sql.Date;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import registerationlogin.dto.AttachmentDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="attachment")
public class Attachment implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "Name should not be empty")
    private String Name;
    private Long CommentId;
    private Date DateCreated;
    private String CreatedBy;
    private java.io.File File;
    
	public Attachment(AttachmentDTO dto) {
		// TODO Auto-generated constructor stub
		this.Name = dto.getName();
		this.CommentId = dto.getCommentId();
		this.DateCreated = dto.getDateCreated();
		this.CreatedBy = dto.getCreatedBy();
		this.File = dto.getFile();
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return Name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		Name = name;
	}
	/**
	 * @return the quotationId
	 */
	public Long getCommentId(){
		return this.CommentId;
	}
	/**
	 * @param quotationId the quotationId to set
	 */
	public void setCommentId(Long quotationId) {
		this.CommentId = quotationId;
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
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
