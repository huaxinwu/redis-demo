package com.example.demo.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;

import java.time.Duration;

/**
 * @ClassName: RedisConfig
 * @Description: Redis配置
 * @Author wxh
 * @Date: 2019/5/22 9:53
 * @Version V1.0.0
 * @Since 1.8
 */
@Configuration
public class RedisConfig  extends CachingConfigurerSupport {

    /**
     * 缓存对象集合中，缓存是以 key-value 形式保存的。
     * 当不指定缓存的 key 时，SpringBoot 会使用 SimpleKeyGenerator 生成 key。
     * @return KeyGenerator
     */
    @Bean
    public KeyGenerator wiselyKeyGenerator() {
        final String PRE_KEY = "test";
        final char sp = ':';
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(PRE_KEY);
            sb.append(sp);
            sb.append(target.getClass().getSimpleName());
            sb.append(sp);
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append(sp);
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * 配置缓存管理
     * @param factory Redis连接工厂
     * @return CacheManager 缓存管理对象
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        // 更改值的序列化方式，否则在Redis可视化软件中会显示乱码。默认为JdkSerializationRedisSerializer
        RedisSerializationContext.SerializationPair<Object> pair = RedisSerializationContext.SerializationPair
                .fromSerializer(new GenericJackson2JsonRedisSerializer());
        RedisCacheConfiguration defaultCacheConfig = RedisCacheConfiguration
                .defaultCacheConfig()
                .serializeValuesWith(pair)
                .entryTtl(Duration.ofHours(1));

        return RedisCacheManager
                .builder(RedisCacheWriter.nonLockingRedisCacheWriter(factory))
                .cacheDefaults(defaultCacheConfig).build();
    }
}
