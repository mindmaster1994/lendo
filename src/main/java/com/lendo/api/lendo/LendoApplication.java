package com.lendo.api.lendo;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.lendo.api.lendo.service.UserService;

@SpringBootApplication
@EnableAutoConfiguration
public class LendoApplication {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(LendoApplication.class, args);
	}
	
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource source = new ReloadableResourceBundleMessageSource();

		source.setBasenames("classpath:/i18n/messages");
		source.setDefaultEncoding("UTF-8");
		source.setUseCodeAsDefaultMessage(true);
		source.setCacheSeconds(100);
		source.setFallbackToSystemLocale(false);
		return source;
	}
	
	@Bean
    public CommandLineRunner CommandLineRunnerBean() {
        return (args) -> {
            System.out.println("Creating temp user..");

            userService.createTempUsers();
        };
    }


	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("*").allowedMethods(HttpMethod.POST.name(),
						HttpMethod.PUT.name(), HttpMethod.GET.name(), HttpMethod.OPTIONS.name(),
						HttpMethod.DELETE.name());
			}
			
		};
	}

}
