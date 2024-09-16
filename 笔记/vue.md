# 安装vue脚手架

1.https://cli.vuejs.org/zh/

在全局安装cli之前需要换taobao的镜像

npm config set registry http://registry.npm.taobao.org

2.全局安装cli

npm install -g @vue/cli

3.create vue 项目名

一开始要选择最后一个手动安装

把router与vuex按空格键选中，按回车

碰到第一个选择(y/n)的选择y

第二个选择n

安装成功后可以选择idea打开，也可以用vscode

创建vue程序

1.第一步，创建一个起始页面

```vue
<template>
  <div class="home">
    <h1>{{username}}</h1>
    名称：<input type="text" v-model="username" v-if="flag"><br/>
    <button @click="show">点一下</button>
    <button @click="jump">再点一下</button>
    <table>
      <thead>
      <th>编号</th>
      <th>名称</th>
      <th>下标</th>
      </thead>
      <tbody>
      <tr v-for="(user,index) in userList" :key="index" >
        <td>
          {{user.userno}}
        </td>
        <td>
          {{user.username}}
        </td>
        <td>
          {{index}}
        </td>
      </tr>
      </tbody>
    </table>
  </div>
</template>

<script>

export default {
  name: 'HomeView',
  components: {

  },
  data(){
    return{
      username:"zhangsan",
      flag:false,
      userList:
          [{
            userno:"RES0001",
            username:"莱伊拉"
          },
          {
            userno: "RES0002",
            username: "纳西妲"
          }]
    }
  },
  methods:{
    show:function(){
      if (this.flag == false){
        this.flag = true
      }else {
        this.flag = false
      }
    },
    jump:function (){
      //this.$router.push({name:"success",params:{username:this.username}})
      this.$router.push({path:"/success",query:{username:this.username}})
    }
  }
}

```

2.第二步，function都放在methods里面，利用@click创建点击事件

3.实现页面跳转

需要在router文件夹的index.js中配置routes路由，比如要跳转success.vue页面

需要如下设置

```
{
  path: '/success',
  name: 'success',
  // route level code-splitting
  // this generates a separate chunk (about.[hash].js) for this route
  // which is lazy-loaded when the route is visited.
component: () => import(/* webpackChunkName: "about" */ '../views/success.vue')
}
```

将path、name、以及component中的文件名改成与跳转的页面相同

4.传递参数，两种方式

4.1 利用路由中的name；push里面的参数是name（router文件夹下的routes中找）、params

4.2 利用路由中的path；push里面的参数是path（router文件夹下的routes中找）、query

```
jump:function (){
  	//this.$router.push({name:"success",params:{username:this.username}})
        this.$router.push({path:"/success",query:{username:this.username}})
}
```



# --------------------------------------------------------------

## 一. Vue基础API

### 1.1 插值表达式（声明式渲染 / 文本插值）

* 作用：
  * 把vue变量直接显示在标签内
* 表达式：可以被求值，凡是有结果的操作
  * 字面量
  * 对象.属性名
  * 算术运算 或 三元表达式
  * 方法的调用
* **语法：** `<标签> {{ 表达式 }} </标签>`
* 注意：
  * 单标签不能使用；
  * vue数据变量要在 `<script>` 中 **data函数** 中 **声明**
  * 双大括号之间只能写表达式（上面的四种）
* 示例展示：
  ```js
  <template>
  <!-- 插值表达式：将表达式的值直接显示在标签内 -->
  <!-- 表达式：可以被求值，凡是有结果的操作 -->
  <!-- 
      表达式：
          字面量
          对象.属性名
          算数运算 或 三元表达式
          函数调用
  -->
  <!-- 插值表达式：{{ 表达式 }} -->
  <!-- 
      使用步骤：
          1.声明数据 -> 在data方法返回的对象中声明
          2.书写插值表达式
  -->
      <div>
          <!-- 数据属性名 -->
          <h1>{{ msg }}</h1>
          <!-- 对象.属性名 -->
          <p>我叫{{ obj.name }}，今年{{ obj.age }}岁了</p>
          <!-- 算术运算 -->
          <p>明年我就{{ obj.age + 1 }}岁了</p>
          <!-- 三元表达式 -->
          <p>我目前是{{ obj.age >= 18 ? "成年" : "未成年" }}</p>
          <!-- 方法调用 -->
          <p>msg由{{ msg.split(",").length }}个单词组成</p>
      </div>
  </template>

      <script>
          export default {
  	// 声明数据的地方
  	data() {
                  return {
                      // msg -> 数据属性
                      msg: "Hello, Vue2!",
                      obj: {
                              name: "小vue",
                              age: 7,
                       },
                  };
              },
          };
      </script>
  复制代码
  ```

### 1.2 Vue指令

#### 1.2.1 v-bind

* 作用：给标签属性赋值（将vue变量的值赋值给标签）
* **语法：**
  ```js
  v-bind:原生属性名 = '表达式'

  // 简写
  :原生属性名 = '表达式'
  复制代码
  ```
* 示例展示：

```js
<template>
    <div>
        <!-- 属性绑定指令 -->
        <!-- 语法：v-bind:属性名="表达式" -->
        <!-- <a v-bind:href="url">去B站</a> -->
        <a :href="url">点击去B站</a>
        <a :href="gitee">
            <img :src="img.urlImg" :title="img.title" />
        </a>
    </div>
</template>

<script>
    // 默认导出
    export default {
        data() {
            return {
                url: "https://www.bilibili.com/",
                gitee: "https://gitee.com/",
                img: {
                    urlImg: "https://gitee.com/static/images/logo-black.svg?t=158106664",
                    title: "点击去Gitee",
                },
            };
        },
    };
</script>
复制代码
```

#### 1.2.2 v-on

* **语法：**
  ```js
  v-on:事件名 = '要执行的少量代码'
  v-on:事件名 = 'methods中的方法名'
  v-on:事件名 = 'methods中的方法名(实参)'

  // 简写
  // 将 v-on 简写成 @
  @事件名= "要执行的少量代码"
  @事件名= "methods中的函数名"
  @事件名= "methods中的函数名(实参)"
  复制代码
  ```
* 注意：
  * **data函数** 会把 **对象** **挂载到** 当前 **组件对象** 上
  * 方法中的 **this** 指向 **组件实例**
    * 这个对象包含了data中所有的数据属性和methods中的所有方法
    * 因此我们可以通过this访问data中的任何一个数据属性和methods中的任何方法
    * 如果是写在标签上，this可以省略
* 示例展示：
  ```js
  <template>
      <div>
          <!-- 事件绑定指令：v-on -->
          <!-- v-on-click="少量代码" -->
          <!-- v-on-click="methods中方法名" -->
          <!-- v-on-click="methods中方法名(实参)" -->
          <h4>您购买的商品数量：{{ count }}</h4>
          <button v-on:click="count++">+1</button>
          <button v-on:click="add">+5</button>
          <button v-on:click="addFn(10)">+10</button>
          <!-- 简写：v-on: => @ -->
          <button @click="count--">-1</button>
          <button @click="minus">-5</button>
          <button @click="minusFn(10)">-10</button>
      </div>
  </template>

  <script>
      export default {
          data() {
              return {
                  count: 0,
              };
          },
          methods: {
              add() {
                  // 方法中的this指向组件实例（对象）
                  // 这个对象包含了data中所有数据属性和methods方法
                  // 因此我们可以通过this访问data中的任何一个数据属性和methods中的方法
                  this.count += 5;
              },
              addFn(num) {
                  this.count += num;
              },
              minus() {
                  this.count -= 5;
              },
              minusFn(num) {
                  this.count -= num;
              },
          },
      };
  </script>
  复制代码
  ```

#### 1.2.3 事件对象

