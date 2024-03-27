package com.example.sse.user.service;

import java.util.HashMap;
import java.util.Objects;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sse.common.JwtProvider;
import com.example.sse.user.dto.TokenResDto;
import com.example.sse.user.repository.UserRepository;
import com.example.sse.user.dto.UserRequestDto;
import com.example.sse.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;
	private final JwtProvider jwtProvider;

	@Transactional
	public void signup(UserRequestDto requestDto) {
		User user = new User(requestDto.getUsername(), requestDto.getPassword());
		userRepository.save(user);
	}

	@Transactional(readOnly = true)
	public TokenResDto login(UserRequestDto requestDto) {
		String username = requestDto.getUsername();
		User user = userRepository.findByUsername(username).orElseThrow(
			() -> new RuntimeException("")
		);

		if (!Objects.equals(user.getPassword(), requestDto.getPassword())) {
			throw new RuntimeException("");
		}

		String accessToken = jwtProvider.createAccessToken(user.getId(), new HashMap<>());
		return new TokenResDto(accessToken);
	}
}
