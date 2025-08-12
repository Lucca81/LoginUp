package br.com.applogin.loginup.controller;

import br.com.applogin.loginup.business.UserService;
import br.com.applogin.loginup.dto.MensagemDTO;
import br.com.applogin.loginup.infrastructure.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping
    public ResponseEntity<MensagemDTO> salvarUsuario(@RequestBody User user){
        userService.userSave(user);

        MensagemDTO mensagemPost = new MensagemDTO(
                "Usuario salvo com sucesso", user.getId()
        );
        return ResponseEntity.ok(mensagemPost);
    }

    @GetMapping
    public  ResponseEntity<User> buscarUserPorEmail(@RequestParam String email){
        return ResponseEntity.ok(userService.buscarUserPorEmail(email));
    }

    @DeleteMapping
    public ResponseEntity<Void> deletarUserPorEmail(@RequestParam String email){
        userService.deletarUserPorEmail(email);
        return ResponseEntity.ok().build();

        }
    @PutMapping("/{id}")
    public ResponseEntity<MensagemDTO> atualizarUserPorId(@PathVariable Long id, @RequestBody User user){
        userService.atualizarUserPorId(id, user);

        MensagemDTO reposta = new MensagemDTO(
                "Usuario " + id +" atualizado com sucesso", id
        );

        return ResponseEntity.ok(reposta);

    }
}