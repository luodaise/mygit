
//查看详情时,ajax返回对象，通过这句话setform(data,$("#formid"))一次性赋值
//不需要一个一个表单赋值
 function setform2 (jsonValue,formobj) 
 {
       var obj=formobj;
       $.each(jsonValue, function (id, ival) {obj.find("#" + id).val(ival); })
 }
 function setform2 (jsonValue,formobj) 
 {
       var obj=formobj;
       $.each(jsonValue, function (id, ival) {obj.find("#" + id).html(ival); })
 }
 
// 常用方法和easyui拓展
// 将form表单的值序列化成对象
function serializeObject(form)
{
	var o = {};
	$.each(form.serializeArray(), function(index) {
		if (o[this['name']]) {
			o[this['name']] = o[this['name']] + "," + this['value'];
		} else {
			o[this['name']] = this['value'];
		}
	});
	return o;
}

//将auth标准数据类型转换成easyui tree的json数据格式
/*loadFilter(data){
	return convert(data,{
		id:"cid",    //必须,节点唯一标识
		text:"name", //必须,节点名称
		pid:"pid",   //必须,父节点id
		url:"url",   //自定义属性
		...
	});
}*/
function convert(rows,json){
	//根据json格式将数据转化为tree节点格式（内部调用）
	function createNode(row){
		var node = {};
		for(j in json){
			node[j] = row[json[j]];
		}
		return node;
	}
	//判断是否有父节点（内部调用）
	function exists(nodes, parentId){
		for(var i=0; i<nodes.length; i++){
			if (nodes[i].id == parentId) return true;
		}
		return false;
	}
	
	//先将数据库集合转化为json字符串格式集合
	var treeNodes = [];
	for(var i=0;i<rows.length;i++){
		var row = rows[i];
		var node = createNode(row);
		treeNodes.push(node);
	}
	
	var nodes = [];//存放方法最终返回的节点集合
	// nodes添加顶层节点
	for(var i=0; i<treeNodes.length; i++){
		var node = treeNodes[i];
		if (!exists(treeNodes, node.pid)){
			nodes.push(node);
		}
	}
	var toDo = [];//存放父节点
	for(var i=0; i<nodes.length; i++){
		toDo.push(nodes[i]);
	}
	while(toDo.length){
		var parentNode = toDo.shift();    // 父节点
		
		// 得到子节点 
		for(var i=0; i<treeNodes.length; i++){
			var node = treeNodes[i];
			if (node.pid == parentNode.id){
	            if (parentNode.children){
	            	parentNode.children.push(node);
	            } else {
	            	parentNode.children = [node];
	            }
	            toDo.push(node);//父节点存放父节点数据，参与遍历
	        }
	    }
	}
	return nodes;
}



//扩展datagrid相同连续列合并
//用法:$(this).datagrid('autoMergeCells');  
//     $(this).datagrid('autoMergeCells',['id','name']);
$.extend($.fn.datagrid.methods, {
	autoMergeCells : function (jq, fields) {
		return jq.each(function () {
			var target = $(this);
			if (!fields) {//判断是否传第二个参数
				fields = target.datagrid("getColumnFields");
			}
			var rows = target.datagrid("getRows");
			var i = 0,
			j = 0,
			temp = {};
			for (i; i < rows.length; i++) {//遍历数据集合
				var row = rows[i];
				j = 0;
				for (j; j < fields.length; j++) {
					var field = fields[j];
					var tf = temp[field];
					if (!tf) {
						tf = temp[field] = {};
						tf[row[field]] = [i];
					} else {
						var tfv = tf[row[field]];
						if (tfv) {
							tfv.push(i);
						} else {
							tfv = tf[row[field]] = [i];
						}
					}
				}
			}

			$.each(temp, function (field, colunm) {
				$.each(colunm, function () {
					var group = this;
					
					if (group.length > 1) {
						var before,
						after,
						megerIndex = group[0];
						for (var i = 0; i < group.length; i++) {
							before = group[i];
							after = group[i + 1];
							if (after && (after - before) == 1) {
								continue;
							}
							var rowspan = before - megerIndex + 1;
							if (rowspan > 1) {
								target.datagrid('mergeCells', {
									index : megerIndex,
									field : field,
									rowspan : rowspan
								});
							}
							if (after && (after - before) != 1) {
								megerIndex = after;
							}
						}
					}
				});
			});
		});
	}
});

