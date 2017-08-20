package com.zcc.data.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zcc.data.dto.PaymentDto;

/**
 * Created by NCP-620 on 2017/8/16.
 */
@Service
public class PaymentServiceImp {

	@Autowired
	PaymentRepository paymentRepository;

	public List<PaymentDto> findByUsername(String username){
		return paymentRepository.findByUsername(username);
	}

	public PaymentDto savePayment(PaymentDto paymentDto){
		return paymentRepository.save(paymentDto);
	}
}
