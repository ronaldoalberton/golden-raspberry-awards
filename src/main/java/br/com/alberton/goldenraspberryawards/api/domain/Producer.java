package br.com.alberton.goldenraspberryawards.api.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToOne;
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
@Table(name = "producer")
public class Producer implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long producer_id;

    private String name;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinTable(name="movie_producer",
            joinColumns={@JoinColumn(name="producer_id", referencedColumnName="producer_id")},
            inverseJoinColumns={@JoinColumn(name="movie_id", referencedColumnName="movie_id")})
    private Movie movie;

    public Producer() {

    }

    public Producer(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return this.producer_id;
    }

    @Override
    public String toString() {
        return this.name;
    }

}