// 扩展验证类型
$.extend($.fn.validatebox.defaults.rules,{
	//手机号码
	mobile : {
		validator : function(value,param){
			var re = /^1\d{10}$/;
			if(re.test(value)){
				return true;
			}else{
				return false;
			}
		},
		message : "请输入正确的手机号"
	},
	//俩次输入是否一致
	equals : {
		validator : function(value,param){
			return value == $(param[0]).val();
		},
		message : "俩次密码输入不一致"
	},
	//验证账号是否合法(字母、数字、下划线组成，字母开头，4-16位)
	username : {
		validator : function(value,param){
			var isOk = false;
			//var re = /^[a-zA-z]\w{3,15}$/;
			//if(re.test(value)){
				$.ajax({
					url : "/LogisticVAT/user/validUsername?username=" + value,
					type : "post",
					async : false,
					success : function(data){
						if(data == "true"){
							isOk = true;
						}
					}
				});
			//}
			return isOk;
		},
		message : "账号已存在"
	},
	//验证手机号
	mobileRemote : {
		validator : function(value,param){
			var isOk = false;
			var re = /^1\d{10}$/;
			if(re.test(value)){
				$.ajax({
					url : "/LogisticVAT/user/validMobile?mobile=" + value,
					type : "post",
					async : false,
					success : function(data){
						if(data == "true"){
							isOk = true;
						}
					}
				});
			}
			return isOk;
		},
		message : "输入的手机号格式不正确或者已注册"
	},
	//验证手机号
	mobileExit : {
		validator : function(value,param){
			var isOk = false;
			var re = /^1\d{10}$/;
			if(re.test(value)){
				$.ajax({
					url : "/LogisticVAT/user/validMobile?mobile=" + value,
					type : "post",
					async : false,
					success : function(data){
						if(data == "false"){
							isOk = true;
						}
					}
				});
			}
			return isOk;
		},
		message : "账号不存在"
	},
	//验证码
	code : {
		validator : function(value,param){
			var isOk = false;
			$.ajax({
				url : "/LogisticVAT/user/checkCode?code="+value,
				type:"post",
				async:false,
				success:function(data){
					if(data == "true"){
						isOk = true;
					}
				}	
			});
			return isOk;
		},
		message : "验证码输入有误"
	},
	msg : {
		validator : function(value,param){
			var isOk = false;
			$.ajax({
				url : "/LogisticVAT/user/checkMsg?msg="+value,
				type:"post",
				async:false,
				success:function(data){
					if(data == "true"){
						isOk = true;
					}
				}
			});
			return isOk;
		},
		message : "短信验证码输入有误"
	}
	
});

// 扩展验证方法
// $('#id').validatebox('remove'); 删除
// $('#id').validatebox('reduce'); 恢复
// 只适用于非easyui的from组件
$.extend($.fn.validatebox.methods, {
	remove : function(jq, newposition) {
		return jq.each(function() {
			$(this).removeClass("validatebox-text validatebox-invalid").unbind(
					'focus.validatebox').unbind('blur.validatebox');
		});
	},
	reduce : function(jq, newposition) {
		return jq.each(function() {
			var opt = $(this).data().validatebox.options;
			$(this).addClass("validatebox-text").validatebox(opt);
		});
	}
});

//对Date的扩展，将 Date 转化为指定格式的String
//月(M)、日(d)、小时(h)、分(m)、秒(s)、季度(q) 可以用 1-2 个占位符， 
//年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字) 
//例子： 
//(new Date()).Format("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423 
//(new Date()).Format("yyyy-M-d h:m:s.S")      ==> 2006-7-2 8:9:4.18 
Date.prototype.Format = function(fmt) {
	var o = {
		"M+" : this.getMonth() + 1, // 月份
		"d+" : this.getDate(), // 日
		"h+" : this.getHours(), // 小时
		"m+" : this.getMinutes(), // 分
		"s+" : this.getSeconds(), // 秒
		"q+" : Math.floor((this.getMonth() + 3) / 3), // 季度
		"S" : this.getMilliseconds() // 毫秒
	};
	if (/(y+)/.test(fmt))
		fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
				.substr(4 - RegExp.$1.length));
	for ( var k in o)
		if (new RegExp("(" + k + ")").test(fmt))
			fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
					: (("00" + o[k]).substr(("" + o[k]).length)));
	return fmt;
};


