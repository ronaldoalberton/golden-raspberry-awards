package br.com.alberton.goldenraspberryawards.api.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import br.com.alberton.goldenraspberryawards.api.commons.IDomain;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Getter
@Setter
@Entity
@Table(name = "movie_producer")
public class MovieProducer implements IDomain {

    @Id
    @Column(name = "movie_producer_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long movieProducerId;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "producer_id")
    private Producer producer;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    public MovieProducer() {

    }

    @Override
    public Long getId() {
        return this.movieProducerId;
    }
}
