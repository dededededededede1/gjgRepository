1.在地址栏输入node.js

2.选择需要的版本下载，不要有中文路径

3.在D:\js\node-v14.10.0-win-x64中新建两个文件夹

node_cache

node_global

4.配置环境变量

    4.1在系统变量中新建NODE_HOME-->D:\js\node-v14.10.0-win-x64-->在path中%NODE_HOME%-->%NODE_HOME%\node_cache-->%NODE_HOME%\node_global

    4.2点击确定

5.cmd-->node -v --> node -v 这是node的版本号 --> npm -v 这是npm的版本

6.配置全局安装路径

npm config set prefix "D:\js\node-v14.10.0-win-x64\node_global"

7.配置cache

npm config set cache "D:\js\node-v14.10.0-win-x64\node_cache"

8.配置下载资源的镜像

npm config set registry http://registry.npm.taobao.org

9.查看配置是否生效

npm config ls

10.测试是否生效

npm install -g cnpm
