## 安装Redis步骤

> [https://github.com/microsoftarchive/redis/releases/tag/win-3.2.100]()

1.进入github中，点击[Redis-x64-3.2.100.msi](https://github.com/microsoftarchive/redis/releases/download/win-3.2.100/Redis-x64-3.2.100.msi)

这是图形化安装

2.下载完之后安装

一路点击next，其中更改文件路径的部分需要修改，端口号默认

3.进入安装目录，点击redis-cli.exe进入dos界面

出现127.0.0.1:6379的dos界面就算安装成功了

4.如果要将redis作为一个服务器，则需要修改 redis.windows-service.conf

如果绑定了远程地址，则需要修改

bind 127.0.0.1

修改端口号：

port 6379

修改持久化时数据写入的文件

dbfilename dump.rdb

持久化文件存放目录

dir  ./

默认情况下创建多少个数据库

databases 16

保存日志文件的文件名称

logfile "server_log.txt"


可视化工具在

D:\redis\resp-2022.5
