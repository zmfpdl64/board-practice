package practice.board.dto.request;


import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import practice.board.dto.UserAccountDto;

import java.util.Collection;
import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

public record Boardprincipal(
        String username,
        String password,
        Collection<? extends GrantedAuthority> authorities,
        String email,
        String nickname,
        String phone,
        String address
) implements UserDetails {

    public static Boardprincipal of(String username, String password, String email,
                                    String nickname, String phone, String address) {
        Set<RoleType> roleTypes = Set.of(RoleType.USER);

        return new Boardprincipal(
                username,
                password,
                roleTypes.stream()
                        .map(RoleType::getName)
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toUnmodifiableSet()),
                email,
                nickname,
                phone,
                address
        );
    }

    public static Boardprincipal from(UserAccountDto dto) {
        return Boardprincipal.of(
                dto.userId(),
                dto.password(),
                dto.email(),
                dto.nickname(),
                dto.phone(),
                dto.address()
        );
    }

    @Override public String getPassword() {return password;}
    @Override public String getUsername() {return username;}
    @Override public Collection<? extends GrantedAuthority> getAuthorities() {return authorities;}

    @Override public boolean isAccountNonExpired() {return true;}
    @Override public boolean isAccountNonLocked() {return true;}
    @Override public boolean isCredentialsNonExpired() {return true;}
    @Override public boolean isEnabled() {return true;}


    public enum RoleType {
        USER("ROLE_USER"); //ROLE_을 이용하는건 스프링시큐리티에서 주어지는 규칙이다.

        @Getter
        private final String name;

        RoleType(String name) {
            this.name = name;
        }
    }

}
