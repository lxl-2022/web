package com.ill.web.service.imp;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.ill.web.Mapper.UserMapper;
import com.ill.web.pojo.Img;
import com.ill.web.pojo.Imgfile;
import com.ill.web.pojo.PageBean;
import com.ill.web.service.ImgService;

@Service
public class ImgServiceImpl implements ImgService {

	@Autowired
	private UserMapper usermapper;
	
	@Override
	public PageBean search(String uid,String tag,Integer page,Integer pagesize) {
		Long count = usermapper.count(uid,tag);
		System.out.println(count);
		System.out.println(uid);
		System.out.println(page);
		System.out.println(pagesize);
		Integer start =(page-1)*pagesize;
		List<Img> imglist = usermapper.search(uid,tag,start,pagesize);

		Iterator iterator=imglist.iterator();

		while(iterator.hasNext()) {
			Img img_data= (Img)iterator.next();
			img_data.listtag();
		}

		PageBean pageBean = new PageBean(count,imglist);
		return pageBean;
	}
	
	@Override
	public void add(Img img,MultipartFile imgfile) throws IllegalStateException, IOException {
		img.setCreateTime(LocalDateTime.now());
		img.setUpdateTime(LocalDateTime.now());
		String originalFilename = imgfile.getOriginalFilename();
		int index = originalFilename.lastIndexOf(",");
		String uuid = UUID.randomUUID().toString();
		img.setUid(uuid);
		String newFilename = uuid + originalFilename.substring(index);
		imgfile.transferTo(new File("H:\\project\\data\\"+newFilename));
		usermapper.insert(img);

	}

	
	@Override
	public void delete(List<Integer> ids) {
		List<Imgfile> imgs= usermapper.deleteSearch(ids);
		for(Imgfile img : imgs) {
			File imgfile = new File(img.getUid()+"."+img.getFormat());
			imgfile.delete();
		}	
		usermapper.delete(ids);
	}
	
	
	@Override
	public Img getById(String id) {
		return usermapper.getById(id);
	}
	
	
	@Override
	public void update(Img img,MultipartFile imgfile) throws IllegalStateException, IOException{
		img.setUpdateTime(LocalDateTime.now());
		if(imgfile != null){
			
			File imgfile1 = new File(img.getUid()+"."+img.getFormat());
			imgfile1.delete();	
			
			String originalFilename = imgfile.getOriginalFilename();
			int index = originalFilename.lastIndexOf(",");
			String uuid = UUID.randomUUID().toString();
			img.setUid(uuid);
			img.setFormat(originalFilename.substring(index+1));
			String newFilename = uuid + originalFilename.substring(index);
			imgfile.transferTo(new File("H:\\project\\data\\"+newFilename));
			}
		usermapper.update(img);
		
	}

}
