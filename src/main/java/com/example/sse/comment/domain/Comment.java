package com.example.sse.comment.domain;

import com.example.sse.board.domain.Board;
import com.example.sse.user.domain.User;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Entity
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String content;

	@ManyToOne
	private Board board;

	@ManyToOne
	private User user;

	public Comment(String content, Board board, User user) {
		this.content = content;
		this.board = board;
		this.user = user;
	}
}
