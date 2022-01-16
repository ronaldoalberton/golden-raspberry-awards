package br.com.alberton.goldenraspberryawards.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.Movie;
import br.com.alberton.goldenraspberryawards.api.repository.MovieRepository;
import br.com.alberton.goldenraspberryawards.api.service.MovieService;

import lombok.NonNull;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Service
public class MovieServiceImpl implements MovieService {

    private final @NonNull
    MovieRepository movieRepository;

    @Autowired
    public MovieServiceImpl(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Override
    public Optional<Movie> findByName(String name) {
        return movieRepository.findByName(name);
    }

    @Override
    public List<Movie> findAll() {
        return movieRepository.findAll();
    }

    @Override
    public Movie save(Movie domain) {
        return movieRepository.save(domain);
    }

}

