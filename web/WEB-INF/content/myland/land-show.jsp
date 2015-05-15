<%@ page import="com.sitexa.framework.config.AppConfig" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7"/>
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/myland/land.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/tools.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/ajaxfileupload.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/pic.js"></script>
    <script type="text/javascript">
        function showPic(id) {
            which = id;
            applyeffect();
            document.getElementById("photoslider").src = photos[which];
            document.getElementById("photodescription").innerHTML = descriptions[which];
            playeffect();
            keeptrack();
        }
    </script>
</head>
<body>
<div id="container">
    <div id="header">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol">
            <s:include value="land-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
			<div class="t">
				<div class="t-t">
					预览农场
				</div>
			</div>
            <div class="box">
                <table border="0">
                    <tr>
                        <td class="odd">农场编号</td>
                        <td>${land.landId}</td>
                        <td class="odd">农场名称</td>
                        <td>${land.landName}</td>
                    </tr>
                    <tr>
                        <td class="odd">农场地址</td>
                        <td>${land.landName}</td>
                        <td class="odd">农场类型</td>
                        <td>${land.landType}</td>
                    </tr>
                    <tr>
                        <td class="odd">所在社区</td>
                        <td>${land.site.name} </td>
                        <td class="odd">起止日期</td>
                        <td><s:date name="land.startDate" format="yyyy.MM.dd"/> 至 <s:date name="land.endDate" format="yyyy.MM.dd"/></td>
                    </tr>
                </table>

                <s:if test="pictures.size()>0">
                    <div class="box">
                        <div class="box" style="width:98%;overflow-x:scroll">
                            <table border="0">
                                <tr>
                                    <s:iterator value="pictures" status="its">
                                        <script>
                                            photos[${its.index}] = "<%=AppConfig.getProperty("imgurl")%>/l/${land.landId}/${picUrl}";
                                            titles[${its.index}] = "${picTitle}";
                                            descriptions[${its.index}] = "${description}";
                                        </script>
                                        <td>
                                            <img src="<%=request.getContextPath()%>/image?type=land&picId=${picId}" alt="点击看大图"
                                                 onclick="showPic(${its.index})"/>
                                        </td>
                                    </s:iterator>
                                    <script>
                                        var preloadedimages = new Array();
                                        for (i = 0; i < photos.length; i++) {
                                            preloadedimages[i] = new Image();
                                            preloadedimages[i].src = photos[i];
                                        }
                                    </script>
                                </tr>
                            </table>
                        </div>
                        <div class="content">
                            <script>
                                if (photos.length > 0) {
                                    document.write('<img src="' + photos[0] + '" id="photoslider" name="photoslider" onclick="forward()" style="filter:revealTrans(duration=2,transition=23);width:100%" border=0>');
                                    document.write('<div id="photodescription" style="text-align:center">' + descriptions[0] + '</div>');
                                }
                            </script>
                        </div>
                    </div>
                </s:if>

                <div class="content">
                    <p>${land.description}</p>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            <s:include value="land-right.jsp"/>
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>