//*****SHA-1加密工具类*****
var hexcase = 0;  /* hex output format. 0 - lowercase; 1 - uppercase        */
var b64pad = ""; /* base-64 pad character. "=" for strict RFC compliance   */
var chrsz = 8;  /* bits per input character. 8 - ASCII; 16 - Unicode      */
function hex_sha1(s) { return binb2hex(core_sha1(str2binb(s), s.length * chrsz)); }
function b64_sha1(s) { return binb2b64(core_sha1(str2binb(s), s.length * chrsz)); }
function str_sha1(s) { return binb2str(core_sha1(str2binb(s), s.length * chrsz)); }
function hex_hmac_sha1(key, data) { return binb2hex(core_hmac_sha1(key, data)); }
function b64_hmac_sha1(key, data) { return binb2b64(core_hmac_sha1(key, data)); }
function str_hmac_sha1(key, data) { return binb2str(core_hmac_sha1(key, data)); }

/*  
* Perform a simple self-test to see if the VM is working  
*/
function sha1_vm_test() {
    return hex_sha1("abc") == "a9993e364706816aba3e25717850c26c9cd0d89d";
}

/*  
* Calculate the SHA-1 of an array of big-endian words, and a bit length  
*/
function core_sha1(x, len) {
    /* append padding */
    x[len >> 5] |= 0x80 << (24 - len % 32);
    x[((len + 64 >> 9) << 4) + 15] = len;

    var w = Array(80);
    var a = 1732584193;
    var b = -271733879;
    var c = -1732584194;
    var d = 271733878;
    var e = -1009589776;

    for (var i = 0; i < x.length; i += 16) {
        var olda = a;
        var oldb = b;
        var oldc = c;
        var oldd = d;
        var olde = e;

        for (var j = 0; j < 80; j++) {
            if (j < 16) w[j] = x[i + j];
            else w[j] = rol(w[j - 3] ^ w[j - 8] ^ w[j - 14] ^ w[j - 16], 1);
            var t = safe_add(safe_add(rol(a, 5), sha1_ft(j, b, c, d)),
                       safe_add(safe_add(e, w[j]), sha1_kt(j)));
            e = d;
            d = c;
            c = rol(b, 30);
            b = a;
            a = t;
        }

        a = safe_add(a, olda);
        b = safe_add(b, oldb);
        c = safe_add(c, oldc);
        d = safe_add(d, oldd);
        e = safe_add(e, olde);
    }
    return Array(a, b, c, d, e);

}

/*  
* Perform the appropriate triplet combination function for the current  
* iteration  
*/
function sha1_ft(t, b, c, d) {
    if (t < 20) return (b & c) | ((~b) & d);
    if (t < 40) return b ^ c ^ d;
    if (t < 60) return (b & c) | (b & d) | (c & d);
    return b ^ c ^ d;
}

/*  
* Determine the appropriate additive constant for the current iteration  
*/
function sha1_kt(t) {
    return (t < 20) ? 1518500249 : (t < 40) ? 1859775393 :
         (t < 60) ? -1894007588 : -899497514;
}

/*  
* Calculate the HMAC-SHA1 of a key and some data  
*/
function core_hmac_sha1(key, data) {
    var bkey = str2binb(key);
    if (bkey.length > 16) bkey = core_sha1(bkey, key.length * chrsz);

    var ipad = Array(16), opad = Array(16);
    for (var i = 0; i < 16; i++) {
        ipad[i] = bkey[i] ^ 0x36363636;
        opad[i] = bkey[i] ^ 0x5C5C5C5C;
    }

    var hash = core_sha1(ipad.concat(str2binb(data)), 512 + data.length * chrsz);
    return core_sha1(opad.concat(hash), 512 + 160);
}

