<h1 style="font-size: 2.5em;"> Spring Boot ������</h1>
 


参�??:[Java视频教程-SpringBoot教程免费分享](https://zhuanlan.zhihu.com/p/47908153)

## 新建项目

<img src="image/00.png"/>

<img src="image/01.png"/>

<img src="image/02.png"/>

<img src="image/03.png"/>

<img src="image/04.png"/>

## 新建 Controller 

`````
@Controller
public class AppController {
    @RequestMapping("/index")
    @ResponseBody
    public String hello() {
        return "hello world";
    }
}
`````

## 运行 Application �? main 方法

**运行结果**

<img src="image/05.png"/>


