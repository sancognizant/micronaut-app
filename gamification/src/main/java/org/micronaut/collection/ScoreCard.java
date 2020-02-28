package org.micronaut.collection;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonCreator;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

@Getter
@Setter
@NoArgsConstructor
public class ScoreCard {

    @BsonId
    private ObjectId id;

    private Long userId;

    private Integer attemptId;

    private Long scoreTimestamp;

    private Integer score;

    @BsonCreator
    @JsonCreator
    public ScoreCard(@BsonProperty("userId") @JsonProperty("userId") Long userId,
                   @BsonProperty("attemptId") @JsonProperty("attemptId") Integer attemptId,
                   @BsonProperty("score") @JsonProperty("score") Integer score) {
        this.userId = userId;
        this.attemptId = attemptId;
        this.scoreTimestamp = System.currentTimeMillis();
        this.score = score;
    }
}
