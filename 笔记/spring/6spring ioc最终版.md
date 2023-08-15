- Spring IoC 

目录： 

1. 简介 
2. 环境搭建 
3. Hello World 示例 
4. Spring IoC 容器 
5. Spring Bean 的作用域 
6. Bean 的生命周期 
7. 依赖注入 
8. 基于构造函数的依赖注入 
9. 基于 Setter 方法的依赖注入 
10. 基于注解的依赖注入 
11. Java 配置类 
12. 自动装配 
13. 注解和组件扫描 
14. 事件处理 
15. AOP（面向切面编程） 
16. 简介

Spring IoC（控制反转）是 Spring 框架的核心，它负责管理应用程序中的对象及其之间的关系。IoC 容器负责创建、配置和管理应用程序中的对象。本教程将教您如何使用 Spring IoC 容器来管理对象及其依赖关系。

1. 环境搭建

本教程使用以下环境和工具：

- JDK 1.8 或更高版本
- Apache Maven 3.x
- Spring Framework 5.x
- 一个支持 Java 的 IDE，例如 IntelliJ IDEA 或 Eclipse

### spring基于xml的Hello World 示例

本节将展示一个简单的 Spring IoC 示例，以帮助您快速了解 Spring IoC 容器的基本概念和用法。

3.1 创建一个简单的 Java 类

首先，我们创建一个名为 `HelloWorld` 的简单 Java 类，它具有一个方法 `sayHello()`，用于在控制台打印 "Hello, World!"。

```java
public class HelloWorld {
    public void sayHello() {
        System.out.println("Hello, World!");
    }
}
```



3.2 创建一个 XML 配置文件

接下来，我们将创建一个名为 `applicationContext.xml` 的 XML 配置文件，用于定义和配置 `HelloWorld` 类的 bean：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="helloWorld" class="com.example.HelloWorld"/>

</beans>
```



3.3 创建一个 Java 程序

接下来，我们将创建一个名为 `Main` 的 Java 程序，用于从 Spring IoC 容器获取 `HelloWorld` 类的实例，并调用其 `sayHello()` 方法：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        HelloWorld helloWorld = context.getBean("helloWorld", HelloWorld.class);
        helloWorld.sayHello();
    }
}
```



3.4 运行程序

运行 `Main` 类，控制台输出如下：

```go
Hello, World!
``
```

Spring IoC 容器负责创建、配置和管理应用程序中的对象。IoC 容器通过读取配置元数据（如 XML 文件、Java 配置类或注解）来管理对象及其依赖关系。Spring 框架提供了两种类型的 IoC 容器：

- BeanFactory：这是一个基本的 IoC 容器，它提供了对象创建、配置和管理的基本功能。虽然它功能较少，但它的资源占用较小，适用于资源受限的环境。
- ApplicationContext：这是一个更高级的 IoC 容器，它继承自 BeanFactory，提供了更多的功能，如事件处理、国际化支持和 AOP 集成。它是 Spring 框架中最常用的 IoC 容器。

在本教程中，我们将主要使用 ApplicationContext 作为 IoC 容 器。

### 								Spring 基于注解的使用



- 在本节中，我们将讲解如何使用基于注解的配置来设置和管理 Spring 应用程序。基于注解的配置可以使我们避免在 XML 文件中进行繁琐的配置，从而简化应用程序的设置。

1. 注解

以下是 Spring 中一些常用的注解：

- @Component：表示一个类是 Spring 管理的 bean。
- @Autowired：表示自动装配 bean 之间的依赖关系。
- @Qualifier：当有多个相同类型的 bean 时，用于指定具体需要注入的 bean。
- @Configuration：表示一个类包含 Spring 应用程序的配置信息。
- @Bean：表示一个方法返回一个 Spring 管理的 bean。
- @ComponentScan：表示 Spring 应该在指定的包中搜索带有 @Component 注解的类。
- @Service：表示一个类是业务层组件，它是 @Component 的特殊化。
- @Repository：表示一个类是数据访问层组件，它是 @Component 的特殊化。
- @Controller：表示一个类是 Web 层组件，它是 @Component 的特殊化。

1. 示例

下面是一个基于注解的配置的简单示例：

2.1 创建 Engine 接口及其实现类 GasEngine：

```java
package com.jnbat.pojo;
public interface Engine {
    void start();
}
package com.jnbat.pojo;
@Component
public class GasEngine implements Engine {
    public void start() {
        System.out.println("Gas engine started.");
    }
}
```



