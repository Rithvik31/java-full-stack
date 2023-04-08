package com.ani.reactive.mongo;

import java.util.concurrent.CountDownLatch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ReactiveMongoDemoApplication {

	public static void main(String[] args) throws InterruptedException {
		ApplicationContext ctx = SpringApplication.run(ReactiveMongoDemoApplication.class, args);

        CountDownLatch latch = new CountDownLatch(1);

		ItemRepository repository = ctx.getBean(ItemRepository.class);
		
		Item item = new Item(10L, "abc", "aaa", 456.90);

		repository.save(item)
				.doOnNext( savedItem ->  { 
					System.out.println(" Save Completed "); 
					System.out.println(savedItem); 
					System.out.println(" One");
				} )
				.doOnError(err -> System.out.println(err))
				.subscribe();


		repository.findAll()
				.doOnNext( itm ->  { 
					System.out.println("FindAll Completed "); 
					System.out.println(itm); 
				})
				.doOnError( err -> System.out.println(err) )
				.subscribe();

		System.out.println(" Two");

		latch.await();
	}

}