<h1 style="font-size: 2.5em;"> Spring Boot 实现 RestFull</h1>
 


`````
@RestController
public class AppController {
    @RequestMapping("/article/{id}")
    public void getArticle(@PathVariable("id") Integer id){

    }
}
`````