2.2我们需要在 `applicationContext.xml` 中开启组件扫描：

Spring 支持使用注解来定义和配置 bean，如 `@Component`、`@Repository`、`@Service` 和 `@Controller`。要启用组件扫描，需要在 XML 配置文件中添加 `<context:component-scan>` 元素，或者在 Java 配置类中使用 `@ComponentScan` 注解

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">
	<!--注解被扫描的包路径-->
    <context:component-scan base-package="com.jnbat.spring"/>
	
</beans>
```

​	2.3继续用以前测试方式写测试类一

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        Engine engine =(Engine) context.getBean("gasEngine");
        engine.start();
    }
}
```



2.3 用类配置类测试，首先创建 AppConfig 配置类，并启用组件扫描：

```java
@Configuration
@ComponentScan("com.example")
public class AppConfig {
}
```



2.4 修改主程序，使用 AnnotationConfigApplicationContext 替换 ClassPathXmlApplicationContext：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean(Car.class);
        car.start();
    }
}
```





现在，运行主程序，将看到 "Gas engine started." 的输出。

这个简单的示例展示了如何使用基于注解的配置来创建和管理 Spring bean。通过使用注解，我们可以避免 XML 配置，使应用程序更加简洁。

1. 注入值

除了依赖注入，我们还可以使用 @Value 注解来注入属性值。例如：

```java
@Component
public class Car {
    private Engine engine;

    @Value("${car.name}")
    private String name;

    // ...
}
```



要使用 @Value 注解注入值，我们需要在配置文件中定义属性，例如在 `application.properties` 文件中：

```
Copy code
car.name=MyCar
```



并在 Java 配置类中添加 `@PropertySource` 注解，以加载属性文件：

```java
@Configuration
@ComponentScan("com.example")
@PropertySource("classpath:application.properties")
public class AppConfig {
}
```

现在，Car 类的 name 属性将被注入 "MyCar" 值。

### Java 配置类

除了 XML 配置，Spring 还支持使用 Java 配置类来定义和配置 bean。以下是一个使用 Java 配置类的示例。

首先，我们创建一个名为 `AppConfig` 的 Java 配置类：

```java
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Engine gasEngine() {
        return new GasEngine();
    }

    @Bean
    public Engine electricEngine() {
        return new ElectricEngine();
    }

    @Bean
    public Car car() {
        Car car = new Car();
        car.setEngine(gasEngine());
        return car;
    }
}
```



接下来，我们修改主程序，使用 `AnnotationConfigApplicationContext` 替换 `ClassPathXmlApplicationContext`：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean("car", Car.class);
        car.start();
    }
}
```



现在，运行主程序，将看到 "Gas engine started." 的输出。


```java

4. 使用 @Profile

@Profile 注解允许我们根据当前环境（如开发、测试或生产）选择不同的配置。例如，我们可以为 Engine 接口创建两个实现类 ElectricEngine 和 GasEngine，并为每个实现类指定一个环境：


@Component
@Profile("dev")
public class ElectricEngine implements Engine {
    public void start() {
        System.out.println("Electric engine started.");
    }
}

@Component
@Profile("prod")
public class GasEngine implements Engine {
    public void start() {
        System.out.println("Gas engine started.");
    }
}
```



在主程序中，我们可以通过设置当前环境来选择不同的 Engine 实现类：

```java
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        System.setProperty("spring.profiles.active", "dev");

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Car car = context.getBean(Car.class);
        car.start();
    }
}
```



在这个例子中，我们将当前环境设置为 "dev"，所以 ElectricEngine 将被注入到 Car 类中。运行主程序，将看到 "Electric engine started." 的输出。

### 依赖注入



依赖注入（Dependency Injection，DI）是一种设计模式，用于实现对象之间的松耦合。通过依赖注入，对象不需要显式地创建和管理它所依赖的其他对象。相反，这些依赖关系由 IoC 容器在运行时自动注入。

在 Spring 框架中，依赖注入可以通过构造函数、Setter 方法或注解实现。接下来的章节将详细介绍这些方法。

1. 基于构造函数的依赖注入

基于构造函数的依赖注入是通过在类的构造函数中提供依赖关系来实现的。以下是一个基于构造函数的依赖注入的示例。

首先，我们创建一个接口 `Engine` 和两个实现类 `GasEngine` 和 `ElectricEngine`：

