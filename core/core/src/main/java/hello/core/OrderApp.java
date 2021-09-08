package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.order.Order;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class OrderApp {

    public static void main(String[] args) {

        /* MemberService 와 OrderService 의 구현 객체 사용하기 */
        //1 AppConfig 없이 객체 사용하는 방법
//1       MemberService memberService = new MemberServiceImpl();
//1       OrderService orderService = new OrderServiceImpl();
        //2 스프링 컨테이너 없이 AppConfig 사용하는 방법
//2       AppConfig appConfig = new AppConfig();
//2       MemberService memberService = appConfig.memberService();
//2       OrderService orderService = appConfig.orderService();
        //3 스프링 컨테이너 사용하여 AppConfig 사용하는 방법
        //AppConfig 에 있는 환경설정 정보를 토대로 스프링이 스프링 컨테이너에 넣고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //getBean([Bean 이름],[반환 타입]): 해당 Bean 객체를 불러와서 해당 타입으로 반환해줘!
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        OrderService orderService = applicationContext.getBean("orderService", OrderService.class);

        //테스트를 위한 새로운 회원 가입
        Long memberId = 1L;
        Member member = new Member(memberId, "memberA", Grade.VIP);
        memberService.join(member);

        //주문 생성
        Order order = orderService.createOrder(memberId, "itemA", 10000);

        System.out.println("order = " + order.toString());
    }
}
