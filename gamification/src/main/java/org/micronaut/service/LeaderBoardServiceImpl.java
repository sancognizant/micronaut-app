package org.micronaut.service;

import org.bson.Document;
import org.micronaut.collection.ScoreCard;
import org.micronaut.domain.LeaderBoard;
import org.micronaut.repository.ScoreCardRepository;
import static org.micronaut.util.SubscriberHelpers.DocumentSubscriber;
import static org.micronaut.util.SubscriberHelpers.ObservableSubscriber;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Singleton
public class LeaderBoardServiceImpl implements LeaderBoardService {

    @Inject
    private ScoreCardRepository scoreCardRepository;

    private static LeaderBoard mapToLeaderBoard(Document document) {
        String userId = String.valueOf(document.get("_id"));
        String totalScore = String.valueOf(document.get("score"));
        return new LeaderBoard(userId, totalScore);
    }

    @Override
    public List<LeaderBoard> getAllLeaderBoardStats() {

        ObservableSubscriber<Document> documentSubscriber = new DocumentSubscriber();
        scoreCardRepository.findAllLeaders().subscribe(documentSubscriber);

        try {
            documentSubscriber.await();
        } catch (Throwable t) {
            System.out.println(t);
        }

        return Optional.ofNullable(documentSubscriber.getReceived())
                .orElseGet(() -> Collections.emptyList())
                .stream()
                .map(LeaderBoardServiceImpl::mapToLeaderBoard)
                .collect(Collectors.toList());
    }
}
