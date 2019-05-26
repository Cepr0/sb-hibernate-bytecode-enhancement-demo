package io.github.cepr0.demo.repo;

import io.github.cepr0.demo.model.DataProjection;
import io.github.cepr0.demo.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ModelRepo extends JpaRepository<Model, Integer> {

	@Query("select m from Model m fetch all properties where m.id = ?1")
	Optional<Model> getFullById(Integer id);

	@Query("select m.data as data from Model m where m.id = ?1")
	Optional<DataProjection> getDataById(Integer id);
}
