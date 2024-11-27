package com.lrn.resolver;

import com.lrn.model.Lesson;
import com.lrn.repository.LessonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lrn.model.Author;
import com.lrn.model.Courses;
import com.lrn.repository.AuthorRepository;
import com.lrn.repository.CourseRepository;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

import java.util.List;

@Component
public class Query implements GraphQLQueryResolver {
	private AuthorRepository authorRepository;
	private CourseRepository courseRepository;
	private LessonRepository lessonRepository;
	
	GraphQLScalarType longScalar =
      ExtendedScalars.newAliasedScalar("Long")
          .aliasedScalar(ExtendedScalars.GraphQLLong)
          .build();

	@Autowired
	public Query(AuthorRepository authorRepository, CourseRepository courseRepository, LessonRepository lessonRepository) {
		this.authorRepository = authorRepository;
		this.courseRepository = courseRepository;
		this.lessonRepository = lessonRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public Iterable<Courses> findAllCourses() {

		List<Courses> courses = courseRepository.findAll();
		return courses;
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public long countCourses() {
		return courseRepository.count();
	}

	// Lessons
	public Iterable<Lesson> findAllLessons() {
		return lessonRepository.findAll();
	}

	public long countLessons() {
		return lessonRepository.count();
	}

}
