package com.dianke.employee.controller;

import com.dianke.employee.base.domain.Datas;
import com.dianke.employee.base.domain.Message;
import com.dianke.employee.domain.SalaryCard;
import com.dianke.employee.service.SalaryCarService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/salaryCard")
public class SalaryCardController {

    @Resource
    private SalaryCarService salaryCarServiceImpl;

    @Value("${exportPath}")
    private String exportPath;

    @Value("${temp}")
    private String temp;

    @RequestMapping(method= RequestMethod.GET)
    public Object getPageUsers(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus,
                               String sfine, Integer limit, Integer offset) {
        List<SalaryCard> list = salaryCarServiceImpl.getPageDatas(swname,sbasic,beginRealWages,endRealWages,sbonus,sfine,limit,offset);
        long l = salaryCarServiceImpl.getTotal(swname,sbasic,beginRealWages,endRealWages,sbonus,sfine);
        Datas datas = new Datas(l,list);
        return datas;
    }

    @RequestMapping("/export")
    public Object exportExcel(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus,
                              String sfine,HttpServletResponse resp) {
        Message msg = null;
        try {
            String fileName = salaryCarServiceImpl.generateExcel(swname,sbasic,beginRealWages,endRealWages,sbonus,sfine);
            resp.setContentType("application/octet-stream;charset=utf-8");
            resp.setHeader("Content-Disposition", "attachment;filename*=utf-8'zh_cn'" + fileName);
            FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));

            msg = new Message(1,true,"工资卡数据导出成功,请在" + temp + "查找文件: " + fileName);
        } catch (Exception e) {
            msg = new Message(-1,false,"工资卡数据导出失败，请联系管理员。");
            e.printStackTrace();
        }
        return msg;
    }

    @RequestMapping("/template")
    public Object template() {
        Message msg = null;
        try {
            String fileName = salaryCarServiceImpl.template();
            FileCopyUtils.copy(new FileInputStream(exportPath + fileName), new FileOutputStream(temp + fileName));
            msg = new Message(1,true,"工资卡模板导出成功,请在" + temp + "查找文件: " + fileName);
        } catch (Exception e) {
            msg = new Message(-1,false,"工资卡模板导出失败，请联系管理员。");
            e.printStackTrace();
        }
        return msg;
    }
}
