package registerationlogin.service;

import registerationlogin.entity.GeneratedQuotation;

import java.util.List;

public interface GeneratedQuotationService {
    GeneratedQuotation createFromQuotationId(Long quotationId, String acceptedBy);
    GeneratedQuotation findById(Long id);
    GeneratedQuotation findByQuotationId(Long quotationId);
    GeneratedQuotation findByLetterNumber(String letterNumber);
    List<GeneratedQuotation> findAll();
}
