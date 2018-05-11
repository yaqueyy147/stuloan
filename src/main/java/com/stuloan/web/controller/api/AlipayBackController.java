package com.stuloan.web.controller.api;

import com.stuloan.web.mybatis.domain.Loanorder;
import com.stuloan.web.mybatis.domain.Repayorder;
import com.stuloan.web.mybatis.domain.inte.LoanMapper;
import com.stuloan.web.mybatis.domain.inte.LoanorderMapper;
import com.stuloan.web.mybatis.domain.inte.RepaydetailMapper;
import com.stuloan.web.mybatis.domain.inte.RepayorderMapper;
import com.stuloan.web.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2018/5/11 0011.
 */
@Controller
@RequestMapping(value = "/stuloan/api")
public class AlipayBackController {

    @Autowired
    private LoanMapper loanMapper;
    @Autowired
    private RepaydetailMapper repaydetailMapper;

    @Autowired
    private LoanorderMapper loanorderMapper;
    @Autowired
    private RepayorderMapper repayorderMapper;

    @RequestMapping(value = "/backorderstatus")
    @ResponseBody
    public Object backorderstatus(@RequestParam Map<String,Object> params) throws UnsupportedEncodingException {

        Map<String,Object> result = new HashMap<>();

        String outtradeno = params.get("out_trade_no") + "";
        String tradestatus = params.get("trade_status") + "";

        Map<String,String> pp = new HashMap<>();
        String state = "1";

        pp.put("state",state);
        pp.put("auditmsg","已放款");
        pp.put("ordernonew",outtradeno);
        pp.put("ordernoold",outtradeno);
        loanMapper.updatebyorderno(pp);

        Loanorder loanorder = new Loanorder();
        loanorder.setOrderno(outtradeno);
        loanorder.setOrderstate(state);
        loanorderMapper.updatebyorderno(loanorder);

        pp = new HashMap<>();
        pp.put("isrepay",state);
        pp.put("repaydatereal", CommonUtil.getDateLong());
        pp.put("ordernonew",outtradeno);
        pp.put("ordernoold",outtradeno);
        repaydetailMapper.updatebyorderno(pp);

        Repayorder repayorder = new Repayorder();
        repayorder.setOrderno(outtradeno);
        repayorder.setOrderstate(state);
        repayorderMapper.updatebyorderno(repayorder);

        System.out.println("订单号::" + outtradeno + "--------订单状态::" + tradestatus);

        result.put("code",1);
        result.put("message","操作成功!");
        return result;
    }

}
