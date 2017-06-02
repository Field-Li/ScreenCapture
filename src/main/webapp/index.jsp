<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8" %>
<html lang="zh-CN"><head>
	<meta charset="utf-8">
	<meta http-equiv="Content-Type" content="text/html" charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
	<meta name="description" content="">
	<meta name="author" content="">
	<link rel="icon" href="../../favicon.ico">

	<title>Theme Template for Bootstrap</title>

	<!-- Bootstrap core CSS -->
	<link href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css" rel="stylesheet">
	<!-- Bootstrap theme -->
	<link href="css/bootstrap-theme.min.css" rel="stylesheet">
	<link href="https://cdn.bootcss.com/bootstrap3-dialog/1.35.4/css/bootstrap-dialog.min.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="theme.css" rel="stylesheet">
	<!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
	<script src="https://cdn.bootcss.com/html5shiv/3.7.3/html5shiv.min.js"></script>
	<script src="https://cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
	<![endif]-->
</head>

<body>


<!-- Fixed navbar -->
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
				<span class="sr-only">Toggle navigation</span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="#">取证中心</a>
		</div>
		<div id="navbar" class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li class="active"><a href="#">Home</a></li>
				<li><a href="">About</a></li>
			</ul>
		</div><!--/.nav-collapse -->
	</div>
</nav>

<div class="container theme-showcase" role="main">

	<!-- Main jumbotron for a primary marketing message or call to action -->
	<div class="jumbotron">
		<h3>网页取证Beta</h3>
	</div>


	<div style="padding: 0px 0px 100px; ">
		<form class="bs-example bs-example-form" role="form">
			<div class="row">
				<br>
				<div class="col-lg-6">
					<div class="input-group">
						<input id="webURL" type="text" class="form-control" placeholder="请输入您要取证的URL，例如：http://www.baidu.com">
                    <span class="input-group-btn">
                        <button id="startCapture" class="btn btn-default" type="button">Go!</button>
                    </span>
					</div><!-- /input-group -->
				</div><!-- /.col-lg-6 -->
			</div><!-- /.row -->
		</form>
	</div>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">取证记录</div>

		<!-- Table -->
		<table class="table">
			<thead>
				<th>网站</th>
				<th>时间</th>
				<th>状态</th>
			</thead>
			<tbody id="webSiteBody">

			</tbody>
		</table>
	</div>






</div> <!-- /container -->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="https://cdn.bootcss.com/jquery/1.12.4/jquery.min.js"></script>
<script>window.jQuery || document.write('<script src="../../assets/js/vendor/jquery.min.js"><\/script>')</script>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script src="https://cdn.bootcss.com/bootstrap3-dialog/1.35.4/js/bootstrap-dialog.min.js"></script>
<script src="js/DateCommon.js"></script>
<script type="text/javascript">
	$('#startCapture').on("click", function (e) {
		var url = $('#webURL').val().trim();
		var param = {'address' : url};
		var queryUrl = "${path}/ScreenCapture/img/" + url.replace("http://",'').replace("https://", '') + '.png';
		if(!isURL(url)){
			var dlg = BootstrapDialog.show({
				message: 'URL错误！'
			});
			setTimeout(function(){
				dlg.close();
			},2000);
			return 0;
		}
		$.ajax({
			url: "${path}/ScreenCapture/capture/site",
			type: 'GET',
			dataType: 'json',
			contentType: 'application/json',
			data: param,
			success: function(data) {
				var body = $('#webSiteBody').html();
				body += '<tr><td><a href="' + queryUrl + '">' +url+'</a></td><td>' + FormatDate(new Date(), 'yyyy-MM-dd') + '</td><td>' + '已取证</td></tr>';
				$('#webSiteBody').html(body);
			},
			error: function(data) {
				var body = $('#webSiteBody').html();
				body += '<tr><td><a href="' + queryUrl + '" target="' + '_Blank">' +url+'</a></td><td>' + FormatDate(new Date(), 'yyyy-MM-dd') + '</td><td>' + '取证失败</td></tr>';
				$('#webSiteBody').html(body);

				var dlg = BootstrapDialog.show({
					message: '取证失败！'
				});
				setTimeout(function(){
					dlg.close();
				},2000);
			}
		});
	});
</script>


<div id="cli_dialog_div"></div><svg xmlns="http://www.w3.org/2000/svg" width="1140" height="500" viewBox="0 0 1140 500" preserveAspectRatio="none" style="display: none; visibility: hidden; position: absolute; top: -100%; left: -100%;"><defs><style type="text/css"></style></defs><text x="0" y="57" style="font-weight:bold;font-size:57pt;font-family:Arial, Helvetica, Open Sans, sans-serif">Thirdslide</text></svg></body></html>