package org.micronaut.controller;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.RxHttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;
import org.micronaut.domain.LeaderBoard;
import org.micronaut.domain.Result;
import org.micronaut.domain.ResultTrivia;
import org.micronaut.collection.ScoreCard;
import org.micronaut.service.GameService;
import org.micronaut.service.GameServiceImpl;
import org.micronaut.service.LeaderBoardService;
import org.micronaut.service.LeaderBoardServiceImpl;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class LeaderBoardControllerTest {

    @Inject
    @Client("/")
    RxHttpClient client;

    @Inject
    LeaderBoardService leaderBoardService;

    @Inject
    GameService gameService;

   /* @Test
    void leaderBoardScoresTest () {
        List<LeaderBoard> leaderBoardList = new ArrayList<>();
        leaderBoardList.add(new LeaderBoard("1", "100"));
        leaderBoardList.add(new LeaderBoard("2", "200"));
        leaderBoardList.add(new LeaderBoard("3", "300"));

        when(leaderBoardService.getAllLeaderBoardStats()).thenReturn(leaderBoardList);

        LeaderBoard[] leaderBoardList1 =  client.toBlocking().retrieve(HttpRequest.GET("/scorecard/leaderboard"),
                LeaderBoard[].class);

        assertEquals(leaderBoardList.size(), leaderBoardList1.length);
    }*/

    @Test
    void saveMethodTest () {
        ResultTrivia resultTrivia = new ResultTrivia(1l,1,1);

        Result result = new Result(1l,1, true);

        when(gameService.createNewResult(resultTrivia)).thenReturn(result);

        when(gameService.newAttemptForUser(any(Result.class))).thenReturn(new ScoreCard(1l,1,1));

        HttpResponse<Result> response = client.toBlocking().exchange(HttpRequest.POST("/results", resultTrivia), Result.class);

        assertEquals(result.getUserId(), response.body().getUserId());
        assertEquals(HttpStatus.OK, response.status());
    }


    @MockBean(GameServiceImpl.class)
    GameService gameService() {
        return mock(GameService.class);
    }

    @MockBean(LeaderBoardServiceImpl.class)
    LeaderBoardService leaderBoardService() {
        return mock(LeaderBoardService.class);
    }
}
