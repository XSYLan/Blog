package com.blog.service;

import com.blog.model.Photo;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */
public interface PhotoService {
    int insert (Photo photo);

    Photo selectPhotoById(Integer id);

    List<Photo> selectPhotoList();

    int deletePhoto(int id);
}
