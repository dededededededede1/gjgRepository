

- springboot中用的常用注解


- Spring Boot 是一个用于简化 Spring 应用程序开发的框架，提供了许多注解来帮助开发者更快地创建和配置应用程序。以下是一些在 Spring Boot 中常用的注解，涵盖了 MyBatis 和 Spring MVC 等方面： 
1. 基本 Spring Boot 注解：
- @SpringBootApplication：用于启动类，表示这是一个 Spring Boot 应用程序。
- @Configuration：表示类是一个配置类，用于定义配置信息。
- @Component：表示类是一个组件，由 Spring 容器管理。
- @ComponentScan：指定 Spring 扫描的包路径。
- @Bean：将方法返回的对象作为 Bean 注册到 Spring 容器中。 
2. Spring MVC 注解：
- @Controller：表示类是一个控制器，处理 HTTP 请求。
- @RestController：组合了 @Controller 和 @ResponseBody，用于创建 RESTful Web 服务。
- @RequestMapping：映射请求路径到控制器方法，支持 GET、POST 等 HTTP 方法。
- @GetMapping、@PostMapping 等：专门针对特定 HTTP 方法的映射注解。
- @PathVariable：从 URL 中获取参数值。
- @RequestParam：从请求参数中获取参数值。
- @RequestBody：将请求体内容绑定到方法参数。
- @ResponseBody：将方法返回值作为响应体返回。 
3. MyBatis 注解：
- @Mapper：表示类是一个 MyBatis 映射器接口。
- @MapperScan：指定 MyBatis 扫描的包路径。
- @Select、@Insert、@Update、@Delete：分别对应 MyBatis 的查询、插入、更新和删除操作。
- @Results：定义查询结果的映射规则。
- @Result：定义单个属性的映射规则。
- @Param：指定方法参数的名称，用于在 SQL 映射中引用。 
4. 数据源和事务相关注解：
- @EnableTransactionManagement：开启事务管理。
- @Transactional：声明方法需要事务支持。
- @Primary：当存在多个同类型的 Bean 时，标记为首选 Bean。

这些注解可以帮助你更快地开发 Spring Boot 应用程序，当然还有许多其他的注解，根据实际需要进行选择和使用。


- 除了上述提到的 Spring Boot、Spring MVC 和 MyBatis 注解外，还有许多其他有用的注解可以用于开发 Spring Boot 应用程序。以下是一些其他常用注解： 
1. Spring Security 注解：
- @EnableWebSecurity：启用 Web 安全功能。
- @PreAuthorize：基于表达式的访问控制，用于方法级别的安全控制。
- @PostAuthorize：在方法执行后检查表达式的结果，用于方法级别的安全控制。
- @Secured：基于角色的访问控制，用于方法级别的安全控制。 
2. Spring Data JPA 注解：
- @Entity：标识类是一个 JPA 实体。
- @Id：指定实体类的主键属性。
- @GeneratedValue：定义主键生成策略。
- @Column：指定属性与数据库列的映射关系。
- @OneToMany、@ManyToOne、@OneToOne、@ManyToMany：定义实体类之间的关联关系。 
3. 验证注解（来自 Java Bean Validation 和 Hibernate Validator）：
- @NotNull：验证属性值不能为空。
- @NotEmpty：验证属性值不能为空且长度大于 0。
- @NotBlank：验证字符串属性值不为空且去除前后空格后长度大于 0。
- @Min、@Max：验证数值属性值的最小值和最大值。
- @Size：验证集合属性的大小范围。
- @Pattern：验证字符串属性值是否匹配给定的正则表达式。
- @Email：验证字符串属性值是否为有效的电子邮件格式。
- @Valid：用于级联验证关联对象的属性。 
4. Spring Cache 注解：
- @EnableCaching：启用缓存功能。
- @Cacheable：声明方法的返回值可以被缓存。
- @CachePut：更新缓存中的数据。
- @CacheEvict：从缓存中删除数据。 
5. Spring AOP 注解：
- @Aspect：定义一个切面类。
- @Pointcut：定义切点表达式。
- @Before、@After、@AfterReturning、@AfterThrowing、@Around：定义通知类型（前置通知、后置通知、返回通知、异常通知、环绕通知）。

以上是一些在 Spring Boot 应用程序开发中可能会用到的其他注解。根据项目需求，可以选择适当的注解来简化开发过程。

- 把以上注解的常用属性，详细解释下

- 以下是上述提到的部分注解的常用属性及其解释： 
1. @SpringBootApplication：
- exclude：用于排除自动配置的类。
- excludeNames：用于排除自动配置的类名。 
2. @Configuration：

无特定属性。 
3. @Component：

无特定属性。 
4. @ComponentScan：
- basePackages：要扫描的包路径。
- basePackageClasses：要扫描的包路径，通过类名指定。 
5. @Bean：
- name：指定 Bean 的名称。
- initMethod：指定 Bean 初始化时执行的方法。
- destroyMethod：指定 Bean 销毁时执行的方法。 
6. @Controller：

无特定属性。 
7. @RestController：

无特定属性。 
8. @RequestMapping：
- value：映射请求的路径。
- method：映射请求的 HTTP 方法。
- consumes：指定处理请求的提交内容类型（Content-Type）。
- produces：指定返回的内容类型（Accept）。 
9. @GetMapping、@PostMapping 等：
- value：映射请求的路径。
- consumes：指定处理请求的提交内容类型（Content-Type）。
- produces：指定返回的内容类型（Accept）。 
10. @PathVariable：
- value：映射 URL 中的占位符名称。
- required：表示路径变量是否必须提供，默认为 true。 
11. @RequestParam：
- value：映射请求参数的名称。
- required：表示请求参数是否必须提供，默认为 true。
- defaultValue：请求参数的默认值。 
12. @RequestBody：
- required：表示请求体是否必须提供，默认为 true。 
13. @ResponseBody：

