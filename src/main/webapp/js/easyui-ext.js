//validate ext
$.extend($.fn.validatebox.defaults.rules,{
	equals:{
		validator:function(value,param){
			return value == $(param[0]).val();
		},
		message:"数据不一致！"
	}
});