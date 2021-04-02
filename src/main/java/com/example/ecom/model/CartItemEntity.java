package com.example.ecom.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "cart_item", schema = "ecom", catalog = "")
@IdClass(CartItemEntityPK.class)
public class CartItemEntity {
    private int cartId;
    private int itemId;
    private CartEntity cartByCartId;
    private ItemEntity itemByItemId;
    private Integer quantity;
    private Double price;

    @Id
    @Column(name = "CartID", nullable = false)
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Id
    @Column(name = "ItemID", nullable = false)
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CartItemEntity that = (CartItemEntity) o;
        return cartId == that.cartId &&
                itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, itemId);
    }

    @ManyToOne
    @JoinColumn(name = "CartID", referencedColumnName = "ID", nullable = false)
    public CartEntity getCartByCartId() {
        return cartByCartId;
    }

    public void setCartByCartId(CartEntity cartByCartId) {
        this.cartByCartId = cartByCartId;
    }

    @ManyToOne
    @JoinColumn(name = "ItemID", referencedColumnName = "ID", nullable = false)
    public ItemEntity getItemByItemId() {
        return itemByItemId;
    }

    public void setItemByItemId(ItemEntity itemByItemId) {
        this.itemByItemId = itemByItemId;
    }

    @Basic
    @Column(name = "Quantity", nullable = true)
    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Basic
    @Column(name = "Price", nullable = true, precision = 0)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