无特定属性。 
14. @Mapper：

无特定属性。 
15. @MapperScan：
- value：要扫描的包路径。
- basePackages：要扫描的包路径。
- basePackageClasses：要扫描的包路径，通过类名指定。 
16. @Select、@Insert、@Update、@Delete：
- value：要执行的 SQL 语句。 
17. @Results：
- value：一个 @Result 注解数组，用于定义多个属性的映射规则。 
18. @Result：
- column：数据库列名。
- property：实体类属性名。
- id：是否为主键，true 表示为主键。 
19. @Param：
- value：方法参数的名称。 
20. @EnableTransactionManagement：

无特定属性。 
21. @Transactional：
- readOnly：指定事务是否为只读，默认为 false。
- timeout：指定事务的超时时间，默认为 -1，表示使用系统默认值。
- isolation：指定事务的隔离级别，默认为 DEFAULT。
- propagation：指定事务的传播行为，默认为 REQUIRED。
- rollbackFor：指定触发回滚的异常类。
- noRollbackFor：指定不触发回滚的异常类。

以上是部分注解的常用属性及其解释。请注意，并非所有注解都有特定的属性。有些注解

接下来，我们继续讨论其他注解的常用属性及其解释： 

1. @EnableWebSecurity：

无特定属性。 
2. @PreAuthorize、@PostAuthorize：
- value：表示要执行的访问控制表达式。 
3. @Secured：
- value：表示允许访问的角色列表。 
4. @Entity：

无特定属性。 
5. @Id：

无特定属性。 
6. @GeneratedValue：
- strategy：指定主键生成策略，默认为 GenerationType.AUTO。
- generator：指定主键生成器的名称，用于引用自定义生成器。 
7. @Column：
- name：指定映射到数据库列的名称。
- unique：指定列是否具有唯一约束，默认为 false。
- nullable：指定列是否允许为空，默认为 true。
- length：指定列的最大长度，默认为 255。
- columnDefinition：指定用于创建列的 SQL 语句。 
8. @OneToMany、@ManyToOne、@OneToOne、@ManyToMany：
- mappedBy：指定关联属性的名称。
- fetch：指定关联数据的抓取策略，默认为 FetchType.LAZY。
- cascade：指定级联操作类型，如 CascadeType.ALL、CascadeType.PERSIST 等。 
9. @NotNull、@NotEmpty、@NotBlank：
- message：指定验证失败时的错误信息。 
10. @Min、@Max：
- value：指定数值的最小值或最大值。
- message：指定验证失败时的错误信息。 
11. @Size：
- min：指定集合的最小大小。
- max：指定集合的最大大小。
- message：指定验证失败时的错误信息。 
12. @Pattern：
- regexp：指定正则表达式。
- message：指定验证失败时的错误信息。 
13. @Email：
- message：指定验证失败时的错误信息。 
14. @Valid：

无特定属性。 
15. @EnableCaching：

无特定属性。 
16. @Cacheable：
- value：指定缓存名称。
- key：指定缓存键的表达式。
- condition：指定缓存条件的表达式。 
17. @CachePut：
- value：指定缓存名称。
- key：指定缓存键的表达式。
- condition：指定缓存条件的表达式。 
18. @CacheEvict：
- value：指定缓存名称。
- key：指定缓存键的表达式。
- condition：指定缓存条件的表达式。
- allEntries：指示是否删除所有条目，默认为 false。
- beforeInvocation：指示是否在方法调用之前删除缓存条目，默认为 false。 
19. @Aspect：

无特定属性。 
20. @Pointcut：
- value：指定切点表达式。 
21. @Before、@After、@AfterReturning、@AfterThrowing、@Around：
- value：指定切点表达式。

以上是其他注解的常用属性及其解释。在使用这些注


- 在使用这些注解时，了解它们的属性以及如何设置这些属性是很重要的。下面继续列举和解释一些其他常用注解及其属性： 
1. @Repository：

无特定属性。 
2. @Service：

无特定属性。 
3. @Autowired：
- required：指示依赖是否是必需的，默认为 true。 
4. @Qualifier：
- value：指定要注入的 Bean 的名称。 
5. @Value：
- value：指定属性值的表达式，可以是字面值、系统属性、环境变量等。 
6. @Profile：
- value：指定激活的配置文件名称。 
7. @Order：
- value：指定 Bean 的顺序值。 
8. @Lazy：
- value：指示是否延迟初始化，默认为 true。 
9. @EventListener：
- classes：指定要监听的事件类。 
10. @PostConstruct：

无特定属性。 
11. @PreDestroy：

无特定属性。 
12. @JsonView：
- value：指定要使用的 JSON 视图类。 
13. @ResponseStatus：
- value：指定响应的 HTTP 状态码。
- reason：指定响应的原因短语。 
14. @ExceptionHandler：
- value：指定要处理的异常类。 
15. @PropertySource：
- value：指定属性文件的位置。
- ignoreResourceNotFound：指示是否忽略找不到的资源，默认为 false。
- encoding：指定属性文件的编码，默认为 "UTF-8"。 
16. @Import：
- value：指定要导入的配置类。 
17. @ImportResource：
- value：指定要导入的资源位置，例如 XML 配置文件。

以上是关于 Spring Boot、Spring MVC、MyBatis、Spring Data JPA、Bean Validation、Spring Cache、Spring AOP 等方面的其他常用注解及其属性的解释。在实际项目开发中，根据需求选择合适的注解，并根据需要设置相应的属性。这将帮助您更好地利用 Spring Boot 框架，简化开发过程并提高代码的可读性和可维护性。
