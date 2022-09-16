package com.yang.all_in_one.service.impl;

import com.yang.all_in_one.dao.api.MemorialsDao;
import com.yang.all_in_one.dao.impl.MemorialsDaoImpl;
import com.yang.all_in_one.entity.Memorials;
import com.yang.all_in_one.service.api.MemorialsService;

import java.util.List;

public class MemorialsServiceImpl implements MemorialsService {

    private MemorialsDao memorialsDao = new MemorialsDaoImpl();

    @Override
    public List<Memorials> getAllMemorialsDigestList() {
        return memorialsDao.selectAllMemorialsDigest();
    }

    @Override
    public Memorials getMemorialsDetailByID(String memorialsID) {

        return memorialsDao.selectMemorialsByID(memorialsID);
    }

    @Override
    public void updateMemorialsStatusToRead(String memorialsID) {
        memorialsDao.updateMemorialsStatusToRead(memorialsID);
    }

    @Override
    public void updateMemorialsFeedBack(String memorialsID, String feedbackContent) {
        memorialsDao.updateMemorialsFeedBack(memorialsID, feedbackContent);
    }
}
