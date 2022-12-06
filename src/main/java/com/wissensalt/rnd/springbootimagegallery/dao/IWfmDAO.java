package com.wissensalt.rnd.springbootimagegallery.dao;

import com.wissensalt.rnd.springbootimagegallery.data.Wfm;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@Repository
public interface IWfmDAO extends JpaRepository<Wfm, Integer> {
	Wfm findTopByOrderByIdDesc();
	Wfm findById(int id);
}
