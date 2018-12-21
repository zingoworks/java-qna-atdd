package codesquad.web;

import codesquad.domain.Answer;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import support.test.AcceptanceTest;

import static codesquad.domain.QuestionTest.DEFAULT_QUESTION;

public class ApiAnswerAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(ApiAnswerAcceptanceTest.class);

    @Test
    public void create() {
        String location = createResource("/api/questions", DEFAULT_QUESTION);
        Answer answer = new Answer(defaultUser(), "test contents");
        ResponseEntity<Void> answerResponse = basicAuthTemplate().postForEntity(location + "/answers", answer, Void.class);

        log.debug("answerResponse : {}", answerResponse);

        softly.assertThat(answerResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }
}
