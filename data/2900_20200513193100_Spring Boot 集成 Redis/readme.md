## 在 pom.xml 中添加 dependency
`````
<dependencies>
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
        <version>2.1.6.RELEASE</version>
    </dependency>
</dependencies>
`````

## 配置 application.properties
`````
spring.redis.database=1
spring.redis.host=db.knxy.top
spring.redis.port=6379
spring.redis.password=
`````
 
## 添加 Redis 工具类
`````

@Component
public class Redis {
    public static final String TAG = "Redis";
    @Autowired
    private StringRedisTemplate redisTemplate = new StringRedisTemplate();


    // 维护一个本类的静态变量
    private static Redis redis;

    @PostConstruct
    public void init() {
        redis = this;
    }

    // Key（键），简单的key-value操作

    /**
     * 实现命令：TTL key，以秒为单位，返回给定 key的剩余生存时间(TTL, time to live)。
     *
     * @param key
     * @return
     */
    public static long ttl(String key) {
        return redis.redisTemplate.getExpire(key);
    }

    /**
     * 实现命令：expire 设置过期时间，单位秒
     *
     * @param key
     * @return
     */
    public static void expire(String key, long timeout) {
        redis.redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：INCR key，增加key一次
     *
     * @param key
     * @return
     */
    public static long incr(String key, long delta) {
        return redis.redisTemplate.opsForValue().increment(key, delta);
    }

    /**
     * 实现命令：KEYS pattern，查找所有符合给定模式 pattern的 key
     */
    public static Set<String> keys(String pattern) {
        return redis.redisTemplate.keys(pattern);
    }

    /**
     * 实现命令：DEL key，删除一个key
     *
     * @param key
     */
    public static void del(String key) {
        redis.redisTemplate.delete(key);
    }

    // String（字符串）

    /**
     * 实现命令：SET key value，设置一个key-value（将字符串值 value关联到 key）
     *
     * @param key
     * @param value
     */
    public static void set(String key, String value) {
        redis.redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 实现命令：SET key value EX seconds，设置key-value和超时时间（秒）
     *
     * @param key
     * @param value
     * @param timeout （以秒为单位）
     */
    public static void set(String key, String value, long timeout) {
        redis.redisTemplate.opsForValue().set(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 实现命令：GET key，返回 key所关联的字符串值。
     *
     * @param key
     * @return value
     */
    public static String get(String key) {
        return redis.redisTemplate.opsForValue().get(key);
    }

    // Hash（哈希表）

    /**
     * 实现命令：HSET key field value，将哈希表 key中的域 field的值设为 value
     *
     * @param key
     * @param field
     * @param value
     */
    public static void hset(String key, String field, Object value) {
        redis.redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 实现命令：HGET key field，返回哈希表 key中给定域 field的值
     *
     * @param key
     * @param field
     * @return
     */
    public static String hget(String key, String field) {
        return (String) redis.redisTemplate.opsForHash().get(key, field);
    }

    /**
     * 实现命令：HDEL key field [field ...]，删除哈希表 key 中的一个或多个指定域，不存在的域将被忽略。
     *
     * @param key
     * @param fields
     */
    public static void hdel(String key, Object... fields) {
        redis.redisTemplate.opsForHash().delete(key, fields);
    }

    /**
     * 实现命令：HGETALL key，返回哈希表 key中，所有的域和值。
     *
     * @param key
     * @return
     */
    public static Map<Object, Object> hgetall(String key) {
        return redis.redisTemplate.opsForHash().entries(key);
    }

    // List（列表）

    /**
     * 实现命令：LPUSH key value，将一个值 value插入到列表 key的表头
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public static long lpush(String key, String value) {
        return redis.redisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 实现命令：LPOP key，移除并返回列表 key的头元素。
     *
     * @param key
     * @return 列表key的头元素。
     */
    public static String lpop(String key) {
        return (String) redis.redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 实现命令：RPUSH key value，将一个值 value插入到列表 key的表尾(最右边)。
     *
     * @param key
     * @param value
     * @return 执行 LPUSH命令后，列表的长度。
     */
    public static long rpush(String key, String value) {
        return redis.redisTemplate.opsForList().rightPush(key, value);
    }
}

`````

## 使用 Redis 存储 Session 的回话信息

???