package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BootDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(BootDemoApplication.class, args);
	}
}


/*From advanced rest client {
    "color":"Blue",
    "miles":200,
    "vin":"1234", 
      "name": [{"lastName":"arpan", 
               "name":[{"lastName":"test"}]
               }, {"lastName":"darpan"}]
}*/