package org.micronaut.repository;

import io.micronaut.test.annotation.MicronautTest;

import org.micronaut.collection.ScoreCard;

import javax.inject.Inject;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@MicronautTest
public class ScoreCardRepositoryImplTest {

    @Inject
    ScoreCardRepository scoreCardRepository;

    @Test
    void testCrudOperations() {

        /*ScoreCard scoreCard1 = new ScoreCard(1l, 1, 10);
        ScoreCard scoreCard2 = new ScoreCard(1l, 1, 0);
        ScoreCard scoreCard3 = new ScoreCard(2l, 1, 10);
        ScoreCard scoreCard4 = new ScoreCard(2l, 1, 10);


        scoreCardRepository.save(scoreCard1);
        scoreCardRepository.save(scoreCard2);
        scoreCardRepository.save(scoreCard3);
        scoreCardRepository.save(scoreCard4);
        assertEquals(4, scoreCardRepository.count());

        int scoreUser1 = scoreCardRepository.findSumScoreByUserId(1l);
        assertEquals(10, scoreUser1);

        int countUser1 = scoreCardRepository.countByUserId(1l);
        assertEquals(2, countUser1);

        List<Object[]> findallLeaders = scoreCardRepository.findAllLeaders();

        Object [] secondUser = findallLeaders.get(0);

        String ttlScore = String.valueOf(secondUser[1]);

        assertEquals("20", ttlScore);*/
    }
}
