package lab.blps.main.bd.entites;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lab.blps.main.bd.entites.enums.TaxFeatureEnum;
import lombok.Data;

@Data
@Entity
@Table(name = "tax_features")
public class TaxFeatures {
    @Id
    @GeneratedValue
    @Column(name = "id", columnDefinition = "serial", nullable = false)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tax_regime_id", nullable = false)
    private TaxRegimes taxRegimes;
    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tax_feature")
    private TaxFeatureEnum taxFeatureEnum;
}
