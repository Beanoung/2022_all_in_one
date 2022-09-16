package com.yang.all_in_one.dao.api;

import com.yang.all_in_one.entity.Memorials;

import java.util.List;

public interface MemorialsDao {
    List<Memorials> selectAllMemorialsDigest();

    Memorials selectMemorialsByID(String memorialsID);

    void updateMemorialsStatusToRead(String memorialsID);

    void updateMemorialsFeedBack(String memorialsID, String feedbackContent);
}
