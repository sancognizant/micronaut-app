package org.micronaut;


import io.micronaut.context.annotation.Requires;
import io.micronaut.context.env.Environment;
import io.micronaut.context.event.ApplicationEventListener;
import io.micronaut.runtime.server.event.ServerStartupEvent;
import org.micronaut.domain.Choice;
import org.micronaut.domain.Trivia;
import org.micronaut.repository.TriviaRepository;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.List;

@Singleton
@Requires(notEnv = Environment.TEST)
public class DataLoader implements ApplicationEventListener<ServerStartupEvent> {

    private TriviaRepository repository;

    public DataLoader(TriviaRepository repository) {
        this.repository = repository;
    }

    @Override
    public void onApplicationEvent(ServerStartupEvent event) {
        if (repository.count() == 0) {
            List<Choice> choices1 = new ArrayList<>();
            List<Choice> choices2 = new ArrayList<>();
            List<Choice> choices3 = new ArrayList<>();
            List<Choice> choices4 = new ArrayList<>();
            List<Choice> choices5 = new ArrayList<>();

            choices1.add(new Choice(1l,"Obama"));
            choices1.add(new Choice(2l,"Trump"));
            choices1.add(new Choice(1l,"Lincoln"));

            choices2.add(new Choice(1l,"Peter Parker"));
            choices2.add(new Choice(1l,"Peter Pan"));
            choices2.add(new Choice(1l,"Pan Parker"));

            choices3.add(new Choice(1l,"Bruce Banned"));
            choices3.add(new Choice(1l,"Bruce Lee"));
            choices3.add(new Choice(1l,"Bruce Wayne"));

            choices4.add(new Choice(1l,"Bruce Banned"));
            choices4.add(new Choice(1l,"Bruce Lee"));
            choices4.add(new Choice(1l,"Bruce Wayne"));

            choices5.add(new Choice(1l,"Bruce Banned"));
            choices5.add(new Choice(1l,"Bruce Lee"));
            choices5.add(new Choice(1l,"Bruce Wayne"));





            repository.save(new Trivia("Who is the president of the USA?",
                    choices1, 1));
            repository.save(new Trivia("What's the real name of Spiderman?",
                    choices2, 0));
            repository.save(new Trivia("What's the real name of Batman?",
                    choices3, 0));
            repository.save(new Trivia("Who is the founder of Apple Inc?",
                    choices4, 0));
            repository.save(new Trivia("Who is the founder of Microsoft?",
                    choices5, 2));
            repository.save(new Trivia("What is the real name of Superman?",
                    choices1, 2));
            repository.save(new Trivia("What is 40 + 50?",
                    choices2, 0));
            repository.save(new Trivia("What is 100 + 50?",
                    choices3, 1));
            repository.save(new Trivia("What is 100 multiplied by 3?",
                    choices4, 1));

        }
    }
}
