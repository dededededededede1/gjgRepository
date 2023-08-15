# js的常用方法

## 1、javaScript的几种选择器

​	在JavaScript中，主要有以下几种选择器来帮助你选取和操作HTML元素：

（1）通过ID选择器

`getElementById` 通过元素的ID来选取一个元素，这是一个常用的选择器。请注意，ID在一个HTML文档中应该是唯一的。

案例：

```
var element = document.getElementById("myElement");
```

（2）通过类名选择器

`getElementsByClassName` 通过元素的类名来选取一组元素，这个方法会返回一个类数组的对象，包含所有匹配的元素。

案例：

```
var elements = document.getElementsByClassName("myClass");
```

（3）通过标签名选择器

`getElementsByTagName` 通过元素的标签名来选取一组元素，类似于 `getElementsByClassName` 方法，它也会返回一个类数组的对象。

案例：

```
var elements = document.getElementsByTagName("p");
```

（4）通过CSS选择器

`querySelector` 和 `querySelectorAll` 可以使用CSS选择器来选取元素。`querySelector` 只会返回第一个匹配的元素，而 `querySelectorAll` 则会返回一个类数组的对象，包含所有匹配的元素。

案例：

```
// 选择第一个匹配的元素
var element = document.querySelector(".myClass");

// 选择所有匹配的元素
var elements = document.querySelectorAll(".myClass");
```

（5）通过属性选择器

属性选择器可以结合 `querySelector` 和 `querySelectorAll` 使用，通过指定属性名及其值来选取元素。

案例：

```
// 选择具有特定属性值的元素
var element = document.querySelector("[data-custom='value']");

// 选择具有特定属性的所有元素
var elements = document.querySelectorAll("[data-custom]");
```

这些选择器可以根据你的需求灵活选择，帮助你在JavaScript中更方便地操作HTML元素。

## 2、javaScript的onclick事件

`onclick` 事件是一个非常常用的JavaScript事件，当用户点击某个元素时触发。以下是使用 `onclick` 事件的一个简单示例：

假设我们有一个HTML按钮，我们希望在用户点击该按钮时执行某个操作。首先，在HTML中创建一个按钮元素：

```
<!DOCTYPE html>
<html>
<head>
  <title>JavaScript onclick Event 示例</title>
</head>
<body>
  <button id="myButton">点击我</button>
  <script src="script.js"></script>
</body>
</html>
```

接下来，我们在 `script.js` 文件中编写JavaScript代码以监听 `onclick` 事件：

```
// 获取按钮元素
var button = document.getElementById("myButton");

// 为按钮添加 onclick 事件监听器
button.onclick = function() {
  console.log("按钮被点击了");
};
```

或者，你也可以使用 `addEventListener()` 方法来监听 `click` 事件，这样可以为同一个元素添加多个事件监听器：

```
// 获取按钮元素
var button = document.getElementById("myButton");

// 使用 addEventListener() 为按钮添加 click 事件监听器
button.addEventListener("click", function() {
  console.log("按钮被点击了");
});
```

现在，当用户点击按钮时，控制台将输出 "按钮被点击了"。

这个简单的示例展示了如何使用 `onclick` 事件来监听用户的点击操作。你可以根据实际需求在事件处理函数中执行不同的操作，例如改变页面样式、显示隐藏的内容、发送请求等。

## 3、javaScript的onload事件

`onload` 事件是一个非常有用的JavaScript事件，当页面或元素（如`<img>`、`<script>`）完成加载时触发。以下是使用 `onload` 事件的两个示例：

**示例1：页面加载完成**

在这个示例中，我们将在页面加载完成后向控制台输出一条消息。首先，创建一个简单的HTML页面：

```
htmlCopy code<!DOCTYPE html>
<html>
<head>
  <title>JavaScript onload Event 示例1</title>
</head>
<body>
  <h1>欢迎访问我们的网站</h1>
  <script src="script1.js"></script>
</body>
</html>
```

接下来，在 `script1.js` 文件中编写JavaScript代码以监听 `onload` 事件：

```
javascriptCopy code// 为 window 对象添加 onload 事件监听器
window.onload = function() {
  console.log("页面加载完毕");
};
```

现在，当页面加载完成时，控制台将输出 "页面加载完毕"。

**示例2：图片加载完成**

