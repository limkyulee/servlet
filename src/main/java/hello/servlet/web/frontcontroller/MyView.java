package hello.servlet.web.frontcontroller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class MyView {
    private String viewPath;

    public MyView(String viewPath) {
        this.viewPath = viewPath;
    }

    public void render(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

    public void render(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//      request 에 파라미터 값 셋팅
        modelToRequestAttribute(model, request);

//      헤당하는 JSP 로 이동.
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);
    }

//  FIXME : RequestAttribute 에 model 값 셋팅
    private static void modelToRequestAttribute(Map<String, Object> model, HttpServletRequest request) {
//        model.forEach((key, value) -> request.setAttribute(key, value));
        model.forEach(request::setAttribute);
    }
}
