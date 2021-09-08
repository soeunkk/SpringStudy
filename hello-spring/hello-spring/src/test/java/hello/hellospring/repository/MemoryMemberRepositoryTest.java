package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class MemoryMemberRepositoryTest {

    MemoryMemberRepository repository = new MemoryMemberRepository();

    //@AfterEach: @Test 가 끝날 때마다 진행
    @AfterEach
    public void afterEach() {
        repository.clearStore();    //테스트가 끝날 때마다 저장소를 지움
    }

    @Test
    public void save() {
        //객체를 하나 만들고 save() 테스트
        Member member = new Member();
        member.setName("spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();  //get을 통해 Optional에서 꺼내서 가져옴

        //기능이 제대로 동적하는지 검증
        System.out.println("result = " + (result == member ));  //콘솔창을 통해 개발자가 직접 확인해야 함
        //Assertions 를 통해 확인(동일하면 실행 성공, 동일하지 않으면 오류)
        Assertions.assertEquals(member, result);    //jupiter
        //Assertions.assertThat(member).isEqualTo(result);   //assertj: 좀 더 가독성이 좋음

    }

    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //rename 단축키: shift + f6
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
}
