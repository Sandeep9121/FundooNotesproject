package com.bridzelabz.fundoonotes.configuration;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import com.github.mustachejava.Binding;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMqConfiguration {
	@Autowired
	private ConnectionFactory rabbitMqConnectionFactory;

	@Bean
	DirectExchange rubeExchange() {
		// return new DirectExchange(name, durable, autoDelete)
		return new DirectExchange("rmq.rube.exchange", true, false);
	}

	@Bean
	public Queue rubeQueue() {
		return new Queue("rmq.rube.exchange", true);

	}
	
/*	@Bean
	Binding rubeExchangeBinding() {
		return BindingBuilder.bind( rubeQueue).to
		
	}*/
}
