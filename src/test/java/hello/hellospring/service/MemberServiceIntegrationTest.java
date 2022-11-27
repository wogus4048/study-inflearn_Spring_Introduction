package hello.hellospring.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class MemberServiceIntegrationTest {

    @Autowired
    MemberService memberService; //멤버 서비스 선언
    @Autowired
    MemberRepository memberRepository;//멤버 저장소 선언


    @Test
    void 회원가입() { //테스트 메소드는 한글로도 이름을 정할 수 있음.
        //given 값이 주어졌을때
        Member member = new Member();
        member.setName("spring");

        //when  실행했을때
        Long saveId = memberService.join(member);

        //then 결과가 이렇게 나와야한다.
        Member findMember = memberService.findOne(saveId).get(); // .get을 이용해서 optional을 벗긴 멤버값을 받을 수 있다.
        assertThat(member.getName()).isEqualTo(findMember.getName()); //방금만든 멤버의 이름이 나와야한다.
        //그러므로 멤버의 이름이 실제값(actual) findmember 했을때 나오는 이름이 예상값(expected)

    }

    @Test
    public void 중복_회원_예외() {
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");
        //when
        memberService.join(member1); // member1을 회원가입
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));// 메시지검증을 하고싶다면 리턴값을 저장해서
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다"); //검증한다.

//        try {
//            memberService.join(member2); // member2를 회원가입 시킬때 중복된 이름으로 인해 오류가 발생해야함
//            //이 다음줄로 넘어간다는것은 오류가 발생해야하는데 catch로 가지않았다는것이므로
//            fail();// 테스트 실패!
//
//        }catch (IllegalStateException e )
//        {
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다"); //catch에 아무것도 안적어도 try catch로 잘넘어갔기때문에 테스트하면 성공이 뜬다.
//        }

        //then
    }


    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}