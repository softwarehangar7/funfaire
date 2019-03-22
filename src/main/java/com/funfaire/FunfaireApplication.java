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
		System.out.println(client.getAllProducts("HQLA9307HSLQYTC24PO2G0LITTIOHS2MJC8120PVZ83HJK4KACRZJL91QB7K01NWS2TUCFXGCHQ8HVED8WNZG0KS6XRNBFRNGY71").getCollection());
		
	}

	
	
	
	
}
