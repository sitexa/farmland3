package com.sitexa.user.rest.user;

import com.alipay.config.AlipayConfig;
import com.alipay.util.CheckURL;
import com.alipay.util.Payment;
import com.alipay.util.UtilDate;
import com.sitexa.sys.service.RoleService;
import com.sitexa.user.action.MemberCreditAction;
import com.sitexa.user.data.Member;
import com.sitexa.user.service.MemberCreditBean;
import com.sitexa.user.service.MemberCreditService;
import com.sitexa.user.service.MemberService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * User: xnpeng
 * Date: 2009-12-2
 * Time: 10:45:20
 */
public class MemberCreditController extends MemberCreditAction {
    public HttpHeaders index() {
        System.out.println("MemberCreditController.index");
        member = getProfile();
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        mc = MemberCreditService.getMemberCredit(member.getMemberId());
        mclogs = MemberCreditService.searchLog(member, page);

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders show() {
        Member me = getProfile();
        if (me == null) {
            try {
                ServletActionContext.getResponse().sendRedirect(ServletActionContext.getRequest().getContextPath() + "/logon");
                return null;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        member = MemberService.getMember(id);
        if (member == null) return null;
        if (!haveRight()) return null;

        mc = MemberCreditService.getMemberCredit(member.getMemberId());
        mclogs = MemberCreditService.searchLog(member, page);

        return new DefaultHttpHeaders("index").disableCaching();
    }

    public HttpHeaders topup() {
        System.out.println("CreditController.topup");
        if (!haveRight()) return index();
        int pt = Integer.parseInt(point);
        member = getProfile();
        MemberCreditService.topup(member, pt);
        return index();
    }

    public HttpHeaders alipay() {
        HttpServletRequest request = ServletActionContext.getRequest();
        int serviceType = Integer.parseInt(request.getParameter("serviceType").trim());
        member = MemberService.getMember(member.getMemberId());

        if (member == null) return index();
        id = member.getMemberId();

        String alipayUrl = "";
        switch (serviceType) {
            //支付宝担保交易
            case 1:
                alipayUrl = cptbb(request);
                break;
            //支付宝即时到帐
            case 2:
                alipayUrl = cdpbu(request);
                break;
            //银行转帐
            case 3:
                topup(3);
                break;
            //现金充值
            case 4:
                topup(4);
                break;
            default:
                alipayUrl = "";
        }
        try {
            if ("".equals(alipayUrl)) return show();
            ServletActionContext.getResponse().sendRedirect(alipayUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return show();
    }

    private void topup(int type) {
        System.out.println("MemberCreditController.topup");
        String reason = "";
        String sn = new Date().getTime() + "";
        if (type == 3) {
            reason = "银行转帐" + sn;
        } else if (type == 4) {
            reason = "现金充值" + sn;
        }
        String price = ServletActionContext.getRequest().getParameter("goodPrice").trim();
        int p = Integer.parseInt(price);
        int point = p * 10;

        try {
            MemberCreditBean mcb = new MemberCreditBean(member);
            mcb.topup(point, reason);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * cptbb : create_partner_trade_by_buyer
     *
     * @param request
     * @return
     */
    private String cptbb(HttpServletRequest request) {
        String service = "create_direct_pay_by_user";//快速付款交易服务（不可以修改）
        String paygateway = AlipayConfig.paygateway; //支付接口（不可以修改）
        String sign_type = AlipayConfig.sign_type;//文件加密机制（不可以修改）
        String input_charset = AlipayConfig.charSet;  //（不可以修改）
        //partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
        String partner = AlipayConfig.partnerID; //支付宝合作伙伴id (账户内提取)
        String key = AlipayConfig.key; //支付宝安全校验码(账户内提取)
        String seller_email = AlipayConfig.sellerEmail; //卖家支付宝帐户,例如：gwl25@126.com

        String body = "MID" + getProfile().getMemberId(); //商品描述，推荐格式：商品名称（订单编号：订单编号）
        String out_trade_no = getProfile().getMemberId() + UtilDate.getOrderNum();//商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
        String price = request.getParameter("goodPrice").trim(); //订单总价，范围：0.01～100000000.00（小数点后面最多两位）例如：23.80  
        String payment_type = "1";//支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
        String subject = "花木兰银两"; //商品名称
        String show_url = "www.farmlander.com";  //根据集成的网站而定 例如：http://wow.alipay.com

        String return_url = "http://www.farmlander.com/user/member-credit!alipayNotify"; //支付完成后用于本地跳转的网址URL
        String notify_url = "http://www.farmlander.com/user/member-credit!alipayNotify"; //接收支付宝通知的URL(本地测试时，服务器返回无法测试)

        //******物流信息(支付宝目前仅支持三种物流信息)和支付宝通知，一般商城不需要通知，请删除此参数，
        //并且在payment.java里面相应删除参数，同时如果增加物流信息，请在payment.java里面相应增加参数********
        String logistics_type = "EMS";  //物流配送方式：POST(平邮)、EMS(EMS)、EXPRESS(其他快递)
        String logistics_fee = "0.00";  //物流配送费用
        String logistics_payment = "BUYER_PAY";  //物流配送费用付款方式：SELLER_PAY(卖家支付)、BUYER_PAY(买家支付)、BUYER_PAY_AFTER_RECEIVE(货到付款)

        String quantity = "1";//一般情况可以默认为1，具体可以参看开发文档
        String discount = "0"; //折扣 参数范围：-10000000.00<i<10000000.00

        String alipayUrl = Payment.CreateUrl1(paygateway, service, sign_type,
                out_trade_no, input_charset, partner, key, seller_email,
                body, subject, price, quantity, show_url, payment_type,
                discount, logistics_type, logistics_fee, logistics_payment,
                return_url, notify_url);
        return alipayUrl;
    }

    /**
     * cdpbu : create_direct_pay_by_user
     *
     * @param request
     * @return
     */
    private String cdpbu(HttpServletRequest request) {
        String service = "create_direct_pay_by_user";//快速付款交易服务（不可以修改）
        String paygateway = AlipayConfig.paygateway; //支付接口（不可以修改）
        String sign_type = AlipayConfig.sign_type;//文件加密机制（不可以修改）
        String input_charset = AlipayConfig.charSet;  //（不可以修改）
        //partner和key提取方法：登陆签约支付宝账户--->点击“商家服务”就可以看到
        String partner = AlipayConfig.partnerID; //支付宝合作伙伴id (账户内提取)
        String key = AlipayConfig.key; //支付宝安全校验码(账户内提取)
        String seller_email = AlipayConfig.sellerEmail; //卖家支付宝帐户,例如：gwl25@126.com

        String body = "MID" + getProfile().getMemberId(); //商品描述，推荐格式：商品名称（订单编号：订单编号）
        String out_trade_no = getProfile().getMemberId() + new UtilDate().getOrderNum();//商户网站订单（也就是外部订单号，是通过客户网站传给支付宝，不可以重复）
        String total_fee = request.getParameter("goodPrice").trim(); //订单总价
        String payment_type = "1";//支付宝类型.1代表商品购买（目前填写1即可，不可以修改）
        String subject = "花木兰银两"; //商品名称
        String show_url = "www.farmlander.com";  //根据集成的网站而定 例如：http://wow.alipay.com

        String return_url = "http://www.farmlander.com/user/member-credit!cdpbu_return"; //支付完成后用于本地跳转的网址URL
        String notify_url = "http://www.farmlander.com/user/member-credit!cdpbu_notify"; //接收支付宝通知的URL(本地测试时，服务器返回无法测试)
        //注意以上两个地址 要用 http://格式的完整路径
        /*以下两个参数paymethod和defaultbank可以选择不使用，如果不使用需要注销，并在Payment类的方法中也要注销*/
        String paymethod = "directPay";//赋值:bankPay(网银);cartoon(卡通); directPay(余额)
        String defaultbank = "CMB";

        String alipayUrl = Payment.CreateUrl(paygateway, service, sign_type,
                out_trade_no, input_charset, partner, key, show_url, body,
                total_fee, payment_type, seller_email, subject, notify_url,
                return_url, paymethod, defaultbank);
        return alipayUrl;
    }

    private boolean testSign(HttpServletRequest request, String encoding) {
        String partner = AlipayConfig.partnerID; //partner合作伙伴id（必须填写）
        String key = AlipayConfig.key; //partner 的对应交易安全校验码（必须填写）
        //**********************************************************************************
        //如果您服务器不支持https交互，可以使用http的验证查询地址
        //***注意下面的注释，如果在测试的时候导致response等于空值的情况，请将下面一个注释，打开上面一个验证连接
        //String alipayNotifyURL = "https://www.alipay.com/cooperate/gateway.do?service=notify_verify"
        String alipayNotifyURL = "http://notify.alipay.com/trade/notify_query.do?"
                + "partner="
                + partner
                + "&notify_id="
                + request.getParameter("notify_id");
        //**********************************************************************************
        String sign = request.getParameter("sign");
        //获取支付宝ATN返回结果，true是正确的订单信息，false 是无效的
        String responseTxt = CheckURL.check(alipayNotifyURL);

        Map params = new HashMap();
        //获得POST 过来参数设置到新的params中
        Map requestParams = request.getParameterMap();
        try {
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext();) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                if (!encoding.equals("UTF-8")) {
                    valueStr = new String(valueStr.getBytes(encoding), "UTF-8");
                }
                params.put(name, valueStr);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        System.out.print(request.getParameter("out_trade_no") + "-----");
        String mysign = com.alipay.util.SignatureHelper_return.sign(params, key);

        return mysign.equals(request.getParameter("sign")) && responseTxt.equals("true");
    }

    public HttpHeaders cdpbu_return() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String sbody = request.getParameter("body");
        int pos = sbody.indexOf("MID");
        if (pos >= 0) {
            String mid = sbody.substring(pos + 3);
            if (mid != null && !"".equals(mid))
                member = MemberService.getMember(mid);
        }
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (testSign(request, "ISO-8859-1")) {
            String get_total_fee = request.getParameter("total_fee");
            String get_order = request.getParameter("out_trade_no");
            double point = Double.parseDouble(get_total_fee) * 10;
            MemberCreditBean mb = new MemberCreditBean(member);
            mb.topup((int) point, get_order);
        }
        try {
            ServletActionContext.getResponse().sendRedirect("member-credit");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void cdpbu_notify() {
        HttpServletRequest request = ServletActionContext.getRequest();
        String sbody = request.getParameter("body");
        int pos = sbody.indexOf("MID");
        if (pos >= 0) {
            String mid = sbody.substring(pos + 3);
            if (mid != null && !"".equals(mid))
                member = MemberService.getMember(mid);
        }
        if (member == null) {
            try {
                ServletActionContext.getResponse().sendRedirect("/logon");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (testSign(request, "UTF-8")) {
            String get_total_fee = request.getParameter("total_fee");
            String get_order = request.getParameter("out_trade_no");
            double point = Double.parseDouble(get_total_fee) * 10;
            MemberCreditBean mb = new MemberCreditBean(member);
            mb.topup((int) point, get_order);
            //若没有这个success的输出，那么支付宝的服务器会在交易状态改变时起的24小时内返回支付订单信息，
            //返回的时间频率会逐渐减弱，（1分钟、3 分钟、5 分钟、10分钟、15。。。。。。。。。。）返回大约有20几次。
            try {
                ServletActionContext.getResponse().getWriter().print("success");
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("success");
        }
    }

    private boolean haveRight() {
        if (getProfile().getMemberId().equals(member.getMemberId())) return true;
        return getProfile() != null && RoleService.getRoleInUser(getMe(), RoleService.getRole("4")) != null;
    }

}
