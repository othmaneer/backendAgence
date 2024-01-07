package ma.agence.voyage.controller;

import ma.agence.voyage.entity.Login;
import ma.agence.voyage.service.LoginServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/login")
@CrossOrigin(origins = "*")
public class LoginController {
    @Autowired
    private LoginServiceImpl loginService;
    // Endpoint pour ajouter un login
    @PostMapping
    public ResponseEntity<Login> addLogin(@RequestBody Login login) {
        Login newLogin = loginService.ajouterLogin(login);
        return new ResponseEntity<>(newLogin, HttpStatus.CREATED);
    }


    @GetMapping
    public List<Login> getAllLogins() {
        return loginService.getLogin();
    }

    // Endpoint pour supprimer un login par ID
    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteLogin(@PathVariable int userId) {
        loginService.supprimerLogin(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    @GetMapping("/exist/{username}/{mdp}")
    public ResponseEntity<Boolean> existLogin(@PathVariable String username, @PathVariable String mdp) {
        boolean exists = loginService.existLogin(username, mdp);
        return new ResponseEntity<>(exists, HttpStatus.OK);
    }
}
