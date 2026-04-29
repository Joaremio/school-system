package br.imd.ufrn.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name="lesson_sessions")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LessonSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private Classroom classroom;

    private LocalDate date;

    @Column(length = 50)
    private String lessonTitle;

    @Column(length = 500)
    private String lessonContent;

}
