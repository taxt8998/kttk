package com.example.ecom.dao;

import com.example.ecom.model.AddressEntity;
import com.example.ecom.model.CustomerEntity;
import com.example.ecom.model.FullnameEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class CustomerDAO extends DAO {
    public CustomerDAO() {
        super();
    }

    public boolean checkLogin(CustomerEntity customerEntity) {
        boolean kq = false;
        String sql = "SELECT c.email, c.ID as customerID, f.firstname, f.lastname, " +
                "a.street, a.district, a.city, a.number " +
                "FROM customer as c " +
                "LEFT JOIN fullname as f ON c.FullnameID = f.ID " +
                "LEFT JOIN address as a ON c.ID = a.CustomerID " +
                "WHERE c.phone = ? AND c.password = ?";
        PreparedStatement ps = null;
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, customerEntity.getPhone());
            ps.setString(2, customerEntity.getPassword());
            ResultSet rs = ps.executeQuery();
            ArrayList<AddressEntity> listAddressEntities = new ArrayList<AddressEntity>();
            while (rs.next()) {
                AddressEntity addressEntity = new AddressEntity();
                addressEntity.setStreet(rs.getString("street"));
                addressEntity.setCity(rs.getString("city"));
                addressEntity.setDistrict(rs.getString("district"));
                addressEntity.setNumber(rs.getString("number"));
                listAddressEntities.add(addressEntity);
                FullnameEntity fullnameEntity = new FullnameEntity();
                fullnameEntity.setFirstname(rs.getString("firstname"));
                fullnameEntity.setLastname(rs.getString("lastname"));
                customerEntity.setId(rs.getInt("customerID"));
                customerEntity.setEmail(rs.getString("email"));
                customerEntity.setFullnameByFullnameId(fullnameEntity);
                kq = true;
            }
            customerEntity.setAddressesById(listAddressEntities);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return kq;
    }

    public boolean register(CustomerEntity customerEntity) {
        boolean kq = false;
        try {
            conn.setAutoCommit(false);
            String sql1 = "INSERT INTO fullname(firstname,lastname) values (?,?);";
            PreparedStatement ps = conn.prepareStatement(sql1, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, customerEntity.getFullnameByFullnameId().getFirstname());
            ps.setString(2, customerEntity.getFullnameByFullnameId().getLastname());
            ps.executeUpdate();
            ResultSet rs = ps.getGeneratedKeys();
            if (rs.next()) {
                int fullnameID = rs.getInt(1);
                String sql2 = "INSERT INTO customer(FullnameID,email,phone,password) values (?,?,?,?);";
                ps = conn.prepareStatement(sql2, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, fullnameID);
                ps.setString(2, customerEntity.getEmail());
                ps.setString(3, customerEntity.getPhone());
                ps.setString(4, customerEntity.getPassword());
                ps.executeUpdate();
                rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    int customerID = rs.getInt(1);
                    String sql3 = "INSERT INTO address(number,street,district,city,customerID) values (?,?,?,?,?);";
                    ps = conn.prepareStatement(sql3, Statement.RETURN_GENERATED_KEYS);
                    Iterator iterator = customerEntity.getAddressesById().iterator();
                    AddressEntity addressEntity = (AddressEntity) iterator.next();
                    ps.setString(1, addressEntity.getNumber());
                    ps.setString(2, addressEntity.getStreet());
                    ps.setString(3, addressEntity.getDistrict());
                    ps.setString(4, addressEntity.getCity());
                    ps.setInt(5, customerID);
                    ps.executeUpdate();
                    conn.commit();
                    kq = true;
                }


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
            try {
                conn.rollback();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return kq;
    }

}
