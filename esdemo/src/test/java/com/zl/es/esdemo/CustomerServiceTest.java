package com.zl.es.esdemo;

import com.zl.es.esdemo.customer.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerServiceTest {


    @Autowired
    private CustomerService customerService;


    @Test
    public void count() {
        System.out.println(customerService.count());
    }

    @Test
    public void save() {
        customerService.save(new Customer("Alice", "Smith"));
        customerService.save(new Customer("Bob", "Smith"));
    }

    @Test
    public void delete() {
    }

    @Test
    public void getAll() {
        Iterable<Customer> iterable = customerService.getAll();
        iterable.forEach(e->System.out.println(e.toString()));
        System.out.println(customerService.getAll());
    }

    @Test
    public void getByName() {
         List<Customer> list = customerService.getByName("Alice");
        System.out.println(list);
    }

    @Test
    public void pageQuery() {
        Page<Customer> page = customerService.pageQuery(0, 10, "Alice");
        System.out.println(page.getTotalPages());
        System.out.println(page.getNumber());
        System.out.println(page.getContent());
    }
}