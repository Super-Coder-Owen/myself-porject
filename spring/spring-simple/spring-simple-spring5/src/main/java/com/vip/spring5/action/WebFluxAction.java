package com.vip.spring5.action;

import com.vip.spring5.model.Goods;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 *
 */
@RestController
public class WebFluxAction {

    @GetMapping("mono")
    public Mono<Goods> mono() {
        Goods goods = new Goods();
        goods.setGoodsName("owen");
        return Mono.just(goods);
    }
}
