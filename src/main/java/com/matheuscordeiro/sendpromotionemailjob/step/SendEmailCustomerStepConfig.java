package com.matheuscordeiro.sendpromotionemailjob.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;

import com.matheuscordeiro.sendpromotionemailjob.domain.ProductCustomerInterest;

@Configuration
public class SendEmailCustomerStepConfig {
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step sendEmailCustomersStep(
			ItemReader<ProductCustomerInterest> productCustomerInterestReader,
			ItemProcessor<ProductCustomerInterest, SimpleMailMessage> productCustomerInterestProcessor,
			ItemWriter<SimpleMailMessage> sendEmailProdcutCustomerWriter) {
		return stepBuilderFactory
				.get("sendEmailCustomersStep")
				.<ProductCustomerInterest, SimpleMailMessage>chunk(1)
				.reader(productCustomerInterestReader)
				.processor(productCustomerInterestProcessor)
				.writer(sendEmailProdcutCustomerWriter)
				.build();
	}
}
