<html>
<body>
<div id="errormsg" style="display:none"></div>
<div>
    <input id="oldpass" name="oldpassword" type="password" />
    <input id="pass" name="password" type="password" />
    <input id="passConfirm" type="password" />              
    <span id="error" style="display:none">Password mismatch</span>
                    
   <button type="submit" onclick="savePass()">Change Password</button>
</div>
 
<script src="jquery.min.js"></script>
<script type="text/javascript">

var serverContext = [[@{/}]];
function savePass(){
    var pass = $("#pass").val();
    var valid = pass == $("#passConfirm").val();
    if(!valid) {
      $("#error").show();
      return;
    }
    $.post(serverContext + "/users/updatePassword",
      {password: pass, oldpassword: $("#oldpass").val()} ,function(data){
        window.location.href = serverContext +"/home.html?message="+data.message;
    })
    .fail(function(data) {
        $("#errormsg").show().html(data.responseJSON.message);
    });
}
</script> 
</body>
</html>