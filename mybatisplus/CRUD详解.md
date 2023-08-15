https://juejin.cn/post/6991630379102765086



## 1、 Mapper CRUD 接口

> **说明** :
>
> * **通用 CRUD 封装BaseMapper 接口，为 Mybatis-Plus 启动时自动解析实体表关系映射转换为 Mybatis 内部对象注入容器**
> * **泛型 T 为任意实体对象**
> * **参数 Serializable 为任意类型主键 Mybatis-Plus 不推荐使用复合主键约定每一张表都有自己的唯一 id 主键**
> * **对象 Wrapper 为 条件构造器**

---

### 1.1、Insert

```
// 插入一条记录
int insert(T entity);
```


**==参数说明==**

| 类型 | 参数名 | 描述     |
| ---- | ------ | -------- |
| T    | entity | 实体对象 |

```
Employee employee = new Employee();
employee.setLastName("皮皮虾");
employee.setAge(21);
employee.setEmail("qq@qq.com");
employee.setGender(1);

employeeMapper.insert(employee);

```

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/55bd41cb39b446359d5825331ed3be88~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b44740f3b56c402eb19c394f47c3831b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 1.2、Delete

```
// 根据 entity 条件，删除记录
int delete(@Param(Constants.WRAPPER) Wrapper<T> wrapper);
// 删除（根据ID 批量删除）
int deleteBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 ID 删除
int deleteById(Serializable id);

```

**==参数说明==**

| 类型                               | 参数名    | 描述                               |
| ---------------------------------- | --------- | ---------------------------------- |
| Wrapper                            | wrapper   | 实体对象封装操作类（可以为 null）  |
| Collection<? extends Serializable> | idList    | 主键ID列表(不能为 null 以及 empty) |
| Serializable                       | id        | 主键ID                             |
| Map<String, Object>                | columnMap | 表字段 map 对象                    |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c27ced3ba89d46ef836015374a2d9d78~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/efcd5aee6cb64d9d8fca8f301f3e9787~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dca0f20f04714afb99be3a7127f75c3d~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e79193fdb4e940509b34965d93d6f296~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/537e12240a8f4cfab09c30bf54fba0a4~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/851c1c81a240495f9b5d690ab0b854c8~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

### 1.3、Update

```
// 根据 ID 修改
int updateById(@Param(Constants.ENTITY) T entity);

```

**==参数说明==**

| 类型    | 参数名        | 描述                                                                |
| ------- | ------------- | ------------------------------------------------------------------- |
| T       | entity        | 实体对象 (set 条件值,可为 null)                                     |
| Wrapper | updateWrapper | 实体对象封装操作类（可以为 null,里面的 entity 用于生成 where 语句） |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ac42b9094b18449da987bc709dd78849~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eb23a3d9ddc2488c97f59e91eaed9caa~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

### 1.4、Select

```
// 根据 ID 查询
T selectById(Serializable id);
// 根据 entity 条件，查询一条记录
T selectOne(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 查询（根据ID 批量查询）
List<T> selectBatchIds(@Param(Constants.COLLECTION) Collection<? extends Serializable> idList);
// 根据 entity 条件，查询全部记录
List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

// 根据 Wrapper 条件，查询总记录数
Integer selectCount(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);

```

**==参数说明==**

| 类型                                                                                                                                                       | 参数名       | 描述                                     |
| ---------------------------------------------------------------------------------------------------------------------------------------------------------- | ------------ | ---------------------------------------- |
| Serializable                                                                                                                                               | id           | 主键ID                                   |
| Wrapper                                                                                                                                                    | queryWrapper | 实体对象封装操作类（可以为 null）        |
| Collection<? extends Serializable>                                                                                                                         | idList       | 主键ID列表(不能为 null 以及 empty)       |
| Map<String, Object>                                                                                                                                        | columnMap    | 表字段 map 对象                          |
| IPage                                                                                                                                                      | page         | 分页查询条件（可以为 RowBounds.DEFAULT） |
| ![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a00448abda40460cb08f1df68cece519~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp) |              |                                          |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eaeb335d490443b5a31392927b6b26ad~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9eaf21004f1947cd9b828b822f3d321a~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1fac0a42386749a2809f238e9830525f~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b0d28a4887414c0e925b411b91bb1973~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/84d87f0bc3e349fb887dd4faa629905b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/48deb2c1477c4904b190e600798d1593~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/08e623a07e284ed688dcd37b6d1aabce~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f8d154e02d564788a1faa186e741c7ee~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5af181989a3a49258826a3318b252049~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1e13fde994bd4b23a886cc229f9adaa3~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

