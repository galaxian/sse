package com.example.sse.board.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.sse.board.repository.BoardRepository;
import com.example.sse.board.dto.CreateBoardReqDto;
import com.example.sse.board.domain.Board;
import com.example.sse.user.repository.UserRepository;
import com.example.sse.user.domain.User;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BoardService {

	private final BoardRepository boardRepository;
	private final UserRepository userRepository;

	@Transactional
	public void createBoard(CreateBoardReqDto reqDto, Long userId) {
		User user = userRepository.findById(userId).orElseThrow(
			() -> new RuntimeException("aaa")
		);
		Board board = new Board(reqDto.getTitle(), reqDto.getContent(), user);
		boardRepository.save(board);
	}
}
