package org.servicea.util;

import java.util.ArrayList;
import java.util.List;


public class MyPage<T> {
	List<T> data;
    Integer total;
    Integer start;
    Integer pageSize;
    Integer resultCount;
    public static final int DEFAULE_PAGESIZE = 10;

    public MyPage(Integer pageSize) {
        this.pageSize = 10;
        this.data = new ArrayList();
        this.total = 0;
        this.start = 1;
        this.pageSize = pageSize;
        this.resultCount = 0;
    }

    public MyPage(org.springframework.data.domain.Page page) {
        this(page.getNumber(), page.getSize(), (int)page.getTotalElements(), page.getContent());
    }

    public MyPage(com.baomidou.mybatisplus.plugins.Page page) {
        this(page.getCurrent(), page.getSize(), page.getTotal(), page.getRecords());
    }

    public MyPage(Integer start, Integer pageSize, Integer resultCount) {
        this.pageSize = 10;
        this.total = resultCount / pageSize + (resultCount % pageSize > 0 ? 1 : 0);
        this.start = start;
        this.pageSize = pageSize;
        this.resultCount = resultCount;
    }

    public MyPage(Integer start, Integer pageSize, Integer resultCount, List<T> data) {
        this(start, pageSize, resultCount);
        this.data = data;
    }

    public MyPage() {
        this.pageSize = 10;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<T> getData() {
        return this.data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public Integer getStart() {
        return this.start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getPageSize() {
        return this.pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getResultCount() {
        return this.resultCount;
    }

    public void setResultCount(Integer resultCount) {
        this.resultCount = resultCount;
    }

    public String toString() {
        return "Page{data=" + this.data + ", total=" + this.total + ", start=" + this.start + ", pageSize=" + this.pageSize + ", resultCount=" + this.resultCount + '}';
    }
}
