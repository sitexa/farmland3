<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<script type="text/javascript">
	$(function(){
		var $menu = $(".menu");
		var menu = '${menu}';
		$menu.find(".select").removeClass("select");
		if(menu!="" ){
			$menu.find("li:eq("+ menu +")").addClass("select");
		}
		
		$(".book").click(function(){
			//$("#t").html($(this).find("a").html());	//换标题
			$(".directory").hide();					//切换菜单
			$(this).next().show();
		})
	})
	function load(name){
		$("#content").load("<%=request.getContextPath()%>/help/book/"+name+".jsp");
	}
	function openBook(index){
		$(".book:eq("+index+") a").click();
	}
/*
 * 当书多了以后，再这样进行处理，现在是用的数组，到时用xml文件；
	var book = {
				name:"name",
				link:"link",
				directory:[{
							name:"directory1",
							link:"directory1-link1",
							directory:[
										{name:"children1",link:"children1-link"},
										{name:"children2",link:"children2-link"}
									]
						}]

	};
	function init(){
		alert(book.name+","+book.link+",");
		var directory1 = book.directory;
		for( var para in directory1){
			alert(directory1[para].name+","+directory1[para].link);
			var directory2 = directory1[para].directory;
			for( var para in directory2){
				alert(directory2[para].name+","+directory2[para].link);
			}
		}
	}
*/
</script>
<div class="box">
	<div class="menu">
		<div class="menubar">目&nbsp;&nbsp;&nbsp;&nbsp;录</div>
	    <div class="menuitem">
			<div class="book"><a href="javascript:void(0)" onclick="load('userManual/0.0')">农场操作手册</a></div>
			<div class="directory">	
		        <ul>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/1.0')">一、注册登录</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/2.0')">二、购买农庄</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/3.0')">三、进入我的农庄</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/4.0')">四、网上农庄界面</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/5.0')">五、收获：采摘</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/6.0')">六、收获：收割</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/7.0')">七、翻耕</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/8.0')">八、统包模式切换</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/9.0')">九、日常管理操作</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/10.0')">十、免费操作</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('userManual/11.0')">十一、个人账户查询</a>
		            </li>
				</ul>
			</div>		
			<div class="book"><a href="javascript:void(0)" onclick="load('gardenOfKnowledge/0.0')">菜地里的学问</a></div>
			<div class="directory">
		        <ul>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.1')">第一章 基础知识</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.1')">植物的根</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.2')">植物的茎</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.3')">植物的叶</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.4')">植物的花</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.5')">植物的种子</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.6')">常用农具</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/1.7')">农业史话</a>
		            </li>
		            <li class="directory1">
		                <a href="#">第二章 土壤</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/2.1')">土壤的基本成分</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/2.2')">理想的土壤</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/2.3')">土壤中的生命</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/2.4')">土壤的酸碱性</a>
		            </li>
		            <li class="directory1">
		                <a href="#">第二章 肥料</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.1')">植物生长所需的化学元素</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.2')">化肥</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.3')">天然肥料</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.4')">堆肥的基本做法</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.5')">堆肥的原理</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.6')">堆肥的改进方法</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/3.7')">绿肥</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.1')">第四章 撒种与栽种</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.1')">栽种时间</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.2')">预备土壤</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.3')">种子</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.4')">基肥</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.5')">播种</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.6')">间苗</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.7')">育苗</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/4.8')">移栽</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.1')">第五章 田间管理</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.1')">覆盖</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.2')">除草</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.3')">灌溉</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.4')">追肥</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.5')">搭架、插扦和立支柱</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.6')">修理</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.7')">人工授粉</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/5.8')">日常田间事务清单</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.1')">第六章 病虫害防治</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.1')">预防的方法</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.2')">控制的方法</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.3')">益虫</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.4')">益鸟</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/6.5')">驱虫植物</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/7.1')">第七章 收获、食用及储藏</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/7.1')">收获</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/7.2')">食用</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/7.3')">储藏</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/8.1')">第八章 蔬菜的习性</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/8.1')">喜酸还是喜碱</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/8.2')">喜荫还是喜阳</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/8.3')">喜湿还是耐旱</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/8.4')">伴生</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/9.0')">第九章 蔬菜的分类</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/10.1')">第十章 轮作</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/10.1')">轮作的基本原理</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/10.2')">考虑季节</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/10.3')">按高矮来轮作</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/10.4')">按习性来轮作</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/11.1')">第十一章 规划菜园</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/11.1')">什么时候规划</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/11.2')">基本原则</a>
		            </li>
		            <li>
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/11.3')">规划之后要做的事</a>
		            </li>
		            <li class="directory1">
		                <a href="javascript:void(0)" onclick="load('gardenOfKnowledge/12.0')">第十二章 一年农事安排</a>
		            </li>
		        </ul>
			</div>
	    </div>
	</div>
</div>