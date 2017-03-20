package controllers;

import akka.dispatch.MessageDispatcher;
import com.fasterxml.jackson.databind.JsonNode;
import dispatchers.AkkaDispatcher;
import models.Evento;
import play.libs.Json;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

import static play.libs.Json.toJson;

/**
 * Created by Lady Pinzon on 11/02/2017.
 */
public class EventoController extends Controller{

    public CompletionStage<Result> getEventos()
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return Evento.FINDER.all(); } ,jdbcDispatcher)
                .thenApply(eventoEntities -> {return ok(toJson(eventoEntities));}
                );
    }

    public CompletionStage<Result> getEvento(Long idM)
    {
        MessageDispatcher jdbcDispatcher = AkkaDispatcher.jdbcDispatcher;

        return CompletableFuture.
                supplyAsync(() -> { return Evento.FINDER.byId(idM); } ,jdbcDispatcher)
                .thenApply(eventos -> {return ok(toJson(eventos));}
                );
    }

    public CompletionStage<Result> createEvento()
    {

        JsonNode n = request().body().asJson();

        Evento evento = Json.fromJson( n , Evento.class ) ;
        return CompletableFuture.supplyAsync(
                ()->{
                    evento.save();
                    return evento;
                }
        ).thenApply(
                eventos -> {
                    return ok(Json.toJson(eventos));
                }
        );
    }

    public CompletionStage<Result> deleteEvento(Long idE)
    {


        return CompletableFuture.supplyAsync(
                ()->{
                    Evento evento = Evento.FINDER.byId(idE);
                    evento.delete();
                    return evento;
                }
        ).thenApply(
                eventos -> {
                    return ok(Json.toJson(eventos));
                }
        );
    }
    public CompletionStage<Result> updateEvento( Long idE)
    {

        JsonNode n = request().body().asJson();
        Evento m = Json.fromJson( n , Evento.class ) ;
        Evento antiguo = Evento.FINDER.byId(idE);

        return CompletableFuture.supplyAsync(
                ()->{
                    antiguo.setId(m.getId());
                    antiguo.setFecha(m.getFecha());
                    antiguo.setNombre(m.getNombre());

                    antiguo.update();
                    return antiguo;
                }
        ).thenApply(
                eventos -> {
                    return ok(Json.toJson(eventos));
                }
        );
    }
}
