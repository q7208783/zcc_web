package com.zcc.user;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.zcc.data.dto.AuthorityDto;
import com.zcc.data.dto.PaymentDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Created by NCP-620 on 2017/7/12.
 */
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {

	private Long userId;

	private String username;

	private String fullname;

	private String password;

	//private Collection<? extends GrantedAuthority> authorities;
	private List<AuthorityDto> authorities;

	private Date lastPasswordResetDate;

	private  List<PaymentDto> payments;

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities.stream()
			.map(authorityDto -> new SimpleGrantedAuthority(authorityDto.getName().name()))
			.collect(Collectors.toSet());
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

}
