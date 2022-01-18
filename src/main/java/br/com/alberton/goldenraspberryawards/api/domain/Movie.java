package br.com.alberton.goldenraspberryawards.api.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.alberton.goldenraspberryawards.api.commons.IDomain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Getter
@Setter
@Entity
@ToString
@Table(name = "movie")
public class Movie implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="movie_id")
    private Long movieId;

    private String name;

    private Integer year;

    private Boolean winner;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="movie_producer",
            joinColumns={@JoinColumn(name="movie_id", referencedColumnName="movie_id")},
            inverseJoinColumns={@JoinColumn(name="producer_id", referencedColumnName="producer_id")})
    private Set<Producer> producers;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(name="movie_studio",
            joinColumns={@JoinColumn(name="movie_id", referencedColumnName="movie_id")},
            inverseJoinColumns={@JoinColumn(name="studio_id", referencedColumnName="studio_id")})
    private Set<Studio> studios;

    @Override
    public Long getId() {
        return this.movieId;
    }
}
