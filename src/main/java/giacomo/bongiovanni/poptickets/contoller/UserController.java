package giacomo.bongiovanni.poptickets.contoller;

import giacomo.bongiovanni.poptickets.dto.TokenDTO;
import giacomo.bongiovanni.poptickets.dto.UserDTO;
import giacomo.bongiovanni.poptickets.dto.UserLoginDTO;
import giacomo.bongiovanni.poptickets.model.Role;
import giacomo.bongiovanni.poptickets.security.AuthenticationService;
import giacomo.bongiovanni.poptickets.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    private final UserService userService;
    private final AuthenticationService authenticationService;

    public UserController(UserService userService, AuthenticationService authenticationService) {
        this.userService = userService;
        this.authenticationService = authenticationService;
    }
    @PostMapping("all/user/registerCustomer")
    public ResponseEntity<UserDTO> registerCustomer(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(authenticationService.registerCustomer(userDTO), HttpStatus.CREATED);
    }
    @PostMapping("admin/user/registerSeller")
    public ResponseEntity<UserDTO> registerSeller(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(authenticationService.registerSeller(userDTO), HttpStatus.CREATED);
    }
    @PostMapping("superadmin/user/registerAdmin")
    public ResponseEntity<UserDTO> registerAdmin(@Valid @RequestBody UserDTO userDTO) {
        return new ResponseEntity<>(authenticationService.registerAdmin(userDTO), HttpStatus.CREATED);
    }

    @PostMapping(path = "all/user/authenticate")
    public ResponseEntity<TokenDTO> authenticateUser(@Valid @RequestBody UserLoginDTO userLoginDTO) {
        return new ResponseEntity<>(authenticationService.authenticate(userLoginDTO),HttpStatus.OK);
    }

    @GetMapping(value = "/all/user/findAll")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping(value = "/all/user/findAllCustomer")
    public List<UserDTO> findAllCustomer(){
        return userService.findAllCustomer();
    }

    @GetMapping(value = "/all/user/findAllSeller")
    public List<UserDTO> findAllSeller(){
        return userService.findAllSeller();
    }

    @GetMapping(value = "/all/user/findAllAdmin")
    public List<UserDTO> findAllAdmin(){
        return userService.findAllAdmin();
    }

    @GetMapping(value = "/admin/user/findById/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable long id){
        return new ResponseEntity<>(userService.findById(id),HttpStatus.OK);
    }

    @PatchMapping (value = "/admin/user/findByEmail/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
    }

    @PatchMapping (value = "/superadmin/user/block/{id}")
    public ResponseEntity<UserDTO> block(@PathVariable long id){
        return new ResponseEntity<>(userService.block(id),HttpStatus.OK);
    }

    @PatchMapping (value = "/superadmin/user/unlock/{id}")
    public ResponseEntity<UserDTO> unlock(@PathVariable long id){
        return new ResponseEntity<>(userService.unlock(id),HttpStatus.OK);
    }

    @DeleteMapping(value = "/superadmin/user/deleteById/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable long id){
        return new ResponseEntity<>(userService.deleteById(id),HttpStatus.OK);
    }
    @DeleteMapping(value = "/superadmin/user/deleteByEmail/{email}")
    public ResponseEntity<Boolean> deleteById(@PathVariable String email){
        return new ResponseEntity<>(userService.deleteByEmail(email),HttpStatus.OK);
    }
}
