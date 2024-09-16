

- springmvc 有常用的注解，把注解的属性也解释说明下


- Spring MVC 是一个基于注解的 Web 框架，提供了一系列注解来简化开发，常用的注解包括：
1. @Controller: 标记一个类作为 Spring MVC 控制器。 
2. @RequestMapping: 映射请求路径到控制器的处理方法，可以在类级别和方法级别上使用。
- value: 请求路径，支持多个路径
- method: 请求方法，支持多个方法，如 GET、POST 等
- params: 请求参数，可以指定参数名和参数值，如 params={"username","admin"}，表示请求必须包含 username 参数且值为 admin
- headers: 请求头，可以指定请求头名和值
- consumes: 指定处理请求的媒体类型，如 consumes="application/json"，表示处理 JSON 格式的请求
- produces: 指定响应的媒体类型，如 produces="application/json"，表示响应 JSON 格式的数据
3. @PathVariable: 映射 URL 路径变量到方法参数，例如 @RequestMapping("/users/{id}")，方法参数可以用 @PathVariable("id") 注解获取 URL 中的 id 值。
4. @RequestParam: 映射请求参数到方法参数，例如 @RequestMapping("/users")，方法参数可以用 @RequestParam("username") 注解获取请求参数中的 username 值。
5. @ResponseBody: 将方法的返回值转换为指定的响应格式，如 JSON 格式。
6. @RequestBody: 将请求体转换为方法参数，如将 JSON 格式的请求体转换为 Java 对象。
7. @ModelAttribute: 将请求参数绑定到模型对象上，模型对象可以作为方法参数或方法返回值。
8. @Valid: 在模型对象上执行校验操作，需要与 javax.validation.Valid 配合使用。

除了上述常用的注解，还有一些其他的注解，例如：
1. @SessionAttributes: 将模型对象中的属性存储到 HttpSession 中，以便在多个请求之间共享数据。
2. @InitBinder: 初始化数据绑定器，用于处理表单数据转换和校验。
3. @ExceptionHandler: 处理异常信息，将指定的异常类型映射到指定的处理方法上。

这些注解都可以帮助开发者更加方便地编写 Spring MVC 应用程序。

- 1. @SessionAttribute: 将指定名称的属性从 HttpSession 中获取并绑定到方法的参数上。使用该注解时，需要先在类级别上使用 @SessionAttributes 注解声明需要绑定到 HttpSession 中的属性名。
- value: 指定要绑定的属性名
- required: 指定该属性是否必须存在于 HttpSession 中，默认为 true。 
2. @CookieValue: 将指定名称的 Cookie 值绑定到方法的参数上。
- value: 指定 Cookie 的名称
- required: 指定该 Cookie 是否必须存在，默认为 true。 
3. @RequestHeader: 将指定名称的请求头信息绑定到方法的参数上。
- value: 指定请求头的名称
- required: 指定该请求头是否必须存在，默认为 true。 
4. @CrossOrigin: 开启跨域访问支持。
- value: 指定允许跨域访问的域名，可以是一个字符串数组。
- methods: 指定允许跨域访问的请求方法，例如 GET、POST、PUT 等。
- allowedHeaders: 指定允许跨域访问的请求头。
- exposedHeaders: 指定允许客户端访问的响应头。
- maxAge: 指定预检请求的有效期，单位为秒。

这些注解提供了更加细粒度的控制能力，可以根据具体的业务需求选择使用。


- 1. @ResponseStatus: 标记一个方法的响应状态码及其原因短语。当方法处理完请求后，可以根据需要设置响应的状态码及其原因短语，例如：

```less

@ResponseStatus(HttpStatus.CREATED)
@RequestMapping("/users", method = RequestMethod.POST)
public void createUser(@RequestBody User user) {
    userService.createUser(user);
}
```

上面的例子中，当创建用户成功时，将设置响应的状态码为 201，表示创建成功。
2. @RequestMappingHandlerMapping: 自定义请求处理映射器，用于解析请求路径与控制器的处理方法之间的映射关系。需要在配置类中定义该 bean，并将其注册到 Spring 容器中。
3. @RequestMappingHandlerAdapter: 自定义请求处理适配器，用于调用控制器的处理方法并将其返回值转换为指定的响应格式。需要在配置类中定义该 bean，并将其注册到 Spring 容器中。

除了上述注解，Spring MVC 还提供了许多其他的注解和组件，例如视图解析器、拦截器、异常处理器等，开发者可以根据具体的业务需求选择使用。

- 1. @RequestMapping(value = "/path", produces = "application/json;charset=UTF-8"): 指定处理请求的媒体类型和字符集。在上面的例子中，指定了请求的媒体类型为 JSON 格式，并指定字符集为 UTF-8。
2. @PathVariable Map<String, String> pathVariables: 通过 Map 接收 URL 路径中的所有变量。在方法参数上使用该注解时，方法中的代码可以通过该 Map 参数获取 URL 路径中的所有变量。
3. @ModelAttribute Map<String, Object> model: 通过 Map 接收模型数据。在方法参数上使用该注解时，方法中的代码可以通过该 Map 参数获取模型数据，并向其中添加新的数据。 
4. @ResponseBody ResponseEntity<T>: 将方法的返回值封装为 ResponseEntity 对象，并通过该对象设置响应头和响应体。在方法的返回值类型为 ResponseEntity<T> 时，Spring MVC 将直接将其作为响应对象返回给客户端，而不会将其转换为视图。
5. @ExceptionHandler(value = Exception.class)：指定处理异常的方法。在方法上使用该注解时，可以指定需要处理的异常类型，当控制器中发生该异常时，Spring MVC 将自动调用该方法进行处理，并将方法的返回值转换为指定的响应格式。
6. @EnableWebMvc 是一个 Spring MVC 配置类注解，用于启用 Spring MVC 框架的支持。使用该注解时，Spring MVC 将自动配置默认的处理器映射器和适配器，并扫描包路径下所有的 @Controller、@RequestMapping、@ExceptionHandler 等注解，自动创建相应的 Bean。

具体来说，使用 @EnableWebMvc 注解时，需要在配置类中添加一个继承自 WebMvcConfigurerAdapter 的配置类，并重写该类的一些方法来自定义 Spring MVC 配置，例如添加拦截器、视图解析器、异常处理器等。示例如下：

```typescript

@Configuration
@EnableWebMvc
public class MvcConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptor());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        exceptionResolvers.add(new MyExceptionHandler());
    }
}
```



上面的示例中，使用了 @EnableWebMvc 注解启用了 Spring MVC 框架的支持，并重写了 WebMvcConfigurerAdapter 的一些方法来自定义配置，例如添加拦截器、视图解析器、异常处理器等。
