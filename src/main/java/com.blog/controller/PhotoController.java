package com.blog.controller;

import com.blog.model.AdminLoginLog;
import com.blog.model.Notice;
import com.blog.model.Photo;
import com.blog.service.impl.PhotoServiceImpl;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2018/8/3.
 */
@Controller
public class PhotoController {
    @Autowired
    private PhotoServiceImpl photoService;

    @Autowired
    private HttpServletRequest request;

    private String photoPath = "D:/BLOG/src/main/webapp/static/images/";

    @RequestMapping("/admin/advertisement/edit/doooo")
    public ModelAndView fileUploadByByte(@RequestParam("file") MultipartFile file) throws IOException {
        ModelAndView mav = new ModelAndView("/admin/advertisement_edit");
        Photo photo = new Photo();
        if(file.getSize()==0){
            mav.addObject("mark","未上传图片。");
        } else {
            byte[] photoFile = file.getBytes();
            photo.setPhotoName(file.getOriginalFilename());
            photo.setPhotoFileByte(String.valueOf(photoFile));
            photo.setPhotoPath(photoPath);
            int a = photoService.insert(photo);
            if (a == 1) {
                mav.addObject("mark", "广告更换成功。");
            }else{
                mav.addObject("mark", "广告更换失败哦。");
            }
        }
//            // 判断文件是否为空
//            File ggfile = new File("D:/BLOG/src/main/webapp/static/images/gg.png");
//            if (ggfile.exists()) {
//                ggfile.delete();
//            }
//            if (!file.isEmpty()) {
//                try {
//                    // 文件保存路径
//                    String filePath =  "D:/BLOG/src/main/webapp/static/images/" + file.getOriginalFilename();
//                    // 转存文件
//                    file.transferTo(new File(filePath));
//                    new File(filePath).renameTo(new File("D:/BLOG/src/main/webapp/static/images/gg.png"));
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }
//            }

        return mav;
    }

    @RequestMapping("/admin/advertisement/edit")
    public ModelAndView advertisementEdit(HttpServletRequest request){
        ModelAndView modelAndView=new ModelAndView("/admin/advertisement_edit");
        List<Photo> photoList = photoService.selectPhotoList();
        modelAndView.addObject("photoList", photoList);
        modelAndView.addObject("notice","");
        return modelAndView;
    }

    @RequestMapping(value = "/admin/advertisement/edit/do")
    public ModelAndView fileUpload(@RequestParam("file") MultipartFile file) {
        ModelAndView mav = new ModelAndView("/admin/advertisement_edit");
        Photo photo = new Photo();
        if(file.getSize()==0){
            // 判断文件是否为空
            mav.addObject("mark","未上传图片。");
        } else{
            File ggfile = new File(String.format(photoPath + "%s" , file.getOriginalFilename()));
            if (!file.isEmpty()) {
                try {
                    // 文件保存路径
                    String filePath =  photoPath + file.getOriginalFilename();
                    // 转存文件
                    file.transferTo(new File(filePath));
                    //new File(filePath).renameTo(new File("D:/BLOG/src/main/webapp/static/images/gg.png"));
                    byte[] photoFile = file.getBytes();
                    photo.setPhotoName(file.getOriginalFilename());
                    photo.setPhotoFileByte(String.valueOf(photoFile));
                    photo.setPhotoPath(photoPath);
                    int a = photoService.insert(photo);
                    if (a == 1) {
                        mav.addObject("mark", "广告更换成功。");
                    }else{
                        mav.addObject("mark", "广告更换失败哦。");
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        List<Photo> photoList = photoService.selectPhotoList();
        mav.addObject("photoList", photoList);
        return mav;
        // 重定向
//        return "redirect:/admin/advertisement/list";
    }

//    @RequestMapping("/admin/advertisement/edit/doooo/download")
//    public ModelAndView download(){
//        ModelAndView mav = new ModelAndView("/admin/advertisement_edit");
//        Photo photo = new Photo();
//        photo = photoService.selectPhotoById(5);
//        byte[] fileByte = photo.getPhotoFileByte().getBytes();
//        String Path = photo.getPhotoPath();
//        String Name = photo.getPhotoName();
//        String PathName = String.format(Path + "/%s" ,Name);
//        mav.addObject("photo", photo);
//        mav.addObject("PathName", PathName);
//        mav.addObject("Name", Name);
//        return mav;
//
//    }

    @ResponseBody
    @RequestMapping(value = "/admin/advertisement/delete", method = RequestMethod.POST)
    public Object loginCheck(HttpServletRequest request ) {
        int id=Integer.parseInt(request.getParameter("id"));
        HashMap<String, String> res = new HashMap<String, String>();
        Photo photo = photoService.selectPhotoById(id);
        if(photo==null){
            //没有此ID对应的数据
            res.put("stateCode", "0");
        }else {
            //执行删除
            int a = photoService.deletePhoto(id);
            File file = new File(String.format(photoPath + "%s" , photo.getPhotoName()));
            file.delete();
            res.put("stateCode", "1");
        }
        return res;
    }

    @ResponseBody
    @RequestMapping(value = "/admin/advertisement/use", method = RequestMethod.POST)
    public Object useit(HttpServletRequest request ) {
        int id=Integer.parseInt(request.getParameter("id"));
        HashMap<String, String> res = new HashMap<String, String>();
        Photo photo = photoService.selectPhotoById(id);
        if(photo==null){
            //没有此ID对应的数据
            res.put("stateCode", "0");
        }else {
            //执行删除
            Photo photo2 = new Photo();
            photo2.setPhotoName(photo.getPhotoName());
            photo2.setPhotoFileByte(photo.getPhotoFileByte());
            photo2.setPhotoPath(photo.getPhotoPath());
            int b = photoService.insert(photo2);
            int a = photoService.deletePhoto(id);
            res.put("stateCode", "1");
        }
        return res;
    }




}
