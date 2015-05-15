function delMessage(){
	$("#lmForm").submit();
}
function read_ajax(img, id){
	if(id==null || id=='') alert("此留言不存在，请刷新页面。");
    $.post(
            path + "/user/leave-message!read",
            {id:id},
            function (success) {
                if (success) {
                	$(img).remove();
                    //alert("成功.");
                }
            }
    );
}
/*
function del_ajax() {
    var ids = getCheckedBoxIds();
    if(ids==null || ids.length==0) return;
    $.post(
            path + "/user/leave-message!delete",
            {ids:ids},
            function (success) {
                if (success) {
                	delRow(ids);
                    alert("删除成功.");
                }
            }
    );
}

function getCheckedBoxIds(){
	var ids = new Array();
	$("#messages tbody tr :input[type='checkbox'][checked]").each(function(i){
		ids[i] = $(this).val();
	});
	return ids;
}

function delRow(values){
	if(values == null || values.length == 0) return;
	$("#messages tbody tr :input[type='checkbox']").each(function(){
		var value = $(this).val();
		for(var i=0; i<values.length; i++){
			if(value==values[i]){
				$(this).parent().parent().remove();
				break;
			}
		}
	})
}
*/
function selectAll(checked){
	$("#messages tbody tr :input[type='checkbox']").attr("checked",checked);
	if(checked){
		$("#messages tbody tr").addClass("row-checked");
	}else{
		$("#messages tbody tr").removeClass("row-checked");
	}
}

function inverse(){
	$("#messages tbody tr :input[type='checkbox']").each(function(){
		var checked = $(this).attr("checked");
		$(this).attr("checked",!checked);
		if(!checked){
			$(this).parent().parent().addClass("row-checked");
		}else{
			$(this).parent().parent().removeClass("row-checked");
		}
	});
}

function selectByRead(readTag){
	if(readTag==0){
		selectAll(false);
		$("#messages tbody tr:has(img)").each(function(){
			$(this).find(":input[type='checkbox']").attr("checked",true);
			$(this).addClass("row-checked");
		});
	}else{
		selectAll(true);
		$("#messages tbody tr:has(img)").each(function(){
			$(this).find(":input[type='checkbox']").attr("checked",false);
			$(this).removeClass("row-checked");
		});
	}


}

function selectMenu(menu){
	$("#menubar li.current").removeClass("current");
	$("#menubar li:eq("+menu+")").addClass("current");
}
