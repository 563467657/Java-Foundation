# Integer



## 一、Integer的基本介绍

​		Integer是Java中非常常用的包装类型，这里会从JDK1.8入手，探究Integer源码中是如何实现的。

​		首先我们来看一下JDK源码中对Integer的描述

```java
/**
 * The {@code Integer} class wraps a value of the primitive type
 * {@code int} in an object. An object of type {@code Integer}
 * contains a single field whose type is {@code int}.
 *
 * <p>In addition, this class provides several methods for converting
 * an {@code int} to a {@code String} and a {@code String} to an
 * {@code int}, as well as other constants and methods useful when
 * dealing with an {@code int}.
 *
 * <p>Implementation note: The implementations of the "bit twiddling"
 * methods (such as {@link #highestOneBit(int) highestOneBit} and
 * {@link #numberOfTrailingZeros(int) numberOfTrailingZeros}) are
 * based on material from Henry S. Warren, Jr.'s <i>Hacker's
 * Delight</i>, (Addison Wesley, 2002).
 *
 * @author  Lee Boynton
 * @author  Arthur van Hoff
 * @author  Josh Bloch
 * @author  Joseph D. Darcy
 * @since JDK1.0
 */
```

​		我们来简单翻译一下

```text
	Integer类在一个对象中包装了一个原始类型int的值，一个Integer对象包含了一个int字段。
	另外，这个类提供了几个方法用来将一个int值转换为String对象。
	后面一段大概是说有些实现是根据Henry S. Warren, Jr.'s的资料Hacker's Delight。
```

​		没有太多特别的东西，大概意思就是说Integer和int的关系。

类图：

- 继承 Number
- 实现 Comparable<Integer> 可以比较大小

## 二、Integer一些特别的API

JAVAapi庞大，很多地方无法做到面面俱到，我们可以根据思路来阅读源码，会更容易理解其中的思想。

* 进制转换

```java
public static String toString(int i, int radix);
```

​		英文的描述很难理解，实际上整个代码的含义是返回一个整形i的在radix进制下的值，或者说是补码可能更为准确，其中

```java
if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            radix = 10;
```

​		表示radix在小于2或大于36，都会返回10进制的String值。

​		类似的API还有几个

```java
@since 1.8
public static String toUnsignedString(int i, int radix) {
	return Long.toUnsignedString(toUnsignedLong(i), radix);
}

private static String toUnsignedString0(int val, int shift) {
	// assert shift > 0 && shift <=5 : "Illegal shift value";
	int mag = Integer.SIZE - Integer.numberOfLeadingZeros(val);
	int chars = Math.max(((mag + (shift - 1)) / shift), 1);
	char[] buf = new char[chars];
	formatUnsignedInt(val, shift, buf, 0, chars);
	// Use special constructor which takes over "buf".
	return new String(buf, true);
}
//十六进制
public static String toHexString(int i) {
	return toUnsignedString0(i, 4);
}
//八进制
public static String toOctalString(int i) {
	return toUnsignedString0(i, 3);
}
//二进制
public static String toBinaryString(int i) {
	return toUnsignedString0(i, 1);
}
```

​		可见实现方法有不同，但提供的API思路是相同的，如有兴趣可以详细了解一下。

​		总结来说

- 正数toString和toUnsignedString是一样的

- 负数结果可以看如下代码

  ```
  System.out.println(Integer.toString(-2, 2));
  >> -10
  System.out.println(Integer.toUnsignedString(-2, 2));
  >> 11111111111111111111111111111110
  ```

  很多时候国人对一些英文单词释义会不明所以，例如上面代码中的Unsigned字面意思为无符号，对应结果为二进制以首位表示符号，不存在上面toString方法结果中的`-`号（负号）。



今天先分析到这里，明天继续