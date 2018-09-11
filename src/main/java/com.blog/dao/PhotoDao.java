package com.blog.dao;

import com.blog.model.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */
@Repository
public interface PhotoDao {

    int insert (Photo photo);

    Photo selectPhotoById(Integer id);

    List<Photo> selectPhotoList();

    int deletePhoto(int id);
}
