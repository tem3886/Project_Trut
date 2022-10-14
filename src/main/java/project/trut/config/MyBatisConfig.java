package project.trut.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.trut.domain.ApiKey;
import project.trut.web.TourLocalRepository;
import project.trut.repository.coordinate.CoordinateRepository;
import project.trut.repository.coordinate.mybatis.CoordinateMapper;
import project.trut.repository.coordinate.mybatis.MyBatisCoordinateRepository;
import project.trut.repository.member.MemberRepository;
import project.trut.repository.member.mybatis.MemberMapper;
import project.trut.repository.member.mybatis.MyBatisMemberRepository;
import project.trut.repository.tour.TourRepository;
import project.trut.repository.tour.mybatis.MyBatisTourRepository;
import project.trut.repository.tour.mybatis.TourMapper;
import project.trut.service.login.LoginService;
import project.trut.service.member.MemberService;
import project.trut.service.tour.DBService;
import project.trut.service.tour.OdsayApiService;
import project.trut.service.tour.OrderService;
import project.trut.service.tour.TourApiService;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;
    private final TourMapper tourMapper;
    private final CoordinateMapper coordinateMapper;
    private final TourLocalRepository tourLocalRepository;

    @Bean
    public ApiKey apiKey() {
        return new ApiKey();
    }

    /**
     * Repository
     */
    @Bean
    public MemberRepository memberRepository() {
        return new MyBatisMemberRepository(memberMapper);
    }

    @Bean
    public TourRepository tourRepository() {
        return new MyBatisTourRepository(tourMapper);
    }

    @Bean
    public CoordinateRepository coordinateRepository() {
        return new MyBatisCoordinateRepository(coordinateMapper);
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
