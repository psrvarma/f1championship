package com.formulaone.championship.service;


import com.formulaone.championship.domain.Driver;
import com.formulaone.championship.domain.Team;
import exception.ChampionShipException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChampionShipService {

    private final ExternalChampionShipService externalChampionShipService;

    @Autowired
    public ChampionShipService(ExternalChampionShipService externalChampionShipService) {
        this.externalChampionShipService = externalChampionShipService;
    }

    public List<Driver> getTopDrivers(int limit) throws ChampionShipException{
        String documentString = externalChampionShipService.getDocument();
        return getTopDrivers(documentString, limit);
    }

    public List<Team> getTopTeams(int limit) throws ChampionShipException {
        String documentString = externalChampionShipService.getDocument();
        return getTopTeams(documentString, limit);
    }

    private List<Driver> getTopDrivers(String documentString, int limit) {
        Document doc = Jsoup.parse(documentString);

        Elements driverElements = doc.select("table.motor-sport-results.msr_season_driver_results").select("tr");

        List<Driver> driversList = driverElements.stream().map(driverInfo -> {
            String driverName = driverInfo.select("td[class=msr_driver]").select("a").text();
            String deriverPointStr = driverInfo.select("td[class=msr_total]").text();

            Integer totalPoints = null;
            if (!StringUtils.isEmpty(deriverPointStr)) {
                totalPoints = Integer.valueOf(deriverPointStr);
            }

            Driver driver = null;
            if (!StringUtils.isEmpty(driverName) && !StringUtils.isEmpty(deriverPointStr)) {
                driver = new Driver(driverName, totalPoints);
            }
            return driver;

        }).filter(driver -> driver != null)
                .sorted((Comparator.comparing(Driver::getTotalPoints).reversed()))
                .limit(limit)
                .collect(Collectors.toList());

        return driversList;
    }

    private List<Team> getTopTeams(String documentString, int limit) {
        Document doc = Jsoup.parse(documentString);

        Elements teamElements = doc.select("table.motor-sport-results.msr_season_team_results").select("tr");

        List<Team> teams = teamElements.stream().map(teamInfo -> {
            String teamName = teamInfo.select("td[class=msr_team]").select("a").text();
            String teamPointStr = teamInfo.select("td[class=msr_total]").text();

            Integer totalPoints = null;
            if (!StringUtils.isEmpty(teamPointStr)) {
                totalPoints = Integer.valueOf(teamPointStr);
            }

            Team team = null;
            if (!StringUtils.isEmpty(teamName) && totalPoints != null) {
                team = new Team(teamName, totalPoints);
            }
            return team;

        }).filter(team -> team != null)
                .sorted((Comparator.comparing(Team::getTotalPoints).reversed()))
                .limit(limit)
                .collect(Collectors.toList());

        return teams;
    }

}
