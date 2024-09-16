

- spring boot starters 自定义 demo

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

message.sender.prefix=testconfig
```



现在，当你在应用程序中调用 `MessageSender.send()` 方法时，发送的消息将包含前缀 "[MyPrefix]"。通过这个示例，你可以学习到如何为自定义的 Spring Boot Starter 添加配置选项，以便用户能够根据需求定制 Starter 的行为。
