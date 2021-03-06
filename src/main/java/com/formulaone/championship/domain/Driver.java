package com.formulaone.championship.domain;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class Driver {

    private String name;

    private Integer totalPoints;

    public Driver(String name, Integer totalPoints) {
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
