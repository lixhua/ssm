package com.dianke.employee.controller;

import com.dianke.employee.base.domain.Datas;
import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.Attendance;
import com.dianke.employee.service.AttendanceService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {

    @Resource
    private AttendanceService attendanceServiceImpl;

    @Value("${exportPath}")
    private String exportPath;

    @Value("${temp}")
    private String temp;
    //
    @RequestMapping(method= RequestMethod.GET)
    public Object getPageUsers(String aname, Integer normal, @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate, @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate,
                               Integer aleave, Integer absence, Integer limit,Integer offset) {
        List<Attendance> list = attendanceServiceImpl.getPageDatas(aname,normal,beginDate,endDate,aleave,absence,limit,offset);
        long l = attendanceServiceImpl.getTotal(aname,normal,beginDate,endDate,aleave,absence);
        Datas datas = new Datas(l,list);
        return datas;
    }

    @RequestMapping("/export")
    public Object exportExcel(String aname, Integer normal, @DateTimeFormat(pattern="yyyy-MM-dd") Date beginDate,
                              @DateTimeFormat(pattern="yyyy-MM-dd") Date endDate, Integer aleave, Integer absence,HttpServletResponse resp) {
        Message msg = null;
        try {
            String fileName = attendanceServiceImpl.generateExcel(aname, normal, beginDate, endDate, aleave,absence);
            resp.setContentType("application/octet-stream;charset=utf-8");
            resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
            FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));

            msg = new Message(1,true,"出勤信息数据导出成功,请在" + temp + "查找文件: " + fileName);
        } catch (Exception e) {
            msg = new Message(-1,false,"出勤信息数据导出失败，请联系管理员。");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/template")
    public Object template() {
        Message msg = null;
        try {
            String fileName = attendanceServiceImpl.template();
            FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));
            msg = new Message(1,true,"出勤信息模板导出成功,请在" + temp + "查找文件: " + fileName);
        } catch (Exception e) {
            msg = new Message(-1,false,"出勤信息模板导出失败，请联系管理员。");
            e.printStackTrace();
        }
        return msg;
    }
}
