# sps 备品备件开发教程

author：wumengxuan

## 构建基础框架

### 新建 Spting Boot 项目

#### 选择组件包

New Project -- Spring Initializr  

可以在 https://start.spring.io/ 网站上选择组件包

#### 创建项目

Project Metadata

![1547694783466](C:\Users\WUMENG~1\AppData\Local\Temp\1547694783466.png)

#### 添加依赖 ※

此步骤要根据项目的需求选择依赖，比如有些后台进程跑定时任务不需要 web 。本项目是前后端分离，不需要 web 界面，但是要处理 web 的请求的API。

主要依赖：JPA, Web, H2，DevTools, Lombok，Cache

- JPA 开发规范

- H2 数据库

- web 展示

- DevTools 热部署 （Crtl+F9），代码已改好就可以生效

- Lombok 注释型代码生成工具

- Actuator 

  ![1547694869161](C:\Users\WUMENG~1\AppData\Local\Temp\1547694869161.png)

创建好项目以后可以将 `.mvn`  `mvnw`  `mvnw.cmd` 文件删掉，主要在 `/src` 文件下进行开发和测试。

#### sps-demo 目录结构

```bash
sps-demo
├── pom.xml
├── sps-demo.iml
├── sps.jdl
└── src
    ├── main
    │   ├── java
    │   │   └── cn
    │   │       └── wilmar
    │   │           └── spsdemo
    │   │               ├── domain                    //实体（Entity）与数据访问层（Repository）
    │   │               │   ├── Organization.java
    │   │               │   ├── Role.java
    │   │               │   └── User.java
    │   │               ├── repository                          // 页面访问控制
    │   │               │   ├── OrganizationRepository.java
    │   │               │   ├── RoleRepository.java
    │   │               │   └── UserRepository.java
    │   │               ├── service                             // 业务类代码
    │   │               │   └── UserService.java         
    │   │               ├── SpsDemoApplication.java             // main入口，作为框架配置
    │   │               └── web
    │   │                   ├── OrganizationController.java
    │   │                   ├── RoleController.java
    │   │                   └── UserController.java
    │   └── resources
    │       ├── application.properties
    │       └── schema.sql
    └── test                                                    // 测试
        └── java
            └── cn
                └── wilmar
                    └── spsdemo
                        ├── repository
                        │   └── UserRepositoryTest.java
                        ├── service
                        │   └── UserServiceTest.java
                        ├── SpsDemoApplicationTests.java
                        └── web
                            └── UserControllerTest.java

19 directories, 21 files
```

#### Hello,world

创建Hello，mengxuanwu 进行测试：

```java
@RestController
class HelloController {//mvc 里的C 返回是个text文本
    @GetMapping("/")//上下文请求
    @ResponseBody
    public String hello() {
        return "Hello, World!";
    }
}
```

#### 访问 h2-console

访问 http://localhost:8080/h2-console 

![1547709055527](C:\Users\WUMENG~1\AppData\Local\Temp\1547709055527.png)

- url: jdbc:h2:mem:testdb
- username: sa
- password: 空

![1547709140882](C:\Users\WUMENG~1\AppData\Local\Temp\1547709140882.png)

#### 创建 Git 仓库

每做完一个阶段都要记得提交git

**Alt+F12 **在项目目录下打开命令行，创建 Git 仓库：

```bash
# 通过 http 进行连接

# git global settings
git config --global user.name "吴梦萱"
git config --global user.email "wumengxuan@cn.wilmar-intl.com"

# git commit
git init
git remote set-url origin http://git.wilmar.cn/wumengxuan/sps-demon-wmx.git
git add .
git commit -m "Complete Hello,world"
git push -u origin master
```

### 基础对象管理

#### 实体 Entity

创建 User 实体对象

```
@Data //lombok 自动生成方法 getId等
@NoArgsConstructor
@AllArgsConstructor
@Entity //JPA规范
public class User { //数据库
    
    public User()//构造函数，加了@Data不用写构造函数
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) //组件生成策略
    Long id; //组件 javax.persistence JPA特有规范

    String username;

    String password;

    String email;

    String gender;
    //以上的次序就是构造函数的次序
}
```

设计规范：

表格--id

枚举型code，只有一个，而且不需要改

#### 数据访问对象 Repository

创建 DAO：UserRepository

```
//通过接口实现
public interface UserRepository
extends JpaRepository<User, Long> //支持哪个对象{
}
```

//到此数据库创建成功，进入UserRepository
findById 对象由实体-->Optional （可以返回null对象）用函数式的编程 用朗木达表达式

#### 控制器 Controller

创建控制器：UserController //前台web

```java
@RestController
public class UserController {
    private final UserRepository userRepository; //推荐
    // @Autowired UserRepository userRepository;//注入，不推荐autowired，传入实体
    
	//简单的查询函数
    @GetMapping("/users")//访问，json对象文本
    // public findAllUsers
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
}
```

就可以测试h2数据库是否含有表（默认定义），启动后访问：http://localhost:8080/users 和 h2 console。然后插入数据，访问/users会看到插入的数据

 /ctrl +F9 修改什么部分 

数据库优化

![1547718094783](C:\Users\WUMENG~1\AppData\Local\Temp\1547718094783.png)

## 遇到的问题

- git push 的时候遇到的错误：

```bash
C:\Users\wumengxuan\Desktop\我的文档\sps-demo-wmx>git push -u origin master
ssh: connect to host git.wilmar.cn port 22: Connection timed out
fatal: Could not read from remote repository.

Please make sure you have the correct access rights
and the repository exists.
```

原因：采用 ssh 建立的连接

解决方案：换用 http 进行传输

```bash
git remote set-url origin http://git.wilmar.cn/wumengxuan/sps-demon-wmx.git
git push origin master
```

- 设置好实体发现：

```java
Caused by: org.hibernate.AnnotationException: No identifier specified for entity: com.example.domain.p.User
```

网上有两种解释：

1. 没有添加主键的注解 `@Entity`
2. 添加了 `@Entity` 仍然出现这样的错误。

解决方法：`import javax.persistence.Id` 而我却导入了 `org.springframework.data.annotation.Id` 

虽然 IDE 语法检测不会报错，但是我们要用到的是 `persistence.Id`。

