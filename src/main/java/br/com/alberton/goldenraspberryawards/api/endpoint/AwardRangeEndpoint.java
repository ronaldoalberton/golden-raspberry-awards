package br.com.alberton.goldenraspberryawards.api.endpoint;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alberton.goldenraspberryawards.api.domain.AwardRange;
import br.com.alberton.goldenraspberryawards.api.service.AwardRangeService;

import lombok.NonNull;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (15/01/22)
 */
@RestController
@RequestMapping("/awardrange")
public class AwardRangeEndpoint {


    private final @NonNull
    AwardRangeService awardRangeService;

    @Autowired
    public AwardRangeEndpoint(AwardRangeService awardRangeService) {
        this.awardRangeService = awardRangeService;
    }

    @GetMapping
    public ResponseEntity<AwardRange> getAwardRange() {

        Optional<AwardRange> result = awardRangeService.findProducerClassification();

        return result.isEmpty() ? ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.ok(result.get());

    }

}
