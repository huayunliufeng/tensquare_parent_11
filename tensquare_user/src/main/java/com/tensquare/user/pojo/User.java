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
@Table(name = "tb_user")
public class User implements Serializable {

	@Id
	private String id;

	private String mobile;

	private String password;

	private String nickname;

	private String sex;

	private java.util.Date birthday;

	private String avatar;

	private String email;

	private java.util.Date regdate;

	private java.util.Date updatedate;

	private java.util.Date lastdate;

	private Long online;

	private String interest;

	private String personality;

	private Integer fanscount;

	private Integer followcount;

}
