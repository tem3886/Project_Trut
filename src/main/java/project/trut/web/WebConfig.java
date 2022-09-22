package project.trut.web;

import com.querydsl.core.annotations.Config;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import project.trut.web.interceptor.LoginCheckInterceptor;

@Config
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/members/add", "/login", "logout",
                        "/css/**", "/*.ico", "/error");
    }
}
