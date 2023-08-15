# 常用类

## 一、String

## 1、简介

* String是一个类，不属于八种基本数据类型之一。
* String可以通过    `String str = "abc";`定义一个变量。
* String类被final关键字修饰，所以该类不可以被继承。

## 2、常用方法

#### （1）字符串切割

* 调用`String[] str2 = str.split("c");`方法可以根据特定字符串切割字符串，并返回字符串数组。

* ```
  String str = "abcabcabcabc";
  String[] str2 = str.split("c");
  for (int i = 0;i<str2.length;i++){
      System.out.println(str2[i]);
  }
  
  输出结果：
  ab
  ab
  ab
  ab
  ```

#### （2）字符串替换

* 调用`String str2 = str.replace("ab","d");`方法可以将目标字符串中特定的字符串替换为指定字符串。

* ```
  String str = "abcabcabcabc";
  String str2 = str.replace("ab","d");
  System.out.println(str2);
  
  输出结果：
  dcdcdcdc
  ```

#### （3）字符串截取

* 调用substring方法截取字符串，该方法有集中情况

* 第一种：

* ```
  String str = "abcdefgh";
  String str2 = str.substring(2,4);
  System.out.println(str2);
  
  输出结果：
  cd
  ```

* `substring`中参数为两个时，代表需要截取字符串的首尾，根绝截取结果可得出结论“**包左不包右**”

* 第二种：

* ```
  String str = "abcdefgh";
  String str2 = str.substring(4);
  System.out.println(str2);
  
  输出结果：
  efgh
  ```

* substring参数为一个时，代表从参数下表位置开始包含参数下表字符，截取后面的所有字符串

#### （4）字符串下标获取

* 调用`indexOf()`方法获取字符串内字符串的首字符下标

* ```
  String str = "abcabc";
  int index1 = str.indexOf("ab");
  int index2 = str.indexOf("ab",1);
  int index1 = str.indexOf("cc");
  System.out.println(index1);
  System.out.println(index2);
  
  输出结果：
  0
  3
  -1
  ```

* `str.indexOf("ab");`返回值为“ab”字符串在`str`字符串中出现第一次的“a”字符的下标。
* `int index2 = str.indexOf("ab",1);`返回值为字符串“ab”在字符串中第1+1次出现的时“a”字符的下标。
* `int index1 = str.indexOf("cc");`在这个字符串中没有“cc”字符串，所以返回值为-1

#### （5）判断字符串里面是否含有某字符串

* 调用`contains`方法

* ```
  String str = "abcabc";
  boolean flag = str.contains("ab");
  System.out.println(flag);
  
  输出结果：
  true
  ```

* 字符串中含有“ab”字符串，所以返回值为“true”

## 3、字符串常量池

* 字符串常量池在JVM中是StringTable类，实际是一个固定大小的HashTable(一种高效用来进行查找的数据结构）

* 在Java程序中，类似于：1， 2， 3，3.14，“hello”等字面类型的常量经常频繁使用，为了使程序的运行速度更快、更节省内存，提供了一个String类型的常量池，只要是双引号引起来的对象，都会存放在常量池中。

* 通过    `String str = "abc"创建String变量时，会先去字符串常量池中查询，如果字符串常量池中以存在该字符串，则将该字符出字符常量池中的地址指向该字符串，如果字符串常量池中不存在该字符串，则添加至字符串常量池，并将字符串常量池中的地址指向该字符串。

* 调用String类的构造方法`String str = new String("123");`生成String对象时,生成两个对象，第一个为字符串常量池中的对象，另一个为调用new关键字生成的对象，两个对象的内存地址不同。

* ```
  String str = new String("123");
  String a = "123";
  System.out.println(a==str);
  
  输出结果：
  false
  --------------------------------
  String a = "123";
  String str = new String("123");
  System.out.println(a==str);
  
  输出结果：
  true
  ```