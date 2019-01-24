package com.dianke.employee.service;

import com.dianke.employee.domain.SalaryCard;

import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;

public interface SalaryCarService {

    public List<SalaryCard> getPageDatas(String swname, String sbasic, BigDecimal beginRealWages, BigDecimal endRealWages, String sbonus,
                                         String sfine, Integer limit, Integer offset);

    public long getTotal(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus, String sfine);

    public String generateExcel(String swname, String sbasic,BigDecimal beginRealWages, BigDecimal endRealWages,String sbonus, String sfine) throws Exception;

    public void importSalaryCard(String importName) throws FileNotFoundException;

    public String template() throws Exception;
}
