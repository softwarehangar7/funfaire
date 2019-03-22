package com.funfaire;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import com.funfaire.restclient.ProductClient;

@SpringBootApplication
@ComponentScan
public class FunfaireApplication implements CommandLineRunner{

	@Autowired
	private ProductClient client;
	
	
	public static void main(final String[] args) {
		SpringApplication.run(FunfaireApplication.class, args);
	}

	@Override
	public void run(final String... args) throws Exception {
		if (args.length !=1) throw new RuntimeException("Should include API KEY as arg");
		final String apiKey = args[0];
		client.setApiKey(apiKey);
		
		
		System.out.println(client.getAllProducts().getCollection());
		
	}

	
	
	
	
}
