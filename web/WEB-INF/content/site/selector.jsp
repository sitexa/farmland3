<%--
  User: xnpeng
  Date: 2009-4-5
  Time: 14:03:52
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>社区选择器</title>
    <link href="<%=request.getContextPath()%>/style/index.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/interface/JSiteService.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/dwr/engine.js"></script>
    <script type="text/javascript">
        function toggleSelect(name, flag) {
            var btn = "btn" + name;
            if (name == "all") {
                document.getElementById("city").disabled = flag;
                document.getElementById("btncity").disabled = flag;
                document.getElementById("county").disabled = flag;
                document.getElementById("btncounty").disabled = flag;
                document.getElementById("town").disabled = flag;
                document.getElementById("btntown").disabled = flag;
                document.getElementById("village").disabled = flag;
                document.getElementById("btnvillage").disabled = flag;
                document.getElementById("site").disabled = flag;
                document.getElementById("btnsite").disabled = flag;
            } else {
                document.getElementById(name).disabled = flag;
                if(name=="town" || name=="village" || name=="site"){
                	document.getElementById(btn).disabled = flag;
                }
            }
        }
        function doChange(t) {
            try {
                var siteId;
                var select = document.getElementById(t);
                for (var m = 0; m < select.length; m++) {
                    if (select.options[m].selected) {
                        siteId = select.options[m].value;
                        break;
                    }
                }
                JSiteService.getSites(siteId,
                {
                    callback:function(str) {
                        if (str == null || str.length == 0) return;
                        setSelect(t, str);
                    }
                });
            } catch(e) {
                alert(e);
            }
        }
        function setSelect(t, str) {
            var arr = str.split(";");
            var kv,k,v;

            var select;
            if (t == "state") {
                select = document.getElementById("city");
                toggleSelect("city", false);
                toggleSelect("county", true);
                toggleSelect("town", true);
                toggleSelect("village", true);
                toggleSelect("site", true);
            }
            else if (t == "city") {
                select = document.getElementById("county");
                toggleSelect("county", false);
                toggleSelect("town", true);
                toggleSelect("village", true);
                toggleSelect("site", true);
            }
            else if (t == "county") {
                select = document.getElementById("town");
                toggleSelect("town", false);
                toggleSelect("village", true);
                toggleSelect("site", true);
            }
            else if (t == "town") {
                select = document.getElementById("village");
                toggleSelect("village", false);
                toggleSelect("site", true);
            }
            else if (t == "village") {
                select = document.getElementById("site");
                toggleSelect("site", false);
            }

            for (var j = 0; j < select.options.length; j++)
                select.options[j] = null;

            select.options[0] = new Option("请选择...", "-1");
            for (var i = 0; i < arr.length; i++) {
                kv = arr[i];
                k = kv.split(",")[0];
                v = kv.split(",")[1];
                select.options[i + 1] = new Option(v, k);
            }
        }
        function doSelect(t) {
            var select = document.getElementById(t);

            var i = select.options.selectedIndex;
            if (i == "0") return;
            var v = select.options[i].text;
            var k = select.options[i].value;
            window.parent.returnValue = k + ";" + v;
            window.parent.close();
        }
    </script>
</head>
<body onload="toggleSelect('all',true);" style="margin:10px">
<s:form theme="simple" action="%{#request.contextPath}/selector">
    <fieldset>
        <legend id="btnall">选择社区</legend>
        <table>
            <tr>
                <td nowrap>省/市/自治区</td>
                <td><s:select id="state" list="stateList" listKey="siteId" listValue="name" name="state.siteId"
                              value="state.siteId" headerKey="-1"
                              headerValue="请选择..." onchange="doChange('state');"/></td>
                <td>
                    <!--<input id="btnstate" type="button" value="选择" onclick="doSelect('state');"/>-->
                </td>
            </tr>
            <tr>
                <td>市/地/州/盟</td>
                <td><s:select id="city" disabled="true" list="cityList" listKey="siteId" listValue="name"
                              name="city.siteId" value="city.siteId"
                              onchange="doChange('city');"/></td>
                <td>
                    <!--<input id="btncity" disabled="true" type="button" value="选择" onclick="doSelect('city');"/>-->
                </td>
            </tr>
            <tr>
                <td>区/县(市)/旗</td>
                <td><s:select id="county" disabled="true" list="countyList" listKey="siteId" listValue="name"
                              name="county.siteId" value="county.siteId"
                              onchange="doChange('county');"/></td>
                <td>
                    <!--<input id="btncounty" disabled="true" type="button" value="选择" onclick="doSelect('county');"/>-->
                </td>
            </tr>
            <tr>
                <td>乡/镇/街道</td>
                <td><s:select id="town" disabled="true" list="townList" listKey="siteId" listValue="name"
                              name="town.siteId" value="town.siteId"
                              onchange="doChange('town');"/></td>
                <td>
                    <input id="btntown" disabled="true" type="button" value="确定" onclick="doSelect('town');"/>
                </td>
            </tr>
            <tr>
                <td>村/社区</td>
                <td><s:select id="village" disabled="true" list="villageList" listKey="siteId" listValue="name"
                              name="village.siteId" value="village.siteId"
                              onchange="doChange('village');"/></td>
                <td>
                    <input id="btnvillage" disabled="true" type="button" value="确定" onclick="doSelect('village');"/>
                </td>
            </tr>
            <tr>
                <td>社的家</td>
                <td><s:select id="site" disabled="true" list="siteList" listKey="siteId" listValue="name"
                              name="site.siteId" value="site.siteId"/></td>
                <td>
                    <input id="btnsite" disabled="true" type="button" value="确定" onclick="doSelect('site');"/>
                </td>
            </tr>
        </table>
    </fieldset>
</s:form>
</body>
</html>