package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@EnableBinding(Source.class)
@RestController
public class SourceApplication {
	
	@Autowired
	Source source;
	
	@RequestMapping("/")
	public String home() {
		source.output().send(MessageBuilder.withPayload(new Greeting("Hello")).build());
		return "Hello World";
	}

	public static void main(String[] args) {
		SpringApplication.run(SourceApplication.class, args);
	}
}


class Greeting {
	private String msg;
	
	@SuppressWarnings("unused")
	private Greeting() {
	}

	public Greeting(String msg) {
		this.msg = msg;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}