package com.formulaone.championship;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import java.io.File;

@EnableWebMvc
@SpringBootApplication
@Slf4j
public class FormulaOneChampionshipApplication {

    public static void main(String[] args) {
        SpringApplication.run(FormulaOneChampionshipApplication.class, args);
    }

    @Bean
    public HttpClient httpClient(@Value(value = "${f1.fansite.url.cert}") String certLocation,
                                 @Value(value = "${f1.fansite.url.cert.password}") String certPassword) throws Exception {

        File file = new File(certLocation);
        SSLContext sslContext = SSLContexts.custom().loadTrustMaterial(file, certPassword.toCharArray()).build();

        HostnameVerifier hostnameVerifier = SSLConnectionSocketFactory.getDefaultHostnameVerifier();

        SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(sslContext, null, null, hostnameVerifier);

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(sslConnectionSocketFactory).build();


        return httpClient;
    }

}
