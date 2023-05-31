package com.xxxweb.tasktracker.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ColumnEntityDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(ColumnEntityDTO.class);
        ColumnEntityDTO columnEntityDTO1 = new ColumnEntityDTO();
        columnEntityDTO1.setId(UUID.randomUUID());
        ColumnEntityDTO columnEntityDTO2 = new ColumnEntityDTO();
        assertThat(columnEntityDTO1).isNotEqualTo(columnEntityDTO2);
        columnEntityDTO2.setId(columnEntityDTO1.getId());
        assertThat(columnEntityDTO1).isEqualTo(columnEntityDTO2);
        columnEntityDTO2.setId(UUID.randomUUID());
        assertThat(columnEntityDTO1).isNotEqualTo(columnEntityDTO2);
        columnEntityDTO1.setId(null);
        assertThat(columnEntityDTO1).isNotEqualTo(columnEntityDTO2);
    }
}
