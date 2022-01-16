package br.com.alberton.goldenraspberryawards.api.commons;

import java.util.List;
import java.util.Optional;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface IService<T> {

    Optional<T> findByName(String name);

    List<T> findAll();

    T save(T domain);

}
