package registerationlogin.service;

import java.util.List;

import registerationlogin.dto.QuotationDTO;
import registerationlogin.entity.Quotation;

public interface QuotationService {
    void saveQuotation(QuotationDTO userDto);

    Quotation findById(Long email);

    List<QuotationDTO>findAllQuotations();

    Quotation updateQuotationState(Long quotationId, registerationlogin.entity.Enums.QuoatationState newState);

    QuotationDTO findDtoById(Long id);
}
