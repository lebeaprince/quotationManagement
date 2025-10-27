package registerationlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import registerationlogin.entity.GeneratedQuotation;

import java.util.Optional;

public interface GeneratedQuotationRepository extends JpaRepository<GeneratedQuotation, Long> {
    Optional<GeneratedQuotation> findByQuotationId(Long quotationId);
    Optional<GeneratedQuotation> findByLetterNumber(String letterNumber);
}
