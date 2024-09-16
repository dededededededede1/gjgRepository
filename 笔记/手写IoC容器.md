步骤

## 1.用户定义好相关的类

如UserDao，UserDaoJdbcImpl，UserDaoMybatisImpl，UserService，UserServiceImpl；

    1.1并且在UserServiceImpl引用UserDao；

    1.2定义spring的配置文件，里面有`<beans>`标签，以及UserDaoJdbcImpl，UserServiceImpl，UserDaoMybatisImpl的bean

```
<beans>
    <bean id ="userDaoJdbcImpl" class="com.TBT.dao.impl.UserDaoJdbcImpl"></bean>

    <bean id="userDaoMybatisImpl" class="com.TBT.dao.impl.UserDaoMybatisImpl"></bean>

    <bean id="userService" class="com.TBT.service.impl.UserServiceImpl">
        <propety name="userDao" ref="userDaoJdbcImpl"/>
    </bean>
</beans>
```

## 2.定义MyClassPathXmlApplicationContext类，里面有解析xml文件的方法

```java
/**
     * 这个方法用来解析spring的xml文件
     * @param xmlPath
     */
    public void extractedXml(String xmlPath){
        // 解析xml配置文件，通过配置文件告诉spring容器，需要管理的bean有哪些，
        // 把解析出来的对象封装到BeanDefinition中
        // 解析出来的bean存到map集合中，id为key，bean的定义信息为
        SAXReader reader = new SAXReader();

        // 读取配置文件的路径，并存入输入流中
        InputStream resourceAsStream = this.getClass().getResourceAsStream(xmlPath);

        try {
            // 利用dom4j读取输入流，并得到一个document对象
            Document document = reader.read(resourceAsStream);
            // 得到文件中的bean的根标签，<beans>
            Element beans = document.getRootElement();
            // 利用迭代器遍历所有的bena标签
            Iterator<Element> beansIterator = beans.elementIterator();

            while (beansIterator.hasNext()){
                // 获取到每一个bean
                Element bean = beansIterator.next();
                // 得到bean标签中的id
                String beanId = bean.attributeValue("id");
                // 得到bean标签中的class
                String beanClass = bean.attributeValue("class");
//                System.out.println(beanId);
//                System.out.println(beanClass);

                // 将得到的数据封装
                // 创建beanDefinition对象封装id和class
                BeanDefinition beanDefinition = new BeanDefinition();
                beanDefinition.setBeanId(beanId);
                beanDefinition.setBeanClass(beanClass);

                // 得到bean标签下的子节点
                Iterator<Element> beanIter = bean.elementIterator();
                // 遍历
                while (beanIter.hasNext()){
                    Element property = beanIter.next();
                    // 假设只获取property的name和ref
                    String name = property.attributeValue("name");
                    String ref = property.attributeValue("ref");
                    // 保存
                    BeanPropertyDefinition propertyDefinition = new BeanPropertyDefinition();
                    propertyDefinition.setName(name);
                    propertyDefinition.setRef(ref);
              
                    // 存入map集合
                    Map<String, BeanPropertyDefinition> propertyDefinitionMap = beanDefinition.getPropertyDefinitionMap();
                    propertyDefinitionMap.put(name,propertyDefinition);
                }

                // 最后，将beanId和bean的定义信息存入大map中
                beanMap.put(beanId,beanDefinition);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }

    }
```

    利用dom4j解析xml文件

    2.1通过构造器传入的xml文件的路径，获取到xml文件里的根标签

    2.2遍历根标签，迭代器也好，forEach也好，获取子节点，也就是bean标签

    2.3获取到bean之后，利用attributeValue("指定bean标签中的属性名")获取到指定的属性值

    2.4再深层遍历bean标签，获取到property标签，同样的方法获取到里面的属性

    2.5定义一个类BeanPropertyDefinition类，存储property中的属性，将这个类存到map集合中

```java
/**
 * bean中属性的定义信息
 */
public class BeanPropertyDefinition {

    private String name; // property的name属性值
    private String ref; // property的ref属性值

    public BeanPropertyDefinition() {
    }

    public BeanPropertyDefinition(String name, String ref) {
        this.name = name;
        this.ref = ref;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
}

```

    2.6在外层循环的最后，定义BeanDefinition类，存储bean标签中的信息，将这个类存到map集合中

定义BeanDefinition类

