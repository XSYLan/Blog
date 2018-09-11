package com.blog.service;

import com.blog.model.Notice;

import java.util.List;

/**
 * Created by xuesiyuan on 2018/4/11.
 */
public interface NoticeService {

    Notice selectNoticeById(int id);

    int updateNotice(Notice notice);

    /**
     * 修改公告（新插入库一条）
     * @param notice
     * @return
     */
    int insertNotice(Notice notice);

    /**
     * 查询所有的公告
     * @return
     */
    List<Notice> queryAllNotice();

    /**
     * 根据ID删除公告
     * @param id
     * @return
     */
    int noticeDeleteById(int id);

}
