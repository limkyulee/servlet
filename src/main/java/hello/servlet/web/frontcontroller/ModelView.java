package hello.servlet.web.frontcontroller;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
public class ModelView {
    private String viewName; // 논리 뷰 이름
    private Map<String, Object> model = new HashMap<>(); // 뷰를 렌더링할 때 필요한 모델 객체 (데이터)

    public ModelView(String viewName) {
        this.viewName = viewName;
    }
}
