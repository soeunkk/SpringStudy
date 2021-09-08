package hello.core.member;

import org.springframework.stereotype.Component;

public interface MemberRepository {
    //회원 객체 저장소에 저장하기
    void save(Member member);

    //id를 통해 회원 객체 찾기
    Member findById(Long memberId);
}
