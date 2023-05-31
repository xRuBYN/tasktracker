package com.xxxweb.tasktracker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class IssueTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Issue.class);
        Issue issue1 = new Issue();
        issue1.setId(UUID.randomUUID());
        Issue issue2 = new Issue();
        issue2.setId(issue1.getId());
        assertThat(issue1).isEqualTo(issue2);
        issue2.setId(UUID.randomUUID());
        assertThat(issue1).isNotEqualTo(issue2);
        issue1.setId(null);
        assertThat(issue1).isNotEqualTo(issue2);
    }
}
