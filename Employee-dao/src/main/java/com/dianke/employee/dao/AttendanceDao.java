package com.dianke.employee.dao;

import com.dianke.employee.domain.Attendance;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface AttendanceDao {

    public List<Attendance> getPageDatas(@Param("aname") String aname,@Param("normal") Integer normal,
                                         @Param("beginDate") Date beginDate,@Param("endDate") Date endDate,
                                         @Param("aleave") Integer aleave,@Param("absence") Integer absence,
                                         @Param("limit") Integer limit,@Param("beginIndex") Integer beginIndex);

    public long getTotal(@Param("aname") String aname,@Param("normal") Integer normal,
                         @Param("beginDate") Date beginDate,@Param("endDate") Date endDate,
                         @Param("aleave") Integer aleave,@Param("absence") Integer absence);

    public List<Attendance> generateExcel(@Param("aname") String aname,@Param("normal") Integer normal,
                                          @Param("beginDate") Date beginDate,@Param("endDate") Date endDate,
                                          @Param("aleave") Integer aleave,@Param("absence") Integer absence);

    public void importAttendance(List<Attendance> lists);
}
