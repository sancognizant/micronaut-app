package org.micronaut.service;

import io.micronaut.test.annotation.MicronautTest;
import io.micronaut.test.annotation.MockBean;
import org.junit.jupiter.api.Test;
import org.micronaut.domain.GameStats;
import org.micronaut.domain.LeaderBoard;
import org.micronaut.domain.ScoreCard;
import org.micronaut.repository.ScoreCardRepository;

import javax.inject.Inject;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@MicronautTest
public class LeaderBoardServiceImplTest {

    @Inject
    ScoreCardRepository scoreCardRepository;

    @Inject
    LeaderBoardService leaderBoardService;

    @Test
    void getAllLeaderBoardStatsTest() {

        /*List<Object []> scoreCardList = new ArrayList<>();

        scoreCardList.add(new Object[] {1, 20});

        scoreCardList.add(new Object[] {2, 40});

        scoreCardList.add(new Object[] {3, 60});

        when(scoreCardRepository().findAllLeaders()).thenReturn(scoreCardList);

        List<LeaderBoard> scoreCardList1 = leaderBoardService.getAllLeaderBoardStats();

        assertEquals(scoreCardList.size(), scoreCardList1.size());*/

    }

    @MockBean(ScoreCardRepository.class)
    ScoreCardRepository scoreCardRepository() {
        return mock(ScoreCardRepository.class);
    }

}
