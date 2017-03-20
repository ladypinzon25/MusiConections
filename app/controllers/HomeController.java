package controllers;

import models.Evento;
import views.html.*;
import models.Musico;
import play.mvc.Controller;
import play.mvc.Result;

import java.util.List;


/**
 * This controller contains an action to handle HTTP requests
 * to the application's home page.
 */
public class HomeController extends Controller {


    /**
     * An action that renders an HTML page with a welcome message.
     * The configuration in the <code>routes</code> file means that
     * this method will be called when the application receives a
     * <code>GET</code> request with a path of <code>/</code>.
     */


    public Result index()
    {
        Musico musico = Musico.FINDER.byId(1000L);
        Musico musico2 = Musico.FINDER.byId(1001L);
        List<Musico> musicians = Musico.FINDER.all();
        return ok(index.render(musico,musico2, musicians));



    }


}
