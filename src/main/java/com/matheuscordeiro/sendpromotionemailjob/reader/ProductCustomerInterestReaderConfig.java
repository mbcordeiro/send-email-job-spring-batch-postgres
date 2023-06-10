package com.matheuscordeiro.sendpromotionemailjob.reader;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.RowMapper;

import com.matheuscordeiro.sendpromotionemailjob.domain.Customer;
import com.matheuscordeiro.sendpromotionemailjob.domain.Product;
import com.matheuscordeiro.sendpromotionemailjob.domain.ProductCustomerInterest;

@Configuration
public class ProductCustomerInterestReaderConfig {
	@Bean
	public JdbcCursorItemReader<ProductCustomerInterest> productCustomerInterestReader(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcCursorItemReaderBuilder<ProductCustomerInterest>()
				.name("productCustomerInterestReader")
				.dataSource(dataSource)
				.sql("select * from product_customer_interest " +
						"join customer on (customer = customer.id)" +
						"join product on (product = product.id)")
				.rowMapper(rowMapper())
				.build();
	}

	private RowMapper<ProductCustomerInterest> rowMapper() {
		return new RowMapper<ProductCustomerInterest>() {

			@Override
			public ProductCustomerInterest mapRow(ResultSet rs, int rowNum) throws SQLException {
				final var customer = new Customer();
				customer.setId(rs.getInt("id"));
				customer.setName(rs.getString("name"));
				customer.setEmail(rs.getString("email"));
				
				final var product = new Product();
				product.setId(rs.getInt(6));
				product.setName(rs.getString(7));
				product.setDescription(rs.getString("descricao"));
				product.setPrice(rs.getDouble("price"));
				
				final var productCustomerInterest = new ProductCustomerInterest();
				productCustomerInterest.setCustomer(customer);
				productCustomerInterest.setProduct(product);
				return productCustomerInterest;
			}
		};
	}
}
