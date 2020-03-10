package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    void addUser(User user);

    Boolean deleteUser(Long id);

    Boolean updateUser(User user);

    User getUserById(Long id);

    List<User> getAllUsers();

    boolean validateUser(String mail, String password);

    User getUserByMail(String mail);
}
