// JavaScript Document
(function($) {
    $.fn.hoverCss = function(styleName, v1, v2) {
        return this.each(function() {
            $(this).hover(
            		function() {
            			$(this).css(styleName, v1);
            		},
                    function() {
                        $(this).css(styleName, v2);
                    }
            );
        });
    };
})(jQuery);

	/**
	 *@ function		getScrollTop
	 *@ description 	获取scrollTop
	 */
	function getScrollTop(){
		return window.pageYOffset  || document.documentElement.scrollTop  || document.body.scrollTop || 0;
	}
	/**
	 *@ function		getScrollLeft
	 *@ description 	获取scrollLeft
	 */
	function getScrollLeft(){
		return window.pageXOffset  || document.documentElement.scrollLeft  || document.body.scrollLeft || 0;
	}
	/**
	 *@ function		getClientheight
	 *@ description 	获取clientheight(html窗体高度)
	 *	注意：如果有头尾文件，会影响top,bottom值。top:0 即为头文件尾部，同理，bottom:0为尾文件顶。
	 *	可能是由于先加载主文件，后include文件造成的。
	 *	
	 */
	function getClientheight(){
		var clientheight = Math.min(document.body.clientHeight , document.documentElement.clientHeight);
		if(clientheight==0)
			clientheight= Math.max(document.body.clientHeight , document.documentElement.clientHeight);
		return clientheight;
	}
	/**
	 * @function		getPageSize
	 * @return			获取 页面的pageWidth（宽高）、pageHeight（高度）、窗口的windowWidth（宽度）、windowHeight（高度）。
	 */
	function getPageSize(){  
	  var xScroll, yScroll;  
	  if (window.innerHeight && window.scrollMaxY) {  
	    xScroll = document.body.scrollWidth;
	    yScroll = window.innerHeight + window.scrollMaxY;
	  } else if (document.body.scrollHeight > document.body.offsetHeight){ // all but Explorer Mac
	    xScroll = document.body.scrollWidth;
	    yScroll = document.body.scrollHeight;
	  } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
	    xScroll = document.body.offsetWidth;
	    yScroll = document.body.offsetHeight;
	  }

	  var windowWidth, windowHeight;
	  if (self.innerHeight) {  // all except Explorer
	    windowWidth = self.innerWidth;
	    windowHeight = self.innerHeight;
	  } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
	    windowWidth = document.documentElement.clientWidth;
	    windowHeight = document.documentElement.clientHeight;
	  } else if (document.body) { // other Explorers
	    windowWidth = document.body.clientWidth;
	    windowHeight = document.body.clientHeight;
	  }  
	  
	  // for small pages with total height less then height of the viewport
	  if(yScroll < windowHeight){
	    pageHeight = windowHeight;
	  } else { 
	    pageHeight = yScroll;
	  }

	  if(xScroll < windowWidth){  
	    pageWidth = windowWidth;
	  } else {
	    pageWidth = xScroll;
	  }

	  arrayPageSize = {"pageWidth":pageWidth,"pageHeight":pageHeight,"windowWidth":windowWidth,"windowHeight":windowHeight};
	  return arrayPageSize;
	}
	/**
	 * 等比例缩放图片
	 * 说明:
	 * 		1、只给定宽，按宽缩放；
	 * 		2、只给定高，按高缩放；
	 * 		3、同时给定宽高，按图片的原比例自适应缩放，在给定宽高的矩形中最大化。
	 * @param imgId	
	 * @param parameters
	 */
	function scaling(Img,parameters) 
	{ 
		var $img = $(Img);
		$img.width("auto").height("auto");
		if($img.width()!=0){
			var scale = $img.height()/$img.width();
			//alert("$img.attr('src'):"+$img.attr('src')+"\n$img.width():"+$img.width()+"\n$img.height():"+$img.height());
			if(parameters.width & parameters.height){
				var scale0 = parameters.height/parameters.width;
				if(scale>=scale0){
					//按高处理:实际高宽比例>给定主宽比例，说明按实际比例缩放，给定高度不够
					$img.height(parameters.height);
					$img.width($img.height()/scale);
				}else{
					//按宽处理
					$img.width(parameters.width);
					$img.height($img.width()*scale);
				}
				
			}else if(parameters.width){
				$img.width(parameters.width);
				$img.height($img.width()*scale);
			}else if(parameters.height){
				$img.height(parameters.height);
				$img.width($img.height/scale);
			}
			//$img.css("display","block");
		}
	}
	
	//---------------------------------正则表达式验证----------------------------------------------
	
	/**
	 * @param value 字符串 
	 * @description	验证字符串是否为空	
	 */
	
	function isEmpty(value){
		var reg = new RegExp(/^[\s|　]*$/);
		return reg.test(value);
	}
	function isInt(value){
		var reg = new RegExp(/^([1-9]\d*|0)$/);
		return reg.test($.trim(value));
	}
	//正整数
	function ispositiveInteger(value){
		var reg = new RegExp(/^[1-9]\d*$/);
		return reg.test($.trim(value));
	}

	function isDouble(value){
		var reg = new RegExp(/^\d+(.\d+)?$/);
		return reg.test($.trim(value));
	}
	function isImg(url){
		var reg = new RegExp(/.((gif)|(jpg)|(bmp)|(png)|(jpeg)|(pjpeg))$/);
		return (reg.test(url.toLowerCase()));
	}
	
	
//$.function 	

$.title = function(obj, top, left){
	var title = $(obj).attr("title");
	var titleId = $(obj).attr("id")+"_title";
	if(top==undefined || top == null) top = 0;
	if(left==undefined || left == null) left = 0;
	$(obj).mouseover(function(e){	 
			$(this).attr("title","");
			var $content = $("<span></span>").css({
													position: 'absolute',
													top: 0,
													left: 0,
													margin: '3px',
													border: '1px solid #B9FF73',
													'font-size': '13px'
												})
											.html(title)
											.appendTo('body');
			$("<div></div>")
				.attr("id",titleId)
				.css({
						position: 'absolute',
						top: (e.pageY||0) + top,		
						left: (e.pageX||0) + left,
						display: 'none'
						})
				.append($("<div></div>").css({
												width: $content.width(),
												height: '15px',
												padding: '3px',
												border: '1px solid #AAFF55',
												background: '#B9FF73',
												overflow: 'hidden'
										})
										.fadeTo(0,0.5)
				
				)
				.append( $content )
				.appendTo('body')
				.fadeIn(333);
		})
		.mousemove(function(e){
			$("#"+titleId)
				.css({
					top: (e.pageY||0) + top,
					left: (e.pageX||0) + left
				});
		})
		.mouseout(function(){ 
						var o = this;
						$(obj).attr("title",title);
						setTimeout(function(){	 $("#"+titleId).remove(); }, 0 );
		});

}

$.substring = function(str, width){
	if (str == null) return "";
	
	var d = 0;
	var n = 0;
	for(; n < str.length; n++) {
		d = str.charCodeAt(n) > 256 ? d+2 : d+1;
		if(d > width) break;
	}
	return str.substring(0, n);
}
$.getStringLength = function(str){
	if (str == null) return 0;
	
	var d = 0;
	var n = 0;
	for(; n < str.length; n++) {
		d = str.charCodeAt(n) > 256 ? d+2 : d+1;
	}
	return d;
}
$.formatLink = function(links, width){
	$(links).filter("a").each(function(){
		var val = $.trim($(this).html());
		var length = $.getStringLength(val);
		var t = length > width;
		$(this).attr("title", $(this).attr("title")+val);
		$(this).html($.substring(val, t?width-3:width)+(t?"...":""));
	})
}
