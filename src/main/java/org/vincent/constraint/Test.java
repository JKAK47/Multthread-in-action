package org.vincent.constraint;

import org.vincent.constraint.pojo.Order;
import org.vincent.constraint.pojo.Product;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Collections;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

/**
 * @author PengRong
 * @package org.vincent.constraint
 * @ClassName Test.java
 * @date 2019/5/27 - 20:45
 * @ProjectName Multthread-in-action
 * @Description: 基于JSR 303 规范实现的 JavaBean 参数校验
 */
public class Test {
    public static void main(String[] args) {
        Order order = new Order();
        order.setOrderId("0123456789");
        order.setCustomer("asdfas");
        order.setEmail("155486204@qq.com");
        order.setAddress("asdfasd");
        order.setStatus("shipped");
        order.setCreateDate(new Date());
        Product product = new Product();
        product.setProductName("productName");
        product.setPrice(9000);
        order.setProduct(product);
        System.out.println(order);
        /**
         * java service 中只能 手动触发 JSR 303 校验
         * 调用 JSR 303 API 进行校验 对指定的javaBean 校验
         * */
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (Objects.nonNull(violations)&& violations.size()>0){
            StringBuffer buf = new StringBuffer();
            for (ConstraintViolation<Order> violation : violations) {
                buf.append("-" + violation.getPropertyPath().toString());
                buf.append(violation.getMessage() + "<BR>\n");
            }
            System.out.println(buf.toString());
        }else {
            System.out.println("参数校验正常");
        }
    }


}
