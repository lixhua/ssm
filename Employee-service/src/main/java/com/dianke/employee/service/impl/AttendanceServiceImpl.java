package com.dianke.employee.service.impl;

import com.dianke.employee.dao.AttendanceDao;
import com.dianke.employee.domain.Attendance;
import com.dianke.employee.service.AttendanceService;
import com.dianke.employee.utils.DateUtils;
import com.dianke.employee.utils.ExportExcel;
import com.dianke.employee.utils.ImportExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AttendanceServiceImpl implements AttendanceService {

    @Resource
    private AttendanceDao attendanceDaoImpl;

    @Value("${exportPath}")
    private String exportPath;

    @Override
    public List<Attendance> getPageDatas(String aname, Integer normal, Date beginDate, Date endDate,
                                         Integer aleave, Integer absence, Integer limit,Integer offset) {
        aname = (null != aname && !"".equals(aname)) ? "%" + aname + "%" : aname;
        return attendanceDaoImpl.getPageDatas(aname,normal,beginDate,endDate,aleave,absence,limit,offset);
    }

    @Override
    public long getTotal(String aname, Integer normal, Date beginDate, Date endDate, Integer aleave, Integer absence ){
        aname = (null != aname && !"".equals(aname)) ? "%" + aname + "%" : aname;
        return attendanceDaoImpl.getTotal(aname,normal,beginDate,endDate,aleave,absence);
    }

    @Override
    public String generateExcel(String aname, Integer normal, Date beginDate, Date endDate, Integer aleave, Integer absence) throws Exception {
        aname = (null != aname && !"".equals(aname)) ? "%" + aname + "%" : aname;
        List<Attendance> list = attendanceDaoImpl.generateExcel(aname,normal,beginDate,endDate,aleave,absence);
        String title = "出勤信息";
        String[] rows = {"员工姓名","正常出勤","请假","迟到","早退","缺勤","创建时间"};
        List<Object[]> datas = new ArrayList<>();
        list.forEach(a -> {
            Object[] obj = {
                    null != a.getAname() ? a.getAname() : "",
                    null != a.getNormal() ? a.getNormal() : 0,
                    null != a.getAleave() ? a.getAleave() : 0,
                    null != a.getLate() ? a.getLate() : 0,
                    null != a.getLeave_early() ? a.getLeave_early() : 0,
                    null != a.getAbsence() ? a.getAbsence() : 0,
                    DateUtils.transferDateToStr(a.getCreateTime(), DateUtils.DATE_SMALL_STR),
            };
            datas.add(obj);
        });

        ExportExcel exportExcel = new ExportExcel(title,rows,datas);
        String fileName = System.nanoTime() +  ".xls";
        exportExcel.export(exportPath + fileName);
        return fileName;
    }

    @Override
    public String template() throws Exception {
        Attendance a = new Attendance("张三",22,1,1,1,1);
        String title = "出勤信息";
        String[] rows = {"员工姓名","正常出勤","请假","迟到","早退","缺勤"};
        List<Object[]> datas = new ArrayList<>();
        Object[] obj = {
                null != a.getAname() ? a.getAname() : "",
                null != a.getNormal() ? a.getNormal() : 0,
                null != a.getAleave() ? a.getAleave() : 0,
                null != a.getLate() ? a.getLate() : 0,
                null != a.getLeave_early() ? a.getLeave_early() : 0,
                null != a.getAbsence() ? a.getAbsence() : 0
        };
        datas.add(obj);
        ExportExcel exportExcel = new ExportExcel(title,rows,datas);
        String fileName = "出勤信息模板.xls";
        exportExcel.export(exportPath + fileName);
        return fileName;
    }

    @Override
    public void importAttendance(String importName) throws FileNotFoundException {
        ImportExcel ie = new ImportExcel();
        List<Attendance> lists = new ArrayList<>();
        Map<Integer, String> map = ie.readExcelContent(new FileInputStream(importName));
        System.out.println(importName);
        map.forEach((k,v) -> {
            if(k > 3) {
                Attendance a = new Attendance();
                String[] strings = v.split("\\s+");
                a.setAname(strings[0] != null ? strings[0] : "");
                System.out.println(strings[0]);
                a.setNormal(Integer.parseInt(strings[1] != null ? strings[1] : "0"));
                a.setAleave(Integer.parseInt(strings[2] != null ? strings[2] : "0"));
                a.setLate(Integer.parseInt(strings[3] != null ? strings[3] : "0"));
                a.setLeave_early(Integer.parseInt(strings[4] != null ? strings[4] : "0"));
                a.setAbsence(Integer.parseInt(strings[5] != null ? strings[5] : "0"));
                lists.add(a);
            }
        });
        attendanceDaoImpl.importAttendance(lists);
    }
}
