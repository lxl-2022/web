package com.ill.web.controller;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

import com.ill.web.pojo.Img;
import com.ill.web.pojo.PageBean;
import com.ill.web.pojo.Result;
import com.ill.web.service.ImgService;


@Slf4j
@RestController   //会把返回值转换成JSON格式
@RequestMapping("/imgs")
public class ImgController {
		
	//private static Logger log = LoggerFactory.getLogger(ImgController.class);   改成注解了，记录日志	

	@Autowired
	private ImgService imgService;

	//@RequestMapping(value="/imgs",method=RequestMethod.GET)  
	@GetMapping
	public Result search(String uid,String tag,@RequestParam(name = "page",defaultValue="1")Integer page,@RequestParam(defaultValue="10")Integer pagesize) {
		log.info("查询:{},{}",uid,tag);
		PageBean pageBean = imgService.search(uid,tag,page,pagesize);
		return Result.success(pageBean);
	}
	
	
	@DeleteMapping("/{ids}")
	public Result delete(@PathVariable List<Integer> ids) {
		log.info("删除");
		imgService.delete(ids);
		return Result.success();		
	}
	//请求体中的要用 RequestBody
	@PostMapping
	public Result save(@RequestBody Img img,MultipartFile[] imgfiles) throws IllegalStateException, IOException{
		log.info("添加");
		imgService.add(img,imgfiles);
		return Result.success();
	}
	
	
	@GetMapping("/{id}")
	public Result getImg(@PathVariable String id) {
		log.info("查询回显");
		Img img = imgService.getById(id);
		return Result.success(img);
	}
	
	
	@PutMapping
	public Result update(@RequestBody Img img,MultipartFile imgfile) throws IllegalStateException, IOException{
		log.info("更新");		
		imgService.update(img,imgfile);
		return Result.success();
	}
}
