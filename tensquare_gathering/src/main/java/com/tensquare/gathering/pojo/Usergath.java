package com.tensquare.gathering.pojo;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

/**
*
* @author 华韵流风
* @Date 2021-10-24 16:30:11
*/
@Data
@Entity
@Table(name = "tb_usergath")
public class Usergath implements Serializable {

	@Id
	private String userid;

	private String gathid;

	private java.util.Date exetime;

}
