package br.imd.ufrn.model;

import br.imd.ufrn.enums.AttendanceStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "attendances")
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "enrollment_id")
    private Enrollment enrollment;


    private LocalDateTime createdAt;

    @Enumerated(EnumType.STRING)
    private AttendanceStatus status;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

}