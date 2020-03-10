package dao;

import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {
    Connection connection;

    public UserJdbcDAO(Connection connectionDao) {
        this.connection = connectionDao;
    }

    @Override
    public void addUser(User user) {
        try (PreparedStatement pr = connection.prepareStatement("insert into users_to_admin(mail,password,role) VALUES " +
                "(?,?,?)")) {
            connection.setAutoCommit(false);
            pr.setString(1, user.getMail());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            pr.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.setAutoCommit(true);
            } catch (SQLException ignored) {

            }
        }
    }

    @Override
    public Boolean deleteUser(Long id) {
        boolean deleted = false;
        try (PreparedStatement pr = connection.prepareStatement("delete from users_to_admin where id = ?")) {
            connection.setAutoCommit(false);
            pr.setLong(1, id);
            deleted = pr.executeUpdate() > 0;
            connection.commit();
            connection.setAutoCommit(false);
            return deleted;
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                connection.rollback();
                connection.setAutoCommit(false);
            } catch (SQLException ignored) {

            }
        }
        return deleted;
    }

    @Override
    public Boolean updateUser(User user) {
        boolean updated = false;
        try (PreparedStatement pr = connection.prepareStatement("UPDATE users_to_admin set mail=?,password=?,role=? where id=?")) {
            connection.setAutoCommit(false);
            pr.setString(1, user.getMail());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            pr.setLong(4, user.getId());
            if (pr.executeUpdate() > 0) {
                updated = true;
                connection.commit();
            } else {
                connection.rollback();
            }
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return updated;
    }

    @Override
    public User getUserById(Long id) {
        User user = null;
        try (PreparedStatement pr = connection.prepareStatement("select  id, mail, password, role from users_to_admin where id=?")) {
            pr.setLong(1, id);
            ResultSet res = pr.executeQuery();
            connection.setAutoCommit(false);
            while (res.next()) {
                String mail = res.getString("mail");
                String password = res.getString("password");
                String role = res.getString("role");

                user = new User(id, mail, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (PreparedStatement pr = connection.prepareStatement("select * from users_to_admin")) {
            ResultSet res = pr.executeQuery();
            while (res.next()) {
                Long id = res.getLong(1);
                String mail = res.getString(2);
                String password = res.getString(3);
                String role = res.getString(4);

                list.add(new User(id, mail, password, role));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public boolean validateUser(String mail, String password) {
        boolean validated = false;
        try (PreparedStatement pr = connection.prepareStatement("select *" +
                " from users_to_admin where mail=? and password=?")) {
            pr.setString(1, mail);
            pr.setString(2, password);

            ResultSet res = pr.executeQuery();// pr.getResultSet();
            validated = res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return validated;
    }

    public User getUserByMail(String mail) {
        User user = null;
        try (PreparedStatement pr = connection.prepareStatement("select id,password,role from users_to_admin where mail=?")) {
            pr.setString(1, mail);
            ResultSet resultSet = pr.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong(1);
                String password = resultSet.getString(2);
                String role = resultSet.getString(3);
                user = new User(id, mail, password, role);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
