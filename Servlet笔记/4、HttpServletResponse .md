# HttpServletResponse的常用方法

HttpServletResponse 是 Java Servlet API 中的一个重要接口，它提供了向客户端发送响应的方法。作为一个资深的 Java 讲师，我将结合一个简单的案例，为你详细讲述 HttpServletResponse 的常用方法。

假设我们要创建一个简单的 Servlet，当用户访问时，显示“欢迎访问我们的网站！”并记录用户的 IP 地址。在这个案例中，我们将使用以下 HttpServletResponse 方法： 

### 1、setStatus(int sc)

​      设置响应的状态码。例如，HTTP 200 表示请求成功，HTTP 404 表示未找到请求的资源。 

### 2、setContentType(String type):

​      设置响应的内容类型，例如 text/html、application/json 等。浏览器根据内容类型来决定如何显示响应内容。 

### 3、setHeader(String name, String value)

​      设置响应头的值。例如，可以设置缓存控制、字符编码等。 

### 4、getWriter()

​       返回一个 PrintWriter 对象，用于将响应内容写入到客户端。

以下是一个简单的 Servlet 示例，展示了如何使用 HttpServletResponse 的常用方法：

```java

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/welcome")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置状态码
        response.setStatus(HttpServletResponse.SC_OK);

        // 设置内容类型
        response.setContentType("text/html;charset=UTF-8");

        // 获取客户端 IP 地址
        String clientIpAddress = request.getRemoteAddr();

        // 设置响应头
        response.setHeader("X-Client-IP", clientIpAddress);

        // 获取 PrintWriter 对象
        PrintWriter out = response.getWriter();

        // 输出响应内容
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>欢迎访问我们的网站！</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>欢迎访问我们的网站！</h1>");
        out.println("<p>您的 IP 地址是: " + clientIpAddress + "</p>");
        out.println("</body>");
        out.println("</html>");

        // 关闭 PrintWriter
        out.close();
    }
}
```



在这个示例中，我们使用了 HttpServletResponse 的 setStatus、setContentType、setHeader 和 getWriter 方法。通过这些方法，我们可以轻松地控制响应的状态、内容类型、响应头和响应内容。这只是 HttpServletResponse 的基本用法，更多高级功能也可以通过这个接口实现。
