package com.mx.pedidos;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication(exclude = { UserDetailsServiceAutoConfiguration.class })
@ComponentScan({ "com.mx"})
public class PedidosApplication {
	public static void main(String[] args) {
		SpringApplication.run(PedidosApplication.class);
	}
}
