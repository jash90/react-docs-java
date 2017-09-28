package com.zimny.reactdocs.model;



public class Component {
    private String name;
    private String link;


    public Component(String name, String link) {
        this.name = name;
        this.link = link;
    }


    public String getLink() {
        return link;
    }


    @Override
    public String toString() {
        return name;
    }
}
