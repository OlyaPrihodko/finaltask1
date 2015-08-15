package com.epam.prihodko.finaltask.domain;

import java.util.Iterator;
import java.util.Set;

public class SetBean implements java.io.Serializable{
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
    public Object getElement(){
        Iterator it=set.iterator();
        if(it.hasNext()){
            return it.next();
        }
        return null;
    }
    public String getElementToString(){
        if(iterator.hasNext()){
        return iterator.next().toString();
        }
        return null;
    }
}
