package com.dianke.employee.service;

import com.dianke.employee.domain.Attendance;

import java.io.FileNotFoundException;
import java.util.Date;
import java.util.List;

public interface AttendanceService {

    public List<Attendance> getPageDatas(String aname, Integer normal, Date beginDate, Date endDate,
                                         Integer aleave, Integer absence, Integer limit,Integer offset);

    public long getTotal(String aname, Integer normal, Date beginDate, Date endDate, Integer aleave, Integer absence);

    public String generateExcel(String aname, Integer normal, Date beginDate, Date endDate, Integer aleave, Integer absence) throws Exception;

    public void importAttendance(String importName) throws FileNotFoundException;

    public String template() throws Exception;
}
