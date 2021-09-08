package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements  MemberRepository {
    //현재 딱히 정해진 데이터베이스가 없다고 가정하고 HashMap 에 저장
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);   //id 세팅
        store.put(member.getId(),member);   //데이터 저장
        return member;
    }

    //id에 해당하는 Member 객체를 반환
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));  //null 일 때도 오류가 없이 반환되도록 ofNullable() 메소드 사용
    }

    @Override
    public Optional<Member> findByName(String name) {
        //stream: 모든 데이터들을 돌림
        //filter: 조건을 기입
        //findAny: 조건에 만족하는 것을 하나라도 찾음 (결과가 Optional 로 반환됨)
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }
}
