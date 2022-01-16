package br.com.alberton.goldenraspberryawards.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alberton.goldenraspberryawards.api.domain.Movie;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface MovieRepository extends JpaRepository<Movie, Long> {

    Optional<Movie> findByName(String name);

}
