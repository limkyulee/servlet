<%--
  Created by IntelliJ IDEA.
  User: limkyulee
  Date: 4/7/25
  Time: 8:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%--FIXME : JSP - 회원 저장--%>
<%@ page import="hello.servlet.domain.member.MemberRepository" %>
<%@ page import="hello.servlet.domain.member.Member" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    MemberRepository memberRepository = MemberRepository.getInstance();
    System.out.println("save.jsp");

//  response, request 사용 가능.
    String username = request.getParameter("username");
    int age = Integer.parseInt(request.getParameter("age"));

    Member member = new Member(username, age);
    System.out.println("member = " + member);
    memberRepository.save(member);
%>
<html>
<head>
    <title>Title</title>
</head>
<body>
성공
<ul>
    <li>id=<%=member.getId()%></li>
    <li>username=<%=member.getUsername()%></li>
    <li>age=<%=member.getAge()%></li>
</ul>
<a href="/index.html">메인</a>
</body>
</html>

<%--

[변경의 라이프사이클]
1. 변경 주기가 다를 떄 분리하라.
UI 를 일부 수정하는 일과 비즈니스 로직을 수정하는 일은 각각 다르게 발생할 가능성이 매우 높고 대부분 서로에게 영향을 주지않음.
이렇게 변경의 라이프 사이클이 다른 부분을 하나의 코드로 관리하는 것은 유지보수 하기 좋지않음.

JSP 같은 view 파일은 화면을 렌더링 하는데 최적화 되어있기 때문에 해당 부분만 담당하는 것이 가장 효과적임.

따라서, (MVC, Model View Controller)
MVC 패턴은 하나의 서블릿이나, JSP 로 처리하던 것을 컨트롤러(Controller), 뷰(View) 라는 영역으로 서로 역할을 나눈 것을 의미함.
웹 애플리케이션은 대체로 MVC 패턴을 사용함.

Controller : HTTP 요청을 받아서 파라미터를 검증하고 비즈니스 로직을 호출.
비즈니스 로직은 대체로 서비스에 작성함.
view에 전달할 결과 데이터를 조회해서 model 에 담음.
Model : 뷰에 출력할 데이터를 담아둠. 뷰라 필요한 데이터를 모두 모델에 담아서 전달 -> 뷰는 비즈니스 로직이나 데이터 접근을 몰라도 됨.
View : 모델에 담겨있는 데이터를 사용하여 화면을 그리는 일에 집중함. (HTML 을 생성하는 부분을 의미함)

--%>