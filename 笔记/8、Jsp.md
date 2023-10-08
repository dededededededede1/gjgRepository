# Jsp

## 一、简介

    JSP（Java Server Pages）的本质实际上是一个Servlet。当一个JSP页面被请求时，服务器会将其转换为一个Java Servlet，然后编译并执行该Servlet。这个过程被称为JSP的转译和编译。

    JSP的设计初衷是将页面展示逻辑与业务逻辑分离，使得开发者可以专注于设计HTML页面，而不必过多关注后台处理逻辑。尽管JSP和HTML看起来很相似，但JSP内部可以包含Java代码片段（脚本元素），这些代码片段在转换为Servlet之后会被执行，从而生成动态内容。

所以，可以说JSP的本质是Servlet，只是它提供了一种更加简洁和易于编写的方式来生成动态HTML页面。

## 二、`<input>`标签

`<input>`标签是HTML中的一个基本元素，用于在表单中创建各种类型的输入字段。根据 `type`属性的不同，`<input>`标签可以创建不同类型的输入框。以下是几种常用的 `<input>`类型及其用途：

1. text（文本）：

   `<input type="text">`用于创建一个单行文本输入框。例如，用于输入用户名：

   ```
   <label for="username">用户名：</label>
   <input type="text" id="username" name="username">
   ```
2. password（密码）：

   `<input type="password">`用于创建一个密码输入框，输入的内容会被显示为隐藏字符（通常是点或星号）。例如，用于输入密码：

   ```
   <label for="password">密码：</label>
   <input type="password" id="password" name="password">
   ```
3. radio（单选按钮）：

   `<input type="radio">`用于创建一组单选按钮，用户只能选择其中一个选项。例如，选择性别：

   ```
   <label for="male">男</label>
   <input type="radio" id="male" name="gender" value="male">
   <label for="female">女</label>
   <input type="radio" id="female" name="gender" value="female">
   ```
4. checkbox（复选框）：

   `<input type="checkbox">`用于创建一个复选框，用户可以选择多个选项。例如，选择感兴趣的话题：

   ```
   <label for="sports">运动</label>
   <input type="checkbox" id="sports" name="interests" value="sports">
   <label for="music">音乐</label>
   <input type="checkbox" id="music" name="interests" value="music">
   ```
5. submit（提交按钮）：

   `<input type="submit">`用于创建一个提交按钮，用于提交表单数据。例如：

   ```
   <input type="submit" value="提交">
   ```
6. email（电子邮件）：

   `<input type="email">`用于创建一个电子邮件输入框，它会验证输入的文本是否符合电子邮件格式。例如，输入电子邮件地址：

   ```
   <label for="email">电子邮件：</label>
   <input type="email" id="email" name="email">
   ```
7. number（数字）：

   `<input type="number">`用于创建一个数字输入框，用户可以输入整数或小数。例如，输入年龄：

   ```
   <label for="age">年龄：</label>
   <input type="number" id="age" name="age" min="1" max="100">
   ```

以上只是 `<input>`标签常用的一部分类型，还有其他类型如：`date`（日期）、`time`（时间）、`range`（范围）等，根据实际需求选择合适的类型即可。

## 三、form表单

HTML表单（form）是一种用于收集用户输入数据的交互式Web页面元素。表单通常包含多个输入元素（如 `<input>`、`<textarea>`和 `<select>`等）以及一个提交按钮，用于将用户填写的数据发送到服务器进行处理。下面是一个简单的表单示例，包括用户名、密码、性别和提交按钮：

```
<!DOCTYPE html>
<html>
<head>
    <title>示例表单</title>
</head>
<body>
    <form action="/submit" method="POST">
        <!-- 用户名 -->
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username">
        <br>
        <!-- 密码 -->
        <label for="password">密码：</label>
        <input type="password" id="password" name="password">
        <br>
        <!-- 性别 -->
        <label for="male">男</label>
        <input type="radio" id="male" name="gender" value="male">
        <label for="female">女</label>
        <input type="radio" id="female" name="gender" value="female">
        <br>
        <!-- 提交按钮 -->
        <input type="submit" value="提交">
    </form>
</body>
</html>
```

