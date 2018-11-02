package com.formulaone.championship.service;

import exception.ChampionShipException;
import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

@Profile(value = "test")
@Service
public class DummyExternalChampionShipService implements ExternalChampionShipService {

    @Override
    public String getDocument() throws ChampionShipException {
        ClassLoader classLoader = getClass().getClassLoader();
        String document;
        try {
            document = IOUtils.toString(classLoader.getResourceAsStream("Sample.html"));
        } catch (Exception e) {
            throw new ChampionShipException("Exception while getting the sample document", e);
        }

        return document;
    }

}
