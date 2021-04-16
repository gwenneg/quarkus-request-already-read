package org.acme;

import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;

@ApplicationScoped
public class RequestAlreadyRead {

    @PostConstruct
    public void init(@Observes Router router) {
        router.route().handler(BodyHandler.create());
        router.get("/test").handler(this::findTriggers);
    }

    public void findTriggers(RoutingContext routingContext) {
        routingContext.vertx().executeBlocking(future -> {
            future.complete("Hello world!");
        }, res -> routingContext.response()
                .setStatusCode(OK.code())
                .end(res.result().toString()));
    }
}
