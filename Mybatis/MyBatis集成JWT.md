**引入依赖**

```xml
<!--JWT-->
<dependency>
  <groupId>com.auth0</groupId>
  <artifactId>java-jwt</artifactId>
  <version>4.3.0</version>
</dependency>
```

**引入JWT工具类**

```java

public class Jwtutils {

   public static final String sale="123456";

   //把需要放入的数据放入到token
public static String jwtToken(String username) {
      Calendar instance = Calendar.getInstance();
      instance.add(Calendar.SECOND, 600);
      ///设置超时时间

Builder builder = JWT.create();

      String token = builder// head
.withClaim("username",username)
            .withExpiresAt(instance.getTime())
            .sign(Algorithm.HMAC256(sale));// 签名
//获取加密后的token
return token;
   }

   //解密token获取加密前字符串
public static String getTokenName(String token) {
      Verification verification = JWT.require(Algorithm.HMAC256(sale));// 签名
JWTVerifier jwtVerifier = verification.build();
      DecodedJWT verify;
      try {
         verify = jwtVerifier.verify(token);
         Claim username = verify.getClaim("username");
         return username.asString();
         } catch (JWTVerificationException e) {
         e.printStackTrace();
         throw e;
      }
   }

   //校验token
public static boolean verifyToken(String token) {
      // 创建验证对象
Verification verification = JWT.require(Algorithm.HMAC256(sale));// 签名
JWTVerifier jwtVerifier = verification.build();


      try {
         jwtVerifier.verify(token);
         //解密token  如果 传入的token无法解析   或者 超时   就报错
return true;
      } catch (JWTVerificationException e) {
         return false;
      }
   }

}
```

**写登录的Servlet**

```java
@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {
    EmpService es = new EmpService();
    @Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("utf-8");
        String username = req.getParameter("username");
        byte[] bytes1 = username.getBytes(StandardCharsets.ISO_8859_1);
        username = new String(bytes1,StandardCharsets.UTF_8);
        String password = req.getParameter("password");
        Integer pwd = Integer.parseInt(password);

        Emp emp = es.login(username, pwd);

        boolean flag = false;
        LoginRes lr = new LoginRes();
        if (emp == null){
            lr.setCode("0");
        }else {
            flag = true;
            lr.setCode("1");
	//调用jwt中的jwtToken方法，把需要放入的数据放入到token
	//并放入封装类中
            lr.setToken(Jwtutils.jwtToken(username));
        }

	//封装成JSON
        String rrJSON = JSON.toJSONString(lr);
        resp.setCharacterEncoding("utf-8");
        resp.setContentType("application/json");
        resp.getWriter().write(rrJSON);

        if (flag){
            Cookie cookie = new Cookie("password",password);
            cookie.setMaxAge(300);
            resp.addCookie(cookie);
            req.setAttribute("username",username);
        }
    }

    @Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req,resp);
    }
}
```

**写登录页面**

```html
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">

    function login(){
        let name = $('#username').val()
        let pwd = $('#password').val()
        $.ajax({
            url:'/mybtis-servlet/loginServlet',
            type:'GET',
            data: {
                username: name,
                password: pwd
},
            success: function (response) {
                if (response.code == "0"){
                    alert("登录失败")
                    window.location.href = "/mybtis-servlet/html/login.html"
}else {
                    alert("登录成功")
                    var token = response.token;
                    localStorage.setItem("token",token);
                    window.location.href = "/mybtis-servlet/html/ShowEmpAll.html"
}
            }
        })
    }
</script>
<body>
<center>
    <span>账号：<input name="username" id="username"></span><br>
    <span>密码：<input type="password" name="password" id="password"></span><br>
    <span><input type="button" value="登录" onclick="login()"></span>
</center>
</body>
```

**所有页面的跳转都需要携带beforeSend中的token**

