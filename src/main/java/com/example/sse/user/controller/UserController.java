package com.example.sse.user.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sse.user.dto.TokenResDto;
import com.example.sse.user.dto.UserRequestDto;
import com.example.sse.user.service.UserService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserController {

	private final UserService userService;

	@PostMapping("/signup")
	public ResponseEntity<Void> signup(@RequestBody UserRequestDto requestDto) {
		userService.signup(requestDto);
		return ResponseEntity.ok().build();
	}

	@GetMapping("/login")
	public ResponseEntity<TokenResDto> login(@RequestBody UserRequestDto requestDto) {
		TokenResDto resDto = userService.login(requestDto);
		return ResponseEntity.ok().body(resDto);
	}
}
