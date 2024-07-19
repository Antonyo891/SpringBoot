package ru.gb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.gb.demo.aspect.NameLoggerAspect;
import ru.gb.demo.service.ReaderService;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class Application {
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
		ReaderService readerService = context.getBean(ReaderService.class);
		NameLoggerAspect nameLoggerAspect = context.getBean(NameLoggerAspect.class);
		System.out.println(nameLoggerAspect.getClass());
		System.out.println(readerService.ReaderInfo(1L));

	}

}
