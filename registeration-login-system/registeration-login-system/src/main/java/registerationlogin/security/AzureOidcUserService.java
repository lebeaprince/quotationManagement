package registerationlogin.security;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import registerationlogin.entity.Role;
import registerationlogin.entity.User;
import registerationlogin.repository.RoleRepository;
import registerationlogin.repository.UserRepository;

@Service
public class AzureOidcUserService extends OidcUserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public AzureOidcUserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public OidcUser loadUser(OidcUserRequest userRequest) {
        OidcUser oidcUser = super.loadUser(userRequest);

        String email = oidcUser.getEmail();
        if (email == null || email.isBlank()) {
            Object preferred = oidcUser.getClaims().get("preferred_username");
            if (preferred != null) {
                email = preferred.toString();
            }
        }
        if (email == null || email.isBlank()) {
            email = oidcUser.getSubject() + "@unknown";
        }

        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setEmail(email);

            String name = oidcUser.getFullName();
            if (name == null || name.isBlank()) {
                Object givenName = oidcUser.getGivenName();
                Object familyName = oidcUser.getFamilyName();
                String gn = givenName == null ? "" : givenName.toString();
                String fn = familyName == null ? "" : familyName.toString();
                name = (gn + " " + fn).trim();
                if (name.isEmpty()) {
                    name = email;
                }
            }
            user.setName(name);

            String randomPassword = UUID.randomUUID().toString();
            user.setPassword(passwordEncoder.encode(randomPassword));

            Role role = roleRepository.findByName("ROLE_USER");
            if (role == null) {
                role = new Role();
                role.setName("ROLE_USER");
                role = roleRepository.save(role);
            }
            user.setRoles(Arrays.asList(role));
            userRepository.save(user);
        }

        List<SimpleGrantedAuthority> authorities = user.getRoles().stream()
                .map(r -> new SimpleGrantedAuthority(r.getName()))
                .collect(Collectors.toList());

        return new DefaultOidcUser(authorities, oidcUser.getIdToken(), oidcUser.getUserInfo());
    }
}
