$.fn.btnFormat =function(){
	document.onselectstart=function(){return /input|textarea/i.test(window.event.srcElement.tagName);}
	return this.each(function(){
			$(this).hover(function(){ $(this).addClass("btn-over") }, function(){ $(this).removeClass("btn-over btn-mdown");})
					.mousedown(function(){ $(this).addClass("btn-mdown") })
					.mouseup(function(){ $(this).removeClass("btn-mdown").addClass("btn-over"); })
		})
}