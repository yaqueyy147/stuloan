package com.stuloan.web.task;

import com.stuloan.web.alipay.AlipayTrade_repay;
import com.stuloan.web.alipay.model.result.AlipayF2FQueryResult;
import com.stuloan.web.mybatis.domain.*;
import com.stuloan.web.mybatis.domain.inte.*;
import com.stuloan.web.util.AlipayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by suyx on 2018-05-01.
 * 定时每30秒查询订单状态
 */
@Component
@Lazy(value=false)
public class ToGetOrderStatusTask {
    private static final Logger LOGGER = LoggerFactory.getLogger(ToGetOrderStatusTask.class);

    @Autowired
    private LoanorderMapper loanorderMapper;
    @Autowired
    private RepayorderMapper repayorderMapper;

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;
    @Autowired
    private SysuserMapper sysuserMapper;

    @Scheduled(cron = "0/10 * * * * ?")
    public void dogetorderstatus(){
        System.out.println("********执行了一次dogetorderstatus。。。。。********");
        try {
            Map<String,Object> params = new HashMap<>();
            params.put("limit",10);
            List<Map<String,Object>> list = loanorderMapper.queryorder(params);

            if(list != null && list.size() > 0){
                for(Map<String,Object> map : list){
                    String orderno = map.get("orderno") + "";
                    String tt = map.get("tt") + "";
                    String id = map.get("id") + "";
                    if("0".equals(tt)){//tt为0，表示是贷款表的订单，对贷款表进行相应的操作
                        //查看订单
                        Loanorder loanorder = loanorderMapper.selectByPrimaryKey(id);
                        //根据订单号查询订单状态
//                        AlipayF2FQueryResult result = AlipayTrade_loan.test_trade_query(orderno);result.isTradeSuccess()
                        boolean bb = AlipayUtil.alipaytransferquery(loanorder);
                        //如果订单状态为成功，则遍历是该订单号的贷款数据，修改状态为已放款
                        if(bb){
                            map.put("id","");
                            List<Loan> loanlist = loanMapper.selectByParams(map);
                            if(loanlist != null && loanlist.size() >0){
                                for(Loan loan : loanlist){
                                    loan.setState("1");
                                    loan.setLoanoutdate(new Date());
                                    loan.setAuditmsg("已放款");
                                    loanMapper.updateByPrimaryKeySelective(loan);
                                    Repaydetail repaydetail = new Repaydetail();
                                    repaydetail.setLoanid(loan.getId());
                                    repaydetail.setState("1");
                                    repaydetailMapper.updatestateByLoanIdSelective(repaydetail);
                                }
                            }
                            //修改当条订单为已还款
                            loanorder.setOrderstate("1");
                            loanorderMapper.updatebyorderno(loanorder);
                        }else{//否则根据订单号批量修改贷款状态为待放款状态，需要重新生成订单。
//                            Loan loan = new Loan();
//                            loan.setState("0");
//                            loan.setOrderno("");
//                            loan.setAuditmsg("贷款申请待审核");
                            Map<String,String> loan = new HashMap<>();
                            loan.put("state","5");
                            loan.put("auditmsg","已同意，待放款");
                            loan.put("ordernonew","");
                            loan.put("ordernoold",orderno);
                            loanMapper.updatebyorderno(loan);
                            //删除本条订单
                            loanorderMapper.deleteByPrimaryKey(id);
//                            loanorder.setOrderstate("2");
//                            loanorderMapper.updatebyorderno(loanorder);
                        }
                    }else{//tt不为0，表示是还款表的订单，对还款表进行相应的操作
                        AlipayF2FQueryResult result = AlipayTrade_repay.test_trade_query(orderno);

                        Repayorder repayorder = repayorderMapper.selectByPrimaryKey(id);
//                        boolean bb = AlipayUtil.alipaytransferquery(repayorder);
                        //如果订单状态为成功，则遍历该订单号的还款数据，修改状态为已还款，并修改贷款表的已还款总金额、还款期数和最近还款时间
                        if(result.isTradeSuccess()){
                            map.put("id","");
                            List<Repaydetail> repaydetaillist = repaydetailMapper.selectByParams(map);
                            if(repaydetaillist != null && repaydetaillist.size() >0){
                                for(Repaydetail repaydetail : repaydetaillist){
                                    repaydetail.setIsrepay("1");
                                    repaydetail.setRepaydatereal(new Date());
                                    repaydetailMapper.updateByPrimaryKeySelective(repaydetail);
                                    Loan loan = loanMapper.selectByPrimaryKey(repaydetail.getLoanid());

                                    loan.setRepayyet(loan.getRepayyet() + repaydetail.getRepaymoney());
                                    loan.setStagenumyet(repaydetail.getStagenum());
                                    loan.setUpdatedate(new Date());
                                    loanMapper.updateByPrimaryKeySelective(loan);

                                    Sysuser sysuser = sysuserMapper.selectByPrimaryKey(loan.getUserid());
                                    if((sysuser.getLoanlimit() + repaydetail.getRepaymoney()) >= sysuser.getLoanlimit()){
                                        sysuser.setLoanlimitleft(sysuser.getLoanlimit());
                                    }else{
                                        sysuser.setLoanlimitleft(sysuser.getLoanlimitleft() + repaydetail.getRepaymoney());
                                    }
                                    sysuserMapper.updateByPrimaryKeySelective(sysuser);
                                }
                            }
                            repayorder.setOrderstate("1");
                            repayorderMapper.updatebyorderno(repayorder);
                        }else{//否则，批量修改还款状态为未还款状态，需要重新生产订单
//                            Repaydetail repaydetail = new Repaydetail();
//                            repaydetail.setIsrepay("0");
                            Map<String,String> repaydetail = new HashMap<>();
                            repaydetail.put("isrepay","4");
                            repaydetail.put("ordernonew","");
                            repaydetail.put("ordernoold",orderno);
                            repaydetailMapper.updatebyorderno(repaydetail);
                            //删除本条订单
                            repayorderMapper.deleteByPrimaryKey(id);
//                            repayorder.setOrderstate("2");
//                            repayorderMapper.updatebyorderno(repayorder);
                        }
                    }
                }

            }

        }catch (Exception e){
            e.printStackTrace();
            LOGGER.info(e.getMessage());
        }
    }
}
