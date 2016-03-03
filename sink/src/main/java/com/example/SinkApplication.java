package com.example;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;

@SpringBootApplication
@EnableBinding(Sink.class)
public class SinkApplication {
	
	@Autowired
	Sink source;
	
	@PostConstruct
	public void init() {
		source.input().subscribe(msg -> {System.err.println(msg);});
	}

	public static void main(String[] args) {
		SpringApplication.run(SinkApplication.class, args);
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