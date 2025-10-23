package registerationlogin.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import registerationlogin.entity.Quotation;


public interface QuotationRepository  extends JpaRepository<Quotation,Long> {
	//Optional findById(Long Id);
}
