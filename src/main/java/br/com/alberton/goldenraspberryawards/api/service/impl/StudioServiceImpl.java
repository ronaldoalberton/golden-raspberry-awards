package br.com.alberton.goldenraspberryawards.api.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.Studio;
import br.com.alberton.goldenraspberryawards.api.repository.StudioRepository;
import br.com.alberton.goldenraspberryawards.api.service.StudioService;

import lombok.NonNull;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Service
public class StudioServiceImpl implements StudioService {

    private final @NonNull
    StudioRepository studioRepository;

    @Autowired
    public StudioServiceImpl(StudioRepository studioRepository) {
        this.studioRepository = studioRepository;
    }

    @Override
    public Optional<Studio> findByName(String name) {
        return studioRepository.findByName(name);
    }

    @Override
    public Studio save(Studio domain) {
        return studioRepository.save(domain);
    }

    @Override
    public Studio createByName(String name) {
        return new Studio(name);
    }

    @Override
    public List<Studio> findAll() {
        return studioRepository.findAll();
    }

}

