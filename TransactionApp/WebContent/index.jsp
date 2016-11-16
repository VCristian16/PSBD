<!DOCTYPE html>
<html>
<head>
<title>My bank</title>
<style>
*, *:before, *:after {
	box-sizing: border-box;
}

form {
	border: 1px solid #c6c7cc;
	border-radius: 5px;
	font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
	overflow: hidden;
	width: 240px;
}

fieldset {
	border: 0;
	margin: 0;
	padding: 0;
}

input {
	border-radius: 10px;
	font: 14px/1.4 "Helvetica Neue", Helvetica, Arial, sans-serif;
	margin: 0;
}

.account-info {
	padding: 20px 20px 0 20px;
}

.account-info label {
	color: #395870;
	display: block;
	font-weight: bold;
	margin-bottom: 20px;
}

.account-info input {
	background: #fff;
	border: 1px solid #c6c7cc;
	box-shadow: inset 0 1px 1px rgba(0, 0, 0, .1);
	color: #636466;
	padding: 6px;
	margin-top: 6px;
	width: 100%;
}

.account-action {
	background: #f0f0f2;
	border-top: 1px solid #c6c7cc;
	padding: 20px;
}

.account-action .btn {
	background: linear-gradient(#4CAF50, #4CAF50);
	border: 0;
	color: #fff;
	cursor: pointer;
	font-weight: bold;
	align: center;
	padding: 8px 16px;
}

.account-action label {
	color: #7c7c80;
	font-size: 12px;
 	margin: 10px 0 0 20px;
 	
}
.outer {
    display: table;
    position: absolute;
    height: 100%;
    width: 100%;
}

.middle {
    display: table-cell;
    vertical-align: middle;
}

.inner {
    margin-left: auto;
    margin-right: auto; 
 }

</style>
</head>
<div align="center" class="outer">
<div class="middle">
<div class="inner">
	<form action="Login" method="post" id="form_login">
		<fieldset class="account-info">
			<label> Id <input type="text" name="userid">
			</label> <label> Password <input type="password" name="password">
			</label>
		</fieldset>
		<fieldset class="account-action">
			<input class="btn" type="submit" name="submit" value="Login">

		</fieldset>
	</form>
</div>
</div>
</div>

</body>
</html>