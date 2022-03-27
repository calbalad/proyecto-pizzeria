package com.pizzaiolo.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pizzaiolo.domains.entities.Ingredient;
@RepositoryRestResource(exported = false)
public interface IngredientRepository extends JpaRepository<Ingredient, Integer>{
	<T> List<T> findByIdIngredientIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdIngredientIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdIngredientIsNotNull(Pageable pageable, Class<T> type);

}
