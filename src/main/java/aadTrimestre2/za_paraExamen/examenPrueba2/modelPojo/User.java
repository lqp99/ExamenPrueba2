package aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.*;

@Entity  //define que es una entidad dentro de una database.
@Table(
        name = "users"/*,  //para poner el name de la Tabla. Para que no se ponga como el name de la clase, se lo especificamos nosotros.
        uniqueConstraints = {@UniqueConstraint(columnNames = {"name", "mail"})}*/  //para que no se puedan introducir la pareja de valores con estos dos mismos atributos iguales. Hay que poner el name de la columna en la tabla.
)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)  //para que se auto incremente solo.
    @Column(name = "id")  //para que la columna de la tabla se llame como le digas.
    private long id;

    @Column(
            name = "name",  //para que la columna de la tabla se llame como le digas.
            //unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private String name;

    @Column(
            name = "mail",  //para que la columna de la tabla se llame como le digas.
            unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private String mail;

    @Column(
            name = "userName",  //para que la columna de la tabla se llame como le digas.
            unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private String userName;

    @Column(
            name = "password",  //para que la columna de la tabla se llame como le digas.
            //unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private String password;

    @Column(
            name = "last_time_login",  //para que la columna de la tabla se llame como le digas.
            //unique = true,  //individualmente no se puede repetir este atributo.
            nullable = false  //no puede ser null.
    )
    private Calendar lastTimeLogin;

    @ManyToMany(  //muchos a muchos.
            mappedBy = "autores",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            //fetch = FetchType.LAZY,
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todos los posts que tiene el user. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
            cascade = {  //al ser "CascadeType.MERGE" cada vez que se actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.MERGE
            }
    )
    private Set<Libro> librosEscritos = new HashSet<>();

    @ManyToMany(  //uno a muchos.
            mappedBy = "personasQueHanLeidoEsteLibro",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            //fetch = FetchType.LAZY,
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todos los posts que tiene el user. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
            cascade = {  //al ser "CascadeType.MERGE" cada vez que se actualice un "Objeto", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.MERGE
            }
    )
    private Set<Libro> librosLeidos = new HashSet<>();

    @OneToMany(  //uno a muchos.
            mappedBy = "autorValoracion",  //mapeamos el valor de la variable de la otra clase que hace la relación con esta clase.
            //fetch = FetchType.LAZY,
            //El FetchType.EAGER: cuando se hace un select te trae ttodo lo que tiene que ver con esta clase. Te va a traer todos los posts que tiene el user. Cuando te da un ERROR de que hay muchas llmadas se pone LAZY y se arregla.
            //El FetchType.LAZY: por defecto si no pones nada es este. Cuando se hace un select te trae ttodo lo que tiene que ver con esta clase menos las asignaturas y así controlas lo que te devuelve. Hay que crear un método para saber cuales asignatruas son las que están relacionadas con cada profesor.
            cascade = {  //al ser "CascadeType.PERSIST / MERGE" cada vez que se cree y actualice un "ObjetoDeEstaClase", se hace en cascada y se modifica la referencia primero esta clase y luego en la que está referenciada.
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            }
    )
    private Set<Valoracion> valoraciones = new HashSet<>();


    //constructor
    public User() {
    }

//    public User(String name, String mail, String password) {
//        this.name = name;
//        this.mail = mail;
//        this.password = password;
//        this.lastTimeLogin = Calendar.getInstance();  //para poner la ultima vez que el user se logeó a la hora de ahora mismo.
//    }

    public User(String name, String mail, String userName, String password) {
        this.name = name;
        this.mail = mail;
        this.userName = userName;
        this.password = password;
        this.lastTimeLogin = Calendar.getInstance();  //para poner la ultima vez que el user se logeó a la hora de ahora mismo.
    }


    //toString
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", password='" + password + '\'' +
                '}';
    }


    //getters and setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Calendar getLastTimeLogin() {
        return lastTimeLogin;
    }

    public void setLastTimeLogin(Calendar lastTimeLogin) {
        this.lastTimeLogin = lastTimeLogin;
    }

    public Set<Libro> getLibrosEscritos() {
        return librosEscritos;
    }

    public void setLibrosEscritos(Set<Libro> librosEscritos) {
        this.librosEscritos = librosEscritos;
    }

    public Set<Libro> getLibrosLeidos() {
        return librosLeidos;
    }

    public void setLibrosLeidos(Set<Libro> librosLeidos) {
        this.librosLeidos = librosLeidos;
    }

    public Set<Valoracion> getValoraciones() {
        return valoraciones;
    }

    public void setValoraciones(Set<Valoracion> valoraciones) {
        this.valoraciones = valoraciones;
    }


}