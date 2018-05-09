package com.stuloan.web.util;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayFundTransOrderQueryRequest;
import com.alipay.api.request.AlipayFundTransToaccountTransferRequest;
import com.alipay.api.response.AlipayFundTransOrderQueryResponse;
import com.alipay.api.response.AlipayFundTransToaccountTransferResponse;
import com.stuloan.web.mybatis.domain.Loanorder;
import com.stuloan.web.mybatis.domain.Repayorder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * Created by suyx on 2018/5/9 0009.
 */
public class AlipayUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(AlipayUtil.class);

    private static Properties props;
    private static AlipayClient alipayClient;

    public static void initalipay(String getproperties) throws Exception{
        props = PropertyUtil.getproperties(getproperties);

        alipayClient = new DefaultAlipayClient(props.get("open_api_domain") + "",
                props.get("appid") + "",
                props.get("private_key") + "",
                "json","GBK",
                props.get("alipay_public_key") + "",
                "RSA2");
    }

//    public static boolean alipaytransfer(Object o) throws Exception{
//        initalipay();
//        Order order = (Order) o;
//        AlipayFundTransToaccountTransferRequest alirequest = new AlipayFundTransToaccountTransferRequest();
//        alirequest.setBizContent("{" +
//                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
//                "\"payee_type\":\"" + props.get("aplipay_appname_01") + "\"," +
//                "\"payee_account\":\"" + props.get("aplipay_appname_02") + "\"," +
//                "\"amount\":\"" + CommonUtil.get2DotStrFrmDouble(order.getTotalamount()) + "\"," +
//                "\"payer_show_name\":\"\"," +
//                "\"payee_real_name\":\"\"," +
//                "\"remark\":\"" + order.getOrderdesc() + "\"" +
//                "}");
//        AlipayFundTransToaccountTransferResponse aliresponse = alipayClient.execute(alirequest);
//        return aliresponse.isSuccess();
//    }

    public static boolean alipaytransfer(Loanorder order) throws Exception{
        initalipay("zfbinfo.properties");
        AlipayFundTransToaccountTransferRequest alirequest = new AlipayFundTransToaccountTransferRequest();
        alirequest.setBizContent("{" +
                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" + props.get("aplipay_appname_maijia") + "\"," +
                "\"amount\":\"" + CommonUtil.get2DotStrFrmDouble(order.getTotalamount()) + "\"," +
                "\"payer_show_name\":\"\"," +
                "\"payee_real_name\":\"\"," +
                "\"remark\":\"" + order.getOrderdesc() + "\"" +
                "}");
        AlipayFundTransToaccountTransferResponse aliresponse = alipayClient.execute(alirequest);
        LOGGER.info("返回参数--->" + JSONObject.toJSONString(aliresponse));
        return aliresponse.isSuccess();
    }

    public static boolean alipaytransfer(Repayorder order) throws Exception{
        initalipay("zfbinfo_repay.properties");
        AlipayFundTransToaccountTransferRequest alirequest = new AlipayFundTransToaccountTransferRequest();
        alirequest.setBizContent("{" +
                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
                "\"payee_type\":\"ALIPAY_LOGONID\"," +
                "\"payee_account\":\"" + props.get("aplipay_appname_maijia") + "\"," +
                "\"amount\":\"" + CommonUtil.get2DotStrFrmDouble(order.getTotalamount()) + "\"," +
                "\"payer_show_name\":\"\"," +
                "\"payee_real_name\":\"\"," +
                "\"remark\":\"" + order.getOrderdesc() + "\"" +
                "}");
        AlipayFundTransToaccountTransferResponse aliresponse = alipayClient.execute(alirequest);
        LOGGER.info("返回参数--->" + JSONObject.toJSONString(aliresponse));
        return aliresponse.isSuccess();
    }

//    public static boolean alipaytransferquery(Object o) throws Exception{
//        initalipay();
//        Order order = (Order) o;
//        AlipayFundTransOrderQueryRequest alirequest = new AlipayFundTransOrderQueryRequest();
//        alirequest.setBizContent("{" +
//                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
//                "  }");
//        AlipayFundTransOrderQueryResponse alyresponse = alipayClient.execute(alirequest);
//        return alyresponse.isSuccess();
//    }

    public static boolean alipaytransferquery(Loanorder order) throws Exception{
        initalipay("zfbinfo.properties");
        AlipayFundTransOrderQueryRequest alirequest = new AlipayFundTransOrderQueryRequest();
        alirequest.setBizContent("{" +
                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
                "  }");
        AlipayFundTransOrderQueryResponse alyresponse = alipayClient.execute(alirequest);
        LOGGER.info("返回参数--->" + JSONObject.toJSONString(alyresponse));
        return alyresponse.isSuccess();
    }

    public static boolean alipaytransferquery(Repayorder order) throws Exception{
        initalipay("zfbinfo_repay.properties");
        AlipayFundTransOrderQueryRequest alirequest = new AlipayFundTransOrderQueryRequest();
        alirequest.setBizContent("{" +
                "\"out_biz_no\":\"" + order.getOrderno() + "\"," +
                "  }");
        AlipayFundTransOrderQueryResponse alyresponse = alipayClient.execute(alirequest);
        LOGGER.info("返回参数--->" + JSONObject.toJSONString(alyresponse));
        return alyresponse.isSuccess();
    }

}
