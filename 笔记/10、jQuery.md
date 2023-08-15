# jQuery

jQuery 是一款流行的 JavaScript 库，致力于简化 HTML 文档遍历、操作、事件处理和动画。jQuery 的选择器使得在 DOM（文档对象模型）中查找和操作元素变得非常容易。

## 1、选择器

1. 基本选择器：
   - 标签选择器：`$('tagName')` 例：`$('p')` 选择所有的 `<p>` 元素。
   - 类选择器：`$('.className')` 例：`$('.highlight')` 选择所有具有 `highlight` 类的元素。
   - ID 选择器：`$('#elementId')` 例：`$('#main')` 选择具有 ID `main` 的元素。
2. 层次选择器：
   - 后代选择器：`$('ancestor descendant')` 例：`$('div p')` 选择所有 `<div>` 元素内部的 `<p>` 元素。
   - 子元素选择器：`$('parent > child')` 例：`$('ul > li')` 选择所有 `<ul>` 元素的直接子元素 `<li>`。
   - 相邻兄弟选择器：`$('prev + next')` 例：`$('h2 + p')` 选择紧邻在 `<h2>` 元素后的 `<p>` 元素。
   - 一般同胞选择器：`$('prev ~ siblings')` 例：`$('h2 ~ p')` 选择所有跟在 `<h2>` 元素后的同级 `<p>` 元素。
3. 属性选择器：
   - 属性存在选择器：`$('[attribute]')` 例：`$('[href]')` 选择所有具有 `href` 属性的元素。
   - 属性值选择器：`$('[attribute="value"]')` 例：`$('input[type="text"]')` 选择所有 `type` 属性值为 `text` 的 `<input>` 元素。
   - 属性值包含选择器：`$('[attribute*="value"]')` 例：`$('a[href*="google"]')` 选择所有 `href` 属性值中包含 "google" 的 `<a>` 元素。
4. 筛选选择器：
   - 第一个元素选择器：`$(':first')` 例：`$('p:first')` 选择第一个 `<p>` 元素。
   - 最后一个元素选择器：`$(':last')` 例：`$('p:last')` 选择最后一个 `<p>` 元素。
   - 索引选择器：`$(':eq(index)')` 例：`$('p:eq(2)')` 选择索引为 2（第三个）的 `<p>` 元素。
   - 奇数元素选择器：`$(':odd')` 例：`$('tr:odd')` 选择所有奇数索引的 `<tr>` 元素。
   - 偶数元素选择器：`$(':even')` 例：`$('tr:even')` 选择所有偶数索引的 `<

## 2、基本选择器，操作input元素

​	假设我们的 HTML 页面中有以下 input 元素：

```
htmlCopy code<input type="text" id="username" class="input-field" />
<input type="password" id="password" class="input-field" />
<input type="submit" id="submit-btn" value="Login" />
```

1. ### 使用 ID 选择器操作 input 元素：

   获取用户名输入框的值：

   ```
   javascriptCopy code
   var username = $('#username').val();
   ```

   为密码输入框设置一个值：

   ```
   javascriptCopy code
   $('#password').val('secretPassword');
   ```

2. ### 使用标签选择器操作 input 元素：

   获取所有 input 元素，并为它们添加一个 focus 事件：

   ```
   javascriptCopy code$('input').focus(function() {
     $(this).addClass('focused');
   });
   ```

   当任意 input 元素获得焦点时，会给它添加一个名为 `focused` 的类。

3. ### 使用类选择器操作 input 元素：

   获取 class 为 `input-field` 的 input 元素，并为它们设置一个边框样式：

   ```
   javascriptCopy code
   $('.input-field').css('border', '1px solid red');
   ```

4. ### 使用属性选择器操作 input 元素：

   为所有类型为 `text` 的 input 元素添加一个背景色：

   ```
   javascriptCopy code
   $('input[type="text"]').css('background-color', 'lightblue');
   ```

这些案例展示了如何使用 jQuery 的基本选择器操作 input 元素，包括获取和设置值、添加事件处理程序以及修改样式。在实际项目中，你可以根据需要灵活运用这些选择器来操作和定位 input 元素。

## 3、jQuery如何运用基本选择器，操作table元素

以下是一些使用 jQuery 基本选择器操作 table 元素的案例：

假设我们的 HTML 页面中有以下 table 元素：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
    <tr>
      <td>Charlie</td>
      <td>25</td>
      <td>Los Angeles</td>
    </tr>
  </tbody>
</table>
```

