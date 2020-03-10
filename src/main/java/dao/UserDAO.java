package dao;

import model.User;

import java.util.List;

public interface UserDAO {
    public void addUser(User user);

    public Boolean deleteUser(Long id);

    public Boolean updateUser(User user);

    public User getUserById(Long id);

    public List<User> getAllUsers();

    public boolean validateUser(String mail, String password);

    public User getUserByMail(String mail);
}
