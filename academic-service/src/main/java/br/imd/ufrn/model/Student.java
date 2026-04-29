package br.imd.ufrn.model;

import br.imd.ufrn.enums.GradeLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "students")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String cpf;

    private LocalDate birthDate;

    private String responsibleName;

    private String responsibleCpf;

    private String responsiblePhone;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @Enumerated(EnumType.STRING)
    private GradeLevel gradeLevel;

    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "student")
    private List<Enrollment> enrollments;

    @PrePersist
    public void prePersist() {
        this.createdAt = LocalDateTime.now();
    }

}