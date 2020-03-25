package org.micronaut.controller;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;
import org.micronaut.Utils;
import org.micronaut.domain.*;
import org.micronaut.service.TriviaResultService;
import org.micronaut.service.TriviaResultServiceImpl;
import org.micronaut.service.TriviaService;
import org.micronaut.service.TriviaServiceImpl;

import javax.inject.Inject;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class TriviaResultControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    TriviaResultService triviaResultService;

    @Inject
    GamificationClient gamificationClient;

    @Inject
    TriviaService triviaService;

    @Inject
    TriviaResultController trivialResultController;

    @Test
    void testPostResultAttempt() {
        User user = new User();
        user.setName("name");
        user.setId(1l);

        List<Choice> choices = new ArrayList<>();

        choices.add(new Choice(1l,"Obama"));
        choices.add(new Choice(2l,"Trump"));
        choices.add(new Choice(1l,"Lincoln"));

        Response response1 = new Response();
        response1.setAnswer("Trump");
        response1.setQuestion("Who is the president of the USA");
        response1.setChoices(choices);
        response1.setCorrectAnswer(1);
        response1.setUser(user);

        ResultAttempt resultAttempt = new ResultAttempt();
        resultAttempt.setAnswer("Trump");
        resultAttempt.setQuestion("Who is the president of the USA");
        resultAttempt.setLocalDateTime(LocalDateTime.now());
        resultAttempt.setCorrect(true);
        resultAttempt.setUserId(1l);
        resultAttempt.setAttemptId(1);
        resultAttempt.setId(1l);

        Result result = new Result(1l, 1, 1);

        ResultAttemptDTO resultAttemptDTO = new ResultAttemptDTO(resultAttempt.getUserId(),
                Utils.dateFormat(LocalDateTime.now()), resultAttempt.getQuestion(), resultAttempt.getAnswer(),
                String.valueOf(resultAttempt.isCorrect()));

        when(triviaService.checkUser(user)).thenReturn(user);

        when(triviaResultService.postTriviaResults(any(ResultAttempt.class)))
                .thenReturn(resultAttempt);

        when(gamificationClient.save(anyLong(), anyInt(), anyInt()))
                .thenReturn(result);

        when(triviaResultService.createResultAttemptDTO(any(ResultAttempt.class)))
                .thenReturn(resultAttemptDTO);

        HttpResponse<ResultAttemptDTO> response = client.toBlocking().exchange(HttpRequest.POST("/results", response1),ResultAttemptDTO.class);

        assertEquals(resultAttemptDTO, response.body());
        assertEquals(HttpStatus.OK, response.status());
    }

    @Test
    void testGetResultAttempt() {
        ResultAttemptDTO resultAttemptDTO = new ResultAttemptDTO();
        resultAttemptDTO.setAnswer("Donald Trump");
        resultAttemptDTO.setQuestion("Who is the president of the USA");
        resultAttemptDTO.setLocalDateTime("22-01-2020");
        resultAttemptDTO.setUserId(1l);


        ResultAttemptDTO resultAttemptDTO2 = new ResultAttemptDTO();
        resultAttemptDTO2.setAnswer("Peter Parker");
        resultAttemptDTO2.setQuestion("What's the real name of Spiderman");
        resultAttemptDTO.setLocalDateTime("22-01-2020");
        resultAttemptDTO2.setUserId(1l);

        when(triviaResultService.getResults(1)).
                thenReturn(Arrays.asList(resultAttemptDTO, resultAttemptDTO2));

        ResultAttemptDTO[] response = client.toBlocking().retrieve(HttpRequest.GET("/results/users/1"),
                ResultAttemptDTO[].class);

        assertEquals(2, response.length);

    }


    @MockBean(TriviaResultServiceImpl.class)
    TriviaResultService triviaResultService() {
        return mock(TriviaResultService.class);
    }

    @MockBean(GamificationClient.class)
    GamificationClient gamificationClient() {
        return mock(GamificationClient.class);
    }

    @MockBean(TriviaServiceImpl.class)
    TriviaService triviaService() {
        return mock(TriviaService.class);
    }


}
