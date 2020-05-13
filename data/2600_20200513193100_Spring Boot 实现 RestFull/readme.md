
`````
@RestController
public class AppController {
    @RequestMapping("/article/{id}")
    public void getArticle(@PathVariable("id") Integer id){

    }
}
`````