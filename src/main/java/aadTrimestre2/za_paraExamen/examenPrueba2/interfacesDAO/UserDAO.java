package aadTrimestre2.za_paraExamen.examenPrueba2.interfacesDAO;

import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.Libro;
import aadTrimestre2.za_paraExamen.examenPrueba2.modelPojo.User;

import java.util.List;

public interface UserDAO {
    public void createUser(User user);
    public void updateUser(User user);
    public void removeUser(User user);
    public User getUserById(long userId);
    public User getUserByNameOrMail(String userName);
    public List<Libro> getUserWrittenBooks(User user);
}
