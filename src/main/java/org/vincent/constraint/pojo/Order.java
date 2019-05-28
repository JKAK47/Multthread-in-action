package org.vincent.constraint.pojo;


import org.vincent.constraint.cust.constraint.Status;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import java.util.Date;

/**
 * @author PengRong
 * @package org.vincent.constraint.pojo
 * @ClassName Order.java
 * @date 2019/5/27 - 20:58
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class Order {
    // 必须不为 null, 大小是 10
    @NotNull
    @Size(min = 10, max = 10)
    private String orderId;
    // 必须不为空
    @NotEmpty
    private String customer;
    // 必须是一个电子信箱地址
    @Email
    private String email;
    // 必须不为空
    @NotEmpty
    private String address;
    // 必须不为 null, 必须是下面四个字符串'created', 'paid', 'shipped', 'closed'其中之一
    // @Status 是一个定制化的 contraint
    @NotNull
    @Status
    private String status;
    // 必须不为 null，并且时间必须是过去的或者是现在的时间
    @NotNull
    @PastOrPresent
    private Date createDate;
    // 嵌套验证
    @Valid
    private Product product;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Order{");
        sb.append("orderId='").append(orderId).append('\'');
        sb.append(", customer='").append(customer).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", address='").append(address).append('\'');
        sb.append(", status='").append(status).append('\'');
        sb.append(", createDate=").append(createDate);
        sb.append(", product=").append(product);
        sb.append('}');
        return sb.toString();
    }
}
