$(function(){

    $("#form").hide();

    $("#edit").click(function(){
        $("#data").hide();
        $("#form").show();
    });

    $("#cancel").click(function(){
        $("#data").show();
        $("#form").hide();
    });
});