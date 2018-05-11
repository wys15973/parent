package com.jk.login.service.impl;

import com.jk.login.dao.LoginDao;
import com.jk.login.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private LoginDao loginDao;

    @Override
    public List getData() {

        return loginDao.getData();
    }

    @Override
    public List findData() {
        return loginDao.findData();
    }
}
