package com.example.ecom.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "order", schema = "ecom", catalog = "")
public class OrderEntity {
    private int id;
    private int paymentId;
    private Double total;
    private Collection<CartEntity> cartsById;
    private PaymentEntity paymentByPaymentId;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PaymentID", nullable = false)
    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    @Basic
    @Column(name = "Total", nullable = true, precision = 0)
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderEntity that = (OrderEntity) o;
        return id == that.id &&
                paymentId == that.paymentId &&
                Objects.equals(total, that.total);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, paymentId, total);
    }

    @OneToMany(mappedBy = "orderByOrderId")
    public Collection<CartEntity> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<CartEntity> cartsById) {
        this.cartsById = cartsById;
    }

    @ManyToOne
    @JoinColumn(name = "PaymentID", referencedColumnName = "ID", nullable = false)
    public PaymentEntity getPaymentByPaymentId() {
        return paymentByPaymentId;
    }

    public void setPaymentByPaymentId(PaymentEntity paymentByPaymentId) {
        this.paymentByPaymentId = paymentByPaymentId;
    }
}
