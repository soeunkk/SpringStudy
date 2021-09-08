package hello.core.order;

import hello.core.annotation.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
//@RequiredArgsConstructor    //final 키워드가 붙은 필드들을 가지고 생성자를 만들어줌 (의존관계 추가할 때 필드 하나만 final 로 추가하면 되므로 굉장히 편리하다)
public class OrderServiceImpl implements OrderService {

    //1 문제점: 할인 정책을 변경하려면 클라이언트인 OrderServiceImpl 코드를 고쳐야 함
    //1 해결책: AppConfig 라고 하는 설정 클래스를 만들어 거기서 관리
    //1 OCP(개방-폐쇄 원칙) 미충족: 기능을 확장했을 때 클라이언트 코드도 변경하고 있음
    //1 DIP(의존성 역전 원칙) 미충족: 구현 클래스에 의존하고 있음(FixDiscountPolicy->RateDiscountPolicy)
//1    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
//1    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
    private final MemberRepository memberRepository;
    private final DiscountPolicy discountPolicy;


    //1 DI를 통해 OCP 와 DIP 모두 충족할 수 있음
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }




    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        //회원 조회와 할인 정책의 역할이 분리되어 있고 각각 기능을 바꾸고 싶다면 구현 객체만 새로 만들어주면 된다.
        Member member = memberRepository.findById(memberId);    //회원 조회
        int discountPrice = discountPolicy.discount(member, itemPrice); //할인 정책

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
