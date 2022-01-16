package br.com.alberton.goldenraspberryawards;

import static java.util.Objects.nonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.alberton.goldenraspberryawards.api.domain.AwardRange;
import br.com.alberton.goldenraspberryawards.api.domain.ProducerAward;
import br.com.alberton.goldenraspberryawards.run.GoldenRaspberryAwardsApplication;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = GoldenRaspberryAwardsApplication.class)
class GoldenRaspberryAwardsApplicationTests {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void testAwardRange() {

        ResponseEntity<AwardRange> response = this.testRestTemplate.getForEntity("/awardrange", AwardRange.class);

        assertEquals(response.getStatusCode(), HttpStatus.OK);

        if (nonNull(response.getBody())) {

            AwardRange awardRange = response.getBody();

            if (nonNull(awardRange.getMin()) && !awardRange.getMin().isEmpty()) {

                ProducerAward producerAward = awardRange.getMin().get(0);

                assertEquals(producerAward.getProducer(), "Joel Silver");
                assertEquals(producerAward.getInterval(), 1);
                assertEquals(producerAward.getPreviousWin(), 1990);
                assertEquals(producerAward.getFollowingWin(), 1991);

            }

            if (nonNull(awardRange.getMax()) && !awardRange.getMax().isEmpty()) {

                ProducerAward producerAward = awardRange.getMax().get(0);

                assertEquals(producerAward.getProducer(), "Matthew Vaughn");
                assertEquals(producerAward.getInterval(), 13);
                assertEquals(producerAward.getPreviousWin(), 2002);
                assertEquals(producerAward.getFollowingWin(), 2015);

            }

        }

    }

}