```html
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>

<body>
<center>
    <input type="button" id="btn1" value="添加">
    <table id="tb1" border="1px" cellspacing="0">
        <thead>
        <tr>
            <th>员工编号</th>
            <th>员工姓名</th>
            <th>职位</th>
            <th>部门编号</th>
            <th>上司</th>
            <th>入职时间</th>
            <th>薪资</th>
            <th>奖金</th>
            <th colspan="2">操作</th>
        </tr>
        </thead>
        <tbody></tbody>
    </table>
    <input type="button" value="上一页" onclick="getPage('pre')">
    当前第<input type="text" id="currentPage" style="width: 10px;text-align: center" onchange="getPage('this')">页
    每页显示<select id="pageSize" onchange="getPage('this')" style="text-align: center"></select>页
<input type="button" value="下一页" onclick="getPage('next')">
</center>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script type="text/javascript">
    window.onload = function (){
        load()
    }

    function load(pageSize,currentPage,operator){
        var token = localStorage.getItem("token");
        $.ajax({
            url: '/mybtis-servlet/selectServlet',
            type: 'post',
            dataType: 'json',
            beforeSend:function (req){
                req.setRequestHeader("token",token);
            },
            data:{
                pageSize:pageSize,
                currentPage:currentPage,
                operator:operator
            },
            success: function (response) {
                console.log(response)
                var currentPage = response.currentPage;
                var pageSize = response.pageSize;
                $('#currentPage').val(currentPage)
                $('#pageSize').val(pageSize)
                var tr = ""
$("#tb1 tbody").empty()
                let empList = response.empList;
                for (let i = 0; i < empList.length; i++) {
                    if (empList[i].cOMM == undefined){
                        empList[i].cOMM = 0;
                    }

                    tr = "<tr>" +
                            "<td>"+empList[i].empno+"</td>" +
                            "<td>"+empList[i].ename+"</td>" +
                            "<td>"+empList[i].job+"</td>" +
                            "<td>"+empList[i].deptno+"</td>" +
                            "<td>"+empList[i].mgrName+"</td>" +
                            "<td>"+empList[i].hiredate+"</td>" +
                            "<td>"+empList[i].sal+"</td>" +
                            "<td>"+empList[i].cOMM+"</td>" +
                            "<td><input type='button' value='修改' onclick='toUpdate(\""+empList[i].empno+"\")'></td>" +
                            "<td><input type='button' value='删除' onclick='toDelete(\""+empList[i].empno+"\")'></td>" +
                        "</tr>";
                    $("#tb1").append(tr)
                    $('#pageSize option').remove()
                    for (let j = 1; j <= 10; j++) {
                        if (j==pageSize){
                            var op = "<option value='"+j+"' selected='selected'>"+j+"</option>"
$('#pageSize').append(op)
                        }else {
                            var op = "<option value='"+j+"'>"+j+"</option>"
$('#pageSize').append(op)
                        }

                    }
                }
            },
            error: function (response) {

            }
        });
    }
    document.getElementById("btn1").onclick = function (){
        window.location.href="/mybtis-servlet/html/insert.html"
}

    function toUpdate(empno){
        window.location.href = '/mybtis-servlet/html/update.html?empno='+empno
    }

    function toDelete(empno){
        $.ajax({
            url: '/mybtis-servlet/deleteOneServlet?empno='+empno,
            type: 'post',
            dataType: 'json',
            success: function (response) {
                if (response.code == '0') {
                    alert(response.message)
                } else {
                    alert(response.message)
                    load()
                }
            },
            error: function (response) {

            }
        });
    }

    function getPage(operator){
        var currentPage = $('#currentPage').val();
        var pageSize = $('#pageSize').val();
        load(pageSize,currentPage,operator)
    }

</script>
</body>
</html>
```

**在过滤器中接收token并进行判断**

```java
@WebFilter("/*")
public class LoginFilter implements Filter {
    @Override
public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("loginFilter初始化");
    }

    @Override
public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        httpServletRequest.setCharacterEncoding("UTF-8");
        httpServletResponse.setCharacterEncoding("UTF-8");

        String token = httpServletRequest.getHeader("token");

        PrintWriter writer = httpServletResponse.getWriter();
        String[] urls = {
                "/login.jsp",
                "/selectServlet",
                "/loginServlet",
                "/html/login.html",
                "/html/ShowEmpAll.html",
                "/html/insert.html",
                "/login.html",
                "/customerController"
};

        // 获取资源路径
String uri = httpServletRequest.getRequestURI();
        if (uri.endsWith(".html")){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        }

        // 获取项目/应用名
String contextPath = httpServletRequest.getContextPath();
        for (String url : urls) {
            if ((contextPath + url).equals(uri)){
                chain.doFilter(httpServletRequest,httpServletResponse);
                return;
            }
        }

        if (token == null || "".equals(token)){
            writer.write("0");
            writer.flush();
            writer.close();
            return;
        }

        if (Jwtutils.verifyToken(token)){
            chain.doFilter(httpServletRequest,httpServletResponse);
            return;
        } else {
            writer.write("0");
            return;
        }

    }

    @Override
public void destroy() {
        System.out.println("loginFilter销毁");
    }
}
```
