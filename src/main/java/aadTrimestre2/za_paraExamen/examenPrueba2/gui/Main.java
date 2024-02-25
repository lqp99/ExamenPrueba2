package aadTrimestre2.za_paraExamen.examenPrueba2.gui;

import aadTrimestre2.za_paraExamen.examenPrueba2.controllerService.LibroController;
import aadTrimestre2.za_paraExamen.examenPrueba2.controllerService.UserController;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    private static final UserController userController = new UserController();
    private static final LibroController libroController = new LibroController();

    public static void main(String[] args) {
        User u1 = new User("Pepe", "pepito13@example.com", "pepito84", "hola1234");
        User u2 = new User("Hergé", "hergéTintin@example.com", "elBelga33", "soyTintin");
        userController.createUser(u1);

        Libro libro1 = new Libro("Viven!", 363);
        Libro libro2 = new Libro("Bruce Dickinson An Autobiography", 371);
        Libro libro3 = new Libro("Las Lágrimas De Shiva", 237);



        //para hacer login:
        if (userController.login(u1.getUserName(), u1.getPassword()) != null) {
            System.out.println("Usuario logeado correctamente " + u1);
        } else {
            System.err.println("Login Incorrecto");
        }


        //marcar como leido un libro:
        userController.marcarComoLeido(u1, libro1);
        userController.marcarComoLeido(u1, libro2);
        userController.marcarComoLeido(u1, libro3);


        //escribir un libro:
        userController.escribirLibro(u2, "Las Aventuras de Tintin - Los Cigarros del Faraón", 62);


        //ver todos los libros que ha leido:
        Set<Libro> books = userController.getAllReadedBooks(u1);
        System.out.println("El usuario \"" + u1.getName() + "\" ha leido estos libros: ");
        for (Libro libro : books) {
            System.out.println("\t\"" + libro.getTitulo() + "\" con \"" + libro.getNumeroPaginas() + "\" páginas.");
        }


        //ver todos los libros disponibles:
        List<Libro> aviableBooks = libroController.aviableBooks();
        System.out.println("Libros disponibles: ");
        for (Libro libro : aviableBooks) {
            System.out.println("\t\"" + libro.getTitulo() + "\" con \"" + libro.getNumeroPaginas() + "\" páginas.");
        }


        //comentar/valorar un libro:
        userController.comentarLibro(u1, libro1, 4, "Un libro que cuenta una historia real, muy bonita y de superación. Muy descriptivo.");
        userController.comentarLibro(u2, libro1, 5, "Un libro increible y muy bonito.");

    }
}