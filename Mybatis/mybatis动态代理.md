### MyBatis基础

#### MyBatis基础知识点

* ${}属于静态文本替换，是properties文件中的变量占位符，主要用于标签属性名和sql内部。
* #{}是sql的**参数**占位符，MyBatis会将#{}替换成?号，在sql执行前会使用 `PrearedStatement`的参数设置方法，按序给sql的?号占位符设置参数值。

#### MyBatis中的常见标签

| 标签         | 属性说明 |
| :----------- | -------- |
| parameterMap |          |
| include      |          |
| selectkey    |          |
| trim         |          |
| where        |          |
| set          |          |
| foreach      |          |
| if           |          |
| choose       |          |
| when         |          |
| otherwise    |          |
| bind         |          |

#### MyBatis核心组件介绍

| 组件名称                         | 组件描述                                                                                                  |
| -------------------------------- | --------------------------------------------------------------------------------------------------------- |
| SqlSessionFactoryBuilder(构造器) | 它可以通过xml，注解或者手动配置来创建SqlSessionFacotry                                                    |
| SqlSessionFactory                | 用来创建SqlSession(会话)的工厂                                                                            |
| SqlSession                       | SqlSession是MyBatis最核心的类，可以用来执行语句，提交或者回滚事务以及获取映射器Mapper的接口               |
| SQL Mapper                       | 它是由一个接口，一个xml配置文件或者注解构成，需要给出对应的SQL和映射规则，它负责发送SQL去执行，并发挥结果 |

```java
public class MybatisTest {
    private static SqlSessionFactory sqlSessionFactory;
    static {
        try {
            sqlSessionFactory = new SqlSessionFactoryBuilder()
                    .build(Resources.getResourceAsStream("mybatis-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            UserMapper userMapper = sqlSession.getMapper(UserMapper.class);
            User user = userMapper.selectById(1);
            System.out.println("User : " + user);
        }
    }
}
// 结果：
User : User{id=1, age=21, name='pjmike'}
复制代码
```

### mybatis动态代理实现

```java
public static void main(String[] args) {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
        UserMapper userMapper = sqlSession.getMapper(UserMapper.class);// <1>
        User user = userMapper.selectById(1);
        System.out.println("User : " + user);
    }
}
复制代码
```

在前面的例子中，我们使用sqlSession的getMapper方法获取了UserMapper对象，实际上我们获取的是UserMapper接口的代理类，然后由代理类来执行方法。在探索动态代理类实现之前，我们需要先明确sqlSessionFactory工厂的创建过程做了哪些准备工作。

#### mybatis全局配置文件解析

```java
private static SqlSessionFactory sqlSessionFactory;
static {
    try {
        sqlSessionFactory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    } catch (IOException e) {
        e.printStackTrace();
    }
}
复制代码
```

我们使用 `new SqlSessionFactoryBuilder().build()`方法创建SqlSessionFactory工厂

```java
public SqlSessionFactory build(InputStream inputStream, Properties properties) {
    return build(inputStream, null, properties);
  }

  public SqlSessionFactory build(InputStream inputStream, String environment, Properties properties) {
    try {
      XMLConfigBuilder parser = new XMLConfigBuilder(inputStream, environment, properties);
      return build(parser.parse());
    } catch (Exception e) {
      throw ExceptionFactory.wrapException("Error building SqlSession.", e);
    } finally {
      ErrorContext.instance().reset();
      try {
        inputStream.close();
      } catch (IOException e) {
        // Intentionally ignore. Prefer previous error.
      }
    }
  }
复制代码
```

对于mybatis的全局配置文件解析，相关的解析代码主要在 `XMLConfigBuilder`的 `parse()`方法中

