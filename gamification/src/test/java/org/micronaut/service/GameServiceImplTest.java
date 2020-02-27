package org.micronaut.service;

import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import io.reactivex.Single;
import org.junit.jupiter.api.Test;
import org.micronaut.domain.GameStats;
import org.micronaut.domain.Result;
import org.micronaut.collection.ScoreCard;
import org.micronaut.repository.ScoreCardRepository;

import javax.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class GameServiceImplTest {

    @Inject
    ScoreCardRepository scoreCardRepository;

    @Inject
    GameService gameService;

    @Test
    void computeBadgeTest() {

        //when(scoreCardRepository().findAllScoresByUserId(1l)).thenReturn(10);
        //when(scoreCardRepository.countByUserId(1l)).thenReturn(2);

        GameStats gameStats = gameService.computeBatch(1l);

        assertEquals(10,gameStats.getScore());


    }

    @Test
    void newAttemptForUserTest() {
        Result result = new Result(1l, 1, true);
        ScoreCard scoreCard = new ScoreCard(1l, 1, 10);
        when(scoreCardRepository().save(any(ScoreCard.class)).blockingGet()).thenReturn(scoreCard);
        ScoreCard scoreCard1 = gameService().newAttemptForUser(result);
        assertEquals(result.getUserId(), scoreCard1.getUserId());
    }


    @MockBean(ScoreCardRepository.class)
    ScoreCardRepository scoreCardRepository() {
        return mock(ScoreCardRepository.class);
    }

    @MockBean(GameService.class)
    GameService gameService() {
        return mock(GameService.class);
    }

}
