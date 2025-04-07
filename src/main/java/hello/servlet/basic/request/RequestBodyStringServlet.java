package hello.servlet.basic.request;

import jakarta.servlet.ServletException;
import jakarta.servlet.ServletInputStream;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

//  FIXME : HTTP 요청 데이터 - API 메세지 바디/단순 텍스트.
@WebServlet(name="requestBodyStringServlet", urlPatterns = "/request-body-string")
public class RequestBodyStringServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ServletInputStream inputStream = request.getInputStream(); // message body 의 내용을 byte code 로 얻을 수 있음.
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);// byte code 를 String 으로 출력.

        System.out.println("messageBody : " + messageBody);

        response.getWriter().write("ok-");
    }
}
