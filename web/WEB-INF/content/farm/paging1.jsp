<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style type="text/css">
    #paging a{
        color: #000066;
        text-decoration:underline;
    }
</style>
<script type="text/javascript">
    function dos1(page) {
        document.getElementById("page1.current").value = parseInt(page);
        document.getElementById("pageFrm1").submit();
    }
</script>
<div id="paging">
    <s:if test="page1.total!=0">
        共有${page1.total}页结果:
        <s:if test="page1.current>6">
            <a href="#" onclick="dos1(1);">1</a>
        </s:if>
        <s:if test="page1.current>7">
            ...
        </s:if>
        <s:bean name="org.apache.struts2.util.Counter" id="counter">
            <s:param name="first" value="page1.current-5"/>
            <s:param name="last" value="page1.current+5"/>
            <s:iterator status="its">
                <s:if test="current>1 && current<=page1.total+1">
                    <s:if test="#its.index==5">
                        <s:property/>
                    </s:if>
                    <s:else>
                        <a href="#" onclick="dos1('<s:property/>');"> <s:property/></a>
                    </s:else>
                </s:if>
            </s:iterator>
        </s:bean>
        <s:if test="page1.total>page1.current+6">
            ...
        </s:if>
        <s:if test="page1.total>page1.current+5">
            <a href="#" onclick="dos1('<s:property value="page1.total"/>');">
                <s:property value="page1.total"/>
            </a>
        </s:if>
    </s:if>
</div>
