package hello.servlet.basic.response;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

//      [status line]
//      PLUS : HttpServletResponse.SC_OK | 200
        response.setStatus(HttpServletResponse.SC_OK);
//      PLUS : SC_BAD_REQUEST | 400
//        response.setStatus(HttpServletResponse.SC_BAD_REQUEST);

//      [response-header]
        response.setHeader("Content-Type", "text/plain;charset=UTF-8");
//      PLUS : cache 없애기. (과거버전 포함)
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");

//      [Header 편의 메서드]
        content(response);
        cookie(response);
        redirect(response);

//      [message-body]
        PrintWriter writer = response.getWriter();
        writer.println("ok, 안녕하세요.");
    }

//  PLUS : Content 편의 메서드
    private void content(HttpServletResponse response) {
//      Content-Type: text/plain;charset=utf-8
//      Content-Length: 2
//      response.setHeader("Content-Type", "text/plain;charset=utf-8");
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
//      response.setContentLength(2); // (생략시 자동 생성)
    }

//  PLUS : 쿠키 편의 메서드
    private void cookie(HttpServletResponse response) {
//      Set-Cookie: myCookie=good; Max-Age=600;
//      response.setHeader("Set-Cookie", "myCookie=good; Max-Age=600");

//      PLUS : 쿠키 객체 생성 후 크키 셋팅
        Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600); //600초
        response.addCookie(cookie);
    }

//  PLUS : redirect 편의 메서드
    private void redirect(HttpServletResponse response) throws IOException {
//      Status Code 302
//      Location: /basic/hello-form.html

//      response.setStatus(HttpServletResponse.SC_FOUND); // 302
//      response.setHeader("Location", "/basic/hello-form.html");
        response.sendRedirect("/basic/hello-form.html");
    }
}
