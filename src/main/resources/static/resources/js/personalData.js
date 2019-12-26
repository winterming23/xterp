var $;
var $form;
var form;
//var areaData = address;
layui.config({
	base : "/js/"
}).use(['form','layer','jquery','laydate','upload'],function(){
    var upload = layui.upload;
	var layer = parent.layer === undefined ? layui.layer : parent.layer,
		laydate = layui.laydate;
		$ = layui.jquery;
		form = layui.form;
		//$form=$('form');
		//初始化省
		//loadProvince();
		
		// laydate.render({
		// 	elem: '#birthday' //指定元素
		// 	,max: 'new Date()'
		// });


	$.ajax({
		type:"POST",
		url:'/user/persUsersByDeptId',
		success:function(data){
			var ad=data.ad;
			/*var as=data.as;*/
			$("#id").attr("value",ad.id)
			$("#loginname").attr("value",ad.loginname)
			$("#name").attr("value",ad.name)
			// $("#eMail").attr("value",ad.eMail)
			// $("#birthday").attr("value",ad.birthday)
			$("#address").attr("value",ad.address)
			$("#pwd").attr("value",ad.pwd)
			//给单选框radio赋值赋值完必须要form.render();
			// $("input[name=sex][value=0]").attr("checked", ad.sex == 0 ? true : false);
			// $("input[name=sex][value=1]").attr("checked", ad.sex == 1? true : false);
			// $("input[name=sex][value=3]").attr("checked", ad.sex == 3? true : false);
			form.render();
			/*var roleId=data.roles;
			$.each(roleId, function (index, item) {
				$('#roles').append(new Option(item.roleName, item.roleId));// 下拉菜单里添加元素
			});
			layui.form.render("select");*/
		}
	})

    //文件上传
    upload.render({
        elem: '.thumbBox',
        url: '/file/uploadFile',
        acceptMime:'image/*',
        field:'mf',
        method : "post",
        done: function(res, index, upload){
            var path=res.path;
            $('.thumbImg').attr('src','/file/showImageByPath?path='+path);
            $('.thumbBox').css("background","#fff");
            //给隐藏域赋值
            $("#imgpath").val(path);
        }
    });

		
		//自定义验证规则
		form.verify({ 
			pass: [/(.+){6,16}$/, '密码必须6到16位']
			,repass: function(value){
				var repassvalue = $('#password').val();
				if(value != repassvalue){
					return '两次输入的密码不一致!';
				}
			}
		});
		
		// $("#eMail").blur(function(){
		// 	$.ajax({
		// 		type: "post",
		// 		url: "/admin/checkAdminByEmail?eMail="+$("#eMail").val()+"&username="+$("#username").val(),
		// 		success:function(data){
		// 			if(data.code!=0){
		// 				top.layer.msg(data.msg);
		// 				$("#eMail").val("");
		// 				$("#eMail").focus();
		// 			}
		// 		}
		// 	});
		// });


 	form.on("submit(updAdmin)",function(data){
 		//弹出loading
 		var index = top.layer.msg('数据提交中，请稍候',{icon: 16,time:false,shade:0.8});
 		var msg="";
 		$.ajax({
    		type: "post",
            url: "/user/updateUserss",
            async:false,
            data:data.field,
			dataType:"json",
			success:function(d){
				if(d.code==0){
		        	msg="更新成功！";
				}else{
					layer.msg(d.msg, {icon: 3});
				}
			}
        });
 		setTimeout(function(){
 			top.layer.close(index);
 			top.layer.msg(msg);
 			layer.closeAll("iframe");
 			//刷新父页面
 			parent.location.reload();
        },2000);
 		return false;
 	})
	
})