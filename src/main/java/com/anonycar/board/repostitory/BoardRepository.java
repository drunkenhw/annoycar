package com.anonycar.board.repostitory;

import com.anonycar.board.domain.Board;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findByName(String name);
}
