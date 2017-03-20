package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.PropuestaMusical;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by Lady Pinzon on 11/02/2017.
 */
public class PropuestaMusicalController extends Controller{

    public CompletionStage<Result> getPropuestaMusicals()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return PropuestaMusical.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(PropuestaMusicalEntities -> {return ok(toJson(PropuestaMusicalEntities));}
                );
    }

    public CompletionStage<Result> getPropuestaMusical(Long idM)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return PropuestaMusical.FINDER.byId(idM); } ,jdbcDispatcher)
                .thenApply(PropuestaMusicals -> {return ok(toJson(PropuestaMusicals));}
                );
    }

    public CompletionStage<Result> createPropuestaMusical()
    {

        JsonNode n = request().body().asJson();

        PropuestaMusical PropuestaMusical = Json.fromJson( n , models.PropuestaMusical.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    PropuestaMusical.save();
                    return PropuestaMusical;
                }
        ).thenApply(
                PropuestaMusicals -> {
                    return ok(Json.toJson(PropuestaMusicals));
                }
        );
    }

    public CompletionStage<Result> deletePropuestaMusical(Long idE)
    {


        return CompletableFuture.supplyAsync(
                ()->{
                    PropuestaMusical propuestaMusical = PropuestaMusical.FINDER.byId(idE);
                    propuestaMusical.delete();
                    return propuestaMusical;
                }
        ).thenApply(
                PropuestaMusicals -> {
                    return ok(Json.toJson(PropuestaMusicals));
                }
        );
    }
    public CompletionStage<Result> updatePropuestaMusical( Long idE)
    {

        JsonNode n = request().body().asJson();
        PropuestaMusical m = Json.fromJson( n , PropuestaMusical.class ) ;
        PropuestaMusical antiguo = PropuestaMusical.FINDER.byId(idE);

        return CompletableFuture.supplyAsync(
                ()->{
                    antiguo.setId(m.getId());
                    antiguo.setCanciones(m.getCanciones());
                    antiguo.setVideos(m.getVideos());

                    antiguo.update();
                    return antiguo;
                }
        ).thenApply(
                PropuestaMusicals -> {
                    return ok(Json.toJson(PropuestaMusicals));
                }
        );
    }
}