* **语法：**
  * 方法没有传递实参：在方法中，直接通过形参直接接收
  * 方法有传递实参：通过 `$event` 指代事件对象传给事件处理函数
  * ![image-20220921101407524.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7fe07da7b3d148b8bd61843201d02a4a~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* Vue事件处理函数，如何拿到事件对象？
  * 没有传递实参：直接在形参接收
  * 有传递实参：手动传入 `$event` 对象
* 示例展示：

```js
<template>
  <div>
    <!-- 阻止事件默认行为 -->
    <!-- 
      语法:
        无传参：可以直接通过事件对象接收
        有传参：通过 $event 指代事件对象传递给事件处理函数
    -->
    <a v-on:click="one" v-bind:href="url">{{ text }}</a>
    <a @click="two(10, $event)" :href="url">{{ text }}</a>
  </div>
</template>

<script>
export default {
  data() {
    return {
      url: "http://www.baidu.com",
      text: "阻止去百度",
    };
  },
  methods: {
    one(e) {
      e.preventDefault();
    },
    two(num, e) {
      e.preventDefault();
    },
  },
};
</script>
复制代码
```

#### 1.2.4 事件修饰符

* **语法：**

  ```js
  @事件名.修饰符 = "methods中的方法名"
  复制代码
  ```
* 多个修饰符可以一起使用；
* 常用修饰符：

  | 修饰符       | 功能                                                                                                                                                                                                                                                                          |
  | ------------ | ----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
  | `.stop`    | 阻止事件冒泡                                                                                                                                                                                                                                                                  |
  | `.prevent` | 阻止事件默认行为                                                                                                                                                                                                                                                              |
  | `.once`    | 程序运行期间，只执行一次事件处理函数（事件还能触发，事件处理函数只执行一次）                                                                                                                                                                                                  |
  | `.native`  | 监听原生事件                                                                                                                                                                                                                                                                  |
  | 更多修饰符   | [见Vue2官方文档](https://link.juejin.cn?target=https%3A%2F%2Fv2.cn.vuejs.org%2Fv2%2Fguide%2Fevents.html%23%25E4%25BA%258B%25E4%25BB%25B6%25E4%25BF%25AE%25E9%25A5%25B0%25E7%25AC%25A6 "https://v2.cn.vuejs.org/v2/guide/events.html#%E4%BA%8B%E4%BB%B6%E4%BF%AE%E9%A5%B0%E7%AC%A6") |
* 示例展示：

```js
<template>
  <div>
    <!-- 事件修饰符 -->
    <!-- .stop：阻止 事件 冒泡 -->
    <!-- .prevent：阻止 事件 默认行为 -->
    <!-- .once：程序运行期间，只触发一次事件处理函数 -->
    <!--- 多个修饰符可以一起使用 -> 链式调用 -->
    <div @click="fatherFn" class="father">
      <button @click.stop="oneFn">{{ textStop }}</button>
      <br />
      <a @click.prevent.stop="prevent" :href="url">{{ textPrevent }}</a>
      <br />
      <input @input.once.stop="input" @click.stop type="text" />
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      textStop: ".stop - 阻止事件冒泡",
      url: "http://www.bilibili.com",
      textPrevent: ".prevent - 阻止事件默认行为",
      textOnce: ".once - 程序运行期间，只触发一次事件处理函数",
      i: 1,
    };
  },
  methods: {
    fatherFn(e) {
      console.log(`我是${e.target.tagName}的父级`);
    },
    oneFn(e) {
      console.log(`我点击了${e.target.tagName}`);
    },
    prevent() {
      console.log("阻止了a链接的默认跳转");
    },
    input() {
      console.log(this.i++);
    },
  },
};
</script>
复制代码
```

#### 1.2.5 按键修饰符

* 语法：

```js
@键盘事件.enter ➡ 监测回车按键
@键盘事件.esc ➡ 监测esc按键
复制代码
```

* [更多按键修饰符](https://link.juejin.cn?target=https%3A%2F%2Fv2.cn.vuejs.org%2Fv2%2Fguide%2Fevents.html%23%25E6%258C%2589%25E9%2594%25AE%25E4%25BF%25AE%25E9%25A5%25B0%25E7%25AC%25A6 "https://v2.cn.vuejs.org/v2/guide/events.html#%E6%8C%89%E9%94%AE%E4%BF%AE%E9%A5%B0%E7%AC%A6")
* 示例展示：
  ```js
  <template>
    <div>
      <!--语法：-->
      <!--  @键盘事件.enter - 监测回车键 -->
      <!-- @键盘事件.esc - 监测Esc键 -->
      <input type="text" @keydown.enter="enterFn" @keydown.esc="escFn">
    </div>
  </template>

  <script>
  export default {
    data() {
      return {}
    },
    methods: {
      enterFn() {
        console.log('按下了回车键');
      },
      escFn() {
        console.log('按下了取消键');
      },
    },
  }
  </script>
  复制代码
  ```

#### 1.2.6 v-model

* 作用：将 **value属性** 和 **Vue数据属性** 进行 **双向绑定**
* **语法：** `v-model = 'vue数据属性'`
* 示例展示：
  ```js
  <template>
      <div>
          <!-- Vue采用MVVM的设计模式，数据和视图双向绑定 -->
          <!-- v-model：将表单的value属性和Vue数据变量进行双向绑定 -->
          <!-- text,password -->
          <label for="text">用户名：</label>
          <input v-model="text" type="text" id="text" /> <br />
          <label for="pwd">密码：</label>
          <input v-model="pwd" type="password" id="pwd" /> <br />

          <!-- 下拉菜单 -->
          <!-- 下拉菜单v-model添加在select标签上，绑定的是option标签的value属性 -->
          <select v-model="city" name="" id="">
              <option value="武汉">武汉</option>
              <option value="杭州">杭州</option>
              <option value="深圳">深圳</option>
              <option value="浙江">浙江</option>
          </select>
          <br />

          <!-- 文本域 -->
          <textarea v-model="txt" name="" id="" cols="30" rows="10"></textarea>

          <!-- 单选框 -->
          <!-- 
              单选框没有value属性，使用v-model得到的是null
              想要使用v-model就必须手动添加value属性
          -->
          <p>
              <span>性别：</span>
              <label for="man">男</label>
              <input v-model="sex" value="男" type="radio" name="sex" id="man" />
              <label for="woman">女</label>
              <input v-model="sex" value="女" type="radio" name="sex" id="woman" />
          </p>

          <!-- 一个复选框 -->
          <!-- 
              要想查看这个复选框是否选中，需要两步
              1.准备一个布尔型的数据变量
              2.通过v-model绑定这个布尔值作用在复选框上即可
              注意：此时这个布尔值和复选框的checked属性关联
          -->
          <p>
              <input v-model="deal" type="checkbox" name="" id="deal" />
              <label for="deal">同意商家协议</label>
          </p>

          <!-- 多个复选框 -->
          <!--
              多个复选框：想收集多个值的时候，需要两步：
              1.准备一个数组类型的数据属性
              2.通过v-model绑定这个数组作用在复选框上即可
              注意：此时这个数组和复选框的value属性关联
                    多个复选框需要手动添加value属性
          -->

          <p>
              爱好：
              <label for="wuhan">武汉</label>
              <input v-model="city" value="武汉" type="checkbox" name="" id="wuhan" />
              <label for="hangzhou">杭州</label>
              <input
                  v-model="city"
                  value="杭州"
                  type="checkbox"
                  name=""
                  id="hangzhou"
              />
              <label for="shanghai">上海</label>
              <input
                  v-model="city"
                  value="上海"
                  type="checkbox"
                  name=""
                  id="shanghai"
              />
              <label for="zhejiang">浙江</label>
              <input
                  v-model="city"
                  value="浙江"
                  type="checkbox"
                  name=""
                  id="zhejiang"
              />
      </p>
  </div>
  </template>

  <script>
      export default {
          data() {
              return {
                  text: "",
                  pwd: "",
                  city: "武汉",
                  txt: "",
                  sex: "",
                  deal: "",
                  city: [],
              };
          },
      };
  </script>
  复制代码
  ```

#### 1.2.7 v-model修饰符

* 常用修饰符：| 修饰符      | 作用                                                                                      |
  | ----------- | ----------------------------------------------------------------------------------------- |
  | `.number` | 尝试调用 `parseFloat()`去将字符串转换为数字，能转就转，不能转就显示原值（原来的字符串） |
  | `.trim`   | 去除 首尾 空白字符                                                                        |
  | `.lazy`   | 在 `change`时触发 而非 `input`（内容改变的同时又失去焦点）                            |
* 示例展示：

```js
<template>
    <div>
        <div>
            <!-- .number - parseFloat将字符串转成数字型 -->
            <span>年龄：</span>
            <input type="text" v-model.number="age" name="" id="" />
            <!-- <input type="text" v-model.number="age" name="" id="" /> -->
        </div>
        <div>
            <!-- .trim - 去除字符串首尾两侧的空白字符 -->
            <span>人生格言：</span>
            <input type="text" v-model.trim="motto" name="" id="" />
            <!-- <input type="text" v-model.trim="motto" name="" id="" /> -->
        </div>
        <div>
            <span>自我介绍：</span>
            <textarea
                v-model.lazy="intro"
                name=""
                id=""
                cols="30"
                rows="10"
            ></textarea>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                age: "",
                motto: "",
                intro: "",
            };
        },
    };
</script>
复制代码
```

#### 1.2.8 v-text 和 v-html

* 作用：显示标签的内容
* **语法：**

```js
v-text = 'Vue数据属性'  // 和 innerText 一样 （不识别标签）
v-html = 'Vue数据属性'  // 和 innerHTML 一样 （识别标签）
复制代码
```

* 注意：
  * 会 覆盖 插值表达式 和 标签的文本
  * v-text 和 插值表达式 效果一样

#### 1.2.9 v-show 和 v-if

* 作用：控制标签的 隐藏 和 显示
* **语法：**
  ```js
  // 表达式的值：true - 显示 / false - 隐藏
  v-show = '表达式'
  v-if = '表达式'
  复制代码
  ```
* 原理：
  * v-show：用的 `display: none;` （频繁切换使用）
  * v-if：动态的向DOM树添加或删除元素（删除/添加标签）
* **高级：**
  * 1️⃣ **双分支：** **v-if** 和 **v-else**
  * 2️⃣ **多分支：** **v-if** 也可以和 **v-else-if** 配合使用
  * **注意：**
    * 不管双分支还是多分支，**标签与标签之间** 必须是 ***兄弟关系***
* 示例展示：

```js
<template>
    <div>
        <!-- 元素的显示和隐藏 -->
        <!-- 
            v-show ： 原理：display: none;
            v-if：原理：直接从DOM树上移除
            相同点：都可以控制元素的显示和隐藏（视觉效果一样）
            不同点：控制元素显示和隐藏的原理不同
                    v-show：控制css的display属性控制
                    v-if：通过创建和插入或者移除DOM元素的方式控制
            使用场景：频繁切换元素的显示隐藏就使用v-show，否则就使用v-if
        -->
        <!-- 双分支 -->
        <p v-if="!token">你好，请登录！</p>
        <p v-else>xxx，欢迎回来！</p>
  
        <!-- 多分支 -->
        <p v-if="score >= 90">A</p>
        <p v-else-if="score >= 80">B</p>
        <p v-else-if="score >= 70">C</p>
        <p v-else-if="score >= 60">E</p>
        <p v-else-if="score < 60">D</p>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                token: "token...",
                score: "85",
            };
        },
    };
</script>
复制代码
```

#### 1.2.10 v-for

* 作用：列表渲染，所在标签结构，按照 数组 / 对象 循环生成
* **语法：**

```js
// 循环数组
v-for = '(值变量, 索引变量) in 目标结构'  // 索引可以省略

// 循环对象
v-for = '值变量 in 目标结构'

// 循环数字
v-for = '变量名 in 固定数字'
复制代码
```

* **注意：**
  * 让谁循环生成，v-for就写在谁身上；
  * **in** **两侧** **必须有**  **空格** ；
  * v-for 的 变量名 只能用在 v-for 的范围内
  * 可以遍历数组 / 对象（不推荐） / 固定数字（不推荐）
  * 在写 v-for 的时候，需要添加 key 属性（ **唯一不重复的数字或字符串** （推荐））
* 示例展示：

```js
<template>
    <div>
        <!-- v-for：循环列表 -->
        <!-- 谁想循环就加给谁 -->
        <!-- v-for的变量名只能在v-for的范围内访问 -->
  
        <!-- 语法1：循环数组 -->
        <!-- v-for="(值变量, 索引变量) in 目标结构" -->
        <ul>
            <li v-for="(item, index) in arr" :key="index">
                {{ item }} --- {{ index }}
            </li>
        </ul>
  
        <!-- 语法2：循环数组（不需要索引） -->
        <ul>
            <li v-for="item in stuArr" :key="item">
                {{ item.id }}-{{ item.name }}-{{ item.sex }}-{{ item.hobby }}
            </li>
        </ul>
  
        <!-- 语法3：循环对象 -->
        <ul>
            <li v-for="(value, key) in tObj" :key="value">{{ value }} - {{ key }}</li>
        </ul>
  
        <!-- 语法4：循环固定数字 -->
        <ul>
            <li v-for="n in count" :key="n">
                {{ n }}
            </li>
        </ul>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                arr: ["小明", "小欢欢", "大黄"],
                stuArr: [
                    {
                        id: 1001,
                        name: "孙悟空",
                        sex: "男",
                        hobby: "吃桃子",
                    },
                    {
                        id: 1002,
                        name: "猪八戒",
                        sex: "男",
                        hobby: "背媳妇",
                    },
                ],
                tObj: {
                    name: "小黑",
                    age: 18,
                    class: "1期",
                },
                count: 10,
            };
        },
    };
</script>
复制代码
```

### 1.3 更新监测 和 key的作用

#### 1.3.1 更新监测

* 目标结构变化，触发v-for的更新
* 哪些操作会导致目标结构发生变化并使 `v-for`更新？
  1. 数组变更方法（改变原始数组）
     `push、unshift、pop、shift、splice、reverse`
  2. 数组非变更方法（不改变原始数组）
     * 注意：数组非变更方法，返回一个新数组，不会导致v-for更新
     * 解决办法：
       * 将返回到的新数组重新赋值给目标结构
  3. 更新某个值
     * 注意：更新某个值的时候，v-for监测不到，不会更新
     * 解决办法：
       * 使用 `this.$set()`
       * 语法：`this.$set(更新的目标结构, 改变原始的索引 / 对象的属性, 更新值)`
* `v-for`更新时，会循环出新的虚拟DOM，和旧的虚拟DOM结构比较，尝试复用标签就地更新（diff算法）。
* 示例展示：
  ```js
  <template>
      <div>
          <!-- 更新监测 -->
          <ul>
              <li v-for="item in arr" :key="item">{{ item }}</li>
          </ul>
          <button @click="reverseFn">数组变更方法</button>
          <button @click="changeFn">数组非变更方法</button>
          <button @click="noChangeFn">改变某个值</button>
      </div>
  </template>

  <script>
      export default {
          data() {
              return {
                  arr: [1, 2, 3, 4, 5],
              };
          },
          methods: {
              reverseFn() {
                  // 1.调用数组变更方法（改变原始数组的方法）
                  // 会导致 v-for 的更新，页面更新
                  // push, unshift, pop, shift, splice, sort, reverse
                  // this指向组件实例
                  this.arr.reverse();
              },
              changeFn() {
                  // 2.调用数组非变更方法，返回一个新数组，不会导致 v-for 的更新
                  // 解决方法： 只用得到的新数组直接覆盖原始数组，既对原始数组进行赋值操作
                  this.arr = this.arr.slice(0, 3);
              },
              noChangeFn() {
                  // 3.改变数组某个值的时候，v-for监测不到，不会导致v-for更新
                  // 解决方法： 使用 this.$set()
                  // 语法： this.$set(更新的目标结构, 改变元素的索引, 更新值)
                  this.$set(this.arr, 0, 1000);
              },
          },
      };
  </script>
  复制代码
  ```

#### 1.3.2 key

* key的使用：
  * 有id用id，无id用索引。
* key的优点：
  * 可以配合虚拟DOM提高更新性能。

### 1.4 动态 class

* 用 `v-bind` 给标签 class 设置 动态的值
* **语法：**
  ```js
  // 表达式为真，类名生效；反之，则不生效。
  // 1. 三元绑定法
  :class = "条件 ? '类名1' : '类名2'"

  // 2. 对象绑定法
  // 可以绑定多个类名
  :class = { 类名: 表达式 }
  复制代码
  ```
* **注意：**
  * 多个类名可以工作，会合并（雷鸣之间使用 空格 隔开），共同作用到标签身上
* 示例展示：

```js
<template>
    <div>
        <!-- 动态设置 - class -->
        <!-- 1.三元绑定法 -->
        <p :class="isActive ? 'active' : ''">鸡你太美</p>
  
        <!-- 2.对象绑定法 -->
        <p :class="{ active: 'active' }">鸡你太美</p>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                isActive: true,
            };
        },
    };
</script>

<style>
    .active {
        color: #9e03d6;
        font-size: 30px;
        font-family: "隶书";
    }
</style>
复制代码
```

### 1.5 动态 style

* 给 标签 动态 设置 style 的 值
* **语法：**
  ```js
  :style = "{ css属性名: 值 }"
  // 针对行内样式
  复制代码
  ```
* 示例展示：

```js
<template>
    <div>
        <!-- 动态设置style -->
        <!-- 语法： :style={css属性名: 值} -->
        <!-- 驼峰命名法 -->
        <p :style="{ backgroundColor: '#e6004b' }">动态设置style</p>
        <p :style="{ color: colorStr }">鸡你太美</p>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                colorStr: "green",
            };
        },
    };
</script>
复制代码
```

### 1.6 计算属性

#### 1.6.1 基础

* 计算属性：一个数据属性的值，依赖于另外的数据属性计算而来的结果
* **语法：**
  ```js
  computed: {
      计算属性名() {
          // 必须有return
          return 值
      }
  }
  复制代码
  ```
* 使用：计算属性也是vue变量，使用的时候，直接当作vue数据属性，直接使用即可。
* **注意：**
  * 计算属性也是vue数据属性，所以不要和data里的变量重名；
  * 计算属性必须要有 `return`；
  * 计算属性 声明 的时候是 方法，但是在 使用 的时候是 属性（有return）；
  * 当依赖发生变化的时候，计算属性会重新执行，返回新的结果
* 使用场景：当一个变量的值，需要通过另外的变量计算而来就使用计算属性（单选和多选框）
* 示例展示：

```js
<template>
    <div>
        <!-- 计算属性也是vue变量，使用的时候，直接当作vue变量使用 -->
        <p>{{ add }}</p>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                a: 10,
                b: 20,
            };
        },
        // 计算属性 - computed
        // 一个变量的值，需要用另外的变量计算得来
        // 	语法：
        // 	computed: {
        // 	    计算属性名() {
        // 	        return 值
        //          }
        // 	}
        computed: {
            add() {
                return this.a + this.b;
            },
        },
    };
</script>
复制代码
```

#### 1.6.2 计算属性缓存特性

* 计算属性基于依赖的值进行缓存，依赖的变量不变，都直接从缓存中取结果。
* 计算属性的优势：
  * 缓存特性；
  * 计算属性对应的函数执行后，会啊 return 的值缓存起来；
  * 如果依赖项不发生变化，多次使用都是从缓存中取值；
  * 依赖项的值发生变化，函数会 自动 重新执行，并把最新的结果再次缓存起来。

#### 1.6.3 进阶 - 完整写法

* 计算属性 也是 vue数据属性，也可以使用 `v-mode` 双向关联 表单数据 的 值
* 写法的选择：
  * 只是读取值：写简易写法（函数形式，必须要有 return ）
  * 给计算属性赋值：必须写完整写法（对象形式）
* **语法：**
  ```js
  computed: {
      计算属性名: {
          set(val) {
              // 不需要传递参数，自动接收要给计算属性赋值的数据
              // val => 要给计算属性赋值的数据
          },
          get() {
              // 对应的逻辑
              return 值
          }
      }
  }
  复制代码
  ```
* 注意：
  * 给计算属性赋值，触发 `set` 方法；
  * 使用计算属性的值，触发 `get` 方法。
* 代码展示：
  ```js
  <template>
          <div>
                  <p>
                          <span>用户名：</span>
                          <input type="text" v-model="full" name="" id="" />
                  </p>
          </div>
  </template>

  <script>
      export default {
          // 给计算属性赋值
          // 计算属性完整写法：
          // computed: {
          //     属性名: {
          //          给full赋值触发set方法
          //          set(值) {},
          //          使用full的值触发get方法
          //          get() {
          //              return 值;
          //          },
          //      },
          // },
          computed: {
              full: {
                  // 给full赋值触发set方法
                  set(val) {
                      console.log(val);
                  },
                  // 使用full的值触发get方法
                  get() {
                      return "张三";
                  },
              },
          },
      };
  </script>
  复制代码
  ```
* 单选、多选、反选 案例：
  ```js
  <template>
      <div>
          <span>全选:</span>
          <input type="checkbox" v-model="isAll" />
          <button @click="opposite">反选</button>
          <ul>
              <li v-for="(item, key) in arr" :key="key">
                  <input type="checkbox" v-model="item.c" />
                  <span>{{ item.name }}</span>
              </li>
          </ul>
      </div>
  </template>

  <script>
      export default {
          data() {
              return {
                  arr: [
                      {
                          name: "猪八戒",
                          c: false,
                      },
                      {
                          name: "孙悟空",
                          c: false,
                      },
                      {
                          name: "唐僧",
                          c: false,
                      },
                      {
                          name: "白龙马",
                          c: false,
                      },
                  ],
              };
          },
          methods: {
              opposite() {
                  this.arr.forEach((item) => (item.c = !item.c));
              },
          },
          // 计算属性
          computed: {
              isAll: {
                  set(val) {
                      this.arr.forEach((item) => (item.c = val));
                  },
                  get() {
                      return this.arr.every((item) => item.c === true);
                  },
              },
          },
      };
  </script>
  复制代码
  ```

### 1.7 侦听器

#### 1.7.1 侦听基本数据类型

* 作用：侦听 `data` 或 `computed` 属性值的改变。
* **语法：**
  ```js
  // 侦听器的简易写法 - 监听基本数据类型
  watch: {
      "被侦听的属性名" (newVal, oldVal) {
          // 相关逻辑
      }
  }
  // newVal：当前最新的值
  // oldVal：上一次的值，旧值
  复制代码
  ```
* 示例展示：
  ```js
  <template>
      <div>
          <input v-model="keyWord" type="text" />
      </div>
  </template>

  <script>
      export default {
          data() {
              return {
                  keyWord: "",
              };
          },
          // 侦听器
          // 语法：
          // watch: {
          // 	被侦听的属性名或方法名(newValue, oldValue) {
          //		函数体
          // 	}
          // },
          watch: {
              keyWord(newValue, oldValue) {
                  console.log(newValue, oldValue);
              },
          },
      };
  </script>
  复制代码
  ```

#### 1.7.2 侦听引用数据类型

* **语法：**

```js
// 如果使用简易写法，当给引用数据赋值的时候才能监测到
watch: {
    // 固定写法
    "data / computed里要侦听的属性名": {
        // 深度侦听引用数据变化
        deep: true, 
        // 立即执行，侦听器函数立即执行，默认为false（一般为false）
        immediate: true,
        handler (newVal, oldVal) {
            // 具体逻辑
        }
    }
}
复制代码
```

* 注意：
  * 侦听引用数据类型，要加 `deep: true` 开启深度侦听；
  * 属性名都是固定写法
  * 新旧对象，原来的对象，三者指向同一地址。
* 示例展示：

```js
<template>
    <div>
        <input v-model="uname" type="text" /> <br />
        <input v-model="msg.age" type="text" />
    </div>
</template>

<script>
    export default {
        data() {
            return {
                uname: "",
                msg: {
                    age: 0,
                },
            };
        },
        // 侦听器
        watch: {
            // 侦听基本数据类型 - 简易写法 - 格式：方法
            // 语法：
            // "data或watch里侦听的属性名"(newVal, oldVal) {
            // 	                                                函数体
            //                                            }
            uname(newVal, oldVal) {
                    console.log(newVal, oldVal);
            },
    
            // 侦听引用数据类型 - 完整写法 - 格式：配置项
            // 侦听的是引用数据类型 - 只要有一个变化，整个引用数据都要变化
            // 语法：
            // "data或methods里侦听的属性名": {
            // 	    immediate: true,	// 立即执行，侦听器函数马上执行，默认为false(一般为false或直接不写)
            // 	    deep: true,
            // 	    handler (newVal, oldVal) {
            // 		函数体
            // 	    }
            // }
            msg: {
                // deep, handler --  固定写法
                deep: true, // 开启深度侦听
                handler(newVal, oldVal) {
                    console.log(newVal, oldVal);
                    // 新旧对象，原来的对象 - 指向同一地址
                    console.log(newVal === oldVal); // true
                    console.log(this.msg === newVal); // true
                },
            },
        },
    };
</script>
复制代码
```

## 二、 组件基础

### 2.1 组件的创建和使用

#### 2.1.1 组件概念

* 组件：可复用的Vue实例，封装  **标签** 、**样式** 和  **JS** ；
  * 代码里面体现为一个独立的 `.vue` 文件；
* 组件化：封装的思想，把页面上 **可复用的部分** 封装为组件，从而方便项目的开发和维护；
  * 一个页面可以拆分成多个组件，一个组件就是一个整体，每个组件有自己独立的结构样式行为。
* 使用场景：遇到标签可复用的时候；
* 组件的优点：
  * 各自独立：每个组件都有自己独立的结构样式和行为；
  * 便于复用

#### 2.1.2 组件的创建和使用

* 1️⃣ **创建组件**
  * 创建一个 `.vue`文件，封装想要复用的html、js、css；
* 2️⃣  **导入组件** ：
  * 1. 全局导入：

    * **语法** ：

    ```js
    import Vue from 'vue'
    import 组件对象 from '组件路径（.vue文件路径）
    复制代码
    ```
  * 2. 局部导入：

    * **语法** ：

    ```js
    import 组件对象 from '组件路径（.vue文件路径）'
    复制代码
    ```
* 3️⃣ **注册组件**
  * 1. 全局注册

    * 在 `main.js` 中注册
    * **语法：**
      ```js
      import Vue from 'vue'
      // 组件名 - 字符串
      // 组件对象 - 变量
      Vue.component('组件名', 组件对象)
      复制代码
      ```
    * 注意：该组件在所有的 `.vue`文件中都可以使用；
  * 2. 局部注册

    * 在 `需要组件的文件` 中注册
    * **语法：**
      ```js
      export default {
          components: {
              组件名: 组件对象
          }
      }
      复制代码
      ```
    * 注意：
      * 根据ES6的新特性，属性名和属性值一样，可以简写，所以可以将 **组件名** 和 **组件对象** **写成一样** 的；
      * 该组件仅限当前文件使用；
* 4️⃣ **使用组件**
  * 将 **组件名** 当作 **自定义标签** 去  **使用** ；
  * **语法：**
    ```js
    <template>
        <div id="app">
            <组件名 />
            <组件名></组件名>
        </div>
    </template>
    复制代码
    ```
  * 注意：
    * 单双标签都可以，**单标签** 需要  **自闭合** ；
    * 该组件是块元素还是行内元素，取决于组件的根标签，既 `template` 下面唯一的根标签；
    * 组件运行之后的样子：使用 组件里的 唯一根标签 替换 组件标签
  * 组件命名规范：
    * 使用 大驼峰 命名法；
    * `.vue`文件名 = 组件对象 = 组件名 = 自定义标签名
  * 示例展示：
    * 全局导入 + 全局注册 + 使用步骤：
      ```js
      // 该行代码已经有，不需重复写
      import Vue from 'vue'

      // 全局导入组件
      // 语法： import 组件对象 from '组件路径'
      import PanelX from './components/Panel.vue'

      // 全局注册组件
      // 语法：Vuw.component('组件名', 组件对象)
      // 组件名 - 字符串
      // 组件对象 - 变量
      Vue.component("PanelX", PanelX);
      复制代码
      ```
    * 局部导入 + 局部注册 + 使用步骤
      ```js
      <template>
          <div id="app">
              <h3>案例：折叠面板</h3>
              <!-- 4.使用组件 - 直接将组件名当作标签使用 -->
              <!-- 单双标签都可以 -->
              <!-- 单标签需要自闭和 -->

              <!-- 局部注册的组件 -->
              <Panel />
              <Panel></Panel>

              <!-- 全局注册的组件 -->
              <PanelX />
              <PanelX></PanelX>
          </div>
      </template>

      <script>
          // 1.封装组件 - 抽取可复用标签 + 对应JS代码 + 对应样式
          // 2.导入组件
          // 语法：import 组件对象 from '组件路径'
          import Panel from "./components/Panel.vue";
          export default {
              // 3.注册组件
              // components: {
              // 	"组件名": 组件对象,
              // }
              components: {
                  // Panel: Panel,
                  // 简写
                  Panel,
              },
          };
      </script>

      <style lang="less">
          body {
              background-color: #ccc;

              #app {
                  width: 400px;
                  margin: 20px auto;
                  background-color: #fff;
                  border: 4px solid blueviolet;
                  border-radius: 1em;
                  box-shadow: 3px 3px 3px rgba(0, 0, 0, 0.5);
                  padding: 1em 2em 2em;

                  h3 {
                      text-align: center;
                  }
              }
          }
      </style>
      复制代码
      ```

#### 2.1.3 scoped 的作用

* 用 `scoped` 修饰 `style` 标签之后，`webpack`在打包的时候，会尝试去给当前vue文件里的标签添加随机哈希值；
* 准备：当前 **组件内标签** 都被添加 `data-v-8位哈希值` 的属性；
* 获取：**css选择器** 都被添加 `[data-v-8位哈希值]` 的  **属性选择器** ；
* 注意：
  * 每个组件的自定义属性都是相互独立的；
  * 给当前所有标签都添加自定义属性，并且自定义属性是相同的；
  * css选择器，就会形成一个交集选择器
    * 原有的选择器 + 属性选择器
  * ![image-20220926150657351.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e5f3025174284feea3954ad6179520e3~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 2.2 组件通信

* 为什么组件之间需要通信？
  * vue写代码思想：组件化，代码不会写在一个文件中，而是拆分成一个一个的组件，最后再进行组装，得到一个完整界面，由于代码分散在不同的文件中中，最后是通过这些组件之间的配合来实现需求，这时候难免它们之间必须要传数据。

#### 2.2.1 父组件向子组件传值（props机制）

* 在 **父组件** 里面引入  **子组加** （谁被引入，谁就是子组件）
* **步骤：**
  * 1️⃣ 子组件内，声明固定变量（`props`）接收传递的数据：
    * ❌ 数组型的props：
      * 在 `props`数组里面，准备字符串型作为接收数据的变量，接收父组件传递过来的数据；
      * 若 **父组件**  **未传值** ，则接收到的数据就是  **undefined** ；
    * ✅ 对象型的props：
      * 每个变量都是一个对象，作为props的子对象；
      * 每个对象都有三个属性：
        * `type`：
          * 属性值：`Number、String、Boolean、Array、Object、Function、Promise、……`
          * 规定传入数据的类型；
          * 如果传入的数据不是规定的类型，就会报错；
          * **注意：**
            * `null` 和 `undefined`会通过任何类型验证；
        * `required`：
          * 属性值：`true / false`
          * 是否必传（不传数据会报错）；
          * `required` `default` 两个属性二选一；
        * `default`：
          * 默认值（不传数据就用默认值，传了就用传递的数据）；
          * **注意：**
            * 对象或数组默认值必须从一个工厂函数获取
    * 
  * 2️⃣ 引入组件，注册组件，使用组件，传值进去；
    * 在自定义标签上，使用 `v-bind`动态绑定属性，子组件内声明的接收变量作为属性名，要传递的数据（变量）作为属性值传递过去。
* 这块东西还是比较多的，具体的一些可以去[Vue2官网的prop中](https://link.juejin.cn?target=https%3A%2F%2Fv2.cn.vuejs.org%2Fv2%2Fguide%2Fcomponents-props.html "https://v2.cn.vuejs.org/v2/guide/components-props.html")看看。
* 示例展示：
  * 父组件：
    ```js
    <template>
        <div>
            <!-- 使用组件 -->
            <组件名 :子组件props中的变量名="父组件要向子组件传递的数据"></组件名>
        </div>
    </template>

    <script>
        // 导入组件
        import 组件对象 from '组件路径'
        // .vue文件名 = 组件对象名 = 组件名 = 自定义标签名
        export default {
            // 注册组件
            components: {
                组件名: 组件对象
            }
        }
    </script>

    复制代码
    ```
  * 子组件：
    ```js
    <script>
        export default {
            // 1.声明固定变量，接收父组件传递过来的数据属性
            // 2.父组件内，子组件上，通过自定义属性传数据属性
            // 注意：props在根data、methods等平级

            // props第一种写法
            props: ['变量名1', '变量名2', '变量名3', ……],

            // props第二种写法
            props: {
                变量1: {
                    type: Number（简单数据类型）,
                    required: true
                },
                变量2: {
                    type: Number（简单数据类型）,
                    default: 写成对应类型的数据,
                },
                变量3: {
                    type: Array / Object,
                    dafault: function () {
                        // type = Array
                        return [对应的数据]

                        // type = Object
                        return { 对应数据 }
                    }
                }
            }
        }
    </script>
    复制代码
    ```

#### 2.2.2 子组件向父组件传值（$emit机制）

* 1️⃣ **父组件内** 给 **组件标签** 绑定 **自定义事件** 和 **事件处理函数**
  * **语法：**
    ```js
    <组件标签 @自定义事件名 = "父组件methods中定义的事件名" />
    复制代码
    ```
* 2️⃣ 子组件内，在恰当的时机，触发父组件给子组件绑定的自定义事件，进而触发父组件methods中对应的事件处理函数执行；
  * **语法：**
    ```js
    this.$emit('自定义事件名', 要向父组件传递的数据)
    复制代码
    ```

#### 2.2.3 单向数据流

* 为什么不建议 子组件修改父组件传过来的值？
  * 父子数据不一致，而且子组件是依赖于父组件传入的值；
  * 导致数据的不一致。
* **单向数据流：**
  * 从 **父组件** 到 **子组件** 的 **数据流向**
* **Vue规定** ：
* `props`  里的 变量，本身是 **只读** 的
* 这里只是简单说了一下，想要了解的更深可以去问[度娘](https://link.juejin.cn?target=https%3A%2F%2Fwww.baidu.com%2F "https://www.baidu.com/")。

#### 2.2.4 跨组件通信（了解即可）

* 跨组件通信：两个 没有任何关系 的 组件 进行 通信；
* **语法：**
  ```js
  // 1. src/EventBus/index.js - 创建空白Vue实例并导出
  // 2. 在要 接受值 的 组件 - eventBus.$on("事件名", 事件处理函数)
  // 3. 在要 传递值 的 组件 - eventBus.$emit("事件名", 事件处理函数的实参1, 事件处理函数的实参2, ……)
  复制代码
  ```
* 示例展示：
* ![image.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/334f12997c2f4845845f3ab394ddabd7~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)（图片看不清可以右键，在新标签页中打开图片）

### 2.3 Vue生命周期

#### 2.3.1 钩子函数

* 钩子函数：
  * Vue框架内置函数，随着组件的生命周期自动执行；
  * 监测Vue生命周期到达了什么阶段；
* 作用：在特定的时间点，执行特定的操作；
* 使用场景：
  * 组件创建完毕后，可以在 `created`生命周期函数中发起Ajax请求，从而初始化 `data`数据；
* 分类：四个阶段，八个方法

#### 2.3.2 复习axios使用步骤

* 下包：`yarn add axios -S`
* 导入包：`import axios from 'axios'`
* 调用 `axios`函数，对接接口文档，获取相关数据：
  ```js
  // 1. 创建 axios 实例并导出
  // 一般都是在 src/utils/request.js 下写
  import axios from 'axios'

  const request = axios.create({
      baseURL: process.env.VUE_APP_BASE_API,
      timeout: 5000
  })

  export default request

  // 2. 在 src/api/对应的js文件 导出 request（axios实例） 
  import request from '@/utils/request'
  // @ 表示 src 文件夹
  /*
   * 参数根据需求填写
   * method = GET ➡ params（get方式传参）
   * method = POST ➡ data（post方式传参）
   */
  export function 方法名(data 或 params) {
      return request({
          method: '请求方式',
          url: '请求地址',
          data 或 params（根据请求方式填写，此处使用了对象的简写，属性值和属性名一样）
      })
  }

  // 3. 在需要使用接口的地方，导入接口，使用即可
  复制代码
  ```

#### 2.3.3 初始化阶段

* 1️⃣ `new Vue()` ➡ Vue实例化
  * 在 `main.js` 中实例化Vue，从此刻起，Vue就诞生了；
  * 组件也是一个小的VUe实例；
* 2️⃣ `Init Event & Lifecyle` ➡ 给Vue实例添加属性和方法
  * 给Vue上户口，给Vue实例添加属性和方法，这些属性和方法我们暂时用不了，无需关心；
* 3️⃣ ❌ `beforeCreate` ➡ 执行钩子函数，当前钩子函数 **访问不了** **data数据** 和 **methods方法**
  * 我们在组件中声明的data数据和methods方法还没有加到Vue实例上，这个钩子中，无法访问数据和方法，开发中不用，但个别源码会用；
* 4️⃣ `Init injections&reactivity` ➡ 开始把在data数据和mothods方法变成响应式，并且加到vue实例上
* 5️⃣ ✅ `created` ➡ 开始执行钩子函数，现在  **可以访问data数据和methods方法** ；
  * **使用场景** ： **发起Ajax请求** 、开启定时器；
  * 当我们一进入某个组件，就需要获取后台数据展示界面。可以在这个时候发起Ajax请求， **早点拿数据，早点渲染界面** ；
  * ![image-20220927171339403.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/be7ff59f420e44b79520284be444c12a~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 示例展示：
* 小结：
  * Vue实例从 创建 到 编译模板 执行了 哪些 钩子函数？
    * `beforeCreate、created`
  * `created`函数 触发 能获取 `data` 吗？
    * 可以获取，但是不能获取真实DOM；

#### 2.3.4 挂载阶段

* `Has el option?` ➡ 判断 `new Vue({})`的参数对象中是否有 `el`选项 - 检查要挂到那里；
  * `el`选项：决定Vue实例编译好模板以后，要将编译后的标签结构挂载到哪里；
  * NO：
    * 脚手架生成的代码没有 `el`选项；
    * new出来的Vue实例调用 `$mount()`方法（脚手架生成的代码就是调用 `$mount()`方法）；
  * YES：
    * 继续检查 `template` 选项；
  * ![image-20220927002138367.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0c61ebb0c26846a0958a0ad826afed44~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* `Has "template" option?` => 判断参数对象中是否有 `template`选项；
  * YES - 编译 `template`返回 `render`函数：
    * 插入到 el 或 $mount() 指定的位置；
  * NO：
    * 编译 `el`的外部HTML内容（自己本身+内部的标签）作为模板；
    * 用 `App.vue`的根标签及其子元素把 `public/index.html`中 `<div id="app"></div>`整体给替换掉（真实DOM展示的就是 `App.vue`的所有标签）；
* 虚拟DOM 挂载成 真实DOM 之前；
* ❌`beforeMount` => 把 `App.vue`的所有标签（插值，指令等）编译完毕之后，开始执行 `beforeMount()`钩子函数，此时，`template`中的标签知识编译完毕，但还没有变成真实DOM，也就是说，此时我们无法获取真实DOM（这个钩子是个废物）；
* `Crate vm.$el ...` - 把 虚拟DOM 和 渲染的数据 一并挂到 真实DOM 上；
  * 用 `App.vue`中的根标签及其子元素替换掉 `<div id="app"></div>`盒子，此时，我们在 `template`中写的标签（虚拟DOM）才会变成真实DOM，挂载到DOM树上；
* 真实DOM挂载完毕；
* ✅ `mounted` - **虚拟DOM变成真实DOM**之后，才执行该函数，因此此时可以 **获取任何一个真实DOM** ，进而可以去操作DOM；
  * 虚拟DOM变成真实DOM之后，才执行mounted钩子，此时可以获取真实DOM；
  * 使用场景：获取真实DOM，进而通过Web Api进行操作；
* ![image-20220927182740238.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0bc604344c3844fe9f0cdaaffb620abe~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 示例展示：
* 小结：
  * Vue实例 从 创建 到 显示 都经历了哪些钩子函数？
    * `beforeCrate、created、beforeMount、mounted`；
  * `created`函数里，能获取真实DOM吗？
    * 不能获取真实DOM（模板都还没编译）；
  * 在什么钩子函数里面可以获取真实DOM？
    * `mounted`钩子中可以获取真实DOM；
    * 挂载之前的是虚拟DOM，挂载之后的是真实DOM；

#### 2.3.5 更新阶段

* 前提：更新阶段的前提是随着data数据的变化而执行的，如果数据不变，这个阶段的钩子是不会执行的，只要数据变化了，每次都是执行该钩子函数；
* 当 `data`里数据改变，更新DOM之前；
* ❌`beforeUpdate` ➡ 生命周期钩子函数被执行；
  * 数据变了之后，紧接着执行 `beforeUpdate()`钩子，此时能获取到最新的数据，但是DOM还没更新，此时获取不到最新的DOM内容（这个钩子基本上是个废物）；
* `Virtual DOM......` ➡ 虚拟DOM重新渲染，打补丁到真实DOM（diff算法的比对）；
  * 当数据变了，Vue内部要开始diff算法对比，找出变化的地方，进行打补丁，重新渲染；
* ❌`updated` ➡ 生命周期钩子函数被执行；
  * 当组件重新渲染完毕之后，开始执行 `updated()`钩子，此时可以 **获取最新的DOM内容** ；
* 当有 `data`数据改变 ➡ 重复这个循环；
* ![image-20220927181014046.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e2ed080a3c2d4984b42f5338147e6a60~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 注意：
  * 程序刚开始运行，不执行这两个钩子函数；
  * 只有当数据改变的时候，才会执行；
* 示例展示：
* 小结：
  * 什么时候执行 `updated` 钩子函数？
    * 当数据发生变化并更新页面后；
  * 在哪可以获取更新后的DOM？
    * 在 `updated` 钩子函数里面；

#### 2.3.6 销毁阶段

* 当组件实例的 `$destroy()`被调用，就会触发组件的销毁（除了调用$destroyed()来销毁组件，还可以通过v-if来销毁组件）；
* `beforeDestroy` ➡ 生命周期钩子函数被执行；
* 拆卸当前组件的所有侦听器、子组件和事件监听器（把当前组件占用的资源释放掉，做内存回收工作）；
* 内存回收处理完毕后，此时执行最后一个钩子函数（组件的销毁后钩子）；
* `destroyed` ➡ 生命周期钩子函数被执行（Vue实例生命周期结束）；
* ![image-20220927183120072.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4d08ea1a32a6492390d8ac8bb647786a~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 示例展示：
  * ![image-20220929092902503.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e546ea228b17430b9ca50d91cb4a423e~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)；
* 小结：
  * 一般在 `beforeDestory、destoryed` 里做什么？
    * 手动消除计时器、定时器、全局事件；
  * 生命周期中常用的钩子函数：

### 2.4 axios

#### 2.4.1 axios 基本介绍

* **特点：**
  * 支持客户端发起Ajax请求
  * 支持服务端Node.js发起请求
  * 支持Promise相关用法
  * 支持请求和响应的拦截器功能
  * 自动转换JSON数据
* Ajax如何给后台传参？
  * 在url拼接 ==> 查询字符串
  * 在url路径上 ==> 需要后端处理
  * 在请求体 / 请求头 ==> 传参给后台

#### 2.4.2 axios 基本使用

* 前置工作：
  ```js
  // 下载 axios
  yarn add axios -S
  // 导入axios
  import axios from 'axios'
  复制代码
  ```

##### 2.4.2.1 GET请求

* **语法：**
  ```js
  // 默认就是 GET 请求，可以不写
  // 请求方式的 大小写 都可以
  axios({
      method: 'GET',
      url: '请求地址',
      params: {
          // 要携带的参数
      }
  })
      .then(res => console.log(res))	// 得到请求成功的数据
      .catch(err => console.log(err))	// 捕获错误

  -----------------------------------------------

  async get() {
      const res = await axios({
          method: 'GET',
          url: '请求地址',
          params: {
              // 要携带的参数
          }
      })
      console.log(res.data)
  }
  复制代码
  ```

##### 2.4.2.2 POST请求

* **语法：**
  ```js
  axios({
      method: 'POST',
      url: '请求地址',
      data: {
          // 要传递的参数
     }
  })
  .then(res => console(res))
  .catch(err => console.log(err))

  -----------------------------------

  async post() {
      const res = await axios({
          method: 'POST',
          url: '请求地址',
          data: {
              // 要写带的数据
          }
      })
      console.log(res.data)
  }
  复制代码
  ```

#### 2.4.3 axios 小结

* `axios()`返回的是一个 `Promise`实例；
* 可以使用 `async` 和 `await` 简化 `Promise`操作；
* 只要函数里面使用了 `await` ，则函数就必须被 `async` 修饰；
  * 是最近一级的函数使用 `async`；
* 发起 `GET` 请求，使用 `params` 配置项  **传递参数** ；
  * 参数是拼接在 **url地址** 上面；
* 发起 `POST` 请求，使用 `data` 配置项  **传递参数** ；
  * 参数在 **请求体** 中；
  * axios 默认发给 后台 请求体数据格式 是  **JSON字符串** ；

### 2.5 `$refs` 和 `$nextTick`

#### 2.5.1 `$refs`

##### 2.5.1.1 获取DOM

* 通过 **原生JS获取DOM的方法** 或 `ref属性` 获取  **原生DOM** ；
* 在 `mounted` 生命周期中 ➡ 有两种方式获取DOM：
  * 1. 目标标签 ➡ 添加id、class、…… 或 ref属性

    ```html
    <标签 ref="" id="" class=""></标签>
    复制代码
    ```
  * 2. 恰当时机，通过id、class 或 ref属性 获取目标元素：

    ```js
    // 挂载后（虚拟DOM变成真实DOM）
    mounted() {
      // 只要是原生JS获取DOM元素的方法都可以获取
      // document.querySelect/document.querySelectAll……
      console.log(document.getElementById('id名称'))
      console.log(this.$refs.ref属性值)
    }
    复制代码
    ```
* 注意：
  * **ref属性** 不展示 在 标签上；
  * 可以在Vue调式面板（）看到：
* 示例展示：
  ```js
  <template>
      <div>
          <!-- 1.给要获取的标签添加ref属性，并设置相应的属性值 -->
          <h4 ref="diJia">迪迦奥特曼</h4>
      </div>
  </template>

  <script>
      export default {
          // 2.在恰当的实际，获取DOM元素
          mounted() {
              // 方法一：Web API方法
              // 方法二：this.$refs.ref属性值
              console.log(this.$refs.diJia);
              this.$refs.diJia.style.color = "red";
          },
      };
  </script>
  复制代码
  ```

##### 2.5.1.2 获取组件对象

* 实现步骤：
  * 1. 在 **组件标签** 上 **添加**  **ref属性** ；
  * 2. 在恰当时机（ **挂在后：`mounted`** ），获取组件对象；

    * 恰当时机 ➡ 虚拟DOM挂载到DOM树上，既 **虚拟DOM变成真实DOM** （mounted）；
    * 使用 `this.$refs.ref名称`；
* 总结：
  * 1. 作用：既可以获取原生DOM，也可以获取组件对象；
  * 2. 使用步骤（2步）：

    * 给需要获取的原生DOM或组件标签添加ref属性，并设置属性值；
    * 在恰当时机（挂在后：mounted）：同故宫 `this.$refs.ref属性值` 获取；
  * 3. 获取到的组件对象就是该组件中的this；

#### 2.5.2 Vue - 异步更新DOM

* **Vue更新DOM** 是 **异步** 的、并且是  **批量的** （减少重绘和回流）；
* Vue监测数据更新，开启一个DOM更新队列（异步任务）；
* 示例展示：

#### 2.5.3 `$nextTick`

* 等  **DOM更新后** ，**触发** 此方法里的  **函数执行** ；
* **语法：**
  ```js
  this.$nextTick(回调函数)
  复制代码
  ```
* 返回值：
  * `$nextTick()` 返回的是一个 `Promise`，可以配合 async 和 await 使用；
* 示例展示：
* 代码展示：
  ```js
  <template>
      <div>
          <h4>4.$nextTick应用场景</h4>
          <button @click="search" v-if="flag">点击搜索</button>
          <input ref="input" type="text" placeholder="请输入关键字" v-else />
      </div>
  </template>

  <script>
      export default {
          data() {
                  return {
                          flag: true,
                  };
          },
          methods: {
              async search() {
                  this.flag = false;
                  console.log(this.$refs.input); // undefined
                  // undefined 的原因
                  // data数据变化，需要更新DOM，Vue更新DOM是异步操作
                  // data数据更新完后，input还没有变成真实DOM

                  // 等待DOM更新完毕后，再去执行this.$nextTick回调函数的函数体
                  this.$nextTick(() => {
                      console.log(this.$refs.input);	// input标签
                      this.$refs.input.focus();
                  });

                  // $nextTick原地返回一个Promise实例，可以async和await简化Promise操作
                  // 这个Promise实例里面保存着异步操作的结果
                  // await可以得到这个异步操作的结果
                  // 也就是说，程序会停止到这一行，直到await等待到这个异步操作结果，才会去执行下面的代码
                  await this.$nextTick();
                  this.$refs.input.focus();
              },
          },
      };
  </script>
  复制代码
  ```

## 三、 组件进阶

### 3.1 动态组件（了解）

* Vue3已经废了，包括Vue2的过滤器也废掉了；
* 定义：**多个组件** 使用  **同一个挂载点** ，并  **动态切换** ；
* 使用场景：同一个挂载点 切换 不同组件 显示；
* 原理：`v-if + v-else-if（只用v-if也是可以的）`；
* 实现步骤：
  1. 设置 **数据属性** ，来承载要显示的组件名：
     * 注意点：
       * 数据属性属性值 === 组件名；
  2. 设置 `挂载点<component :is="Vue数据属性"></component>`，使用 `is` 来设置要  **显示哪个组件** ；
     * 注意点：
       * `<component>`是 Vue内置组件；
       * 使用 **v-bind** 给 **is属性**  **动态赋值** ；
  3. 根据需求， **切换数据属性值** ；

### 3.2 组件缓存

* 使用 `<component>` 频繁切换组件 存在的问题：
  * 使用 `<component>`内置组件频繁切换会导致 **组件一直在被创建和销毁** ， **性能低** （会将该组件所有的代码执行一遍），影响用户体验（如果有Ajax请求还会浪费用户流量）；
  * 解决方法：⬇
* **语法：**
  ```js
  <keep-alive>
      <component :is="Vue数据属性"></component>
  </keep-alive>
  复制代码
  ```
* 原理：
  * 使用Vue内置的 `keepAlive`组件，包裹要频繁切换的组件，将其 **缓存起来** ，在切换的时候，不会再去创建，直接从缓存中获取（纯属废话了，原理这块可以去问问[度娘](https://link.juejin.cn?target=https%3A%2F%2Fwww.baidu.com%2F "https://www.baidu.com/")，后面我会补充）；
* 组件缓存的好处：
  * 八平村组件的状态（数据不丢失）；
  * 提高性能（可以避免徐建切来切去发起无效请求）；
  * 提升用户体验；

### 3.3 组件激活和非激活

* 配合 `<keep-alive>` 使用：
  * 使用了该标签，activated钩子替换created，deactivated钩子替换activated钩子；
* 2个新的生命周期 **钩子函数** ：
  * **作用：** 知道 **缓存** 的 **组件** 是  **出现还是消失** ;
    * **activated** : **激活时 自动触发** （类似于created()钩子）;
    * **deactivated** :  **停用时 触发** （类似于destroyed()钩子）

### 3.4 组件插槽

* **作用：**
  * 通过 `slot` 标签，让 **组件内** 可以 **接收** **不同的标签结构** **显示**  **不同的效果** （使用 `slot`标签进行占位，要显示什么就直接插入到组件标签里面）；
  * 解决了组件内标签不确定的问题；
* [Vue2官网 - 插槽](https://link.juejin.cn?target=https%3A%2F%2Fv2.cn.vuejs.org%2Fv2%2Fguide%2Fcomponents-slots.html "https://v2.cn.vuejs.org/v2/guide/components-slots.html")；

#### 3.4.1 匿名插槽

* **步骤：** （先占位再分发）

1. **组件内** 标签不确定的地方使用 `<slot></slot>` 占位；
2. **使用组件** 时，在 **组件标签内 插入 要显的 标签**即可（插入的标签 替换 `slot`标签 ）；

* 原理：
  * 使用 **组件标签** **内的** **标签结构** **去替换**  **slot标签** ；
* **语法：**
  * 写法1：

    ```js
    // 父组件 - 直接传递标签结构，不写template标签
    <组件标签>
        要传递的标签结构
    </组件标签>

    // 子组件
    // 使用 slot 标签占位
    <slot></slot>
    复制代码
    ```
  * 写法2：

    ```js
    // 父组件 - 将传递的标签结构包裹在template中进行传递
    <组件标签>
        <template>
            要传递的标签结构
        </template>
    </组件标签>

    -------------------------

    // 子组件:
    // 使用 slot 标签占位
    <slot></slot>
    复制代码
    ```
  * 写法3：

    * `<slot>` 不加 `name属性`，**Vue** 会 自动添加 `name属性` 为 `default`；
    * `template标签` 的 `v-solt` 属性值就是 `default`；

    ```js
    // 父组件 - 将传递的标签结构包裹在template中，并给template添加 v-slot:default 进行传递
    <组件标签>
        // 不使用简写
        <template v-slot:default></template>

        // v-slot: 简写
        <template #default></template>
    </组件标签>

    -------------------------

    //子组件:
    <slot naem="default"></slot>
    复制代码
    ```
* 示例展示：
* 总结：
  * 对比上面展示的效果图，得知 三种写法之间存在 优先级；
  * 第三种写法的优先级最高；
  * 第一种写法和第二种写法一样；
  * 如果三种语法同时存在，只显示有 `v-slot:default` 或 `#default` 的结构；

#### 3.4.2 插槽默认内容

* `<slot>`内放置内容，作为插槽的默认内容；

  * `<slot>`中夹着的所有标签或文字都属于默认内容；

  ```html
      <slot> 默认内容（可以是任意的） </slot>
  复制代码
  ```
* 在组件标签内传递了标签结构，就显示传递的；否则就显示默认的；

#### 3.4.3 具名插槽

* **使用步骤：**
  * 1️⃣ 在 **子组件内** 使用 **slot标签** **占位** 并 **添加**  **name属性** （属性值自定义）；
  * 2️⃣ 在  **父组件内** ，**组件标签**下， 用 **template标签** 包裹  **要传递的标签结构** ，并给 **template标签** 添加  **v-slot指令** （**指令的属性值** ===  **slot标签的name属性值** ）标明要替换的slot标签（**slot标签** 与 **template标签** 是 **相对应**的）；
    * `template`：Vue内置的组件，只起包裹作用，不会渲染到页面上；
* 注意：
  * **`v-slot`语法：** `v-slot:slot标签的name属性值`；
    * `v-slot: === # （简写）`
  * `slot标签的name属性值 === templte标签v-slot指令的值`；
  * 任何没有被包裹在带有 `v-slot` 的 `<template>` 中的内容都会被视为默认插槽的内容；
* 示例展示：
  ![image-20220929190518513.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/63ec2098c8bb4d5e8a76a2de2c0b2998~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  ![image.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1332a014dd954cfd95082d1dfcf866cc~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

#### 3.4.4 作用域插槽

* **使用步骤：**
  * 1️⃣ 子组件内，在 **`<slot>`标签** 上 **绑定自定义属性** 并  **设置属性值** ；
    * 属性名：自定义；
    * 属性值：Vue数据属性（data中的数据）；
    * 绑定的值：
      * 字符串：不需要使用指令；
      * 变量：要使用 `v-bind` 指令（使用简写）；
  * 2️⃣ 父组件内，先导入，再使用该组件，传入自定义标签结构，用 **template标签包裹要传入的内容** 并 **设置**  **v-slot = "子组件内slot上的自定义变量名"** ；
  * 3️⃣ **自定义变量名** **自动绑定** **slot标签** 上  **所有属性 和 值** ；
* 🔺 注意两种写法的区别：
  * `v-slot:子组件内slot标签name属性值` ➡  **具名插槽** ；
  * `v-slot:子组件内slot标签name属性值="子组件内slot标签的自定义变量名"` ➡  **作用域插槽** ；
    * 对上面的简写：`#子组件内slot标签name属性值="子组件内slot标签的自定义变量名"`
* 作用域插槽：
  * 带数据的插槽，带的是子组件内部的数据，带给父组件，父组件接收到子组件的数据，并且可以通过插槽回传给子组件；
* 作用域插槽是插槽的终极版：
  * 普通插槽只能做到给子组件分发内容，不能获取子组件的数据；
  * 作用域插槽不仅可以给子组件分发内容还能拿到子组件的数据，再回传给子组件；
* 使用场景：
  * 插槽内容能够访问子组件内的数据；
* 示例展示：
  * 作用域插槽是根据匿名插槽或具名插槽演变而来；
    ![image.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ee935a37596f4c9eac5eafc7da39b722~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 作用域插槽细节：
  * `v-slot:名字`： **名字** 需要与 **slot标签的name属性值**  **保持一致** ；
  * `v-slot:` ➡ 可以简写成 **#**
  * 因为接收到的数据是对象，为了今后使用方便，可以在原地进行解构；

#### 3.4.5 插槽总结

* 作用：解决组件内标签内容不确定的问题；
* 分类：（3类）
  * 1️⃣ 匿名 / 默认插槽：
    * 作用：解决 组件内 只有一处 标签不确定 的 问题；
    * 使用步骤：先占位 + 再分发内容（只分发内容，有三种写法）：
      * 写法1：不写 `template`；
      * 写法2：写 `template`，不写 `#`；
      * 写法3：写 `template + #default`；
  * 2️⃣ 具名插槽：
    * 作用：解决 组件内 有 两处及以上 标签不确定 的 问题；
    * 使用步骤：
      * 先占位并添加name属性 + 分发内容并添加 `#slot的name属性值`；
  * 3️⃣ 作用域插槽：
    * 作用：标签不确定的问题 + 插槽内容使用子组件数据；
    * 使用步骤：
      * 传数据（slot上自定义属性名，属性值为传递的数据） + 接数据（template #slot的name属性值 = "变量名"）；
      * 使用的时候，可以对对象进行解构。

### 3.5 自定义指令

* 对 **普通DOM元素** 进行  **原生操作** ：

  * ref；
  * 自定义指令；
* 关于自定义指令的参数，请移步[Vue2官网 - 自定义指令](https://link.juejin.cn?target=https%3A%2F%2Fv2.cn.vuejs.org%2Fv2%2Fguide%2Fcustom-directive.html "https://v2.cn.vuejs.org/v2/guide/custom-directive.html")；
* 全局注册：

  * 目标文件：`main.js`；

  ```js
  // 所有的 Vue. 都要写在 new Vue 前面
  // 定义 的时候 不加v-，使用 的时候必须 加v-
  Vue.directive('指令名', {
    // inserted：指令的钩子函数之一，表示指令所在DOM元素插入到真实DOM中自动 触发一次
    // el：当前指令所在的DOM元素
    inserted (el) { 
      // 可以对el标签扩展额外功能
    }
  })

  ------------

  // 携带数据
  Vue.directive ('指令名', {
    // binding：记录了指令相关信息的对象
    // 就关心binding.value这个属性值，里面记录的使指令后面表达式的值
    inserted (el, binding) {
    }
  })

  --------------

  // update()也是指令钩子函数之一，表示指令所在组件数据变了的时候，每次都会执行一次
  Vue.directive ('指令名', {
    inserted (el, binding) { 相同代码 },
    undate (el, binding) { 相同代码 }
  })
  复制代码
  ```
* 局部注册：

  * 目标文件：某个 `.vue`文件；

  ```js
  export dafault {
      directives: {
          focus: {
              // 指令的定义
              inserted: function (el) {
                  el.focus()
              }
          }
      }
  }
  复制代码
  ```
* 自定义指令的使用：

  * 全局注册：
    * 该项目中任何 `.vue` 文件都是可以使用；
  * 局部注册：
    * 只能在当前 `.vue` 文件中使用；
* 🔺 注意：

  * 定义 的时候 不加 `v-`，使用 的时候必须 加 `v-`；
* 代码展示：

  * 全局定义：
    ![image-20220930160541863.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/52cf8b3856624707b27e650d367318fc~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 使用：
    ![image-20220930160720953.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1502ea9f1b5844c9b07c66329b406836~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 传数据并且可以修改数据：
    ![image-20220930162853129.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/efd8abd4f26c4ac98f987f941773439c~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

# 贰、VueRouter

## 1.1 路由简介

* 前端路由：**路径** 和 **组件** 的  **映射关系** （一一对应）；
* SPA：单页面应用程序
  * 基于前端路由技术；
  * 优点：
    * 局部刷新，用户体验更好；
    * 数据传递容易，开发效率高；
  * 缺点：
    * 开发成本高；
    * 首次加载会比较慢，不利于SEO优化；

## 1.2 vue-router 使用步骤

* 以下步骤是基于 默认模式 创建Vue2项目的；
* 1️⃣ 下包：
  * `yarn add vue-router@3.5.3`
  * 下载太高版本的VueRoute会报错的；
* 2️⃣ `main.js` 导包：
  * `import VueRouter from 'vue-router`
* 3️⃣ 注册：
  * 使用 `Vue.use(VueRouter)` 进行注册；
  * 一旦注册了路由插件，会给Vue提供两个全局组件和两个全局对象；
  * 全局组件：`RouterLink` 和 `RouterView`；
  * 全局对象：`$router` 和 `$route`；
* 4️⃣ 创建路由规则数组：
  * 属性名是固定的；
  * 配置 path 和 component 的关系；
  * Vue中路由指的是：
    * 路径（path） 和 组件（component） 的映射关系（不同的路径对应不同的组件）;
* 5️⃣ 创建路由实例：
  * `new VueRouter()`;
* 6️⃣ 注入：
  * 在 `new Vue()` 里面注入；
* 7️⃣ 给出口
* 路由的运作原理：
  * 当 浏览器url地址 发生改变 的时候，在 `router` 数组中 从上往下 查找 对应的 `path`，如果 命中（全等）了，则用 `path` 对应的 `component` 替换掉 `<router-view />` 的位置。
* 示例展示：
  ```js
  import Vue from 'vue'
  import App from './App.vue'

  Vue.config.productionTip = false

  // 1.下载包：yarn add vue-router -S

  // 2.导入vue-router包
  import VueRouter from 'vue-router'

  // 3.注册
  // vue.use(插入对象)：给Vue增强能力
  // 一旦注册了路由了插件，会给Vue提供两个全局组件和两个全局对象
  // 全局组件：RouterLink 和 RouterView
  // 全局对象：$route 和 $router
  Vue.use(VueRouter)

  // 4.创建路由规则数组
  // 配置path和component的对应关系
  // Vue中路由是指：
  // 路径（path）和组件（component）的映射关系（不同路径对应不同的组件）
  // 导入三个页面组件
  import Find from './views/Find.vue'
  import My from './views/My.vue'
  import Friend from './views/Friend.vue'
  import NotFind from './views/NotFind.vue'
  const routes = [
    {
      path: '/',
      // 重定向
      redirect: '/find'
    },
    {
      // 属性名是固定写法
      path: '/find',    // /标识根路径，不能丢
      component: Find   // 页面组件
    },
    {
      path: '/my',
      component: My
    },
    {
      path: '/friend',
      component: Friend
    },
    {
      path: '*',
      // component: NotFind,
      // 路由懒加载
      component: () => import('./views/NotFind.vue')
    }
  ]

  // 5.创建路由实例
  const router = new VueRouter({
    // 属性名固定：routes
    routes,
    // 自配置激活类名
    linkActiveClass: 'on-active',
    linkExactActiveClass: 'off-active'
  })

  new Vue({
    // 6.注入
    // 属性名固定：router
    router,
    render: h => h(App),
  }).$mount('#app')

  // 7.给出口 回到App.vue中 给一级路由添加出口
  // <router-view></router-view>（标签名也可以写成大驼峰命名法）
  复制代码
  ```

## 1.3 声明式导航

* `router-link`：`VueRouter`在全局注册的组件，本质就是 `a`标签；
* `router-link`怎么使用？
  * 当标签使用：
    * 必须传入 `to`属性，**属性值** 必须和 **路由规则数组** 的 **path属性值**  **一致** ；
    * `<router-link to="路由规则数组中component对应的path属性值"> 给用户展示的内容 </router-link>`；
    * ![image-20221006174929446.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9548d823cee34bacbf485cef3f8cfe55~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* `router-link`的好处？
  * 自带激活时的类名，可以做高亮效果；
  * ![image-20221006174502269.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/056283f20a2b4b92869295a98327f986~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 配置激活类型（类名自定义）：
    ![image-20221006175952443.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/bd08e7e9efdc4f338230dd46ddb2b531~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.4 重定向和路由模式

### 1.4.1 Vue路由重定向

* 匹配 `path`后，**强制跳转**  **path路径** ；
* 如何检测默认路由？
  * 规则里定义  **`path: '/'`** ；
* 如何重定向路由路径？
  * `redirect `配置项，属性值为要强制切换的路由路径；
    ![image-20221006180731473.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/86b892a7e5904a8aaaa0055d23cbb35b~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 说明：当检测到浏览器的url地址是 `/`的时候，就跳转到 `Find` 组件；

### 1.4.2 Vue路由配置404页面

* 放在 **路由规则数组**  **最后面** ，`path` **从上往下** 匹配，如果前面没有匹配上的 `path`就命中最后这个 `*` （任意路径都能访问该路径对应的组件）；
* 配置步骤：
  * 1️⃣ 先准备一个 `404` 组件；
  * 2️⃣ 在路由规则数组的最后面准备一个配置项；
  * 3️⃣ `path` 为 `*`（如果把该配置项放在前面，那显示的页面全部都是404了😂）；
  * 4️⃣ ![image-20221006181546608.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/265b115b667f4479b805df2f08e8825a~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.4.3 路由模式

* 路由两种有两种模式：
  * `hash`：
    * **url** 有 **#** 号；
  * `history`：
    * **url** 没有 **#** 号；
* 切换路由模式：
  * 在 **创建路由实例** 的 **配置项** 中，有  **mode属性** ，可以配置路由模式，属性值就是 `hash` 和 `history`；
* 示例展示：
  ![image-20221007085129608.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b14957c8c86e415c93d83a47071d9793~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.5 声明式导航传参

* 在跳转路由时，可以给对应的组件内传值；
* **声明式导航** -  **`router-link`** ;

### 1.5.1 查询字符串传参（参数出现在浏览器url地址上）

* **查询字符串** ，**接值** 的时候，看  **`query`** ；

#### 1.5.1.1 字符串拼接传参

* 跳转的时候传值：
  * 在 `<router-link>`标签的 `to`属性上进行拼接；
  * **语法：**
    ```JS
    <router-link to="/path?参数名1=值1&参数名2=值2&……">
        xxx
    </router-link>
    复制代码
    ```
* 在对应的组件接收值：
  * 在标签中写不需要 `this`，在js中书写还是需要的；
  * `this.$route.query`；
  * ![image-20221007090806358.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ea79fec89dc54953a94f48e797a010a4~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * ![image-20221007091106283.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0ff4171ce6d44efaab01d3f2d669b933~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

#### 1.5.1.2 配置对象传参

* 传参语法：
  ```js
  <router-link :to="{
                path: '和规则数组的path属性一样',
                query: {
                    属性名: 属性值,
                    // 键值对形式
                }
            }">
        给用户展示的内容
  </router-link>
  复制代码
  ```
* 接值语法：
  ![image-20221007092142413.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2068fedd70374813ac91682e6868eead~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  ![image-20221007091944125.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f7b421fddbbd4a8f972f162b624d2488~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.5.2 动态路由传参

* **动态路由传参** ，**接收值** 的时候，看  **params** ；
* **先占位** （ **改造规则数组** ）， **再传值** （有几个占位的参数名，就产几个参数值）， **最后接值** （`$route.params.参数名`）；
* 步骤：
  * 1️⃣ 需要先对规则数组将对应的path进行改造：
    ![image-20221007094934547.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/248a8cf263054b47b333604a4226f17a~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 2️⃣ 传参：

    1. `to` **属性值** 为  **字符串** ：

       ```js
       <router-link
           to="/path/参数1的值/参数的值/……"
       >
           // 要展示的内容
       </router-link>
       复制代码
       ```

       * 示例展示：
         ![image-20221007095042809.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ade02245962a461a8ac8e9b15bb22eb7~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
    2. `to` **属性值** 为  **配置对象** ：

       * 示例展示：
         ![image-20221007101053900.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5ec43d60693e4e05adadc54306695969~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
       * **注意：**
         * 动态路由不能通过 `path + params` 配合，因为 `path`会忽略 `params`;
           * 这里的 `path`指的是，`RouterLink`的to属性的属性值中不能出现 `path`；
         * 正确方式：`name(需要给对应的路由取名，也叫命名路由) + params`
         * 虽然写的是对象，但是最终会转为字符串；
  * 3️⃣ 接值：

    ```js
    // 标签中
    $route.params.参数名

    // js中
    this.$route.params.参数名
    复制代码
    ```

    * 示例展示：
      ![image-20221007094725130.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/028fbc92f7564fcd83c1079d51a8523e~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.6 编程式导航

* `$route` 和 `$router` 区别：
  * `$route`： **获取路由参数** ，表示当前激活的路由对象；
  * `$router`： **实现路由跳转** ，`$router.push()`实现跳转；
* 挂载后（使用 `mounted`钩子函数），延迟n秒，自动跳转到指定页面（主要是路由跳转和传参的语法展示）：
  ```js
  mounted() {
      setTimeout(() => {
          // 1.push(字符串)
          this.$router.push('/path')

          // 2.push(对象)
          this.$router.push({
              path: '/path'
          })

          // 3.查询字符串参
          // 字符串
          this.$router.push('/path?属性名1=属性值1&属性名2=属性值2&……')
          // 对象
          this.$router.push({
              path: '/path',
                  query: {
                  属性名1: 属性值1,
                  属性名2: 属性值2,
                  ……
              }
          })

          // 4.传动态路由参
          // 改造规则数组
          {
              path: '/path/:属性名1/:属性名2……',
          }
          // 字符串
          this.$router.push('/path/属性值1/属性值2……')
          // 对象
          // 改造规则数组
          {
              path: '/path/:属性名1/:属性名2……',
              name: '对应component'
          }
          this.$router.push({
              name: '规则数组name属性值',
              params: {
                  属性名1: 属性值1,
                  属性名2: 属性值2,
                  ……
              }
          })
      }, n);
  }
  复制代码
  ```
* 示例展示：
  ![image-20221007110314058.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8a4bd09b8f20421f888a70679709c751~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  ![image-20221007110701091.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9d942b3fc2bd4cb3a20d22483b1cacde~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.7 路由嵌套

* 二级路由如何配置：
  * 创建需要的二级路由组件；
  * 在路由规则数组里面对应的对象里面新增 `children` 属性（ **属性值 = 数组对象** ），在里面配置二级路由规则对象（`path + component`）；
  * 对应第一季页面设置给出口，显示二级路由页面；
* 二级路由主要注意什么？
  * **二级路由** **`path`** 一般  **不写根路径** （ **`/`** ）；
* 示例展示：
  * 在views里创建对应二级页面组件并导入到main.js中；
    ![image-20221007113655467.png](https://p1-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d2d2a2b948d94c51a62bf714f919fc47~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 在对应的规则数组里面，新增children属性，在里面配置二级路由规则对象；
    ![image-20221007115908224.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/beeeff4377bf44049c68d4174f81767e~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 在对应一级组件里面设置出口以及router-link的to属性；
    ![image-20221007114151001.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/196a8ab2b05547b7a39b1b3ce243bba6~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.8 导航守卫

* 只有常用的：
  * 全局前置守卫：
    * 任何一个路由在跳转之前都必须经过这个函数；
  * 全局后置守卫；
* 还有一些别的守卫，可以移步[Vue Router官网](https://link.juejin.cn?target=https%3A%2F%2Frouter.vuejs.org%2Fzh%2Fguide%2Fadvanced%2Fnavigation-guards.html "https://router.vuejs.org/zh/guide/advanced/navigation-guards.html")：
  * 全局解析守卫；
  * 路由独享守卫；
  * 组件内的守卫：

```js
import Vue from 'vue'
// import Router from 'vue-router'
import VueRouter from 'vue-router'

// 路由规则数组
const routes = []

/**
* 导入 vue-router 的时候，起的变量名是啥，就是new 啥
* const router = new Router({})
**/

const router = new VueRouter({
    // 最基本的有个routes
    // 属性名和属性值一样，可以简写
    routes
})

// 路由全局前置守卫
router.beforeEach((to, from, next) => {
    /**
     * to = 到哪里去
     * from = 从哪里来
     * next = 是前置守卫必须执行的钩子，next必须执行，如果不执行，页面就死了
     * next() - 通过，放行
     * next(false) - 跳转终止
     * next(地址) - 跳转到某个页面
     */
   
    // 相关逻辑
})

// 路由全局后置守卫
router.afterEach()
复制代码
```

* 今后在项目中判断 `token`（令牌） 是否为空字符串，可知道用户是否登录；
* 示例展示：
  ![image-20221007122333947.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c734e1cc12e1442bb888d9843a3ea61b~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * `next`函数传值：
    * `false`，停留在原地；
    * 不传之表示正常放行
      ![image-20221007121839074.png](https://p9-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/8e2b425513c14856916403889970f5fa~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

## 1.9 路由元信息

* 通过 `meta` 字段可以给路由添加更多信息；
* 取值：
  ```js
  this.$route.meta.参数名
  复制代码
  ```
* ![image-20221008091557585.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f821cc09155641cc926afa7e215445ce~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* ![image-20221008091627536.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d87f9f5b5a25448a95fe6dad408e528e~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

# 叁、VueX

* 在项目中，都是使用的是模块化，Vuex基础只是方便掌握基础；

## 一、 Vue基础

### 1.1 初始化功能

* 目标文件：`main.js`
  ![image-20221015110248791.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4836f46a4eae4a1a8ada04bc129bed0b~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.2 state

* 共享状态数据；
* 组件中如何获取数据？
  * 方式一：

    * 组件中可以使用 **`this.$store`** 可以获取到Vuex中的 **`store实例`** ，可以通过 **`state属性`** 获取数据；

    ```js
    this.$store,state.数据属性名
    复制代码
    ```
  * 方式二：

    * 辅助函数 - `mapState`；
    * `mapState`需要解构在计算属性 `(computed)`中；

    ```js
    <script>
        // 1.导入辅助函数
        // mapState是辅助函数，帮我们把store实例对象中的数据映射到 组件的计算属性中，它属于一种方便的用法
        import { mapState } from 'vuex';

        export default {
            // 2.利用扩展运算符将导出的状态映射给计算属性
            computed: {
                // 1.data数据属性无重名 - 数组传参 - 原样映射
                ...mapState(['state中的数据属性名1', 'state中的数据属性名2', …]);

                // 2.data数据属性有重名 - 对象传参 - 别名映射
                ...mapState({
                    新属性名: '旧属性名'
                });
            }
            // 3.使用的时候，按照计算属性使用方法使用即可
        }
    </script> 
    复制代码
    ```
  * 示例展示：
    ![image-20221015131757284.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9cc0659366df4d5eb8cb9b69f180f35b~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.3 mutations

* **`mutations`** 中 **只能写**  **同步代码** ；
* 同步修改 `state` 的数据；
* `state`数据的修改只能通过 `mutations`，并且 `mutations`必须是同步更新，目的是形成 **数据快照** ；

  * **数据快照：** 一次 `mutations`的执行，立即得到一种视图状态，因为是立刻，所以必须是同步的；
* **语法：**

  * 格式说明：mutations是一个对象。对象中存放着修改state的方法；

  ```js
  mutations: {
      // 方法里的参数：
      // 第一参数：satte => 当前store实例中state的属性名（该参数是固定参数）
      // 第二个参数： payload => 载荷、运输参数，调用mutations的时候，可以传递参数、传递荷载

      方法名 (state, payload) {
           // 函数体
      }
  }
  复制代码
  ```
* 如何在组件中使用 `mutations`？

  * 方式一：原始方法；

    ```js
    methods: {
        fn (payload) {
            // 使用mutations提供的commit方法，将入参提交给mutations中对应的方法使用
            this.$route.commit('mutations方法名', payload)
        }
    }
    复制代码
    ```

    * 示例展示：
      ![image-20221015134237004.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c91e844fe6ad46a1ae1f51015057d656~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
  * 方式二：辅助函数；

    ```js
    // 1.导入辅助函数
    import { mapMutations } from 'vuex';

    methods: {
        // payload ➡ 传递的参数，对 state 进行修改

        // 不需要对 mutations 中的方法重命名
        ...mapMutations(['mutations中的方法名']);
        fn (payload) {
            this.mutations中的方法名(payload)
        }

        // 需要对 mutations 中的方法进行重命名
        ...mapMutations({
            新的方法名: 'mutations中的方法名'
        })
        fn (payload) {
            this.新的方法名(payload)
        }   
    }
    复制代码
    ```

    * 示例展示：
      ![image-20221015134829298.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d7534b08250740d29618b7914cd73eb0~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
      ![image-20221015134822533.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d392b94e4fdb416c802a108766eddd90~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
      ![image-20221015134807330.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d2074db36a2e4c1b9b1998da32b2f3ff~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.4 actions

* `actions`中写异步代码；
* **`state`** 是 **存放数据** 、**`mutations`** 是  **同步更新数据** 、**`actions`** 则负责进行  **异步操作** ；
* **语法：**

  ```js
  actions: {
      // 获取异步的数据：context表示当前store的实例，可以通过 context.state 获取状态，也可以通过 context.commit 来提交mutations。也可以 context.dispatch调用其他的action
      方法名(context, payload) {
          context.commit('mutations的方法名', payload)
          // 函数体
    }
  }
  复制代码
  ```

  * 示例展示：
    ![image.png](https://p6-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5075dcc8e8114f91bf9ef5c5b34de4ce~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 使用方式：

  * 方式一：原始调用
    ```js
    this.$store.dispatch('actions的方法名', payload)
    复制代码
    ```
  * 方式二：辅助函数
    ```js
    import { mapActions } from 'vuex'

    // 解构在 methods 中
    methods: {
      ...mapActions('[actions的方法名]')
      // 直接通过 this.方法 就可以调用

      ...mapActions({
          新的方法名: 'actions中的方法名'
      })
    }
    复制代码
    ```
  * 示例展示：
    ![image-20221015154301872.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/74c1c7eed5f041cc80d1e070eff7ce00~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)

### 1.5 getters

* 可以理解成计算属性；
* 除了 `state`之外，有时我们还需要从 `state`中派生出一些状态，这些状态是依赖 `state`的，此时会用到 `getters`；
* **语法：**
  ```js
  getters: {
      // getters函数的第一个参数是state
      // 必须要有返回值
      return xxx
  }
  复制代码
  ```
* 使用方式：
  * **语法：**
    ```js
    import { mapGetters } from 'vuex'
    computed: {
        ...mapGetters(['getters中的方法名'])
    }
    复制代码
    ```
  * 示例展示：
    ![image-20221015161745226.png](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0939c12dd0394055897d0661a9ac1fe1~tplv-k3u1fbpfcp-zoom-in-crop-mark:1512:0:0:0.awebp?)
* 注意：
  * 解构 `getters` 必须在 `computed`中；

## 二、 模块化

### 2.1 模块化基本语法

* 在对应的模块中写
* 方式一：
  ```js
  const Module = {
      /**
      * 没有启用命名空间的时候，mutations是注册到全局的
      * 启用命名空间以后，mutations只注册到当前模块下
      * 
      */
      // 启用命名空间
      namespaced: true
      // 和没有模块化的不一样
      // 模块化的state是一个函数（可以是箭头函数）
      state(){
          return {}
      },
      mutations: {},
      actions: {},
      getters: {}
  }

  /**
  * 默认导出
  * 导出模块，目的是为了在其他文件中能够导入
  * 如果有import，那么一定会有export
  */
  export default Module
  复制代码
  ```
* 方式二：（我自己喜欢这种方式，比较简单）
  ```js
  const start = {
      // 存储数据
  }

  const mutations = {
      // 同步修改 state 的方法 或 其他逻辑
  }

  const actions = {
      // 异步修改 state 的方法 或 其他逻辑
  }

  export default {
      // 开启命名空间
      namespaced: true,
      state,
      mutations,
      actions
  }
  复制代码
  ```

### 2.2 state

* 需要从模块去映射，需要使用对象作为入参，使用key，value的形式;

```js
// 计算属性
computed: {
    ...mapState({
         /**
        * 这里需要从模块去映射，需要使用对象作为入参，使用key，value的形式
        * 新的变量名: (state) => state.模块名.该模块中对应的变量名
        * XianCount: (state) => state.Xian.count
        * ChengduCount: (state) => state.Chengdu.count,
        */
        // 解构 state
        XianCount: ({ Xian }) => Xian.count,
        ChengduCount: ({ Chengdu }) => Chengdu.count,
    }),
},
复制代码
```

### 2.3 mutations

* 没有模块化之前，`mutations` 是 注册到 全局的；
* 模块化之后，要实现每个模块的独立，需要使用命名空间 `（namespaced: true）`；
* 解构 `mapMutations`的时候，需要写对应的 `mutations`属于哪个模块；

```js
methods: {
    // 原样映射
    // ...mapMutations(["Xian/updateCount", "Chengdu/updateCount"]),
    // 别名映射
    ...mapMutations({
        // 新的方法名: '模块名/该模块中对应的方法名',
        updateXianCount: "Xian/updateCount",
        updateChengduCount: "Chengdu/updateCount",
    }),
    // 同步修改
    doDeliver(city, changedValue) {
        if (city === "xian") {
            /**
            * 模块化 mutations 的 原始写法
            * this.$store.commit("Xian/updateCount", changedValue);
            */

            // 原样映射 - 太丑太难看
            // this["Xian/updateCount"](changedValue);

            //别名映射
            this.updateXianCount(changedValue);
        } else {
            /**
            * 模块化 mutations 的 原始写法
            * this.$store.commit("Chengdu/updateCount", changedValue);
            */

            // 原样映射
            // this["Chengdu/updateCount"](changedValue);

            //别名映射
            this.updateChengduCount(changedValue);
        }
    }
},
复制代码
```

### 2.4 actions

* 解构mapActions的时候，需要写对应的actions属于哪个模块；

```js
methods: {
    // 异步修改
    ...mapActions({
        // 新的变量名: '模块名/模块名中对应的方法名',
        XianDeliver: "Xian/deliver",
        ChengduDeliver: "Chengdu/deliver",
    }),
  
    deliver(city, changedValue) {
        if (city === "xian") {
            /**
            * actions原始写法
            * this.$store.dispatch("Xian/deliver", changedValue);
            */

            // 原样映射
            this.XianDeliver(changedValue);
        } else {
            /**
            * actions原始写法
            * this.$store.dispatch("Chengdu/deliver", changedValue);
            */

            // 原样映射
            this.ChengduDeliver(changedValue);
        }
    }
}
复制代码
```

### 2.5 getters

* **getters：** 由于是对 **不同模块** 的 **`state`** 进行运算，所以要定义在 **根store实例** 中；
* 方式一：（这种方式更简单，更实用）

  * `@/store/getters.js`

  ```js
  const getters = {
      变量名: (state) => state.模块名.变量名,
      变量名: ({ 模块名 }) => 模块名.变量名,
      函数名(state, payload) {
          return 相关逻辑
      }
  }

  export default getters
  复制代码
  ```
* 方式二：（这种方式也要会）

  * `store/index.js`

  ```js
  import Vue from "vue";

  // 1.导入 vuex
  import Vuex from 'vuex'

  // 1.2 导出 Xian 和 Chengdu 两个模块
  import XianModule from '@/store/module/Xian'
  import ChengduModule from '@/store/module/Chengdu'

  import getters from '@/store/getters.js'

  // 2.注册 Vuex
  Vue.use(Vuex)

  // 3.实例化 Vuex.Store 对象
  const store = new Vuex.Store({
      state: {},
      mutations: {},
      actions: {},
      getters
  })

  // 4.默认导出
  export default store
  ```
