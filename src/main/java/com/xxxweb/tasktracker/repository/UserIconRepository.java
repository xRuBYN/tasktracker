package com.xxxweb.tasktracker.repository;

import com.xxxweb.tasktracker.domain.UserIcon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIconRepository extends JpaRepository<UserIcon, Long> {}
