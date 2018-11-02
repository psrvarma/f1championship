package com.formulaone.championship.controller;

import com.formulaone.championship.domain.Driver;
import com.formulaone.championship.domain.Team;
import com.formulaone.championship.service.ChampionShipService;
import exception.ChampionShipException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Slf4j
@Controller
public class ChampionShipController {

    private final ChampionShipService championShipService;

    @Autowired
    public ChampionShipController(ChampionShipService championShipService) {
        this.championShipService = championShipService;
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String getTopDriverStandinds(ModelMap modeMap) throws ChampionShipException {

        List<Driver> drivers = championShipService.getTopDrivers(10);
        modeMap.put("driverStandings", drivers);

        List<Team> teams = championShipService.getTopTeams(5);
        modeMap.put("teamStandings", teams);

        return "Index";
    }

    @ExceptionHandler({ChampionShipException.class})
    public String onException() {
        return "Error";
    }
}
