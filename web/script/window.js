// JavaScript Document
	function BaseWindow(id, title){
		this.windowId = "";
		if(id != undefined && id != null && id != ""){
			this.windowId = id
		}else{
			this.windowId = "BaseWindow" + new Date().getMilliseconds();
		}
		this.title = title;
		this.create();
		this.btnParas  = [{name:"取 消",fun:null}];
		//this.setButtons();
	}
	BaseWindow.prototype.create = function(){
		var windowId = this.windowId;
		var title = this.title;
		$("<div></div>").attr("id",windowId)
						.addClass("BaseWindow")
						.append(
							$("<div></div>").attr("id",windowId+"-hd")
											.addClass("hd")
											.append(
												$("<span></span>").addClass("bg bg-l")
											)
											.append(
												$("<h4></h4>").attr("id",windowId+"-title")
															.html(title)
											)
											.append(
												$("<span></span>").addClass("bg bg-r")
											)
											.append(
												$("<a></a>").attr("id",windowId+"-close")
															.attr("href","javascript:void(0)")
															.attr("title","关闭")
															.addClass("Aclose")
															.click(function(){		$("#"+windowId).css("display","none")	})
											)
						
						)
						.append(
							$("<div></div>").attr("id",windowId+"-cont")
											.addClass("cont")
						)
						.append(
							$("<div></div>").attr("id",windowId+"-ft")
											.addClass("ft")
						)
						.appendTo("body")
						.easydrag().setHandler(windowId+"-hd");
						
	}
	BaseWindow.prototype.setTitle = function(title){
		this.title = title;
		$("#"+this.windowId+"-title").html(this.title);
	}
	BaseWindow.prototype.hidden = function(){
		$("#"+this.windowId).css("display","none");
	}
	BaseWindow.prototype.close = function(){
		$("#"+this.windowId).remove();
	}
	BaseWindow.prototype.setCont = function(cont){
		$("#"+this.windowId+"-cont").append(cont);
	}	
	/**
	 *	btnParas:{{name,mode,fun},...}
	 *	name:按钮名称；
	 *	mode:按钮模式；（默认及其它：点击后关闭；1：不关闭）
	 *	fun:点击事件；
	 */
	BaseWindow.prototype.setButtons = function(btnParas){
		var self = this;
		var $ft = $("#"+this.windowId+"-ft");
		$ft.html("");
		if(Object.prototype.toString.apply(btnParas) === '[object Array]'){
			btnParas = this.btnParas.concat(btnParas);
		}else{
			btnParas = this.btnParas;
		}
		
		for(var i in btnParas){
			$ft.append(
				$("<div></div>")
					.css("float","right")
					.append(
						$("<div></div>")
							.addClass("btn mg5")
							.append(
								$("<span></span>").html(btnParas[i].name)
							)
							.click(
								(function(i){
									return function(){
												self.hidden();
												if(typeof(btnParas[i].fun)=="function")	btnParas[i].fun();
												if(btnParas[i].mode!=1)		self.close();
											}
								})(i)
							)
							.hover(function(){ $(this).addClass("btn-over") }, function(){ $(this).removeClass("btn-over btn-mdown");})
							.mousedown(function(){ $(this).addClass("btn-mdown") })
							.mouseup(function(){ $(this).removeClass("btn-mdown").addClass("btn-over"); })
						)
			)
		
		}
	}		
	
	var UI = new Object();
	
	UI.msgConfirm = function(title){
		this.baseWindow = new BaseWindow("",title);
		this.baseWindow.setCont(
			$('<div>请输入:<br /><textarea id="v" style="width:490px;height:100px; overflow:auto"/></div>')
		);
		this.baseWindow.setButtons([{name:"关 闭",fun:function(){alert($("#v").val());}}]);
	}