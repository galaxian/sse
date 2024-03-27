package com.example.sse.common.resolver;

import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import com.example.sse.common.JwtProvider;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserArgumentResolver implements HandlerMethodArgumentResolver {

	private static final String BEARER = "Bearer ";

	private final JwtProvider jwtProvider;

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterType().equals(String.class)
			&& parameter.hasParameterAnnotation(UserId.class);
	}

	@Override
	public String resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
		NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
		String authorizationHeader = webRequest.getHeader(HttpHeaders.AUTHORIZATION);
		checkHeader(authorizationHeader);
		String token = authorizationHeader.substring(BEARER.length());
		return jwtProvider.findSubject(token);
	}

	private void checkHeader(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
			throw new RuntimeException("");
		}
	}
}
