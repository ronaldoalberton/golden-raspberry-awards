package br.com.alberton.goldenraspberryawards.api.service;

import java.util.Optional;

import br.com.alberton.goldenraspberryawards.api.domain.AwardRange;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
public interface AwardRangeService {

    Optional<AwardRange> findProducerClassification();

}
