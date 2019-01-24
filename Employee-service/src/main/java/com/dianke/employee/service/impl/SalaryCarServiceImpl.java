package com.dianke.employee.service.impl;

import com.dianke.employee.dao.SalaryCardDao;
import com.dianke.employee.domain.SalaryCard;
import com.dianke.employee.service.SalaryCarService;
import com.dianke.employee.utils.DateUtils;
import com.dianke.employee.utils.ExportExcel;
import com.dianke.employee.utils.ImportExcel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class SalaryCarServiceImpl implements SalaryCarService {

    @Resource
    private SalaryCardDao salaryCardDaoImpl;

    @Value("${exportPath}")
    private String exportPath;

    private String getSql(String sbasic, String sbonus,String sfine){
        StringBuffer orderBySql = new StringBuffer();
        if(sbasic != null && !"-1".equals(sbasic)) {
            orderBySql.append("basic " + sbasic);
            if(sbonus != null && !"-1".equals(sbonus)){
                orderBySql.append(" ,bonus " + sbonus);
                if(sfine != null && !"-1".equals(sfine)){
                    orderBySql.append(" ,fine " + sfine);
                }
            }
        }else {
            if(sbonus != null && !"-1".equals(sbonus)){
                orderBySql.append("bonus " + sbonus);
                if(sfine != null && !"-1".equals(sfine)){
                    orderBySql.append(" ,fine " + sfine);
                }
            }else{
                if(sfine != null && !"-1".equals(sfine)){
                    orderBySql.append("fine " + sfine);
                }
            }
        }
       return orderBySql.toString();
    }

    @Override
    public List<SalaryCard> getPageDatas(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus,
                                         String sfine, Integer limit, Integer offset) {
        swname = (null != swname && !"".equals(swname)) ? "%" + swname + "%" : swname;
        String orderBySql = getSql(sbasic,sbonus,sfine);
        return salaryCardDaoImpl.getPageDatas(swname, beginRealWages, endRealWages,orderBySql, limit, offset);
    }

    @Override
    public long getTotal(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus, String sfine) {
        swname = (null != swname && !"".equals(swname)) ? "%" + swname + "%" : swname;
        String orderBySql = getSql(sbasic,sbonus,sfine);
        return salaryCardDaoImpl.getTotal(swname,beginRealWages, endRealWages,orderBySql);
    }

    @Override
    public String generateExcel(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus, String sfine) throws Exception {
        swname = (null != swname && !"".equals(swname)) ? "%" + swname + "%" : swname;
        String orderBySql = getSql(sbasic,sbonus,sfine);
        List<SalaryCard> list = salaryCardDaoImpl.generateExcel(swname, beginRealWages, endRealWages, orderBySql );
        String title = "工资卡";
        String[] rows = {"姓名","基础工资","奖金","罚款","应发工资","实发工资","发款日期"};
        List<Object[]> datas = new ArrayList<>();
        list.forEach(s -> {
            Object[] obj = {
                    null != s.getWname() ? s.getWname() : "",
                    null != s.getBasic() ? s.getBasic() : 0.0,
                    null != s.getBonus() ? s.getBonus() : 0.0,
                    null != s.getFine() ? s.getFine() : 0.0,
                    null != s.getReal_wages() ? s.getReal_wages() : 0.0,
                    null != s.getWage_payable() ? s.getWage_payable() : 0.0,
                    DateUtils.transferDateToStr(s.getCeateTime(), DateUtils.DATE_SMALL_STR)
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
        SalaryCard sc = new SalaryCard("张三",new BigDecimal(1230.00),new BigDecimal(230.00),new BigDecimal(12.00),new BigDecimal(1450.00),new BigDecimal(1500.00));
        String title = "工资卡";
        String[] rows = {"员工姓名","基本工资","奖金","罚款","应发工资","实发工资"};
        List<Object[]> datas = new ArrayList<>();
        Object[] obj = {
                null != sc.getWname() ? sc.getWname() : "",
                null != sc.getBasic() ? sc.getBasic() : 0.0,
                null != sc.getBonus() ? sc.getBonus() : 0.0,
                null != sc.getFine() ? sc.getFine() : 0.0,
                null != sc.getReal_wages() ? sc.getReal_wages() : 0.0,
                null != sc.getWage_payable() ? sc.getWage_payable() : 0.0
        };
        datas.add(obj);
        ExportExcel exportExcel = new ExportExcel(title,rows,datas);
        String fileName = "工资卡模板.xls";
        exportExcel.export(exportPath + fileName);
        return fileName;
    }

    @Override
    public void importSalaryCard(String importName) throws FileNotFoundException {
        ImportExcel ie = new ImportExcel();
        List<SalaryCard> lists = new ArrayList<>();
        Map<Integer, String> map = ie.readExcelContent(new FileInputStream(importName));
        System.out.println(importName);
        map.forEach((k,v) -> {
            if(k > 3) {
                SalaryCard s = new SalaryCard();
                String[] strings = v.split("\\s+");
                s.setWname(strings[0] != null ? strings[0] : "");
                s.setBasic(new BigDecimal(strings[1] != null ? strings[1] : "0.00"));
                s.setBonus(new BigDecimal(strings[2] != null ? strings[2] : "0.00"));
                s.setFine(new BigDecimal(strings[3] != null ? strings[3] : "0.00"));
                s.setReal_wages(new BigDecimal(strings[4] != null ? strings[4] : "0.00"));
                s.setWage_payable(new BigDecimal(strings[5] != null ? strings[5] : "0.00"));
                lists.add(s);
            }
        });
        salaryCardDaoImpl.importSalaryCard(lists);
    }
}
