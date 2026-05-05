package br.ufrn.imd.model;
import br.ufrn.imd.enums.TuitionStatus;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tuitions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Tuition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long studentId;

    private String studentName;

    @Column(nullable = false)
    private BigDecimal totalAmount;

    @Column(nullable = false)
    private BigDecimal paidAmount = BigDecimal.ZERO;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TuitionStatus status;

    @Column(nullable = false)
    private String referenceMonth;

    @OneToMany(mappedBy = "tuition", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> payments;
}