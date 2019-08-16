package com.example.myapplication;

public class Anime {
    String name, description, links;

    public Anime(String name, String description, String links) {
        this.name = name;
        this.description = description;
        this.links = links;
    }

    public Anime(){}

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getLinks() {
        return links;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLinks(String links) {
        this.links = links;
    }
}
