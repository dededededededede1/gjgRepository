

- springboot application.properties配置文件中常用的配置说明，详细解释配置的作用



- `application.properties` 是一个 Spring Boot 应用程序的配置文件，它允许您在一个集中的位置自定义应用程序的各种配置。以下是一些常用配置及其详细解释： 
1. `server.port`：指定应用程序运行的端口。默认情况下，Spring Boot 使用 8080 端口。

```

server.port=8081
```


1. `server.servlet.context-path`：定义应用程序的上下文路径，即 URL 的基本路径。

```bash

server.servlet.context-path=/myapp
```


1. `spring.datasource.url`：设置数据库连接的 URL。这取决于您所使用的数据库类型。

```bash

spring.datasource.url=jdbc:mysql://localhost:3306/mydb
```


1. `spring.datasource.username`：设置数据库连接的用户名。

```

spring.datasource.username=root
```


1. `spring.datasource.password`：设置数据库连接的密码。

```

spring.datasource.password=secret
```


1. `spring.datasource.driver-class-name`：设置数据库连接的驱动程序类名。

```arduino

spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```


1. `spring.jpa.hibernate.ddl-auto`：配置 JPA 如何自动生成数据库表结构。有效值包括：`none`、`validate`、`update`、`create` 和 `create-drop`。

```arduino

spring.jpa.hibernate.ddl-auto=update
```


1. `spring.jpa.show-sql`：设置是否在控制台输出 SQL 查询。对于调试和性能优化很有用。

```sql

spring.jpa.show-sql=true
```


1. `spring.jpa.properties.hibernate.format_sql`：设置是否格式化输出的 SQL 查询。

```arduino

spring.jpa.properties.hibernate.format_sql=true
```


1. `spring.profiles.active`：设置当前激活的 Spring 配置文件。这允许您为不同环境（如开发、测试和生产）定义不同的配置。

```

spring.profiles.active=dev
```


1. `logging.level.*`：设置不同包或类的日志级别。例如，以下设置将 org.springframework 包的日志级别设置为 WARN。

```

logging.level.org.springframework=WARN
```


1. `logging.file.name`：设置日志文件的名称和路径。

```c

logging.file.name=myapp.log
```


1. `spring.mail.host`：配置邮件服务器的主机名。

```

spring.mail.host=smtp.example.com
```


1. `spring.mail.port`：配置邮件服务器的端口。

```

spring.mail.port=587
```


1. `spring.mail.username`：配置邮件服务器的用户名。

```sql

spring.mail.username=user@example.com
```


1. `spring.mail.password`：配置邮件服务器的密码。

```

spring.mail.password=secret
```


1. `spring.mail.properties.mail.smtp.auth`：设置是否使用 SMTP 认证。

```arduino

spring.mail.properties.mail.smtp.auth=true
```


1. `spring.mail.properties.mail.smtp.starttls.enable`：设置是否使用 TLS 加密连接。

```arduino

spring.mail.properties.mail.smtp.starttls.enable=true
```



这些仅仅是 `application.properties` 文件中常用配置的一部分。您可以根据实际需求添加其他配置。



- 关于springmvc 和mybatis的配置

- Spring MVC 和 MyBatis 配置涉及到几个主要部分。以下是在 `application.properties` 文件中配置 Spring MVC 和 MyBatis 的示例：
1. 配置 MyBatis 的扫描路径：

要使用 MyBatis，需要告诉 Spring Boot 扫描 MyBatis 的映射器接口（Mapper）所在的包。可以使用 `mybatis.mapper-locations` 属性来设置：

```makefile

mybatis.mapper-locations=classpath:mapper/*Mapper.xml
```


1. 配置 MyBatis 的别名包：

在 MyBatis 中，可以为实体类创建别名，这样在映射文件中就可以使用简短的别名来引用实体类。可以使用 `mybatis.type-aliases-package` 属性来设置别名包：

```go

mybatis.type-aliases-package=com.example.myapp.domain
```


1. 配置 MyBatis 的配置文件路径：

MyBatis 有一个名为 `mybatis-config.xml` 的全局配置文件。可以使用 `mybatis.config-location` 属性来设置配置文件的路径：

```arduino

mybatis.config-location=classpath:mybatis/mybatis-config.xml
```


1. Spring MVC 静态资源配置：

如果需要配置 Spring MVC 的静态资源，例如 CSS、JavaScript 和图片等，可以使用 `spring.resources` 属性进行设置：

```arduino

spring.resources.static-locations=classpath:/static/
```



此配置将从类路径下的 `/static/` 文件夹加载静态资源。
1. 配置视图解析器：

在 Spring MVC 中，视图解析器（View Resolver）用于将控制器返回的视图名称解析为实际的视图。在 Spring Boot 中，默认的视图解析器是 Thymeleaf，但是可以通过以下配置来自定义：

```javascript

spring.mvc.view.prefix=/WEB-INF/views/
spring.mvc.view.suffix=.jsp
```



以上配置将视图解析器设置为 JSP，并指定 JSP 文件的路径前缀和后缀。

这些配置可以根据您的实际需求进行调整。这里仅提供了一些基本的示例，实际项目中可能还需要配置其他属性。

- 接下来我们继续探讨一些与 Spring Boot、Spring MVC 和 MyBatis 相关的配置选项。
1. 文件上传配置：

在 Spring MVC 应用程序中，可以使用 `MultipartResolver` 来处理文件上传。以下是配置文件上传的相关属性：

```arduino

spring.servlet.multipart.max-file-size=10MB
spring.servlet.multipart.max-request-size=100MB
```



这些属性用于限制上传文件的大小和一个请求中允许上传文件的总大小。
1. 使用 PageHelper 分页插件：

MyBatis 的 PageHelper 分页插件可以帮助您轻松地实现分页功能。要使用 PageHelper，需要在 `application.properties` 文件中进行以下配置：

```csharp

pagehelper.helper-dialect=mysql
pagehelper.reasonable=true
pagehelper.support-methods-arguments=true
pagehelper.params=count=countSql
```



这些属性用于指定数据库方言、启用合理化分页、支持通过方法参数传递分页参数等。
1. 配置事务管理器：

在基于 Spring Boot 和 MyBatis 的应用程序中，可以使用 `DataSourceTransactionManager` 作为事务管理器。以下是配置事务管理器的示例：

```csharp

spring.transaction.default-timeout=300
spring.transaction.rollback-on-commit-failure=true
```



这些属性用于设置默认的事务超时时间以及在提交失败时自动回滚事务。
1. 配置拦截器（Interceptor）：

在 Spring MVC 应用程序中，可以使用拦截器对请求进行预处理和后处理。要配置拦截器，请在 Java 配置类中注册拦截器：

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/login", "/register");
    }
}
```



上述示例将自定义的 `MyInterceptor` 拦截器应用于所有请求，但排除了 `/login` 和 `/register` 路径。
1. 配置跨域资源共享（CORS）：

要在 Spring MVC 应用程序中配置跨域资源共享（CORS），可以使用以下示例：

```java

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }
}
```



这些配置允许所有来源的跨域请求，并允许常用的 HTTP 方法。

这里列出的配置仅涵盖了 Spring Boot、Spring MVC 和 MyBatis 中的部分常用配置。实际应用中，您可能需要根据项目的具体需求对这些配置进行调整。
