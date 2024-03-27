package com.example.sse.common.interceptor;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.sse.common.JwtProvider;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final String BEARER = "Bearer ";

	private final JwtProvider jwtProvider;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
		Object handler) {

		if (!(handler instanceof HandlerMethod)) {
			return true;
		}
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		if (handlerMethod.hasMethodAnnotation(Authenticated.class)) {
			String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
			checkHeader(authorizationHeader);
			String token = authorizationHeader.substring(BEARER.length());
			verifyToken(token);
		}
		return true;
	}

	private void checkHeader(String authorizationHeader) {
		if (authorizationHeader == null || !authorizationHeader.startsWith(BEARER)) {
			throw new RuntimeException("");
		}
	}

	private void verifyToken(String token) {
		if (!jwtProvider.isValidToken(token)) {
			throw new RuntimeException("");
		}
	}
}
