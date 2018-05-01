package com.stuloan.web.task;

import com.stuloan.web.mybatis.domain.Repaydetail;
import com.stuloan.web.mybatis.domain.inte.LoanMapper;
import com.stuloan.web.mybatis.domain.inte.LoanorderMapper;
import com.stuloan.web.mybatis.domain.inte.RepaydetailMapper;
import com.stuloan.web.mybatis.domain.inte.RepayorderMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * Created by suyx on 2018-05-01.
 * 定时每30秒查询订单状态
 */
@Component
@Lazy(value=false)
public class CheckOverdueRepayTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(CheckOverdueRepayTask.class);

    @Autowired
    private LoanorderMapper loanorderMapper;
    @Autowired
    private RepayorderMapper repayorderMapper;

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;

    @Scheduled(cron = "0 0 6 * * ?")
    public void docheckoverdue(){
        System.out.println("********执行了一次docheckoverdue。。。。。********");
        try {
//            List<Repaydetail> repaydetailList = repaydetailMapper.queryoverdue(new Date());

            repaydetailMapper.updateoverdue(new Date());
        }catch (Exception e){
            LOGGER.info(e.getMessage());
        }
    }
}
