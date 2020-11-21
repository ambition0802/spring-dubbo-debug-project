package com.huang.dubboprovider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.math.BigDecimal;
import java.util.TreeSet;

@SpringBootApplication
public class DubboProviderApplication {

	public static void main(String[] args) {

		TreeSet<BigDecimal> treeSet = new TreeSet<>();
		treeSet.add(BigDecimal.ONE);
		SpringApplication.run(DubboProviderApplication.class, args);
	}

}
