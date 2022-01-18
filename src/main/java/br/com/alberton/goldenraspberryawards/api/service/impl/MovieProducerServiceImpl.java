package br.com.alberton.goldenraspberryawards.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.MovieProducer;
import br.com.alberton.goldenraspberryawards.api.repository.MovieProducerRepository;
import br.com.alberton.goldenraspberryawards.api.service.MovieProducerService;

import lombok.NonNull;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Service
public class MovieProducerServiceImpl implements MovieProducerService {

    private final @NonNull
    MovieProducerRepository movieProducerRepository;

    @Autowired
    public MovieProducerServiceImpl(MovieProducerRepository movieProducerRepository) {
        this.movieProducerRepository = movieProducerRepository;
    }

    @Override
    public Optional<MovieProducer> findByName(String name) {
        return Optional.empty();
    }

    @Override
    public MovieProducer save(MovieProducer domain) {
        return movieProducerRepository.save(domain);
    }

    @Override
    public List<MovieProducer> findAll() {
        return movieProducerRepository.findAll();
    }

}
