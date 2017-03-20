package models;

import com.avaje.ebean.Model;
import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa un grupo musical
 * Created by Lady Pinzon on 11/02/2017.
 */
@Entity
//Este es el nombre de la tabla en la base de datos
@Table(name="Musico")
public class Musico extends Model {

    //--------------------------------------------------------------
    //                          CONSTANTES
    //--------------------------------------------------------------

    //Permite acceso a la base de datos para hacer busquedas
    public static Finder<Long, Musico> FINDER = new Finder<>(Musico.class);

    /**
     * Genero del musico
     */
    public final static String ROCK = "Rock";
    public final static String JAZZ = "Jazz";
    public final static String SALSA = "Salsa";
    public final static String PUNK = "Punk";
    public final static String METAL = "Metal";
    /**
     * Tipo de musico
     */

    public final static String ARTISTA = "Artista";
    public final static String AGRUPACION = "Agrupacion";
    //--------------------------------------------------------------
    //                          ATRIBUTOS
    //--------------------------------------------------------------

    /**
     * identificador del musico
     */
    @Id
    //los id seran generados de forma secuencial y el nombre del generador es Musico
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MusicoSequence")
    private Long id;

    /**
     * lista de eventos del musico
     */
//    @JsonBackReference
//    @ManyToMany(mappedBy = "musicos")
//    private List<Evento> eventos;
//
//    @OneToOne
//    private PropuestaMusical propuestaMusical;

    /**
     * Nombre del musico
     */
    private String nombre;

    /**
     * Biografia del musico
     */
    private String biografia;
    /**
     * Correo del musico
     */
    private String correo;

    private String tipo;

    private String urlImagen;
    //--------------------------------------------------------------
    //                          Constructores
    //--------------------------------------------------------------



    /**
     * Constructor vacio de la clase.
     */
    public Musico() {

        id = null;
        nombre = "NO NAME";
        biografia = "NO NAME";
        correo = "NO NAME";
        tipo = "NO NAME";
        urlImagen = "NO NAME";
//        eventos = new ArrayList<>();
//        propuestaMusical = null;

    }

    /**
     * Constructor con todos los atributos de la clase.
     *
     * @param id
     * @param nombre
     * @param biografia
     * @param correo
     */
    public Musico(Long id, String nombre, String biografia, String correo, String tipo, String url) {
        System.out.println("Musico");
        this.id = id;
        this.nombre = nombre;
        this.biografia = biografia;
        this.correo = correo;
//        this.eventos = new ArrayList<Evento>();
//        this.propuestaMusical = null;
        this.tipo = tipo;
        this.urlImagen = url;
    }

    public Musico(Long id) {
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

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }
}