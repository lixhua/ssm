package com.dianke.employee.dao;

import com.dianke.employee.domain.SalaryCard;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface SalaryCardDao {

    public List<SalaryCard> getPageDatas(@Param("swname") String swname,@Param("beginRealWages") BigDecimal beginRealWages, @Param("endRealWages") BigDecimal endRealWages ,@Param("orderBySql")String orderBySql,@Param("limit") Integer limit,@Param("beginIndex") Integer beginIndex);

    public long getTotal(@Param("swname") String swname,@Param("beginRealWages") BigDecimal beginRealWages, @Param("endRealWages") BigDecimal endRealWages,@Param("orderBySql")String orderBySql);

    public List<SalaryCard> generateExcel(@Param("swname") String swname, @Param("beginRealWages") BigDecimal beginRealWages,
                                          @Param("endRealWages") BigDecimal endRealWages,@Param("orderBySql") String orderBySql);

    public void importSalaryCard(List<SalaryCard> lists);
}
