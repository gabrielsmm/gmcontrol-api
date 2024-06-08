package com.gabrielsmm.gmcontrol.security;

import com.gabrielsmm.gmcontrol.entities.enums.Perfil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Set;
import java.util.stream.Collectors;

@NoArgsConstructor
@Getter
public class UserSS implements UserDetails {

    private Long id;
    private String usuario;
    private String senha;
    private Collection<? extends GrantedAuthority> authorities;

    public UserSS(Long id, String usuario, String senha, Set<Perfil> perfis) {
        this.id = id;
        this.usuario = usuario;
        this.senha = senha;
        this.authorities = perfis.stream().map(x -> new SimpleGrantedAuthority(x.getDescricao())).collect(Collectors.toList());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return usuario;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    public boolean hasRole(Perfil perfil) {
        return getAuthorities().contains(new SimpleGrantedAuthority(perfil.getDescricao()));
    }

}
