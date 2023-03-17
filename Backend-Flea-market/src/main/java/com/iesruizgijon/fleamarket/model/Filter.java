package com.iesruizgijon.fleamarket.model;

import javax.persistence.Entity;
import javax.persistence.criteria.Predicate;
import java.util.EnumSet;
import java.util.List;

public class Filter {


    private String search;

    private Integer cat;

    private Integer subcat;

    private Integer ac;

    private Integer pr;

    private Integer lo;


    private Double min_price;

    private Double max_price;

    private Boolean status_nuevo;

    private Boolean status_usado;

    private Boolean status_reparado;

    private Boolean status_averiado;


    private String order_price;

    private String order;

    private List<Integer> statusList;


    public Filter() {
    }

    public Filter(String search) {
        this.search = search;
    }


    public Filter(String search, Integer cat, Integer subcat, Integer ac, Integer pr, Integer lo, Double min_price,
                  Double max_price, Boolean status_nuevo, Boolean status_usado, Boolean status_reparado,
                  Boolean status_averiado, String order_price) {
        this.search = search;
        this.cat = cat;
        this.subcat = subcat;
        this.ac = ac;
        this.pr = pr;
        this.lo = lo;
        this.min_price = min_price;
        this.max_price = max_price;
        this.status_nuevo = status_nuevo;
        this.status_usado = status_usado;
        this.status_reparado = status_reparado;
        this.status_averiado = status_averiado;
        this.order_price = order_price;
    }


    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public Integer getCat() {
        return cat;
    }

    public void setCat(Integer cat) {
        this.cat = cat;
    }

    public Integer getSubcat() {
        return subcat;
    }

    public void setSubcat(Integer subcat) {
        this.subcat = subcat;
    }

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }

    public Integer getPr() {
        return pr;
    }

    public void setPr(Integer pr) {
        this.pr = pr;
    }

    public Integer getLo() {
        return lo;
    }

    public void setLo(Integer lo) {
        this.lo = lo;
    }

    public Double getMin_price() {
        return min_price;
    }

    public void setMin_price(Double min_price) {
        this.min_price = min_price;
    }

    public Double getMax_price() {
        return max_price;
    }

    public void setMax_price(Double max_price) {
        this.max_price = max_price;
    }

    public Boolean getStatus_nuevo() {
        return status_nuevo;
    }

    public void setStatus_nuevo(Boolean status_nuevo) {
        this.status_nuevo = status_nuevo;
    }

    public Boolean getStatus_usado() {
        return status_usado;
    }

    public void setStatus_usado(Boolean status_usado) {
        this.status_usado = status_usado;
    }

    public Boolean getStatus_reparado() {
        return status_reparado;
    }

    public void setStatus_reparado(Boolean status_reparado) {
        this.status_reparado = status_reparado;
    }

    public Boolean getStatus_averiado() {
        return status_averiado;
    }

    public void setStatus_averiado(Boolean status_averiado) {
        this.status_averiado = status_averiado;
    }

    public String getOrder_price() {
        return order_price;
    }

    public void setOrder_price(String order_price) {
        this.order_price = order_price;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}