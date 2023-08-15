
Person: 
- spring boot starters 自定义 demo

ChatGPT: 
- 创建一个自定义 Spring Boot Starter 示例：

假设我们要创建一个简化消息发送的自定义 Starter，名为 "message-sender-starter"。以下是创建这个 Starter 的步骤：
1. 创建一个新的 Maven 项目，目录结构如下：

```css

message-sender-starter
├── src
│   └── main
│       ├── java
│       │   └── com
│       │       └── example
│       │           └── messagesender
│       └── resources
│           └── META-INF
│               └── spring.factories
└── pom.xml
```

 
1. 在 `pom.xml` 中添加 Spring Boot 的依赖：

```xml

<parent>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-parent</artifactId>
    <version>2.5.5</version>
</parent>

<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter</artifactId>
    </dependency>
</dependencies>
```

 
1. 创建一个简单的消息发送接口 `MessageSender.java`：

```java

package com.example.messagesender;

public interface MessageSender {
    void send(String message);
}
```

 
1. 创建一个默认的消息发送实现类 `DefaultMessageSender.java`：

```java

package com.example.messagesender;

public class DefaultMessageSender implements MessageSender {

    @Override
    public void send(String message) {
        System.out.println("Default message sender: " + message);
    }
}
```

 
1. 创建一个自动配置类 `MessageSenderAutoConfiguration.java`，用于配置消息发送器实例：

```java

package com.example.messagesender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageSenderAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MessageSender messageSender() {
        return new DefaultMessageSender();
    }
}
```

 
1. 在 `src/main/resources/META-INF/spring.factories` 文件中注册自动配置类：

```

org.springframework.boot.autoconfigure.EnableAutoConfiguration=com.example.messagesender.MessageSenderAutoConfiguration
```


1. 构建并发布 Starter：

将项目打包成 JAR 文件，然后将 JAR 文件发布到 Maven 仓库（如 Nexus 或 Artifactory），或者安装到本地仓库。
1. 在需要使用这个 Starter 的项目中添加依赖：

```xml

<dependency>
    <groupId>com.example</groupId>
    <artifactId>message-sender-starter</artifactId>
    <version>1.0.0</version>
</dependency>
```

 
1. 在应用程序中使用 `MessageSender`：

```java

import com.example.messagesender.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MyService {

    @Autowired
    private MessageSender messageSender;

    public void doSomething() {
        messageSender.send("Hello, Custom Starter!");
    }
}
```



通过这个示例，你可以学习到如何创建一个自定义的 Spring Boot Starter，并在其他项目中使用这个 Starter。

Person: 
- 继续

ChatGPT: 
- 接下来，我们将为自定义 Spring Boot Starter 添加配置选项和更多功能。假设我们需要在发送消息时添加一个前缀。 
1. 创建一个配置类 `MessageSenderProperties.java`：

```java

package com.example.messagesender;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "message.sender")
public class MessageSenderProperties {
    private String prefix = "";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
```



这个配置类使用 `@ConfigurationProperties` 注解，允许用户在 `application.properties` 或 `application.yml` 文件中设置 `message.sender.prefix` 属性。 
1. 在 `MessageSenderAutoConfiguration.java` 中注入 `MessageSenderProperties`：

```java

package com.example.messagesender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageSenderProperties.class)
public class MessageSenderAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MessageSender messageSender(MessageSenderProperties properties) {
        return new DefaultMessageSender(properties.getPrefix());
    }
}
```

 
1. 修改 `DefaultMessageSender.java`，使其能够接收并使用前缀：

```java

package com.example.messagesender;

public class DefaultMessageSender implements MessageSender {
    private final String prefix;

    public DefaultMessageSender(String prefix) {
        this.prefix = prefix;
    }

    @Override
    public void send(String message) {
        System.out.println("Default message sender: " + prefix + message);
    }
}
```


1. 在需要使用这个 Starter 的项目中配置前缀：

在 `application.properties` 或 `application.yml` 文件中添加以下配置：

```css

message.sender.prefix=[MyPrefix]
```



现在，当你在应用程序中调用 `MessageSender.send()` 方法时，发送的消息将包含前缀 "[MyPrefix]"。通过这个示例，你可以学习到如何为自定义的 Spring Boot Starter 添加配置选项，以便用户能够根据需求定制 Starter 的行为。

