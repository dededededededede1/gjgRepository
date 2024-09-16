

- spring aop 各个通知的执行时机

- Spring AOP（面向切面编程）是 Spring 框架中实现横切关注点的技术。在 Spring AOP 中，通知（Advice）是在特定的连接点（JoinPoint）上执行的动作。以下是 Spring AOP 的五种类型通知，以及它们的执行时机：
1. 前置通知（Before）：在目标方法执行之前执行。
2. 后置通知（After）：在目标方法执行之后执行，无论方法是否成功完成。
3. 返回通知（AfterReturning）：在目标方法执行成功并返回结果之后执行。
4. 异常通知（AfterThrowing）：在目标方法抛出异常后执行。
5. 环绕通知（Around）：在目标方法执行前后执行，可以控制目标方法是否执行以及如何执行。

以下是一个简单的代码演示，以说明这些通知类型及其执行时机：

```java
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.ProceedingJoinPoint;

@Aspect
public class LoggingAspect {

    @Before("execution(* com.example.service.*.*(..))")
    public void beforeAdvice() {
        System.out.println("前置通知：目标方法执行之前...");
    }

    @After("execution(* com.example.service.*.*(..))")
    public void afterAdvice() {
        System.out.println("后置通知：目标方法执行之后...");
    }

    @AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
    public void afterReturningAdvice(Object result) {
        System.out.println("返回通知：目标方法执行成功，返回结果：" + result);
    }

    @AfterThrowing(pointcut = "execution(* com.example.service.*.*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        System.out.println("异常通知：目标方法抛出异常：" + exception);
    }

    @Around("execution(* com.example.service.*.*(..))")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("环绕通知：目标方法执行之前...");
        Object result = joinPoint.proceed(); // 执行目标方法
        System.out.println("环绕通知：目标方法执行之后...");
        return result;
    }
}
```



在这个例子中，我们定义了一个切面 `LoggingAspect`，并实现了五种通知类型。我们使用了 AspectJ 语法来定义切点表达式（`execution(* com.example.service.*.*(..))`），这意味着这些通知将在 `com.example.service` 包中的所有类的所有方法上应用。每个通知都打印一条消息，以说明它们的执行时机。
