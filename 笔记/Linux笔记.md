## 一、环境搭建

### jdk

    1.1下载Linux版本的JDK（注意看自己安装的Linux系统是什么位数）

查看本机位数命令：sudo uname --m

 JDK官网下载地址：https://www.oracle.com/java/technologies/downloads

    1.2使用工具远程进入Linux系统，查看Linux是否有安装JDK

以root用户登录输入命令：Java-version

    1.3卸载安装的JDK(有JDK的情况下先卸载)

1)查看命令：rpm -qa | grep jdk

2)卸载命令：rpm -e --nodeps xxx（xxx代表删除的文件全名）

    1.4在usr目录下新建Java目录，然后将下载的JDK拷贝到这个新建的Java目录中创建目录命令：mkdir /usr/java

 xftp工具可以直接拖动JDK文件到Java中

    1.5进入到Java目录中解压下载的JDK

解压命令：tar -zxvf jdk-18_linux-x64_bin.tar.gz

 给解压后的文件夹重新命名，方便后续设置环境变量(也可以不用重命名，先复制解压后的JDK名称)

    1.6设置环境变量

设置命令：vim /etc/profile

输入上面的命令后，shift+g快速将光标定位到最后一行，然后按“i”，再输入下面代码

JAVA_HOME=/usr/java/jdk1.8.0_171

CLASSPATH=$JAVA_HOME/lib

PATH=$PATH:$JAVA_HOME/bin

export PHTH JAVA_HOME CLASSPATH

填写完代码后按ESC键，输入“:wq”保存并退出编辑页面

    1.7输入下面命令让设置的环境变量生效

生效命令：source /etc/profile

    1.8验证JDK是否安装成功

验证命令：Java -version

### tomcat安装

tomcat的默认路径，usr/share/

存放共享文件

安装步骤：

1.在官网下载

https://tomcat.apache.org/download-90.cgi

版本：9

2.下载好后(扩展名为tar.gz)，在xshell上输入“rz”打开外部文件，或者拖入文件

3.解压 tar -zxvf tomcat的压缩文件名

4.可以进入解压之后的tomcat文件的启动日志

vim usr/share/tomcat9(自己新建)/tomcat解压之后的文件名/conf/server.xml

5.查看8080端口是否在运行

netstat -anp | grep 8080

6.查看指定端口是否被占用

netstat  -anp | grep 8080

find命令：

find /etc -name nginx.conf

查找/etc下的以nginx.conf为文件名的文件

通用写法

find /etc -name  "nginx.*"

### 安装nginx

#### 这里先添加一下Nginx安装包的源，不然可能会下载不到

sudo rpm -ivh http://nginx.org/packages/centos/7/noarch/RPMS/nginx-release-centos-7-0.el7.ngx.noarch.rpm

#### 安装nginx

yum install nginx -y

#### 启动nginx

systemctl start nginx

#### 设置开机自启动

systemctl enable nginx

#### 可以通过以下命令查看是否设置成功

systemctl list-unit-files | grep nginx
或者
systemctl status nginx

安装nginx的网站

https://access.redhat.com/documentation/zh-cn/red_hat_enterprise_linux/8/html/deploying_different_types_of_servers/setting-up-and-configuring-nginx_deploying-different-types-of-servers

二、权限管理

添加用户以及添加用户的所属组

useradd -m 用户名  ------------加入"-m" 可以在系统不允许创建用户的情况下创建用户

1.groupadd student 添加组

2.cat /etc/passwd  查看所有的用户

3.cat /etc/group  查看所有的用户组

4.useradd -m -g student stuNginx  添加用户

groups stuNginx

5.修改用户密码 passwd stuNginx

6.用新创建的用户stuNginx登录

7.如果新用户不能正常使用命令

    则需要执行命令dpkg-reconfigure bash

    进入之后修改为"no"


权限

8.给子用户添加sudo权限，暂时使用root权限，需要修改/etc/sudoers文件

    请先切换到超级用户（root），将`/etc/sudoers` 修改为可编辑。

    chmod 777 /etc/sudoers

    对此文件进行修改后，将`/etc/sudoers` 修改回只读模式。

    chmod 440 /etc/sudoers

**
    文件里的权限配置如何修改：**

    root ALL=(ALL:ALL) ALL
	关于此权限设置的说明：

    root 表示 此项规则应用于root用户

    从左到右

    第一个ALL 表示这项规则应用于所有主机

    第二个ALL 表示root（用户）可以像所有用户一样运行命令，可以切换到所有用户

    第三个ALL 表示root（用户）可以像所有组一样运行命令

    第四个ALL 表示这项规则应用与所有命令。
	所以这条权限设置的语句表示root用户可以使用sudo执行任何命令。

#### 允许普通用户使用 sudo 命令

修改 /etc/sudoers

增加一行

greatdb ALL=(ALL)      NOPASSWD: ALL

NOPASSWD: ALL 表示不输入密码也能执行所有命令。


修改用户权限的命令：

chomd

修改文件的权限为766

chmod 766 /etc/nginx/conf.d/huidiao.conf


将权限转让给其他用户

chmod -R student:student /var/log/nginx

加上-R表示递归调用，这个目录以及这个目录下的所有文件都转让权限

如果是root用户转让的权限，root可以随时收回

三、项目部署

* 上传
* 启动
* 查看端口
* 查看日志
