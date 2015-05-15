<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/help/css/help.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <style type="text/css">
        .help ul li {
            list-style: inside;
            list-style-type: square;
            padding-left: 10px;
            line-height: 24px;
        }

        p {
            text-indent: 20px;
            line-height: 24px;
        }
    </style>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="centerbar">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        关于花木兰ICSA农场
                    </div>
                </div>
                <div class="help">
                    <p>
                        <b>花木兰ICSA农场</b>是在CSA农场模式下创建的开心农场。

                    <p>
                        <b>社区支持农业(CSA,Community Supported Agriculture)</b>是一种粮食生产的社会经济模式。CSA社区由城市居民和农村农民组成，社区的每个人对农场运作作出承诺，让农场可以在法律上和精神上，成为该社区的农场，让农民与消费者互相支持以及承担粮食生产的风险和分享利益。这是一种城乡社区相互支持，发展本地生产、本地消费的小区域经济合作方式。在这种合作的基础上，CSA一方面看重在保护生态及资源下共同承担、相互分享的社区关系，看重社区中情感及文化的传递，另一方面则往往会推行有机生产、健康生活及包括身、心在内的全面的健康观念。所以它也不单纯是有机生活或环保，亦不单是消费者运动，背后更多的体现我们怎样看待这个世界，而这看法也反映在社区发展工作中。CSA起源于20世纪70年代的德国、瑞士和日本。1984年，CSA理念传入北美之后,如雨后春笋般在北美大地上建立起来了，分布在东北部、太平洋沿岸、中北部以及加拿大。根据美国农业部的统计，到2007年，北美地区共有超过13000家CSA农场，12549家在美国。
                        花木兰ICSA农场是基于互联网的社区支持农业（ICSA），是社区支持农业（CSA）在互联网时代的发展。ICSA在CSA的基础上提供两大技术手段，通过互联网缩短城市与农村的距离，通过互联网建立和维护社区关系。

                    <p>
                        <b>花木兰ICSA农场具有如下特点：</b>
                    <ul>
                        <li>城市消费者与农村生产者组成基于互联网的社区；</li>
                        <li>城市居民在农村租用土地，满足其一年的消费需求；</li>
                        <li>城市居民亲自耕种或者付费委托农民耕种；</li>
                        <li>产出的粮食归城市居民所有；</li>
                        <li>通过互联网进行操作与互动；</li>
                        <li>提倡有机种植、健康生产、保护环境、食品安全；</li>
                        <li>提倡劳动锻炼、教育分享；</li>
                        <li>提倡快乐与创造；</li>
                    </ul>
                    <p>
                        <b>花木兰ICSA农场的操作规程</b>
                    <ul>
                        <li>1，客户租用土地，命名农庄名称，建立农庄台帐；</li>
                        <li>2，自主种植。自己购买种子种苗、农资材料，自己操作。记录操作日志。</li>
                        <li>3，委托农场操作。
                            <ul>
                                <li>（1）当面委托；</li>
                                <li>（2）电话委托；</li>
                                <li>（3）网上委托；</li>
                                <li>（4）记录委托及操作日志。</li>
                            </ul>
                        </li>
                        <li>4，代工及材料费用。委托农场操作、购买生产物资，按成本收取费用。</li>
                    </ul>
                    <p>
                        <b>花木兰ICSA农场租地种菜问答</b>
                    <ul>
                        <li><b>问：怎么样租地？</b><br>
                            答：你可以在网上租地，也可以去农场现场租地。网上租地，浏览“CSA农场”，如“雷锋农场”，点击
                            “立即购买”，填写农场名称、租种面积、租种模式，网页会自动计算出所需租金（按花币计算），
                            输入登录密码，点击“购买”按钮提交。现场租地，选中喜欢的地块，测量面积，命名并挂牌农庄，缴纳
                            租金。
                        </li>
                        <li><b>问：租多大的面积合适？</b><br>
                            答：根据用户的经验，一户人租100平方米的地较合适，不但满足一家四口人一年的蔬菜用量，
                            还可以在丰收的季节里送给亲友、同事。如果面积太大，则种不好；面积太小，量不够，不但不够吃，
                            往返汽油费用不合算。
                        </li>
                        <li><b>问：两种模式有什么区别？</b><br>
                            答：不管两种模式还是三种模式，农场都有简单说明。比如说“1元/平方模式”，是只缴纳租地费用，种菜
                            和管理的一切费用，都由用户负担，包括购买种子/种苗，购买肥料、溥膜等生产物资的费用，用户需要
                            亲自下地劳动，浇肥、浇水、除草、除虫等。如果需要农场服务人员代工，则需要支付服务费用。“2元/平方模式”
                            是全委托模式，客户风趣地称为“懒人模式”，用户支付全年的农事服务费用后，只需“点菜”，安排好一年四季春夏秋冬
                            的蔬菜品种（必须是当地当季蔬菜品种，以及合理的种植面积），农场服务人员按照有机环保的种植原则将打理好
                            蔬菜的种植和管理，客户只需要按时到农庄去摘菜。
                        </li>
                        <li><b>问：农场提供哪些服务？</b><br>
                            答：农场免费提供农具（锄头、刀、剪、桶），用户只需到工具房领用，用完送回原处。农场还提供防火防盗服务。
                            农场组织农家肥、竹条、竹片等材料，用来搭棚架、围篱笆，这些材料需要收取工本费。全包模式之外，如果需要
                            农场服务人员提供服务，则需要支付人工费。另外，农场坚持环保理念，不提供塑料袋，希望客户自备环保袋。
                        </li>
                        <li><b>问：农场提供送菜服务么？</b><br>
                            答：农场暂不提供配送服务。根据CSA农场精神，客户应当自发的组成社区，互帮互助。大家共同建立起
                            “社区生产队，开心花木兰”。农场将来会在市区帮助建立几个合理的送菜点，用户自行去送菜点提菜。
                        </li>
                        <li><b>问：什么是花木兰币（花币）？</b><br>
                            答：花木兰币（花币）是花木兰ICSA网站计费系统，用以结算农场发生的材料及人工费用。每个注册用户都有一个
                            农庄银号，用户可以通过网银、现金购买花木兰币。1元人民币=10花币。
                        </li>
                        <li><b>问：如何了解蔬菜的生长状态？</b><br>
                            答：农场管理人员每周会在网上反映蔬菜的生长状况，还有现场图片发布在各个农场。用户也可以在网站上询问，
                            农场管理人员会及时回答用户的问题的。
                        </li>
                    </ul>
                </div>
            </div>
        </div>
        <div id="rightbar">
            <div class="box">
                <div class="t">
                    <div class="t-t">
                        花木兰ICSA农场建设者
                    </div>
                </div>
                <div class="content">
                    <img src="<%=request.getContextPath()%>/help/img/pxn.jpg" width="100" height="110" alt="" style="float:left;padding:4px"/>
                    彭大海，资深IT人士，十多年IT从业经历，在软件开发管理方面有丰富的经验。
                    曾在美国、加拿大、新加城、马来西亚等国家工作和学习过。在项目开发管理、系统分析、架构设计领域有深厚的功力。
                    <br><b>软件开发经验</b><br>
                    曾在物流领域内为国际著名的船公司、贷运公司开发过集装箱管理系统、贷代管理平台；为国内某著名企业开发过供应链管理系统，
                    协同生产制造平台；为国外著名保险公司开发过保险公司操作系统；为某省环保厅开发过全省环保监控平台。
                    <br><b>ICSA理论及实践</b><br>
                    提出互联网社区支持农业(ICSA)理念，把国外的CSA理念与国内实际相结合，通过互联网建立和维持社区，对农业进行支持。
                    进行农业、农村和农民工作的深度探索。
                    <br><b>QQ：460461</b>，联系请注明原因。<br>
                </div>
                <div class="clear"></div>
                <hr/>
                <div class="content">
                    <img src="<%=request.getContextPath()%>/help/img/qiuli.jpg" width="100" height="110" alt="" style="float:left;padding:4px"/>
                    花木丽，资深公益人士，十多年致力于用自己的知识与热情服务社会，在环境保护、高等教育、社区支持农业等方面有丰富的经验。
                    曾留学荷兰，游历欧洲各国，对于东西文化交流，以及互联网支持的农业深有体会。
                    <br><b>干过的几件事情</b><br>
                    多次赴可可西里为保护藏羚羊做志愿服务，上书国家环保局为青藏铁路建设中保护藏羚羊的有关设施提出合理建议。
	                在512汶川地震后17小时，创立了寻亲网（www.xunren110.org），用自己的专长与知识，尽力帮助他人。
	                创立公益起点网（www.17do.org），倡导点滴公益，协作共赢，建设美好世界。
                    <br><b>ICSA理论及实践</b><br>
                    调研欧洲的社区支持农业的发展情况，提出互联网社区支持农业(ICSA)理念，把国外的CSA理念与国内实际相结合。
                    <br><b>QQ：705851650</b>，联系请注明原因。<br>
                </div>
                <div class="clear"></div>
                <hr/>
                <div class="content">
                    <img src="<%=request.getContextPath()%>/help/img/furong.jpg" width="100" height="110" alt="" style="float:left;padding:4px"/>
                    读书人，博士，高校教师，电子商务与网站运营资深人士，MBA导师。
                    <br><b>ICSA理论及实践</b><br>
                    电子商务将大大提升传统农业的效率，减少市场风险，实现互联网社区与农民的共赢，深入研究互联网社区支持农业(ICSA)概念、模式，坚信：互联网让世界更美好，互联网让农业更美好！
                    <br><b>QQ：64536821</b>，联系请注明原因。<br>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>