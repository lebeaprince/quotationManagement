package registerationlogin.service.impl;

import org.springframework.stereotype.Service;
import registerationlogin.entity.GeneratedQuotation;
import registerationlogin.entity.Quotation;
import registerationlogin.repository.GeneratedQuotationRepository;
import registerationlogin.repository.QuotationRepository;
import registerationlogin.service.GeneratedQuotationService;

import java.util.List;
import java.util.Optional;

@Service
public class GeneratedQuotationServiceImpl implements GeneratedQuotationService {

    private final GeneratedQuotationRepository generatedQuotationRepository;
    private final QuotationRepository quotationRepository;

    public GeneratedQuotationServiceImpl(GeneratedQuotationRepository generatedQuotationRepository,
                                         QuotationRepository quotationRepository) {
        this.generatedQuotationRepository = generatedQuotationRepository;
        this.quotationRepository = quotationRepository;
    }

    @Override
    public GeneratedQuotation createFromQuotationId(Long quotationId, String acceptedBy) {
        Optional<Quotation> maybe = quotationRepository.findById(quotationId);
        if (maybe.isEmpty()) {
            return null;
        }
        Quotation q = maybe.get();
        GeneratedQuotation entity = GeneratedQuotation.fromQuotation(q, acceptedBy);
        return generatedQuotationRepository.save(entity);
    }

    @Override
    public GeneratedQuotation findById(Long id) {
        return generatedQuotationRepository.findById(id).orElse(null);
    }

    @Override
    public GeneratedQuotation findByQuotationId(Long quotationId) {
        return generatedQuotationRepository.findByQuotationId(quotationId).orElse(null);
    }

    @Override
    public GeneratedQuotation findByLetterNumber(String letterNumber) {
        return generatedQuotationRepository.findByLetterNumber(letterNumber).orElse(null);
    }

    @Override
    public List<GeneratedQuotation> findAll() {
        return generatedQuotationRepository.findAll();
    }
}
