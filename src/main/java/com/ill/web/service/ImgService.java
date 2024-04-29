package com.ill.web.service;

import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ill.web.pojo.Img;
import com.ill.web.pojo.PageBean;


public interface ImgService {
	
	PageBean search(String uid,String tag,Integer page,Integer pagesize);
	
	void add(Img img,MultipartFile imgfile) throws IllegalStateException, IOException;
	
	void delete(List<Integer> ids);
	
	Img getById(String id);
	
	public void update(Img img,MultipartFile imgfile) throws IllegalStateException, IOException;

}
	