package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.Project;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ProjectRepositoryWithBagRelationshipsImpl implements ProjectRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Project> fetchBagRelationships(Optional<Project> project) {
        return project.map(this::fetchUsers);
    }

    @Override
    public Page<Project> fetchBagRelationships(Page<Project> projects) {
        return new PageImpl<>(fetchBagRelationships(projects.getContent()), projects.getPageable(), projects.getTotalElements());
    }

    @Override
    public List<Project> fetchBagRelationships(List<Project> projects) {
        return Optional.of(projects).map(this::fetchUsers).orElse(Collections.emptyList());
    }

    Project fetchUsers(Project result) {
        return entityManager
            .createQuery("select project from Project project left join fetch project.users where project is :project", Project.class)
            .setParameter("project", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<Project> fetchUsers(List<Project> projects) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, projects.size()).forEach(index -> order.put(projects.get(index).getId(), index));
        List<Project> result = entityManager
            .createQuery(
                "select distinct project from Project project left join fetch project.users where project in :projects",
                Project.class
            )
            .setParameter("projects", projects)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
