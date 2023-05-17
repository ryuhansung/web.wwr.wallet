$(function() {
  // radio, checkbox style
  $(".iCheck").iCheck({
    checkboxClass: 'icheckbox_flat-blue',
    radioClass: 'iradio_flat-blue'
  });	
  // 탭다운 게시판
  $('dl.tabDownBody dt').each(function() {
	  $(this).on("click", function() {
		  $('.tabDownBody dd').slideUp();
		  if($(this).next().css("display") != "block") {
			  $(this).next().slideDown();
		  }
	  });
  });	
	//open popup + 1
	$('.popup01').on('click', function(event){
		event.preventDefault();
		$('.popupCon01').addClass('is-visible');
	});
	//open popup + 2
	$('.popup02').on('click', function(event){
		event.preventDefault();
		$('.popupCon02').addClass('is-visible');
	});
	//open popup PIN
	$('.popupPin').on('click', function(event){
		event.preventDefault();
		$('.popupPinCon').addClass('is-visible');
	});	
	//close popup
	$('.cd-popup').on('click', function(event){
		if( $(event.target).is('.cd-popup-close') || $(event.target).is('.cd-popup') ) {
			event.preventDefault();
			$(this).removeClass('is-visible');
		}
	});
	//close popup when clicking the esc keyboard button
	$(document).keyup(function(event){
		if(event.which=='27'){
			$('.cd-popup').removeClass('is-visible');
		}
	});	
});