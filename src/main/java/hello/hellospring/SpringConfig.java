package hello.hellospring;


import hello.hellospring.repository.JdbcMemberRepository;
import hello.hellospring.repository.JdbcTemplateMemberRepository;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import hello.hellospring.repository.SpringDataJpaMemberRepository;
import hello.hellospring.repository.jpaMemberRepository;
import hello.hellospring.service.MemberService;
import javax.persistence.EntityManager;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {

//    private final DataSource dataSource;
//    @Autowired
//    public SpringConfig(DataSource dataSource) {
//        this.dataSource = dataSource;
//    }

//    private EntityManager em;
//
//    @Autowired
//    public SpringConfig(EntityManager em) {
//        this.em = em;
//    }

    private final MemberRepository memberRepository; //Spring Data Jpa가 구현체를 만들고 빈으로 등록해놓은것을

    @Autowired
    public SpringConfig(MemberRepository memberRepository) { // 주입해서 사용하면 된다.
        this.memberRepository = memberRepository;
    }
    //그리고 인젝션받은것을 memberSerivce를 생성할때 넣어주면된다.

    @Bean //@Bean 어노테이션 -> 스프링빈을 등록할거다 라는 의미
    public MemberService memberService() {
        return new MemberService(memberRepository);
    }

//    @Bean
//    public MemberRepository memberRepository() {
//        return new MemoryMemberRepository();
//        return new JdbcTemplateMemberRepository(dataSource);
//        return new jpaMemberRepository(em);
//
//    }


}
