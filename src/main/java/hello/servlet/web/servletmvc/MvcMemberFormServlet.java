package hello.servlet.web.servletmvc;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";

//      FIXME : Controller 에서 View 로 이동.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
//      forward 는 서버 내부에서 일어나는 호출이기 때문에, 클라이언트에서는 인지하지 못함.
        dispatcher.forward(request, response); // servlet 에서 jsp 호출.
    }
}
