<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <title>花木兰ICSA农场 </title>
    <link href="<%=request.getContextPath()%>/style/main.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/user/css/leaveMessage.css" rel="stylesheet" type="text/css"/>
    <link href="<%=request.getContextPath()%>/style/element.css" rel="stylesheet" type="text/css"/>

    <script type="text/javascript" src="<%=request.getContextPath()%>/script/jquery.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/user/js/leaveMessage.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath()%>/script/element.js"></script>
    <script type="text/javascript">
        $(function() {
            $("#switchTab").click(function() {
                if ($("#right").css("display") == "none") {
                    $("#right").css("display", "block");
                    $(this).html("&gt;");
                } else {
                    $("#right").css("display", "none");
                    $(this).html("&lt;");
                }
            })
            /*
             $("#friends li").each(function(){
             $(this).dblclick(function(){
             var separator = $("#receiver").val()==""?"":";";
             $("#receiver").val($("#receiver").val()+separator+$(this).html());
             })
             })
             */
            $(".g-title").click(function() {
                var $this = $(this);
                if ($this.hasClass("fold")) {
                    $this.removeClass("fold");
                    $(this).next().show();
                } else {
                    $this.addClass("fold");
                    $(this).next().hide();
                }
            })
            $(".g-list li").each(function() {
                $(this).hover(function() {
                    $(this).css("background", "#E8EBD0")
                }, function() {
                    $(this).css("background", "")
                })
                        .dblclick(function() {
                    var separator = $("#receiver").val() == "" ? "" : ";";
                    $("#receiver").val($("#receiver").val() + separator + $(this).html());
                });
            })
            $(".btn").btnFormat();
        })
        function testForm() {
            if ($.trim($("#receiver").val()) == "") {
                alert("请您选择收件人。");
                return false;
            }
            var ids = getIds();
            if (ids == "") {
                alert("未找到收件人，请重新输入或选择。");
                return false;
            }
            if ($.trim($("#title").val()) == "") {
                alert("请您输入主题。");
                return false;
            }
            if ($.trim($("#message").val()) == "") {
                alert("请您输留言信息。");
                return false;
            }
            $("#ids").val(ids);
            $("#lmForm").submit();
        }
        function getIds() {
            var receiver = $("#receiver").val();
            if (receiver == "") return "";
            var names = receiver.split(";");
            var id;
            var value = "";
            for (var i = 0; i < names.length; i++) {
                id = findIdByName(names[i]);
                value += ((value == "") ? ("'" + id + "'") : (",'" + id + "'"));
            }
            return value;
        }
        function findIdByName(name) {
            var friends = $(".g-list li");
            for (var i = 0; i < friends.length; i++) {
                if ($.trim(friends[i].innerHTML) == name) return friends[i].id;
            }
            return "";
        }
    </script>
    <style type="text/css">
        .message {
            width: 500px;
            height: 100px;
            border: 1px solid #E8E8E8;
            margin: 1px 2px;
        }

        .sendBox {
        }

        /*由于DOCTYPE解释标准不同，所以在此页面对body、container、header位置大小进行了调整*/
        body {
            text-align: center;
        }
    </style>
</head>
<body>
<div id="container" style="+ *width:987px;">
    <div id="header" style="+ *height:88px;">
        <s:include value="../h.jsp"/>
    </div>
    <div id="mainwrapper">
        <div id="leftbar" class="leftcol a-l">
            <s:include value="control-menu.jsp"/>
        </div>
        <div id="centerbar" class="centercol">
            <s:include value="leave-message-header.jsp"/>
            <div style="margin:3px auto;">
                <div class="actionbar b-bg">
                    <div class="btn mg5" onclick="testForm()">
                        <span>发 送</span>
                    </div>
                    <div class="btn mg5" onclick="history.back()">
                        <span>返 回</span>
                    </div>
                </div>
                <div class="bodybar">
                    <span class="blank6"></span>
                    <table border="0" cellpadding="0" cellspacing="0">
                        <tbody>
                        <tr>
                            <td width="100%">
                                <s:form id="lmForm" theme="simple" action="%{#request.contextPath}/user/leave-message!save">
                                    <input type="hidden" name="leaveMessage.sender.memberId" value="${member.memberId}"/>
                                    <input type="hidden" id="ids" name="ids" value=""/>
                                    <table id="messagebody" border="0" cellpadding="0" cellspacing="0">
                                        <tbody>
                                        <tr>
                                            <th width="60px" align="right" valign="top" class="paddingTop7">收件人:</th>
                                            <td><input type="text" class="textInput" id="receiver" name="receiver" value="${strangerName}"/></td>
                                        </tr>
                                        <tr>
                                            <th width="60px" align="right" valign="top" class="paddingTop7">主　题:</th>
                                            <td><input type="text" class="textInput" id="title" name="leaveMessage.title"/></td>
                                        </tr>
                                        <tr>
                                            <th width="60px" align="right" valign="top" class="paddingTop7">内　容:</th>
                                            <td valign="top"><textarea class="textarea" id="message" name="leaveMessage.message"></textarea></td>
                                        </tr>
                                        </tbody>
                                    </table>
                                </s:form>
                            </td>
                            <td width="10px" align="center" valign="middle"><a id="switchTab">&gt;</a></td>
                            <td id="right" width="150px" height="100%">
                                <div class="treeList">
                                    <div class="t-title">通讯录</div>
                                    <div class="actionbar b-b1">
                                        <input type="text" size="8"/>
                                        <input type="button" value="查询"/>
                                    </div>
                                    <div class="group">
                                        <div class="g-title">好友<a href="javascript:void(0)">(<s:property value="friends.size"/>)</a></div>
                                        <div class="g-list">
                                            <ul>
                                                <s:iterator value="friends">
                                                    <li id="${fellow.memberId}"><s:property value="fellow.realname"/></li>
                                                </s:iterator>
                                                <li class="stranger" id="${strangerId}">${strangerName}</li>
                                            </ul>
                                        </div>
                                    </div>
                                </div>
                            </td>
                        </tr>
                        </tbody>
                    </table>
                </div>
                <div class="actionbar b-bg">
                    <div class="btn mg5" onclick="testForm()">
                        <span>发 送</span>
                    </div>
                    <div class="btn mg5" onclick="history.back()">
                        <span>返 回</span>
                    </div>
                </div>
            </div>
        </div>
        <div id="rightbar" class="rightcol">
            &nbsp;
        </div>
        <div class="clear"></div>
    </div>
    <div id="footer" class="a-l">
        <s:include value="../f.jsp"/>
    </div>
</div>
</body>
</html>