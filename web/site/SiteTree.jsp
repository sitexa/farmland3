<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title><%=request.getRequestURI()%>
    </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link rel="STYLESHEET" type="text/css" href="../dhtmlx/dhtmlxTree/codebase/dhtmlxtree.css">
    <script src="../dhtmlx/dhtmlxTree/codebase/dhtmlxcommon.js"></script>
    <script src="../dhtmlx/dhtmlxTree/codebase/dhtmlxtree.js"></script>
</head>
<body style="overflow:auto" oncontextmenu=self.event.returnValue=true>
<table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0">
    <tr>
        <td style="padding:10px;width:20%;height:100%">
            <div id="tree1" style="width:100%; height:100%;border :1px solid Silver; overflow:auto;"></div>
        </td>
        <td style="padding:10px;width:20%;height:100%">
            <div id="tree2" style="width:100%; height:100%;border :1px solid Silver; overflow:auto;"></div>
        </td>
        <td style="padding:10px;width:20%;height:100%">
            <div id="tree3" style="width:100%; height:100%;border :1px solid Silver; overflow:auto;"></div>
        </td>
        <td style="padding:10px;width:20%;height:100%">
            <div id="tree4" style="width:100%; height:100%;border :1px solid Silver; overflow:auto;"></div>
        </td>
        <td style="padding:10px;width:20%;height:100%">
            <div id="tree5" style="width:100%; height:100%;border :1px solid Silver; overflow:auto;"></div>
        </td>
    </tr>
</table>
<script type="text/javascript">
    try {
        var tree1 = new dhtmlXTreeObject("tree1", "100%", "100%", 100);
        tree1.setImagePath("../dhtmlx/dhtmlxTree/codebase/imgs/");
        tree1.setXMLAutoLoading("/site/site!siteTree?siteId=100");
        tree1.loadXML("/site/site!siteTree?siteId=100");
        tree1.enableCheckBoxes(0);
        //tree1.attachEvent("onClick", onNodeSelect);//firefox:onNodeSelect undefined error!
        tree1.attachEvent("onClick",function(nodeId,event){
            onNodeSelect(nodeId);
        });
        function onNodeSelect(nodeId) {
            document.getElementById("tree2").innerHTML="";
            document.getElementById("tree3").innerHTML="";
            document.getElementById("tree4").innerHTML="";
            document.getElementById("tree5").innerHTML="";
            var tree2 = new dhtmlXTreeObject("tree2", "100%", "100%", nodeId);
            tree2.setImagePath("../dhtmlx/dhtmlxTree/codebase/imgs/");
            tree2.setXMLAutoLoading("/site/site!siteTree?siteId="+nodeId);
            tree2.loadXML("/site/site!siteTree?siteId="+nodeId);
            tree2.enableCheckBoxes(0);
            tree2.attachEvent("onClick", onNodeSelect2);//set function object to call on node select
        }
        function onNodeSelect2(nodeId) {
            document.getElementById("tree3").innerHTML="";
            document.getElementById("tree4").innerHTML="";
            document.getElementById("tree5").innerHTML="";
            var tree3 = new dhtmlXTreeObject("tree3", "100%", "100%", nodeId);
            tree3.setImagePath("../dhtmlx/dhtmlxTree/codebase/imgs/");
            tree3.setXMLAutoLoading("/site/site!siteTree?siteId="+nodeId);
            tree3.loadXML("/site/site!siteTree?siteId="+nodeId);
            tree3.enableCheckBoxes(0);
            tree3.attachEvent("onClick", onNodeSelect3);//set function object to call on node select
        }
        function onNodeSelect3(nodeId){
            document.getElementById("tree4").innerHTML="";
            document.getElementById("tree5").innerHTML="";
            var tree3 = new dhtmlXTreeObject("tree4", "100%", "100%", nodeId);
            tree3.setImagePath("../dhtmlx/dhtmlxTree/codebase/imgs/");
            tree3.setXMLAutoLoading("/site/site!siteTree?siteId="+nodeId);
            tree3.loadXML("/site/site!siteTree?siteId="+nodeId);
            tree3.enableCheckBoxes(0);
            tree3.attachEvent("onClick", onNodeSelect4);//set function object to call on node select
        }
        function onNodeSelect4(nodeId){
            document.getElementById("tree5").innerHTML="";
            var tree3 = new dhtmlXTreeObject("tree5", "100%", "100%", nodeId);
            tree3.setImagePath("../dhtmlx/dhtmlxTree/codebase/imgs/");
            tree3.setXMLAutoLoading("/site/site!siteTree?siteId="+nodeId);
            tree3.loadXML("/site/site!siteTree?siteId="+nodeId);
            tree3.enableCheckBoxes(0);
            tree3.attachEvent("onClick", onNodeSelect5);//set function object to call on node select
        }
        function onNodeSelect5(nodeId){
            
        }
    } catch(e) {
        alert(e);
    }

        //    tree.enableDragAndDrop(1);
        //    tree.setOnOpenHandler(tonopen);
        //    tree.setOnClickHandler(tonclick);
        //    tree.setOnCheckHandler(toncheck);
        //    tree.setOnDblClickHandler(tonopen);
        //    tree.setDragHandler(tondrag);
        //set input control as "landing zone"
        /*
         var f = document.getElementById("frmOrgEdit");
         for (var i = 0; i < f.elements.length; i++) {
         tree.dragger.addDragLanding(f.elements[i], new s_control);
         }
         */

</script>
</body>
</html>