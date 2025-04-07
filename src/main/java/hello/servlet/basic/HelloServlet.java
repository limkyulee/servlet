package hello.servlet.basic;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(name = "helloServlet",  urlPatterns = "/hello") // 서블릿 이름, url 매핑 값은 겹치면 안됨.
public class HelloServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("HelloServlet.service");
        System.out.println("request : " + request);
        System.out.println("response : " + response);

        String username = request.getParameter("username");
        System.out.println("username : " + username);

//      header
//        > HTTP 응답에서 Content-Length 는 웹 애플리케이션 서버가 자동으로 생성.
        response.setContentType("text/plain"); // 단순문자
        response.setCharacterEncoding("UTF-8"); // 대체로 사용
//      body
        response.getWriter().write("Hello " + username + "!");
    }
}
