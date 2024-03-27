package com.example.sse.board.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sse.board.service.BoardService;
import com.example.sse.board.dto.CreateBoardReqDto;
import com.example.sse.common.interceptor.Authenticated;
import com.example.sse.common.resolver.UserId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class BoardController {

	private final BoardService boardService;

	@Authenticated
	@PostMapping("/board")
	public ResponseEntity<Void> createBoard(CreateBoardReqDto reqDto, @UserId String userId) {
		boardService.createBoard(reqDto, Long.valueOf(userId));
		return ResponseEntity.ok().build();
	}
}
