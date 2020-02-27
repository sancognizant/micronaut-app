package org.micronaut.repository;

import com.mongodb.client.model.Accumulators;
import com.mongodb.client.model.Aggregates;
import com.mongodb.client.model.Filters;
import com.mongodb.reactivestreams.client.MongoClient;
import com.mongodb.reactivestreams.client.MongoCollection;
import io.micronaut.data.annotation.Repository;
import io.reactivex.Single;
import org.bson.Document;
import org.micronaut.collection.ScoreCard;
import org.micronaut.util.GamificationConst;
import org.reactivestreams.Publisher;

import javax.inject.Inject;
import javax.inject.Singleton;
import javax.validation.Valid;
import java.util.Arrays;

@Repository
public class ScoreCardRepository {

    public static final String COLLECTION_NAME = "scorecard";

    @Inject
    private MongoClient mongoClient;

    public Publisher<ScoreCard> findAllScoresByUserId(long userId) {
        return getCollection().find(Filters.eq("userId", userId));
    };

    public Publisher<Long> countByUserId(long userId) {
        return getCollection().countDocuments(Filters.eq("userId", userId));
    };

    public Publisher<Document> findAllLeaders() {
        return getCollection().aggregate(Arrays.asList(
                Aggregates.group("$" + "userId", Accumulators.sum("score", "$score")),
                Aggregates.sort(new Document("score", -1))));
    }

    public Single<ScoreCard> save(@Valid ScoreCard scoreCard) {
        return Single.fromPublisher(getCollection().insertOne(scoreCard)).map(success -> scoreCard);
    }

    private MongoCollection<ScoreCard> getCollection() {
        return mongoClient.getDatabase(GamificationConst.DB_NAME).getCollection(COLLECTION_NAME, ScoreCard.class);
    }
}
