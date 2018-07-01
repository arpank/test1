package com.example;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;
import javax.ws.rs.Produces;

import org.apache.catalina.core.ApplicationContext;

/*import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;*/

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
@RestController
public class ReservationRestController{
	@RequestMapping(value="/reservations", method = RequestMethod.GET, produces ="application/json")
	public String reservations()
	{
		RestTemplate restTemplate = new RestTemplate();
		
		 
	    ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/reservations1",   String.class); 
	    HttpHeaders headers = new HttpHeaders();
	    HttpEntity<String> entity = new HttpEntity<String>("Hello World11111111!", headers);
	    
		ResponseEntity<String> personEntity = restTemplate.exchange("http://localhost:8080/reservations1", HttpMethod.GET, entity, String.class, 100);
		
		
	    System.out.println("Response from service is " + response.getBody());
	    
	    ResponseEntity<HashMap> response1 = restTemplate.getForEntity("http://localhost:8080/reservations2",  HashMap.class ); 
        
	    System.out.println("Response from service is " + response.getBody());

	    
	    System.out.println("Response from service11 is " + response1.getBody());
		Map <String, String> aa = response1.getBody();
		
		return response + "test";
	}
	
	@RequestMapping("/{id}")
	public String getById(@PathVariable Integer id) {
		return  "test111111";
		
	}
	
}
@RestController
class ReservationRestController1{
	@RequestMapping("/reservations1")
	String reservations()
	{
		return "test shivji is great";
	}
}
@RestController
class ReservationRestController2{
	@RequestMapping("/reservations2")
	  Map <String, ? extends Object> reservations()
	{
		Map <String, String> aa = new HashMap();
		aa.put("shivji", "protects and forgives");
		
		return aa;
	}
}





@RestController
class ReservationRestController3{
	@RequestMapping(value="/reservations3", method = RequestMethod.GET, produces ="application/json")
	  Map <String, ? extends Object> reservations()
	{
		Map <String, String> aa = new HashMap();
		aa.put("shivji", "protects and forgives");
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// the mysql url
		String url = "jdbc:mysql://localhost/test";

		// get the mysql database connection
		Connection con;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root", "shall123");
			Calendar calendar = Calendar.getInstance();
			java.sql.Date startDate = new java.sql.Date(calendar.getTime().getTime());
	 		
			connectURL3 cc = new connectURL3();
			HashMap cache = cc.getTables(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			Connection con1 = DriverManager.getConnection("jdbc:mysql://localhost:3306/terer", "root", "shall123");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


		return aa;
	}
}




