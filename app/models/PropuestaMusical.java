package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un grupo musical
 * Created by Lady Pinzon on 11/02/2017.
 */
@Entity
//Este es el nombre de la tabla en la base de datos
@Table(name="PropuestaMusical")
public class PropuestaMusical extends Model {

    //--------------------------------------------------------------
    //                          CONSTANTES
    //--------------------------------------------------------------

    //Permite acceso a la base de datos para hacer busquedas
    public static Finder<Long, PropuestaMusical> FINDER = new Finder<>(PropuestaMusical.class);

    //--------------------------------------------------------------
    //                          ATRIBUTOS
    //--------------------------------------------------------------

    /**
     * identificador del musico
     */
    @Id
    //los id seran generados de forma secuencial y el nombre del generador es
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PropuestaMusicalSequence")
    private Long id;

    /**
     * Canciones del artista o grupo musical
     */
    private List<String> canciones;

    /**
     * Videos del artista o grupo musical
     */
    private List<String> videos;



    //--------------------------------------------------------------
    //                          Constructores
    //--------------------------------------------------------------



    /**
     * Constructor vacio de la clase.
     */
    public PropuestaMusical() {

        id = null;
        canciones = new ArrayList<>();
        videos = new ArrayList<>();
    }

    /**
     * Constructor con todos los atributos de la clase.
     *
     * @param id
     * @param canciones
     * @param videos
     */
    public PropuestaMusical(Long id, List<String> canciones, List<String> videos) {

        this.id = id;
        this.canciones = canciones;
        this.videos = videos;
    }

    public PropuestaMusical(Long id) {
        this();
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<String> getCanciones() {
        return canciones;
    }

    public void setCanciones(List<String> canciones) {
        this.canciones = canciones;
    }

    public List<String> getVideos() {
        return videos;
    }

    public void setVideos(List<String> videos) {
        this.videos = videos;
    }
}