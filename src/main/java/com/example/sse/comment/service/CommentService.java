package com.example.sse.comment.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sse.board.repository.BoardRepository;
import com.example.sse.board.domain.Board;
import com.example.sse.comment.repository.CommentRepository;
import com.example.sse.comment.dto.CreateCommentReqDto;
import com.example.sse.comment.domain.Comment;
import com.example.sse.user.repository.UserRepository;
import com.example.sse.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class CommentService {

	private final CommentRepository commentRepository;
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	@Transactional
	public void createComment(CreateCommentReqDto reqDto, Long userId, Long boardId) {
		User user = userRepository.findById(userId).orElseThrow(
			() -> new RuntimeException("")
		);

		Board board = boardRepository.findById(boardId).orElseThrow(
			() -> new RuntimeException("")
		);

		Comment comment = new Comment(reqDto.getContent(), board, user);
		commentRepository.save(comment);
	}
}
