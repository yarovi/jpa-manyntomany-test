package com.yasmani.manytomany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yasmani.manytomany.model.Tag;

public interface TagRepository extends JpaRepository<Tag, Long> {

	List<Tag> findTagsByTutorialsId(Long tutorialId);
}
