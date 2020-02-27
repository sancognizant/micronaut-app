package org.micronaut.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.micronaut.domain.LeaderBoard;
import org.micronaut.domain.Result;
import org.micronaut.domain.ResultTrivia;
import org.micronaut.domain.ScoreCard;
import org.micronaut.service.GameService;
import org.micronaut.service.LeaderBoardService;

import javax.inject.Inject;
import java.util.List;


@Controller
@Validated
public class LeaderBoardController {

    @Inject
    private LeaderBoardService leaderBoardService;

    @Inject
    private GameService gameService;

    // getting the results from Trivia app
    @Post(value = "/results", consumes = MediaType.APPLICATION_JSON)
    public HttpResponse<Result> save(@Body ResultTrivia resultTrivia) {
           Result result = gameService.createNewResult(resultTrivia);
           gameService.newAttemptForUser(result);
           return HttpResponse.ok(result);
    }

    // getting all the leaderboard
    @Get(value = "/scorecard/leaderboard", produces = MediaType.APPLICATION_JSON)
    public List<LeaderBoard> leaderBoardScores() {
        return leaderBoardService.getAllLeaderBoardStats();
    }
}
