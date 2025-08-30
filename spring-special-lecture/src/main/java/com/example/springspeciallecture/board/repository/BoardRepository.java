package com.example.springspeciallecture.board.repository;

import com.example.springspeciallecture.board.entity.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BoardRepository extends JpaRepository<Board, Long> {

    @Query("select b from board b join fetch b.writer order by b.boardId desc")
    Page<Board> findAllWithWriter(PageRequest pageRequest);
}
