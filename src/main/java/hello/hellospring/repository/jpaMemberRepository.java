package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.List;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.springframework.beans.factory.annotation.Autowired;

public class jpaMemberRepository implements MemberRepository {

    private final EntityManager em; //jpa는 엔티티매니저라는것으로 모든게 동작을 한다.
    //jpa관련 라이브러리를 받으면 spring boot가 자동으로 현재 데이터베이스와 연결까지 시켜줘서 엔티티매니저를 생성해준다.
    //(application.properties에 적은 데이터베이스정보를 이용하여)
    //그래서 스프링부트가 만들어 놓은것을 injection(주입) 받기만 하면된다.

    @Autowired
    public jpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member); //persist가 고집하다 지속하다도 있지만 영구저장하다는 느낌도 있다고한다.
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        Member member = em.find(Member.class, id);//조회할 타입과 식별자를 넘겨주면 조회가 된다.
        /*id가 pk(primaryKey)라서 find로 간단하게 처리가 가능
        pk기반이 아닌 나머지들은 jpql을 작성해서 처리해줘야한다.*/
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        List<Member> result = em.createQuery("select m from Member m where m.name= :name",
                Member.class) //findByname은 이런식으로.
            .setParameter("name", name) //파라미터 전달은 여기서
            .getResultList();

        return result.stream().findAny();
    }

    @Override
    public List<Member> findAll() {
        //jpql이라는 쿼리언어를 사용한다.
        //보통 테이블을 대상으로 쿼리를 날리는데, 여기서는 객체를 대상으로 쿼리를 날린다.(그게 sql로 번역이됨)
        //Member엔티티를 대상으로 쿼리를 날리는데 멤버 m 엔티티 전체를 select한다 (sql문의 *과 같은거)
        //jdbc와 달리 jpa에서는 매핑없고 알아서 매핑도 됨
        return em.createQuery("select m from Member m", Member.class)
            .getResultList();
    }
}
