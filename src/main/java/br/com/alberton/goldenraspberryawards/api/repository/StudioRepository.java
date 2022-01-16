package br.com.alberton.goldenraspberryawards.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alberton.goldenraspberryawards.api.domain.Studio;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface StudioRepository extends JpaRepository<Studio, Long> {

    Optional<Studio> findByName(String name);

}
