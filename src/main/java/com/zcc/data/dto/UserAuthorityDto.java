package com.zcc.data.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/8/16.
 */
@Entity
@Table(name="USER_AUTHORITY")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UserAuthorityDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_authority_seq")
	@SequenceGenerator(name = "user_authority_seq", sequenceName = "user_authority_seq", allocationSize = 1)
	@Column(name="ID", nullable = false, updatable = false)
	private Long id;

	@Column(name="USER_ID")
	private Long userId;

	@Column(name="AUTHORITY_ID")
	private Long authorityId;
}
