package giacomo.bongiovanni.poptickets.contoller;

import giacomo.bongiovanni.poptickets.dto.UserDTO;
import giacomo.bongiovanni.poptickets.security.JwtService;
import giacomo.bongiovanni.poptickets.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class UserController {
    private final UserService userService;
    private final JwtService jwtService;

    public UserController(UserService userService, JwtService jwtService) {
        this.userService = userService;
        this.jwtService = jwtService;
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

    @PostMapping(value = "/admin/user/findByEmail/{email}")
    public ResponseEntity<UserDTO> findByEmail(@PathVariable String email){
        return new ResponseEntity<>(userService.findByEmail(email),HttpStatus.OK);
    }

    @PostMapping(value = "/all/user/save")
    public ResponseEntity<UserDTO> save (@RequestBody UserDTO userDTO){
        return new ResponseEntity<>(userService.save(userDTO),HttpStatus.CREATED);
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
