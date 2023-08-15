

- springboot中application.properties 中关于日志的配置


- 在 Spring Boot 中，可以通过配置 application.properties 文件来控制日志的输出格式、级别以及输出位置等。以下是关于日志的一些常用配置项及其解释：

- 在 springboot项目pom中添加

- ```
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-logging</artifactId>
  </dependency>
  ```
1. 日志级别：可以设置整个应用或特定包的日志级别。

```

logging.level.root=INFO
logging.level.com.example.project=DEBUG   #com.example.project需要设置日志级别的包名
```



`logging.level.root` 用于设置整个应用的默认日志级别，通常包括：TRACE、DEBUG、INFO、WARN、ERROR、FATAL。`logging.level.com.example.project` 用于设置特定包的日志级别。
1. 日志格式：可以自定义日志输出的格式。

```perl

logging.pattern.console=%d{yyyy-MM-dd HH:mm:ss} - %msg%n
logging.pattern.file=%d{yyyy-MM-dd HH:mm:ss} [%thread] %-5level %logger{36} - %msg%n
```



`logging.pattern.console` 用于设置控制台输出日志的格式，`logging.pattern.file` 用于设置文件输出日志的格式。`%d` 表示时间戳，`%msg` 表示日志消息，`%n` 表示换行等。
1. 日志文件：可以设置日志输出到文件。

```lua

logging.file.name=myapp.log
logging.file.path=/var/log
```



`logging.file.name` 用于设置日志文件的名称，`logging.file.path` 用于设置日志文件的存储路径。
1. 日志文件大小限制：可以设置日志文件的最大大小以及文件滚动策略。

```arduino

logging.file.max-size=10MB
logging.file.max-history=10
logging.file.total-size-cap=100MB
```



`logging.file.max-size` 用于设置单个日志文件的最大大小。`logging.file.max-history` 用于设置最多保留的历史日志文件数量。`logging.file.total-size-cap` 用于设置所有日志文件的总大小限制。

这些配置可以帮助你根据项目需求调整日志输出，以便在开发、测试或生产环境中轻松诊断问题。注意，这里的配置仅适用于 Spring Boot 默认使用的 Logback 日志框架，如果你使用其他日志框架，例如 Log4j2，配置可能有所不同。


- 如果您希望使用除 Logback 之外的其他日志框架，例如 Log4j2，您需要进行一些额外的配置。以下是在 Spring Boot 中使用 Log4j2 的一些常见配置： 
1. 首先，将 Log4j2 的依赖项添加到项目的 `pom.xml`（如果使用 Maven）或 `build.gradle`（如果使用 Gradle）中。同时，排除 Spring Boot 默认的 Logback 依赖。

在 Maven 中添加：

```xml

<dependencies>
  ...
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
    <exclusions>
      <exclusion>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-logging</artifactId>
      </exclusion>
    </exclusions>
  </dependency>
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-log4j2</artifactId>
  </dependency>
  ...
</dependencies>
```



在 Gradle 中添加：

```groovy

dependencies {
  ...
  implementation('org.springframework.boot:spring-boot-starter') {
    exclude group: 'org.springframework.boot', module: 'spring-boot-starter-logging'
  }
  implementation 'org.springframework.boot:spring-boot-starter-log4j2'
  ...
}
```


1. 在 `src/main/resources` 目录下创建一个名为 `log4j2-spring.xml` 或 `log4j2.xml` 的文件。然后，您可以在其中定义 Log4j2 的日志配置，例如：

```xml

<?xml version="1.0" encoding="UTF-8"?>
<Configuration status="WARN">
  <Appenders>
    <Console name="Console" target="SYSTEM_OUT">
      <PatternLayout pattern="%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n"/>
    </Console>
  </Appenders>
  <Loggers>
    <Root level="info">
      <AppenderRef ref="Console"/>
    </Root>
  </Loggers>
</Configuration>
```



在这个配置文件中，您可以定义日志的格式、级别、输出位置等。虽然 Log4j2 的配置与 Logback 有所不同，但它们的核心概念相似。具体来说，您可以使用 Appenders 控制日志的输出位置，使用 PatternLayout 定义日志格式，使用 Loggers 控制日志级别。

