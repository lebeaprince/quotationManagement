package registerationlogin.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "generated_quotation")
public class GeneratedQuotation implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long QuotationId;

    @Column(nullable = false)
    private Long CustomerId;

    private Date DateCreated;

    @Column(nullable = false)
    private Date ValidUntil;

    @Column(nullable = false)
    private double Total;

    private double Discount;

    private double VAT;

    private String Currency;

    private String CreatedBy;

    // Extra fields for generated letter
    private String AcceptedBy;

    private Timestamp AcceptedAt;

    @Column(unique = true)
    private String LetterNumber;

    public static GeneratedQuotation fromQuotation(Quotation quotation, String acceptedBy) {
        GeneratedQuotation gq = new GeneratedQuotation();
        gq.setQuotationId(quotation.getId());
        gq.setCustomerId(quotation.getCustomerId());
        gq.setDateCreated(quotation.getDateCreated());
        gq.setValidUntil(quotation.getValidUntil());
        gq.setTotal(quotation.getTotal());
        gq.setDiscount(quotation.getDiscount());
        gq.setVAT(quotation.getVAT());
        gq.setCurrency(quotation.getCurrency());
        gq.setCreatedBy(quotation.getCreatedBy());
        gq.setAcceptedBy(acceptedBy);
        gq.setAcceptedAt(new Timestamp(System.currentTimeMillis()));
        // simple letter number generator: QT-YYYYMMDD-<millis % 100000>
        long suffix = System.currentTimeMillis() % 100000;
        java.time.LocalDate today = java.time.LocalDate.now();
        String letterNo = String.format("QT-%04d%02d%02d-%05d",
                today.getYear(), today.getMonthValue(), today.getDayOfMonth(), suffix);
        gq.setLetterNumber(letterNo);
        return gq;
    }
}
