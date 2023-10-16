package giacomo.bongiovanni.poptickets.service.impl;
import giacomo.bongiovanni.poptickets.dto.UserDTO;
import giacomo.bongiovanni.poptickets.dto.mapper.UserMapper;
import giacomo.bongiovanni.poptickets.exception.*;
import giacomo.bongiovanni.poptickets.model.Role;
import giacomo.bongiovanni.poptickets.model.User;
import giacomo.bongiovanni.poptickets.repository.UserRepository;
import giacomo.bongiovanni.poptickets.service.UserService;
import org.mapstruct.control.MappingControl;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no registered user");
        }
        return UserMapper.INSTANCE.usersToUserDTOs(users);
    }

    @Override
    public List<UserDTO> findAllCustomer() {
        List<User> customers = userRepository.findAllByRole(Role.CUSTOMER);
        if (customers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no registered customer");
        }
        Collections.sort(customers, Comparator.comparing(u -> (u.getSurname() + u.getName())));
        return UserMapper.INSTANCE.usersToUserDTOs(customers);
    }

    @Override
    public List<UserDTO> findAllSeller() {
        List<User> sellers = userRepository.findAllByRole(Role.SELLER);
        if (sellers.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no registered seller");
        }
        Collections.sort(sellers, Comparator.comparing(s -> (s.getSurname() + s.getName())));
        return UserMapper.INSTANCE.usersToUserDTOs(sellers);
    }

    @Override
    public List<UserDTO> findAllAdmin() {
        List<User> admins = userRepository.findAllByRole(Role.ADMIN);
        if (admins.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"no registered customer");
        }
        Collections.sort(admins, Comparator.comparing(a -> (a.getSurname() + a.getName())));
        return UserMapper.INSTANCE.usersToUserDTOs(admins);
    }

    @Override
    public UserDTO findById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Override
    public UserDTO findByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(email));
        return UserMapper.INSTANCE.userToUserDTO(user);
    }



    @Override
    public UserDTO block(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        try{
            user.setBlocked(true);
            userRepository.save(user);
        }catch (Exception e){
            throw new GeneralErrorException(id);
        }
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Override
    public UserDTO unlock(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));
        try{
            user.setBlocked(false);
            userRepository.save(user);
        }catch (Exception e){
            throw new GeneralErrorException(id);
        }
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    @Override
    public boolean deleteById(long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(id));

        return delete(user);
    }

    @Override
    public boolean deleteByEmail(String email) {
        User user = userRepository.findByEmail(email).orElseThrow(() -> new EntityNotFoundException(email));

        return delete(user);
    }
    private boolean delete(User user) {
        user.setDeletedAt(LocalDateTime.now());

        try {
            userRepository.save(user);
        } catch (Exception e) {
            throw new EntityDeletionException(user.getId());
        }

        return true;
    }
}
