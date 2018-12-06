package codesquad.web;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import support.test.AcceptanceTest;

import java.util.Arrays;

public class LoginAcceptanceTest extends AcceptanceTest {
    private static final Logger log = LoggerFactory.getLogger(LoginAcceptanceTest.class);

    @Test
    public void loginForm() throws Exception {
        ResponseEntity<String> response = template().getForEntity("/login/form", String.class);
        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        log.debug("body : {}", response.getBody());
    }

    @Test
    public void login() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userId", "javajigi");
        params.add("password", "test");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

        ResponseEntity<String> response = template().postForEntity("/login", request, String.class);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.FOUND);
        softly.assertThat(response.getHeaders().getLocation().getPath()).isEqualTo("/");
    }

    @Test
    public void loginFail() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.TEXT_HTML));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        params.add("userId", "javajigi");
        params.add("password", "test2");
        HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<MultiValueMap<String, Object>>(params, headers);

        ResponseEntity<String> response = template().postForEntity("/login", request, String.class);

        softly.assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        softly.assertThat(response.getBody().contains("아이디 또는 비밀번호가 틀립니다.")).isTrue();
    }
}