## 2、Service CRUD 接口

> **==说明==** :
>
> * **用 Service CRUD 封装IService 接口，进一步封装 CRUD 采用 get 查询单行 remove 删除 list 查询集合 page 分页 前缀命名方式区分 Mapper 层避免混淆，**
> * **泛型 T 为任意实体对象**
> * **建议如果存在自定义通用 Service 方法的可能，请创建自己的 IBaseService 继承 Mybatis-Plus 提供的基类**
> * **对象 Wrapper 为 条件构造器**

**==创建Service和实现类==**
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/af8ac49b83074a418ff7744988503ac8~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

**EmployeeService**

```
public interface EmployeeService extends IService<Employee> {
}

```

**EmployeeImpl**

```
@Service
public class EmployeeImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {
}

```

---

### 2.1、Save

```
// 插入一条记录（选择字段，策略插入）
boolean save(T entity);
// 插入（批量）
boolean saveBatch(Collection<T> entityList);

```

**==参数说明==**

| 类型       | 参数名     | 描述         |
| ---------- | ---------- | ------------ |
| T          | entity     | 实体对象     |
| Collection | entityList | 实体对象集合 |
| int        | batchSize  | 插入批次数量 |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4692623c0cde432691be47eeb7d3418f~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/0db19a1be58147ecb2d162d94c98322e~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6f35b450fa60444f96f30acddf963c28~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e70d893573184cfb80d10cf1264f621d~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5e274cf5ff0043d28d1be97c7489e0ee~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/6dc5a52561f642ecbad282e7187d6d52~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3e9a9a130e314b19b83b9c7837fdc03b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.2、SaveOrUpdate

```
// TableId 注解存在更新记录，否插入一条记录
boolean saveOrUpdate(T entity);
// 批量修改插入
boolean saveOrUpdateBatch(Collection<T> entityList);

```

**==参数说明==**

| 类型       | 参数名        | 描述                             |
| ---------- | ------------- | -------------------------------- |
| T          | entity        | 实体对象                         |
| Wrapper    | updateWrapper | 实体对象封装操作类 UpdateWrapper |
| Collection | entityList    | 实体对象集合                     |
| int        | batchSize     | 插入批次数量                     |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f8abfb06896342299163af20cddfe6f0~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/2b93d3451aff4cea92af3d5dbe728b6f~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f4b92023bca5439ca1429be31fc119de~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1d8c65d803df4f12b3158054eda93cc9~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/9104ec875a714330904fa2e078349523~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.3、Remove

```
// 根据 entity 条件，删除记录
boolean remove(Wrapper<T> queryWrapper);
// 根据 ID 删除
boolean removeById(Serializable id);
// 删除（根据ID 批量删除）
boolean removeByIds(Collection<? extends Serializable> idList);

```

**==参数说明==**

| 类型                               | 参数名       | 描述                    |
| ---------------------------------- | ------------ | ----------------------- |
| Wrapper                            | queryWrapper | 实体包装类 QueryWrapper |
| Serializable                       | id           | 主键ID                  |
| Map<String, Object>                | columnMap    | 表字段 map 对象         |
| Collection<? extends Serializable> | idList       | 主键ID列表              |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7261b941ff83442fb7af86b96294f46b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/059fc7166f824f3ea3083d42ee41405b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/c0053acf7d604bcd872ca8bfbf718d7a~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/1c1fb5eba6e54f3e969ad4ae2f40d076~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/faf25abdd59d474cb52d9879a9831417~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3221772387ec4ca3924fda873d6b9322~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/dd50b592080148398cd0b627116dca86~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f8a1ff9c3ba848ad97cc030ea0775bc5~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3fa2bd6fb6574ed1924d6f8ddb256bfd~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.4、Update

