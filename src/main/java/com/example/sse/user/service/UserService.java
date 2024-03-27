package com.example.sse.user.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sse.notification.NotificationService;
import com.example.sse.common.JwtProvider;
import com.example.sse.user.repository.UserRepository;
import com.example.sse.user.dto.UserRequestDto;
import com.example.sse.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	@Transactional
	public void signup(UserRequestDto requestDto) {
		User user = new User(requestDto.getUsername(), requestDto.getPassword());
		userRepository.save(user);
	}

}
