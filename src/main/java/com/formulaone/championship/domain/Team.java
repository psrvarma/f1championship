package com.formulaone.championship.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Team {

    private String name;

    private Integer totalPoints;

    public Team(String name, Integer totalPoints) {
        this.name = name;
        this.totalPoints = totalPoints;
    }

    public String getName() {
        return name;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }


}