在这个示例中，我们将在图片加载完成后显示一条消息。首先，在HTML中创建一个图片元素：

```
htmlCopy code<!DOCTYPE html>
<html>
<head>
  <title>JavaScript onload Event 示例2</title>
</head>
<body>
  <img id="myImage" src="example.jpg" alt="示例图片" />
  <script src="script2.js"></script>
</body>
</html>
```

接下来，在 `script2.js` 文件中编写JavaScript代码以监听图片的 `onload` 事件：

```
javascriptCopy code// 获取图片元素
var image = document.getElementById("myImage");

// 为图片添加 onload 事件监听器
image.onload = function() {
  console.log("图片加载完毕");
};
```

现在，当图片加载完成时，控制台将输出 "图片加载完毕"。

这些示例展示了如何使用 `onload` 事件来检测页面或元素加载完成。你可以根据实际需求在事件处理函数中执行不同的操作，例如在页面加载完成后初始化页面内容、在图片加载完成后执行动画等。

## 4、javaScript的onChange事件

`onChange` 事件是一个非常实用的JavaScript事件，当表单元素（如`<input>`、`<select>`或`<textarea>`）的值发生变化时触发。以下是使用 `onChange` 事件的一个示例：

假设我们有一个HTML输入框，我们希望在用户更改输入框的值时执行某个操作。首先，在HTML中创建一个输入框元素：

```
htmlCopy code<!DOCTYPE html>
<html>
<head>
  <title>JavaScript onChange Event 示例</title>
</head>
<body>
  <input type="text" id="myInput" placeholder="在这里输入文字" />
  <p id="displayText">输入框的值尚未更改。</p>
  <script src="script.js"></script>
</body>
</html>
```

接下来，在 `script.js` 文件中编写JavaScript代码以监听 `onChange` 事件：

```
javascriptCopy code// 获取输入框和显示文本的元素
var input = document.getElementById("myInput");
var displayText = document.getElementById("displayText");

// 为输入框添加 onChange 事件监听器
input.addEventListener("change", function() {
  console.log("输入框的值发生了变化");
  displayText.innerHTML = "输入框的值已更改为：" + input.value;
});
```

现在，当用户更改输入框的值并且失去焦点时（例如，通过点击页面上的其他地方或按下Tab键），控制台将输出 "输入框的值发生了变化"，同时显示文本将更新为显示当前输入框的值。

这个简单的示例展示了如何使用 `onChange` 事件来监听表单元素值的变化。你可以根据实际需求在事件处理函数中执行不同的操作，例如验证输入的有效性、实时过滤搜索结果等。

## 5、用javaScript的onblur事件编写一个数据校验

`onblur` 事件在元素失去焦点时触发，这使它成为执行数据校验的理想事件。以下是一个简单的示例，使用 `onblur` 事件对电子邮件输入框进行数据校验：

首先，在HTML中创建一个输入框元素和一个显示验证结果的元素：

```
htmlCopy code<!DOCTYPE html>
<html>
<head>
  <title>JavaScript onblur Event 示例</title>
</head>
<body>
  <input type="text" id="emailInput" placeholder="请输入电子邮件地址" />
  <p id="validationResult">请填写电子邮件地址。</p>
  <script src="script.js"></script>
</body>
</html>
```

接下来，在 `script.js` 文件中编写JavaScript代码以监听 `onblur` 事件并进行数据校验：

```
javascriptCopy code// 获取输入框和显示验证结果的元素
var emailInput = document.getElementById("emailInput");
var validationResult = document.getElementById("validationResult");

// 验证电子邮件地址的正则表达式
var emailRegex = /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,}$/;

// 为输入框添加 onblur 事件监听器
emailInput.addEventListener("blur", function() {
  // 使用正则表达式测试输入框的值
  if (emailRegex.test(emailInput.value)) {
    validationResult.innerHTML = "电子邮件地址有效。";
    validationResult.style.color = "green";
  } else {
    validationResult.innerHTML = "电子邮件地址无效，请重新输入。";
    validationResult.style.color = "red";
  }
});
```

现在，当输入框失去焦点时，将根据输入的电子邮件地址是否有效显示相应的验证结果。

这个简单的示例展示了如何使用 `onblur` 事件进行数据校验。你可以根据需要扩展此示例以支持其他类型的输入和验证规则。