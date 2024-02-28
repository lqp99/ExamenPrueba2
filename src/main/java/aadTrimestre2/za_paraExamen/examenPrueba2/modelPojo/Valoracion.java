package aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo;

import jakarta.persistence.*;

@Entity  //define que es una entidad dentro de una database.
@Table(
        name = "valoraciones"/*,  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
        uniqueConstraints = {@UniqueConstraint(columnNames = {"", ""})}*/  //para que no se puedan introducir la pareja de valores con estos dos mismos atributos iguales. Hay que poner el nombre de la columna en la tabla.
)
public class Valoracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(
            name = "valoracion"  //para que la columna de la tabla se llame como le digas.
            //unique = true,  //individualmente no se puede repetir este atributo.
            //nullable = false  //no puede ser null.
    )
    private int nota;

    @Column(
            name = "comentario"  //para que la columna de la tabla se llame como le digas.
            //unique = true,  //individualmente no se puede repetir este atributo.
            //nullable = false  //no puede ser null.
    )
    private String comentario;

    @ManyToOne(  //muchos a uno.
            //fetch = FetchType.LAZY,
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todos los posts que tiene el user. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
            /*cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
    )
    private User autorValoracion;

    @ManyToOne(  //muchos a uno.
            //fetch = FetchType.LAZY,
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todos los posts que tiene el user. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
            /*cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
    )
    private Libro libroValorado;


    //constructor
    public Valoracion() {
    }

    public Valoracion(User autorValoracion, Libro libroValorado, int nota, String comentario) {
        this.nota = nota;
        this.comentario = comentario;
        this.autorValoracion = autorValoracion;
        this.libroValorado = libroValorado;
    }


    //toString
    @Override
    public String toString() {
        return "Valoracion{" +
                "id=" + id +
                ", nota=" + nota +
                ", comentario='" + comentario + '\'' +
                ", autorValoracion=" + autorValoracion +
                ", libroValorado=" + libroValorado +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public User getAutorValoracion() {
        return autorValoracion;
    }

    public void setAutorValoracion(User autorValoracion) {
        this.autorValoracion = autorValoracion;
    }

    public Libro getLibroValorado() {
        return libroValorado;
    }

    public void setLibroValorado(Libro libroValorado) {
        this.libroValorado = libroValorado;
    }
}
