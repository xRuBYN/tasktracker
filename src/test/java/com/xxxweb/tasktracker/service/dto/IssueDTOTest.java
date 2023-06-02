package com.xxxweb.tasktracker.service.dto;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class IssueDTOTest {

    @Test
    void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(IssueDTO.class);
        IssueDTO issueDTO1 = new IssueDTO();
        issueDTO1.setId(UUID.randomUUID());
        IssueDTO issueDTO2 = new IssueDTO();
        assertThat(issueDTO1).isNotEqualTo(issueDTO2);
        issueDTO2.setId(issueDTO1.getId());
        assertThat(issueDTO1).isEqualTo(issueDTO2);
        issueDTO2.setId(UUID.randomUUID());
        assertThat(issueDTO1).isNotEqualTo(issueDTO2);
        issueDTO1.setId(null);
        assertThat(issueDTO1).isNotEqualTo(issueDTO2);
    }
}