1. 使用 ID 选择器操作 table 元素：

   为整个表格设置一个边框：

   ```
   javascriptCopy code
   $('#example-table').css('border', '1px solid black');
   ```

2. 使用标签选择器操作 table 元素：

   为所有表头（th）元素设置背景颜色：

   ```
   javascriptCopy code
   $('th').css('background-color', 'lightgray');
   ```

   为表格中的所有单元格（td）添加一个内边距：

   ```
   javascriptCopy code
   $('td').css('padding', '5px');
   ```

3. 使用层次选择器操作 table 元素：

   为表格中的奇数行设置一个背景颜色：

   ```
   javascriptCopy code
   $('#example-table tr:odd').css('background-color', 'lightblue');
   ```

   为表格中的偶数行设置一个背景颜色：

   ```
   javascriptCopy code
   $('#example-table tr:even').css('background-color', 'white');
   ```

这些案例展示了如何使用 jQuery 的基本选择器操作 table 元素，包括修改样式和选择特定的行。在实际项目中，你可以根据需要灵活运用这些选择器来操作和定位 table 元素。

## 4、jquery ID选择器如何遍历table的td元素

要使用 jQuery 的 ID 选择器遍历 table 中的所有 td 元素，可以结合层次选择器来实现。以下是一个具体的例子：

假设我们的 HTML 页面中有以下 table 元素：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
    <tr>
      <td>Charlie</td>
      <td>25</td>
      <td>Los Angeles</td>
    </tr>
  </tbody>
</table>
```

接下来，我们使用 jQuery 的 ID 选择器和层次选择器遍历所有 td 元素：

```
javascriptCopy code$('#example-table td').each(function() {
  // 在这个函数中，'this' 是当前遍历到的 td 元素
  var currentTd = $(this);
  console.log('当前单元格的内容：', currentTd.text());
});
```

在这个例子中，我们首先使用 ID 选择器 `#example-table` 选中了具有 ID "example-table" 的表格。然后，我们使用层次选择器 `td` 选择了表格中的所有 td 元素。接下来，我们使用 `each` 函数来遍历这些 td 元素，并在每次遍历时执行一个匿名函数。

在这个匿名函数内部，`this` 是当前遍历到的 DOM 元素（td 元素）。我们用 `$()` 将其包装成一个 jQuery 对象，以便使用 jQuery 的方法。在本例中，我们使用 `text()` 方法获取了当前 td 元素的文本内容，并将其打印到控制台。

这样，我们就成功地使用 jQuery 的 ID 选择器和层次选择器遍历了 table 中的所有 td 元素。

## 5、jquery ID选择器如何遍历table的tr元素

要使用 jQuery 的 ID 选择器遍历 table 中的所有 tr 元素，可以结合层次选择器来实现。以下是一个具体的例子：

假设我们的 HTML 页面中有以下 table 元素：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
    <tr>
      <td>Charlie</td>
      <td>25</td>
      <td>Los Angeles</td>
    </tr>
  </tbody>
</table>
```

接下来，我们使用 jQuery 的 ID 选择器和层次选择器遍历所有 tr 元素：

```
javascriptCopy code$('#example-table tbody tr').each(function(index) {
  // 在这个函数中，'this' 是当前遍历到的 tr 元素
  var currentTr = $(this);
  console.log('第 ' + (index + 1) + ' 行：', currentTr);
});
```

在这个例子中，我们首先使用 ID 选择器 `#example-table` 选中了具有 ID "example-table" 的表格。然后，我们使用层次选择器 `tbody tr` 选择了表格 `<tbody>` 中的所有 tr 元素。这里我们特意只选择了 `<tbody>` 中的 tr 元素，以避免遍历到表头的 tr。接下来，我们使用 `each` 函数来遍历这些 tr 元素，并在每次遍历时执行一个匿名函数。

在这个匿名函数内部，`this` 是当前遍历到的 DOM 元素（tr 元素）。我们用 `$()` 将其包装成一个 jQuery 对象，以便使用 jQuery 的方法。在本例中，我们将遍历到的 tr 元素打印到控制台，并显示它们在表格中的行数。

这样，我们就成功地使用 jQuery 的 ID 选择器和层次选择器遍历了 table 中的所有 tr 元素。

## 6、jQuery如何操作table的多选框

在操作 table 中的多选框时，我们可以使用 jQuery 结合属性选择器和事件处理程序。以下是一个具体的例子：

假设我们的 HTML 页面中有以下 table 元素，其中包含多选框：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th><input type="checkbox" id="check-all" /></th>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td><input type="checkbox" class="row-checkbox" /></td>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td><input type="checkbox" class="row-checkbox" /></td>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
    <tr>
      <td><input type="checkbox" class="row-checkbox" /></td>
      <td>Charlie</td>
      <td>25</td>
      <td>Los Angeles</td>
    </tr>
  </tbody>
