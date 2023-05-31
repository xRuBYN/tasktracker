package com.xxxweb.tasktracker.domain;

import static org.assertj.core.api.Assertions.assertThat;

import com.xxxweb.tasktracker.web.rest.TestUtil;
import java.util.UUID;
import org.junit.jupiter.api.Test;

class ProjectTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Project.class);
        Project project1 = new Project();
        project1.setId(UUID.randomUUID());
        Project project2 = new Project();
        project2.setId(project1.getId());
        assertThat(project1).isEqualTo(project2);
        project2.setId(UUID.randomUUID());
        assertThat(project1).isNotEqualTo(project2);
        project1.setId(null);
        assertThat(project1).isNotEqualTo(project2);
    }
}
