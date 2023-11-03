package com.zb.loanproject.config;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Value("${spring.data.redis.host}")
    private String redisHost;

    @Value("${spring.data.redis.port}")
    private String redisPort;

    @Bean
    public CacheManager redisCacheManager(RedisConnectionFactory redisConnectionFactory) {
        // ObjectMapper와 Jackson2JsonRedisSerializer 설정

        ObjectMapper om = new ObjectMapper();
        om.activateDefaultTyping(
          om.getPolymorphicTypeValidator(), // 역직렬화 시 타입을 검증할 수 있는 기능
          DefaultTyping.NON_FINAL, // 역직렬화 시 타입 정보를 포함하도록 설정
          As.WRAPPER_ARRAY // 역직렬화 시 타입 정보를 어떤 형태로 포함할지 설정
        );

        Jackson2JsonRedisSerializer<Object> jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer<>(
          Object.class);
        jackson2JsonRedisSerializer.serialize(om);

        RedisCacheConfiguration conf = RedisCacheConfiguration.defaultCacheConfig()
                                                              .serializeKeysWith(
                                                                RedisSerializationContext.SerializationPair.fromSerializer(
                                                                  new StringRedisSerializer()))
                                                              .serializeValuesWith(
                                                                RedisSerializationContext.SerializationPair.fromSerializer(
                                                                  jackson2JsonRedisSerializer));
        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(redisConnectionFactory)
                                                         .cacheDefaults(conf)
                                                         .build();
    }

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration conf = new RedisStandaloneConfiguration();
        conf.setHostName(redisHost);
        conf.setPort(Integer.parseInt(redisPort));
        return new LettuceConnectionFactory(conf);
    }
}
