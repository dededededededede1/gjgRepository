基础工具类

```
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HttpClientUtil {

    static Logger log = LoggerFactory.getLogger(HttpClientUtil.class);

    public static String doGet(String url, Map<String, String> param) {

        // 创建Httpclient对象
        CloseableHttpClient httpclient = HttpClients.createDefault();

        String resultString = "";
        CloseableHttpResponse response = null;
        try {
            // 创建uri
            URIBuilder builder = new URIBuilder(url);

            if (param != null) {
                for (String key : param.keySet()) {
                    builder.addParameter(key, param.get(key));
                }
            }
            URI uri = builder.build();

            // 创建http GET请求
            HttpGet httpGet = new HttpGet(uri);

            // 执行请求
            response = httpclient.execute(httpGet);
            // 判断返回状态是否为200
            if (response.getStatusLine().getStatusCode() == 200) {
                resultString = EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {

            log.error("Exception", e);
        } finally {
            try {
                if (response != null) {
                    response.close();
                }
                httpclient.close();
            } catch (IOException e) {
                log.error("IOException", e);
            }
        }
        return resultString;
    }

    public static String doGet(String url) {
        return doGet(url, null);
    }

    public static String doPost(String url, Map<String, String> param) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建参数列表
            if (param != null) {
                List<NameValuePair> paramList = new ArrayList<>();
                for (String key : param.keySet()) {
                    paramList.add(new BasicNameValuePair(key, param.get(key)));
                }
                // 模拟表单
                UrlEncodedFormEntity entity = new UrlEncodedFormEntity(paramList);
                httpPost.setEntity(entity);
            }
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("IOException", e);
            }
        }

        return resultString;
    }

    public static String postFileMultiPart(String url, Map<String, String> reqParam) {

        CloseableHttpClient httpclient = HttpClients.createDefault();

        try {

            HttpPost httppost = new HttpPost(url);

            //setConnectTimeout：设置连接超时时间，单位毫秒。setConnectionRequestTimeout：设置从connect Manager获取Connection 超时时间，单位毫秒。这个属性是新加的属性，因为目前版本是可以共享连接池的。setSocketTimeout：请求获取数据的超时时间，单位毫秒。 如果访问一个接口，多少时间内无法返回数据，就直接放弃此次调用。
            RequestConfig defaultRequestConfig = RequestConfig.custom().setConnectTimeout(5000).setConnectionRequestTimeout(5000).setSocketTimeout(15000).build();
            httppost.setConfig(defaultRequestConfig);

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            for (Map.Entry<String, String> param : reqParam.entrySet()) {
                ContentBody contentBody = new StringBody(param.getValue(), ContentType.MULTIPART_FORM_DATA);
                multipartEntityBuilder.addPart(param.getKey(), contentBody);
            }
            HttpEntity reqEntity = multipartEntityBuilder.build();
            httppost.setEntity(reqEntity);

            // 执行post请求.
            CloseableHttpResponse response = httpclient.execute(httppost);

            try {
                // 获取响应实体
                HttpEntity entity = response.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, Charset.forName("UTF-8"));
                }
            } finally {
                response.close();

            }
        } catch (Exception e) {

        } finally {
            // 关闭连接,释放资源
            try {
                httpclient.close();
            } catch (IOException e) {
                log.error("IOException", e);
            }
        }
        return null;
    }

    public static String doPost(String url) {
        return doPost(url, null);
    }

    public static String doPostJson(String url, String json) {
        // 创建Httpclient对象
        CloseableHttpClient httpClient = HttpClients.createDefault();
        CloseableHttpResponse response = null;
        String resultString = "";
        try {
            // 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
            // 执行http请求
            response = httpClient.execute(httpPost);
            resultString = EntityUtils.toString(response.getEntity(), "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                response.close();
            } catch (IOException e) {
                log.error("IOException", e);
            }
        }

        return resultString;
    }
}
```

httpclient各个代码的作用

CloseableHttpClient

```
//可关闭的HttpClient客户端，这就是一个打开的浏览器
CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
```

HttpGet

```
String urlStr = "https://www.baidu.com";

// 发送Get请求
HttpGet httpGet = new HttpGet(urlStr);
```

CloseableHttpResponse

```
// 可以关闭的http响应
CloseableHttpResponse response = null;
// 根据执行的请求方式，接收响应
response = closeableHttpClient.execute(httpGet);
```

HttpEntity

```
// 获取响应的实体
HttpEntity entity = response.getEntity();
```

EntityUtils工具类

```
// HttpClient用来处理HttpEntity的工具类
String s = EntityUtils.toString(entity, StandardCharsets.UTF_8);
System.out.println(s);
// 确保处理HttpEntity这个流关闭
EntityUtils.consume(entity);
```

httpGet.addHeader，设置请求头

加一个用户代理的认证

```
// 解决httpclient被认为不是真人的行为
httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/113.0.0.0 Safari/537.36");
```

防盗链

```
// 防盗链，Refer后面的网址，必须是这个网站的网址,也就是你请求的网址
httpGet.addHeader("Refer","https://www.baidu.com");
```

对于密码加密之后，httpClient不能识别的特殊字符，做编码格式的转换

```
String password = "123+abd 456|..";
String passwordEncode = URLEncoder.encode(password, StandardCharsets.UTF_8.name());
```

响应状态码

