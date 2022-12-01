package com.anonycar.board.service;

import com.anonycar.board.dto.BoardRequest;
import com.anonycar.board.repostitory.BoardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@SpringBootTest
class BoardServiceTest {

    @Autowired
    private BoardService boardService;

    @Autowired
    private BoardRepository boardRepository;

    @Test
    void createBoard() {
        boardService.createBoard(new BoardRequest("익명", "ANONYMOUS"));

        Assertions.assertThat(boardRepository.findByName("익명")).isPresent();
    }
}