```java
public interface Engine {
    void start();
}

public class GasEngine implements Engine {
    public void start() {
        System.out.println("Gas engine started.");
    }
}

public class ElectricEngine implements Engine {
    public void start() {
        System.out.println("Electric engine started.");
    }
}
```



接下来，我们创建一个 `Car` 类，它依赖于 `Engine` 接口：

```java
public class Car {
    private Engine engine;

    public Car(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }
}
```



我们需要在 `applicationContext.xml` 中配置这些类：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="gasEngine" class="com.example.GasEngine"/>
    <bean id="electricEngine" class="com.example.ElectricEngine"/>
    <bean id="car" class="com.example.Car">
        <constructor-arg ref="gasEngine"/>
    </bean>

</beans>
```



现在，运行主程序，将看到 "Gas engine started." 的输出。

2.基于 Setter 方法的依赖注入

基于 Setter 方法的依赖注入是通过在类的 Setter 方法中提供依赖关系来实现的。以下是一个基于 Setter 方法的依赖注入的示例。

我们修改 `Car` 类，将依赖注入从构造函数改为 Setter 方法：

```java
public class Car {
    private Engine engine;

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }
}
```



我们需要在 `applicationContext.xml` 中配置这些类：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd">

    <bean id="gasEngine" class="com.example.GasEngine"/>
    <bean id="electricEngine" class="com.example.ElectricEngine"/>
    <bean id="car" class="com.example.Car">
        <property name="engine" ref="gasEngine"/>
    </bean>

</beans>
```

现在，运行主程序，将看到 "Gas engine started." 的输出。

3.基于注解的依赖注入

基于注解的依赖注入是通过在类的属性或方法上添加注解来实现的。以下是一个基于注解的依赖注入的示例。

我们修改 `Car` 类，将依赖注入从 Setter 方法改为注解：

```java
import org.springframework.beans.factory.annotation.Autowired;

public class Car {
    private Engine engine;

    @Autowired
    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public void start() {
        engine.start();
    }
}
```



我们需要在 `applicationContext.xml` 中启用注解支持，并配置这些类：

```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
       http://www.springframework.org/schema/beans/spring-beans.xsd
       http://www.springframework.org/schema/context
       http://www.springframework.org/schema/context/spring-context.xsd">

    <context:annotation-config/>

    <bean id="gasEngine" class="com.example.GasEngine"/>
    <bean id="electricEngine" class="com.example.ElectricEngine"/>
    <bean id="car" class="com.example.Car"/>

</beans>
```



现在，运行主程序，将看到 "Gas engine started." 的输出。

1. 自动装配

自动装配是 Spring IoC 容器根据类型自动解析 bean 之间的依赖关系的过程。要启用自动装配，可以在 XML 配置文件中使用 `autowire` 属性，或者在 Java 类中使用 `@Autowired` 注解。

​																						

###  Spring Bean 作用域。

- 在 Spring 框架中，Bean 作用域指定了 Bean 在容器中的生命周期和可见性。Spring Bean 作用域决定了在 Spring 容器中创建 bean 实例的方式。Spring 提供了以下作用域。Spring 提供了以下五种 Bean 作用域： 

1. Singleton（默认）：在整个 Spring 容器中，一个类只有一个实例。无论何时请求该类型的 Bean，都将获得相同的实例。Singleton 是默认的作用域，适用于无状态的服务或对象。 
2. Prototype：每次请求 Bean 时，都会创建一个新的实例。这种作用域适用于有状态的对象，每个实例都需要独立维护自己的状态。 
3. Request：在单个 HTTP 请求的生命周期内，这个 Bean 作用域保证了每个请求都有一个实例。这意味着，同一请求内的多个组件将共享相同的 Bean 实例，而不同的请求将获得不同的实例。这种作用域仅在 WebApplicationContext 环境中可用。 
4. Session：在单个 HTTP 会话的生命周期内，这个 Bean 作用域保证了每个会话都有一个实例。这意味着，同一会话内的多个请求将共享相同的 Bean 实例，而不同的会话将获得不同的实例。这种作用域仅在 WebApplicationContext 环境中可用。 
5. Application：在整个 ServletContext 生命周期内，这个 Bean 作用域保证了一个实例。这意味着，应用程序内的所有会话和请求都将共享相同的 Bean 实例。这种作用域仅在 WebApplicationContext 环境中可用。 
6. WebSocket：在单个 WebSocket 会话的生命周期内，这个 Bean 作用域保证了每个 WebSocket 会话都有一个实例。这种作用域仅在 WebApplicationContext 环境中可用。

