package com.szq.ajax.pojo;

import java.util.Objects;

public class City {
    private String content;

    public City(String content) {
        this.content = content;
    }

    public City() {
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(content, city.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(content);
    }

    @Override
    public String toString() {
        return "City{" +
                "content='" + content + '\'' +
                '}';
    }
}
