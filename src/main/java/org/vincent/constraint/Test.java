package org.vincent.constraint;

import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.HibernateValidatorConfiguration;
import org.vincent.constraint.pojo.Order;
import org.vincent.constraint.pojo.Product;

import javax.validation.Configuration;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
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
        order.setStatus("shcreatedipped");
        order.setCreateDate(new Date());
        Product product = new Product();
        product.setProductName("productName");
        product.setPrice(9000);
        order.setProduct(product);
        System.out.println(order);
        /**
         * Bean Validation 规范对 Java Bean 的验证流程：
         * java service 中只能 手动触发 JSR 303 校验
         * 调用 JSR 303 API 进行校验 对指定的javaBean 校验
         * */
        /**
         * 创建默认校验工厂
         * */
       /* ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();*/
       /**
        * hibernateValidator 创建校验工厂
        * */
        Configuration<HibernateValidatorConfiguration> config =
                Validation.byProvider(HibernateValidator.class).configure();
        ValidatorFactory vf = config.buildValidatorFactory();
        Validator validator = vf.getValidator();

        /**
         * ConstraintViolation 接口用于描述 校验规则失败的信息。
         * */
        Set<ConstraintViolation<Order>> violations = validator.validate(order);
        if (Objects.nonNull(violations)&& violations.size()>0){
            StringBuffer buf = new StringBuffer();
            for (ConstraintViolation<Order> violation : violations) {
                buf.append("-" + violation.getPropertyPath().toString()+"-"+" value = "+violation.getInvalidValue());
                buf.append(violation.getMessage() + "<BR>\n");
            }
            System.out.println(buf.toString());
        }else {
            System.out.println("参数校验正常");
        }
    }


}