```java
public Configuration parse() {
        if (this.parsed) {
            throw new BuilderException("Each XMLConfigBuilder can only be used once.");
        } else {
            this.parsed = true;
            //解析全局配置文件
            this.parseConfiguration(this.parser.evalNode("/configuration"));
            return this.configuration;
        }
    }

  private void parseConfiguration(XNode root) {
        try {
            this.propertiesElement(root.evalNode("properties"));
            Properties settings = this.settingsAsProperties(root.evalNode("settings"));
            this.loadCustomVfs(settings);
            this.typeAliasesElement(root.evalNode("typeAliases"));
            this.pluginElement(root.evalNode("plugins"));
            this.objectFactoryElement(root.evalNode("objectFactory"));
            this.objectWrapperFactoryElement(root.evalNode("objectWrapperFactory"));
            this.reflectorFactoryElement(root.evalNode("reflectorFactory"));
            this.settingsElement(settings);
            this.environmentsElement(root.evalNode("environments"));
            this.databaseIdProviderElement(root.evalNode("databaseIdProvider"));
            this.typeHandlerElement(root.evalNode("typeHandlers"));
            //解析映射器配置文件
            this.mapperElement(root.evalNode("mappers"));
        } catch (Exception var3) {
            throw new BuilderException("Error parsing SQL Mapper Configuration. Cause: " + var3, var3);
        }
    }
复制代码
```

从 `parseConfiguration`方法的源码中可以看出，`XmlConfigBuilder`读取 `mybatis-config.xml`中的配置信息，然后将信息保存到 `configuration`类中

#### 映射器Mapper文件解析

```java
//解析映射器配置文件
this.mapperElement(root.evalNode("mappers"));
复制代码
```

该方法是对全局配置文件中 `mappers`属性的解析

```java
private void mapperElement(XNode parent) throws Exception {
        if (parent != null) {
            Iterator var2 = parent.getChildren().iterator();

            while(true) {
                while(var2.hasNext()) {
                    XNode child = (XNode)var2.next();
                    String resource;
                    if ("package".equals(child.getName())) {
                        resource = child.getStringAttribute("name");
                        this.configuration.addMappers(resource);
                    } else {
                        resource = child.getStringAttribute("resource");
                        String url = child.getStringAttribute("url");
                        String mapperClass = child.getStringAttribute("class");
                        XMLMapperBuilder mapperParser;
                        InputStream inputStream;
                        if (resource != null && url == null && mapperClass == null) {
                            ErrorContext.instance().resource(resource);
                            inputStream = Resources.getResourceAsStream(resource);
                            mapperParser = new XMLMapperBuilder(inputStream, this.configuration, resource, this.configuration.getSqlFragments());
                            mapperParser.parse();
                        } else if (resource == null && url != null && mapperClass == null) {
                            ErrorContext.instance().resource(url);
                            inputStream = Resources.getUrlAsStream(url);
                            mapperParser = new XMLMapperBuilder(inputStream, this.configuration, url, this.configuration.getSqlFragments());
                            mapperParser.parse();
                        } else {
                            if (resource != null || url != null || mapperClass == null) {
                                throw new BuilderException("A mapper element may only specify a url, resource or class, but not more than one.");
                            }

                            Class<?> mapperInterface = Resources.classForName(mapperClass);
                            this.configuration.addMapper(mapperInterface);
                        }
                    }
                }

                return;
            }
        }
    }
复制代码
```

其中的 `mapperParser.parse()`方法就是 `XmlMapperBuilder`对映射器文件的解析

```java
public void parse() {
        if (!this.configuration.isResourceLoaded(this.resource)) {
            //解析映射文件的mapper节点，该方法主要用于将mapper文件中的元素信息解析到MappedStatement对象，并保存到configuration类的mappedStatements属性中
            this.configurationElement(this.parser.evalNode("/mapper"));
            this.configuration.addLoadedResource(this.resource);
            //重点方法，这个方法内部会根据namespace属性值，生成动态代理类
            this.bindMapperForNamespace();
        }

        this.parsePendingResultMaps();
        this.parsePendingCacheRefs();
        this.parsePendingStatements();
    }
复制代码
```

核心方法：`bindMapperForNamespace()`方法，该方法会根据mapper文件中的namespace属性值，为接口类生成动态代理类

#### 动态代理类的生成

