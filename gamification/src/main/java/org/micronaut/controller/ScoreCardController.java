package org.micronaut.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.validation.Validated;
import lombok.ToString;
import org.micronaut.domain.GameStats;
import org.micronaut.service.GameService;

import javax.inject.Inject;

@Controller("/scorecard")
@Validated
public class ScoreCardController {

    @Inject
    private GameService gameService;

    @Get(value = "gamestats/{userId}", produces = MediaType.APPLICATION_JSON)
    public HttpResponse<GameStats> gameStatsUser(@PathVariable long userId) {
        GameStats gameStats = gameService.computeBatch(userId);
        return HttpResponse.ok(gameStats);
    }


}
