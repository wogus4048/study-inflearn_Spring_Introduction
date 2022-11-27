package hello.hellospring.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {



    @GetMapping("hello") //   도메인 /hello 할시 이 함수 실행
    public String hello(Model model)
    {
        //모델 뷰 컨트롤러의 그 모델임
        model.addAttribute("data","hello!");
        return "hello";

    }

    @GetMapping("hello-mvc")
    public String helloMVC(@RequestParam("name" )String name ,Model model)
    {
        model.addAttribute("name",name); // 파람에서 넘어온걸 모델에 담는다.
        return "hello-template";

    }
    @GetMapping("hello-string")
    @ResponseBody // html 통신프로토콜에서 헤더와 바디가있는데 , 바디에다가 직접 데이터를 넣어주겠다는 뜻 // 템플릿엔진없이 뷰없이 그냥 값을 때려넣는다.
    public String helloString(@RequestParam("name")String name )
    {
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name")String name )
    {
        Hello hello = new Hello();
        hello.setName(name);
        return hello;

    }

    static class Hello
    {
        private String name;


        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }


    }


}