在这个示例中，我们使用了 `<form>`标签定义了一个表单。`action`属性指定了表单数据提交后处理请求的服务器端URL（本例中是 `/submit`），`method`属性指定了发送数据时使用的HTTP方法（本例中是 `POST`）。

表单内包含了三个输入字段和一个提交按钮：

1. 用户名：一个 `type="text"`的 `<input>`元素用于输入用户名。
2. 密码：一个 `type="password"`的 `<input>`元素用于输入密码，输入的字符会被隐藏。
3. 性别：两个 `type="radio"`的 `<input>`元素用于选择性别。注意，它们的 `name`属性相同，这样用户只能选择其中一个选项。
4. 提交按钮：一个 `type="submit"`的 `<input>`元素用于提交表单数据。

当用户填写完表单并点击提交按钮时，浏览器会将表单数据编码后发送到服务器的 `/submit`URL进行处理，通常会得到一个新的页面或者更新当前页面的部分内容。服务器端可以使用各种编程语言（如PHP、Python、Java等）来处理表单数据，例如存储到数据库或发送电子邮件等。

## 四、Servlet如何通过form表单获取各种类型input框的值

为了演示如何在Servlet中通过form表单获取各种类型的input框的值，我们首先创建一个简单的HTML表单，包含不同类型的输入框。然后，我们将创建一个Servlet来处理表单提交的数据。

1. 创建一个简单的HTML表单：

```
<!DOCTYPE html>
<html>
<head>
    <title>示例表单</title>
</head>
<body>
    <form action="processForm" method="POST">
        <!-- 用户名 -->
        <label for="username">用户名：</label>
        <input type="text" id="username" name="username">
        <br>
        <!-- 密码 -->
        <label for="password">密码：</label>
        <input type="password" id="password" name="password">
        <br>
        <!-- 性别 -->
        <label for="male">男</label>
        <input type="radio" id="male" name="gender" value="male">
        <label for="female">女</label>
        <input type="radio" id="female" name="gender" value="female">
        <br>
        <!-- 兴趣爱好 -->
        <label for="sports">运动</label>
        <input type="checkbox" id="sports" name="hobbies" value="sports">
        <label for="music">音乐</label>
        <input type="checkbox" id="music" name="hobbies" value="music">
        <br>
        <!-- 提交按钮 -->
        <input type="submit" value="提交">
    </form>
</body>
</html>
```

1. 创建一个Servlet来处理表单数据：

```
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FormProcessingServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置请求编码，防止中文乱码
        request.setCharacterEncoding("UTF-8");

        // 获取表单输入框的值
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String gender = request.getParameter("gender");
        String[] hobbies = request.getParameterValues("hobbies");

        // 输出获取到的表单数据
        response.setContentType("text/html;charset=UTF-8");
        response.getWriter().println("用户名：" + username + "<br>");
        response.getWriter().println("密码：" + password + "<br>");
        response.getWriter().println("性别：" + gender + "<br>");
        response.getWriter().println("兴趣爱好：");
        if (hobbies != null) {
            for (String hobby : hobbies) {
                response.getWriter().println(hobby + " ");
            }
        }
    }
}
```

在这个示例中，`<form>`标签的 `action`属性设置为"processForm"，用于指定提交表单数据后处理请求的Servlet。当用户填写表单并点击提交按钮后，表单数据会通过POST方法发送到"processForm"对应的Servlet。

在 `FormProcessingServlet`中，我们重写了 `doPost`方法来处理表单数据。首先设置请求编码为UTF-8，防止中文乱码。然后使用 `request.getParameter()`方法获取单个值的输入框（如text、password和radio），使用 `request.getParameterValues()`方法获取多个值的

## 五、jsp的四大作用域

JSP中的四大作用域是指在JSP页面中使用的四种不同的存储数据和对象的范围。这些作用域分别是：page、request、session和application。它们之间的主要区别在于它们的生命周期和可访问范围。

