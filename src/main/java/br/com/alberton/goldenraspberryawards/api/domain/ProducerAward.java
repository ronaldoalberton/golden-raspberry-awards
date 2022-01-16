package br.com.alberton.goldenraspberryawards.api.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author Ronaldo Alberton
 * @since 1.0 (15/01/22)
 */
@Getter
@Setter
@ToString
@JsonPropertyOrder({"producer", "interval","previousWin", "followingWin"})
public class ProducerAward {

    @JsonIgnore
    private Long id;

    private String producer;

    private int interval;

    private int previousWin;

    private int followingWin;

    public int getInterval() {
        return (followingWin - previousWin);
    }

}
