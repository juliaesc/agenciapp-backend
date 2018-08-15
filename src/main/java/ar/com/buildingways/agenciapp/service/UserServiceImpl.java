package ar.com.buildingways.agenciapp.service;

import ar.com.buildingways.agenciapp.exception.AppException;
import ar.com.buildingways.agenciapp.model.Account;
import ar.com.buildingways.agenciapp.model.Role;
import ar.com.buildingways.agenciapp.model.Store;
import ar.com.buildingways.agenciapp.model.User;
import ar.com.buildingways.agenciapp.repository.RoleRepository;
import ar.com.buildingways.agenciapp.repository.UserRepository;
import ar.com.buildingways.agenciapp.util.Constants;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.net.URLEncoder;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    /**
     * Recupera diariamente los datos de los usuarios habilitados para usar la aplicación.
     * Actualiza las tablas Users, Stores y Accounts.
     */
    @Override
    public void updateUsers() {
        Collection<Object[]> usersFromDB = userRepository.loadUsersFromDB();
        Iterator<Object[]> it = usersFromDB.iterator();
        while (it.hasNext()) {
            Object[] item = (Object[]) it.next();
            Optional<User> user = userRepository.findByUsername(((BigDecimal) item[0]).longValue());
            if (user.isPresent()) {
                user.get().setLastModifiedBy("escobjul");
                user.get().setLastModifiedDate(new DateTime());
            } else {
                user = Optional.of(new User(((BigDecimal) item[0]).longValue(), // LEGAJO (NOMBRE DE USUARIO)
                                            (String) item[1], (String) item[2], (String) item[4], // NOMBRE, APELLIDO, MAIL
                                            "escobjul", new DateTime()));
                user.get().setPassword(passwordEncoder.encode(user.get().getUsername()+"bla"));
            }
            Role userRole = roleRepository.findByName(Constants.ROL_USUARIO).orElseThrow(() -> new AppException("User Role not set."));
            user.get().setRoles(Collections.singleton(userRole));
            user.get().setEnabled(true);
            Store store = createStore(item, user.get());
            Account account = createAccount(item, user.get());
            user.get().setAccount(account);
            user.get().setStore(store);
            userRepository.save(user.get());
        }
    }

    private Store createStore(Object[] item, User user) {
        Store store = new Store(
            // DOMICILIO, USUARIO, NOMBRE DE LA AGENCIA
            (String) item[5] , user, (String) item[3],
            // CUIT, COMISIONISTA, CANTIDAD DE TERMINALES
            ((BigDecimal) item[8]).longValue(), ((BigDecimal) item[6]).intValue(), ((BigDecimal) item[7]).intValue(),
            user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
            user.isEnabled(), user.isDeleted());
        return store;
    }

    private Account createAccount(Object[] item, User user) {
        Account account = new Account(
                // NÚMERO DE CUENTA, USUARIO, SUCURSAL, TITULAR DE CUENTA
                ((BigDecimal) item[11]).intValue(), user, ((BigDecimal) item[12]).intValue(), (String) item[9],
                // ADHERIDO AL DA,  TIPO DE CUENTA, PORC. IIBB, CBU
                (char) item[14], (String) item[10], ((BigDecimal) item[15]).doubleValue(), (String) item[13],
                user.getCreatedBy(), user.getCreatedDate(), user.getLastModifiedBy(), user.getLastModifiedDate(),
                user.isEnabled(), user.isDeleted());
        return account;
    }

}