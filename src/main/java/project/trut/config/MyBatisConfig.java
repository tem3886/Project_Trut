package project.trut.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import project.trut.domain.member.MemberRepository;
import project.trut.domain.member.mybatis.MemberMapper;
import project.trut.domain.member.mybatis.MyBatisMemberRepository;
import project.trut.domain.service.login.LoginService;

@Configuration
@RequiredArgsConstructor
public class MyBatisConfig {

    private final MemberMapper memberMapper;

    @Bean
    public LoginService loginService() {
        return new LoginService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository() {
        return new MyBatisMemberRepository(memberMapper);
    }
}
