# Servlet的创建方式



Servlet 是 Java Web 应用程序的核心组件，它负责处理来自客户端的请求，并生成响应。在 Java 中，Servlet 通常遵循 Java Servlet API 规范。创建 Servlet 的主要方式有两种：实现 Servlet 接口和继承 HttpServlet 类。

## 1、实现 Servlet 接口

要创建一个 Servlet，您可以实现 `javax.servlet.Servlet` 接口。这个接口包含五个方法，您需要为这五个方法提供具体实现。

以下是一个简单的案例，展示了如何通过实现 Servlet 接口来创建一个 Servlet：

```java

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyServlet implements Servlet {
    public void init(ServletConfig config) throws ServletException {
        // Servlet 初始化代码
    }

    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is MyServlet using Servlet Interface</h1>");
        out.println("</body></html>");
    }

    public void destroy() {
        // Servlet 销毁代码
    }

    public ServletConfig getServletConfig() {
        return null;
    }

    public String getServletInfo() {
        return "MyServlet using Servlet Interface";
    }
}
```

## 2、继承 HttpServlet 类

由于直接实现 Servlet 接口可能导致大量的样板代码，因此在实践中，我们通常通过继承 `javax.servlet.http.HttpServlet` 类来创建 Servlet。HttpServlet 类提供了一些默认实现，使得创建 Servlet 更加简单。

以下是一个简单的案例，展示了如何通过继承 HttpServlet 类来创建一个 Servlet：

```java

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyHttpServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is MyHttpServlet using HttpServlet Class</h1>");
        out.println("</body></html>");
    }
}
```



在这个例子中，我们只需要覆盖 `doGet` 方法即可。`doGet` 方法用于处理 HTTP GET 请求。类似地，您还可以覆盖其他 HTTP 请求处理方法，例如 `doPost`、`doPut` 和 `doDelete` 等。这些方法分别对应 HTTP POST、PUT 和 DELETE 请求。



## 3、多个请求处理方法的 HttpServlet 示例：

```java

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class MyExtendedHttpServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is a GET request in MyExtendedHttpServlet</h1>");
        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is a POST request in MyExtendedHttpServlet</h1>");
        out.println("</body></html>");
    }

    protected void doPut(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is a PUT request in MyExtendedHttpServlet</h1>");
        out.println("</body></html>");
    }

    protected void doDelete(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        res.setContentType("text/html");
        PrintWriter out = res.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello, this is a DELETE request in MyExtendedHttpServlet</h1>");
        out.println("</body></html>");
    }
}
```



要使 Servlet 生效，您还需要在应用程序的 `web.xml` 文件中进行配置。以下是一个简单的 `web.xml` 示例：

```xml

<?xml version="1.0" encoding="UTF-8"?>
<web-app xmlns="http://xmlns.jcp.org/xml/ns/javaee"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://xmlns.jcp.org/xml/ns/javaee
         http://xmlns.jcp.org/xml/ns/javaee/web-app_3_1.xsd"
         version="3.1">
    <servlet>
        <servlet-name>MyExtendedHttpServlet</servlet-name>
        <servlet-class>MyExtendedHttpServlet</servlet-class>
    </servlet>
    <servlet-mapping>
        <servlet-name>MyExtendedHttpServlet</servlet-name>
        <url-pattern>/my-extended-servlet</url-pattern>
    </servlet-mapping>
</web-app>
```



在这个例子中，我们将 MyExtendedHttpServlet 类映射到了 `/my-extended-servlet` 路径。当用户通过浏览器访问这个路径时，相应的请求处理方法（例如 `doGet`, `doPost`, `doPut` 或 `doDelete`）将被调用。这取决于用户发起的 HTTP 请求类型。以下是一个简单的说明，描述了如何使用浏览器和命令行工具（如 curl）与 MyExtendedHttpServlet 进行交互： 

### （1）GET 请求：

使用浏览器访问 `http://localhost:8080/your-webapp-context/my-extended-servlet`。浏览器将发送一个 GET 请求，触发 `doGet` 方法。

或者，您可以在命令行中使用 curl 工具发送 GET 请求：

```bash
curl -X GET http://localhost:8080/your-webapp-context/my-extended-servlet
```
### （2）POST 请求：

您可以使用表单或者命令行工具发送 POST 请求。使用 curl 发送 POST 请求的示例：

```bash
curl -X POST http://localhost:8080/your-webapp-context/my-extended-servlet
```



此时，将触发 `doPost` 方法。 

### （3）PUT 请求：

使用命令行工具发送 PUT 请求。以下是使用 curl 发送 PUT 请求的示例：

```bash
curl -X PUT http://localhost:8080/your-webapp-context/my-extended-servlet
```



此时，将触发 `doPut` 方法。 

### （4）DELETE 请求：

使用命令行工具发送 DELETE 请求。以下是使用 curl 发送 DELETE 请求的示例：

```bash
curl -X DELETE http://localhost:8080/your-webapp-context/my-extended-servlet
```



此时，将触发 `doDelete` 方法。

通过这个示例，您可以看到如何使用 HttpServlet 类轻松地创建 Servlet，并处理不同类型的 HTTP 请求。在实际应用中，Servlet 通常与其他 Java Web 技术（如 JSP, JavaServer Faces, Spring MVC 等）结合使用，以实现更丰富的 Web 应用程序功能。
