package com.blog.service.impl;

import com.blog.dao.NoticeDao;
import com.blog.model.Notice;
import com.blog.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xuesiyuan on 2018/4/11.
 */
@Service
public class NoticeServiceImpl implements NoticeService {

    @Autowired
    public NoticeDao noticeDao;


    public Notice selectNoticeById(int id) {
        return noticeDao.selectNoticeById(id);
    }

    public int updateNotice(Notice notice) {
        return  noticeDao.updateNotice(notice);
    }

    /**
     * 修改公告（新插入库一条）
     * @param notice
     * @return
     */
    public int insertNotice(Notice notice){
        return noticeDao.insertNotice(notice);
    }

    /**
     * 查询所有的公告
     * @return
     */
    public List<Notice> queryAllNotice(){
        return noticeDao.queryAllNotice();
    }

    /**
     *根据ID删除公告
     * @param id
     * @return
     */
    public int noticeDeleteById(int id) {
        return noticeDao.noticeDeleteById(id);
    }
}
