<h1 style="font-size: 2.5em;"> Spring Boot �����ļ�</h1>
 


## �?�?

配置文件�? application.properties 位于 /src/main/resources �?.

配置文件�?2�?,分别�? properties �? yml, 这里主要�? properties 格式

## 多配置文�?

在不同的场景使用不同的配置文�?,比如�?发环境和生产环境部分参数是不同的,可以分别给每个环境创建配置文�?,格式如下:

�?发环�?: application-dev.properties
生产环境: application-pro.properties

具体使用哪个环境,可以�? application.properties �? spring.profiles.active 中设�?
如设置生产环�?
`````
spring.profiles.active=pro
`````

## 读取配置文件

### 注解 @Value

`````
@Controller
public class AppController {
    
    @Value("database.username")
    private String username;
    
    @Value("database.password")
    private String password;

    @RequestMapping("/index")
    @ResponseBody
    public String hello() {
        return "hello world,"+username;
    }
}
`````

### 注解 @Component

`````

@Component
@ConfigurationProperties(prefix = "database")
public class Config {

    private String username;

    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}


@Controller
public class AppController {

    @Autowired
    private Config config;

    @RequestMapping("/index")
    @ResponseBody
    public String hello() {
        return "hello world," + config.getUsername();
    }
}

`````

### 直接读取

`````
public static String getProperty(String filePath) throws IOException {
    InputStream in = FileUtils.class.getClassLoader().getResourceAsStream("application.properties");
    Properties prop = new Properties();
    prop.load(in);
    String value = prop.get(key);
    return value;
}
`````