/*  
* Add integers, wrapping at 2^32. This uses 16-bit operations internally  
* to work around bugs in some JS interpreters.  
*/
function safe_add(x, y) {
    var lsw = (x & 0xFFFF) + (y & 0xFFFF);
    var msw = (x >> 16) + (y >> 16) + (lsw >> 16);
    return (msw << 16) | (lsw & 0xFFFF);
}

/*  
* Bitwise rotate a 32-bit number to the left.  
*/
function rol(num, cnt) {
    return (num << cnt) | (num >>> (32 - cnt));
}

/*  
* Convert an 8-bit or 16-bit string to an array of big-endian words  
* In 8-bit function, characters >255 have their hi-byte silently ignored.  
*/
function str2binb(str) {
    var bin = Array();
    var mask = (1 << chrsz) - 1;
    for (var i = 0; i < str.length * chrsz; i += chrsz)
        bin[i >> 5] |= (str.charCodeAt(i / chrsz) & mask) << (32 - chrsz - i % 32);
    return bin;
}

/*  
* Convert an array of big-endian words to a string  
*/
function binb2str(bin) {
    var str = "";
    var mask = (1 << chrsz) - 1;
    for (var i = 0; i < bin.length * 32; i += chrsz)
        str += String.fromCharCode((bin[i >> 5] >>> (32 - chrsz - i % 32)) & mask);
    return str;
}

/*  
* Convert an array of big-endian words to a hex string.  
*/
function binb2hex(binarray) {
    var hex_tab = hexcase ? "0123456789ABCDEF" : "0123456789abcdef";
    var str = "";
    for (var i = 0; i < binarray.length * 4; i++) {
        str += hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8 + 4)) & 0xF) +
           hex_tab.charAt((binarray[i >> 2] >> ((3 - i % 4) * 8)) & 0xF);
    }
    return str;
}

/*  
* Convert an array of big-endian words to a base-64 string  
*/
function binb2b64(binarray) {
    var tab = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    var str = "";
    for (var i = 0; i < binarray.length * 4; i += 3) {
        var triplet = (((binarray[i >> 2] >> 8 * (3 - i % 4)) & 0xFF) << 16)
                | (((binarray[i + 1 >> 2] >> 8 * (3 - (i + 1) % 4)) & 0xFF) << 8)
                | ((binarray[i + 2 >> 2] >> 8 * (3 - (i + 2) % 4)) & 0xFF);
        for (var j = 0; j < 4; j++) {
            if (i * 8 + j * 6 > binarray.length * 32) str += b64pad;
            else str += tab.charAt((triplet >> 6 * (3 - j)) & 0x3F);
        }
    }
    return str;
}
//*****SHA-1加密工具类*****

/*进行个人或者多人云端消息推送
title–消息标题，
content – 消息内容
type – 消息类型，1:消息 2:通知
platform - 0:全部平台，1：ios, 2：android
groupName - 推送组名，多个组用英文逗号隔开.默认:全部组。eg.group1,group2 .
userIds - 推送用户id, 多个用户用英文逗号分隔，eg. user1,user2。
*/
function sendInfo(title,content,type,platform,userIds)
{
	var  appId="A6981959466960";
    var appKey="1C89C81F-F2F7-C9B9-0878-966FDB26A723";
    var host="https://p.apicloud.com/api/push/message";
    //var now = new Date().getTime();
    var now = Date.now();;
    var AppKey = hex_sha1(appId + "UZ" + appKey + "UZ" + now) + "." + now;
    $.ajax({
    	type: "POST",
    	url: host,
    	dataType:"json",
    	cache:false,
    	crossDomain: true,
    	headers:{
    		'X-APICloud-AppId' : appId,
            'X-APICloud-AppKey' : AppKey
    	},
    	data:{
    			'title' : title, 
    		    'content' : content,
    		    'type' : type, 
    		    'platform' : platform, 
    		    'userIds' : userIds	
    	 },
    	 success: function(msg){
    		 alert("发送状态"+msg)
    	 }
    })
}