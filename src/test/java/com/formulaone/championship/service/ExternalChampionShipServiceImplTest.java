package com.formulaone.championship.service;

import exception.ChampionShipException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ExternalChampionShipServiceImplTest {

    private ExternalChampionShipService externalChampionShipService;

    @Mock
    private HttpClient httpClient;

    @Mock
    private HttpResponse httpResponse;

    @Before
    public void setUp() throws Exception {
        // given
        externalChampionShipService = new ExternalChampionShipServiceImpl("URL", httpClient);

        HttpEntity entity = mock(HttpEntity.class);

        when(httpResponse.getEntity()).thenReturn(entity);

        InputStream stream = new ByteArrayInputStream("test".getBytes(StandardCharsets.UTF_8));
        when(entity.getContent()).thenReturn(stream);

        when(httpClient.execute(any(HttpUriRequest.class))).thenReturn(httpResponse);
    }

    @Test
    public void shouldGetDocumentFromExternalResource() throws ChampionShipException, Exception {
        // when
        String document  = externalChampionShipService.getDocument();

        // then
        ArgumentCaptor<HttpUriRequest> argumentCaptor= ArgumentCaptor.forClass(HttpUriRequest.class);

        verify(httpClient).execute(argumentCaptor.capture());
        verify(httpResponse).getEntity();

        Assert.assertEquals("URL", argumentCaptor.getValue().getURI().getPath());
        Assert.assertTrue(document.equals("test"));

    }

}