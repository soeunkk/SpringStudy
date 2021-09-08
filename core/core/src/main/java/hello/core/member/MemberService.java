package hello.core.member;

import org.springframework.stereotype.Component;

public interface MemberService {
    //회원가입
    void join(Member member);

    //해당 id를 가진 회원 찾기
    Member findMember(Long memberId);
}
