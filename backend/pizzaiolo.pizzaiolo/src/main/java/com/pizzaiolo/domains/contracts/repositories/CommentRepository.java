package com.pizzaiolo.domains.contracts.repositories;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.pizzaiolo.domains.entities.Comment;

@RepositoryRestResource(exported = false)
public interface CommentRepository extends JpaRepository<Comment, Integer>{
	<T> List<T> findByIdCommentIsNotNull(Class<T> type);
	<T> Iterable<T> findByIdCommentIsNotNull(Sort sort, Class<T> type);
	<T> Page<T> findByIdCommentIsNotNull(Pageable pageable, Class<T> type);

}
