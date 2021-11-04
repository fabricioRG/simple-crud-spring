package blautech.gt.backend.controller;

import blautech.gt.backend.domain.User;
import blautech.gt.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8080")
@RestController
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/api/users/create")
    public ResponseEntity<Object> createUser(@RequestBody User user) {
        try {
            if(!userService.findUserByEmail(user).isEmpty()){
                throw new Exception("El correo del usuario ya esta en uso");
            }
            user.setCreatedAt(getCurrentTimeInString());
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(user));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/api/users")
    public ResponseEntity<Object> getUsers() {
        try {
            List<User> persons = new ArrayList<>();
            userService.userList().forEach(persons::add);
            return ResponseEntity.status(HttpStatus.OK).body(persons);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/api/users/findByEmail/{email}")
    public ResponseEntity<Object> findUserByEmail(@PathVariable("email") String email) {
        try {
            User aux = new User();
            aux.setEmail(email);
            List<User> users = userService.findUserByEmail(aux);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping("/api/users/findByUsername/{username}")
    public ResponseEntity<Object> findUserByUsername(@PathVariable("username") String username) {
        try {
            User aux = new User();
            aux.setUsername(username);
            List<User> users = userService.findUserByUsername(aux);
            return ResponseEntity.status(HttpStatus.OK).body(users);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/api/users/update")
    public ResponseEntity<Object> updateUser(@RequestBody User params) {
        try {
            if(userService.findUserById(params.getId()) == null)
                throw new Exception("El usuario con ID solicitado no existe");
            params.setUpdatedAt(getCurrentTimeInString());
            return ResponseEntity.status(HttpStatus.OK).body(userService.save(params));
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping("/api/users/delete/{id}")
    public ResponseEntity<Object> deleteUser(@PathVariable("id") String id) {
        try {
            var user = userService.findUserById(id);
            if(user == null) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            userService.delete(user);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("No se pudo realizar la operacion", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private Timestamp getCurrentSystemTime(){
        return new Timestamp(System.currentTimeMillis());
    }

    private String getCurrentTimeInString(){
        return getCurrentSystemTime().toString();
    }

}
