package project.trut.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.trut.domain.ApiKey;
import project.trut.repository.coordinate.CoordinateRepository;
import project.trut.repository.coordinate.jpa.JpaCoordinateRepository;
import project.trut.repository.coordinate.jpa.JpaCoordinateRepositoryImple;
import project.trut.repository.member.MemberRepository;
import project.trut.repository.member.jpa.JpaMemberRepository;
import project.trut.repository.member.jpa.JpaMemberRepositoryImple;
import project.trut.repository.tour.TourRepository;
import project.trut.repository.tour.jpa.JpaTourRepository;
import project.trut.repository.tour.jpa.JpaTourRepositoryImple;
import project.trut.service.login.LoginService;
import project.trut.service.member.MemberService;
import project.trut.service.tour.DBService;
import project.trut.service.tour.OdsayApiService;
import project.trut.service.tour.OrderService;
import project.trut.service.tour.TourApiService;
import project.trut.web.TourLocalRepository;

@Configuration
@RequiredArgsConstructor
public class JpaConfig {
    private final TourLocalRepository tourLocalRepository;
    private final JpaMemberRepository jpaMemberRepository;
    private final JpaTourRepository jpaTourRepository;
    private final JpaCoordinateRepository jpaCoordinateRepository;

    @Bean
    public ApiKey apiKey() {
        return new ApiKey();
    }

    /**
     * Repository
     */
    @Bean
    public MemberRepository memberRepository() {
        return new JpaMemberRepositoryImple(jpaMemberRepository);
    }

    @Bean
    public TourRepository tourRepository() {
        return new JpaTourRepositoryImple(jpaTourRepository);
    }

    @Bean
    public CoordinateRepository coordinateRepository() {
        return new JpaCoordinateRepositoryImple(jpaCoordinateRepository);
    }


    /**
     * Service
     */
    @Bean
    public LoginService loginService() {
        return new LoginService(memberRepository());
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public TourApiService tourService() {
        return new TourApiService(apiKey());
    }

    @Bean
    public DBService dbService() {
        return new DBService(tourRepository(), coordinateRepository());
    }

    @Bean
    public OrderService pathService() {
        return new OrderService(tourLocalRepository, dbService(), apiKey());
    }

    @Bean
    public OdsayApiService odsayApiService() {
        return new OdsayApiService(apiKey());
    }

}
