package com.nithin.secure_user_platform.security.principal;

import com.nithin.secure_user_platform.utility.enums.Roles;
import com.nithin.secure_user_platform.utility.enums.UserStates;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

// store data of security identity for ONE request from JWT filter in security-filter-chain
// i.e, authenticated identity of a request for controller & services
public class UserPrincipal implements UserDetails {

    private final Long userId;
    private final String username;
    private final Roles role;
    private final UserStates state;

    public UserPrincipal(Long userId, String username, Roles role, UserStates state) {
        this.userId = userId;
        this.username = username;
        this.role = role;
        this.state = state;
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_"+role.name()));
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public Long getUserId(){ return userId; }

    public UserStates getUserState(){ return state; }

    // ** UserDetails flags - LOGIN gatekeeper **
    @Override
    public boolean isAccountNonExpired() { return true; }

    // TO-DO: suspended user
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // TO-DO: password expired
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    // TO-DO: soft-deleted user
    @Override
    public boolean isEnabled() { return true; }
}