```java
private void bindMapperForNamespace() {
		//获取mapper元素的namespace的元素值
        String namespace = this.builderAssistant.getCurrentNamespace();
        if (namespace != null) {
            Class boundType = null;
            try {
                boundType = Resources.classForName(namespace);
            } catch (ClassNotFoundException var4) {
                //如果没有这个类，可以直接忽略，这是因为namespace属性值只需要保持唯一就可以了，并不一定对应一个XXXMapper接口，没有XXXMapper接口时，我们可以直接使用SqlSession来进行增删改查
            }

            if (boundType != null && !this.configuration.hasMapper(boundType)) {
                this.configuration.addLoadedResource("namespace:" + namespace);
                //如果namespace属性值有对应的java类，调用configuration中的addMapper方法，将其添加到MapperRegistry中
                this.configuration.addMapper(boundType);
            }
        }

    }
复制代码
```

```java
public <T> void addMapper(Class<T> type) {
    //这个类必须是一个class接口，因为使用的是JDK动态代理，所以需要接口，否则不会针对其生成动态代理
        if (type.isInterface()) {
            if (this.hasMapper(type)) {
                throw new BindingException("Type " + type + " is already known to the MapperRegistry.");
            }
            boolean loadCompleted = false;
            try {
                //用于生成一个MapperProxyFacotry,用于后面生成动态代理类
                this.knownMappers.put(type, new MapperProxyFactory(type));
                //以下代码块主要用于解析我们定义的XXXMapper接口里面使用的注解，这里主要处理不使用xml映射文件的情况
                MapperAnnotationBuilder parser = new MapperAnnotationBuilder(this.config, type);
                parser.parse();
                loadCompleted = true;
            } finally {
                if (!loadCompleted) {
                    this.knownMappers.remove(type);
                }

            }
        }

    }
复制代码
```

`MapperRegistry`内部维护了一个映射关系，每个接口对应一个MapperProxyFactory(生成动态代理工厂类)

```java
private final Map<Class<?>, MapperProxyFactory<?>> knownMappers = new HashMap();
复制代码
```

这样便于在后面调用 `MapperRegistry`的 `getMapper()`时，直接从Map中获取某一个接口对应的动态代理工厂类，然后再利用工厂类对其接口生成真正的动态代理类

#### Configuration的getMapper()方法

开始我们通过 `sqlsession的getMapper()`方法调用获取到动态代理类

```java
UserMapper userMapper = sqlSession.getMapper(UserMapper.class);

public class DefaultSqlSession implements SqlSession {

  private final Configuration configuration;
  private final Executor executor;
  @Override
  public <T> T getMapper(Class<T> type) {
    return configuration.getMapper(type, this);
  }
  ...
}
复制代码
```

`Configuration`中的 `getMapper()`方法内部其实是使用的 `MapperRegistry`的 `getMapper()`方法，`MapperRegistry`里面有 `MapperProxyFactory`代理工厂类

```java
public <T> T getMapper(Class<T> type, SqlSession sqlSession) {
        MapperProxyFactory<T> mapperProxyFactory = (MapperProxyFactory)this.knownMappers.get(type);
        if (mapperProxyFactory == null) {
            throw new BindingException("Type " + type + " is not known to the MapperRegistry.");
        } else {
            try {
                //这里可以看到，每次调用都会生成一个新的代理对象返回
                return mapperProxyFactory.newInstance(sqlSession);
            } catch (Exception var5) {
                throw new BindingException("Error getting mapper instance. Cause: " + var5, var5);
            }
        }
    }
复制代码
```

```java
protected T newInstance(MapperProxy<T> mapperProxy) {
    //这里使用JDK动态代理，通过Proxy.newProxyInstance生成动态代理类
    //newProxyInstance的参数：类加载器，接口类，InvocationHandler接口实现类
    //动态代理可以将所有的接口的调用重定向到调用处理器InvocationHandler，调用它的invoke方法
        return Proxy.newProxyInstance(this.mapperInterface.getClassLoader(), new Class[]{this.mapperInterface}, mapperProxy);
    }

    public T newInstance(SqlSession sqlSession) {
        MapperProxy<T> mapperProxy = new MapperProxy(sqlSession, this.mapperInterface, this.methodCache);
        return this.newInstance(mapperProxy);
    }
复制代码
```

这里就是InvocationHandler接口的实现类MapperProxy

