package org.vincent.constraint.cust.constraint;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * @author PengRong
 * @package org.vincent.constraint.cust.constraint
 * @ClassName Price.java
 * @date 2019/5/27 - 21:01
 * @ProjectName Multthread-in-action
 * @Description: 定制化 constraint 校验规则, 由两个内置的 constraint 组合而成。 Min,Max 两个校验规则
 */
@Min(value = 8000)
@Max(value = 10000)
@Constraint(validatedBy = {}) /** 组合原来的 校验注解，不用实现 自己的校验方法 */
@Documented
@Target({ANNOTATION_TYPE, METHOD, FIELD, CONSTRUCTOR, PARAMETER, TYPE_USE})
@Retention(RetentionPolicy.RUNTIME)
public @interface Price {
    String message() default "错误的价格";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
