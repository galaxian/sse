package com.example.sse.user.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class TokenResDto {
	private String accessToken;

	public TokenResDto(String accessToken) {
		this.accessToken = accessToken;
	}
}
