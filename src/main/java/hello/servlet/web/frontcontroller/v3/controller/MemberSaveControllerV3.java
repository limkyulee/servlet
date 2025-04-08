package hello.servlet.web.frontcontroller.v3.controller;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.v3.ControllerV3;

import java.util.Map;

public class MemberSaveControllerV3 implements ControllerV3 {

    private MemberRepository memberRepository = MemberRepository.getInstance();

    @Override
    public ModelView process(Map<String, String> paramMap) {
        System.out.println("paramMap: " + paramMap);
//      FIXME : HttpServletRequest paramMap 에서 요청 데이터 꺼내기.
        String username = paramMap.get("username");
        int age = Integer.parseInt(paramMap.get("age"));

//      FIXME : 꺼낸 데이터로 멤버 객체 생성 후 memberRepository 에 저장. (회원 저장 로직 실행)
        Member member = new Member(username, age);
        memberRepository.save(member);

//      FIXME : 모델과 뷰 이름을 저장.
        ModelView modelView = new ModelView("save-result");
        modelView.getModel().put("member", member);

//      FIXME : 만들어진 ModelView 객체 반환.
        return modelView;
    }
}
