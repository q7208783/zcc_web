package com.zcc.data.dto;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by NCP-620 on 2017/7/12.
 */
@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name="USER")
public class UserDto {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_seq")
	@SequenceGenerator(name = "user_seq", sequenceName = "user_seq", allocationSize = 1)
	@Column(name="USER_ID", nullable = false, updatable = false)
	private Long userId;

	@Column(name="USER_NAME", nullable = false, updatable = false)
	private String username;

	@Column(name="FULL_NAME",nullable = false)
	private String fullname;

	@Column(name="PASSWORD",nullable = false,updatable = false)
	private String password;

	@Column(name = "LASTPASSWORDRESETDATE")
	@Temporal(TemporalType.TIMESTAMP)
	@NotNull
	private Date lastPasswordResetDate;

	@ManyToMany(fetch = FetchType.EAGER, targetEntity = AuthorityDto.class)
	@JoinTable(
		name = "USER_AUTHORITY",
		joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "USER_ID")},
		inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "AUTHORITY_ID")}
	)
	private List<AuthorityDto> authorities;

	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
	private  List<PaymentDto> payments;
}
