package br.com.alberton.goldenraspberryawards.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alberton.goldenraspberryawards.api.domain.MovieProducer;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface MovieProducerRepository extends JpaRepository<MovieProducer, Long> {


}
