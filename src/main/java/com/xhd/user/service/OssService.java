package com.xhd.user.service;

import org.springframework.web.multipart.MultipartFile;

public interface OssService {
    String upfile(MultipartFile file);
}
