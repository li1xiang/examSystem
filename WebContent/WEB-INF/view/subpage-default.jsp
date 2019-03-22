<html>
  <head>
  </head>
 
<body>
	<div class="sub-page" id="subpage-default">
	    <div class="section-a">
	        <div class="section-a-head section-a-head-fixed">
	            <div class="san-row">
	                <div class="san-col-9">
	                    <div class="section-a-title h5"></div>
	                </div>
	                <div class="san-col-3 AR"></div>
	            </div><!-- san-row end -->
	        </div><!-- section-a-head end -->
	    </div><!-- section-a end -->
	
	    <div id="section-welcome">
	        <div id="welcome-text"></div>
	        <div id="welcome-bar">
	            <div id="welcome-system-name"></div>
	        </div>
	    </div>
	    
	</div><!-- sub-page end -->

	<script type="text/javascript">
	$(function(){
	    var cw = parseFloat($('#main-page-body-content').width()),
	        ch = parseFloat($('#main-page-body').height());
	    
	    $('#subpage-default').css('height', ch + 'px');
	    $('#subpage-default').css('background',"url(images/bg-01.png)")
	    var welWidth = parseFloat($('#section-welcome').width()),
	        welHeight = parseFloat($('#section-welcome').height());
	    var welTop = (ch - 41 > welHeight) ? (ch - 41 - welHeight) * 0.3 : 41;
	    $('#section-welcome').css({
	        'top': welTop + 'px'
	    });
	    
	    $('#welcome-text').css('left', (cw - parseFloat($('#welcome-text').width())) * 0.5 + 'px').addClass('show');
	    $('#welcome-bar').animate({'right': 0+'px'}, 400, function(){
	        $('#welcome-system-name').css('left', (cw - parseFloat($('#welcome-system-name').width())) * 0.5 + 'px').addClass('show');
	    });
	});
	</script>
</body>
</html>