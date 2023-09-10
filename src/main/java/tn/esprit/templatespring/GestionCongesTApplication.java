package tn.esprit.templatespring;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;
import java.util.ArrayList;
import tn.esprit.templatespring.Entities.StructureHierarchique;
import tn.esprit.templatespring.Controller.BuildHierarchyTree;
import tn.esprit.templatespring.Controller.StructureHierarchiqueController;



@SpringBootApplication
@ComponentScan("tn.esprit.templatespring.Services")
@ComponentScan("tn.esprit.templatespring.Controller")
@ComponentScan("tn.esprit.templatespring.Repository")


@ComponentScan({"tn.esprit.templatespring.Services", "tn.esprit.templatespring.Controller","tn.esprit.templatespring.Repository"})

@EntityScan("tn.esprit.templatespring.Entities")

public class GestionCongesTApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionCongesTApplication.class, args);

		
		
	}


}
