package aadTrimestre2.za_paraExamen.examenPrueba2.impl;

import aadTrimestre2.za_paraExamen.examenPrueba2.interfacesDAO.LibroDAO;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;
import aadTrimestre2.za_paraExamen.examenPrueba2.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class LibroDAOimpl implements LibroDAO {

    @Override
    public void createBook(Libro libro) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.persist(libro);  //esto es como hacer un insert para insertar el libro a la tabla.
            System.out.println("Libro con titulo \"" + libro.getTitulo() + "\" insertado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR al añadir el Libro \"" + libro.getTitulo() + "\".");
        }
    }

    @Override
    public void updateBook(Libro libro) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.merge(libro);  //esto es como hacer un update para actualizar el producto a la tabla.
            System.out.println("Libro con titulo \"" + libro.getTitulo() + "\" actualizado.");

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR actualizando el Libro \"" + libro.getTitulo() + "\".");
        }
    }

    @Override
    public void removeBook(Libro libro) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();  //inicializamos la transacción.

            session.remove(libro);  //si es distinto de null (existe el estudiante) que elimine el estudiante.
            System.out.println("Libro con titulo \"" + libro.getTitulo() + "\" eliminado.");

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR eliminando el Libro \"" + libro.getTitulo() + "\".");
        }
    }

    public Libro getLibroById(long libroId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Libro> query = builder.createQuery(Libro.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Libro> booksTable = query.from(Libro.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, titulo, numero_paginas, autores, personasQueHanLeidoEsteLibro, valoraciones)
            from libros
            where id = libroId;
             */
            query.select(booksTable).where(builder.equal(booksTable.get("id"), libroId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    public List<Libro> getAllBooks() {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Libro> query = builder.createQuery(Libro.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Libro> booksTable = query.from(Libro.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, titulo, numero_paginas, autores, personasQueHanLeidoEsteLibro, valoraciones)
            from libros;
             */
            query.select(booksTable);

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }
}
