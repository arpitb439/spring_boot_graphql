package com.lrn.resolver;

import java.util.Optional;

import com.lrn.model.Courses;
import com.lrn.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.lrn.model.Author;
import com.lrn.repository.AuthorRepository;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
	private AuthorRepository authorRepository;
	private CourseRepository courseRepository;

	@Autowired
	public Mutation(AuthorRepository authorRepository, CourseRepository courseRepository) {
		this.authorRepository = authorRepository;
		this.courseRepository = courseRepository;
	}

	public Author createAuthor(String name, Integer age) {
		Author author = new Author();
		author.setName(name);
		author.setAge(age);

		authorRepository.save(author);
		return author;
	}

	public Courses createCourse(String title, String description, Long authorId) {
		Courses courses = new Courses();
		courses.setAuthor(authorRepository.findById(authorId).get());
		courses.setTitle(title);
		courses.setDescription(description);
		courseRepository.save(courses);
		return courses;
	}

	public boolean deleteCourse(Long id) {
		courseRepository.deleteById(id);
		return true;
	}

	public Courses updateCourse(Long id, String title, String description) throws EntityNotFoundException {
		Optional<Courses> optCourse = courseRepository.findById(id);

		if (optCourse.isPresent()) {
			Courses courses = optCourse.get();

			if (title != null)
				courses.setTitle(title);
			if (description != null)
				courses.setDescription(description);

			courseRepository.save(courses);
			return courses;
		}

		throw new EntityNotFoundException("Not found Course to update!");
	}
}
