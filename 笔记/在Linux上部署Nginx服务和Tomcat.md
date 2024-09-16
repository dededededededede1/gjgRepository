前端访问后端路径：http://m.jnbat.com/api/后端接口

前端打包：npm run build   ->dist文件，将他压缩，改个名，

后端打包：回调、数据库、redis

数据库：jdbc.driverClassName=com.mysql.cj.jdbc.Driver
jdbc.url=jdbc:mysql://47.94.220.67:3306/forTest?useUnicode=true;characterEncoding=utf8;serverTimezone=Asia/Shanghai;useTimezone=true
jdbc.username=student
jdbc.password=stu101600

回调：{
后端回调：http://m.jnbat.com/api/后端接口
前端回调：http://m.jnbat.com/前端path
}

redis 最大连接数 改成10以下、ip:127.0.0.1 port:6379

后端打包：clean install

前端.zip 后端.war

前端.zip解压，命令： unzip 文件名 ，将这个文件夹：sudo mv ./前端文件名 /usr/share/nginx
后端.war，
1、关闭tomcat：sudo -E /root/tomcat/apache-tomcat-9.0.58/bin/shutdown.sh
2、sudo rm -rf /root/tomcat/apache-tomcat-9.0.58/webapps/xjbshop.war     sudo rm -rf /root/tomcat/apache-tomcat-9.0.58/webapps/xjbshop
3、sudo mv ./后端.war /root/tomcat/apache-tomcat-9.0.58/webapps
4、启动tomcat：sudo -E /root/tomcat/apache-tomcat-9.0.58/bin/startup.sh

访问的话需要配置nginx
sudo vi /etc/nginx/conf.d/huidiao.conf
8080/后端项目名
/nginx/前端包名
重新加载nginx：sudo systemctl reload nginx

查看日志

sudo tail -n 200 /root/tomcat/apache-tomcat-9.0.58/logs/catalina.out

动态日志

sudo tail 200f /root/tomcat/apache-tomcat-9.0.58/logs/catalina.out
