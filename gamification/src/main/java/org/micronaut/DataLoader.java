package org.micronaut;

import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import org.micronaut.repository.ScoreCardRepository;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
@Requires(notEnv = Environment.TEST)
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    @Inject
    private ScoreCardRepository scoreCardRepository;

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {

        /*if (scoreCardRepository.count() == 0) {

            scoreCardRepository.save(new ScoreCard(1l, 1, 10));
            scoreCardRepository.save(new ScoreCard(1l, 2, 10));
            scoreCardRepository.save(new ScoreCard(1l, 3, 0));
            scoreCardRepository.save(new ScoreCard(1l, 4, 0));
            scoreCardRepository.save(new ScoreCard(1l, 5, 0));
            scoreCardRepository.save(new ScoreCard(2l, 6, 10));
            scoreCardRepository.save(new ScoreCard(2l, 7, 10));
            scoreCardRepository.save(new ScoreCard(2l, 8, 10));
            scoreCardRepository.save(new ScoreCard(2l, 9, 10));
            scoreCardRepository.save(new ScoreCard(2l, 10, 10));
        }*/
    }
}
