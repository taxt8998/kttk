package com.example.ecom.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Objects;

@Entity
@Table(name = "fullname", schema = "ecom", catalog = "")
public class FullnameEntity {
    private int id;
    private String lastname;
    private String firstname;
    private Collection<CustomerEntity> customersById;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "Lastname", nullable = true, length = 255)
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Basic
    @Column(name = "Firstname", nullable = true, length = 255)
    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FullnameEntity that = (FullnameEntity) o;
        return id == that.id &&
                Objects.equals(lastname, that.lastname) &&
                Objects.equals(firstname, that.firstname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, lastname, firstname);
    }

    @OneToMany(mappedBy = "fullnameByFullnameId")
    public Collection<CustomerEntity> getCustomersById() {
        return customersById;
    }

    public void setCustomersById(Collection<CustomerEntity> customersById) {
        this.customersById = customersById;
    }
}
