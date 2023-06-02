package com.xxxweb.tasktracker.service;

import com.xxxweb.tasktracker.domain.User;
import com.xxxweb.tasktracker.domain.User_;
import com.xxxweb.tasktracker.repository.UserRepository;
import com.xxxweb.tasktracker.service.criteria.UserCriteria;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tech.jhipster.service.QueryService;

@Service
@Transactional(readOnly = true)
public class UserQueryService extends QueryService<User> {

    private final Logger log = LoggerFactory.getLogger(UserQueryService.class);

    private final UserRepository userRepository;

    public UserQueryService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Return a {@link Page} of foreign {@link User} which matches the criteria from the database.
     *
     * @param criteria The object which holds all the filters, which the entities should match.
     * @param pageable The page, which should be returned.
     * @return the matching entities.
     */
    public Page<User> findByOrCriteria(UserCriteria criteria, Pageable pageable) {
        log.debug("find by or criteria : {}, page: {}", criteria, pageable);
        final Specification<User> specification = createOrSpecification(criteria);
        return userRepository.findAll(specification, pageable);
    }

    protected Specification<User> createOrSpecification(UserCriteria criteria) {
        Specification<User> specification = Specification.where(null);
        if (criteria != null) {
            if (criteria.getLastName() != null) {
                specification = specification.or(buildStringSpecification(criteria.getLastName(), User_.lastName));
            }
            if (criteria.getFirstName() != null) {
                specification = specification.or(buildStringSpecification(criteria.getFirstName(), User_.firstName));
            }
            if (criteria.getEmail() != null) {
                specification = specification.or(buildStringSpecification(criteria.getEmail(), User_.email));
            }
        }
        return specification;
    }
}