```
    int SC_CONTINUE = 100;
    int SC_SWITCHING_PROTOCOLS = 101;
    int SC_PROCESSING = 102;
    int SC_OK = 200;
    int SC_CREATED = 201;
    int SC_ACCEPTED = 202;
    int SC_NON_AUTHORITATIVE_INFORMATION = 203;
    int SC_NO_CONTENT = 204;
    int SC_RESET_CONTENT = 205;
    int SC_PARTIAL_CONTENT = 206;
    int SC_MULTI_STATUS = 207;
    int SC_MULTIPLE_CHOICES = 300;
    int SC_MOVED_PERMANENTLY = 301;
    int SC_MOVED_TEMPORARILY = 302;
    int SC_SEE_OTHER = 303;
    int SC_NOT_MODIFIED = 304;
    int SC_USE_PROXY = 305;
    int SC_TEMPORARY_REDIRECT = 307;
    int SC_BAD_REQUEST = 400;
    int SC_UNAUTHORIZED = 401;
    int SC_PAYMENT_REQUIRED = 402;
    int SC_FORBIDDEN = 403;
    int SC_NOT_FOUND = 404;
    int SC_METHOD_NOT_ALLOWED = 405;
    int SC_NOT_ACCEPTABLE = 406;
    int SC_PROXY_AUTHENTICATION_REQUIRED = 407;
    int SC_REQUEST_TIMEOUT = 408;
    int SC_CONFLICT = 409;
    int SC_GONE = 410;
    int SC_LENGTH_REQUIRED = 411;
    int SC_PRECONDITION_FAILED = 412;
    int SC_REQUEST_TOO_LONG = 413;
    int SC_REQUEST_URI_TOO_LONG = 414;
    int SC_UNSUPPORTED_MEDIA_TYPE = 415;
    int SC_REQUESTED_RANGE_NOT_SATISFIABLE = 416;
    int SC_EXPECTATION_FAILED = 417;
    int SC_INSUFFICIENT_SPACE_ON_RESOURCE = 419;
    int SC_METHOD_FAILURE = 420;
    int SC_UNPROCESSABLE_ENTITY = 422;
    int SC_LOCKED = 423;
    int SC_FAILED_DEPENDENCY = 424;
    int SC_TOO_MANY_REQUESTS = 429;
    int SC_INTERNAL_SERVER_ERROR = 500;
    int SC_NOT_IMPLEMENTED = 501;
    int SC_BAD_GATEWAY = 502;
    int SC_SERVICE_UNAVAILABLE = 503;
    int SC_GATEWAY_TIMEOUT = 504;
    int SC_HTTP_VERSION_NOT_SUPPORTED = 505;
    int SC_INSUFFICIENT_STORAGE = 507;
```

获取响应状态码

response.getStatusLine()

```
// 获取响应的状态码
StatusLine statusCode = response.getStatusLine();
//判断是否响应成功200
if (HttpStatus.SC_OK == statusCode.getStatusCode()) {
    HttpEntity entity = response.getEntity();

    String toStringEntity = EntityUtils.toString(entity);
    System.out.println(toStringEntity);
    EntityUtils.consume(entity);
}else {
    System.out.println("响应失败");
}
```

获取响应头response.getAllHeaders()

```
// 1.获取所有响应头,利用response获取
Header[] allHeaders = response.getAllHeaders();
for (Header header : allHeaders) {
    System.out.println("响应头"+header.getName()+" 的值："+header.getValue());
}


HttpEntity entity = response.getEntity();
// 2.获取到HttpEntity之后，再去获取Content-Type
System.out.println("Content-Type"+entity.getContentType());
```

.setConnectTimeout(5000)    设置请求超时时间，请求一个url的时间

```
String IP = "127.0.0.1";
int port = 8080;
HttpHost httpHost = new HttpHost(IP,port);
RequestConfig requestConfig = RequestConfig.custom()
// 设置代理
//.setProxy(httpHost)
// 设置连接超时时间，单位是ms，完成tcp3次握手的时限
.setConnectTimeout(5000)
.build();
```

.setSocketTimeout(3000)    设置读取超时时间

```java
RequestConfig requestConfig = RequestConfig.custom()
        // 设置代理
//.setProxy(httpHost)
        // 设置连接超时时间，单位是ms，完成tcp3次握手的时限
.setConnectTimeout(5000)
        // 设置读取超时时间
.setSocketTimeout(3000)
        .build();
```

Post请求方式

```
CloseableHttpClient httpClient = HttpClients.createDefault();
        String url = "https://www.baidu.com";
        HttpPost httpPost = new HttpPost(url);

        CloseableHttpResponse response = null;

        List<NameValuePair> param = new ArrayList<>();
        param.add(new BasicNameValuePair("姓名","zhangsan"));
        param.add(new BasicNameValuePair("年龄","23"));
    
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(param);
        httpPost.setEntity(urlEncodedFormEntity);
```

模拟表单提交数据 使用UrlEncodedFormEntity这个对象

需要的参数是一个List集合，泛型指定为NameValuePair

发送给json类型的post请求

```
// 创建Http Post请求
            HttpPost httpPost = new HttpPost(url);
            // 创建请求内容,发送json数据
            StringEntity entity = new StringEntity(json, ContentType.APPLICATION_JSON);
            httpPost.setEntity(entity);
```

这里的json参数是json字符串

PostJson

```
Map<String,Object> map = new HashMap<>();
        map.put("姓名","张三1");
        map.put("年龄",23);

        JSONObject jsonObj = new JSONObject();
        String s = jsonObj.toJSONString(map);
        //System.out.println(s);

        StringEntity stringEntity = new StringEntity(s, sontentType.APPLICATION_JSON);
        stringEntity.setContentType(new BasicHeader("Content-Type","application/json"));
        stringEntity.setContentEncoding(Consts.UTF_8.name());
        httpPost.setEntity(stringEntity);
```
