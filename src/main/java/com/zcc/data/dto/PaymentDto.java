package com.zcc.data.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/7/10.
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="PAYMENT")
public class PaymentDto {
	@Id
	@GeneratedValue
	private Long paymentId;
	private String date;
	private String price;
	private String detail;
	private String category;
	private String username;
	private Long userId;
	@ManyToOne
	@JoinColumn(name = "ID")
	@JsonIgnore
	private UserDto user;

}
