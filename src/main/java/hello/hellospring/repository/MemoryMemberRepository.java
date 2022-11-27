package hello.hellospring.repository;

import hello.hellospring.domain.Member;

import java.util.*;
import org.springframework.stereotype.Repository;

public class MemoryMemberRepository implements MemberRepository {
    //실무에서는 동시성문제가있을수있어서 공유되는 변수일때는 다른걸 써야하는데 여기는 예제이므로 Map으로 진행
    //저장할 변수를 선언해준다.
    private static Map<Long,Member> store = new HashMap<>();
    private static long sequence = 0L; //0,1,2처럼 키값을 생성해주는 역할


    @Override
    public Member save(Member member) { //멤버가 넘어올때 이름은 넘어온다고보고
        member.setId(++sequence); // 아이디는 시스템상에서 정해주고
        store.put(member.getId(), member); //그값을 저장
        return member; // 저장된걸 반환
    }
    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id)); //id를 이용하여 Map에서는 키값이니까 키값에 해당한 밸류를 가져온다.
        //하지만 값이 없을경우에 null이 반환될텐데 null이 반환되는것을 막기위해 Optional.ofNullable()이라는것으로 감싸준다.
        //감싸서 보내주면 클라이언트에서 뭔가 할 수 있음
    }

    @Override
    public Optional<Member> findByName(String name) {
        //.values()는 해당 map의 value 목록을 Collection 형태로 리턴한다. 여기서는 Collection<Member> 형태로 반환.
        return store.values().stream() // 밸류값 콜렉션 객체로 스트림객체를 만들어주고
                .filter(member -> member.getName().equals(name)) // 스트림객체에서 입력으로 들어온 이름과 멤버 네임을 가진애들만 뽑아서 구성해주고
                .findAny(); //그 중 아무거나 하나를 고른다, 그 후 return이므로 그걸 반환해줌 , 없다면 null 하지만 optional로 감싸져서 리턴된다.

    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }


    public void clearStore() { //인터페이스에 있는걸 구현한게아닌 해당 클래스에서 구현한 고유의 메소드
        store.clear(); //
    }
}
