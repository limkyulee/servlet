package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

//  FIXME : HTTP 응답 데이터 - API JSON
@WebServlet(name="responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {

    ObjectMapper objectMapper = new ObjectMapper();

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//      Content-Type: application/json
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");

        HelloData helloData = new HelloData();
        helloData.setUsername("임규리");
        helloData.setAge(18);

//      변경 결과 : {"username": "lim", "age": 20}
//      writeValueAsString() : 객체를 문자 형태로 바꿔라.
        String result = objectMapper.writeValueAsString(helloData);
        resp.getWriter().write(result);
    }
}
