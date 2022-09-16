package com.yang.all_in_one.service.api;

import com.yang.all_in_one.entity.Memorials;

import java.util.List;

public interface MemorialsService {
    List<Memorials> getAllMemorialsDigestList();

    Memorials getMemorialsDetailByID(String memorialsID);

    void updateMemorialsStatusToRead(String memorialsID);

    void updateMemorialsFeedBack(String memorialsID, String feedbackContent);
}
