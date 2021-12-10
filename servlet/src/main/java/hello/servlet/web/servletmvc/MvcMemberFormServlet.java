package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//URLPatterns는 항상 '/'로 시작해야 함 (오류 못찾아서 헤맴....ㅠ)
@WebServlet(name = "mvcMemberFormServlet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //제어권을 해당 경로이 있는 new-form.jsp로 넘겨줌 (new-form.jsp 실행)
        String viewPath = "/WEB-INF/views/new-form.jsp";    //WEB-INF: 이 경로안에 JSP가 있으면 외부에서 직접 JSP를 호출할 수 없음 (항상 컨트롤러를 통해서 JSP를 호출하도록)
        RequestDispatcher dispatcher = request.getRequestDispatcher(viewPath);
        dispatcher.forward(request, response);  //forward(): 다른 서블릿이나 JSP로 이동할 수 있는 기능 -> 서버 내부에서 다시 호출이 발생 (리다이렉트와 비슷하지만 서버 내부에서 일어나는 일이므로 클라이언트가 알지 못함)
    }
}