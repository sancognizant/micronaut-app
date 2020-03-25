package org.micronaut.domain;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Choice {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String choice;

    @ManyToOne
    private Trivia trivia;

    public Choice(Long id, String choice) {
        this.id = id;
        this.choice = choice;
    }
}
