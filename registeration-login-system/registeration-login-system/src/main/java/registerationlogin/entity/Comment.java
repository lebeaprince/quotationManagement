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
import registerationlogin.dto.CommentDTO;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="comment")
public class Comment implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@NotEmpty(message = "Text should not be empty")
    private String Text;
	
    private Long QuotationId;
    private Date DateCreated;
    private String CreatedBy;
    private java.io.File File;
    
    public Comment(CommentDTO dto) {
    	this.Text = dto.getCreatedBy();
    	this.QuotationId = dto.getQuotationId();
    	this.DateCreated = dto.getDateCreated();
    	this.CreatedBy = dto.getCreatedBy();
    	this.File = dto.getFile();
    }
	/**
	 * @return the name
	 */
	public String getName() {
		return this.Text ;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.Text = name;
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
	public Long getId() {
		// TODO Auto-generated method stub
		return this.id;
	}
}
