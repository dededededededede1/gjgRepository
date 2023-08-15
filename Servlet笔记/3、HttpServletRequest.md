# HttpServletRequest的常用方法

HttpServletRequest 是一个接口，它继承自 ServletRequest 接口，用于处理 HTTP 请求。在 Servlet 中，当客户端发出 HTTP 请求时，容器会创建一个 HttpServletRequest 对象并将其传递给 Servlet 的 service 方法。下面是一些 HttpServletRequest 常用方法的概述和示例： 

### 1、`String getMethod()`

这个方法返回请求的 HTTP 方法（例如 "GET"、"POST" 等）。例如，如果您想在 Servlet 中根据请求的方法执行不同的操作，您可以使用此方法。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String method = request.getMethod(); // 获取请求的 HTTP 方法
    if ("GET".equals(method)) {
        // 处理 GET 请求
    } else if ("POST".equals(method)) {
        // 处理 POST 请求
    }
}
```

### 2、`String getRequestURI()`

这个方法返回请求的 URI，不包括查询字符串。例如，如果请求的 URL 是 `http://example.com/app/login?user=admin`，则 getRequestURI() 返回 `/app/login`。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String uri = request.getRequestURI();
    System.out.println("请求的 URI: " + uri);
}
```

### 3、`String getQueryString()`

此方法返回请求的查询字符串。例如，如果请求的 URL 是 `http://example.com/app/login?user=admin`，则 getQueryString() 返回 `user=admin`。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String queryString = request.getQueryString();
    System.out.println("请求的查询字符串: " + queryString);
}
```

### 4、`String getParameter(String name)`

这个方法用于获取请求参数的值。例如，如果请求的 URL 是 `http://example.com/app/login?user=admin`，则 `request.getParameter("user")` 返回 `admin`。

```java

public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    // 验证用户名和密码的逻辑
}
```

### 5、`String getHeader(String name)`

这个方法用于获取 HTTP 请求头中的特定字段值。例如，获取 User-Agent 请求头的值：

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String userAgent = request.getHeader("User-Agent");
    System.out.println("客户端 User-Agent: " + userAgent);
}
```

### 6、`HttpSession getSession()`

这个方法用于获取与此请求关联的 HttpSession 对象。可以用来在多个请求之间存储和共享数据。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    HttpSession session = request.getSession();
    session.setAttribute("user", "admin");
    String user = (String) session.getAttribute("user");
    System.out.println("会话中的用户:" + user);
} 
```

### 7、`String getContextPath()`

这个方法返回请求的上下文路径。例如，如果请求的 URL 是 `http://example.com/app/login`，则 getContextPath() 返回 `/app`。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String contextPath = request.getContextPath();
    System.out.println("上下文路径: " + contextPath);
}
```

### 8、`boolean isSecure()`

这个方法用于检查请求是否是安全的，即请求是否通过 SSL（HTTPS）进行。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    boolean isSecure = request.isSecure();
    System.out.println("请求是否安全: " + isSecure);
}
```

### 9、`Cookie[] getCookies()`

此方法返回与此请求关联的所有 Cookie 对象数组。可以用于获取和处理客户端发送的 Cookie 信息。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    Cookie[] cookies = request.getCookies();
    for (Cookie cookie : cookies) {
        System.out.println("Cookie 名称: " + cookie.getName() + ", 值: " + cookie.getValue());
    }
}
```

### 10、`String getRemoteAddr()`

此方法返回发出请求的客户端的 IP 地址。

```java

public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String remoteAddr = request.getRemoteAddr();
    System.out.println("客户端 IP 地址: " + remoteAddr);
}
```



这些是 HttpServletRequest 中的一些常用方法。实际上，根据您的需求，可能还需要使用其他方法。在开发 Web 应用程序时，请参考 Java Servlet API 文档以获取有关 HttpServletRequest 的更多信息。
