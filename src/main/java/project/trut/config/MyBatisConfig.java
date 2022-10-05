package project.trut.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.trut.domain.ApiKey;
import project.trut.domain.location.LocationRepository;
import project.trut.domain.location.mybatis.LocationMapper;
import project.trut.domain.location.mybatis.MyBatisLocationRepository;
import project.trut.domain.member.MemberRepository;
import project.trut.domain.member.mybatis.MemberMapper;
import project.trut.domain.member.mybatis.MyBatisMemberRepository;
import project.trut.domain.service.location.LocationService;
import project.trut.domain.service.login.LoginService;
import project.trut.domain.service.member.MemberService;
import project.trut.domain.service.tour.PathService;
import project.trut.domain.service.tour.TourService;
import project.trut.domain.tour.TourLocalRepository;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;
    private final LocationMapper locationMapper;

    @Bean
    public ApiKey apiKey() {
        return new ApiKey();
    }
    @Bean
    public LoginService loginService() {
        return new LoginService(memberRepository());
    }

    @Bean
    public MemberService memberService() {
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MyBatisMemberRepository(memberMapper);
    }

    @Bean
    public LocationService locationService() {
        return new LocationService(locationRepository());
    }

    @Bean
    public LocationRepository locationRepository() {
        return new MyBatisLocationRepository(locationMapper);
    }

    @Bean
    public TourService tourService() {
        return new TourService(apiKey());
    }

    @Bean
    public TourLocalRepository tourLocalRepository() {
        return new TourLocalRepository();
    }

    @Bean
    public PathService pathService() {
        return new PathService(tourLocalRepository(), apiKey());
    }
}
