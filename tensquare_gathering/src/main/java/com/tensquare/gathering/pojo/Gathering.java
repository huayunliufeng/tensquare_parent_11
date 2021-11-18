package com.tensquare.gathering.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.*;

/**
*
* @author 华韵流风
* @Date 2021-10-24 16:30:11
*/
@Data
@Entity
@Table(name = "tb_gathering")
public class Gathering implements Serializable {

	@Id
	private String id;

	private String name;

	private String summary;

	private String detail;

	private String sponsor;

	private String image;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date starttime;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endtime;

	private String address;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date enrolltime;

	private String state;

	private String city;

}
