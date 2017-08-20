package com.zcc.data.dto;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.zcc.common.constance.AuthorityName;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by NCP-620 on 2017/8/15.
 */
@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="AUTHORITY")
public class AuthorityDto {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq")
	@SequenceGenerator(name = "authority_seq", sequenceName = "authority_seq", allocationSize = 1)
	@Column(name="AUTHORITY_ID", nullable = false, updatable = false)
	private Long authorityId;

	@Column(name="NAME", nullable = false, updatable = false)
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = UserDto.class)
	private List<UserDto> userDtos;
}
