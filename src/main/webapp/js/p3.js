$(function(){
	$('.p1').click(function(){
		$('.body2').html(p1);
	});
	$('.p2').click(function(){
		$('.body2').html(p2);
	});
	$('.p3').click(function(){
		$('.body2').html(p1+p2);
	});
	$('#shop').mouseenter(function(){
		$('#suspend').show();
	});
	$('#shop').mouseleave(function(){
		$('#suspend').hide();
	});
})

$(window).scroll(function(){
		if ($(document).scrollTop() >20) {
			$('.header1').css({backgroundColor:"white"});
		}
		if ($(document).scrollTop() <20) {
			$('.header1').css({backgroundColor:"rgb(53, 54, 56)"});
		}
})


let p1="<h2><b>应用如何横屏显示</b></h2><h4><b>在我们的大屏系统中所有应用默认竖屏显示，窗口大小由系统指定且无法任意拉伸。</b></h4>"+
"<ul ><li id=\"factor3\">为了达到应用窗口可配置的目的，对于非适配应用我们维护了一个支持云端下发的 xml配置文件，对于适配应用，支持应用在自己的 AndroidManifest.xml 中配置窗口参数。</li>"+
"</ul><p id=\"factor1\">通过 application 和 activity 内的 meta-data 来指定配置参数。注意：application为必配项，activity 根据需要配置，未配置的 activity 会继承 application 的配置。value"+
"书写格式定义如下：</p><p id=\"factor2\">< meta-data android:name=\"windowParams\"value=\"version,windowMode,resizeMode,forceResizeMode,width,height,minWidth,minHeight,densityDpi\" />"+
"</p><h4><b>各参数以英文逗号分隔，顺序不能变，中间不能出现空格</b></h4><ul><li id=\"factor3\">version: 版本号，起始版本号从0开始，修改配置参数后请增加版本号使之生效。注意：版本号仅对 application 配置起作用，activity 下无意义，可写任意值，"+
"当前主要是为了保持配置参数一致性，方便开发使用；如果同一应用在系统 xml 文件配置的版本号和应用自定义配置的版本号相同时则优先使用应用自定义配置。</li><li id=\"factor3\">windowMode: -1-不指定；0-竖屏显示；1-横屏显示；2-最大化显示；4-全屏显示</li>"+
"8-按长宽比拉伸（暂不支持）</li><li id=\"factor3\">forceResizeMode: -1-不指定；0-不支持；1-支持restart后缩放；特殊用途，应用一般不用管这个参数，直接设置0或-1。</li><li id=\"factor3\">width, height: "+
"窗口默认宽高，单位dp，0-不指定。注意：请确保长宽比跟windowMode一致。</li><li id=\"factor3\">minWidth, minHeight: 窗口最小宽高，单位dp，0-不指定，用于自由拉伸时最小窗口大小，如果不配置则使用窗口默认宽高。注意：请确保长宽比跟 windowMode 一致。</li></ul>";

let p2="<h2><b>应用如何横屏显示</b></h2><h4><b>在我们的大屏系统中所有应用默认竖屏显示，窗口大小由系统指定且无法任意拉伸。</b></h4>"+
"<p id=\"factor1\">value 最后的参数如果不做定义可以不写，比如下例 application 配置最小宽高使用窗口默认宽高就可以不用再写最小宽高了activity 中如有未配置的参数，则会继承 application 的相同参数，"+
"比如 application 配置为支持全屏拉伸的默认横屏显示的 value=\"0,1,5,0,900,614\"，如果该应用的某个 activity 只是想配置为不支持全屏，而其他参数跟 application 保持一致，则 activity 的配置可简单的写成 value=\"0,1,1\"。"+
"</p><p id=\"factor2\">< application android:name=\".EmailApplication\" android:label=\"@string/app_name\">< !-- application layout parameters on pc mode -->< !-- 注意：application的meta-data必须在所有activity的前面 -->"+
"< meta-data android:name=\"windowParams\" android:value=\"0,0,0,-1,360,614,0,0,480\" />< activity android:name=\"com.android.email2.ui.MailActivityEmail\">< !-- activity layout parameters on pc mode -->"+
"< meta-data android:name=\"windowParams\" android:value=\"0,1,5,-1,900,614\" />< /activity>< /application></p><p id=\"factor1\">作为对比，下面是对非适配应用而使用的 xml 配置文件内容，各字段含义请参考上面应用自定义的说明，"+
"对于适配应用请尽量在应用内自己解决参数配置问题，今后原则上 xml 配置文件仅给非适配应用使用。</p><p id=\"factor2\">< application package=\"com.android.email\" version=\"0\">< windowMode>0< /windowMode>< resizeMode>0< /resizeMode>"+
"< width>360< /width>< height>614< /height>< densityDpi>480< /densityDpi>< special-activity name=\"com.android.email2.ui.MailActivityEmail\">< activity-width>900< /activity-width>< activity-height>614< /activity-height>"+
"< activity-windowMode>1< /activity-windowMode>< activity-resizeMode>5</ activity-resizeMode>< activity-minWidth>900</ activity-minWidth>< activity-minHeight>614< /activity-minHeight>< /special-activity>< /application></p>";