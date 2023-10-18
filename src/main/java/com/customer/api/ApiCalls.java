package com.customer.api;

import com.common.vo.info.ItemVO;
import com.garbage.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

public class ApiCalls {

    @Autowired
    private WebClient.Builder webClientBuilder;

    @Value("${info.service-name}")
    private String infoServiceName;

    @Value("${info.items.request-path}")
    private String itemRequestPath;

    public Optional<ItemVO> getItemById(Long id){
        return Optional.ofNullable(webClientBuilder.build().get()
                .uri(infoServiceName + itemRequestPath+"/"+id)
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .retrieve()
                .bodyToMono(Item.class)
                .block());
    }
}
