<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<table cellpadding="0" cellspacing="0" border="0">
    <tr>
        <td width="60">数量</td>
        <td class="value first"><img src="<%=request.getContextPath()%>/myfarm/img/bar.png" height="16" width="${crops.seedNumber}">${crops.seedNumber}
        </td>
    </tr>
    <tr>
        <td>心情</td>
        <td class="value"><img src="<%=request.getContextPath()%>/myfarm/img/bar.png" height="16" width="${crops.vitalPower}">${crops.vitalPower}</td>
    </tr>
    <tr>
        <td>成熟度</td>
        <td class="value"><img src="<%=request.getContextPath()%>/myfarm/img/bar.png" height="16" width="${crops.maturity}">${crops.maturity}</td>
    </tr>
    <s:iterator value="crops.statuses" status="its">
        <tr>
            <td>${faction.actionName}</td>
            <td <s:if test="its.index==crops.statuses.size"> class="value last"</s:if><s:else>class="value"</s:else> >
                <img src="<%=request.getContextPath()%>/myfarm/img/bar.png" height="16" width="${actionCount}">${actionCount}
            </td>
        </tr>
    </s:iterator>
</table>