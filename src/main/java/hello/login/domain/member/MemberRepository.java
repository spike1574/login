package hello.login.domain.member;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.*;

// 로그
@Slf4j
// bean 등록
@Repository
public class MemberRepository {

    // store map
    private static Map<Long, Member> store = new HashMap<>();
    // 회원등록번호
    private static Long sequence = 0L;

    // 회원 저장
    public Member save(Member member){
       member.setId(++sequence);
       log.info("save : member={}", member);
       store.put(member.getId(), member);
       return member;
    }

    // 시퀀스ID로 찾기
    public Member findbyId(Long id){
        return store.get(id);
    }

    // 로그인ID 찾기
    public Optional<Member> findByLoginId(String loginId) {
        return findAll().stream()
                .filter(m -> m.getLoginId().equals(loginId))
                .findFirst();
    }

    // 전체찾기
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore() {
        store.clear();
    }

}
