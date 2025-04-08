package hello.servlet.web.frontcontroller.v3;


import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//  REFACTOR : servlet 종속성, view 이름 중복 제거.
@WebServlet(name="frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {

    private Map<String, ControllerV3> controllerMap = new HashMap<>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

//      FIXME : 1. 컨트롤러 조회.
        String requestURI = request.getRequestURI();
        ControllerV3 controllerV3 = controllerMap.get(requestURI);

        if (controllerV3 == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND); // 404

            return;
        }

//      FIXME : 2. 컨트롤러 호츌.
//      REFACTOR : createParamMap() | Http Servlet Request 의 모든 파라미터 값 셋팅
        Map<String, String> paramMap = createParamMap(request); // HttpServletRequest 로 들어온 param map

        System.out.println("paramMap: " + paramMap);

        ModelView modelView = controllerV3.process(paramMap); // 논리이름만 가진 ModelView 반환.

//      FIXME : 3. ModelView 반환.
        String viewName = modelView.getViewName();// View 의 논리이름.

//      FIXME : 4. viewResolver 호출.
//      논리 이름으로 실제 view 생성 | 실제 물리 경로가 있는 MyView 객체를 반환.
        MyView view = viewResolver(viewName);
//      FIXME : 6. render(model) 호출 -> HTML 응답.
        view.render(modelView.getModel(), request, response);
    }

//  FIXME : 5. MyView 반환.
//  MyView 를 만들어 실제 물리 이름을 포함한 MyView 객체 반환.
    private static MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(
                    paramName -> paramMap.put(
                            paramName,
                            request.getParameter(paramName)
                    )
        );

        return paramMap;
    }
}
