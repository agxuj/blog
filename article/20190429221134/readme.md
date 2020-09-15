<h1 style="font-size: 2.5em;"> Redis ��ʹ��</h1>
 




## 配置远程密码登录
`````
vi /usr/local/redis/etc/redis.conf

注释 #bind 127.0.0.1
设置 protected-mode no
设置 requirepass ${password}
`````
### 重启
### 防火墙端口开�?

## 客户端使�?
`````
./redis-cli
auth ${password}
`````
## Java�?单使�?
导入 [jedis.jar](https://mvnrepository.com/artifact/redis.clients/jedis)
`````

import redis.clients.jedis.*;

public class Main {

    public static void main(String[] args) {
        //连接本地�? Redis 服务
        String host  = "39.106.114.110";
        int port = 6379;
        Jedis jedis = null;
        try {
            jedis = new Jedis(host,port);
            jedis.auth("pawword);
            jedis.select(1);
            jedis.set("name","kun");
            String name = jedis.get("name");
            System.out.println("name = " + name);
            jedis.flushDB();
            String name2 = jedis.get("name");
            System.out.println("name2 = " + name2);
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (null != jedis){
                try {
                    jedis.close();
                }catch (Exception e){
                    System.out.println("redis连接关闭失败");
                    e.printStackTrace();
                }
            }
        }
    }
}

`````
## 教程
[Redis 教程 -- 菜鸟教程](https://www.runoob.com/redis/redis-tutorial.html)

## Reference
[redis�?启远程访问](https://www.cnblogs.com/liusxg/p/5712493.html)

[Linux Redis启用远程连接](https://liuyanzhao.com/8349.html)

[Java密码连接redis](https://blog.csdn.net/qq_35981283/article/details/80338221)