package com.blog.service.impl;

import com.blog.dao.PhotoDao;
import com.blog.model.Photo;
import com.blog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */
@Service
public class PhotoServiceImpl implements PhotoService {

    @Autowired
    public PhotoDao photoDao;

    public int insert(Photo photo) {
        return photoDao.insert(photo);
    }

    public Photo selectPhotoById(Integer id) {
        return photoDao.selectPhotoById(id);
    }

    @Override
    public List<Photo> selectPhotoList() {
        return photoDao.selectPhotoList();
    }

    @Override
    public int deletePhoto(int id) {
        return photoDao.deletePhoto(id);
    }
}