以下是一个代码示例，说明了如何使用不同的 Spring Bean 作用域：

1. 创建一个简单的 bean 类（MessageService）：

```java
public class MessageService {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
```


1. 在配置文件（applicationContext.xml）中定义 bean 的作用域：

```xml
<bean id="singletonMessageService" class="com.example.MessageService" scope="singleton">
    <property name="message" value="Hello from Singleton scope" />
</bean>

<bean id="prototypeMessageService" class="com.example.MessageService" scope="prototype">
    <property name="message" value="Hello from Prototype scope" />
</bean>
```



在这个例子中，我们创建了两个 MessageService bean，一个具有 singleton 作用域，另一个具有 prototype 作用域。

1. 编写一个简单的主程序来演示不同作用域的行为：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Singleton scope:");
        MessageService singletonMessageService1 = (MessageService) context.getBean("singletonMessageService");
        System.out.println(singletonMessageService1.getMessage());
        MessageService singletonMessageService2 = (MessageService) context.getBean("singletonMessageService");
        System.out.println(singletonMessageService2.getMessage());

        System.out.println("\nPrototype scope:");
        MessageService prototypeMessageService1 = (MessageService) context.getBean("prototypeMessageService");
        System.out.println(prototypeMessageService1.getMessage());
        MessageService prototypeMessageService2 = (MessageService) context.getBean("prototypeMessageService");
        System.out.println(prototypeMessageService2.getMessage());
    }
}
```



当运行这个程序时，您将看到以下输出：

```sql
Singleton scope:
Hello from Singleton scope
Hello from Singleton scope

Prototype scope:
Hello from Prototype scope
Hello from Prototype scope
```



在这个示例中，singleton 作用域的 bean 在整个应用程序中只有一个实例，而 prototype 作用域的 bean 每次请求时都会创建一个新的实例。这表明作用域设置确实影响了 bean 实例的创建方式。

注意：request、session、application 和 websocket 作用域通常在 web 应用程序中使用，它们需要在 WebApplicationContext 环境中运行。这些作用域允许您在 Web 应用程序的不同层次上存储 bean，例如在 HTTP 请求、HTTP 会话、ServletContext 或 WebSocket 会话中。

通过使用不同的作用域，您可以根据需要灵活地管理 bean 实例及其生命周期。



接下来，我们将通过一个简单的示例来说明 Singleton 和 Prototype 作用域。

首先，创建一个简单的 Person 类：

```java
public class Person {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
```



接下来，在 Spring 配置文件中（例如 applicationContext.xml）定义两个 Person bean，一个为 Singleton 作用域，另一个为 Prototype 作用域：

```xml
<bean id="singletonPerson" class="com.example.Person" scope="singleton">
    <constructor-arg value="John Singleton" />
</bean>

<bean id="prototypePerson" class="com.example.Person" scope="prototype">
    <constructor-arg value="Jane Prototype" />
</bean>
```



编写一个简单的主程序来展示 Singleton 和 Prototype 作用域的行为：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

      System.out.println("Singleton scope:");
      Person singletonPerson1 = context.getBean("singletonPerson", Person.class);
      System.out.println(singletonPerson1.getName());
      Person singletonPerson2 = context.getBean("singletonPerson", Person.class);
      System.out.println(singletonPerson2.getName());

      System.out.println("\nPrototype scope:");
      Person prototypePerson1 = context.getBean("prototypePerson", Person.class);
      System.out.println(prototypePerson1.getName());
      Person prototypePerson2 = context.getBean("prototypePerson", Person.class);
      System.out.println(prototypePerson2.getName());
  }
}
```



```
运行这个程序，您将看到以下输出：

```rust
Singleton scope:
John Singleton
John Singleton

Prototype scope:
Jane Prototype
Jane Prototype
```



在这个示例中，您可以看到，对于 Singleton 作用域，无论请求多少次，我们都会得到相同的实例。然而，对于 Prototype 作用域，每次请求都会创建一个新的实例。

总结一下，理解不同的 Spring Bean 作用域及其适用场景非常重要。Singleton 作用域适用于无状态的服务或对象，而 Prototype 作用域适用于需要独立维护状态的对象。Request、Session、Application 和 WebSocket 作用域主要用于 Web 应用程序中，以根据不同的生命周期来管理 bean 实例。通过正确选择合适的作用域，您可以更好地控制 bean 的生命周期和可见性，从而优化应用程序的性能和资源管理。



### 再用一个购物商场的例子来说明 Singleton 和 Prototype 作用域。

假设我们有两个类：`Customer` 和 `ShoppingCart`。`Customer` 类代表商场的顾客，`ShoppingCart` 类代表顾客在商场中使用的购物车。我们希望每个顾客都有自己的购物车，但是商场内的广播系统（例如广播优惠活动信息）对所有顾客是一样的。

首先，创建一个 `Customer` 类和一个 `ShoppingCart` 类：

```java
public class Customer {
    private String name;

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

public class ShoppingCart {
    private List<String> items = new ArrayList<>();

