package com.wdyapplications.prime_access.utils.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.time.Duration;

@Configuration
public class RedisConfig {
	/*
    @Value("${redis.host:127.0.0.1}")
    private String  redisHost;
    @Value("${redis.port:6379}")
    private Integer redisPort;
    @Value("${redis.password:}")
    private String redisPassword;
    */
	
// local	
//    @Value("${redis.nodes}")
//    private String  redisNode;
    
    @Value("${redis.host}")
	private String redisNode;
    
    @Value("${redis.port}")
    private Integer redisPort;
    @Value("${redis.password}")
    private String redisPassword;
    @Value("${redis.database}")
    private Integer database;


    @Bean
    public RedisTemplate<String, Object> redisTemplateObject() {
        RedisTemplate<String, Object> template = new RedisTemplate<String, Object>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<Object>(Object.class));
//        template.setEnableTransactionSupport(true);
        return template;
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory() {
        RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
        redisConfig.setHostName(redisNode);
        redisConfig.setPort(redisPort);
        redisConfig.setPassword(RedisPassword.of(redisPassword));
        redisConfig.setDatabase(database);

        // Configuration des limites de pool de connexions et timeouts si nécessaire
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofSeconds(2))
                .build();

        return new LettuceConnectionFactory(redisConfig, clientConfig);
    }

    
    /*
    @Bean
    public RedisTemplate<String, UserDto> redisClientTemplate() {
        final RedisTemplate<String, UserDto> template = new RedisTemplate<String, UserDto>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<UserDto>(UserDto.class));
        template.setValueSerializer(new Jackson2JsonRedisSerializer<UserDto>(UserDto.class));
        return template;
    }
    
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
    	 // local
//    	RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration(redisHost, redisPort);
    
    	
        // dev/Prod
    	RedisSentinelConfiguration redisStandaloneConfiguration = new RedisSentinelConfiguration().master(redisCluster);
        redisStandaloneConfiguration.sentinel(redisHost, redisPort);
  
        
        
        if (StringUtils.isNotBlank(redisPassword)) {
            redisStandaloneConfiguration.setPassword(RedisPassword.of(redisPassword));
        }        
        return new JedisConnectionFactory(redisStandaloneConfiguration);
    }
    */
    
}
