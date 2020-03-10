package dao;


import util.DBHelper;
import util.PropertiesReader;

public class UserDaoFactory {
    public UserDAO getFactory() {
        if (PropertiesReader.getProperties("hibernate.properties").getProperty("DAO").equals("Hibernate")) {
            return new UserHibernateDAO(DBHelper.getSessionFactory());
        } else {
            return new UserJdbcDAO(DBHelper.getConnection());
        }
    }
}
