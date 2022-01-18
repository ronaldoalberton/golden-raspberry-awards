package br.com.alberton.goldenraspberryawards.api.service;

import java.util.List;

import br.com.alberton.goldenraspberryawards.api.commons.IService;
import br.com.alberton.goldenraspberryawards.api.domain.MovieProducer;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface MovieProducerService extends IService<MovieProducer> {

    List<MovieProducer> findAll();

}
