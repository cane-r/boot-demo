package com.n11.demo.converters;

import java.time.LocalDate;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateTimeToStringConverter implements Converter<LocalDate, String> {
	 
   @Override
   public String convert(LocalDate source) {
       if (source == null) {
           return null;
       }

       return source.toString();
   }
}