```java
/**
 * bean的定义信息
 */
public class BeanDefinition {

    private String beanId; // bean的id

    private String beanClass; // bean的类的全路径

    // 存放bean的属性信息，key是属性名，value是属性的定义信息
    Map<String,BeanPropertyDefinition> propertyDefinitionMap = new HashMap<>();

    public BeanDefinition() {

    }

    public BeanDefinition(String beanId, String beanClass, Map<String, BeanPropertyDefinition> propertyDefinitionMap) {
        this.beanId = beanId;
        this.beanClass = beanClass;
        this.propertyDefinitionMap = propertyDefinitionMap;
    }

    public String getBeanId() {
        return beanId;
    }

    public void setBeanId(String beanId) {
        this.beanId = beanId;
    }

    public String getBeanClass() {
        return beanClass;
    }

    public void setBeanClass(String beanClass) {
        this.beanClass = beanClass;
    }

    public Map<String, BeanPropertyDefinition> getPropertyDefinitionMap() {
        return propertyDefinitionMap;
    }

    public void setPropertyDefinitionMap(Map<String, BeanPropertyDefinition> propertyDefinitionMap) {
        this.propertyDefinitionMap = propertyDefinitionMap;
    }
}

```

## 3.利用反射创建对象

    3.1遍历大map，获取到BeanDefinition，再得到beanClass属性

    3.2将得到的beanClass，利用反射创建对象

    3.3将得到的对象存入map集合中，key是beanId，value是这个对象

    3.4第一getBean(String beanId)方法，获取bean对象

```java
/**
     * 这个方法利用反射创建对象
     */
    private void createBeanObjects() {
        // 先遍历beanMap，获取到beanId与bean的定义信息
        Set<Map.Entry<String, BeanDefinition>> entries = beanMap.entrySet();
        entries.forEach(entry ->{
            String beanId = entry.getKey();
            BeanDefinition beanDefinition = entry.getValue();
            // 得到类的全限定类名
            String beanClass = beanDefinition.getBeanClass();

            // 创建对象
            try {
                Object clazz = Class.forName(beanClass).newInstance();

                // 将得到的对象存入objectsMap
                objectsMap.put(beanId,clazz);
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }
```

封装getBean方法

```java
/**
     * 获取bean
     * @param beanId
     * @return
     */
    public Object getBean(String beanId){
        Object obj = objectsMap.get(beanId);
        return obj;
    }
```

编写测试类

```java
public class testIoC {

    @Test
    public void testIoCXml(){
        // 创建自定义的解析xml文件的类对象
        MyClassPathXmlApplicationContext context = new MyClassPathXmlApplicationContext("/spring.xml");
        UserService userService = (UserService) context.getBean("userService");

        userService.save();
    }
}
```


## 4.DI的set方式注入

```java
/**
     * 通过di给对象里的对象属性赋值
     */
    public void depend_inject(){
        // 遍历beansMap获取到beanId和BeanDefinition的信息
        Set<Map.Entry<String, BeanDefinition>> entries = beansMap.entrySet();
        Iterator<Map.Entry<String, BeanDefinition>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<String, BeanDefinition> bean = iterator.next();
            String beanId = bean.getKey();
            BeanDefinition beanDefinition = bean.getValue();

            // 获取BeanDefinition中存储的property信息
            // 也就是propertyDefinitionMap
            // 遍历这个map集合获取到，property标签的name和ref
            Map<String, BeanPropertyDefinition> propertyDefinitionMap = beanDefinition.getPropertyDefinitionMap();
            Set<Map.Entry<String, BeanPropertyDefinition>> propEntries = propertyDefinitionMap.entrySet();
            Iterator<Map.Entry<String, BeanPropertyDefinition>> proIter = propEntries.stream().iterator();
            while (proIter.hasNext()){
                // 循环一次拿到一个key和value，也就是拿到一个name和BeanPropertyDefinition类
                Map.Entry<String, BeanPropertyDefinition> props = proIter.next();
                // 得到property标签中的name属性
                String propName = props.getKey(); // userDao
                BeanPropertyDefinition propertyDefinition = props.getValue();
                // 得到property标签中的ref属性
                String ref = propertyDefinition.getRef(); // userDaoJdbcImpl

                // 一个实现set注入的可行方法
                // 先拼接一个set，再切割propName的首字母转大写，再拼上后面的字母
                // 既 setUserDao
                String setMethodName = "set" + propName.substring(0,1).toUpperCase()
                        + propName.substring(1);

                // 反射
                try {
                    // 通过反射，得到这个类的字节码信息，通过bean的class属性值创建对象
                    Class<?> clazz = Class.forName(beanDefinition.getBeanClass());

                    // 得到这个类中的所有
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        // 判断这个类中是否有自己拼的方法
                        if (method.getName().equals(setMethodName)){
                            // 以下方式有点像多态的思想，通过多态创建对象
                            // 有
                            // 通过beanId得到具体对象
                            Object beanObj = objectsMap.get(beanId);
                            // 通过ref获取到对应的beanId的对象
                            Object refToBeanObj = objectsMap.get(ref);

                            // 此时method就是这个set方法(setUserDao),
                            // 调用method的invoke(哪个对象的，哪个方法)方法，赋值
                            method.invoke(beanObj,refToBeanObj);

                        }
                    }

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
```
