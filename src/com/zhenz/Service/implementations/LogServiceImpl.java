package com.zhenz.Service.implementations;

import com.zhenz.DAO.LogDao;
import com.zhenz.Entity.Log;
import com.zhenz.Service.LogService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class LogServiceImpl implements LogService {
    @Autowired
    LogDao logDao;

    @Override
    public List<Log> getLogsByUserId(int userId) {
        return logDao.getLogsByUserId(userId);
    }

    @Override
    public void newLog(Log log) {
        logDao.newLog(log);
    }

    @Override
    public void flush() {
        logDao.flush();
    }

    @Override
    public void flushByUserId(int userId) {
        logDao.flushByUserId(userId);
    }

    @Override
    public void delete(int id) {
        logDao.delete(id);
    }
}
