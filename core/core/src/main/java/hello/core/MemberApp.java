package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {
    public static void main(String[] args) {

        /* MemberService 의 구현 객체 사용하기 */
        //1 AppConfig 없이 객체 사용하는 방법
//1       MemberService memberService = new MemberServiceImpl();
        //2 스프링 컨테이너 없이 AppConfig 사용하는 방법
//2       AppConfig appConfig = new AppConfig();
//2       MemberService memberService = appConfig.memberService();    //DI에 의한 서비스 객체
        //3 스프링 컨테이너 사용하여 AppConfig 사용하는 방법
        //AppConfig 에 있는 환경설정 정보를 토대로 스프링이 스프링 컨테이너에 넣고 관리해줌
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        //getBean([Bean 이름],[반환 타입]): 해당 Bean 객체를 불러와서 해당 타입으로 반환해줘!
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember((1L));
        System.out.println("new Member = " + member.getName());
        System.out.println("find Member = " + findMember.getName());
    }
}
