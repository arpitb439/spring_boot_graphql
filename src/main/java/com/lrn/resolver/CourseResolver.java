package com.lrn.resolver;

import com.lrn.model.Courses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lrn.model.Author;
import com.lrn.repository.AuthorRepository;

import graphql.kickstart.tools.GraphQLResolver;

@Component
public class CourseResolver implements GraphQLResolver<Courses> {

	@Autowired
	private AuthorRepository authorRepository;

	public CourseResolver(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Author getAuthor(Courses courses) {
		return authorRepository.findById(courses.getAuthor().getId()).orElseThrow(null);
	}
}
