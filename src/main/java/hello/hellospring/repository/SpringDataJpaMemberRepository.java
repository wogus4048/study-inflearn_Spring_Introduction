package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member,Long>, MemberRepository {
    //interface의 상속은 extends로 한다.  인터페이스는 다중 상속이 가능하다.
    // jpaRepository를 상속받아야한다. (멤버와, 식별자PK의 타입을 받는다) ,
    // jpaRepository를 상속받고있으면 Spring Data Jpa가 구현체를 자동으로 만들어 등록해준다. 그걸 가져다 쓰기만하면된다.
    //그럼 SpringDataJpaMemberRepository라는 구현체가 생성되고 그 구현체는 MemberRepository를 상속하니까 MemberRepository의 구현체도 된다.


    //JPQL select m from Member m where m.name = ?
    @Override
    Optional<Member> findByName(String name);

}
