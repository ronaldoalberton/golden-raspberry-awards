package br.com.alberton.goldenraspberryawards.api.service.impl;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.MovieProducer;
import br.com.alberton.goldenraspberryawards.api.domain.ProducerAward;
import br.com.alberton.goldenraspberryawards.api.domain.AwardRange;
import br.com.alberton.goldenraspberryawards.api.service.MovieProducerService;
import br.com.alberton.goldenraspberryawards.api.service.AwardRangeService;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (15/01/22)
 */
@Service
public class AwardRangeServiceImpl implements AwardRangeService {

    @Autowired
    private MovieProducerService movieProducerService;

    @Autowired
    public AwardRangeServiceImpl(MovieProducerService movieProducerService) {
        this.movieProducerService = movieProducerService;
    }

    @Override
    public Optional<AwardRange> findProducerClassification() {

        List<MovieProducer> movieProducerList =  movieProducerService.findAll().stream()
                .filter(m -> m.getMovie().getWinner())
                .sorted(Comparator.comparing(m -> m.getMovie().getYear()))
                .collect(Collectors.toList());

        //Cria a lista dos produtores
        final List<ProducerAward> awardList = new ArrayList<>();

        for (MovieProducer movieProducer : movieProducerList) {

            //Caso nao esteja na lista cria um novo produtor
            ProducerAward producerAward = new ProducerAward();
            producerAward.setId(movieProducer.getProducer().getId());
            producerAward.setProducer(movieProducer.getProducer().getName());
            producerAward.setPreviousWin(movieProducer.getMovie().getYear());
            producerAward.setFollowingWin(movieProducer.getMovie().getYear());

            //Recupera o maior ano de vitória do produdor ja adicionado na lista.
            Optional<ProducerAward> optional = awardList.stream()
                    .filter(a -> a.getId().equals(movieProducer.getProducer().getId()))
                    .max(Comparator.comparing(ProducerAward::getFollowingWin));

            //Caso encontre atribui o ano encontrado como anterior
            //e o ano do loop como proximo, visto que a lista está ordenada por ano.
            if (optional.isPresent()) {

                ProducerAward producerAwardFind = optional.get();
                producerAward.setPreviousWin(producerAwardFind.getFollowingWin());
                producerAward.setFollowingWin(movieProducer.getMovie().getYear());

            }
            awardList.add(producerAward);

        }

        //Busca na lista qual tem o menor intervalo
        int minInterval = awardList.stream()
                .filter(p -> p.getInterval() > 0)
                .min(Comparator.comparing(ProducerAward::getInterval))
                .orElse(new ProducerAward()).getInterval();

        //Busca na lista qual tem o maior intervalo
        int maxInterval = awardList.stream()
                .filter(p -> p.getInterval() > 0)
                .max(Comparator.comparing(ProducerAward::getInterval))
                .orElse(new ProducerAward())
                .getInterval();

        AwardRange awardRange = new AwardRange();

        //Filtra os produtores com o menor intervalo
        if (minInterval > 0) {
            awardRange.setMin(awardList.stream().filter(award -> award.getInterval() == minInterval).collect(Collectors.toList()));
        }

        //Filtra os produtores com o maior intervalo
        if (maxInterval > 0) {
            awardRange.setMax(awardList.stream().filter(award -> award.getInterval() == maxInterval).collect(Collectors.toList()));
        }

        //Caso nao tenha nenhum produdor classificado retorna vazio
        if (nonNull(awardRange.getMax()) || nonNull(awardRange.getMin())) {
            return Optional.of(awardRange);
        } else {
            return Optional.empty();
        }

    }

}
