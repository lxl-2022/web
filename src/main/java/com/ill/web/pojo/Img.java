package com.ill.web.pojo;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Img {
	private String uid;
	private String uuid;
	private Integer id;
	private String tag;
	private List<String> taglist;
	private String author;
	private Integer zan;
	private Integer visit;
	private Integer shoucang;
	private Integer num;
    private LocalDateTime createTime; //创建时间
    private LocalDateTime updateTime; //修改时间
    private String url;



	public void listtag() {

		String[] tag1 = tag.split("&!");
		taglist = new ArrayList<String>() ;
		for (int i = 0; i < tag1.length; i++) {
			taglist.add(i, tag1[i]);
			}
		}
	}
