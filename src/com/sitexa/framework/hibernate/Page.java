package com.sitexa.framework.hibernate;

public class Page {

    public static final int INDEX_FIRST = 1;
    public static final int INDEX_UNDEFINED = -1;

    public static final int SIZE_ONE = 1;
    public static final int SIZE_TWENTY = 20;
    public static final int TOTAL_ZERO = 0;

    private int aPageSize = SIZE_TWENTY;

    private int total = TOTAL_ZERO;
    private int current = INDEX_FIRST;

    static int count = 0;

    public Page() {
    }

    public Page(int aPageSize) {
        setPageSize(aPageSize);
    }

    public Page(int current, int total) {
        this.current = current;
        this.total = total;
    }

    public boolean hasNext() {
        return current < total;
    }

    public boolean hasLast() {
        return current > INDEX_FIRST;
    }

    public boolean hasHead() {
        return current != INDEX_FIRST;
    }

    public boolean hasTail() {
        return current != total;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return this.aPageSize;
    }

    public void setPageSize(int aPageSize) {
        this.aPageSize = aPageSize;
    }

    public boolean isCurrentInvalid() {
        return this.current == Page.INDEX_UNDEFINED;
    }

    @Override
    public String toString() {
        return "Page{" +
                "aPageSize=" + aPageSize +
                ", total=" + total +
                ", current=" + current +
                '}';
    }
}
