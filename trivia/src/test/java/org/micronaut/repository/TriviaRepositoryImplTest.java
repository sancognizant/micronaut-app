package org.micronaut.repository;


import io.micronaut.test.annotation.MicronautTest;
import org.junit.jupiter.api.Test;
import org.micronaut.domain.Choice;
import org.micronaut.domain.Trivia;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

@MicronautTest
public class TriviaRepositoryImplTest {

    @Inject
    TriviaRepository triviaRepository;

    @Test
    void testCrudOperations() {

        List<Choice> choices1 = new ArrayList<>();
        List<Choice> choices2 = new ArrayList<>();

        choices1.add(new Choice(1l,"Obama"));
        choices1.add(new Choice(2l,"Trump"));
        choices1.add(new Choice(1l,"Lincoln"));

        choices2.add(new Choice(1l,"Peter Parker"));
        choices2.add(new Choice(1l,"Peter Pan"));
        choices2.add(new Choice(1l,"Pan Parker"));

        Trivia trivia1 = new Trivia("Who is the president of the USA",
                choices1, 1);
        Trivia trivia2 = new Trivia("What's the real name of Spiderman",
                choices2, 2);

        triviaRepository.save(trivia1);
        triviaRepository.save(trivia2);
        assertEquals(2, triviaRepository.count());

        List<Trivia> triviaList = triviaRepository.findAll();
        assertEquals(2, triviaList.size());

        Trivia trivia = triviaRepository.findById(1L).orElse(null);
        assertSame("Who is the president of the USA", trivia.getQuestion());
        assertEquals(Optional.empty(), triviaRepository.findById(27L));
    }
}
