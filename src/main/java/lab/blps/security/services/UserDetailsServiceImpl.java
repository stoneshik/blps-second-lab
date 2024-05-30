package lab.blps.security.services;

import lab.blps.bd.entites.User;
import lab.blps.repositories.UserRepository;
import lab.blps.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository
            .findByEmail(username)
            .orElseThrow(
                () -> new UsernameNotFoundException("User Not Found with username: " + username)
            );
        return UserDetailsImpl.build(user);
    }
}
