package com.anonycar.board.controller;

import com.anonycar.board.dto.BoardRequest;
import com.anonycar.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BoardController {
    private final BoardService boardService;

    @PostMapping("/boards")
    public ResponseEntity<Void> create(BoardRequest boardRequest) {
        boardService.createBoard(boardRequest);
        return ResponseEntity.noContent().build();
    }
}
