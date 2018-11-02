package com.formulaone.championship.service;

import exception.ChampionShipException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.client.methods.RequestBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.net.URI;

@Slf4j
@Service
@Profile(value = "production")
public class ExternalChampionShipServiceImpl implements ExternalChampionShipService {

    private final String url;

    private final HttpClient httpClient;

    @Autowired
    public ExternalChampionShipServiceImpl(@Value(value = "${f1.fansite.url}") String url,
                                           HttpClient httpClient) {
        this.url = url;
        this.httpClient = httpClient;
    }

    @Override
    public String getDocument() throws ChampionShipException {

        String document;
        try {

            HttpUriRequest request = RequestBuilder.get().setUri(new URI(url)).build();
            HttpResponse response = httpClient.execute(request);

            document = IOUtils.toString(response.getEntity().getContent());

        } catch (Throwable throwable) {
            log.error("Exception while getting the document from external resource", throwable);
            throw new ChampionShipException("Exception while getting the document from the external resource", throwable);
        }

        return document;
    }

}
