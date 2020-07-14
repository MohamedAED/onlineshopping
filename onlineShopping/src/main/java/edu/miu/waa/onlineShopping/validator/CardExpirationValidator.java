package edu.miu.waa.onlineShopping.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;
import java.util.Date;

public class CardExpirationValidator implements ConstraintValidator<CardExpirationDate, String> {
    @Override
    public void initialize(CardExpirationDate annotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value.matches("(?:0[1-9]|1[0-2])/[0-9]{2}") ){
            String year ="20";
            year +=value.substring(3);
            Date date = new Date();
            date.setYear(Integer.parseInt(year));
            date.setMonth(Integer.parseInt(value.substring(0,2)));
            if(date.after(new Date()))
                return true;
        }
        return false;
    }
}