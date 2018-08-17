package com.spring.orm.extend.model;

import com.spring.orm.extend.springJdbc.ColumnAlias;

import java.io.Serializable;

/**
 *
 */
public class GoodsBean implements Serializable {
    @ColumnAlias(value = "id")
    private Integer id;
    @ColumnAlias(value = "goods_id")
    private String goodsId;
    @ColumnAlias(value = "goods_name")
    private String goodsName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    @Override
    public String toString() {
        return "GoodsBean{" +
                "id=" + id +
                ", goodsId='" + goodsId + '\'' +
                ", goodsName='" + goodsName + '\'' +
                '}';
    }
}
