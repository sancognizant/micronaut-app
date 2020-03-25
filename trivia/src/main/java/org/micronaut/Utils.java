package org.micronaut;

import org.micronaut.domain.Response;
import org.micronaut.domain.ResultAttempt;
import org.micronaut.domain.ResultAttemptDTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public final class Utils {

    private Utils() {
    }

    public final static String dateFormat(LocalDateTime localDateTime) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return df.format(localDateTime);
    }

    public final static List<ResultAttemptDTO> resultAttemptDTOList(List<ResultAttempt> resultAttempts) {
        List<ResultAttemptDTO> resultAttemptDTOS = new LinkedList<>();
        for (ResultAttempt resultAttempt : resultAttempts) {
            String date = dateFormat(resultAttempt.getLocalDateTime());

            ResultAttemptDTO resultAttemptDTO = new ResultAttemptDTO(
                    resultAttempt.getUserId(),
                    date, resultAttempt.getQuestion(), resultAttempt.getAnswer(),
                    String.valueOf(resultAttempt.isCorrect())
            );

            resultAttemptDTOS.add(resultAttemptDTO);
        }

        return resultAttemptDTOS;
    }

    public static ResultAttemptDTO resultAttemptDTO(ResultAttempt resultAttempt) {

        String date = dateFormat(resultAttempt.getLocalDateTime());

        ResultAttemptDTO resultAttemptDTO = new ResultAttemptDTO(resultAttempt.getUserId(),
                date, resultAttempt.getQuestion(), resultAttempt.getAnswer(),
                String.valueOf(resultAttempt.isCorrect())
        );

        return resultAttemptDTO;
    }

    public static int generateAttemptId() {
        Random random = new Random();

        /* Create a random response Id */
        int randomAttemptId = random.nextInt(30);

        return randomAttemptId;
    }

    public static int checkResponse(Response response) {
//        String[] choiceArray = response.getChoices().split("\\|");
//        return choiceArray[response.getCorrectAnswer()].equals(response.getAnswer()) ? 1 : 0;
        return 1;
    }
}

