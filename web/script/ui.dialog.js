// JavaScript Document
function dialog(id, title){
	this.id = "";
	if(id != undefined && id != null && id != ""){
		this.id = id
	}else{
		this.id = "dialog" + new Date().getMilliseconds();
	}
	this.headerId = this.id + "header";
	this.overlayId = this.id+"overlay";
	this.windows = [];
	
	this.create();
	this.setTitle(title);
}
// create dialog
dialog.prototype.create = function(){
	var self = this;
	var id = this.id;
	$("<div></div>").attr("id",id)
					.addClass("dialog")
					.append(
						$("<div></div>").attr("id",this.headerId)
										.addClass("header")
										.append(
											$("<span></span>").addClass("h")//dialog title
										)
										.append(
											$("<label></label>").addClass("x")
																.click(function(){ self.close(); })//laber of close 
																.hover(function(){	$(this).css("background-position","top right")},
																		function(){	$(this).css("background-position","top left")}
																)
										)
					)
					.append(
						$("<div></div>").addClass("contents")
										.append($("<div></div>").attr("id",id+"left").addClass("left"))
										.append($("<div></div>").attr("id",id+"right").addClass("right"))
										.append($("<div></div>").attr("id",id+"bottom").addClass("bottom"))
					)
					.appendTo("body")
					.easydrag().setHandler(this.headerId);
}
//show dialog
dialog.prototype.show = function(){
	var $dialog = $("#"+this.id);
	if($dialog <= 0){
		this.create();
		$dialog = $("#"+this.id);
	}
	this.createOverlay(this.overlayId);
    var pageSize = getPageSize();
    $dialog.css("left",getScrollLeft()+(pageSize.windowWidth-$dialog.width())/2)
		 .css("top",getScrollTop()+(pageSize.windowHeight-$dialog.height())/2)
		 .fadeIn(666);
}
dialog.prototype.close = function(){
	$("#"+this.id).css("display","none");
	$("#"+this.overlayId).remove();
}
dialog.prototype.fillBody = function(bodyName,html){
	var id = "#"+this.id+bodyName;
	$(id).html('').append(html);
}
dialog.prototype.setTitle = function(title){
	$("#"+this.headerId+" .h").html(title);	
	$("#"+this.overlayId).remove();
}
dialog.prototype.createOverlay = function(id) {
    $("<div id='" + id + "'></div>").css("position", "absolute").css("z-index", "998")
            .css("width", document.body.scrollWidth)
            .css("height", document.body.scrollHeight)
            .css("top", "0").css("left", "0")
            .css("background", "#777")
            .css("filter", "progid:DXImageTransform.Microsoft.Alpha(style=3,opacity=25,finishOpacity=75)")
            .css("opacity", "0.40")
            .appendTo("body");
}
function addLeft(imgId,url,w,h){
	return $("<div></div>").addClass("img").append(
												$("<img />").attr("id", imgId)
															.attr("src",url)
															.css({"width": w, "height": h})
											);
}
