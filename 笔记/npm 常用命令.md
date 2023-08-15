

## npm常用命令详解

- `npm`（Node Package Manager）是Node.js的包管理器，用于安装、更新和管理Node.js模块。以下是一些常用的npm命令和示例： 
1. `npm init`: 初始化一个新的Node.js项目，创建`package.json`文件。`package.json`是一个描述项目信息和依赖关系的文件。

示例：

```bash

mkdir my_project
cd my_project
npm init
```
2. `npm install <package_name>`: 安装一个Node.js模块。这将把模块安装到`node_modules`目录下，并添加到`package.json`的依赖项中。

示例：

```

npm install express
```
3. `npm install`: 安装`package.json`中列出的所有依赖项。

示例：

```bash

cd my_project
npm install
```
4. `npm install -g <package_name>`: 全局安装一个Node.js模块。这使得模块可以在任何地方使用。

示例：

```
npm install -g nodemon
```
5. `npm uninstall <package_name>`: 卸载一个已安装的Node.js模块。

示例：

```
npm uninstall express
```
6. `npm update`: 更新所有已安装的模块到最新版本。

示例：

```bash

cd my_project
npm update
```
7. `npm outdated`: 列出所有过时的已安装模块。

示例：

```bash

cd my_project
npm outdated
```
8. `npm list`: 列出项目中安装的所有模块。

示例：

```bash

cd my_project
npm list
```
9. `npm run <script>`: 运行`package.json`中的一个脚本。你可以在`scripts`字段中定义脚本。

示例：

```arduino

// package.json中的"scripts"字段
"scripts": {
  "start": "node app.js",
  "test": "mocha"
}

// 运行脚本
npm run start
npm run test
```

这些命令是Node.js开发中最常用的一些npm命令。了解这些命令可以帮助你更高效地管理和使用Node.js模块。

- 除了上述常用的npm命令之外，这里有一些其他实用的npm命令： 
1. `npm install --save-dev <package_name>`: 以开发依赖的形式安装一个Node.js模块。这些依赖项仅在开发和测试时需要，而在生产环境中不需要。

示例：

```css

npm install --save-dev mocha
```
2. `npm config`: 用于查看和修改npm的配置。可以通过`npm config list`查看当前配置，通过`npm config set <key> <value>`设置配置，通过`npm config delete <key>`删除配置。

示例：

```arduino

npm config list
npm config set registry https://registry.npm.taobao.org
npm config delete registry
```
3. `npm link`: 创建一个当前模块的符号链接。这在本地开发和测试模块时非常有用，因为它允许您在不发布模块的情况下，在其他项目中使用它。

示例：

```bash

cd my_module
npm link
cd ../my_project
npm link my_module
```
4. `npm search <keyword>`: 搜索与关键词相关的Node.js模块。

示例：

```sql

npm search express
```
5. `npm view <package_name> [field]`: 查看一个Node.js模块的详细信息。可以通过指定字段来查看特定信息。

示例：

```sql

npm view express
npm view express version
```
6. `npm cache`: 管理npm的缓存。可以通过`npm cache clean --force`清空缓存，通过`npm cache verify`验证缓存的完整性。

示例：

```css

npm cache clean --force
npm cache verify
```
7. `npm login`和`npm logout`: 登录和登出npm。登录后，您可以发布自己的模块或修改已发布模块的信息。

示例：

```bash

npm login
npm logout
```
8. `npm publish`和`npm unpublish`: 发布和取消发布Node.js模块。这需要您已经登录npm。

示例：

```bash

cd my_module
npm publish
npm unpublish my_module@1.0.0
```

了解和掌握这些npm命令可以帮助您更好地管理Node.js项目和模块。在实际开发中，您可能会根据需求使用这些命令来完成各种任务。


## npm install的常见参数

- `npm install`命令有很多可选参数，可以用来修改安装行为。以下是一些常用的参数及其解释和示例： 
1. `-g` 或 `--global`：全局安装模块。将模块安装到全局目录，使其在任何地方都可以使用。

示例：

```

npm install -g express
```



安装`express`模块到全局目录，之后可以在任何项目中使用它。 
2. `--save` 或 `-S`：将模块保存为项目的依赖。这是默认行为，不需要显式添加该参数。

示例：

```

npm install lodash
```



将`lodash`模块安装为项目的依赖。结果会将`lodash`添加到`package.json`文件的`dependencies`字段中。 
3. `--save-dev` 或 `-D`：将模块保存为项目的开发依赖。开发依赖仅在开发和测试时需要，生产环境中不需要。

示例：

```mathematica

npm install -D mocha
```



将`mocha`模块安装为项目的开发依赖。结果会将`mocha`添加到`package.json`文件的`devDependencies`字段中。 
4. `--no-save`：不将模块保存到`package.json`文件。安装模块，但不修改`package.json`。

示例：

```css

npm install moment --no-save
```



