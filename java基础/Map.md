# 常用类

## Map类

#### 1、简介

* Java中的Map是一个接口，表示一组键值对映射关系。

* 它的实现类包括`HashMap`、`TreeMap`、`LinkedHashMap`、`ConcurrentHashMap`等。

* ```
  定义一个Map对象：Map<String,Object> map = new HashMap<>();
  ```

* Map类的尖括号里，有两个泛型，第一个代表Key的类型，第二个代表value的类型。

### 2、常用方法

#### （1）向Map中添加对象

* 调用put（）方法，向Map中添加对象,有两个参数，分别为Map的key和value属性。

* ```
  User user1 = new User("U0001","张三","123456");
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  ```

#### （2）获取Map中的值

* 调用get（）方法，根据之前put时的key获取，返回值为value对应的泛型。

* ```
  User user1 = new User("U0001","张三","123456");
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  User user2 = userMap.get("U0001");
  System.out.println(user2.getUserName());
  
  控制台输出：
  张三
  ```

#### （3）判断是否存在为key的某个值

* 调用containsKey（）方法，返回值为boolean，参数为key所对应的泛型。

* ```
  User user1 = new User("U0001","张三","123456");
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  System.out.println(userMap.containsKey("U0001"));
  
  控制台输出：
  true
  ```

#### （4）判断是否存在为value的某个值

* 调用containsValue（）方法，返回值为boolean，参数为value所对应的泛型。

* ```
  User user1 = new User("U0001","张三","123456");
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  System.out.println(userMap.containsValue(user1));
  
  控制台输出：
  true
  ```

#### （5）关于Map存储重复数据和null值问题

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  userMap.put("U0001",user2);
  System.out.println(userMap.size());
  
  控制台输出：
  1
  ```

* 由此得出key不可以重复，具有唯一性。

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user1);
  userMap.put("U0002",user1);
  System.out.println(userMap.size());
  
  控制台输出：
  2
  ```

* 由此得出，只要key不同，相同的value值可以存储多个。

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  Map<String,User> userMap = new HashMap<>();
  userMap.put(null,user1);
  userMap.put(null,user2);
  System.out.println(userMap.size());
  
  控制台输出：
  1
  ```

* 由此得出，key值可以存储null，但是由于key 的唯一性，只能存储一个null；

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  Map<String,User> userMap = new HashMap<>();
  userMap.put("U0001",user2);
  userMap.put("U0001",user2);
  System.out.println(userMap.size());
  
  控制台输出：
  2
  ```

* 由此得出，value值可以存储null，只要key不同，value为null可以存储多个

#### （6）Map的一些小技巧

* 当两个List需要互相匹配的时候，双重for循环很浪费性能，我们可以根据需要匹配的值，将List转化为Map，就是以匹配的值为key，其他属性为value，这样我们就只需要执行一次for循环就，根据匹配的值从Map里面取就可以。

* ```
          List<User> userList = new ArrayList<>();
          List<User> userList1 = new ArrayList<>();
          //现在要将两个List互相匹配，如果userList里存在和userList1
          //userNo相同的数据，就输出存在。
  //        for(User user:userList){
  //            for (User user1:userList1){
  //                if(user.getUserNo().equals(user1.getUserNo())){
  //                    System.out.println("存在重复数据");
  //                }
  //            }
  //        }
          //我们可以双重for循环遍历，但是双重for循环十分浪费性能
          Map<String,User> userMap = new HashMap<>();
          for(User user :userList){
              userMap.put(user.getUserNo(),user);
          }
          //我们可以定义一个Map以userNo为key
          for(User user :userList1){
              User user1 = userMap.get(user.getUserNo());
              if(user1!=null){
                  System.out.println("存在重复数据！");
              }
          }
          //这样只需要执行两次for循环即可。
  ```