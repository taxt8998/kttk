package com.example.ecom.controller;

import com.example.ecom.dao.CustomerDAO;
import com.example.ecom.model.AddressEntity;
import com.example.ecom.model.CustomerEntity;
import com.example.ecom.model.FullnameEntity;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;

@Controller
public class EcomController {

    CustomerDAO customerDAO = new CustomerDAO();

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/doRegister")
    public String doRegister(String firstname,
                             String lastname,
                             String email,
                             String password,
                             String phone,
                             String city,
                             String district,
                             String street,
                             String number
    ) {
        FullnameEntity fullnameEntity = new FullnameEntity();
        fullnameEntity.setFirstname(firstname);
        fullnameEntity.setLastname(lastname);
        ArrayList<AddressEntity> listAddress = new ArrayList<AddressEntity>();
        AddressEntity addressEntity = new AddressEntity();
        addressEntity.setStreet(street);
        addressEntity.setNumber(number);
        addressEntity.setCity(city);
        addressEntity.setDistrict(district);
        listAddress.add(addressEntity);
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setEmail(email);
        customerEntity.setPhone(phone);
        customerEntity.setPassword(password);
        customerEntity.setAddressesById(listAddress);
        customerEntity.setFullnameByFullnameId(fullnameEntity);
        boolean kq = customerDAO.register(customerEntity);
        if (kq) return "index";
        else return "register";
    }

    @PostMapping("/doLogin")
    public String login(String phone, String password, Model model, HttpSession session) {
        CustomerEntity customer = new CustomerEntity();
        customer.setPhone(phone);
        customer.setPassword(password);
        boolean kq = customerDAO.checkLogin(customer);
        if (kq) {
            session.setAttribute("user",customer);
            model.addAttribute("user",customer);
            return "home";
        } else return "index";
    }

}