package com.wissensalt.rnd.springbootimagegallery.dao;

import com.wissensalt.rnd.springbootimagegallery.data.Method;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@Repository
public interface IMethodDAO extends JpaRepository<Method, Integer> {
}
