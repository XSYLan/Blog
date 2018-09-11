package com.blog.controller;

import com.blog.model.Article;
import com.blog.model.Notice;
import com.blog.model.Photo;
import com.blog.service.impl.ArticleServiceImpl;
import com.blog.service.impl.NoticeServiceImpl;
import com.blog.service.impl.PhotoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class IndexController {
    @Autowired
    public ArticleServiceImpl articleService;

    @Autowired
    public NoticeServiceImpl noticeService;

    @Autowired
    public PhotoServiceImpl photoService;

    @RequestMapping("/")
    public ModelAndView index(@RequestParam(required=true,defaultValue="1") Integer page, @RequestParam(required=false,defaultValue="5") Integer pageSize){
        ModelAndView modelAndView =new ModelAndView("index");
        PageHelper.startPage(page, pageSize);
        List<Article> articles=articleService.queryAll();
        List<Notice> noticeList = noticeService.queryAllNotice();
        List<Photo> photoList = photoService.selectPhotoList();
        int listSize = noticeList.size();
        Notice notice = noticeList.get(listSize-1);
        int photoListSize = photoList.size();
        Photo photo = photoList.get(0);
        PageInfo<Article> pageInfo=new PageInfo<Article>(articles);
        modelAndView.addObject("pageInfo",pageInfo);
        modelAndView.addObject("articles",articles);
        modelAndView.addObject("notice",notice);
        modelAndView.addObject("photo",photo);
        return modelAndView;
    }

    @RequestMapping("/about")
    public ModelAndView about(){
        ModelAndView modelAndView =new ModelAndView("about");
        return modelAndView;
    }
}
