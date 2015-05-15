package com.sitexa.post.data;

/**
 * Filter entity.
 *
 * @author MyEclipse Persistence Tools
 */

public class Filter implements java.io.Serializable {

    // Fields

    private Integer id;
    private String words;
    private String status;

    // Constructors

    /**
     * default constructor
     */
    public Filter() {
    }

    /**
     * full constructor
     */
    public Filter(String words) {
        this.words = words;
    }

    // Property accessors

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWords() {
        return this.words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Filter filter = (Filter) o;

        if (!id.equals(filter.id)) return false;
        if (!words.equals(filter.words)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + words.hashCode();
        return result;
    }
}