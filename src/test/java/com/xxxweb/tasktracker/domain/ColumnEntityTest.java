package com.xxxweb.tasktracker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ColumnEntityTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColumnEntity.class);
        ColumnEntity columnEntity1 = new ColumnEntity();
        columnEntity1.setId(UUID.randomUUID());
        ColumnEntity columnEntity2 = new ColumnEntity();
        columnEntity2.setId(columnEntity1.getId());
        assertThat(columnEntity1).isEqualTo(columnEntity2);
        columnEntity2.setId(UUID.randomUUID());
        assertThat(columnEntity1).isNotEqualTo(columnEntity2);
        columnEntity1.setId(null);
        assertThat(columnEntity1).isNotEqualTo(columnEntity2);
    }
}
