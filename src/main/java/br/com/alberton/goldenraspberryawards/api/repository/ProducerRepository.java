package br.com.alberton.goldenraspberryawards.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alberton.goldenraspberryawards.api.domain.Producer;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface ProducerRepository extends JpaRepository<Producer, Long> {

    Optional<Producer> findByName(String name);

}
