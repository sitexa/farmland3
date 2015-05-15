<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    #paging a{
        color: #000066;
        text-decoration:underline;
    }
</style>
<script type="text/javascript">
    function dos(page) {
        document.getElementById("page.current").value = parseInt(page);
        document.getElementById("pageFrm").submit();
    }
</script>
<div id="paging">
    <s:if test="page.total!=0">
        共有${page.total}页结果:
        <s:if test="page.current>6">
            <a href="#" onclick="dos(1);">1</a>
        </s:if>
        <s:if test="page.current>7">
            ...
        </s:if>
        <s:bean name="org.apache.struts2.util.Counter" id="counter">
            <s:param name="first" value="page.current-5"/>
            <s:param name="last" value="page.current+5"/>
            <s:iterator status="its">
                <s:if test="current>1 && current<=page.total+1">
                    <s:if test="#its.index==5">
                        <s:property/>
                    </s:if>
                    <s:else>
                        <a href="#" onclick="dos('<s:property/>');"> <s:property/></a>
                    </s:else>
                </s:if>
            </s:iterator>
        </s:bean>
        <s:if test="page.total>page.current+6">
            ...
        </s:if>
        <s:if test="page.total>page.current+5">
            <a href="#" onclick="dos('<s:property value="page.total"/>');">
                <s:property value="page.total"/>
            </a>
        </s:if>
    </s:if>
</div>
