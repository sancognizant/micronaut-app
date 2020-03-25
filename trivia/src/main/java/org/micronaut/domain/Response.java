package org.micronaut.domain;

import io.micronaut.core.annotation.Introspected;
import lombok.*;

import java.util.List;

@RequiredArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
@Introspected
@Setter
public class Response {

    private User user;

    private String question;

    private List<Choice> choices;

    private String answer;
}
