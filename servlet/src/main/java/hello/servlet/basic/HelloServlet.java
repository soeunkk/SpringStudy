package hello.servlet.basic;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//서블릿 애노테이션 (name: 서블릿 이름, urlPatterns: URL 매핑)
//name 과 urlPatterns 는 이름이 겹치면 안됨
@WebServlet(name = "helloServlet", urlPatterns = "/hello")
public class HelloServlet extends HttpServlet  {

    //HTTP 요청을 통해 매핑된 URL 이 호출되면 서블릿 컨테이너는 protected void service(request, response) 메서드를 실행함
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //super.service(request, response);
        System.out.println("HelloServlet.service");
        System.out.println("request = " + request);
        System.out.println("response = " + response);

        //HTTP 요청 메시지에서 정보 받기
        String username = request.getParameter("username");
        System.out.println("username = " + username);

        //전달할 HTTP 응답 메시지 만들기
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
        response.getWriter().write("hello " + username);
    }
}
