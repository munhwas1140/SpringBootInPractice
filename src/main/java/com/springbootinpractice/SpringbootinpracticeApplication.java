package com.springbootinpractice;

import com.springbootinpractice.customproperties.AppProperties;
import com.springbootinpractice.model.Course;
import com.springbootinpractice.model.User;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.event.EventListener;

import java.util.Date;
import java.util.Set;

@SpringBootApplication
@Slf4j
// ConfigurationProperties 애너테이션이 붙어있는 클래스를 스프링 컨테이너에 등록한다.
// 자동 탐색해서 등록해주는 것이 아니라 직접 명시해주어야 한다.
@EnableConfigurationProperties(AppProperties.class)
public class SpringbootinpracticeApplication implements CommandLineRunner {
	public static void main(String[] args) {
//		SpringApplication.run(SpringbootinpracticeApplication.class, args);
		// 이벤트리스너 추가
//		SpringApplication springApplication = new SpringApplication(SpringbootinpracticeApplication.class);
//		springApplication.addListeners(new ApplicationStartingEventListener());
//		springApplication.run(args);

		// 프로퍼티 직접 설정
//		SpringApplication springApplication = new SpringApplication(SpringbootinpracticeApplication.class);
//		Properties properties = new Properties();
//		properties.setProperty("spring.config.on-not-found", "ignore");
//		springApplication.setDefaultProperties(properties);
//		springApplication.run(args);


//		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootinpracticeApplication.class, args);
//		DbConfiguration dbConfiguration = applicationContext.getBean(DbConfiguration.class);
//		log.info(dbConfiguration.toString());

		// AppProperties로 프로퍼티 값들 바인딩해서 AppProperties에 담기
//		ConfigurableApplicationContext applicationContext = SpringApplication.run(SpringbootinpracticeApplication.class, args);
//		AppService appService = applicationContext.getBean(AppService.class);
//		log.info(appService.getAppProperties().toString());

		SpringApplication.run(SpringbootinpracticeApplication.class, args);
		log.info("CourseTrackerApplication started successfully with Log4j2 configuration");
	}

	@EventListener(ApplicationReadyEvent.class)
	public void applicationReadyEvent(ApplicationReadyEvent applicationReadyEvent) {
		System.out.println("Application Ready Event generated at " + new Date(applicationReadyEvent.getTimestamp()));
	}


	// 빈으로 CommandLineRunner 빈 만들기
	@Bean
	public CommandLineRunner commandLineRunner() {
		return args -> {
			log.info("CommandLineRunner executed as a bean definition with " + args.length + " arguments");
			for(int i = 0; i < args.length; i++) {
				log.info("Argument: " + args[i]);
			}
		};
	}

	// 오버라이딩하기
	@Override
	public void run(String... args) throws Exception {
		log.info("Application CommandLineRunner has executed");

		Course course = Course.builder().id(1L)
						.rating(0).build();
		Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
		Set<ConstraintViolation<Course>> violations = validator.validate(course);
		violations.forEach(courseConstraintViolation ->
				log.error("A constraint violation has occurred. Violation details: [{}]", courseConstraintViolation));


		User user1 = new User("sbip01", "ship");
		Set<ConstraintViolation<User>> userViolations = validator.validate(user1);
		log.error("Password for user1 do not adhere to the password policy");
		userViolations.forEach(userConstraintViolation ->
				log.error("Violation details: [{}]", userConstraintViolation.getMessage()));

		User user2 = new User("sbip02", "Sbip01$4UDfg");
		userViolations = validator.validate(user2);
		if(violations.isEmpty()) {
			log.info("Password for user2 adhere to the password policy");
		}

		User user3 = new User("sbip03", "Sbip01$4UDFggg");
		userViolations = validator.validate(user3);
		log.error("Password for user3 violates maximum repetitive rule");
		userViolations.forEach(userConstraintViolation ->
				log.error("Violation details: [{}]", userConstraintViolation.getMessage()));

		User user4 = new User("sbip04", "Sbip014UDfgggg");
		userViolations = validator.validate(user4);
		log.error("Password for user4 violates special character rule");
		userViolations.forEach(userConstraintViolation ->
				log.error("Violation details: [{}]", userConstraintViolation.getMessage()));
	}
}
