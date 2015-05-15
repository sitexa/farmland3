//market-index.jsp************
/**
 * 分页跳转
 */
function dos(page) {
    document.getElementById("page.current").value = parseInt(page);
    document.getElementById("frmSearch").submit();
}
/**
 * 跳转到编辑页面
 */
function edit(itemId) {
	$("#frmSearch").attr("action",url + "/market/market/" + itemId + "/edit");
    document.getElementById("frmSearch").submit();
}
/**
 * 删除
 */
function del(itemId) {
	$("#frmSearch").attr("action",url + "/market/market/" + itemId + "/destroy");
    document.getElementById("frmSearch").submit();
}
/**
 * 跳转到显示页面
 */
function show(itemId) {
	$("#frmSearch").attr("action",url + "/market/market/" + itemId );
    document.getElementById("frmSearch").submit();
}
/**
 * 加载上传flash.
 */
function loading()
{ 
	$("#loading").ajaxStart(function(){ $(this).show();})
				 .ajaxComplete(function(){ $(this).hide(); }); 
} 
/**
 * @Function: updateImgTemp
 * 上传临时图片.
 */
function addImg()
{ 	
	if(!testImg("upload"))	return false;
	$.ajaxFileUpload({ url:url + "/market/market!uploadImgTemp", 
					   secureuri:false, 
					   fileElementId:'upload', 
					   data:{ 
							title:"1"
							}, 
					   dataType: 'json', 
					   success: function (data, status)
    					   { 	
    					   		if(data!=null){
    					   			addImgInfo(data.newFileName,data.oldFileName);
    					   		}
    					   	}, 
					   	error: function (data, status, e)
    					   	{ 
    					   		alert("添加失败，请重新添加。");
    					   	} 
					   });
	return false;
} 
        
function addImgInfo(newFileName,oldFileName){
	$("#imgBoxTitle").css("display","block");
	var imgUrl = imgPath + "/uploads/temp/" + newFileName ;
	var del = "<a href='javascript:void(0)' onClick='delImgInfo(this)'>删除</a>";
	var img = "<img width='50px' height='50px' title='" + oldFileName + "' src='" + imgUrl + "' style='border: 1px dashed #849BCA;'/>"; 
	var imgName = "<input type='hidden' name='imgName' value='" + newFileName + "' />";
	var imgTitle = "标题：<input type='text' size='10' name='imgTitle' value='" + $("#title").val() + "'/>";
	var imgDescription = "说明：<input type='text' size='15' name='imgDescription' value='" + $("#description").val() + "'/>";
	$("<li>" + del + img + imgName + imgTitle + imgDescription + "</li>").appendTo($("#imgBox"));
	
	//清空
	$("#title").val("");
	$("#description").val("");
	$("#upload").remove();//只有提交或from的reset方法能清空，所以采用删除再加的办法
	var upload = "<input id='upload' type='file' size='20' name='upload' class='input' contenteditable='false'>";
	$(upload).insertBefore("#buttonUpload");
}

function delImgInfo(obj){
	$(obj).parent().remove();
}

//market-edit.jsp*********************************

/**
 * @Function: uploadImg
 * 上图片.
 */
function uploadImg(id)
{ 
	if(!testImg("upload"))	return false;
	loading();//动态加载小图标 
	$.ajaxFileUpload({ url:url + "/market/market/" + id +"/uploadImg", 
					   secureuri:false, 
					   fileElementId:'upload', 
					   data:{ 
							title:$("#title").val(), 
							description:$("#description").val() 
							}, 
					   dataType: 'json', 
					   success: function (data, status)
    					   { 	
    					   		addRow(data);
    					   		$("#title").val("标题");
    					   		$("#description").val("说明");
    					   	}, 
					   	error: function (data, status, e)
    					   	{ 
    					   	} 
					   });
	return false; 
} 

/**
 * @Function: addRow
 * @para data	json数据
 * 添加行.
 */        
function addRow(data){
	var row = $("#imgBox tr:first").clone();
	var cols = row.children();
	cols[0].innerHTML =  "<img src='" + url + "/market/market!getImage?picId=" + data.picId +"'/>";
	cols[1].innerHTML =  "<input type='text' size='10' id='" + data.picId + "-title' value='" + data.title + "'/><br>";
	cols[1].innerHTML += "<input type='text' size='30' id='" + data.picId + "-description' value='" + data.description + "'/>";
	cols[2].innerHTML =  "<input type='button' value='删 除' onClick='delImg(this," + data.picId + ")'/>&nbsp;&nbsp;&nbsp;";
	cols[2].innerHTML += "<input type='button' value='修 改' onClick='upDateImg(this," + data.picId + ")'/>&nbsp;&nbsp;&nbsp;";
	row.appendTo("#imgBox");
}
/**
 * @Function: delRow
 * @obj		即this
 * 删除行.
 */ 
	function delRow(obj){
	$(obj).parent().parent().remove();
}
	
/**
 * @Function: ajaxFileUpload
 * 上传文件.
 */
function delImg(obj,picId){
		$.get(
				url + "/market/market!delMarketPicture",
				{picId:picId}, 
				function (success) {
				if(success=="true"){
					delRow(obj);
				}else{
					alert("删除失败，请重新操作。");
				}
			}
		);
}
/**
 * @Function: upDateImg
 * 更新图片信息.
 */
function upDateImg(obj,picId){
	var _title = $("#" + picId + "-title").val();
	var _description = $("#" + picId + "-description").val();
		$.post(
				url + "/market/market!updateMarketPicture",
				{picId:picId,title:_title,description:_description}, 
				function (data) {
				if(data.success=="false"){
					alert("没有找到您操作的图片,可能图片已被删除，请刷新页面.");
					delRow(obj);
				}
			}
		);
}

//------------------------------------------public function()--------------------------------------------------
//提交验证
function testFrom(){ 
	if(isEmpty("itemTitle","项目标题为空,请输入项目标题。"))	return false;
	if(isEmpty("contents","项目内容为空，请输入项目内容。"))	return false;
	return true;
}
function testImg(id){
	var url = $("#"+id).val();
	if(url==""){
		alert("请您选择上传图片。");
		return false;
	}
	if(!isImg(url)){
		alert("您选择的图片文件格式不正确\n只能上传图片扩展名为(.gif .jpg .bmp .png .jpeg .pjpeg)的图片\n请重新选择。");
		return false;
	}
	return true;
}
/**
 * 验证图片
 */
function isImg(imgUrl){
	var reg = new RegExp(/.((gif)|(jpg)|(bmp)|(png)|(jpeg)|(pjpeg))$/);
	return (reg.test(imgUrl.toLowerCase()));
}
/**
 * @param id
 * @param message
 */
function isEmpty(id,message){
	if($.trim($("#"+id).val())==""){
		alert(message);
		return true;
	}
	return false;
}