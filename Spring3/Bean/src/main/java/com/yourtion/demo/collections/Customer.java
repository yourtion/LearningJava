package com.yourtion.demo.collections;

import com.yourtion.demo.innerBean.Person;

import java.util.*;

/**
 * Created by yourtion on 07/05/2017.
 */
public class Customer {
    private List<Object> lists ;
    private Set<Object> sets ;
    private Map<Object, Object> maps ;
    private Properties pros;

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Set<Object> getSets() {
        return sets;
    }

    public void setSets(Set<Object> sets) {
        this.sets = sets;
    }

    public Map<Object, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<Object, Object> maps) {
        this.maps = maps;
    }

    public Properties getPros() {
        return pros;
    }

    public void setPros(Properties pros) {
        this.pros = pros;
    }

    private Person person;//不要忘记写内部要引用的 Bean

    public Customer(Person person) {
        this.person = person;
    }

    public Customer(){}

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "Customer [person=" + person + "]";
    }
}
