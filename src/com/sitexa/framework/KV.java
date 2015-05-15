package com.sitexa.framework;

/**
 * User: xnpeng
 * Date: 2009-5-5
 * Time: 19:38:14
 */
public class KV {
    private String id;
    private String name;

    public KV(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "KV{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
