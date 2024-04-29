package com.ill.web.Mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.ill.web.pojo.Img;
import com.ill.web.pojo.Imgfile;

@Mapper
public interface UserMapper {

	public Long count(String uid,String tag);

	//查询
	public List<Img> search(String uid,String tag,Integer start,Integer pagesize);
	
	//添加
	public void insert(Img img);
	
	//删除
	public void delete(List<Integer> ids);
	
	public List<Imgfile> deleteSearch(List<Integer> ids);
	
	@Select("select * from img where id=#{id}")
	public Img getById(String id);
	
	
	public void update(Img img);
	
	
	
}
