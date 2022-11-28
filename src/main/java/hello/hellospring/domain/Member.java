package hello.hellospring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity //jpa가 관리하는 엔티티라는 뜻

//아래는 lombok관련 어노테이션
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)//@Id로 pk를 매핑해준다, db에 멤버이름만 넣어도 키값이 자동으로 생성되는것을 identity 전략이라고한다.
    //멤버에는 그런 전략을 사용했으므로 @GeneratedValue로 해당 전략에 대해 적어준다.
    private Long id;

//    @Column(name = "username") // 만약에 db상에서 컬럼이름이 username이라면 @Column 어노테이션을 이용해서 매핑해준다, 컬렴명과 변수명이 같다면 필요없다.
    private String name;

}
