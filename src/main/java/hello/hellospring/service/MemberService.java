package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
public class MemberService {

    private final MemberRepository memberRepository; //여기서는 멤버저장소 인터페이스를 선언한다. 그렇게 함으로서 해당 인터페이스를 구현한 저장소객체를 다 받을수 있기떄문.


    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    /*
            회원가입
         */
    public Long join(Member member) {
        //같은 이름이 있는 회원은 안됨 (중복방지-> 임의로 지정한 규칙)
        validateDuplicateMember(member);  //중복회원 검증 메소드
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
            .ifPresent(m -> { // findbyname이라는 메소드의 리턴값은 optinal임 거기다가 바로 ifpresent를 사용한것임
                throw new IllegalStateException("이미 존재하는 회원입니다");
                // .ifPresent() 만약 값이 있다면 -> 안의 람다식이나 메소드 레퍼런스가 올 수 있다. 여기서는 Consumer(함수형 인터페이스 = 람다로 구현가능)가 왔다.
                // m -> 에서 m 을 이용해도 되는데 여기서는 throw 만 해주면 되니까 throw 해줌.  Exception를 날리면서 메시지도 날려줬다.
                //findbyname은 optinal<member>를 리턴한다.
                //.get()을 이용해서 직접 꺼낼수도 있지만 권장하지않는다 -> optinal을 이용하는 이유가 null일수도 있어서 사용한거라
                //아니면 값이있으면 꺼내고 아니면 안에있는 메소드를 실행하는 .orElseGet 이라는 메소드가 있다.
                //따로 optinal 변수에 담고 .findbyname을 실행할 수 있지만 더 깔끔하게 -> 리팩토링
            });
    }

    /*
    전체 회원 조회
     */
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    /*
    멤버 아이디를 이용한 멤버조회
     */
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }


}
