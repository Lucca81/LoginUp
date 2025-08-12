package br.com.applogin.loginup.business;

import br.com.applogin.loginup.infrastructure.entity.User;
import br.com.applogin.loginup.infrastructure.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public void userSave(User user){
        userRepository.saveAndFlush(user);
    }

    public User buscarUserPorEmail(String email){
        return  userRepository.findByEmail(email).orElseThrow(
                () -> new RuntimeException("Email não encontrado")
        );
    }

    public void deletarUserPorEmail(@RequestParam String email){
        userRepository.deleteByEmail(email);

    }

    public void atualizarUserPorId(Long id, User user){
        User userEntity = userRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Usuario não encontrado")
        );
        User userAtualizado = User.builder()
                .email(user.getEmail() != null ? user.getEmail() : userEntity.getEmail())
                .nome(user.getNome() != null ? user.getNome() : userEntity.getNome())
                .id(userEntity.getId())
                .build();

        userRepository.saveAndFlush(userAtualizado);

    }


}
