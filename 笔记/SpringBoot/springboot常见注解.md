## 1.@SpringBootApplication 注解

是 **@SpringBootConfiguration，@EnableAutoConfiguration，@ComponentScan**三个注解的组合，这三个注解一般一起使用，所以干脆合二为一。

## 2.@EnableAutoConfiguration 注解

开启自动配置功能，帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。智能的自动配置功效借助于Spring框架原有的一个工具类：SpringFactoriesLoader的支持。

## 3.@Import 导入额外的配置信息

功能类似XML配置的，用来导入配置类，可以导入带有@Configuration注解的配置类或实现了ImportSelector/ImportBeanDefinitionRegistrar。

## 4.@Value

使用 @Import 注解可以方便地在一个配置类中引入另一个配置类或任意数量的 Bean 定义，从而避免了重复定义和维护这些组件的麻烦。

## 5.@ConditionalOnMissingBean

当用户没有配置响应的bean，则加载默认的bean。


## @ConditionalOnClass

主要是判断是否存在这个类文件，如果有这个文件就相当于满足条件，然后可以注入到容器当中。

当然并不是说容器里面是否有这个类哈，不要理解错了，这也就是我们有时候使用springboot只需要引入个依赖，框架就可以用的原因！

用在类上

```
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Conditional({OnClassCondition.class})
public @interface ConditionalOnClass {
	// 必须出现的类
    Class<?>[] value() default {};

	// 必须存在的类名，必须是全限类名，也就是包含包名+类名。
    String[] name() default {};
}

```

```
@Configuration
@ConditionalOnClass({TestBean2.class})
public class Myconfig {

    @Bean
    @ConditionalOnClass(name = "com.gzl.cn.springbootcache.config.TestBean3")
    public TestBean1 testBean1(){
        return new TestBean1();
    }
}

```


## @ConditionalOnMissingClass

```
@ConditionalOnMissingClass("com.gzl.cn.springbootcache.config.TestBean5")

```


## @ConditionalOnBean

bean存在的时候注入，不存在的时候不注入，这块就是指的spring的ioc容器了。


### @ConditionalOnMissingBean

bean不存在的时候注入，存在的时候为false。跟@ConditionalOnBean正好是相反的。




## **6.@EnableAutoConfiguration**

可以帮助SpringBoot应用将所有符合条件的@Configuration配置都加载到当前SpringBoot创建并使用的IoC容器。借助于Spring框架原有的一个工具类：SpringFactoriesLoader的支持，@EnableAutoConfiguration可以智能的自动配置功效才得以大功告成

## **7，@MapperScan 注解**

**@MapperScan是Spring Boot框架中的注解，用于自动扫描和注册MyBatis Mapper接口。**

在使用Spring Boot进行开发时，通常需要访问数据库并使用持久化框架（如MyBatis）管理数据。为便于Spring容器管理和调用这些Mapper接口，我们可以使用@MapperScan注解来将其注册为Bean，并交由SpringBoot容器统一管理，从而实现相关操作（如事务管理）。

使用@MapperScan注解时，我们可以指定需要扫描的Mapper接口所在的包，这样SpringBoot启动时会自动扫描该路径下所有的Mapper接口，并将其转换为对应的Bean对象放入容器管理。

需要注意的是，@MapperScan注解一般与@Configuration注解一起使用，以保证被Spring容器扫描到并初始化。同时，还需要保证要扫描的Mapper接口类路径、文件命名等规范正确，以免出现找不到接口类或配置错误等情况。

## **8.@ConfigurationProperties**

**@ConfigurationProperties是Spring框架中的注解之一，它可以将配置文件的属性绑定到Java类的字段上，使得开发人员可以更加方便地读取和使用配置文件中的属性。**

通过@ConfigurationProperties注解，我们可以创建一个Java类，这个类可以与某个特定的.properties或.yml文件相关联。这样，当应用程序启动时，Spring就会自动读取配置文件中的属性，并将其值作为Java对象的字段赋值。

需要注意的是，@ConfigurationProperties注解一般用于读取全局配置信息的操作，通常被定义在一个专门的@Configuration类中。同时，还可以通过加入@Validated注解，指定该类所属包的全部校验规则生效。

## 9.@ResponseBody

@ResponseBody注解既可以在方法上使用，也可以在类上使用，在类上使用表明该类中所有方法均返回JSON数据，也可以与 `@Controller`注解合并为@RestController。它的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。

## 10.@Configuration

* 标注在类上
* 将类设置为配置类
* 用来代替 applicationContext.xml 配置文件，所有这个配置文件里面能做到的事情都可以通过这个注解所在类来进行注册。

## 11.@PathVariable

* 标注在形参
* 用于获取路径参数，使用{参数名称}描述路径参数
* 获取url中的数据

## 12.@RequestBody

* 标注在形参
* 读取请求的body部分且Content-Type 为 application/json格式的数据，接收到数据之后会自动将数据绑定到 java对象上。
* @ResponseBody作用在方法上或类上，让该方法的返回结果直接写入 HTTP response body 中，不会经过视图解析器，返回数据直接在页面展示。

# 13.跨域

## **13.1 @CrossOrigin**

**此注解既可以修饰类，也可以修饰方法。当修饰类时，表示此类中的所有接口都可以跨域；当修饰方法时，表示此方法可以跨域**

但是只能在类里面使用，无法做到一个注解所有的类都使用

## 13.2 重写addCorsMappings

```
@Configuration // 一定不要忽略此注解
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // 所有接口
                .allowCredentials(true) // 是否发送 Cookie
                .allowedOriginPatterns("*") // 支持域
                .allowedMethods(new String[]{"GET", "POST", "PUT", "DELETE"}) // 支持方法
                .allowedHeaders("*")
                .exposedHeaders("*");
    }
}
```
