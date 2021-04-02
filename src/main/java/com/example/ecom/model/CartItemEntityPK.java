package com.example.ecom.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Objects;

public class CartItemEntityPK implements Serializable {
    private int cartId;
    private int itemId;

    @Column(name = "CartID", nullable = false)
    @Id
    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
    }

    @Column(name = "ItemID", nullable = false)
    @Id
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
        CartItemEntityPK that = (CartItemEntityPK) o;
        return cartId == that.cartId &&
                itemId == that.itemId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(cartId, itemId);
    }
}
