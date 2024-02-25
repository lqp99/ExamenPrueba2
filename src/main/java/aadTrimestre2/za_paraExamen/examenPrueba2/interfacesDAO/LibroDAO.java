package aadTrimestre2.za_paraExamen.examenPrueba2.interfacesDAO;

import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;

import java.util.List;

public interface LibroDAO {
    public void createBook(Libro libro);
    public void updateBook(Libro libro);
    public void removeBook(Libro libro);
    public List<Libro> getAllBooks();
}
