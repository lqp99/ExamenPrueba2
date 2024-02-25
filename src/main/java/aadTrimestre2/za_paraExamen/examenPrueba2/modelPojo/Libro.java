package aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity  //define que es una entidad dentro de una database.
@Table(
        name = "libros"/*,  //para poner el nombre de la Tabla. Para que no se ponga como el nombre de la clase, se lo especificamos nosotros.
        uniqueConstraints = {@UniqueConstraint(columnNames = {"", ""})}*/  //para que no se puedan introducir la pareja de valores con estos dos mismos atributos iguales. Hay que poner el nombre de la columna en la tabla.
)
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(
            name = "titulo",  //para que la columna de la tabla se llame como le digas.
            unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private String titulo;

    @Column(
            name = "numero_paginas",  //para que la columna de la tabla se llame como le digas.
            unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private int numeroPaginas;

    @ManyToMany (  //muchos a muchos.
            /*cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
            //fetch = FetchType.LAZY
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todas las asignaturas que tiene el profesor. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
    )
    @JoinTable(  //es la tabla que se genera cuando hay una relacion de muchos a muchos (N:M).
            name = "autores",  //se pone el nombre de la tabla que se crea al hacer la referencia entre las dos clases.
            joinColumns = @JoinColumn(name = "libro_id"),  //es la Foreign Key con el nombre de la clase donde se hace el @JoinTable.
            inverseJoinColumns = @JoinColumn(name = "persona_id"),  //la Foreign Key de la otra clase.
            uniqueConstraints = {@UniqueConstraint(columnNames = {"persona_id", "libro_id"})}  //para que no se puedan introducir dos valores con estos dos mismos atributos iguales. Hay que poner el nombre de la columna en la tabla.
    )
    private Set<User> autores = new HashSet<>();

    @ManyToMany (  //muchos a muchos.
            /*cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree o actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }*/
            //fetch = FetchType.LAZY
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todas las asignaturas que tiene el profesor. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
    )
    @JoinTable(  //es la tabla que se genera cuando hay una relacion de muchos a muchos (N:M).
            name = "lectura",  //se pone el nombre de la tabla que se crea al hacer la referencia entre las dos clases.
            joinColumns = @JoinColumn(name = "libro_id"),  //es la Foreign Key con el nombre de la clase donde se hace el @JoinTable.
            inverseJoinColumns = @JoinColumn(name = "persona_id"),  //la Foreign Key de la otra clase.
            uniqueConstraints = {@UniqueConstraint(columnNames = {"persona_id", "libro_id"})}  //para que no se puedan introducir dos valores con estos dos mismos atributos iguales. Hay que poner el nombre de la columna en la tabla.
    )
    private Set<User> personasQueHanLeidoEsteLibro = new HashSet<>();

    @OneToMany(  //uno a muchos.
            mappedBy = "libroValorado",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private Set<Valoracion> valoraciones = new HashSet<>();


    //constructor
    public Libro() {
    }

    public Libro(String titulo, int numeroPaginas) {
        this.titulo = titulo;
        this.numeroPaginas = numeroPaginas;
    }

    //toString
    @Override
    public String toString() {
        return "Libro{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", numeroPaginas='" + numeroPaginas + '\'' +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getNumeroPaginas() {
        return numeroPaginas;
    }

    public void setNumeroPaginas(int numeroPaginas) {
        this.numeroPaginas = numeroPaginas;
    }

    public Set<User> getAutores() {
        return autores;
    }

    public void setAutores(Set<User> autores) {
        this.autores = autores;
    }

    public Set<User> getPersonasQueHanLeidoEsteLibro() {
        return personasQueHanLeidoEsteLibro;
    }

    public void setPersonasQueHanLeidoEsteLibro(Set<User> personasQueHanLeidoEsteLibro) {
        this.personasQueHanLeidoEsteLibro = personasQueHanLeidoEsteLibro;
    }

    public Set<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Set<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }
}
