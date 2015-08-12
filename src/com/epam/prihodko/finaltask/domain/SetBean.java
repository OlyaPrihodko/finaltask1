package com.epam.prihodko.finaltask.domain;

import java.util.Iterator;
import java.util.Set;

public class SetBean {
    private Iterator iterator;
    private Set set;

    public SetBean() {}
    public SetBean(Set set) {
        this.set = set;
    }
    public String getSize(){
        iterator = set.iterator();
        return Integer.toString(set.size());
    }
    public String getElement(){
        return iterator.next().toString();
    }
}
