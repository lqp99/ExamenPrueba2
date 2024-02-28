package aadTrimestre2.za_paraExamen.examenPrueba2.impl;

import aadTrimestre2.za_paraExamen.examenPrueba2.interfacesDAO.UserDAO;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.User;
import aadTrimestre2.za_paraExamen.examenPrueba2.utils.HibernateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDAOimpl implements UserDAO {
    @Override
    public void createUser(User user) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.persist(user);  //esto es como hacer un insert para insertar el libro a la tabla.

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR al añadir el User \"" + user.getName() + "\".");
        }
    }

    @Override
    public void updateUser(User user) {
        Transaction tx = null;

        try (Session session = HibernateUtil.getSessionFactory().openSession();) {  //para hacer la conexión con la database.
            tx = session.beginTransaction();

            session.merge(user);  //esto es como hacer un update para actualizar el producto a la tabla.

            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            System.err.println("ERROR actualizando el user \"" + user.getName() + "\".");
        }
    }

    @Override
    public void removeUser(User user) {
        Transaction tx = null;  //inicializamos la transacción a null. La transacción solo se hace si se ejecuta ttodo el código, si falla algo no hace nada.

        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            tx = session.beginTransaction();  //inicializamos la transacción.

            session.remove(user);  //si es distinto de null (existe el estudiante) que elimine el estudiante.

            tx.commit();  //para completar la transacción.
        } catch (Exception ex) {
            if (tx != null) {  //si la transacción es distinta de null que significa que está abierta y que no se ha completado....
                tx.rollback();  //esto va a deshacer lo que ha hecho antes y va a volver a como estaba.
            }
            System.err.println("ERROR eliminando el user \"" + user.getName() + "\".");
        }
    }

    public User getUserById(long userId) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users
            where id = userId;
             */
            query.select(usersTable).where(builder.equal(usersTable.get("id"), userId));

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    @Override
    public User getUserByNameOrMail(String userNameOrMail) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<User> query = builder.createQuery(User.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<User> usersTable = query.from(User.class);  //se utilza para ver de que clase sacamos la información.

            /*
            select * (id, name, mail, description, telephone)
            from users
            where name = "user_name" or mail = "mail";
             */
            query.select(usersTable).where(
                    builder.or(
                            builder.equal(usersTable.get("userName"), userNameOrMail),
                            builder.equal(usersTable.get("mail"), userNameOrMail)
                    )
            );

            return session.createQuery(query).getSingleResult();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

    public List<Libro> getUserWrittenBooks(User user) {
        try (Session session = HibernateUtil.getSessionFactory().openSession();){  //para hacer la conexión con la database.
            CriteriaBuilder builder = session.getCriteriaBuilder();  //el CriteriaBuilder lo que nos permite es realizar modificaciones sobre el select.
            CriteriaQuery<Libro> query = builder.createQuery(Libro.class);  //se tiene que poner la clase general, si quieres que devuelva un int, se pone Integer o Long.

            Root<Libro> booksTable = query.from(Libro.class);  //se utilza para ver de que clase sacamos la información.
            Join<Libro, User> usersTable = booksTable.join("users");  //esta tabla que devuelve es de la tabla que hacemos el join.

            /*
            select id, titulo, numero_paginas
            from libros
            inner join users
            on id = user_id;
             */
            query.multiselect(booksTable.get("id"), booksTable.get("titulo"), booksTable.get("numero_paginas")).where(
                    builder.equal(usersTable, user)
            );  //donde tengan el mismo id coge todos los "Libros" y los devuelve en una lista.

            return session.createQuery(query).getResultList();
        } catch (Exception ex) {
            System.err.println(ex);
            return null;  //si salta una exception devuelve null que es como no devolver nada.
            //también pudes retornar un ArrayList vacío pero luego tienes que controlarlo cuando lo muestres.
        }
    }

}
