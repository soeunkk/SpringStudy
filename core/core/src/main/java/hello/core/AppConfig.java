package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * 제어의 역전(IoC)
 * 구현 객체는 자신의 로직을 실행하는 역할만 담당하며, 프로그램의 제어 흐름은 AppConfig 가 가져간다.
 * 이렇듯 프로그램의 제어 흐름을 직접 제어하는 것이 아니라 외부에서 관리하는 것을 제어의 역전(IoC)이라 한다.
 */

/*
 * 의존관계 주입(DI)
 * 정적인 클래스 의존관계: 애플리케이션을 실행하지 않아도 분석할 수 있다. (import 만 보고 쉽게 알 수 있음)
 * ex) MemberServiceImpl 은 MemberRepository 에 의존한다.
 * 동적인 객체 의존관계: 애플리케이션 실행 시점에 생성된 객체 인스턴스의 참조가 연결된 의존관계다. (AppConfig 와 같은 애들에 의해)
 * ex) MemberServiceImpl 은 MemoryMemberRepository 에 의존한다.
 * 여기서, 애플리케이션 실행 시점에 외부에서 실제 구현 객체를 생성하고 클라이언트에 전달해서 클라이언트와 서버의 실제 의존관계가 연결 되는 것을 DI 라 한다.
 *
 * DI를 사용하면 클라이언트 코드를 변경하지 않고, 클라이언트가 호출하는 대상의 타입 인스턴스를 변경할 수 있다.
 * 즉, 정적인 클래스 의존관계를 변경하지 않고, 동적인 객체 인스턴스 의존관계를 쉽게 변경할 수 있다.
 */

/*
 * IoC 컨테이너, DI 컨테이너
 * : AppConfig 처럼 객체를 생성하고 관리하면서 의존관계를 연결해주는 것을 말한다.
 */
//구현 객체를 생성하고 연결하는 책임을 가지는 별도의 설정 클래스
//애플리케이션의 전체 동작 방식을 구성
@Configuration
public class AppConfig {

    /*
     * @Bean: 스프링 컨테이너에 등록
    */

    //DI(생성자 주입)
    //MemberService 에서 필요한 MemberRepository 구현 객체 설정
    @Bean
    public MemberService memberService() {
//        return new MemberServiceImpl(new MemoryMemberRepository());
        return new MemberServiceImpl(memberRepository());
    }

    //MemberRepository 에 어떤 구현 객체를 사용할 지 지정
    @Bean
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }

    //OrderService 에서 필요한 MemberRepository, DiscountPolicy 구현 객체 설정
    @Bean
    public OrderService orderService() {
//        return new OrderServiceImpl(new MemoryMemberRepository(), new FixDiscountPolicy());
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    //DiscountPolicy 에 어떤 구현 객체를 사용할 지 지정
    @Bean
    public DiscountPolicy discountPolicy() {
//        return new FixDiscountPolicy();
        return new RateDiscountPolicy();
    }
}
