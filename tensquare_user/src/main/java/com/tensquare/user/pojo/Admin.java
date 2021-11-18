package com.tensquare.user.pojo;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

/**
*
* @author 华韵流风
*@Date 2021-10-09 10:48:19
*/
@Data
@Entity
@Table(name = "tb_admin")
public class Admin implements Serializable {

	@Id
	private String id;

	private String loginname;

	private String password;

	private String state;

}
