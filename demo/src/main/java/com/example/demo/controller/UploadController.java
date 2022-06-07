package com.example.demo.controller;

import com.example.demo.common.Result;
import com.example.demo.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;

/**
 * @author fuyao lee
 * @date 2022/6/7 21:04
 */
@RestController
@RequestMapping("/edu/upload")
@CrossOrigin
public class UploadController {
    @Autowired
    private UploadService uploadService;


    @PostMapping
    public Result uploadFile(MultipartFile file, HttpServletRequest httpServletRequest){

        String url=uploadService.uploadFileAvatar(file,httpServletRequest);
        return Result.ok().data("url",url);
    }
}
