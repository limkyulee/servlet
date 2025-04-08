package hello.servlet.web.frontcontroller.v5.adapter;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v4.ControllerV4;
import hello.servlet.web.frontcontroller.v5.MyHandlerAdapter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV4HandlerAdapter implements MyHandlerAdapter {
    @Override
    public boolean supports(Object handler) {
        return (handler instanceof ControllerV4);
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {
        ControllerV4 controller = (ControllerV4) handler;

        Map<String, String> paramMap = createParamMap(request);
        Map<String, Object> model = new HashMap<>();

//      FIXME : controllerV4 는 viewName 을 반환
//         > [adapter 의 순 기능] | handle 이 modelView 로 맞춰 반환할 수 있도록 변한.
        String viewName = controller.process(paramMap, model);

        ModelView modelView = new ModelView(viewName);
        modelView.setModel(model);

        return modelView;
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
