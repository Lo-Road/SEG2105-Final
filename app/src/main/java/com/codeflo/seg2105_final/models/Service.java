package com.codeflo.seg2105_final.models;

import java.util.ArrayList;

public class Service {
    String name;
    double rate;
    ArrayList<Service> children;

    public Service(String name, double rate, ArrayList<Service> children){
        this.name = name;
        this.rate = rate;
        this.children = children;
    }

    public Service(String name, int rate){
        this.name = name;
        this.rate = rate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public ArrayList<Service> getChildren() {
        return children;
    }

    public void addChild(Service child) {
        children.add(child);
    }
}
