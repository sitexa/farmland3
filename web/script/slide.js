function turnImg(speed,delay){
	var boxWid = $(".slideBox").width();
	var imgWid = $(".slideBox ul li").outerWidth(true);
	var imgLen = $(".slideBox ul li").length;
	var listWid = imgWid * imgLen;
	if(listWid < boxWid)
	{
		$(".slideBox ul").css("margin-left",(boxWid-listWid)/2);
	}
	else
	{
		var index = 0;
		$(".slideBox ul").css("margin-left","0");
		$(".slideBox ul li").clone().appendTo(".slideBox ul");
		$(".slideBox ul").width(listWid*2);
		var autoTime = setInterval(function(){index++; if(index > imgLen){index = 1; $(".slideBox ul").css("margin-left","0");} $(".slideBox ul").animate({marginLeft:-imgWid*index},speed);},delay);
		$(".slideBox ul li").hover(
			function(){clearInterval(autoTime);},
			function(){autoTime = setInterval(function(){index++; if(index > imgLen){index = 1; $(".slideBox ul").css("margin-left","0");} $(".slideBox ul").animate({marginLeft:-imgWid*index},speed);},delay);}
		);
	}
}