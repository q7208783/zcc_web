package com.zcc.controller.payment.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by NCP-620 on 2017/8/10.
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentRequest {
	private String price;
	private String detail;
	private String category;
}
