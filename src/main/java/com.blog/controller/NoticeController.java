package com.blog.controller;


import com.blog.model.Notice;
import com.blog.service.impl.NoticeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.List;

@Controller
public class NoticeController {
    @Autowired
    public NoticeServiceImpl noticeService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("/admin/notice/edit")
    public ModelAndView noticeEdit(HttpServletRequest request){
        List<Notice> noticeList=noticeService.queryAllNotice();
        ModelAndView modelAndView=new ModelAndView("/admin/notice_edit");
        modelAndView.addObject("noticeList",noticeList);
        return modelAndView;
    }

    @RequestMapping(value = "/admin/notice/edit/do")
    public ModelAndView noticeEditDo(HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("utf-8");
        Notice notice = new Notice();
        notice.setNotifydesc(request.getParameter("notifydesc"));
        ModelAndView modelAndView=new ModelAndView("/admin/notice_edit");
        if (noticeService.insertNotice(notice) == 1){
            modelAndView.addObject("mark", "公告发布成功！");
        }else {
            modelAndView.addObject("mark", "公告发布失败！");
        }
        List<Notice> noticeList=noticeService.queryAllNotice();
        modelAndView.addObject("noticeList",noticeList);
        return modelAndView;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/notice/delete", method = RequestMethod.POST)
    public Object noticeDelete(HttpServletRequest request){
        int id=Integer.parseInt(request.getParameter("id"));
        int result=noticeService.noticeDeleteById(id);
        HashMap<String, String> res = new HashMap<String, String>();
        if (result==1){
            res.put("stateCode", "1");
        }else {
            res.put("stateCode", "0");
        }
        return res;
        }
    }