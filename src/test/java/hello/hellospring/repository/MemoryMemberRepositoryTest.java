package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;

class MemoryMemberRepositoryTest { //다른곳에 가져다 쓸게아니니까 public일 필요가없음

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach //하나의 테스트 메소드가 동작이 끝날때마다 해당 어노테이션이 붙은 메소드를 동작하라는뜻.
    public void afterEach()
    {
        repository.clearStore(); // 저장소에있는 내용 삭제
    }



    @Test
    public void save(){
        Member member = new Member(); //멤버 객체를 만들고
        member.setName("spring"); // 이름설정해주고

        repository.save(member); // 만든 멤버를 저장해보고

        //잘 저장됬는지 id를 리턴받아본다. 반환값이 Optional<Member>인데 .get()으로 Optional안에있는값을 꺼내볼수있다.
        //.get()말고 다른게쓰이긴하는데 테스트에서는 그냥 쓰인다고함.
        Member result = repository.findById(member.getId()).get();

        //검증방법 1. 내가 만든 member와 , result로 받은 멤버가 같은지 비교
//        System.out.println("result = " + (result == member));

        //하지만 콘솔에 찍어서 눈으로 확인하는 방법보다는 Assertions.assertThat(member).isEqualTo(result)를 많이쓴다.
        assertThat(member).isEqualTo(result); //실제값 예상값 순으로 넣는다.
        //돌려보면 출력되는건 없지만 잘돌아간다면 Run 왼쪽아래에 녹색체크모양이 뜬다.

    }
    @Test
    public void findByName() {
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); //shift+F6으로 편하게 수정해주고
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll(); //컨트롤+알트+v로 편하게 변수 생성해준다.

        assertThat(result.size()).isEqualTo(2); // 갯수로 테스트해본다.
    }





}
