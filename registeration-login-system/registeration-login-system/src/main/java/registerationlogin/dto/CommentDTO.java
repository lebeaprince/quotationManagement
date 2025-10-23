package registerationlogin.dto;

import java.sql.Date;

import org.apache.tomcat.util.http.fileupload.FileItem;

import jakarta.validation.constraints.NotEmpty;
import registerationlogin.entity.Comment;

public class CommentDTO {
    private Long id;
	
	@NotEmpty(message = "Name should not be empty")
    private String Text;
	
    private Long QuotationId;
    private Date DateCreated;
    private String CreatedBy;
    private java.io.File File;
	public CommentDTO(Comment qtt) {
		// TODO Auto-generated constructor stub
		this.id = qtt.getId();
		this.Text = qtt.getName();
		this.QuotationId = qtt.getQuotationId();
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
	 * @return the quotationId
	 */
	public Long getQuotationId() {
		return QuotationId;
	}
	/**
	 * @param quotationId the quotationId to set
	 */
	public void setQuotationId(Long quotationId) {
		QuotationId = quotationId;
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
}
