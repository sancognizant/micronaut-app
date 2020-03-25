package org.micronaut.domain;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
public class Trivia {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String question;

    @OneToMany(mappedBy = "trivia")
    private List<Choice> choices;

    private int answer;

    public Trivia(String question, List<Choice> choices, int answer) {
        this.question = question;
        this.choices = choices;
        this.answer = answer;
    }
}
