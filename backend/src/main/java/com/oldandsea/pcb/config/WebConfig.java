package com.oldandsea.pcb.config;

import com.oldandsea.pcb.domain.repository.SessionRepository;
import com.oldandsea.pcb.interceptor.LoginCheckInterceptor;
import com.oldandsea.pcb.service.SessionService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class WebConfig implements WebMvcConfigurer {
    private final SessionService sessionService;
    private final SessionRepository sessionRepository;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor(sessionService)) //인터셉터 등록. .
                .order(1) //낮을 수록 먼저 호출
                .addPathPatterns("/**") //인터셉터를 적용할 url 패턴
                /*
                인터셉터에서 제외할 패턴 지정, 로그인할 때는 로그인 인증과정 제외
                (안그러면 로그인하기전에는 세션이 없으니 로그인 요청이 계속 실패됨)
                 */
                .excludePathPatterns("/css/**", "/*.ico", "/error","/member/login","/member/create", "/api/main",
                        "member/uid-check","member/nickname-check");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        resolvers.add(new LoginMemberArgumentResolver(sessionService));
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:4000") // 허용할 출처
                .allowedMethods("*") // 허용할 HTTP method (OPTIONS 도 허용)
                .allowCredentials(true) // 쿠키 인증 요청 허용
                .maxAge(3000) // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
        ;
    }
}