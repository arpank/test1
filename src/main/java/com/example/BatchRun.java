package com.example;

import org.springframework.boot.SpringApplication;

public class BatchRun {
	 
		  public static void main(String [] args) {
		    System.exit(SpringApplication.exit(SpringApplication.run(
		        BatchConfiguration.class, args)));
		  }
		 
}
