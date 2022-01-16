package br.com.alberton.goldenraspberryawards.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.Producer;
import br.com.alberton.goldenraspberryawards.api.repository.ProducerRepository;
import br.com.alberton.goldenraspberryawards.api.service.ProducerService;

import lombok.NonNull;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Service
public class ProducerServiceImpl implements ProducerService {

    private final @NonNull
    ProducerRepository producerRepository;

    @Autowired
    public ProducerServiceImpl(ProducerRepository producerRepository) {
        this.producerRepository = producerRepository;
    }

    @Override
    public Optional<Producer> findByName(String name) {
        return producerRepository.findByName(name);
    }

    @Override
    public Producer save(Producer domain) {
        return producerRepository.save(domain);
    }

    @Override
    public Producer createByName(String name) {
        return new Producer(name);
    }

    @Override
    public List<Producer> findAll() {
        return producerRepository.findAll();
    }
}

