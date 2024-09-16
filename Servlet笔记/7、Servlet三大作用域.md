# 一、Servlet的ServletContext作用域

ServletContext是一个全局的存储区，它可以让所有的Servlet在一个Web应用中共享数据。ServletContext对象的生命周期与Web应用的生命周期相同，它在Web应用启动时创建，终止时销毁。ServletContext作用域主要有以下功能：

1. 共享数据：可以在整个Web应用中实现数据的共享，方便不同Servlet之间的通信。
2. 获取全局：全局初始化参数：可以通过ServletContext获取Web应用中的全局初始化参数。

3. 资源访问：可以通过ServletContext对象访问Web应用中的资源，如读取Web应用中的文件等。

下面我将通过两个Servlet的案例为您详细讲解ServletContext的作用域：

## 案例：

​	在一个Servlet中设置ServletContext作用域的属性，另一个Servlet中获取这个属性。

**ServletA.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletA extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置ServletContext属性
        ServletContext context = getServletContext();
        context.setAttribute("message", "Hello from ServletA");

        // 跳转到ServletB
        RequestDispatcher dispatcher = context.getRequestDispatcher("/servletB");
        dispatcher.forward(request, response);
    }
}
```



**ServletB.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletB extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取ServletContext属性
        ServletContext context = getServletContext();
        String message = (String) context.getAttribute("message");

        // 将信息输出到客户端
        PrintWriter out = response.getWriter();
        out.println("ServletContext message: " + message);
    }
}
```



在这个案例中，ServletA设置了一个ServletContext属性`message`，值为"Hello from ServletA"。然后通过RequestDispatcher跳转到ServletB。ServletB获取ServletContext属性`message`并将其输出到客户端。这样我们就实现了ServletA和ServletB之间的数据共享。

# 二、Servlet的Session作用域



Session作用域用于在一个客户端与服务器之间的会话期间存储用户特定的数据。当一个用户访问Web应用时，服务器会为该用户创建一个HttpSession对象。在多个请求之间，用户的状态信息将被保存在HttpSession对象中。这使得Servlet可以跟踪每个用户的状态信息。以下是两个Servlet案例来详细说明Session作用域：

### 案例1：

​		在一个Servlet中设置Session属性，另一个Servlet中获取这个属性。

**ServletA.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletA extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置Session属性
        HttpSession session = request.getSession();
        session.setAttribute("username", "JohnDoe");

        // 跳转到ServletB
        RequestDispatcher dispatcher = request.getRequestDispatcher("/servletB");
        dispatcher.forward(request, response);
    }
}
```



**ServletB.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletB extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Session属性
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");

        // 将信息输出到客户端
        PrintWriter out = response.getWriter();
        out.println("Session username: " + username);
    }
}
```



在这个案例中，ServletA设置了一个Session属性`username`，值为"JohnDoe"。然后通过RequestDispatcher跳转到ServletB。ServletB获取Session属性`username`并将其输出到客户端。这样我们就实现了ServletA和ServletB之间的数据共享。

### 案例2：

​	使用Session作用域实现用户登录状态的跟踪。

**LoginServlet.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class LoginServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的用户名和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // 验证用户名和密码
        if ("JohnDoe".equals(username) && "mypassword".
```

# 三、Servlet的Request作用域

Request作用域是在一个请求范围内的数据存储区，它的生命周期从请求到达服务器开始，到服务器响应请求结束。Request作用域允许在同一次请求中的多个Servlet间共享数据。以下是两个Servlet案例来详细说明Request作用域：

### 案例1：

​	在一个Servlet中设置Request属性，另一个Servlet中获取这个属性。

**ServletA.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletA extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置Request属性
        request.setAttribute("greeting", "Hello from ServletA");
        
// 跳转到ServletB
    RequestDispatcher dispatcher = request.getRequestDispatcher("/servletB");
    dispatcher.forward(request, response);
	}
}
ServletB.java
```java
import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ServletB extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Request属性
        String greeting = (String) request.getAttribute("greeting");

        // 将信息输出到客户端
        PrintWriter out = response.getWriter();
        out.println("Request greeting: " + greeting);
    }
}
```



在这个案例中，ServletA设置了一个Request属性`greeting`，值为"Hello from ServletA"。然后通过RequestDispatcher跳转到ServletB。ServletB获取Request属性`greeting`并将其输出到客户端。这样我们就实现了ServletA和ServletB之间的数据共享。

### 案例2：

​	使用Request作用域传递用户输入的数据。

**FormServlet.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class FormServlet extends HttpServlet {
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取用户输入的数据
        String name = request.getParameter("name");
        String email = request.getParameter("email");

        // 设置Request属性
        request.setAttribute("name", name);
        request.setAttribute("email", email);

        // 跳转到ResultServlet
        RequestDispatcher dispatcher = request.getRequestDispatcher("/resultServlet");
        dispatcher.forward(request, response);
    }
}
```

**ResultServlet.java**

```java

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;

public class ResultServlet extends HttpServlet {
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 获取Request属性
        String name = (String) request.getAttribute("name");
        String email = (String) request.getAttribute("email");

        // 将信息输出到客户端
        PrintWriter out = response.getWriter();
        out.println("Name: " + name);
        out.println("Email: " + email);
    }
}
```



在这个案例中，FormServlet获取了用户输入的数据（姓名和邮箱），并将这些数据设置为Request属性。然后通过RequestDispatcher跳转到ResultServlet。ResultServlet获取Request属性并将用户输入的数据输出到客户端。这样我们就实现了用户数据在两个Servlet之间的传递。



# 四、ServletContext,Session,Request三大servlet作用域的区别

ServletContext、Session和Request这三大作用域在Servlet应用中具有不同的生命周期和作用范围。以下是它们之间的主要区别： 

### 1、ServletContext（应用作用域）：

- 生命周期：从Web应用启动时创建，到Web应用终止时销毁。
- 作用范围：整个Web应用。所有Servlet共享同一个ServletContext实例，可以用于在整个Web应用中共享数据。
- 主要功能：共享数据、获取全局初始化参数、访问Web应用中的资源。 

### 2、HttpSession（会话作用域）：

- 生命周期：从用户第一次访问Web应用时创建，到用户会话结束（如超时或手动销毁）时销毁。
- 作用范围：同一用户在一个会话期间访问Web应用的所有请求。同一个用户的不同请求可以共享Session中的数据。
- 主要功能：保存用户会话状态、跟踪用户状态和行为。 
### 3、HttpServletRequest（请求作用域）：

- 生命周期：从请求到达服务器开始，到服务器响应请求结束。
- 作用范围：单次请求。在一次请求中的多个Servlet间共享Request对象的数据。
- 主要功能：传递用户输入的参数、在同一次请求的多个Servlet间共享数据。

# 总结：

- ServletContext具有最大的作用范围，它可以在整个Web应用中共享数据。适用于全局设置、配置参数和跨Servlet的数据共享。
- HttpSession的作用范围仅限于一个用户的会话期间。适用于跟踪用户状态、存储用户相关的信息，如购物车、个人偏好等。
- HttpServletRequest的作用范围最小，仅限于一次请求。适用于在同一次请求的多个Servlet间传递数据，如表单提交数据处理等。