通过上述步骤，您可以在 Spring Boot 项目中使用 Log4j2 日志框架。更多关于 Log4j2 配置选项的详细信息，请参考其官方文档：[https://logging.apache.org/log4j/2.x/manual/configuration.html。](https://logging.apache.org/log4j/2.x/manual/configuration.html%E3%80%82)


- logback 单独的配置文件


- Logback 单独的配置文件，通常为 `logback.xml` 或 `logback-spring.xml`。将该文件放在项目的 `src/main/resources` 目录下，Logback 将自动识别和应用这些配置。

以下是一个简单的 `logback-spring.xml` 配置文件示例：

```xml

<?xml version="1.0" encoding="UTF-8"?>
<configuration>
    <include resource="org/springframework/boot/logging/logback/defaults.xml"/>

    <springProperty scope="context" name="app_name" source="spring.application.name"/>

    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>

    <appender name="FILE" class="ch.qos.logback.core.rolling.RollingFileAppender">
        <file>${LOG_FILE}</file>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
        <rollingPolicy class="ch.qos.logback.core.rolling.TimeBasedRollingPolicy">
            <fileNamePattern>${LOG_FILE}.%d{yyyy-MM-dd}.gz</fileNamePattern>
            <maxHistory>30</maxHistory>
        </rollingPolicy>
    </appender>

    <root level="INFO">
        <appender-ref ref="CONSOLE"/>
        <appender-ref ref="FILE"/>
    </root>

</configuration>
```



在此示例中，我们包含了 Spring Boot 默认的 Logback 配置，并添加了一些自定义配置。这里定义了两个 appender：CONSOLE 和 FILE。CONSOLE appender 将日志输出到控制台，而 FILE appender 将日志输出到文件。我们还设置了日志格式、滚动策略等。

需要注意的是，当文件名为 `logback-spring.xml` 时，可以使用 Spring Boot 提供的一些高级特性，例如使用 `<springProperty>` 标签从 Spring 环境中获取属性。而如果文件名为 `logback.xml`，则只能使用 Logback 的标准特性。

在实际项目中，可以根据需要调整 Logback 配置文件以满足日志记录的需求。更多关于 Logback 配置的详细信息，请参考其官方文档：[http://logback.qos.ch/documentation.html。](http://logback.qos.ch/documentation.html%E3%80%82)

- 在application.properties中如何配置logback.xml位置

- 在 Spring Boot 项目中，如果您想要指定 Logback 配置文件的位置，可以通过设置 `logging.config` 属性来实现。在 `application.properties` 文件中添加以下配置：

```lua

logging.config=classpath:your-custom-path/logback.xml
```



或者，如果您的 Logback 配置文件位于文件系统中，可以使用以下配置：

```javascript

logging.config=file:/your-custom-path/logback.xml
```



其中，`your-custom-path` 应替换为您的实际文件路径。

请注意，当您在 `application.properties` 中指定了 `logging.config` 属性时，Spring Boot 将不再自动寻找 `src/main/resources` 目录下的 `logback.xml` 或 `logback-spring.xml` 文件。因此，请确保指定了正确的配置文件路径。

另外，如果您使用的是 YAML 格式的配置文件（`application.yml`），可以使用以下配置来指定 Logback 配置文件的位置：

```yaml

logging:
  config: classpath:your-custom-path/logback.xml
```



或者，对于文件系统中的文件：

```yaml

logging:
  config: file:/your-custom-path/logback.xml
```



同样，请将 `your-custom-path` 替换为实际文件路径。

- 当用logback.xml和application.properties配置了相同的配置时候，如日志级别，优先级是什么

- 当您在 `logback.xml`（或 `logback-spring.xml`）和 `application.properties` 文件中配置了相同的设置，例如日志级别，Spring Boot 将优先使用 `logback.xml`（或 `logback-spring.xml`）中的配置。

Logback 配置文件（`logback.xml` 或 `logback-spring.xml`）直接控制 Logback 的行为，因此它的配置优先级更高。`application.properties` 文件中的配置对于 Spring Boot 项目的其他部分非常重要，但对于 Logback 的配置而言，它是次要的。

所以，如果您在两个文件中都配置了相同的选项，建议将日志相关的配置集中在 Logback 配置文件中（`logback.xml` 或 `logback-spring.xml`），以确保日志系统的一致性和可维护性。在 Logback 配置文件中，您可以更详细地配置日志记录器、输出和格式等选项，从而更好地满足项目需求。

- 在配置 Logback 时，可以通过一些高级设置来满足特定需求。以下是一些可能有用的 Logback 配置选项： 
1. 日志异步输出：
为了减少日志记录对应用性能的影响，可以使用异步输出。要实现异步输出，可以使用 Logback 的 `AsyncAppender`。以下是一个 `logback-spring.xml` 配置文件中的示例：

```xml

<configuration>
    ...
    <appender name="ASYNC" class="ch.qos.logback.classic.AsyncAppender">
        <appender-ref ref="CONSOLE"/>
        <queueSize>512</queueSize>
        <discardingThreshold>0</discardingThreshold>
    </appender>

    <root level="info">
        <appender-ref ref="ASYNC"/>
    </root>
</configuration>
```



在这个示例中，我们创建了一个名为 `ASYNC` 的 `AsyncAppender`，并将 `CONSOLE` appender 作为其引用。`queueSize` 表示异步队列的大小，`discardingThreshold` 定义了从队列中丢弃日志的阈值。将根记录器的 appender 引用更改为 `ASYNC` 以实现异步输出。
1. 条件过滤器：
您可以为 appender 添加过滤器以便根据条件记录日志。以下是一个基于日志级别的过滤器示例：

```xml

<configuration>
    ...
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <filter class="ch.qos.logback.classic.filter.ThresholdFilter">
            <level>WARN</level>
        </filter>
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n</pattern>
        </encoder>
    </appender>
    ...
</configuration>
```



在这个示例中，我们为 `CONSOLE` appender 添加了一个过滤器，要求日志级别至少为 WARN。其他可用的过滤器包括 `ch.qos.logback.core.filter.EvaluatorFilter` 和自定义过滤器。 
1. MDC（Mapped Diagnostic Context）：
MDC 可以用于在日志中包含特定于上下文的信息，例如当前用户、请求 ID 等。在 Logback 配置文件中，可以使用 `%X` 转换词来引用 MDC 中的值。以下是一个包含请求 ID 的示例：

```xml

<configuration>
    ...
    <appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender">
        <encoder>
            <pattern>%d{yyyy-MM-dd HH:mm:ss.SSS} [%thread] %-5level %logger{36} [ReqID: %X{requestId}] - %msg%n</pattern>
        </encoder>
    </appender>
    ...
</configuration>
```



在这个示例中，我们将 `%X{requestId}` 添加到日志输出格式中。要将请求 ID 添加到 MDC，可以在应用程序代码中使用 `MDC.put("requestId", requestId)`。

这些高级配置选项可以帮助您
