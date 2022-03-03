package com.appllication.teluslibrary;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.appllication.teluslibrary.entities.Loan;
import com.appllication.teluslibrary.entities.User;
import com.appllication.teluslibrary.payload.LoanDto;
import com.appllication.teluslibrary.payload.UserDto;
import com.appllication.teluslibrary.util.LoanStatus;

@SpringBootApplication
public class TeluslibraryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TeluslibraryApplication.class, args);
	}

	@Bean
	public WebMvcConfigurer corsConfigurer() {
		return new WebMvcConfigurer() {
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				registry.addMapping("/**").allowedOrigins("http://localhost:4500").allowedMethods("GET", "POST", "PUT",
						"DELETE");
			}
		};
	}

	@Bean
	public ModelMapper modelMapper() {
		Converter<LocalDate, Float> dateToPenalty = (date) -> {
			Integer days = LocalDate.now().getDayOfYear() - date.getSource().getDayOfYear();
			return 0.2F * (days <= 7? 0 : days - 7);
		};
		Converter<List<Loan>, Integer> userToActiveloans = (list) -> {
			if(list.getSource() != null) {
				return list.getSource().stream()
					.filter(loan -> !loan.getStatus()
					.equals(LoanStatus.RETURNED.getValue()))
					.collect(Collectors.toList()).size();
			}else {
				return 0;
			}
		};
		ModelMapper mm = new ModelMapper();
		//user mapping properties
		mm.typeMap(User.class, UserDto.class).addMappings(mapper -> mapper.using(userToActiveloans).map(User::getLoans, UserDto::setActiveLoans));
		//loan mapping properties
		mm.typeMap(Loan.class, LoanDto.class).addMapping(Loan::getCorrelative, LoanDto::setId)
				.addMapping(loan -> loan.getBook().getTitle(), LoanDto::setBookTitle)
				.addMappings(mapper -> mapper.using(dateToPenalty).map(Loan::getStartDate, LoanDto::setPenalty));
		return mm;
	}
}
