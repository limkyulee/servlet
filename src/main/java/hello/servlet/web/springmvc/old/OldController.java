package hello.servlet.web.springmvc.old;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

/**
 * [실행 순서]
 * 1. 핸들러 매핑으로 핸들러 조회
 *  > HandlerMapping 을 순서대로 실행해서, 핸들러를 찾음.
 *  > bean 이름으로 핸들러를 찾아야하기 때문에 BeanNameUrlHandlerMapping 이 실행되고 MyHttpRequestHandler 반환.
 * 2. 핸들러 어댑터 조회
 *  > HandlerAdapter 의 supports() 를 순서대로 호출.
 *  > HttpRequestHandlerAdapter 가 HttpRequestHandler 인터페이스를 지워하므로 대상이 됨.
 * 3. 핸들러 어댑터 실행
 *  > 디스패처 서브릿이 조회한 HttpRequestHandlerAdapter 를 실행하면서 핸들러 정보도 함꼐 넘겨줌.
 *  > MyRequestHandler 를 내부에서 실행하고, 그 결과를 반환함.
 */

/**
 * @RequestMapping
 * 가장 우선순위가 높은 핸들러 매핑과 핸들러 어뎁터는 RequestMappingHandlerMapping, RequestMappingHandlerAdapter 임.
 * 스프링에서 주로 사용하는 애노테이션 기분의 컨트롤러를 지원하는 매핑과 어뎁터. (실무에서 거의 대부분 사용)
 */

/**
 * HandlerMapping(핸들러 매핑)
 * 0 | RequestMappingHandlerMapping : 애노테이션 기반의 컨트롤러인 @RequestMapping 에서 사용.
 * 1 | BeanNameUrlHandlerMapping : 스프링 빈의 이름으로 핸들러를 찾음.

 * 핸들러 매핑에서 이 컨트롤러를 찾을 수 있어야 함.
 * 스프링 빈의 이름으로 핸들러를 찾을 수 있는 "핸들러 매핑" 이 필요함.
 */

/**
 * HandlerAdapter(핸들러 어뎁터)
 * 0 | RequestMappingHandlerAdapter : 애노테이션 기반의 컨트롤러인 @RequestMapping 에서 사용.
 * 1 | HttpRequestHandlerAdapter : HttpRequestHandler 처리
 * 2 | SimpleControllerHandlerAdapter : Controller 인터페이스 (애노테이션 X, 과거에 사용) 처리

 * 핸들러 매핑을 통해서 차증 핸들러를 실행할 수 있는 핸들러 어뎁터가 필요함.
 * Controller 인터페이스를 실행할 수 있는 핸들러 어뎁터를 찾고 실행해야함.
 */

@Component("/springmvc/old-controller") // spring bean 이름 지정.
public class OldController implements Controller {

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");

//      PLUS : Springboot 가 자동으로 ViewResolver 실행.
        return new ModelAndView("new-form");
    }
}
