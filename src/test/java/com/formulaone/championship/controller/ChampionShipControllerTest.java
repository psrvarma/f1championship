package com.formulaone.championship.controller;

import com.formulaone.championship.domain.Driver;
import com.formulaone.championship.domain.Team;
import com.formulaone.championship.service.ChampionShipService;
import exception.ChampionShipException;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.ui.ModelMap;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;


@RunWith(MockitoJUnitRunner.class)
public class ChampionShipControllerTest {

    private ChampionShipController championShipController;

    @Mock
    private ChampionShipService championShipService;

    List<Driver> driverList;

    List<Team> teams;

    @Before
    public void setUp() throws ChampionShipException {
        // given
        championShipController = new ChampionShipController(championShipService) ;

        driverList = new ArrayList<>();
        when(championShipService.getTopDrivers(10)).thenReturn(driverList);

        teams = new ArrayList<>();
        when(championShipService.getTopTeams(5)).thenReturn(teams);
    }

    @Test
    public void shouldForwardThrRightViewWithModelPopulated() throws ChampionShipException {
        ModelMap modelMap = new ModelMap();
        // when
        String viewName = championShipController.getTopDriverStandinds(modelMap);

        // then
        Assert.assertThat("Index", Matchers.is(viewName));
        Assert.assertThat(modelMap , Matchers.is(modelMap));
        Assert.assertThat(modelMap.get("driverStandings") , Matchers.is(driverList));
        Assert.assertThat(modelMap.get("teamStandings") , Matchers.is(driverList));

    }

}