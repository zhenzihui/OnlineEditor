package com.zhenz.DAO;

import com.zhenz.Entity.Log;

import java.util.List;

public interface LogDao {
    public List<Log> getLogsByUserId(int userId);

    public void newLog(Log log);

    public void flush();

    public void flushByUserId(int userId);

    public void delete(int id);

}
