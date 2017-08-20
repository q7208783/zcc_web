package com.zcc.controller.payment;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zcc.common.annotation.RequestAttribute;
import com.zcc.common.constance.ParamConstance;
import com.zcc.common.exception.CommonException;
import com.zcc.data.UserConverter;
import com.zcc.data.repository.PaymentServiceImp;
import com.zcc.data.repository.UserServiceImp;
import com.zcc.controller.payment.request.PaymentRequest;
import com.zcc.data.dto.PaymentDto;
import com.zcc.user.User;

/**
 * Created by NCP-620 on 2017/7/11.
 */
@Controller
public class PaymentController {
	private static final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);

	@Autowired
	PaymentServiceImp paymentServiceImp;

	@Autowired
	UserConverter converter;

	SimpleDateFormat formatter=new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	@PostMapping("/addPayment")
	public String addPayment(@RequestAttribute("user")User user, PaymentRequest request) {
		PaymentDto paymentDto = new PaymentDto();
		paymentDto.setDetail(request.getDetail());
		paymentDto.setCategory(request.getCategory());
		paymentDto.setPrice(request.getPrice());
		paymentDto.setDate(formatter.format(new Date()));
		paymentDto.setUser(converter.convertUserToUserDto(user));
		paymentDto.setUsername(user.getUsername());
		paymentServiceImp.savePayment(paymentDto);
		return "redirect:/showPaymentList";
	}

	@RequestMapping(value = "/showPaymentList")
	public String showPaymentList(@RequestAttribute("user") User user, Model model) {
		if (user == null)
			throw new CommonException(ParamConstance.ERROR_CODE_NOTFOUNT, "user userId cannot find !");
		List<PaymentDto> paymentDtos = paymentServiceImp.findByUsername(user.getUsername());
		model.addAttribute("paymentDtos", paymentDtos);
		return "paymentList";
	}
}
