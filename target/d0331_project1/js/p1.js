$(function(){

	$('#shop').mouseenter(function(){
		$('#suspend').show();
	});
	$('#shop').mouseleave(function(){
		$('#suspend').hide();
	})
	
})
$(window).scroll(function(){
		if ($(document).scrollTop() >20) {
			$('.header1').css({backgroundColor:"white"});
		}
		if ($(document).scrollTop() <20) {
			$('.header1').css({backgroundColor:"rgb(53, 54, 56)"});
		}
})