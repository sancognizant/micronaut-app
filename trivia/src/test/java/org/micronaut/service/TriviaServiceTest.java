package org.micronaut.service;


import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.micronaut.domain.Choice;
import org.micronaut.domain.Trivia;
import org.micronaut.domain.User;
import org.micronaut.repository.TriviaRepository;
import org.micronaut.repository.UserRepository;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


@MicronautTest
public class TriviaServiceTest {

    @Inject
    TriviaService triviaService;

    @Inject
    TriviaRepository triviaRepository;

    @Inject
    UserRepository userRepository;

    @Test
    void getTrivia(){
        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice(1l,"Obama"));
        choices.add(new Choice(2l,"Trump"));
        choices.add(new Choice(1l,"Lincoln"));

        List<Trivia> list = Arrays.asList(new Trivia("Who is the president of the USA",
                        choices, 1),
                new Trivia("What's the real name of Spiderman", choices, 2));

        when(triviaRepository.findAll()).thenReturn(list);

         final String question = triviaService.getTrivia(0).getQuestion();

        Assertions.assertTrue(question.equalsIgnoreCase("Who is the president of the USA")
                || question.equalsIgnoreCase("What's the real name of Spiderman"));
    }

    @Test
    void checkUser(){
       User user = new User();
       user.setName("joe");

       doReturn(Optional.of(user)).when(userRepository).findByName("joe");
       doReturn(user).when(userRepository).save(user);

       User user1 = triviaService.checkUser(user);


    }

    @MockBean(TriviaRepository.class)
    TriviaRepository triviaRepository() {
        return mock(TriviaRepository.class);
    }

    @MockBean(UserRepository.class)
    UserRepository userRepository() {
        return mock(UserRepository.class);
    }
}
