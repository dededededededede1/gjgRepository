# 常用类

## 二、List

### 1、简介

* Java工作中常用的List类有`ArrayList`和`LinkList`两种。

* 如何定义一个List?

* ```
  List<?> list1 = new ArrayList<>();
  List<?> list2 = new LinkedList<>();
  ```

### 2、常用方法

#### （1）向List中添加元素

* 调用`add()`方法，参数为生成List对象<>中对应类型。

* ```
  String str1 = "abc";
  List<String> list = new ArrayList<>();
  list.add(str1);
  ```

#### （2）获取List中的元素

* 调用`get()`方法，参数为list中元素的下标，返回值为生成List对象<>中对应类型。

* ```
  String str1 = "abc";
  List<String> list = new ArrayList<>();
  list.add(str1);
  String str2 = list.get(0);
  System.out.println(str2);
  
  控制台输出：
  abc
  ```

#### （3）判断List中是否存在该元素

* 调用`contains()`方法，参数为对象，返回值为boolean类型。该方法比较的是内存地址和`hashcode`

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0001","张三","123456");
  List<User> list = new ArrayList<>();
  list.add(user1);
  boolean flag = list.contains(user2);
  System.out.println(flag);
  flag = list.contains(user1);
  System.out.println(flag);
  
  控制台输出：
  false
  true
  ```

####  (4)获取List的长度

* 调用`size()`方法。

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0001","张三","123456");
  List<User> list = new ArrayList<>();
  System.out.println(list.size());
  list.add(user1);
  System.out.println(list.size());
  list.add(user2);
  System.out.println(list.size());
  
  控制台输出：
  0
  1
  2
  ```

#### （5）移除List中的元素

* 调用`remove()`方法，参数有两种，一种为object根据对象移除，返回值为boolean。注意：调用for循环remove的时候注意下标问题。

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0002","李四","123456");
  List<User> list = new ArrayList<>();
  list.add(user1);
  list.add(user2);
  
  boolean flag = list.remove(user2);
  System.out.println(flag);
  User user = list.remove(0);
  System.out.println(user.getUserName());
  System.out.println(list.size());
  
  控制台输出：
  true
  张三
  0
  ```

####   (6)将一个集合，合并到另一个集合

* 调用`addAll()`方法，参数为另一个集合

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0002","李四","123456");
  List<User> list = new ArrayList<>();
  List<User> list1 = new ArrayList<>();
  list.add(user1);
  list.add(user2);
  System.out.println(list.size());
  list1.add(user1);
  list1.add(user2);
  list.addAll(list1);
  System.out.println(list.size());
  
  控制台输出：
  2
  4
  ```

#### （7）List中重复数据和null值问题

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  List<User> list = new ArrayList<>();
  list.add(user1);
  list.add(user1);
  System.out.println(list.size());
  list.add(user2);
  list.add(user2);
  list.add(user2);
  System.out.println(list.size());
  
  控制台输出：
  2
  5
  ```

* 从以上程序可以看出，list中存储了两个`user1`，所以List中可以存储重复数据。
* 从以上程序可以看出，list中存储了多个`user2`，所以List中可以存储null，并且可以存储多个。

#### （8）ArrayList和LinkList的区别

* ArrayList底层数据结构为数组，所以查询效率高，增删效率低。
* LinkList底层数据结构为链表，所以增删效率高，查询效率低。