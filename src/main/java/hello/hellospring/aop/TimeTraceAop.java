package hello.hellospring.aop;


import net.bytebuddy.implementation.bytecode.Throw;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect //AOP는 해당 어노테이션을 붙여줘야한다.
@Component //이렇게해서 해당 클래스를 스프링 빈 등록할수있다.(컴포넌트 어노테이션을 쓰면 컴포넌트스캔에 걸리게되서 스프링빈등록됨)
// 또는 SpringConfig에 빈등록해서 사용가능.
public class TimeTraceAop {
    @Around("execution(* hello.hellospring..*(..))") //어디다가 적용할것인지를 적는곳,해당 예시는 hello.hellospring하위에는 전부적용을 의미
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        System.out.println("START " + joinPoint.toString());
        try {
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}





