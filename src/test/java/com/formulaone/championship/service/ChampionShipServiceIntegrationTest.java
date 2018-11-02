package com.formulaone.championship.service;

import com.formulaone.championship.BaseFormulaOneChampionshipApplicationTest;
import com.formulaone.championship.domain.Driver;
import com.formulaone.championship.domain.Team;
import exception.ChampionShipException;
import org.hamcrest.collection.IsIterableContainingInOrder;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
public class ChampionShipServiceIntegrationTest extends BaseFormulaOneChampionshipApplicationTest {

    @Autowired
    private ChampionShipService championShipService;

    @Test
    public void shouldReturnTopTenDriversInSortedOrder() throws ChampionShipException {
        //when
        List<Driver> drivers = championShipService.getTopDrivers(10);

        // then
        Assert.assertEquals(drivers.size(), 10);
        Assert.assertThat(drivers, IsIterableContainingInOrder.contains(drivers().toArray()));
    }

    @Test
    public void shouldReturnTopTenFiveTeamsInSortedOrder() throws ChampionShipException {
        // when
        List<Team> teams = championShipService.getTopTeams(5);

        // then
        Assert.assertEquals(teams.size(), 5);
        Assert.assertThat(teams, IsIterableContainingInOrder.contains(teams().toArray()));
    }

    private List<Driver> drivers() {
        List<Driver> drivers = new ArrayList<>();
        drivers.add(new Driver("Lewis Hamilton", 358));
        drivers.add(new Driver("Sebastian Vettel", 294));
        drivers.add(new Driver("Kimi Räikkönen", 236));
        drivers.add(new Driver("Valtteri Bottas", 227));
        drivers.add(new Driver("Max Verstappen", 216));
        drivers.add(new Driver("Daniel Ricciardo", 146));
        drivers.add(new Driver("Nico Hülkenberg", 69));
        drivers.add(new Driver("Sergio Pérez", 57));
        drivers.add(new Driver("Kevin Magnussen", 53));
        drivers.add(new Driver("Fernando Alonso", 50));

        return drivers;
    }

    private List<Team> teams() {
        List<Team> teams = new ArrayList<>();
        teams.add(new Team("Mercedes", 585));
        teams.add(new Team("Ferrari", 530));
        teams.add(new Team("Red Bull", 362));
        teams.add(new Team("Renault", 114));
        teams.add(new Team("Haas F1 Team", 84));
        return teams;
    }


}