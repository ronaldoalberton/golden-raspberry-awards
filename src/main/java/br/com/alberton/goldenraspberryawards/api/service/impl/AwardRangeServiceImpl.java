package br.com.alberton.goldenraspberryawards.api.service.impl;

import static java.util.Objects.nonNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alberton.goldenraspberryawards.api.domain.Movie;
import br.com.alberton.goldenraspberryawards.api.domain.Producer;
import br.com.alberton.goldenraspberryawards.api.domain.ProducerAward;
import br.com.alberton.goldenraspberryawards.api.domain.AwardRange;
import br.com.alberton.goldenraspberryawards.api.service.MovieService;
import br.com.alberton.goldenraspberryawards.api.service.AwardRangeService;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (15/01/22)
 */
@Service
public class AwardRangeServiceImpl implements AwardRangeService {

    @Autowired
    private MovieService movieService;

    @Autowired
    public AwardRangeServiceImpl(MovieService movieService) {
        this.movieService = movieService;
    }

    @Override
    public Optional<AwardRange> findProducerClassification() {

        List<Movie> movies = movieService.findAll().stream().filter(Movie::getWinner).collect(Collectors.toList());

        //Cria a lista dos produtores
        final List<ProducerAward> awardList = new ArrayList<>();

        for (Movie movie : movies) {

            for (Producer producer : movie.getProducers()) {

                //Verifica se o produtor que esta no loop ja foi adicionado na lista;
                Optional<ProducerAward> optional = awardList.stream().filter(a -> a.getId().equals(producer.getId())).findFirst();

                ProducerAward producerAward;

                //Caso nao esteja na lista cria um novo produtor
                if (optional.isEmpty()) {

                    producerAward = new ProducerAward();
                    producerAward.setId(producer.getId());
                    producerAward.setProducer(producer.getName());
                    producerAward.setPreviousWin(movie.getYear());
                    producerAward.setFollowingWin(movie.getYear());

                    awardList.add(producerAward);

                } else {

                    //Caso ja esteja recupera da lista
                    producerAward = optional.get();

                    //Faz a validacao se o ano do filme que está no loop menos o proximo ano é um um intervalo
                    //maior do que o que ja esta armazenado, caso seja atribui os valores.
                    if ((movie.getYear() - producerAward.getFollowingWin()) >= producerAward.getInterval()) {

                        producerAward.setPreviousWin(producerAward.getFollowingWin());
                        producerAward.setFollowingWin(movie.getYear());

                    }

                }

            }

        }

        //Busca na lista qual tem o menor intervalo
        int minInterval = awardList.stream().filter(p -> p.getInterval() > 0).min(Comparator.comparing(ProducerAward::getInterval)).orElse(new ProducerAward())
                .getInterval();

        //Busca na lista qual tem o maior intervalo
        int maxInterval = awardList.stream().filter(p -> p.getInterval() > 0).max(Comparator.comparing(ProducerAward::getInterval)).orElse(new ProducerAward())
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
