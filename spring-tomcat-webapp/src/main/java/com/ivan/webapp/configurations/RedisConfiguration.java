package com.ivan.webapp.configurations;

import java.util.concurrent.Executors;

import com.ivan.webapp.redis.receiver.CustomReceiver;
import com.ivan.webapp.redis.sender.CustomSender;

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

public class RedisConfiguration {

  @Bean
  JedisConnectionFactory connectionFactory() {
    JedisConnectionFactory factory = new JedisConnectionFactory();
    return factory;
  }

  @Bean
  public RedisTemplate<String, Object> redisTemplate() {
    RedisTemplate<String, Object> template = new RedisTemplate<>();
    template.setConnectionFactory(connectionFactory());
    template.setKeySerializer(new StringRedisSerializer());
    template.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    template.setHashKeySerializer(new StringRedisSerializer());
    template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

    return template;
  };


	@Bean
	public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory,
			MessageListenerAdapter listenerAdapter) {

		RedisMessageListenerContainer container = new RedisMessageListenerContainer();
		container.setConnectionFactory(connectionFactory);
		container.addMessageListener(listenerAdapter, new PatternTopic("channel1"));
    container.setTaskExecutor(Executors.newFixedThreadPool(2));

		return container;
  }
  
  @Bean
	public MessageListenerAdapter listenerAdapter(CustomReceiver receiver) {
		return new MessageListenerAdapter(receiver, "receiveMessage");
  }
  
  @Bean
	public CustomReceiver receiver() {
		return new CustomReceiver();
  }
  
  @Bean
	public CustomSender sender() {
		return new CustomSender();
  }

}
