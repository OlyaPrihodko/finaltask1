package com.epam.prihodko.finaltask.domain;

import java.util.Iterator;
import java.util.Map;

public class MapBean implements java.io.Serializable{
    private Iterator<Map.Entry<Integer,Object>> iterator;
    private Map map;
    private int id;

    public MapBean() {}
    public MapBean(Map map) {

        this.map = map;

    }
    public String getSize(){
        iterator = map.entrySet().iterator();
        return Integer.toString(map.size());
    }

    public Object getElement(){
        Iterator<Map.Entry<Integer,Object>> it=map.entrySet().iterator();
        if(it.hasNext()){
            Map.Entry<Integer,Object> m = it.next();
            return m.getValue();
        }
        return null;
    }
    public String getElementToString(){
        if(iterator.hasNext()){
            Map.Entry<Integer,Object> m = iterator.next();
            this.setId(m.getKey());
            return m.getValue().toString();
        }
        return null;
    }
    public int getId(){
        return id;
    }
    public void setId(int id){
        this.id=id;
    }
}