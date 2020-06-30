package edu.pja.mas.eventmasfinal;

import javax.swing.SwingUtilities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

import edu.pja.mas.eventmasfinal.gui.controller.MainController;


//import edu.pja.mas.eventmasfinal.entity.OrganizersGroup.*;

@SpringBootApplication
public class EventMasFinalApplication {

	@Autowired
	private DataInitializer dataInitializer;
	
	public static void main(String[] args) {
		
		ConfigurableApplicationContext context = new SpringApplicationBuilder(EventMasFinalApplication.class)
				.headless(false).run(args);
	//	uncomment this after the first run of the program
		context.getBean(DataInitializer.class).initData();
		
		SwingUtilities.invokeLater(()-> {
			context.getBean(MainController.class).showGUI();

		});
		//SpringApplication.run(EventMasFinalApplication.class, args);
	}

}
