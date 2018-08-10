package ar.com.buildingways.agenciapp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service("userService")
public class UserServiceImpl implements UserService {

    /*@Autowired
    @Qualifier("userDaoImpl")
    private UserDao userDaoImpl;

    /**
     * Recupera diariamente los datos de los usuarios habilitados para usar la aplicación.
     * Actualiza las tablas Users, UserDetails y Accounts.
     */
    @Override
    public void updateUsers() {
       // Collection<Object[]> users = userDaoImpl.loadUsersFromDB();
        //userDaoImpl.insertUsers(users);
    }

}