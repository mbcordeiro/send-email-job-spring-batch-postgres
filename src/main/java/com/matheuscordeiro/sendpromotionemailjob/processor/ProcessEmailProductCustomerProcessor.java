package com.matheuscordeiro.sendpromotionemailjob.processor;

import java.text.NumberFormat;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.matheuscordeiro.sendpromotionemailjob.domain.ProductCustomerInterest;

@Configuration
public class ProcessEmailProductCustomerProcessor implements ItemProcessor<ProductCustomerInterest, SimpleMailMessage> {

	@Override
	public SimpleMailMessage process(ProductCustomerInterest productCustomerInterest) throws Exception {
		final var email = new SimpleMailMessage();
		email.setFrom("email@no-reply.com");
		email.setTo(productCustomerInterest.getCustomer().getEmail());
		email.setSubject("Promotion Sale!!!!");
		email.setText(generateTextPromotion(productCustomerInterest));
		Thread.sleep(2000);
		return email;
	}

	private String generateTextPromotion(ProductCustomerInterest productCustomerInterest) {
		final var writer = new StringBuilder();
		writer.append(String.format("Hello, %s!\n\n", productCustomerInterest.getCustomer().getName()));
		writer.append("This promotion may be of interest to you:\n\n");
		writer.append(String.format("%s - %s\n\n", productCustomerInterest.getProduct().getName(),
				productCustomerInterest.getProduct().getDescription()));
		writer.append(String.format("Per: %s!",
				NumberFormat.getCurrencyInstance().format(productCustomerInterest.getProduct().getPrice())));
		return writer.toString();
	}

}
