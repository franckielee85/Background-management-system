package com.example.demo.service;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author fuyao lee
 * @date 2022/6/7 21:06
 */
public interface UploadService {
    String uploadFileAvatar(@RequestParam MultipartFile file, HttpServletRequest request);
}
