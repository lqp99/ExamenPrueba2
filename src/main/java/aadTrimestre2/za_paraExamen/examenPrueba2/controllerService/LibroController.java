package aadTrimestre2.za_paraExamen.examenPrueba2.controllerService;

import aadTrimestre2.za_paraExamen.examenPrueba2.implementations.LibroDAOimpl;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;

import java.util.List;
import java.util.Set;

public class LibroController {
    private final LibroDAOimpl libroDAOimpl = new LibroDAOimpl();


    //metodos
    public List<Libro> aviableBooks() {
        return this.libroDAOimpl.getAllBooks();
    }


}
