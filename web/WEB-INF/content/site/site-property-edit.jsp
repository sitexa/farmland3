<%--
  User: xnpeng
  Date: 2009-4-6
  Time: 23:38:20
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>花木兰ICSA农场</title>
    <link href="<%=request.getContextPath()%>/style/site.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript">
        function addProp() {
            if (document.getElementById("div_prop").style.display == "none") {
                document.getElementById("div_prop").style.display = "block";
                return;
            }
            var tableb = document.getElementById("proptable");
            var maxtr = tableb.childNodes.length;
            var oldtr = tableb.childNodes[maxtr - 1];
            var td0 = oldtr.childNodes[0];
            var td1 = oldtr.childNodes[1];

            maxtr++;

            var newtr = document.createElement("tr");
            newtr.setAttribute("id", "prop" + maxtr);

            var newtd1 = document.createElement("td");
            var newtd2 = document.createElement("td");
            var newtd3 = document.createElement("td");

            newtd1.innerHTML = td0.innerHTML;
            newtd2.innerHTML = td1.innerHTML;
            newtd3.innerHTML = "<input type=button value=\"删除\" onClick=\"delProp('prop" + maxtr + "')\"/>";

            newtr.appendChild(newtd1);
            newtr.appendChild(newtd2);
            newtr.appendChild(newtd3);

            tableb.appendChild(newtr);

        }

        function delProp(rowId) {
            var tableb = document.getElementById("proptable");
            var row = document.getElementById(rowId);
            tableb.removeChild(row);
        }

        function deleteProperty(propId) {
            var f = document.getElementById("frmProperty");
            f.propId.value = propId;
            f._method.value = "delete";
            f.submit();
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
	    	<s:include value="site-menu.jsp"/>
		</div>
	    <div id="centerbar" class="centercol">
	        <div class="box">
	            <div class="title1">修改社区属性</div>
	            <div class="content">
	                <s:form theme="simple" id="frmProperty" action="%{#request.contextPath}/site/site-property/%{site.siteId}">
	                    <s:hidden name="_method"/>
	                    <s:hidden name="propId"/>
	                    <s:hidden name="site.siteId"/>
	                    <table class="borderAll">
	                        <s:iterator value="properties">
	                            <tr>
	                                <s:hidden name="properties(%{propId}).site.siteId" value="%{site.siteId}"/>
	                                <s:hidden name="properties(%{propId}).propId" value="%{propId}"/>
	                                <td class="nowrap"><s:textfield size="10" readonly="true" name="properties(%{propId}).propName" value="%{propName}"/></td>
	                                <td class="nowrap"><s:textfield size="30" name="properties(%{propId}).propValue" value="%{propValue}"/></td>
	                                <td><input type=button value="删除" onClick="deleteProperty('<s:property value="%{propId}"/>');"></td>
	                            </tr>
	                        </s:iterator>
	                    </table>
	                    <div id="div_prop" style="display:none">
	                        <table class="borderAll">
	                            <tbody id="proptable">
	                            <tr id="prop0">
	                                <td class="nowrap"><s:select list="propertyItems" name="propName"/></td>
	                                <td><input type="text" name="propValue"></td>
	                                <td><input type=button value="删除" onClick="delProp('prop0');"></td>
	                            </tr>
	                            </tbody>
	                        </table>
	                    </div>
	                    <input class="formbutton" type=button value="增加属性" onClick="addProp();">
	                    <s:submit cssClass="formbutton" method="update" value="保存修改"/>
	                </s:form>
	            </div>
	        </div>
	        <s:actionmessage/>
	        <s:actionerror/>
	    </div>
	    <div id="rightbar" class="rightcol" >
	    	<s:include value="site-right.jsp"/>
		</div>
	</div>
	<div class="clear"></div>
	<div id="footer">
	    <s:include value="../f.jsp"/>
	</div>
</div>
</body>
</html>