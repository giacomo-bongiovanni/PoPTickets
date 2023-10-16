package giacomo.bongiovanni.poptickets.util;

import giacomo.bongiovanni.poptickets.model.Role;
import giacomo.bongiovanni.poptickets.model.User;
import giacomo.bongiovanni.poptickets.repository.UserRepository;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class DefaulUserLoader implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DefaulUserLoader(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (!userRepository.existsByEmail("admin@example.com")) {
            User user = new User();
            user.setName("Admin");
            user.setSurname("User");
            user.setEmail("admin@example.com");
            user.setRole(Role.SUPERADMIN);
            user.setPassword(passwordEncoder.encode("password"));
            user.setBirthDate(LocalDate.now());
            user.setFiscalCode("AAAAAAAAAAAA");
            userRepository.save(user);
        }
    }
}
