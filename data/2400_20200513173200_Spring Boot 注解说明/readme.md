## Controller
@Controller

@RestController : Spring4 后新增注解, 是 @Controller 和 @ResponseBody 的组合注解, 用于**返回字符串或者json数据**.

@RequestMapping : 配置请求信息

@GetMapping : @RequestMapping 和 method = RequestMethod.GET 请求方法的组合.

@PostMapping : @RequestMapping 和 method = RequestMethod.POST 请求方法的组合

@PutMapping : @RequestMapping 和 method = RequestMethod.PUT 请求方法的组合

@DeleteMapping : @RequestMapping 和 method = RequestMethod.DELETE 请求方法的组合

@ResponseBody : 确定返回字符串

@PathVariable : RestFull参数设定

## Service
@Service

## 其他
@Autowired : (声明读取properties的实体类)

@Component : 是所有受Spring 管理组件的通用形式，@Component注解可以放在类的头上，@Component不推荐使用

@ConfigurationProperties : (读取properties参数时设置前缀)

@Configuration : (拦截器配置)