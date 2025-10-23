package registerationlogin.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import registerationlogin.dto.QuotationDTO;

import registerationlogin.entity.Quotation;

import registerationlogin.repository.QuotationRepository;
import registerationlogin.service.QuotationService;

@Service
public class QuotationServiceImpl implements QuotationService{
	private QuotationRepository repo;

	public QuotationServiceImpl(QuotationRepository srepo){
		this.repo = srepo;
	}
	@Override
	public void saveQuotation(QuotationDTO userDto) {
		
		Quotation qtn = new Quotation(userDto);
		this.repo.save(qtn);
	}

	@Override
	public Quotation findById(Long email) {
		
		return this.repo.getReferenceById(email);
	}

	@Override
	public List<QuotationDTO> findAllQuotations() {
	
		List<Quotation> qtlist =  this.repo.findAll();
		List<QuotationDTO> dtoList = new ArrayList<>();
        for(Quotation qtt : qtlist){
        	QuotationDTO userDto = new QuotationDTO(qtt);

            dtoList.add(userDto);
        }
        return dtoList;
	}

}
