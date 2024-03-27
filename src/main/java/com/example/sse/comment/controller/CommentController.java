package com.example.sse.comment.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.sse.comment.service.CommentService;
import com.example.sse.comment.dto.CreateCommentReqDto;
import com.example.sse.common.interceptor.Authenticated;
import com.example.sse.common.resolver.UserId;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {

	private final CommentService commentService;

	@Authenticated
	@PostMapping("/board/{boardId}/comment")
	public ResponseEntity<Void> createComment(@RequestBody CreateCommentReqDto reqDto, @UserId String userId,
		@PathVariable("boardId") Long boardId) {
		commentService.createComment(reqDto, Long.valueOf(userId), boardId);
		return ResponseEntity.ok().build();
	}
}
