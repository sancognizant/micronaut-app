package org.micronaut.service;

import org.micronaut.collection.ScoreCard;
import org.micronaut.domain.GameStats;
import org.micronaut.domain.Badge;
import org.micronaut.domain.Result;
import org.micronaut.domain.ResultTrivia;
import org.micronaut.repository.ScoreCardRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.micronaut.util.SubscriberHelpers.ScoreCardSubscriber;
import static org.micronaut.util.SubscriberHelpers.ObservableSubscriber;
import static org.micronaut.util.SubscriberHelpers.ObjectSubscriber;

@Singleton
public class GameServiceImpl implements GameService {

    @Inject
    private ScoreCardRepository scoreCardRepository;

    @Override
    public GameStats computeBatch(long userId) {

        ObservableSubscriber<ScoreCard> scoreCardSubscriber = new ScoreCardSubscriber();
        scoreCardRepository.findAllScoresByUserId(userId).subscribe(scoreCardSubscriber);
        int totalScoreUser = 0;
        try {
            scoreCardSubscriber.await();
            totalScoreUser = Optional.ofNullable(scoreCardSubscriber.getReceived())
                    .orElseGet(() -> Collections.emptyList())
                    .stream()
                    .mapToInt(ScoreCard::getScore)
                    .sum();
        } catch (Throwable t) {
            System.out.println(t);
        }

        ObservableSubscriber<Object> objectSubscriber = new ObjectSubscriber();
        scoreCardRepository.countByUserId(userId).subscribe(objectSubscriber);
        Optional<Integer> totalAttemptUser = null;
        try {
            objectSubscriber.await();
            totalAttemptUser = Optional.ofNullable(objectSubscriber.getReceived())
                    .orElseGet(() -> Collections.emptyList())
                    .stream()
                    .filter(obj -> obj instanceof Long)
                    .map(obj -> (Long) obj)
                    .map(Long::intValue)
                    .findFirst();

        } catch (Throwable t) {
            System.out.println(t);
        }

        double scorePercentage = calculateScorePercentage(totalScoreUser, totalAttemptUser.get());
        Badge badge = BadgeType(scorePercentage);
        GameStats gameStats = new GameStats(userId, totalScoreUser, badge);
        return gameStats;
    }

    @Override
    public ScoreCard newAttemptForUser(Result result) {
        return scoreCardRepository.save(new ScoreCard(result.getUserId(), result.getAttemptId(), calculateScore(result.isCorrect()))).blockingGet();
    }

    private Badge BadgeType(double scorePercentage) {
        if (scorePercentage > 80) {
            return Badge.GOLD;
        } else if (scorePercentage <= 80 && scorePercentage >= 40) {
            return Badge.SILVER;
        } else if (scorePercentage <= 40 && scorePercentage >= 0) {
            return Badge.BRONZE;
        } else {
            return Badge.NO_BADGE;
        }
    }

    @Override
    public Result createNewResult(ResultTrivia resultTrivia) {
        return new Result(resultTrivia.getUserId(), resultTrivia.getAttemptId(), resultTrivia.getIsCorrect() == 1 ? true : false);
    }

    private int calculateScore(boolean correct) {
        return correct == true ? 10 : 0;
    }

    private double calculateScorePercentage(int totalScoreUser, int totalAttemptUser) {
        return (totalScoreUser * 100) / (totalAttemptUser * 10);
    }
}
