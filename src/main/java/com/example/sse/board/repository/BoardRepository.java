package com.example.sse.board.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.sse.board.domain.Board;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
}
