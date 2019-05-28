package org.vincent.constraint.pojo;

import org.vincent.constraint.cust.constraint.Price;

import javax.validation.constraints.NotEmpty;

/**
 * @author PengRong
 * @package org.vincent.constraint.pojo
 * @ClassName Product.java
 * @date 2019/5/27 - 20:58
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class Product {
    // 必须非空
    @NotEmpty
    private String productName;
    // 必须在 8000 至 10000 的范围内
    // @Price 是一个定制化的 constraint
    @Price
    private float price;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Product{");
        sb.append("productName='").append(productName).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
