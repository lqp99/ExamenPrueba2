package aadTrimestre2.za_paraExamen.examenPrueba2.controllerService;

import aadTrimestre2.za_paraExamen.examenPrueba2.implementations.UserDAOimpl;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.User;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Valoracion;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class UserController {
    private final UserDAOimpl userDAOimpl = new UserDAOimpl();
    private final LibroController libroController = new LibroController();


    //metodos
    public User createUser(String name, String mail, String userName, String password){
        User user = new User(name, mail, userName, password);  //creamos el user pasandole todos los datos por el constructor.

        this.userDAOimpl.createUser(user);  //creamos el usuario.

        return user;  //devolvemos el user.
    }

    public void createUser(User user){
        this.userDAOimpl.createUser(user);
        System.out.println("User con nombre \"" + user.getName() + "\" insertado.");
    }

    public void updateUser(User user) {
        this.userDAOimpl.updateUser(user);
        System.out.println("User con nombre \"" + user.getName() + "\" actualizado.");
    }

    public void removeUser(User user){
        this.userDAOimpl.removeUser(user);
        System.out.println("User con nombre \"" + user.getName() + "\" eliminado.");
    }

    public User login(String userNameOrMail, String password) {
        User user = this.userDAOimpl.getUserByNameOrMail(userNameOrMail);

        if (user != null && user.getPassword().equals(password)){
            user.setLastTimeLogin(LocalDate.now());  //para actualizar la ultima vez que se hizo login a ahora.
            return user;
            //return true;
        } else {
            return null;
            //return false;
        }
    }

    public void marcarComoLeido(User user, Libro libro) {
        user.getLibrosLeidos().add(libro);  //en user, cogemos la lista que tiene de libros leidos y añadimos el libro que nos pasan.

        libro.getPersonasQueHanLeidoEsteLibro().add(user);  //en libro, cogemos la lista que tiene de personas que han leido este libro y añadimos el usuario que nos pasan.

        this.userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.

        System.out.println("\"" + user.getName() + "\" ha leido el libro \"" + libro.getTitulo() + "\".");
    }

    public void escribirLibro(User user, String titulo, int numeroPaginas) {
        Libro libro = new Libro(titulo, numeroPaginas);

        user.getLibrosEscritos().add(libro);  //en user, cogemos la lista que tiene de libros escritos y añadimos el libro que se crea.

        libro.getAutores().add(user);  //en libro, cogemos la lista de autores y añadimos el usuario que nos pasan.

        this.userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.

        System.out.println("\"" + user.getName() + "\" ha escrito el libro \"" + libro.getTitulo() + "\" con \"" + libro.getNumeroPaginas() + "\" páginas.");
    }

    public Set<Libro> getAllReadedBooks(User user) {
        return user.getLibrosLeidos();  //devuelve la lista de todos los libros que ha leido este usuario.
    }

    public void comentarLibro(User user, Libro libro, int nota, String comentario) {
        Valoracion valoracion = null;

        if (nota >= 0 && nota <= 5) {
            valoracion  = new Valoracion(user, libro, nota, comentario);

            user.getValoraciones().add(valoracion);  //en user, cogemos la lista que tiene de valoraciones y añadimos la valoración que se crea.

            libro.getValoraciones().add(valoracion);  //en libro, cogemos la lista que tiene de valoraciones y añadimos la valoración que se crea.

            this.userDAOimpl.updateUser(user);  //actualizamos el usuario para que se guarden los cambios.

            System.out.println("\"" + user.getName() + "\" ha valorado el libro \"" + libro.getTitulo() + "\" con una nota de \"" + nota + "\".");
        } else {
            System.err.println("No se ha podido crear la valoración porque la nota \"" + nota + "\" no está permitida. Tiene que ser entre 0 y 5.");
        }
    }
}
