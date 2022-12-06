package com.wissensalt.rnd.springbootimagegallery.dao;

import com.wissensalt.rnd.springbootimagegallery.data.Ggee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
/**
 * Created on 5/17/21.
 *
 * Pritesh
 */
@Repository
public interface IGgeeDAO extends JpaRepository<Ggee, Integer> {

   @Query("select g from Ggee g where g.id_method = ?1")
   List<Ggee> findByIdMethod(int id_method);
}