```
// 根据 ID 选择修改
boolean updateById(T entity);
// 根据ID 批量更新
boolean updateBatchById(Collection<T> entityList);

```

**==参数说明==**

| 类型       | 参数名       | 描述                    |
| ---------- | ------------ | ----------------------- |
| Wrapper    | queryWrapper | 实体包装类 QueryWrapper |
| T          | entity       | 实体对象                |
| Collection | entityList   | 实体对象集合            |
| int        | batchSize    | 更新批次数量            |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e8791092f2564705b5d1f07deeebfe93~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/59e9e0e32c4f4e28815133fbf2a52f23~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/7f70b45d137b4fd798f8bbcc4a1a7593~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/21e472f95a7d4431b22b98032165e058~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.5、Get

```
// 根据 ID 查询
T getById(Serializable id);
// 根据 Wrapper，查询一条记录
T getOne(Wrapper<T> queryWrapper, boolean throwEx);

```

**==参数说明==**

| 类型                        | 参数名       | 描述                            |
| --------------------------- | ------------ | ------------------------------- |
| Serializable                | id           | 实体包装类 主键ID               |
| T                           | entity       | 实体对象                        |
| Collection                  | entityList   | 实体对象集合                    |
| Function<? super Object, V> | mapper       | 转换函数                        |
| Wrapper                     | queryWrapper | 实体对象封装操作类 QueryWrapper |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/4b895ad0b8e9413b9b5a9f9a0e8e751c~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/11c99d38f4464c338b37bbf6b7da1efe~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/aab3cda7a32b49e69a7efe6fd9e4c0c3~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/34407f0cc8144a6c96f12de21e1aa4c0~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.6、List

```
// 查询所有
List<T> list();
// 查询列表
List<T> list(Wrapper<T> queryWrapper);
// 查询（根据ID 批量查询）
Collection<T> listByIds(Collection<? extends Serializable> idList);

```

**==参数说明==**

| 类型                               | 参数名       | 描述                            |
| ---------------------------------- | ------------ | ------------------------------- |
| Collection<? extends Serializable> | idList       | 主键ID列表                      |
| Map<?String, Object>               | columnMap    | 表字段 map 对象                 |
| Collection                         | entityList   | 实体对象集合                    |
| Function<? super Object, V>        | mapper       | 转换函数                        |
| Wrapper                            | queryWrapper | 实体对象封装操作类 QueryWrapper |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/06d21c54bdae4431abceeb42736c6180~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/e5a8efc09e054e789794fa15ca09b020~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b8a98b3165d646e288a887513d2e7f9d~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/3df987e73f2543faa17ec202f60cdcf9~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/f4aa1904572a4d9f92e22aef4691f192~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/39467e3b0b544b7280fd3697b1392f2f~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

---

### 2.7、Count

```
// 查询总记录数
int count();
// 根据 Wrapper 条件，查询总记录数
int count(Wrapper<T> queryWrapper);

```

**==参数说明==**

| 类型    | 参数名       | 描述                            |
| ------- | ------------ | ------------------------------- |
| Wrapper | queryWrapper | 实体对象封装操作类 QueryWrapper |

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/df8cf0f1062d4b8aad321c2e511edf19~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/04797238f28e401790961b706a5dc0de~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/b428d074984a49a5bd48a01ccf661bf4~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/ddc29719dbdf4c98a60a9fb5482c930b~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)
![在这里插入图片描述](https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/52b9af22a142425a833e33114f45d849~tplv-k3u1fbpfcp-zoom-in-crop-mark:4536:0:0:0.awebp)

作者：Code皮皮虾
链接：https://juejin.cn/post/6991630379102765086
来源：稀土掘金
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
