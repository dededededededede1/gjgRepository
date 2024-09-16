1.将不需要的东西删干净

2.pom.xml默认是jar包

3.点击maven-->找到项目-->Liftcycle-->package就可以打包好了

4.此时maven仓库中对应，你新建项目时的group_id的文件夹里就有了一个jar包

5.新建项目，在pom文件里引入依赖就行了，group_id对映maven仓库中相关jar包的文件名

或者引用全路径