    public void addItem(String item) {
        items.add(item);
    }

    public List<String> getItems() {
        return items;
    }
}
```



接下来，在 Spring 配置文件（例如 applicationContext.xml）中定义一个 Singleton 作用域的 `ShoppingCart` bean 和一个 Prototype 作用域的 `Customer` bean：

```xml
<bean id="shoppingCart" class="com.example.ShoppingCart" scope="singleton" />

<bean id="customer" class="com.example.Customer" scope="prototype">
    <constructor-arg value="John Doe" />
</bean>
```



现在我们编写一个简单的主程序来展示 Singleton 和 Prototype 作用域的行为：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Singleton scope (ShoppingCart):");
        ShoppingCart shoppingCart1 = context.getBean("shoppingCart", ShoppingCart.class);
        shoppingCart1.addItem("Apple");
        ShoppingCart shoppingCart2 = context.getBean("shoppingCart", ShoppingCart.class);
        shoppingCart2.addItem("Banana");
        System.out.println("ShoppingCart 1 items: " + shoppingCart1.getItems());
        System.out.println("ShoppingCart 2 items: " + shoppingCart2.getItems());

        System.out.println("\nPrototype scope (Customer):");
        Customer customer1 = context.getBean("customer", Customer.class);
        Customer customer2 = context.getBean("customer", Customer.class);
        System.out.println("Customer 1: " + customer1.getName());
        System.out.println("Customer 2: " + customer2.getName());
    }
}
```



运行这个程序，您将看到以下输出：

```yaml
Singleton scope (ShoppingCart):
ShoppingCart 1 items: [Apple, Banana]
ShoppingCart 2 items: [Apple, Banana]

Prototype scope (Customer):
Customer 1: John Doe
Customer 2: John Doe
```



在这个示例中，您可以看到，对于 Singleton 作用域的 `ShoppingCart`，无论请求多少次，我们都会得到相同的实例。这意味着，所有顾客实际上共享同一个购物车。然而，对于 Prototype 作用域的 `Customer`，每次请求都会创建一个新的实例，这意味着每个顾客都是独立的。

请注意，这个例子仅用于说明 Singleton 和 Prototype 作用域的行为。在实际应用场景中，我们可能会为每个顾客分配一个独立的购物车，因此 `ShoppingCart` 类应该使用 Prototype 作用域。同时，商场广播系统仅需要一个实例，因此可以使用 Singleton 作用域。

下面我们修改配置文件，将 `ShoppingCart` 的作用域更改为 Prototype：

```xml
<bean id="shoppingCart" class="com.example.ShoppingCart" scope="prototype" />

<bean id="customer" class="com.example.Customer" scope="prototype">
    <constructor-arg value="John Doe" />
</bean>
```



接着，我们修改主程序以展示每个顾客都拥有独立的购物车：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Prototype scope (ShoppingCart):");
        ShoppingCart shoppingCart1 = context.getBean("shoppingCart", ShoppingCart.class);
        shoppingCart1.addItem("Apple");
        ShoppingCart shoppingCart2 = context.getBean("shoppingCart", ShoppingCart.class);
        shoppingCart2.addItem("Banana");
        System.out.println("ShoppingCart 1 items: " + shoppingCart1.getItems());
        System.out.println("ShoppingCart 2 items: " + shoppingCart2.getItems());

        System.out.println("\nPrototype scope (Customer):");
        Customer customer1 = context.getBean("customer", Customer.class);
        Customer customer2 = context.getBean("customer", Customer.class);
        System.out.println("Customer 1: " + customer1.getName());
        System.out.println("Customer 2: " + customer2.getName());
    }
}
```



现在，运行程序后的输出将显示每个顾客拥有独立的购物车：

```yaml
Prototype scope (ShoppingCart):
ShoppingCart 1 items: [Apple]
ShoppingCart 2 items: [Banana]