</table>
```

1. 为 "全选/全不选" 多选框添加事件处理程序：

```
javascriptCopy code$('#check-all').change(function() {
  var isChecked = $(this).prop('checked');
  $('.row-checkbox').prop('checked', isChecked);
});
```

在这个例子中，我们使用 ID 选择器 `#check-all` 选中了 "全选/全不选" 多选框，并为其添加了一个 `change` 事件处理程序。当这个多选框的状态改变时，事件处理程序会执行。我们使用 `prop()` 函数获取当前多选框的 `checked` 属性值（选中为 `true`，未选中为 `false`），然后将该值应用到所有具有 class `row-checkbox` 的多选框上，实现全选或全不选的功能。

1. 获取选中的多选框：

```
javascriptCopy codefunction getSelectedRows() {
  var selectedRows = [];
  $('.row-checkbox:checked').each(function() {
    var currentTr = $(this).closest('tr');
    selectedRows.push(currentTr);
  });
  return selectedRows;
}
```

在这个函数中，我们使用类选择器和属性选择器 `.row-checkbox:checked` 选中了所有处于选中状态的多选框。接着，我们使用 `each` 函数遍历这些选中的多选框，并在每次遍历时执行一个匿名函数。在这个匿名函数内部，我们使用 `closest()` 函数获取当前多选框所在的 `tr` 元素，并将这些 tr 元素添加到 `selectedRows` 数组中。最后，函数返回 `selectedRows` 数组，其中包含所有选中的行。

使用这个函数，你可以轻松地获取选中的多选框所在的行，以便进行进一步的操作，如删除选中的行或获取选中行的数据等。

## 7、jQuery如何删除table  thead之外的元素

要使用 jQuery 删除 table 中 thead 之外的元素，我们可以先选中 tbody，然后删除其中的内容。以下是一个具体的例子：

假设我们的 HTML 页面中有以下 table 元素：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
    <tr>
      <td>Charlie</td>
      <td>25</td>
      <td>Los Angeles</td>
    </tr>
  </tbody>
</table>
```

使用以下 jQuery 代码来删除 thead 之外的元素：

```
javascriptCopy code
$('#example-table tbody').empty();
```

在这个例子中，我们首先使用 ID 选择器 `#example-table` 选中了具有 ID "example-table" 的表格。然后，我们使用层次选择器 `tbody` 选中了表格中的 tbody 元素。接下来，我们使用 `empty()` 方法将 tbody 的内容清空，从而实现删除 thead 之外的元素的目标。

注意：这里我们使用 `empty()` 而不是 `remove()`，因为 `remove()` 会将整个 tbody 元素一起删除，而我们只想删除 tbody 中的内容。

## 8、jQuery如何为table添加一行

要使用 jQuery 为 table 添加一行，你可以创建一个新的 tr 元素，并将其追加到 tbody 中。以下是一个具体的例子：

假设我们的 HTML 页面中有以下 table 元素：

```
htmlCopy code<table id="example-table">
  <thead>
    <tr>
      <th>Name</th>
      <th>Age</th>
      <th>City</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <td>Alice</td>
      <td>28</td>
      <td>New York</td>
    </tr>
    <tr>
      <td>Bob</td>
      <td>32</td>
      <td>San Francisco</td>
    </tr>
  </tbody>
</table>
```

接下来，我们使用 jQuery 代码为 table 添加一行：

```
javascriptCopy codefunction addRow(name, age, city) {
  var newRow = $('<tr>')
    .append($('<td>').text(name))
    .append($('<td>').text(age))
    .append($('<td>').text(city));
  
  $('#example-table tbody').append(newRow);
}

addRow('Charlie', 25, 'Los Angeles');
```

在这个例子中，我们创建了一个名为 `addRow` 的函数，接受三个参数：`name`、`age` 和 `city`。函数内部，我们首先使用 `$('<tr>')` 创建一个新的 tr 元素，并将其赋值给 `newRow` 变量。然后，我们使用 `append()` 方法向这个新 tr 元素中添加三个 td 元素，分别包含姓名、年龄和城市信息。最后，我们将这个新的 tr 元素追加到表格的 tbody 中。

通过调用 `addRow` 函数并传入相应的参数，我们可以为表格动态添加一行。在这个例子中，我们添加了一个名为 Charlie、年龄 25 岁、来自洛杉矶的新行。