1. page作用域： 

   page作用域是指在当前JSP页面中有效的范围。在page作用域中存储的数据只在当前页面中可用。当服务器处理完当前页面后，page作用域中的数据就会被销毁。可以使用JSP的内置对象 `pageContext`来设置和获取page作用域的属性。

   ```
   <% pageContext.setAttribute("key", "value"); %>
   <% String value = (String) pageContext.getAttribute("key"); %>
   ```
2. request作用域：

   request作用域是指在一次HTTP请求中有效的范围。在request作用域中存储的数据可以在同一次请求中的其他页面（包括被请求页面、被包含页面、被转发页面等）中使用。可以使用JSP的内置对象 `request`来设置和获取request作用域的属性。

   ```
   <% request.setAttribute("key", "value"); %>
   <% String value = (String) request.getAttribute("key"); %>
   ```
3. session作用域：

   session作用域是指在一次用户会话中有效的范围。在session作用域中存储的数据可以在同一用户的多次请求中使用，直到用户会话结束。session作用域通常用于存储用户相关的信息，如登录状态、用户设置等。可以使用JSP的内置对象 `session`来设置和获取session作用域的属性。

   ```
   <% session.setAttribute("key", "value"); %>
   <% String value = (String) session.getAttribute("key"); %>
   ```
4. application作用域：

   application作用域是指在整个Web应用程序中有效的范围。在application作用域中存储的数据可以在应用程序的任何地方使用，直到应用程序关闭。application作用域通常用于存储全局配置信息和应用程序级别的共享数据。可以使用JSP的内置对象 `application`（实际上是一个 `ServletContext`对象）来设置和获取application作用域的属性。

   ```
   <% application.setAttribute("key", "value"); %>
   <% String value = (String) application.getAttribute("key"); %>
   ```

总结一下，JSP的四大作用域是page、request、session和application，它们根据数据的生命周期和可访问范围划分，为JSP提供了灵活的数据存储和访问方式。根据实际需求选择合适的作用域来存储数据和对象。

## 六、 jsp的九大内置对象

在JSP中，有九个内置对象（也称为隐式对象）可供开发者使用，这些对象在JSP页面中可直接使用，无需声明和初始化。以下是九大内置对象的简要介绍：

1. request（HttpServletRequest）：

   request对象代表客户端的请求。它包含了请求的URL、HTTP方法、请求头、请求参数等信息。通过request对象，开发者可以获取客户端提交的数据和请求相关的信息。
2. response（HttpServletResponse）：

   response对象代表服务器端的响应。它包含了响应的状态码、响应头、响应正文等信息。通过response对象，开发者可以设置响应的内容类型、编码、状态码等信息，以及向客户端发送数据。
3. pageContext（PageContext）：

   pageContext对象提供了一个与当前JSP页面相关的上下文环境。它可以用于获取和设置当前页面的属性，以及获取其他内置对象（如request、response、session等）。
4. session（HttpSession）：

   session对象代表一个用户会话。它可以用于在多个请求之间存储用户相关的信息，例如用户登录状态、用户设置等。
5. application（ServletContext）：

   application对象代表整个Web应用程序的上下文环境。它可以用于存储全局配置信息和应用程序级别的共享数据。
6. out（JspWriter）：

   out对象是一个用于向客户端发送响应数据的输出流。它可以用于输出HTML内容、文本、JavaScript等。开发者可以使用 `out.print()`和 `out.println()`方法向客户端发送数据。
7. config（ServletConfig）：

   config对象代表当前JSP页面对应的Servlet的配置信息。它可以用于获取Servlet的初始化参数，例如在 `web.xml`文件中设置的参数。
8. page（Object）：

   page对象表示当前JSP页面本身。它实际上是一个指向当前页面对应的Servlet实例的引用。通常情况下，开发者很少直接使用page对象。
9. exception（Throwable）：

   exception对象仅在错误页面中可用。当一个JSP页面抛出异常并被错误页面捕获时，可以通过exception对象获取异常信息。错误页面需要在 `web.xml`文件中进行配置，或者在JSP页面中使用 `<%@ page errorPage="..." %>`指令指定。

这些内置对象为开发者提供了方便的方式来处理请求、响应、会话、应用程序等相关的操作。在使用这些对象时，请注意它们的作用域和生命周期，以避免出现意外的错误。
