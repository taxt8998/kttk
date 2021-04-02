package com.example.ecom.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "customer", schema = "ecom", catalog = "")
public class CustomerEntity {
    private int id;
    private int fullnameId;
    private String email;
    private String phone;
    private Collection<AddressEntity> addressesById;
    private Collection<CartEntity> cartsById;
    private FullnameEntity fullnameByFullnameId;
    private String password;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "FullnameID", nullable = false)
    public int getFullnameId() {
        return fullnameId;
    }

    public void setFullnameId(int fullnameId) {
        this.fullnameId = fullnameId;
    }

    @Basic
    @Column(name = "Email", nullable = true, length = 255)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Phone", nullable = true, length = 255)
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CustomerEntity that = (CustomerEntity) o;
        return id == that.id &&
                fullnameId == that.fullnameId &&
                Objects.equals(email, that.email) &&
                Objects.equals(phone, that.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fullnameId, email, phone);
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<AddressEntity> getAddressesById() {
        return addressesById;
    }

    public void setAddressesById(Collection<AddressEntity> addressesById) {
        this.addressesById = addressesById;
    }

    @OneToMany(mappedBy = "customerByCustomerId")
    public Collection<CartEntity> getCartsById() {
        return cartsById;
    }

    public void setCartsById(Collection<CartEntity> cartsById) {
        this.cartsById = cartsById;
    }

    @ManyToOne
    @JoinColumn(name = "FullnameID", referencedColumnName = "ID", nullable = false)
    public FullnameEntity getFullnameByFullnameId() {
        return fullnameByFullnameId;
    }

    public void setFullnameByFullnameId(FullnameEntity fullnameByFullnameId) {
        this.fullnameByFullnameId = fullnameByFullnameId;
    }

    @Basic
    @Column(name = "Password", nullable = false, length = 100)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
