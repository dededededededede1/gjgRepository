# 常用类

## 三、set类

### 1、简介

* Set是Java开发中常用集合之一，其中最常用的set集合为`HashSet`。
* 定义一个Set对象：`Set<User> set = new HashSet<>();`
* <>代表泛型，尖括号里面写什么类型，这个Set集合就为该类型的集合

### 2、常用方法

#### （1）向set集合添加数据

* 调用add()方法，参数类型为尖括号中所对应的类型。

* ```
  User user1 = new User("U0001","张三","123456");
  Set<User> set = new HashSet<>();
  set.add(user1);
  ```

#### （2）从set集合中取出数据

* 通过增强for循环遍历set集合

* ```
  User user1 = new User("U0001","张三","123456");
  Set<User> set = new HashSet<>();
  set.add(user1);
  for(User user:set){
      System.out.println(user.getUserName());
  }
  
  控制台输出：
  张三
  ```

#### （3）判断set集合是否存在某对象

* 通过`contains()`方法

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0001","张三","123456");
  Set<User> set = new HashSet<>();
  set.add(user1);
  System.out.println(set.contains(user2));
  
  控制台输出：
  false
  ```

* 该方法比较的是set内数据的`hashcode`和内存地址，和本身数值没有关系。

#### （4）将一个set集合添加到另一个Set集合中

* 调用`addAll()`方法

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0001","张三","123456");
  Set<User> set = new HashSet<>();
  Set<User> set1 = new HashSet<>();
  set.add(user1);
  set.add(user2);
  set1.add(user1);
  set1.add(user2);
  set.addAll(set1);
  ```

#### （5）关于set能否存储重复数据和null值问题

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = new User("U0001","张三","123456");
  Set<User> set = new HashSet<>();
  set.add(user1);
  set.add(user1);
  set.add(user1);
  set.add(user2);
  System.out.println(set.size());
  
  控制台输出：
  2
  ```

* 根据以上代码得出，set多次存储user1但set 的长度并未发生变化，所以set不可以存储重复数据

* ```
  User user1 = new User("U0001","张三","123456");
  User user2 = null;
  Set<User> set = new HashSet<>();
  set.add(user1);
  set.add(user2);
  set.add(user2);
  set.add(user2);
  System.out.println(set.size());
  
  控制台输出：
  2
  ```

* 根据以上代码得出，set多次存储user2，但长度并未发生变化，所以set可以存储null，但只可以存储一个

#### （6）关于List去重问题

* ```
  User user1 = new User("U0001","张三","123456");
  List<User> list = new ArrayList<>();
  list.add(user1);
  list.add(user1);
  list.add(user1);
  list.add(user1);
  System.out.println(list.size());
  Set<User> set = new HashSet<>(list);
  List<User> list1 = new ArrayList<>(set);
  System.out.println(list1.size());
  
  控制台输出：
  4
  1
  ```

* 由以上代码可得出：将list转化为set，再转化为list，可以去除list中的重复数据（内存地址重复）