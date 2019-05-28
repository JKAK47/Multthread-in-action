package org.vincent.constraint.cust.constraint.validator;

import org.vincent.constraint.cust.constraint.Status;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Arrays;

/**
 * @author PengRong
 * @package org.vincent.constraint.cust.constraint.validator
 * @ClassName StatusValidator.java
 * @date 2019/5/27 - 21:20
 * @ProjectName Multthread-in-action
 * @Description: 自定义校验规则
 */
public class StatusValidator implements ConstraintValidator<Status, String> {
    private final String[] ALL_STATUS = {"created", "paid", "shipped", "closed"};

    @Override
    public void initialize(Status constraintAnnotation) {
        //System.out.println(" initialize message"+constraintAnnotation.message());

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (Arrays.asList(ALL_STATUS).contains(value))
            return true;
        return false;
    }
}
