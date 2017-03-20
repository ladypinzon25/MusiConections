package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.Musico;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by Lady Pinzon on 11/02/2017.
 */
public class MusicoController extends Controller{

    public CompletionStage<Result> getMusicos()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return Musico.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(musicoEntities -> {return ok(toJson(musicoEntities));}
                );
    }

    public CompletionStage<Result> getMusico(Long idM)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return Musico.FINDER.byId(idM); } ,jdbcDispatcher)
                .thenApply(musicos -> {return ok(toJson(musicos));}
                );
    }

    public CompletionStage<Result> createMusico()
    {

        JsonNode n = request().body().asJson();

        Musico musico = Json.fromJson( n , Musico.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    musico.save();
                    return musico;
                }
        ).thenApply(
                musicos -> {
                    return ok(Json.toJson(musicos));
                }
        );
    }

    public CompletionStage<Result> deleteMusico(Long idE)
    {


        return CompletableFuture.supplyAsync(
                ()->{
                    Musico musico = Musico.FINDER.byId(idE);
                    musico.delete();
                    return musico;
                }
        ).thenApply(
                musicos -> {
                    return ok(Json.toJson(musicos));
                }
        );
    }
    public CompletionStage<Result> updateMusico( Long idE)
    {

        JsonNode n = request().body().asJson();
        Musico m = Json.fromJson( n , Musico.class ) ;
        Musico antiguo = Musico.FINDER.byId(idE);

        return CompletableFuture.supplyAsync(
                ()->{
                    antiguo.setId(m.getId());
                    antiguo.setNombre(m.getNombre());
                    antiguo.setBiografia(m.getBiografia());
                    antiguo.setCorreo(m.getCorreo());

                    antiguo.update();
                    return antiguo;
                }
        ).thenApply(
                musicos -> {
                    return ok(Json.toJson(musicos));
                }
        );
    }
}
