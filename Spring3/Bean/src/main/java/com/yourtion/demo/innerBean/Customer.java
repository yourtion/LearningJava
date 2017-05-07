package com.yourtion.demo.innerBean;

/**
 * Created by yourtion on 07/05/2017.
 */
public class Customer {
    private Person person;

    public Customer(Person person) {
        this.person = person;
    }

    public Customer() {}

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Customer [person=" + person + "]";
    }
}
