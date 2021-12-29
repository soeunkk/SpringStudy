package hello.servlet.web.frontcontroller.v3;

import hello.servlet.web.frontcontroller.ModelView;
import hello.servlet.web.frontcontroller.MyView;
import hello.servlet.web.frontcontroller.v3.controller.MemberFormControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberListControllerV3;
import hello.servlet.web.frontcontroller.v3.controller.MemberSaveControllerV3;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV3", urlPatterns = "/front-controller/v3/*")
public class FrontControllerServletV3 extends HttpServlet {
    private Map<String, ControllerV3> controllerMap = new HashMap<String, ControllerV3>();

    public FrontControllerServletV3() {
        controllerMap.put("/front-controller/v3/members/new-form", new MemberFormControllerV3());
        controllerMap.put("/front-controller/v3/members/save", new MemberSaveControllerV3());
        controllerMap.put("/front-controller/v3/members", new MemberListControllerV3());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("FrontControllerServletV3.service");

        //해당 요청의 URI 를 가져옴
        String requestURI = request.getRequestURI();

        //Map 에서 URI 맞는 인스턴스 꺼냄
        ControllerV3 controller = controllerMap.get(requestURI);
        if (controller == null) {   //서비스할 수 있는 URI 가 아니면 404 띄움
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        //paramMap 에 request 의 각 parameter 저장
        //원래 paramMap 을 생성하는 코드가 service 안에 있었으나 너무 세부내용이라 판단, 새로운 메서드로 분리 (Ctrl+Alt+M)
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);

        //논리 이름을 물리 이름으로 바꿔 MyView 생성
        //이것도 세부적인 내용이라 새로운 메소드로 분리
        String viewName = mv.getViewName(); //논리이름
        MyView view = viewResolver(viewName);

        view.render(mv.getModel(), request, response);
    }

    private MyView viewResolver(String viewName) {
        return new MyView("/WEB-INF/views/" + viewName + ".jsp");
    }

    private Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> paramMap = new HashMap<>();
        request.getParameterNames().asIterator()
                .forEachRemaining(paramName -> paramMap.put(paramName, request.getParameter(paramName)));
        return paramMap;
    }
}
