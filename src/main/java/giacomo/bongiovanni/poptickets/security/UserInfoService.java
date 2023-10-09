package giacomo.bongiovanni.poptickets.security;

import giacomo.bongiovanni.poptickets.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserInfoService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Recupera l'utente dal repository (ad esempio, un database) in base al nome utente
        // Nota: dovresti implementare il tuo metodo findByUsername o simile nel repository
         giacomo.bongiovanni.poptickets.model.User userEntity = userRepository.findByEmail(email).orElseThrow(EntityNotFoundException::new);

        // Costruisci e restituisci un oggetto UserDetails basato sul tuo modello di utente
        UserDetails userDetails = User.builder()
                .username(userEntity.getUsername())
                .password(userEntity.getPassword())
                .roles(String.valueOf(userEntity.getRole())) // Aggiungi ruoli o autorizzazioni dell'utente
                .build();

        return userDetails;
    }
}
