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

/**
 * @author Ronaldo Alberton
 * @since 1.0 (11/01/22)
 */
@Getter
@Setter
@Entity
@Table(name = "studio")
public class Studio implements IDomain {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long studio_id;

    private String name;

    @OneToOne(cascade= CascadeType.ALL)
    @JoinTable(name="movie_studio",
            joinColumns={@JoinColumn(name="studio_id", referencedColumnName="studio_id")},
            inverseJoinColumns={@JoinColumn(name="movie_id", referencedColumnName="movie_id")})
    private Movie movie;

    public Studio() {

    }

    public Studio(String name) {
        this.name = name;
    }

    @Override
    public Long getId() {
        return this.studio_id;
    }
}
