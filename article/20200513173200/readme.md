<h1 style="font-size: 2.5em;"> Spring Boot ע��˵��</h1>
 

## Controller
@Controller

@RestController : Spring4 后新增注�?, �? @Controller �? @ResponseBody 的组合注�?, 用于**返回字符串或者json数据**.

@RequestMapping : 配置请求信息

@GetMapping : @RequestMapping �? method = RequestMethod.GET 请求方法的组�?.

@PostMapping : @RequestMapping �? method = RequestMethod.POST 请求方法的组�?

@PutMapping : @RequestMapping �? method = RequestMethod.PUT 请求方法的组�?

@DeleteMapping : @RequestMapping �? method = RequestMethod.DELETE 请求方法的组�?

@ResponseBody : 确定返回字符�?

@PathVariable : RestFull参数设定

## Service
@Service

## 其他
@Autowired : (声明读取properties的实体类)

@Component : 是所有受Spring 管理组件的�?�用形式，@Component注解可以放在类的头上，@Component不推荐使�?

@ConfigurationProperties : (读取properties参数时设置前�?)

@Configuration : (拦截器配�?)