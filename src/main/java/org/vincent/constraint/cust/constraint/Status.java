package org.vincent.constraint.cust.constraint;

import org.vincent.constraint.cust.constraint.validator.StatusValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE_USE;

/**
 * @author PengRong
 * @package org.vincent.constraint.cust.constraint
 * @ClassName Status.java
 * @date 2019/5/27 - 21:07
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD,ElementType.FIELD,ElementType.ANNOTATION_TYPE,ElementType.CONSTRUCTOR,PARAMETER,TYPE_USE})
@Constraint(validatedBy = {StatusValidator.class})
public @interface Status {
    String message() default "不正确的状态 , 应该是 'created', 'paid', shipped', closed'其中之一 by pr.  "; /** message  属性指定当属性不满足条件时候 输出错误信息 */
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
