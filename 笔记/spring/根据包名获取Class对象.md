

- Java 根据包名获取里面的类的Class 类型对象


- 要根据包名获取其中的类的Class对象，你需要首先获取包内的所有类名。这通常需要使用Java的反射机制。在这个示例中，我们将使用`ClassPath`类从`Guava`库来实现这个功能。首先，请确保您已经在项目中添加了Guava库的依赖项。以下是一个简单的例子，演示如何实现这个功能： 
1. 添加Guava库的依赖项。如果您使用的是Maven项目，请在`pom.xml`文件中添加以下依赖项：

```xml

<dependencies>
    <!-- Guava: Google Core Libraries for Java -->
    <dependency>
        <groupId>com.google.guava</groupId>
        <artifactId>guava</artifactId>
        <version>30.1-jre</version>
    </dependency>
</dependencies>
```


1. 编写Java代码，使用`ClassPath`类从Guava库获取包内的所有类名，并根据类名获取Class对象。

```java

import com.google.common.reflect.ClassPath;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PackageClasses {
    public static void main(String[] args) {
        String packageName = "your.package.name";
        List<Class<?>> classes = getClassesInPackage(packageName);

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz.getName());
        }
    }

    public static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();

        try {
            ClassPath classPath = ClassPath.from(classLoader);
            for (ClassPath.ClassInfo classInfo : classPath.getTopLevelClassesRecursive(packageName)) {
                classes.add(classInfo.load());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return classes;
    }
}
```



请将`your.package.name`替换为您希望获取类的Class对象的包名。这个示例会输出指定包中的所有类名。通过修改`getClassesInPackage`方法，您可以根据需要获取特定类的Class对象。


- 不用第三方包，如何实现？


- 如果不使用第三方库，您可以通过扫描类文件路径的方法来实现。以下是一个示例，演示了如何通过扫描文件系统和JAR文件来获取包内的类的Class对象：

```java

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class PackageClasses {

    public static void main(String[] args) {
        String packageName = "your.package.name";
        List<Class<?>> classes = getClassesInPackage(packageName);

        for (Class<?> clazz : classes) {
            System.out.println("Class: " + clazz.getName());
        }
    }

    public static List<Class<?>> getClassesInPackage(String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        String packagePath = packageName.replace('.', '/');
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            Enumeration<URL> resources = classLoader.getResources(packagePath);
            while (resources.hasMoreElements()) {
                URL url = resources.nextElement();
                if ("file".equals(url.getProtocol())) {
                    File directory = new File(url.toURI());
                    classes.addAll(findClassesInDirectory(directory, packageName));
                } else if ("jar".equals(url.getProtocol())) {
                    String jarPath = url.getPath().substring(5, url.getPath().indexOf("!"));
                    classes.addAll(findClassesInJar(new File(jarPath), packagePath, packageName));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return classes;
    }

    private static List<Class<?>> findClassesInDirectory(File directory, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        if (directory.exists()) {
            for (File file : directory.listFiles()) {
                String fileName = file.getName();
                if (file.isDirectory()) {
                    classes.addAll(findClassesInDirectory(file, packageName + "." + fileName));
                } else if (fileName.endsWith(".class")) {
                    String className = packageName + '.' + fileName.substring(0, fileName.length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return classes;
    }

    private static List<Class<?>> findClassesInJar(File jarFile, String packagePath, String packageName) {
        List<Class<?>> classes = new ArrayList<>();
        try (JarFile jar = new JarFile(jarFile)) {
            for (Enumeration<JarEntry> entries = jar.entries(); entries.hasMoreElements();) {
                JarEntry entry = entries.nextElement();
                String entryName = entry.getName();
                if (entryName.endsWith(".class") && entryName.startsWith(packagePath)
                        && entryName.length() > packagePath.length()) {
                    String className = entryName.replace('/', '.').substring(0, entryName.length() - 6);
                    try {
                        classes.add(Class.forName(className));
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return classes;
    }
}
```



请将`your.package.name`替换为您希望获取类的Class对象的包名。这个示例会输出指定包中的所有类名。通过修改`getClassesInPackage`方法，您可以根据需要获取特定类的Class对象。