安装`moment`模块，但不将其添加到`package.json`文件。 
5. `--save-exact` 或 `-E`：精确安装模块的指定版本。将确切的版本号添加到`package.json`文件，而不是使用版本范围。

示例：

```sql

npm install express@4.17.1 --save-exact
```



安装`express`模块的`4.17.1`版本，并将精确的版本号添加到`package.json`文件。 
6. `--save-optional` 或 `-O`：将模块保存为项目的可选依赖。可选依赖项是在安装失败时不会导致整个安装过程失败的依赖项。

示例：

```mathematica

npm install -O fsevents
```



将`fsevents`模块安装为项目的可选依赖。结果会将`fsevents`添加到`package.json`文件的`optionalDependencies`字段中。 
7. `--dry-run`：模拟安装过程。不实际执行安装，但显示将要执行的操作。

示例：

```arduino

npm install express --dry-run
```



显示安装`express`模块的过程，但实际上不执行安装。这有助于了解将要进行的操作，而不会对项目产生实际影响。 
8. `--legacy-peer-deps`：忽略对等依赖项的版本冲突。在某些情况下，可能会


- 在某些情况下，可能会遇到对等依赖项版本冲突的问题。使用`--legacy-peer-deps`参数可以忽略这些冲突，继续安装。

示例：

```css

npm install react-scripts --legacy-peer-deps
```



安装`react-scripts`模块，即使存在对等依赖项版本冲突，也继续执行安装。 
1. `--force` 或 `-f`：强制安装模块。如果已经存在缓存或安装的模块，忽略它们，强制重新安装。

示例：

```css

npm install lodash --force
```



强制安装`lodash`模块，忽略已存在的缓存和已安装的模块。 
1. `--production`：仅安装项目的生产依赖项。跳过安装`devDependencies`字段中的模块。

示例：

```css

npm install --production
```



仅安装`package.json`文件中`dependencies`字段中列出的模块，跳过`devDependencies`中的模块。 
1. `--ignore-scripts`：安装模块时，不运行`package.json`中的脚本。某些模块在安装过程中可能会运行脚本，这个参数可以阻止这些脚本的执行。

示例：

```css

npm install express --ignore-scripts
```



安装`express`模块，但不执行任何与之相关的脚本。

了解这些`npm install`参数可以帮助您在不同情况下灵活地安装和管理Node.js模块。根据项目需求和实际情况选择合适的参数来优化安装过程。


## `-g 或 --global：全局安装模块。将模块安装到全局目录，使其在任何地方都可以使用。`这里的全局目录是什么

- 全局目录是一个特殊的目录，用于存储全局安装的Node.js模块。当你使用`-g`或`--global`参数安装模块时，模块将被安装到全局目录。全局安装的模块可以在任何地方使用，而不仅仅局限于某个特定的项目目录。

全局目录的位置因操作系统和Node.js的安装方式而异。要查找全局目录的位置，可以运行以下命令：

```

npm root -g
```



这将返回全局`node_modules`目录的路径。以下是一些常见操作系统上的全局目录示例： 
- Windows：全局目录通常位于`C:\Users\<用户名>\AppData\Roaming\npm\node_modules`。 
- macOS/Linux：全局目录通常位于`/usr/local/lib/node_modules`。

请注意，根据你的系统设置和Node.js的安装方式，这些路径可能会有所不同。通过运行`npm root -g`命令，你可以获得准确的全局目录路径。

全局安装的模块主要用于命令行工具或可在多个项目中共享的公共库。在使用全局模块时，不需要为每个项目单独安装，而是可以直接在命令行中使用这些模块的命令或在Node.js代码中引用它们。


## 如何设置全局目录

- 设置Node.js全局目录可以通过修改npm的配置来实现。以下是如何设置全局目录的步骤： 
1. 打开终端或命令提示符。 
2. 运行以下命令来查看当前的全局目录：

```arduino

npm config get prefix
```
3. 若要设置新的全局目录，请运行以下命令，将`<new_path>`替换为你希望将全局目录设置为的新路径：

```arduino

npm config set prefix <new_path>
```



示例：

```arduino

npm config set prefix /Users/yourusername/.npm-global
```
4. 更新系统环境变量以包含新的全局目录。这样，当你全局安装模块时，命令行工具就可以找到它们。 
- Windows：编辑系统环境变量，将新的全局目录添加到`Path`变量中。 
- macOS/Linux：在你的`~/.bashrc`（或相应的配置文件，如`~/.zshrc`）中添加以下行：

```bash

export PATH=<new_path>/bin:$PATH
```



将`<new_path>`替换为你在第3步中设置的全局目录。 
5. 保存更改并重启终端或命令提示符。

通过这些步骤，你可以更改Node.js全局目录。在执行全局安装时，模块将安装到新的全局目录，并且命令行工具将能够找到这些模块。

请注意，在修改全局目录时，请确保具有所需的权限，以便在新目录中安装模块。在某些情况下，可能需要使用管理员权限执行命令。
