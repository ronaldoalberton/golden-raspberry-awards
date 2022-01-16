package br.com.alberton.goldenraspberryawards.api.domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Getter
@Setter
public class AwardRange {

    private List<ProducerAward> min;

    private List<ProducerAward> max;

}
