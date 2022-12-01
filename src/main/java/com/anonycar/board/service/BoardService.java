package com.anonycar.board.service;

import com.anonycar.board.domain.Board;
import com.anonycar.board.domain.BoardType;
import com.anonycar.board.dto.BoardRequest;
import com.anonycar.board.repostitory.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    public void createBoard(BoardRequest boardRequest) {
        Board board = Board.builder()
                .name(boardRequest.getBoardName())
                .boardType(BoardType.valueOf(boardRequest.getBoardType()))
                .build();
        boardRepository.save(board);
    }
}