Prototype scope (Customer):
Customer 1: John Doe
Customer 2: John Doe
```



这个例子展示了如何根据实际需求选择合适的作用域。对于每个顾客都需要独立的资源（例如购物车）的情况，使用 Prototype 作用域是合适的。对于整个应用程序共享的资源（例如商场广播系统），使用 Singleton 作用域是合适的。

理解 Spring Bean 作用域及其用例有助于更好地管理应用程序中的资源。这可以帮助您优化应用程序的性能和资源利用率，从而提高应用程序的可扩展性和可维护性。

### 		Bean 的生命周期

Bean 的生命周期是指从创建到销毁的过程。在这个过程中，Spring IoC 容器负责管理 bean 的生命周期，并在适当的时候调用初始化和销毁方法。要自定义 bean 的初始化和销毁方法，可以在 XML 配置文件中使用 `init-method` 和 `destroy-method` 属性，或者在 Java 类中使用 `@PostConstruct` 和 `@PreDestroy` 注解。

- Spring Bean 生命周期是指从创建 Bean 的实例到销毁 Bean 之间经历的各个阶段。下面详细解释一下 Spring Bean 生命周期，并通过代码演示说明。

Spring Bean 生命周期主要包括以下步骤：

1. 实例化
2. 设置 Bean 属性
3. 调用 Bean 的初始化方法
4. Bean 可用于应用程序
5. 调用 Bean 的销毁方法

现在，让我们通过一个简单的例子来演示这些步骤。首先，创建一个名为 `MyBean` 的类，并在其中添加一些方法：

```java
public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your message: " + message);
    }

    public void init() {
        System.out.println("Bean is going through the init process.");
    }

    public void destroy() {
        System.out.println("Bean is going through the destroy process.");
    }
}
```



接下来，在 Spring 配置文件（例如 applicationContext.xml）中定义 `MyBean` 并指定其初始化和销毁方法：

```xml
<bean id="myBean" class="com.example.MyBean" init-method="init" destroy-method="destroy">
    <property name="message" value="Hello, World!" />
</bean>
```



最后，编写一个简单的主程序来展示 Bean 生命周期的各个阶段：

```java
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        AbstractApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Getting bean from the context:");
        MyBean myBean = (MyBean) context.getBean("myBean");
        myBean.getMessage();

        System.out.println("\nClosing the context:");
        context.registerShutdownHook();
    }
}
```



运行这个程序，您将看到以下输出：

```csharp
Bean is going through the init process.
Getting bean from the context:
Your message: Hello, World!

Closing the context:
Bean is going through the destroy process.
```



从输出中，您可以看到 Bean 生命周期的各个阶段： 

1. 实例化：Spring 创建了 `MyBean` 的实例。 
2. 设置 Bean 属性：Spring 通过调用 `setMessage` 方法设置了 `MyBean` 的属性。 
3. 调用 Bean 的初始化方法：Spring 调用了 `init` 方法。
4. Bean 可用于应用程序：在这个阶段，我们可以从上下文获取 Bean，并调用其方法。 
5. 调用 Bean 的销毁方法：当关闭应用程序上下文时，Spring 调用了 `destroy` 方法来清理资源。

了解 Spring Bean 生命周期有助于更好地管理 Bean 的创建、初始化和销毁过程，从而提高应用程序的性能和可维护性。实际开发中，您可以根据需要定制 Bean 的生命周期，例如，在初始化方法中执行资源分配，或在销毁方法中执行资源释放

### Spring Bean 后置处理器



- Spring Bean 后置处理器允许我们在 Bean 初始化方法之前和之后对 Bean 进行额外的处理。Bean 后置处理器实现了 `BeanPostProcessor` 接口，它有两个主要方法：`postProcessBeforeInitialization` 和 `postProcessAfterInitialization`。这些方法分别在 Bean 初始化方法之前和之后执行。

让我们通过一个示例来演示 Bean 后置处理器的功能。首先，创建一个名为 `MyBean` 的类，并在其中添加初始化方法和一个简单的方法：

```java
public class MyBean {
    private String message;

    public void setMessage(String message) {
        this.message = message;
    }

    public void getMessage() {
        System.out.println("Your message: " + message);
    }

    public void init() {
        System.out.println("Bean is going through the init process.");
    }
}
```



接下来，创建一个实现 `BeanPostProcessor` 接口的 `MyBeanPostProcessor` 类：

```java
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

