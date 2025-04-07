package hello.servlet.basic.request;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

// FIXME : HTTP 요청 데이터 - API 메시지 바디/JSON
@WebServlet(name="requestBodyJsonServlet", urlPatterns = "/request-body-json")
public class RequestBodyJsonServlet extends HttpServlet {

//  PLUS : Jackson 라이브러리 | ObjectMapper
    private ObjectMapper objectMapper = new ObjectMapper();
    
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

//      PLUS : JSON 도 문자.
        System.out.println("messageBody : " + messageBody);

        HelloData helloData = objectMapper.readValue(messageBody, HelloData.class);

//      PLUS : ObjectMapper 를 사용하여 객체로 출력 가능.
        System.out.println("helloData.getUsername() : " + helloData.getUsername());
        System.out.println("helloData.getAge() : " + helloData.getAge());
    }
}
