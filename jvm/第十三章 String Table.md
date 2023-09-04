# 第十三章 String Table

## 1 String的基本特性

### String的基本特性

* String：字符串，使用一对""引起来表示
* String声明为final的，不可被继承
* String实现了Serializable接口：表示字符串是支持序列化的。实现了Comparable接口：表示String可以比较大小
* String在jdk8及以前内部定义了final char[] value用于存储字符串数据。jdk9时改为byte[]。

#### String在jdk9中存储结构变更

http://openjdk.java.net/jeps/254

<img src="C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20221224161536380.png" alt="image-20221224161536380" style="zoom:67%;" />

<img src="C:\Users\ASUS\AppData\Roaming\Typora\typora-user-images\image-20221224161556707.png" alt="image-20221224161556707" style="zoom:67%;" />

**结论：**String再也不用char[]来存储啦，改成了byte[]加上编码标记，节约了一些空间。

```java
public final class String
    implements java.io.Serializable, Comparable<String>, CharSequence {
    @Stable
    private final byte value[];
}
```

那么StringBuffer和StringBuilder是否仍无动于衷呢？

```
String-related classes such as AbstractStringBuilder, StringBuilder,
and StringBuffer will be updated to use the same representation,as will the
HotSpot VM's intrinsic(固有的、内置的) string operations.
```



* String代表不可变的字符序列。简称：不可变性
  * 当对字符串重新赋值时，需要重写指定内存区域赋值，不能使用原有的value进行赋值。
  * 当对现有的字符串进行连接操作时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
  * 当调用String的replace ()方法修改指定字符或字符串时，也需要重新指定内存区域赋值，不能使用原有的value进行赋值。
* 通过字面量的方式(区别于new)给一个字符串赋值，此时的字符串值声明在字符串常量池中。

* **字符串常量池中是不会存储相同内容的字符串的。**
  * String的String Pool是 一个固定大小的Hashtable，默认值大小长度是1009。如果放进String Pool的String非常多， 就会造成Hash冲突严重，从而导致链表会很长，而链表长了后直接会造成的影响就是当调用String.intern时性能会大幅下降。
  * 使用**-XX:StringTableSize**可设置StringTable的长度。
  * 在jdk6中StringTable是固定的，就是**1009**的长度，所以如果常量池中的字符串过多就会导致效率下降很快。StringTableSize设置没有要求。
  * 在jdk7中，StringTable的长度默认值是**60013**，StringTableSize设置没有要求。
  * 在jdk8中，设置StringTable的长度的话，1009是可设置的最小值。

## 2 String的内存分配



## 3 String的基本操作

## 4 字符串拼接操作

## 5 intern()的使用

## 6 StringTable的垃圾回收

## 7 G1中的String去重操作