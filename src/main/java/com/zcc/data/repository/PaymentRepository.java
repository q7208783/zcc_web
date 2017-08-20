package com.zcc.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zcc.data.dto.PaymentDto;

/**
 * Created by NCP-620 on 2017/7/10.
 */
//
public interface PaymentRepository extends JpaRepository<PaymentDto, Long> {
	PaymentDto findPaymentByPaymentId(Long paymentId);
	List<PaymentDto> findByUsername(String username);
}
