package com.example.sse.config;

import java.util.List;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.sse.common.interceptor.AuthInterceptor;
import com.example.sse.common.resolver.UserArgumentResolver;

@Configuration
public class WebConfig implements WebMvcConfigurer {

	private final AuthInterceptor authInterceptor;
	private final UserArgumentResolver argumentResolver;

	public WebConfig(AuthInterceptor authInterceptor, UserArgumentResolver argumentResolver) {
		this.authInterceptor = authInterceptor;
		this.argumentResolver = argumentResolver;
	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
			.allowedMethods("GET", "POST")
			.exposedHeaders(HttpHeaders.LOCATION);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authInterceptor);
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(argumentResolver);
	}
}
