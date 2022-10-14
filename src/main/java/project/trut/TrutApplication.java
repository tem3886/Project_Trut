package project.trut;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;
import project.trut.config.MyBatisConfig;
import project.trut.domain.ApiKey;
import project.trut.repository.coordinate.CoordinateRepository;
import project.trut.repository.member.MemberRepository;
import project.trut.repository.tour.TourRepository;

@Slf4j
@Import(MyBatisConfig.class)
@SpringBootApplication(scanBasePackages = "project.trut.web")
public class TrutApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrutApplication.class, args);
	}

}
