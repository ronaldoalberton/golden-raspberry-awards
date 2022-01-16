package br.com.alberton.goldenraspberryawards.api.service.impl;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import br.com.alberton.goldenraspberryawards.api.domain.Movie;
import br.com.alberton.goldenraspberryawards.api.service.InitializeDatabaseService;
import br.com.alberton.goldenraspberryawards.api.service.MovieService;
import br.com.alberton.goldenraspberryawards.api.service.ProducerService;
import br.com.alberton.goldenraspberryawards.api.service.StudioService;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Service
public class InitializeDatabaseServiceImpl implements InitializeDatabaseService {

    private static final String SEPARATOR = ";";
    private static final String WINNER_STRING = "yes";
    private static final int COL_YEAR = 0;
    private static final int COL_TITLE = 1;
    private static final int COL_STUDIOS = 2;
    private static final int COL_PRODUCERS = 3;
    private static final int COL_WINNER = 4;

    @Autowired
    private ProducerService producerService;

    @Autowired
    private StudioService studioService;

    @Autowired
    private MovieService movieService;

    @Override
    @EventListener(ApplicationReadyEvent.class)
    public void loadDataFromFile() {

        try (BufferedReader fileContent = new BufferedReader(new FileReader(ResourceUtils.getFile("classpath:movielist.csv")))) {

            Iterator<String> iterator = fileContent.lines().iterator();

            iterator.next();

            while (iterator.hasNext()) {

                convertData(iterator.next().split(SEPARATOR));

            }

        } catch (IOException e) {

            e.printStackTrace();

        }

    }

    private void convertData(String[] values) {

        Integer year  = Integer.valueOf(values[COL_YEAR]);
        String title  = values[COL_TITLE].trim();
        String[] studiosName  = values[COL_STUDIOS].split(",");
        String[] producersNames  = values[COL_PRODUCERS].replace(","," and ").split(" and ");
        String winner = values.length > 4 ?  values[COL_WINNER] : "";

        Movie movie = new Movie();
        movie.setName(title);
        movie.setStudios(studioService.convertByListNames(studiosName));
        movie.setProducers(producerService.convertByListNames(producersNames));
        movie.setYear(year);
        movie.setWinner(winner.equals(WINNER_STRING));

        movieService.save(movie);

    }


}
