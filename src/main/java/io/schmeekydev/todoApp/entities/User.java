package io.schmeekydev.todoApp.entities;

import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class User implements UserDetails {

    public User(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "name", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 30)
    private String name;

    @Column(name = "username", nullable = false, length = 20)
    @NotNull
    @NotEmpty
    @Size(min = 3, max = 20)
    private String username;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @Column(name = "password", nullable = false, length = 100)
    @NotNull
    @NotEmpty
    @Pattern(regexp = "[a-zA-Z0-9]+$")
    @Size(min = 6, max = 200)
    private String password;

    @NotNull
    @NotEmpty
    @Size(min = 5, max = 100)
    private String about;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
