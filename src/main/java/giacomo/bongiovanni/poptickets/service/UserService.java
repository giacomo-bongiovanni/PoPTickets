package giacomo.bongiovanni.poptickets.service;

import giacomo.bongiovanni.poptickets.dto.UserDTO;

import java.util.List;

public interface UserService {
    List<UserDTO> findAll();
    List<UserDTO> findAllCustomer();
    List<UserDTO> findAllSeller();
    List<UserDTO> findAllAdmin();
    UserDTO findById(long id);
    UserDTO findByEmail(String email);
    UserDTO save(UserDTO userDTO);
    UserDTO login(String username,String password);
    UserDTO block(long id);
    UserDTO unlock(long id);
    boolean deleteById(long id);
    boolean deleteByEmail(String email);
}
