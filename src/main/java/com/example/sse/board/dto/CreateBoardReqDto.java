package com.example.sse.board.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CreateBoardReqDto {

	private String title;
	private String content;
}
