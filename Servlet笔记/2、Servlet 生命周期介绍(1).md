# servlet的生命周期

Servlet 生命周期指的是 Servlet 对象从创建到销毁的整个过程。在这个过程中，Servlet 容器会自动调用 Servlet 对象的一系列方法，以完成对 Servlet 对象的初始化、处理请求和销毁等工作。

下面，我将结合一个简单的案例来讲解 Servlet 生命周期的各个阶段。

假设我们有一个名为 HelloServlet 的 Servlet 类，该类的 doGet() 方法用于处理 GET 请求，返回一个字符串 "Hello World!"。

## 1、Servlet 生命周期的第一阶段

Servlet 容器会创建一个 ServletConfig 对象，并调用 Servlet 的 init() 方法来完成对 Servlet 的初始化工作。在 HelloServlet 类中，init() 方法的实现可能如下所示：

```java

public class HelloServlet extends HttpServlet {
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        System.out.println("HelloServlet 初始化...");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>Hello World!</h1>");
        out.println("</body></html>");
    }
}
```



在上面的代码中，我们重写了 HttpServlet 类的 init() 方法，并在方法中调用了父类的 init() 方法以完成基本的初始化工作。同时，我们在 init() 方法中输出了一条日志，以便于我们后续观察 Servlet 生命周期中的各个阶段。

## 2、Servlet 生命周期的第二阶段

Servlet 容器会调用 Servlet 对象的 service() 方法来处理请求。在我们的案例中，当收到一个 GET 请求时，容器会调用 HelloServlet 对象的 doGet() 方法来生成响应。HelloServlet 类中的 doGet() 方法会将 "Hello World!" 字符串返回给客户端。

## 3、Servlet 生命周期的第三阶段

Servlet 容器会调用 Servlet 对象的 destroy() 方法，以销毁 Servlet 对象并释放相关资源。在 HelloServlet 类中，我们可以实现 destroy() 方法如下：

```java

public void destroy() {
    System.out.println("HelloServlet 销毁...");
    super.destroy();
}
```



在上面的代码中，我们重写了 HttpServlet 类的 destroy() 方法，并在方法中输出了一条日志以便于观察 Servlet 生命周期中的各个阶段。

## 4、总结

​	Servlet 生命周期包括三个阶段：初始化、请求处理和销毁。在初始化阶段，Servlet 容器会调用 Servlet 的 init() 方法来完成初始化工作；在请求处理阶段，Servlet 容器会调用 Servlet 对象的 service() 方法来处理请求；在销毁阶段，Servlet 容器会调用 Servlet 对象的 destroy() 方法来销毁对象并释放相关资源。通过对 Servlet 生命周期的了解，我们可以更好地理解 Servlet 的工作原理，从而更好地编写高效稳定的 Servlet 应用程序。