Person: 
- public MessageSender messageSender(MessageSenderProperties properties  这里properties读取prefix

ChatGPT: 
- 抱歉，我在上面的示例中没有展示如何在 `DefaultMessageSender` 类中读取 `MessageSenderProperties` 的 `prefix` 属性。请按照以下步骤修改示例： 
1. 修改 `MessageSenderAutoConfiguration.java` 中的 `messageSender` 方法，将 `MessageSenderProperties` 的 `prefix` 属性传递给 `DefaultMessageSender` 的构造函数：

```java

package com.example.messagesender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageSenderProperties.class)
public class MessageSenderAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MessageSender messageSender(MessageSenderProperties properties) {
        return new DefaultMessageSender(properties.getPrefix());
    }
}
```

 
1. 这样，当 Spring Boot 应用程序启动时，`MessageSenderAutoConfiguration` 类会自动创建一个 `MessageSender` Bean，并将 `MessageSenderProperties` 的 `prefix` 属性传递给 `DefaultMessageSender` 的构造函数。`DefaultMessageSender` 类现在可以正确地读取并使用前缀。

现在，当你在应用程序中调用 `MessageSender.send()` 方法时，发送的消息将包含你在 `application.properties` 或 `application.yml` 文件中配置的前缀。

Person: 
- public MessageSender messageSender(MessageSenderProperties properties)  这里的properties没有从application.yml 读到信息

ChatGPT: 
- 如果 `MessageSenderProperties` 无法从 `application.yml` 或 `application.properties` 文件中读取配置信息，请确保以下步骤正确设置： 
1. 确保 `MessageSenderProperties` 类中使用了 `@ConfigurationProperties` 注解，并指定了正确的 `prefix`：

```java

package com.example.messagesender;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "message.sender")
public class MessageSenderProperties {
    private String prefix = "";

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }
}
```

 
1. 在 `MessageSenderAutoConfiguration` 类中，确保启用了 `MessageSenderProperties` 的配置属性，通过添加 `@EnableConfigurationProperties` 注解：

```java

package com.example.messagesender;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageSenderProperties.class)
public class MessageSenderAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public MessageSender messageSender(MessageSenderProperties properties) {
        return new DefaultMessageSender(properties.getPrefix());
    }
}
```

 
1. 确保在需要使用 Starter 的项目的 `application.yml` 或 `application.properties` 文件中设置了 `message.sender.prefix` 属性。例如，在 `application.yml` 文件中添加以下配置：

```yaml

message:
  sender:
    prefix: "[MyPrefix]"
```



或在 `application.properties` 文件中添加：

```css

message.sender.prefix=[MyPrefix]
```


1. 确保将自定义 Starter 作为依赖添加到需要使用它的项目中，以便 Spring Boot 能够自动发现并启用 Starter。

如果按照上述步骤进行设置仍然无法读取配置，请检查项目的其他部分，以确保没有影响 Spring Boot 自动配置机制的错误或配置。

Person: 
- EnableConfigurationProperties 作用

ChatGPT: 
- `@EnableConfigurationProperties` 注解是 Spring Boot 提供的一种用于启用和管理 `@ConfigurationProperties` 注解类的配置属性绑定的机制。简单来说，它用于将 `application.properties` 或 `application.yml` 文件中的配置属性绑定到 Java 类中的属性。

当你在一个 Spring Boot 项目中使用 `@EnableConfigurationProperties` 时，你需要将这个注解添加到一个配置类上，并指定要启用的 `@ConfigurationProperties` 类。这样，Spring Boot 将自动将指定的属性前缀下的配置属性绑定到对应的 Java 类属性上。

例如，在我们的自定义 Starter 示例中，我们使用了以下代码来启用 `MessageSenderProperties` 类的属性绑定：

```java

@EnableConfigurationProperties(MessageSenderProperties.class)
```



这样，当 Spring Boot 应用程序启动时，它将在 `application.properties` 或 `application.yml` 文件中寻找 `message.sender` 前缀的属性，并将这些属性绑定到 `MessageSenderProperties` 类的实例中。

需要注意的是，`@EnableConfigurationProperties` 可以在多个地方使用，并且可以指定多个 `@ConfigurationProperties` 类。例如：

```java

@EnableConfigurationProperties({MessageSenderProperties.class, OtherProperties.class})
```



总之，`@EnableConfigurationProperties` 注解的作用是启用和管理 `@ConfigurationProperties` 注解类的配置属性绑定，使得你可以轻松地将配置文件中的属性映射到 Java 类中。

Person: 
- public MessageSender messageSender(MessageSenderProperties properties)  这里的properties怎么传入的

ChatGPT: 
- 在Spring Boot中，方法参数的依赖注入是通过Spring框架实现的。当你在`MessageSenderAutoConfiguration`类中定义了一个`messageSender`方法，并将`MessageSenderProperties`作为参数传入时，Spring会自动注入一个`MessageSenderProperties`实例。

首先，确保你已经启用了`MessageSenderProperties`类的配置属性绑定：

```java

@EnableConfigurationProperties(MessageSenderProperties.class)
```



这将通知Spring Boot自动将`application.properties`或`application.yml`文件中的`message.sender`前缀的属性绑定到`MessageSenderProperties`类的实例上。

接下来，在`MessageSenderAutoConfiguration`类中定义一个`messageSender`方法，这个方法将`MessageSenderProperties`作为参数：

```java

@Bean
@ConditionalOnMissingBean
public MessageSender messageSender(MessageSenderProperties properties) {
    return new DefaultMessageSender(properties.getPrefix());
}
```



当Spring Boot应用程序启动时，Spring框架将检测到`messageSender`方法，创建一个`MessageSender`类型的Bean。在创建这个Bean的过程中，Spring会自动注入一个`MessageSenderProperties`实例作为方法参数。此`MessageSenderProperties`实例包含从配置文件中读取的属性值。

简而言之，在Spring Boot中，方法参数的依赖注入是通过Spring框架自动处理的。只要你正确配置了`@ConfigurationProperties`类和`@EnableConfigurationProperties`注解，Spring就会自动注入一个包含从配置文件中读取的属性值的实例。
