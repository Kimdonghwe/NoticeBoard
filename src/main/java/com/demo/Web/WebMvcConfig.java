package com.demo.Web;

import com.demo.Web.interceptor.LoginCheckInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterceptor())
            .addPathPatterns("/board/**")  // 인터셉터를 적용할 경로 패턴
            .excludePathPatterns("/", "/login", "/logout", "/css/**", "/js/**", "/images/**");  // 인터셉터를 제외할 경로
  }

}
