package models;

import com.avaje.ebean.Model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un grupo musical
 * Created by Lady Pinzon on 11/02/2017.
 */
@Entity
//Este es el nombre de la tabla en la base de datos
@Table(name="Evento")
public class Evento extends Model {

    //--------------------------------------------------------------
    //                          CONSTANTES
    //--------------------------------------------------------------

    //Permite acceso a la base de datos para hacer busquedas
    public static Finder<Long, Evento> FINDER = new Finder<>(Evento.class);

    /**
     * Genero del evento
     */
    public final static String ROCK = "Rock";
    public final static String JAZZ = "Jazz";
    public final static String SALSA = "Salsa";
    public final static String PUNK = "Punk";
    public final static String METAL = "Metal";

    //--------------------------------------------------------------
    //                          ATRIBUTOS
    //--------------------------------------------------------------

    /**
     * identificador del musico
     */
    @Id
    //los id seran generados de forma secuencial y el nombre del generador es Evento
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EventoSequence")
    private Long id;

    /**
     * lista de eventos del musico
     */
//    @ManyToMany(mappedBy = "eventos")
//    private List<Musico> musicos;

    /**
     * Nombre del musico
     */
    private String nombre;

    /**
     * Biografia del musico
     */
    private Date fecha;

    //--------------------------------------------------------------
    //                          Constructores
    //--------------------------------------------------------------


    /**
     * Constructor vacio de la clase.
     */
    public Evento() {

        id = null;
        nombre = "NO NAME";
        fecha = null;
//        musicos = new ArrayList<>();

    }

    /**
     * Constructor con todos los atributos de la clase.
     *
     * @param id
     * @param nombre
     * @param biografia
     * @param correo
     * @param musicos
     */
    public Evento(Long id, String nombre, String biografia, String correo, List<Musico> musicos) {

        this.id = id;
        this.nombre = nombre;
//        this.musicos = musicos;
    }

    public Evento(Long id) {
        this();
        setId(id);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
}