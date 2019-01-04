# Demo Instructions

1. Build and run without caching enabled. Show latency on subsequent requests
    ```
    mvn clean package
    java -jar /target/*.jar
    ```
1. Add the Maven dependency
    ```
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-data-redis</artifactId>
    </dependency>
    ```
1. Add Redis properties to `application.properties` file
    ```
    spring.cache.type=redis
    spring.redis.host=
    spring.redis.port=
    spring.redis.password=
    ```
1. Add the `@Cacheable` tags in `PostController.java`
    ```
    @Cacheable(value = "post-single", key = "#id", unless = "#result.shares < 500")
    @CachePut(value = "post-single", key = "#post.id")
    @CacheEvict(value = "post-single", key = "#id")
    @Cacheable(value = "post-top")
    @CacheEvict(value = "post-top")
    ```
1. Add the `@EnableCaching` tag to the main method