```java
public class MapperProxy<T> implements InvocationHandler, Serializable {
    private static final long serialVersionUID = -6424540398559729838L;
    private final SqlSession sqlSession;
    private final Class<T> mapperInterface;
    private final Map<Method, MapperMethod> methodCache;

    public MapperProxy(SqlSession sqlSession, Class<T> mapperInterface, Map<Method, MapperMethod> methodCache) {
        this.sqlSession = sqlSession;
        this.mapperInterface = mapperInterface;
        this.methodCache = methodCache;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        try {
            //如果调用的是Object类中定义的方法，直接通过反射调用即可
            if (Object.class.equals(method.getDeclaringClass())) {
                return method.invoke(this, args);
            }

            if (this.isDefaultMethod(method)) {
                return this.invokeDefaultMethod(proxy, method, args);
            }
        } catch (Throwable var5) {
            throw ExceptionUtil.unwrapThrowable(var5);
        }
		//调用XXXMapper接口自定义的方法，进行代理
        //首先将当前被调用的方法构造成一个MapperMethod对象，然后调用其excute方法真正开始执行
        MapperMethod mapperMethod = this.cachedMapperMethod(method);
        return mapperMethod.execute(this.sqlSession, args);
    }
   	...
}
复制代码
```

```java
public Object execute(SqlSession sqlSession, Object[] args) {
        Object param;
        Object result;
        switch(this.command.getType()) {
        case INSERT:
            param = this.method.convertArgsToSqlCommandParam(args);
            result = this.rowCountResult(sqlSession.insert(this.command.getName(), param));
            break;
        case UPDATE:
            param = this.method.convertArgsToSqlCommandParam(args);
            result = this.rowCountResult(sqlSession.update(this.command.getName(), param));
            break;
        case DELETE:
            param = this.method.convertArgsToSqlCommandParam(args);
            result = this.rowCountResult(sqlSession.delete(this.command.getName(), param));
            break;
        case SELECT:
            if (this.method.returnsVoid() && this.method.hasResultHandler()) {
                this.executeWithResultHandler(sqlSession, args);
                result = null;
            } else if (this.method.returnsMany()) {
                result = this.executeForMany(sqlSession, args);
            } else if (this.method.returnsMap()) {
                result = this.executeForMap(sqlSession, args);
            } else if (this.method.returnsCursor()) {
                result = this.executeForCursor(sqlSession, args);
            } else {
                param = this.method.convertArgsToSqlCommandParam(args);
                result = sqlSession.selectOne(this.command.getName(), param);
            }
            break;
        case FLUSH:
            result = sqlSession.flushStatements();
            break;
        default:
            throw new BindingException("Unknown execution method for: " + this.command.getName());
        }

        if (result == null && this.method.getReturnType().isPrimitive() && !this.method.returnsVoid()) {
            throw new BindingException("Mapper method '" + this.command.getName() + " attempted to return null from a method with a primitive return type (" + this.method.getReturnType() + ").");
        } else {
            return result;
        }
    }
复制代码
```

在 `MapperMethod`中还有两个内部类，SqlCommand和MethodSignature类，在excute方法中首先使用 `swithc case`语句根据 `SqlCommand`的 `getType()`方法，判断要执行的sql类型，然后调用SqlSession的增删改查方法

#### getMapper()方法流程图

![](https://file+.vscode-resource.vscode-cdn.net/d%3A/VSCode-win32-x64-1.77.3/work/%E7%AC%94%E8%AE%B0/image/mybatis%E5%8A%A8%E6%80%81%E4%BB%A3%E7%90%86/1682644619721.png "补充sqlSession与configuration之间的关系")

![img](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/50eb562da97f46a0afc09ed89f65a12e~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

流程扩展：

`SqlSession`接口的 `getMapper `--> `DefaultSqlSession`或 `SqlSessionManager`实现的 `getMapper`并调用 --> `Configuration`的 `getMapper `-- >调用 `MapperRegistry`的 `getMapper`

--> 由 `MapperRegistry`去创建mapper的代理工厂，如果不空则调用 `newInstance`去创建mapper代理 --> 如果代理对象为空则 `newProxyInstance`去创建代理对象
