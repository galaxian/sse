package com.example.sse.comment.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sse.comment.domain.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
	List<Comment> findAllByBoardId(Long boardId);
}
