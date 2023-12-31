package giacomo.bongiovanni.poptickets.security;

import giacomo.bongiovanni.poptickets.dto.TokenDTO;
import giacomo.bongiovanni.poptickets.dto.UserDTO;
import giacomo.bongiovanni.poptickets.dto.UserLoginDTO;
import giacomo.bongiovanni.poptickets.dto.mapper.UserMapper;
import giacomo.bongiovanni.poptickets.exception.EntityCreationExeption;
import giacomo.bongiovanni.poptickets.exception.EntityDuplicateException;
import giacomo.bongiovanni.poptickets.exception.EntityNotFoundException;
import giacomo.bongiovanni.poptickets.model.Role;
import giacomo.bongiovanni.poptickets.model.User;
import giacomo.bongiovanni.poptickets.repository.UserRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public UserDTO register(@NotNull UserDTO userDTO, Role role) {
        // I check if there is a user with the same email
        if (userRepository.findByEmail(userDTO.getEmail()).orElse(null) != null){
            throw new EntityDuplicateException(userDTO.getEmail());
        }
        User user = UserMapper.INSTANCE.userDTOToUser(userDTO);

        if (role == null) return null;

        user.setRole(role);
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        try {
            user = userRepository.save(user);
        }catch (DataIntegrityViolationException e){
            throw new EntityDuplicateException(user.getEmail());
        }catch (Exception e){
            throw new EntityCreationExeption(user.getId());
        }
        return UserMapper.INSTANCE.userToUserDTO(user);
    }

    public UserDTO registerAdmin(UserDTO userDTO){
        Role role = Role.ADMIN;
        return register(userDTO,role);
    }
    public UserDTO registerSeller(UserDTO userDTO){
        Role role = Role.SELLER;
        return register(userDTO,role);
    }
    public UserDTO registerCustomer(UserDTO userDTO){
        Role role = Role.CUSTOMER;
        return register(userDTO,role);
    }
    public TokenDTO authenticate(@NotNull UserLoginDTO userLoginDTO) {
        // I authenticate a user based on their email and password
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userLoginDTO.getEmail(), userLoginDTO.getPassword())
        );

        User user = userRepository.findByEmail(userLoginDTO.getEmail()).orElseThrow(()-> new EntityNotFoundException(userLoginDTO.getEmail()));

        String token = jwtService.generateToken(user.getUsername());

        return new TokenDTO(token);
    }
}