public class MyBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("Before initialization of bean " + beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("After initialization of bean " + beanName);
        return bean;
    }
}
```



在 Spring 配置文件（例如 applicationContext.xml）中定义 `MyBean` 和 `MyBeanPostProcessor`：

```xml
<bean id="myBean" class="com.example.MyBean" init-method="init">
    <property name="message" value="Hello, World!" />
</bean>

<bean class="com.example.MyBeanPostProcessor" />
```



最后，编写一个简单的主程序来展示 Bean 后置处理器的功能：

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        System.out.println("Getting bean from the context:");
        MyBean myBean = (MyBean) context.getBean("myBean");
        myBean.getMessage();
    }
}
```



运行这个程序，您将看到以下输出：

```csharp
Before initialization of bean myBean
Bean is going through the init process.
After initialization of bean myBean
Getting bean from the context:
Your message: Hello, World!
```



从输出中，您可以看到 `MyBeanPostProcessor` 的 `postProcessBeforeInitialization` 方法在 `MyBean` 的初始化方法之前执行，而 `postProcessAfterInitialization` 方法在初始化方法之后执行。

Bean 后置处理器提供了在 Bean 生命周期的特定阶段执行自定义逻辑的能力，这对于执行一些额外的配置、校验或者资源处理等操作非常有用。实际开发中，您可以根据需要编写自己的 Bean 后置处理器，以实现更高级的功能。



- 继续了解 Spring Bean 后置处理器，我们将深入了解一些常见用例。
  ### 

您可以在 Bean 初始化过程中通过后置处理器修改 Bean 的属性。例如，您可以在所有 Bean 的名称后附加一个特定的后缀。

```java
public class MyBeanPostProcessor implements BeanPostProcessor {
    // ...

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            ((MyBean) bean).setMessage(((MyBean) bean).getMessage() + " [Processed]");
        }
        return bean;
    }
}
```



现在，当您运行主程序时，您将看到消息后面附加了 `[Processed]` 后缀：

```less
Your message: Hello, World! [Processed]
```


### 

Bean 后置处理器还可以用于创建动态代理。代理对象可以截获原始 Bean 的方法调用，并在执行实际方法之前或之后执行其他操作。

```java
public class MyBeanPostProcessor implements BeanPostProcessor {
    // ...

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof MyBean) {
            return Proxy.newProxyInstance(
                bean.getClass().getClassLoader(),
                bean.getClass().getInterfaces(),
                (proxy, method, args) -> {
                    System.out.println("Intercepted method: " + method.getName());
                    return method.invoke(bean, args);
                }
            );
        }
        return bean;
    }
}
```



此时，`MyBean` 类需要实现一个接口，以便我们可以使用动态代理。以下是一个简单的接口示例：

```java
public interface MyBeanInterface {
    void getMessage();
}
```



修改 `MyBean` 类以实现 `MyBeanInterface`：

```java
public class MyBean implements MyBeanInterface {
    // ...
}
```



现在，当您运行主程序时，您将看到在执行 `getMessage` 方法之前，有一个额外的输出：

```yaml
Intercepted method: getMessage
Your message: Hello, World!
```



这只是 Spring Bean 后置处理器功能的一小部分。实际上，您可以利用后置处理器在 Bean 生命周期的适当阶段实现各种复杂的逻辑。深入了解后置处理器功能并结合实际需求，您将能够充分利用 Spring 框架的灵活性和强大功能。



- JSR-250 是 Java 规范的一个子集，它定义了一组通用的 Java 注解，以简化 Java EE 和 Spring 应用程序的开发。这些注解在 Spring 框架中得到了广泛支持，使得开发人员能够轻松地实现依赖注入和其他常见功能。在本节中，我们将作为一名资深 Java 讲师，讲解一下 Spring 中支持的主要 JSR-250 注解。 

1. `@Resource`

`@Resource` 注解用于在 Bean 中注入依赖。它是通过名称进行依赖查找，但如果找不到匹配的名称，则会尝试按类型查找。您可以将 `@Resource` 注解应用于属性、Setter 方法或构造函数上。

示例：

```java
import javax.annotation.Resource;

public class MyBean {
    @Resource
    private MyDependency myDependency;
}
```


1. `@PostConstruct`

`@PostConstruct` 注解用于标注 Bean 生命周期中的初始化方法。当 Spring 容器创建 Bean 实例并完成依赖注入后，将调用带有 `@PostConstruct` 注解的方法。

示例：

```java
import javax.annotation.PostConstruct;

public class MyBean {
    @PostConstruct
    public void init() {
        System.out.println("Bean is going through the init process.");
    }
}
```


