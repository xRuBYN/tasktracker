package com.xxxweb.tasktracker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class BoardTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Board.class);
        Board board1 = new Board();
        board1.setId(UUID.randomUUID());
        Board board2 = new Board();
        board2.setId(board1.getId());
        assertThat(board1).isEqualTo(board2);
        board2.setId(UUID.randomUUID());
        assertThat(board1).isNotEqualTo(board2);
        board1.setId(null);
        assertThat(board1).isNotEqualTo(board2);
    }
}
