package com.tensquare.base.pojo;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author 华韵流风
 * @ClassName City
 * @Date 2021/9/18 13:15
 * @packageName com.tensquare.base.pojo
 * @Description TODO
 */
@Data
@Entity
@Table(name="tb_city")
public class City implements Serializable {

    @Id
    private String id;
    private String name;
    private String ishot;
}