1. `@PreDestroy`

`@PreDestroy` 注解用于标注 Bean 生命周期中的销毁方法。在 Spring 容器关闭之前，将调用带有 `@PreDestroy` 注解的方法，以便 Bean 可以释放资源或执行其他清理操作。

示例：

```java
import javax.annotation.PreDestroy;

public class MyBean {
    @PreDestroy
    public void destroy() {
        System.out.println("Bean is going through the destroy process.");
    }
}
```



注意：要使用 JSR-250 注解，您需要在项目的依赖中包含 `javax.annotation-api`。对于 Maven 项目，可以在 `pom.xml` 中添加以下依赖：

```xml
<dependency>
    <groupId>javax.annotation</groupId>
    <artifactId>javax.annotation-api</artifactId>
    <version>1.3.2</version>
</dependency>
```



Spring 支持的 JSR-250 注解提供了一种简洁、灵活的方式来实现依赖注入、初始化和销毁等常见功能。在实际开发中，结合这些注解和 Spring 框架的其他功能，您可以更轻松地构建可维护、可扩展的应用程序。

- Java 规范是一组定义 Java 编程语言、Java 虚拟机 (JVM) 和 Java 应用程序接口 (API) 的标准和指南。这些规范确保了 Java 平台的兼容性、一致性和可移植性。Java 规范是由 Java 社区过程 (Java Community Process, JCP) 通过 Java 规范请求 (Java Specification Requests, JSR) 来制定和维护的。

Java 规范主要包括以下几个部分： 

1. **Java 语言规范（Java Language Specification, JLS）** ：Java 语言规范定义了 Java 编程语言的语法、语义以及与其他语言特性相关的内容。JLS 详细描述了 Java 的关键字、运算符、数据类型、类、接口、异常处理、泛型等方面的规定。 
2. **Java 虚拟机规范（Java Virtual Machine Specification）** ：Java 虚拟机规范描述了 Java 字节码（Java 编译器生成的低级代码）的执行方式以及 Java 虚拟机的运行时数据区、指令集、垃圾回收等方面的规定。Java 虚拟机规范确保了各种不同平台上的 Java 虚拟机实现具有一致的行为。 
3. **Java API 规范** ：Java API 规范描述了 Java 标准库中的一系列接口和类。这些 API 提供了实现各种功能（如文件 I/O、网络编程、日期和时间处理、数据结构等）所需的基础构建模块。Java API 规范通常随着 JDK（Java Development Kit）的发布而更新。

除了这些核心规范外，Java 生态系统还包括许多其他规范，如 Java EE（现在称为 Jakarta EE）规范、Java ME 规范等，它们分别针对企业级应用和嵌入式设备应用提供了更高级别的抽象和功能。

Java 规范的目的是确保 Java 代码在不同的平台和实现上的一致性和可移植性。遵循 Java 规范，开发人员可以编写一次代码并在多个平台上运行，这是 Java 的核心优势之一。在实际开发中，了解 Java 规范以及其背后的原则有助于编写健壮、可维护和可扩展的 Java 程序。

### 事件处理

Spring 提供了一个灵活的事件处理机制，允许应用程序组件在不紧密耦合的情况下相互通信。要使用事件处理，需要执行以下步骤： 

1. 创建一个自定义事件，该事件继承自 `ApplicationEvent` 类。 
2. 创建一个事件监听器，该监听器实现 `ApplicationListener` 接口，并重写 `onApplicationEvent()` 方法。
3. 在配置中注册监听器。
4. 在需要的地方发布事件。

以下是一个简单的事件处理示例：

14.1 创建一个自定义事件

```java
import org.springframework.context.ApplicationEvent;

public class CustomEvent extends ApplicationEvent {
    public CustomEvent(Object source) {
        super(source);
    }

    @Override
    public String toString() {
        return "Custom event occurred.";
    }
}
```



14.2 创建一个事件监听器

```java
import org.springframework.context.ApplicationListener;

public class CustomEventListener implements ApplicationListener<CustomEvent> {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println(event.toString());
    }
}
```



14.3 在配置中注册监听器

```xml
<bean id="customEventListener" class="com.example.CustomEventListener"/>
```



14.4 发布事件

```java
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        context.publishEvent(new CustomEvent(context));
    }
}
```



运行主程序，将看到 "Custom event occurred." 的输出。

现在，运行主程序，将看到以下输出：

```
Copy code
Starting the car.
Gas engine started.
```

