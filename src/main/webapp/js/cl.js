String.prototype.trim     = function(){return this.replace(/^\s+|\s+$/, '');};
String.prototype.left     = function(inLen){return this.substring(0,inLen);};
String.prototype.right    = function(inLen){return this.substring((this.length-inLen),this.length);};

String.prototype.mid      = function(inStart,inLen){
	var iEnd;
	if (!inLen)
	 iEnd = this.length;
	else
	 iEnd = inStart + inLen;
	return this.substring(inStart,iEnd);
};
String.prototype.arrSplit = function(split){
	var tmpStr;
	var i ;
	var iCnt;
	var iEnd;
	tmpStr = this;

	iCnt = 0;
	for( i = 0 ; i < tmpStr.length ; i++) {
		if (tmpStr.charAt(i) == split) {
			iCnt++;
		}
	}
	iCnt++;

	arr_str = new Array(iCnt);

	for (i = 0 ; i < iCnt ; i++)	{
		iEnd = tmpStr.indexOf(split);
		if (iEnd < 0)
			arr_str[i] = tmpStr;
		else{
			arr_str[i] = tmpStr.substring(0,iEnd);
			tmpStr = tmpStr.substring(iEnd+1);
		}
	}

	return arr_str;	
	
};

String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

/**
 * 브라우저를 체킹하는 함수. jquery가 1.9.1 부터 함수를 없애버림 ㅡㅡ;;
 * 
 */
var BrowserDetect = {
	init: function () {
		this.browser = this.searchString(this.dataBrowser) || "An unknown browser";
		this.version = this.searchVersion(navigator.userAgent)
			|| this.searchVersion(navigator.appVersion)
			|| "an unknown version";
		this.OS = this.searchString(this.dataOS) || "an unknown OS";
	},
	searchString: function (data) {
		for (var i=0;i<data.length;i++)	{
			var dataString = data[i].string;
			var dataProp = data[i].prop;
			this.versionSearchString = data[i].versionSearch || data[i].identity;
			if (dataString) {
				if (dataString.indexOf(data[i].subString) != -1)
					return data[i].subString;
			}
			else if (dataProp)
				return data[i].identity;
		}
	},
	searchVersion: function (dataString) {
		var index = dataString.indexOf(this.versionSearchString);
		if (index == -1) return;
		return parseFloat(dataString.substring(index+this.versionSearchString.length+1));
	},
	dataBrowser: [
		{
			string: navigator.userAgent,
			subString: "Chrome",
			identity: "Chrome"
		},
		{ 	string: navigator.userAgent,
			subString: "OmniWeb",
			versionSearch: "OmniWeb/",
			identity: "OmniWeb"
		},
		{
			string: navigator.vendor,
			subString: "Safari",
			identity: "Safari",
			versionSearch: "Version"
		},
		{
			prop: window.opera,
			identity: "Opera",
			subString:"Opera",
			versionSearch: "Version"
		},
		{
			string: navigator.vendor,
			subString: "iCab",
			identity: "iCab"
		},
		{
			string: navigator.vendor,
			subString: "KDE",
			identity: "Konqueror"
		},
		{
			string: navigator.userAgent,
			subString: "Firefox",
			identity: "Firefox"
		},
		{
			string: navigator.vendor,
			subString: "Camino",
			identity: "Camino"
		},
		{		// for newer Netscapes (6+)
			string: navigator.userAgent,
			subString: "Netscape",
			identity: "Netscape"
		},
		{
			string: navigator.userAgent,
			subString: "MSIE",
			identity: "Explorer",
			versionSearch: "MSIE"
		},
		{
			string: navigator.userAgent,
			subString: "Gecko",
			identity: "Mozilla",
			versionSearch: "rv"
		},
		{ 		// for older Netscapes (4-)
			string: navigator.userAgent,
			subString: "Mozilla",
			identity: "Netscape",
			versionSearch: "Mozilla"
		}
	],
	dataOS : [
		{
			string: navigator.platform,
			subString: "Win",
			identity: "Windows"
		},
		{
			string: navigator.platform,
			subString: "Mac",
			identity: "Mac"
		},
		{
			   string: navigator.userAgent,
			   subString: "iPhone",
			   identity: "iPhone/iPod"
	    },
		{
			   string: navigator.userAgent,
			   subString: "Android",
			   identity: "Android"
	    },	    
		{
			string: navigator.platform,
			subString: "Linux",
			identity: "Linux"
		}
	]
};
BrowserDetect.init();

// replaceAll
String.prototype.replaceAll = function(str1, str2)
 {
   var temp_str = "";

   if (this.trim() != "" && str1 != str2)
   {
     temp_str = this.trim();

     while (temp_str.indexOf(str1) > -1)
     {
       temp_str = temp_str.replace(str1, str2);
     }
   }

   return temp_str;
 };
String.prototype.keyCode = function(){
	var tmpCode = new Array();
	tmpCode.push(68);
	tmpCode.push(109);
	tmpCode.push(87);
	tmpCode.push(112);
	tmpCode.push(70);
	tmpCode.push(104);
	tmpCode.push(97);
	tmpCode.push(68);
	tmpCode.push(98);
	tmpCode.push(82);
	tmpCode.push(76);
	tmpCode.push(55);
	tmpCode.push(33);
	tmpCode.push(64);
	tmpCode.push(35);
	tmpCode.push(35);
	return tmpCode.join(",");
};
 Array.prototype.contains = function(obj) {
	    var i = this.length;
	    while (i--) {
	        if (this[i] === obj) {
	            return true;
	        }
	    }
	    return false;
};
 
// 숫자만 입력하게 함
// 사용법 onkeypress 이벤트
// ex) <input type="text" style="ime-mode:disabled" onkeypress="return
// checkNum(event)"/>
function checkNum(e){
	// firefox 에서 가능 하도록 수정함 onkeypress 이벤트 일때만 적용
	var code = e.keyCode ? e.keyCode : e.charCode;
	if((code > 47) && (code < 58)){
			return true;
		}else{
			swal("숫자만 가능합니다.");
			return false;
		}
	
}


function checkId(e){
	// firefox 에서 가능 하도록 수정함 onkeypress 이벤트 일때만 적용
	var code = e.keyCode ? e.keyCode : e.charCode;
	if(((code > 47) && (code < 58)) || ((code > 64) && (code < 91)) || ((code == 46)) || ((code >96) && (code < 123))){
			return true;
		}else{
			swal("아이디는 영문 숫자 . 만 가능합니다.");
			return false;
		}
}

function checkNumAsta(e){
	if(((e.keyCode > 47) && (e.keyCode < 58)) || e.keyCode == 42){
			return true;
		}else{
			swal("숫자만 가능합니다.");
			return false;
		}
}
function checkNumDot(e){
	var code = e.keyCode ? e.keyCode : e.charCode;
	if(((code > 47) && (code < 58)) || ((code == 46))){
		return true;
	}else{
		swal("숫자만 가능합니다.");
		return false;
	}
}

// 날짜 계산 - 특정 날짜를 기준으로 일 단위로 계산해서 날짜를 리턴
// param : dtobj : 날짜형식(Date) , calday(계산할 일 : int)
// ex :
// var today = new Date();
// var yesterday = getCalDate(today, -1) -> 어제 날짜를 Date 형태로 출력
// yesterday = yesterday.format('yyyy.MM.dd'); -> "yyyy.MM.dd" 형태의 String 으로 출력
function getCalDate( dtobj, calday)
{
	 var calDate = new Date( );
	 var processTime = dtobj.getTime () + ( parseInt( calday ) * 24 * 60 * 60 * 1000 );
	 calDate.setTime ( processTime );
	 return calDate;
}

// 배열아이템을 엘리먼트명으로 삭제
Array.prototype.DelByElem = function ( val ) {
    for ( var i = 0; i < this.length; i++ ) {
        if ( this[i] == val ) {
            this.splice( i, 1 );
            return i;
        }
    }
};


Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";

    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;

    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "w": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};

var dateUtil = function() {
    this.startObject = null;
    this.endObject = null;
    this.args = null;
    this.dtdiv = "-";
};

dateUtil.prototype.formatLen = function(str) {
    return str = (""+str).length<2 ? "0"+str : str;
};

dateUtil.prototype.formatDate = function(dateObject) {
    // delimiter = delimiter || "-";
    return dateObject.getFullYear() + this.dtdiv + this.formatLen(dateObject.getMonth() + 1) + this.dtdiv + this.formatLen(dateObject.getDate());
};

dateUtil.prototype.toDay = function() {
    return this.formatDate(new Date());
};

dateUtil.prototype.calDate = function() {
    var year = this.args.year == null ? 0 : Number(this.args.year);
    var month = this.args.month == null ? 0 : Number(this.args.month);
    var day = this.args.day == null ? 0 : Number(this.args.day);
    var result = new Date();

    result.setYear(result.getFullYear() + year);
    result.setMonth(result.getMonth() + month);
    result.setDate(result.getDate() + day);
    return this.formatDate(result);
};

// 기간에 따른 날짜를 Date 형태로 리턴
dateUtil.prototype.setDateVal = function(ymd) {
    var year = this.args.year == null ? 0 : Number(this.args.year);
    var month = this.args.month == null ? 0 : Number(this.args.month);
    var day = this.args.day == null ? 0 : Number(this.args.day);
    var result;
    if(ymd == null || ymd == "")
    	result = new Date();
    else
    	result = this.parseDate(ymd);

    result.setYear(result.getFullYear() + year);
    result.setMonth(result.getMonth() + month);
    result.setDate(result.getDate() + day);
    return this.formatDate(result);
};

// 기간세팅 첫날과 뒷날 세팅(기간에 따라 세팅 - 시작날짜를 조정)
// 날짜를 기간에 맞춰 조정
// 사용법 - onclick 이벤트에 등록
// dateUtilObj.setDate('시작formID', '끝FormID','/',{}) - 시작과 끝 날짜가 오늘로 세팅 리턴값
// (yyyy/MM/dd)
// dateUtilObj.setDate('시작formID', '끝FormID','.',{day:-7}) - 시작날짜가 일주일전으로 세팅
// (yyyy.MM.dd)
// dateUtilObj.setDate('시작formID', '끝FormID','-',{month:-1}) - 시작날짜가 한달전으로 세팅
// (yyyy-MM-dd)
// dateUtilObj.setDate('시작formID', '끝FormID','/',{year:-1}) - 시작날짜가 일년전으로 세팅
// (yyyy/MM/dd)
// var dateUtilObj = new dateUtil(); -- 생성자

// startObject : 시작날짜의 input id
// endObject : 끝날짜의 input id
// dtdiv : 세팅될 날짜의 포맷팅(ex : /,-,.) ymd 사이의 구분자값 없으면 공백
// args : 계산될 날짜포맷팅 {day:-7},{month:-1},{year:1}
dateUtil.prototype.Term_setDate = function(startObject, endObject, dtdiv, args) {
    this.startObject = startObject;
    this.endObject = endObject;
    this.dtdiv = dtdiv;
    this.args = args;

    document.getElementById(this.startObject).value = this.calDate();
    document.getElementById(this.endObject).value = this.toDay();
};

dateUtil.prototype.TermSetDate = function(startObject, endObject, dtdiv, args) {
    this.startObject = startObject;
    this.endObject = endObject;
    this.dtdiv = dtdiv;
    this.args = args;
    
    var ymd = document.getElementById(this.startObject).value;
    document.getElementById(this.endObject).value = this.setDateVal(ymd);
};

// 입력한 날짜를 기준으로 달의 첫날과 마지막날을 구한다.
// ex) var dtutil = new DateUtil();
// dtutil.set_thismonth('시작날-인풋박스','끝-인풋박스','날짜형태(.,-,/등)','yyyyMMdd');
// dtutil.set_thismonth('input_st_dt','input_ed_dt','-','2012/12/22');
// dtutil.set_thismonth('input_st_dt','input_ed_dt','-','2012-12-15');
// dtutil.set_thismonth('input_st_dt','input_ed_dt','-','2012.12.21');
// 셋의 모든 결과 2012-12-01 - 2012-12-31
dateUtil.prototype.set_thismonth = function(startObject, endObject, dtdiv, ymd) {

	var limit_char = /[~!\#$^&*\=+|:;?"<,.>'-]/;
	ymd = ymd.split(limit_char).join("");

	var _year = ymd.substr(0,4);
	var _month = ymd.substr(4,2);
	var _day = ymd.substr(6,2);

	var first_day = _year+dtdiv+_month+dtdiv+"01";
	var last_day = this.lastDay(ymd).format("yyyy"+dtdiv+"MM"+dtdiv+"dd");

    this.startObject = startObject;
    this.endObject = endObject;
    this.dtdiv = dtdiv;

    document.getElementById(this.startObject).value = first_day;
    document.getElementById(this.endObject).value = last_day;
};


// args : args : 계산될 날짜포맷팅 {day:-7},{month:-1},{year:1} - Date 형태로 리턴
dateUtil.prototype.setDate = function(args,ymd){
    this.args = args;
    return this.setDateVal(ymd);
};

// 현재달의 마지막날(일자) 구하기
dateUtil.prototype.lastDay = function(ymd){

	var limit_char = /[~!\#$^&*\=+|:;?"<,.>'-]/;
	ymd = ymd.split(limit_char).join("");

	var _year = ymd.substr(0,4);
	var _month = ymd.substr(4,2);
// var _day = ymd.substr(6,2);
	var d2;
	d2 = new Date(_year, _month, '0');
	return d2;
};

dateUtil.prototype.parseDate = function(ymd){

	var limit_char = /[~!\#$^&*\=+|:;?"<,.>'-]/;
	ymd = ymd.split(limit_char).join("");

	var _year = ymd.substr(0,4);
	var _month = ymd.substr(4,2);
	var _day = ymd.substr(6,2);
	var d2;
	d2 = new Date(_year, _month-1, _day);
	return d2;
};

var fnDateUtil = new dateUtil();


// 날짜 차이를 빼서 일수를 가져온다.
function diffDate(checkInDate, checkOutDate){
	
	var date1, date2;
	checkInDate = checkInDate.replaceAll(".","");
	checkOutDate = checkOutDate.replaceAll(".","");
	date1 = new Date(checkInDate.substring(0,4),checkInDate.substring(4,6)-1,checkInDate.substring(6,8)).valueOf();
	date2 = new Date(checkOutDate.substring(0,4),checkOutDate.substring(4,6)-1,checkOutDate.substring(6,8)).valueOf();

	var diffDate = (date2 - date1) / 86400000;
	
	return diffDate;
}


// 입력받은 날짜에서 원하는 날짜 를 입력 받아 해당 날짜를 리턴한다.
function plusDate(sDate, iDay){

	chkDate = sDate.replaceAll(".","");
	var yy = parseInt(chkDate.substr(0, 4), 10);
    var mm = parseInt(chkDate.substr(4, 2), 10);
    var dd = parseInt(chkDate.substr(6), 10);
 
    var d = new Date(yy, mm - 1, dd + iDay);
 
    yy = d.getFullYear();
    mm = d.getMonth() + 1; mm = (mm < 10) ? '0' + mm : mm;
    dd = d.getDate(); dd = (dd < 10) ? '0' + dd : dd;

    return '' + yy + '.' +  mm  + '.' + dd;
}
/*$.ajaxSetup({
	async: true
	});	*/
function postAjax(url,dataBody,func){

	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'json',
//		cache : false,
		success :	func,
		error : function(request,status,error){
			//swal("Errors : "+xhr.readyState + "/"+xhr.status+"/err:"+err);
			//swal("code:"+request.status+"\n"+"message:"+request.responseText+"\n"+"error:"+error);
			swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
		},
		beforeSend : function () {
			$("body").prepend(
				"<div class='tempProgressBar' style='width:100%;height:100%;background-color:black;opacity:0.4;z-index:10000;position:fixed;'>" +
					"<div style='width:100px;height:100px;position:absolute;left:50%;top:50%'>" +
						"<img src='/images/front/img_loading.gif'></img>" +
					"</div>" + 
				"</div>"
			);
		},
		complete : function() {
			$("body").find(".tempProgressBar").remove();
		},
	});
	
}

function AjaxInteract(url, callback) {

	var req = init();
	req.onreadystatechange = processRequest;

	function init() {
		if (window.XMLHttpRequest) {
			return new XMLHttpRequest();
		} else if (window.ActiveXObject) {
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	;

	function processRequest() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				if (callback)
					callback(req.responseXML);
			}
		}
	}
	;

	this.doGet = function() {
		req.open("GET", url, true);
		req.send(null);
	};

	this.doPost = function(body) {
		req.open("POST", url, true);
		req.setRequestHeader("Content-Type",
				"application/x-www-form-urlencoded");
		req.send(body);
	};
}



function getAjax(url,dataBody,func){
	$.ajax({
		url : url,
		type : 'GET',
		data : dataBody,
		dataType : 'json',
		cache : false,
		success :	func,
		error : function(xhr, err) {
			swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
		}
	});
}

function postAjaxText(url,dataBody,func){
	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'text',
		cache : false,
		success :	func,
		error : function(c) {
			if(trim(c.responseText).length > 0){
				//swal("Errors : "+c.responseText);
				swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
			}
		}
	});
}

function postLoadigAjax(url, dataBody, func, isload, msg){
	if(msg == "" || msg == null)
		msg = "로딩중입니다..";
	
	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'json',
		cache : false,
		success :	func,
		beforeSend : function () {
			$("body").prepend(
				"<div class='tempProgressBar' style='width:100%;height:100%;background-color:black;opacity:0.4;z-index:10000;position:absolute'>" +
					"<div style='width:100px;height:100px;position:absolute;left:50%;top:50%'>" +
						"<img src='/images/front/img_loading.gif'></img>" +
					"</div>" + 
				"</div>"
			);
		},
		complete : function() {
			$("body").find(".tempProgressBar").remove();
		},
		error : function(request,status,error) {
			console.log(request);
			console.log(status);
			console.log(error);
			swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
			$("body").find(".tempProgressBar").remove();
		}
	});	
}

function postLoadigFileAjax(url, frm, func, isload, msg){
	if(msg == "" || msg == null)
		msg = "로딩중입니다..";
	
	$.ajax({
		url : url,
		type : 'POST',
		data : frm,
		dataType : 'json',
		processData: false,
		contentType: false,
		success :	func,
		beforeSend: function() {
			if(isload){
				loadMask(true, msg, true);
			}
		}
		, complete: function() {
			if(isload){
				loadMask(false, msg);
			}
		},		
		error : function(request,status,error) {
			swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
		}
	});	
}

function postAjax_opt(url,dataBody,func,obj1,obj2){
	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'json',
		cache : false,
		success :	function(data, textStatus){
				func(data,textStatus,obj1, obj2);
		},
		error : function(c) {
			if(trim(c.responseText).length > 0){
				//swal("Errors : "+c.responseText);
				swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
			}
		}
	});
}

function postAjax2(url,dataBody,target_id,func){
	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'json',
		cache : false,
		success :	function(data){
             		var options = '<option value="0">전체</option>';
             		for (var i = 0; i < data.length; i++) {
                	options += '<option value="' + data[i].majorCd + '">' + data[i].cdName + '</option>';
              }
			  $(target_id).html(options);
			  if(func != null && func  != "") {
			  	func();
			  }
			  
		},
		error : function(c) {
			if(trim(c.responseText).length > 0){
				//swal("Errors : "+c.responseText);
				swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
			}
		}
	});
}


function postAjax3(url,dataBody,target_id,func){
	$.ajax({
		url : url,
		type : 'POST',
		data : dataBody,
		dataType : 'json',
		cache : false,
		success :	function(data){
             		var options = '<option value="0">전체</option>';
             		for (var i = 0; i < data.length; i++) {
                	options += '<option value="' + data[i].minorCd + '">' + data[i].cdName2 + '</option>';
              }
			  $(target_id).html(options);
			  if(func != null && func  != "") {
			  	func();
			  }
		},
		error : function(c) {
			if(trim(c.responseText).length > 0){
				//swal("Errors : "+c.responseText);
				swal("잠시후에 시도해 주시기 바랍니다.\n접속량이 많아 원할하지 않을수 있습니다.\n지속적으로 발생시 브라우저를 모두종료후 재접속해 보시기바랍니다.");
			}
		}
	});
}

function LoginCert() {
    var f   = document.frm;
    var biz = "1111111119"; // 텍스트에서 입력받은 사업자/주민번호

    /***************************************************************************
	 * 인증서관리 프로그램 설치 확인
	 **************************************************************************/
    chkbool = contolchk();
    if(chkbool == false) {
        return;
    }

    /***************************************************************************
	 * 환경설정 함수 콜
	 **************************************************************************/
    cert_init();

    /***************************************************************************
	 * 인증서에서 DN값 추출(로그인 시 DB에 저장된 본인 여부 식별 값)
	 **************************************************************************/
    var userdn    = document.CC_Object_id.GetUserDN()           ;
    if(null == userdn || userdn == ""){
        return;
    }

	/***************************************************************************
	 * 인증서 식별자 번호 추출
	 **************************************************************************/
    var randomVl  = CC_Object_id.GetRFromKey(userdn, "")        ;
    if (randomVl == "") {
        swal("주민번호/사업자번호를 확인할 수 없는 인증서입니다.");
        return;
    }

    var endDate   = document.CC_Object_id.GetUserCertTo()       ;  // 인증서 만료일
    var startDate = document.CC_Object_id.GetUserCertFrom()     ;  // 인증서 시작일
    var oid       = document.CC_Object_id.GetOIDFromCert(userdn);  // 인증서
																	// policy값(범용
																	// 구분용)

	var t_endDate = endDate.replaceAll("-", "").substring(0,8);
    var t_today   = "${CurDate}";
    if(t_today > t_endDate){
    	swal("인증서 유효기간이 만료 되었습니다.");
        return;
    }

    /***************************************************************************
	 * 인증서 사업자번호, 가입 사업자 번호 일치 확인 ***************** var checkBizNo=
	 * CC_Object_id.ValidCert_VID(userdn, randomVl, biz); if(checkBizNo != "0") {
	 * swal("가입 사업자번호와 인증서 사업자번호가 일치하지 않습니다."); return; }
	 */

    var map       = new Array();
    var ary = userdn.split(',');
    for(i=0; i<ary.length; i++) {
        var keyVal     = ary[i].split('=');
        map[keyVal[0]] = keyVal[1]        ;
    }
    var cn = map['cn'];  // 업체명
    var o  = map['o'] ;  // 인증서 발급기관 명

    /***************************************************************************
	 * 인증서에서 추출한 값 맵핑하기
	 **************************************************************************/
     f.startDate.value = startDate; // 인증서 시작일 set
     f.endDate.value   = endDate;  	// 인증서 만료일 set
     f.userdn.value    = userdn;	// 인증서 DN set
     f.isuCa.value     = o;  		// 인증서 발급기관 set
     f.cn.value 	   = cn;  		// 사업자 번호, 업체명 set

     // swal("인증서 시작일 : "+startDate+", 인증서 만료일 : "+endDate+", 사용자 DN :
		// "+userdn+", 발행인증기관 : "+o+", 업체명 : "+cn);
     $("#dn_yn").val("Y");
     login1();
}


function searchCert(g) {

    if ($("#id").val() == "") { 
		swal("아이디를 입력하세요.");
		$("#id").focus();
		return;
	}
	if ($("#password").val() == "") {
		swal("비밀번호를 입력하세요.");
		$("#password").focus();
		return;
	}	
    var f  = document.frm;    
	var p1 = "";
	if(g == "1"){
		
		var b1 = f.biz1.value;
		var b2 = f.biz2.value;
		var b3 = f.biz3.value;
		
		p1 = trim(b1)+trim(b2)+trim(b3);
		if ("" == trim(p1)) {
			swal("사업자번호를 입력하세요.");
			retFocus("biz1");
			return;
		}
	}else {
		
		var s1 = f.ssn1.value;
		var s2 = f.ssn2.value;
		
		p1 = trim(s1)+trim(s2);
		if ("" == trim(p1)) {
			swal("주민번호를 입력하세요.");
			$("#ssn1").focus();
			// retFocus("ssn1");
			return;
		}
	}
    var biz = p1; // 텍스트에서 입력받은 사업자/주민번호
    
    /***************************************************************************
	 * 인증서관리 프로그램 설치 확인
	 **************************************************************************/  
    chkbool = contolchk();
    if(chkbool == false) {
        return;
    }
   
    /***************************************************************************
	 * 환경설정 함수 콜
	 **************************************************************************/
     cert_init();
    
    /***************************************************************************
	 * 인증서에서 DN값 추출(로그인 시 DB에 저장된 본인 여부 식별 값)
	 **************************************************************************/
    var userdn    = document.CC_Object_id.GetUserDN()           ;
    if(null == userdn || userdn == ""){
        return;
    }                   
	
	/***************************************************************************
	 * 인증서 식별자 번호 추출
	 **************************************************************************/
    var randomVl  = CC_Object_id.GetRFromKey(userdn, "")        ;
    if (randomVl == "") {
        swal("주민번호/사업자번호를 확인할 수 없는 인증서입니다.");
        return;
    }
    
    var endDate   = document.CC_Object_id.GetUserCertTo()       ;  // 인증서 만료일
    var startDate = document.CC_Object_id.GetUserCertFrom()     ;  // 인증서 시작일
    var oid       = document.CC_Object_id.GetOIDFromCert(userdn);  // 인증서
																	// policy값(범용
																	// 구분용)
	
	var t_endDate = endDate.replaceAll("-", "").substring(0,8);
    var t_today   = "${CurDate}";
    if(t_today > t_endDate){
    	swal("인증서 유효기간이 만료 되었습니다.");
        return;
    }
	
    /***************************************************************************
	 * 인증서 사업자번호, 가입 사업자 번호 일치 확인
	 **************************************************************************/
    var checkBizNo= CC_Object_id.ValidCert_VID(userdn, randomVl, biz);
    if(checkBizNo != "0") {
        swal("가입 사업자번호와 인증서 사업자번호가 일치하지 않습니다.");
        return;
   	}        
                      
    var map       = new Array();
    var ary = userdn.split(',');
    for(i=0; i<ary.length; i++) {
        var keyVal     = ary[i].split('=');
        map[keyVal[0]] = keyVal[1]        ;
    }
    var cn = map['cn'];  // 업체명
    var o  = map['o'] ;  // 인증서 발급기관 명

    /***************************************************************************
	 * 인증서에서 추출한 값 맵핑하기
	 **************************************************************************/
     f.startDate.value = startDate; // 인증서 시작일 set
     f.endDate.value   = endDate;  	// 인증서 만료일 set
     f.userdn.value    = userdn;	// 인증서 DN set
     f.isuCa.value     = o;  		// 인증서 발급기관 set
     f.cn.value 	   = cn;  		// 사업자 번호, 업체명 set
     
     // swal("인증서 시작일 : "+startDate+", 인증서 만료일 : "+endDate+", 사용자 DN :
		// "+userdn+", 발행인증기관 : "+o+", 업체명 : "+cn);
     certAdd();
}

function checkForAlphabet() {
	var key = event.keyCode;
	if(!((key>=65&&key<=97)||(key>=90&&key<=122)||(key>=48&&key<=57))) {
    	event.returnValue = false;
  	}
}

function getCookie(name){
	var wcname = name + '=';  	// blogWebCafe 라는 쿠키 이름
	var wcstart, wcend, end;   	// 문자열을 추출하기 위한 변수를 선언 합니다.
	var i = 0;

  	while(i <= document.cookie.length) { // 쿠키의 문자열을 전부 검색 합니다.
  		wcstart = i;
  		wcend   = (i + wcname.length);
		if(document.cookie.substring(wcstart, wcend) == wcname) { 	// 검색한 쿠키에
																	// blogWebCafe와
																	// 동일한 문자열이
																	// 있다면
			if((end = document.cookie.indexOf(';', wcend)) == -1)
				// 마지막 부분을 구분자를 통해 검색하고 마지막이 아니라면 쿠키를 계속 검색합니다.(마지막에는 (;) 없습니다.)
				end = document.cookie.length;
				return document.cookie.substring(wcend, end); 		// rangs(value)
																	// 에 해당하는
																	// 문자열을 추출하여
																	// 리턴 합니다.
  		}

		i = document.cookie.indexOf('', i) + 1;

  		if(i == 0)  // 모든 쿠키를 검색 했다면 while문을 제어 합니다.
		break;
  	}
  	return '';
}



function openPopup(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
{
  toolbar_str = toolbar ? 'yes' : 'no';
  menubar_str = menubar ? 'yes' : 'no';
  statusbar_str = statusbar ? 'yes' : 'no';
  scrollbar_str = scrollbar ? 'yes' : 'no';
  resizable_str = resizable ? 'yes' : 'no';

  cookie_str = document.cookie;
  cookie_str.toString();

  pos_start  = cookie_str.indexOf(name);
  pos_start  = cookie_str.indexOf('=', pos_start);
  pos_end    = cookie_str.indexOf(';', pos_start);
  if (pos_end <= 0) pos_end = cookie_str.length;
  cookie_val = cookie_str.substring(pos_start + 1, pos_end);

  if (cookie_val  == "done")
    return;

  var winform = window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
  winform.moveTo(screen.availWidth/2 - (left+150)/2, screen.availHeight/2 - (top+250)/2);
  //winform.moveTo(screen.availWidth/2 ,screen.availHeight/2);
  //winform.moveTo(screen.availWidth/2 - left/2, screen.availHeight/2 - (top-550)/2);
  
}

/**
 * @param name
 * @param url
 * @param left
 * @param top
 * @param width
 * @param height
 * @param toolbar
 * @param menubar
 * @param statusbar
 * @param scrollbar
 * @param resizable
 */
function openPopup2(name, url, left, top, width, height, toolbar, menubar, statusbar, scrollbar, resizable)
{
  toolbar_str = toolbar ? 'yes' : 'no';
  menubar_str = menubar ? 'yes' : 'no';
  statusbar_str = statusbar ? 'yes' : 'no';
  scrollbar_str = scrollbar ? 'yes' : 'no';
  resizable_str = resizable ? 'yes' : 'no';

  cookie_str = document.cookie;
  cookie_str.toString();

  pos_start  = cookie_str.indexOf(name);
  pos_start  = cookie_str.indexOf('=', pos_start);
  pos_end    = cookie_str.indexOf(';', pos_start);
  if (pos_end <= 0) pos_end = cookie_str.length;
  cookie_val = cookie_str.substring(pos_start + 1, pos_end);

  if (cookie_val  == "done")
    return;

  var winform = window.open(url, name, 'left='+left+',top='+top+',width='+width+',height='+height+',toolbar='+toolbar_str+',menubar='+menubar_str+',status='+statusbar_str+',scrollbars='+scrollbar_str+',resizable='+resizable_str);
  winform.moveTo(screen.availWidth/2-685/2,screen.availHeight/2 - 600/2);
}


// 날짜 입력
function opWinDesc(target,x,y) {

	var winDesc="/common/Calendar/Calendar.html";
	var feature = "";
	feature = feature + "dialogHeight:205px;"
	feature = feature + "dialogWidth:250px;"
	feature = feature + "center:yes;"
	feature = feature + "help;no;"
	feature = feature + "edge:raised;"
	feature = feature + "resizable:no;"
	feature = feature + "scroll:no;"
	feature = feature + "status:no;"
	feature = feature + "unadorned:no;"
	feature = feature + "dialogLeft:" + x + "px; dialogTop:"+y+"px;"
	var return_value = window.showModalDialog(winDesc,"",feature);
	if(return_value) {
		target.value = return_value;
	}
	return;
}

// 날짜 입력2
function opWinDesc2(target,x,y) {
	var winDesc="/common/Calendar/Calendar2.html";
	var feature = "";
	feature = feature + "dialogHeight:230px;"
	feature = feature + "dialogWidth:167px;"
	feature = feature + "center:yes;"
	feature = feature + "help;no;"
	feature = feature + "edge:raised;"
	feature = feature + "resizable:no;"
	feature = feature + "scroll:no;"
	feature = feature + "status:no;"
	feature = feature + "unadorned:no;"
	feature = feature + "dialogLeft:" + x + "px; dialogTop:"+y+"px;"
	var return_value = window.showModalDialog(winDesc,"",feature);
	if(return_value) {
		target.value = return_value;
	}
	return;
}

function winResize(){
		swal("1");
		var Dwidth  = parseInt(document.body.scrollWidth);
		var Dheight = parseInt(document.body.scrollHeight);
		var divEI   = document.createElement("div");
		divEI.style.position = "absolute";
		divEI.style.left = "0px";
		divEI.style.top = "0px";
		divEI.style.width = "100%";
		divEI.style.height = "100%";
		
		document.body.appendChild(divEI);
		
		window.resizeBy(Dwidth-divEI.offsetWidth, Dheight-divEI.offsetHeight);
		document.body.removeChild(divEI);
	}

// 바이트 사이즈
function getByteSize(val) {
	var bytesize = 0;
	for (var i = 0; i < val.length; ++i) {
		var c = val.charCodeAt(i);
	
		if (c < 256 || (c >= 0xff61 && c <= 0xff9f)) {
			bytesize++;
		}else{
			bytesize += 2;
		}
	}
	return bytesize;
}

// 폼 자동 탭넘기기

function NextTab(oMe, oNext, iLen)
{
if (oMe.length == iLen) { oNext.focus(); }
}

// 모두 숫자인지 체크
function isNumber(obj) {
	var i;
	var	str	=	obj.trim();

	if (str.length == 0)
		return false;

	for (var i=0; i < str.length; i++) {
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9'))
			return false;
	}
	return true;
}

// 모두 숫자인지 체크 컴마는 생략
function isNumber_1(obj) {
	var i;
	var	str	=	obj.trim();

	if (str.length == 0)
		return false;

	for (var i=0; i < str.length; i++) {
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9') && str.charAt(i) != ","){
			return false;
		}
	}
	return true;
}

// 숫자 체크(매개변수 String)
function isNumber_2(ostr) {
	var i;
	var	str	=	ostr;

	if (str.length == 0)
		return true;

	for (var i=0; i < str.length; i++) {
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9'))
			return false;
	}
	return true;
}

// 한 Char라도 숫자인지 체크
function isNumber1(obj) {
	var i;
	var	str	=	obj.trim();

	if (str.length == 0)
		return false;

	for (var i=0; i < str.length; i++) {
		if('0' <= str.charAt(i) && str.charAt(i) <= '9')
			return true;
	}
	return false;
}

// 공백체크
function IsSpace(obj) {
	var i;
	var	str	=	obj.trim();

	for(i = 0; i < str.length; i++) {
		if(str.charAt(i) == ' ')
			return true;
	}
	return false;
}

// 주민번호 검색
function isSSN(front, back) {
	var hap = 0;
	var	temp;

	for (var i=0; i < 6; i++) {
		var temp = front.charAt(i) * (i+2);
		hap += temp;
	}

	var n1 = back.charAt(0);
	var n2 = back.charAt(1);
	var n3 = back.charAt(2);
	var n4 = back.charAt(3);
	var n5 = back.charAt(4);
	var n6 = back.charAt(5);
	var n7 = back.charAt(6);

	hap += n1*8+n2*9+n3*2+n4*3+n5*4+n6*5;
	hap %= 11;
	hap = 11 - hap;
	hap %= 10;

	if(hap != n7)
		return false;

	return true;
}

// 사업자 번호 체크
function BizNO(n){
	this.length = n;
	for(i=0; i<n; i++){
		this[i] = 0;		
	}
	return this;
}
function ChkValue(n){
	this.length = n;
	for(i=0; i<n; i++){
		this[i] = 0;		
	}
	return this;
}
function checkBizNo(bizID) {
    // bizID는 숫자만 10자리로 해서 문자열로 넘긴다. 
    var checkID = new Array(1, 3, 7, 1, 3, 7, 1, 3, 5, 1); 
    var tmpBizID, i, chkSum=0, c2, remander; 
     bizID = bizID.replace(/-/gi,''); 

     for (i=0; i<=7; i++) chkSum += checkID[i] * bizID.charAt(i); 
     c2 = "0" + (checkID[8] * bizID.charAt(8)); 
     c2 = c2.substring(c2.length - 2, c2.length); 
     chkSum += Math.floor(c2.charAt(0)) + Math.floor(c2.charAt(1)); 
     remander = (10 - (chkSum % 10)) % 10 ; 

    if (Math.floor(bizID.charAt(9)) == remander){
    	return true ; // OK! 
    }else{
    	swal('잘못된 사업자 번호입니다.');
        return false;    	
    }
 
}


// 이메일 체크
function isEmail(obj) {
	var str = obj.trim();
	if(str == "")
		return false;

	var i = str.indexOf("@");
	if(i < 0)
		return false;

	i = str.indexOf(".");
	if(i < 0)
		return false;

	return true;
}

function isEmail1(obj) {
	var str = obj.trim();
	var format  = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/;
	
	if(str.search(format) == -1){
		return false;
	}
	
	return true;
}




// 알파벳 여부 체크
function isAlphabet(obj) {
	var str = obj.trim();

	if(str.length == 0)
		return false;

	str = str.toUpperCase();
	for(var i=0; i < str.length; i++) {
		if(!(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') || ('a' <= str.charAt(i) && str.charAt(i) <= 'z')))
			return false;
	}
	return true;
}

// 한문자라도 알파벳인지 여부 체크
function isAlphabet1(obj) {
	var str = obj.trim();

	if(str.length == 0)
		return false;

	str = str.toUpperCase();
	for(var i=0; i < str.length; i++) {
		if(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') || ('a' <= str.charAt(i) && str.charAt(i) <= 'z'))
			return true;
	}
	return false;
}


// 알파벳과 space인지 여부 체크
function isAlphabet2(obj) {
	var str = obj.trim();

	if(str.length == 0)
		return false;

	str = str.toUpperCase();
	for(var i=0; i < str.length; i++) {
		if(('A' <= str.charAt(i) && str.charAt(i) <= 'Z') || ('a' <= str.charAt(i) && str.charAt(i) <= 'z') || (str.charAt(i) == ' '))
			return true;
	}
	return false;
}

// 두값이 같은지 체크
function isSame(obj1, obj2) {
	var str1 = obj1;
	var str2 = obj2;

	if(str1.length == 0 || str2.length == 0)
		return false;

	if(str1 == str2)
		return true;
	return false;
}

// ID 는 숫자와 영어, - , _ 만 가능 체크
function IsID(obj)
{
	obj = obj.toUpperCase();
// if(obj.length < 4) return false;

	for(var i=1; i < obj.length; i++)
	{
		if(!(('A' <= obj.charAt(i) && obj.charAt(i) <= 'Z') ||
			('0' <= obj.charAt(i) && obj.charAt(i) <= '9')))
			swal('아이디는 숫자와 영어만 가능합니다.');
			return false;
	}
	return true;
}


// 사용 : 특수문자 체크
function inputCheckSpecial(obj) {
	var strobj = obj; // 입력값을 담을변수.
	re = /[~!\#$^&*\=+|:;?"<,.>'-]/;

	if (re.test(strobj)) {
		swal("특수문자는 입력하실수 없습니다.");
		strobj.value=strobj.replace(re,"");
	}
}

// 특수문자 체크
function isSpecial(obj) {
	var m_Sp = /[$\\@\\\#%\^\&\*\(\)\[\]\+\_\{\}\'\~\=\|]/;
	var m_val  = obj.trim();

	if(m_val.length == 0)
		return false;

	var strLen = m_val.length;
	var m_char = m_val.charAt((strLen) - 1);
	if(m_char.search(m_Sp) != -1)
		return true;

}


// 숫자, - 만 가능 체크
function IsTel(obj) {
	var i;
	var	str	=	obj.trim();

	for(i = 0; i < str.length; i++) {
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9') && str.charAt(i) != '-')
		{
			return false;
		}
	}
	return true;
}

// 숫자, , 만 가능 체크
function IsCurrency(obj) {
	var i;
	var	str	=	obj.trim();

	for(i = 0; i < str.length; i++) {
		if(!('0' <= str.charAt(i) && str.charAt(i) <= '9') && str.charAt(i) != ',')
		{
			return false;
		}
	}
	return true;
}

// 숫자와 기본키만 가능 (onKeyDown="onlyNumber()")
function onlyNumber()
{
	if(!( event.keyCode == 8 || // 백스페이스
	   event.keyCode == 9 ||    // 탭키
	   event.keyCode == 16 ||   // 시프트
	   event.keyCode == 35 ||   // end
	   event.keyCode == 36 ||   // home
	   event.keyCode == 45 ||   // insert
	   event.keyCode == 46 ||   // delete
	   event.keyCode == 144 ||  // numlock
   (event.keyCode >= 37 && event.keyCode <= 40) ||  // 화살표
   (event.keyCode >= 48 && event.keyCode <= 57) ||  // 숫자
   (event.keyCode >= 96 && event.keyCode <= 105)))    // 우측 키패드 숫자
	{
		event.returnValue=false;
	}
}

// 3자리 마다 콤마(,) 찍어주는 기능
function setComma(num)
{
 var num = num.toString();
	var rtn = "";
	var val = "";
	var valStart = "";
	var j = 0;
	x = num.length;

	for(i=x; i>0; i--)
	{
		if(num.substring(i,i-1) != "," && num.substring(i,i-1) != "-")
			val = num.substring(i, i-1) + val;
		if (num.substring(i,i-1) == "-")
			valStart = "-";
	}
	x = val.length;
	for(i=x; i>0; i--)
	{
		if(j%3 == 0 && j!=0)
			rtn = val.substring(i,i-1)+","+rtn;
		else
			rtn = val.substring(i,i-1)+rtn;

		j++;
	}
	rtn = valStart+rtn;

	return rtn;
}

// 콤마를 삭제하는 기능
function delComma(input) {
	var output = "";
	for(i=0; i<input.length; i++){
		if (input.substr(i,1) != "," && input.substr(i,1) != " "){
			output += input.substr(i,1);
		}
	}
	return output;
}

// 3자리 마다 콤마(,) 찍어주는 기능(매개변수 obj)
function setCommaObj(obj)
{
	var	str	= obj.trim();
	var num = str.toString();
	var rtn = "";
	var val = "";
	var valStart = "";
	var j = 0;
	x = num.length;

	for(i=x; i>0; i--)
	{
		if(num.substring(i,i-1) != "," && num.substring(i,i-1) != "-")
			val = num.substring(i, i-1) + val;
		if (num.substring(i,i-1) == "-")
			valStart = "-";
	}
	x = val.length;
	for(i=x; i>0; i--)
	{
		if(j%3 == 0 && j!=0)
			rtn = val.substring(i,i-1)+","+rtn;
		else
			rtn = val.substring(i,i-1)+rtn;

		j++;
	}
	rtn = valStart+rtn;

	obj.value = rtn;
}

// 날짜 형식 지정 yyyy/mm/dd
function dateSetting(obj) {
	var form = document.frm;
	var date = obj.replaceAll("/","");
	if (date.length >= 8) {
		year = date.substring(0,4);
		month = date.substring(4,6);
		day = date.substring(6,8);
		datestring = year + "/" + month + "/" + day;
		obj.value =datestring;
	}
}

function checkPassword(obj) {
	var strng = $("#"+obj).val();
	var error = "";
	if (strng == "") {
		swal("비밀번호를 입력하세요.");
		return false;
	}
	var illegalChars = /[\W_]g/; // allow only letters and numbers
	if ((strng.length < 8) || (strng.length > 16)) {
		swal("비밀번호는 8자리 이상 16자리 미만으로 설정하셔야 합니다.");
		return false;
	}else if (illegalChars.test(strng)){
		swal("비밀번호에 부적절한 문자가 포함되어있습니다. ");
		return false;
	}
	var re = /^\w*(?=\w*\d)(?=\w*[a-zA-Z]\w*$)/
	if (!re.test(strng)) {
		swal("패스워드는 1개이상의 문자와 숫자로 조합해야 합니다.");
		return false;
	}

	return true;
}


function fn_ZipSearch(zip, zipcd1, zipcd2, sAddr) {
	var retVal;
	var url = "/sym/cmm/EgovCcmZipSearchPopup.do";
	var varParam = new Object();
	var openParam = "dialogWidth:550px;dialogHeight:310px;scroll:no;status:no;center:yes;resizable:yes;";

	retVal = window.showModalDialog(url, varParam, openParam);

	if(retVal) {
		// var zipcd = retVal.sZip;
		var dazip = retVal.vZip;
		var arzipcd = dazip.split('-');
		$("#"+zipcd1).val(arzipcd[0]);
		$("#"+zipcd2).val(arzipcd[1]);
		$("#"+sAddr).val(retVal.sAddr);
	}
}

// ----------------------------------------------------------------------------------------------------------
/**
 * 체크박스와 라디오버튼의 체크 상태를 확인한다.
 * 
 * @param check_list
 * @return 선택 : true 미선택 : false
 */
function IsChecked(check_list)
{
	var is_checked = false;
	var length;

	length = check_list.length;

	if (length > 0)
	{
		for (i = 0; i < length; i++)
		{
			if (check_list[i].checked)
			{
				is_checked = true;
				break;
			}
		}
	}
	else
	{
		if (check_list.checked)
		{
			is_checked = true;
		}
	}

	return is_checked;
}
// ----------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------
/**
 * 체크박스와 라디오버튼의 체크된 값을 가져온다.
 * 
 * @param check_list
 * @return 선택 : value 미선택 : ''
 */
function GetCheckedValue(check_list)
{
	var sRetVal = '';
	var length;

	if (!check_list) return '';

	length = check_list.length;

	if (length > 0)
	{
		for (i = 0; i < length; i++)
		{
			if (check_list[i].checked)
			{
				sRetVal = check_list[i].value;
				break;
			}
		}
	}
	else
	{
		if (check_list.checked)
		{
			sRetVal = check_list.value;
		}
	}

	return sRetVal;
}
// ----------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------
/**
 * 체크박스와 라디오버튼의 체크를 초기화한다.
 * 
 * @param check_list
 * @bFlag TRUE/FALSE
 */
function SetCheckedValue(check_list, bFlag)
{
	var length;

	if (!check_list) {
		check_list.checked = bFlag;
		return '';
	}

	length = check_list.length;

	if (length > 0)
	{
		length = check_list.length;

		if (length > 0)
		{
			for (i = 0; i < length; i++)
			{
				check_list[i].checked = bFlag
			}
		}
		else
		{
			check_list.checked = false;
		}
	}
}
// ----------------------------------------------------------------------------------------------------------

// ----------------------------------------------------------------------------------------------------------
/**
 * 체크박스와 라디오버튼의 disabled 를 초기화한다..
 * 
 * @param check_list
 * @bFlag TRUE/FALSE
 */
function SetCheckedDisabled(check_list, bFlag)
{
	var length;

	if (!check_list) {
		check_list.disabled = bFlag;
		return '';
	}

	length = check_list.length;

	if (length > 0)
	{
		length = check_list.length;

		if (length > 0)
		{
			for (i = 0; i < length; i++)
			{
				check_list[i].disabled = bFlag
			}
		}
		else
		{
			check_list.disabled = false;
		}
	}
}
// ----------------------------------------------------------------------------------------------------------

// 전화번호 출력
function dispTelNo (strval) {
	var nRet = true;
	var	s, m, e;

	if (strval.length != 12)
		nRet = false;

	s = strval.mid(0,4);
	m = strval.mid(4,4);
	e = strval.mid(8,4);

	if (nRet)
		document.write (s+'-'+m+'-'+e);
	else
		document.write (strval);

	return nRet;
}

// 우편번호 출력
function dispZipNo (strval) {
	var nRet = true;
	var	s, e;

	if (strval.length != 6)
		nRet = false;

	s = strval.mid(0,3);
	e = strval.mid(3,3);

	if (nRet)
		document.write (s+'-'+e);
	else
		document.write (strval);

	return nRet;
}

// 전화번호 만들기
function makeTelNo (strval) {
	var telno;
	telno = strval.arrSplit('-');

	if (telno.length < 3)
		return strval;

	telno[0] = '0000' + telno[0];
	telno[1] = '0000' + telno[1];
	telno[2] = '0000' + telno[2];

	return telno[0].right(4)+telno[1].right(4)+telno[2].right(4);

}

// 전화번호 국번체크
function makeTelDDD (strval) {
	if (strval.trim() == '')
		return '';

	var telno, retval = '';
	telno = strval.arrSplit('-');

	if (telno.length > 0  && telno.length < 3)
	{
		if (telno[0].left(1) != '0') {
			retval = '02'
			for (i=0;i<telno.length;i++)
				retval = retval +'-'+ telno[i];
		}
	}

	if (retval == '')
		retval = strval;

	return retval;
}

// 우편번호만들기
function makeZip (strval) {
	var zipno

	zipno = strval.arrSplit('-');
	if (zipno.length != 2)
		return strval;

	return zipno[0] + zipno[1];

}

// 우편번호만들기2
function makeZip2 (strval) {
	var zipno
	
	if (strval.length == 6)
	{
		s = strval.mid(0,3);
		e = strval.mid(3,3);
		zipno = s+'-'+e;
	}
	else
	{
		zipno = strval;
	}
	
	return zipno;	
}

// 숫자 3자리콤마
function makeInt (strval) {
	var num, retNum = '';
	num = strval.arrSplit(',');

	for (var i=0; i < num.length ;i++) {
		retNum = retNum + num[i];
	}

	return retNum;

}


function dispLeftStr (strval, length) {
	var retval;

	var aaa = strval.arrSplit('\n')

	swal (aaa.length);

	retval = strval.left (length) + '...';
	document.write(retval);
}

// 한글 길이 체크
function GetLength(sText)
{
	var i;
	var nLength = 0;

	for (i = 0; i < sText.length; i++) 	{
		if (sText.charCodeAt(i) > 128)
			nLength	+= 2;
		else
			nLength	++;
	}

	return nLength;
}
/**
 * Radio, Check 체크
 * 
 * @param javaFlag :
 *            javascript flag (Y or N)
 * @param obj :
 *            radio, check object
 * @return true or false
 */
function CheckRadioObject (javaFlag, obj, sMsg) {

	if (!obj)
	{
		swal ("["+ sMsg + "] objRadio Object Not Found...");
		return false;
	}

	if (javaFlag == 'Y') {
		if (!IsChecked(obj))
		{
			swal ("["+sMsg+"]" + " 항목을 선택하세요.")
			return false;
		}
	}

	return true;
}

/**
 * Combo 체크
 * 
 * @param javaFlag :
 *            javascript flag (Y or N)
 * @param obj :
 *            combo object
 * @return true or false
 */
function CheckComboObject (javaFlag, obj, sMsg) {

	if (!obj)
	{
		swal ("["+ sMsg + "] objCombo Object Not Found...");
		return false;
	}

	if (javaFlag == 'Y') {
	// 자바스크립트 체크 플래그 TRUE
		if (obj.options[obj.selectedIndex].value.length == 0) {
			swal ("["+sMsg+"]" + " 항목을 선택하세요.");
			obj.focus();
			return false;
		}
	}

	return true;
}

// --------TRIM() START----------

function _private_arrSplit(split) {
	var tmpStr;
	var i ;
	var iCnt;
	var iEnd;
	tmpStr = this;

	iCnt = 0;
	for( i = 0 ; i < tmpStr.length ; i++) {
		if (tmpStr.charAt(i) == split) {
			iCnt++;
		}
	}
	iCnt++;

	arr_str = new Array(iCnt);

	for (i = 0 ; i < iCnt ; i++)	{
		iEnd = tmpStr.indexOf(split);
		if (iEnd < 0)
			arr_str[i] = tmpStr;
		else{
			arr_str[i] = tmpStr.substring(0,iEnd);
			tmpStr = tmpStr.substring(iEnd+1);
		}
	}

	return arr_str;
}

function _private_trim() {
var tmpStr, atChar;
tmpStr = this;

if (tmpStr.length > 0) atChar = tmpStr.charAt(0);
while (_private_stringvb_isSpace(atChar)) {
 tmpStr = tmpStr.substring(1, tmpStr.length);
 atChar = tmpStr.charAt(0);
}
if (tmpStr.length > 0) atChar = tmpStr.charAt(tmpStr.length-1);
while (_private_stringvb_isSpace(atChar)) {
 tmpStr = tmpStr.substring(0,( tmpStr.length-1));
 atChar = tmpStr.charAt(tmpStr.length-1);
}
return tmpStr;
}

function _private_left(inLen) {
return this.substring(0,inLen);
}

function _private_right(inLen) {
return this.substring((this.length-inLen),this.length);
}

function _private_mid(inStart,inLen) {
var iEnd;
if (!inLen)
 iEnd = this.length;
else
 iEnd = inStart + inLen;
return this.substring(inStart,iEnd);
}

function _private_stringvb_isSpace(inChar) {
return (inChar == ' ' || inChar == '\t' || inChar == '\n');
}

// --------TRIM() END----------

function ibsheet_open(swidth, sheight){
	document.write('<OBJECT ID="IBSheet" width="'+swidth+'" height="'+swidth+'" CLASSID="CLSID:C838E9DA-1625-4E14-8B37-C6706B43C423"');
	document.write('CODEBASE="/com/ibsheet/IBSheet_3_2_0_13.cab#version=3.3.0.13">');
	document.write('</OBJECT>');			
}
 
// 모달팝업의 사이즈를 변경한다.
function sizeDialog(w, h)
{
top.dialogWidth = w + "px";
// top.dialogHeight = h + "px";
top.dialogHeight = (h+55) + "px";// for ie7.0
// top.dialogWidth = document.body.scrollWidth + 65 + "px";

top.dialogTop = (window.screen.height - Number(top.dialogHeight.replace(/\D/g, "")))/2;
top.dialogLeft = (window.screen.width - Number(top.dialogWidth.replace(/\D/g, "")))/2;
}

// 윈도우팝업의 사이즈를 변경한다.
function sizeWindow(w, h)
{
top.resizeTo(w, h);
var wtop = (window.screen.height - h)/2;
var wleft = (window.screen.width - w)/2;
top.moveTo(wleft, wtop);
}    
// grid 의 cell value 를 가져한다.
function grid_CellValue(grid, row, colNm)
{
var col = ( typeof colNm == "number" ) ? colNm : array_indexOf(grid.arrColName, colNm);	 
return grid.arrData[row][col];
}
// 배열에서 val 에 해당하는 index 를 찾는다.
function array_indexOf(array, val)
{
for ( var n = 0; n < array.length; n++ )
{
 if ( array[n] == val )
 {
   return n;
 }
}
return -1;
}
// sheet 에서 체크된 row 데이타를 구성하여 배열로 만들어 return 한다. grid 를 구성한다.
function sheet_getData(sheet, chkSvnm)
{
	var grid = {};
	
	// set column name
	var arrColName = [];
	var mapColName = {};
	for ( var col = 0; col <= sheet.LastCol; col++ )
	{
	 arrColName[arrColName.length] = sheet.ColSaveName(col);
	 mapColName[sheet.ColSaveName(col)] = col;
	}
	grid.arrColName = arrColName;
	grid.mapColName = mapColName;
	
	// set data
	var arrData = [];
	for ( var row = sheet.HeaderRows, rcnt = 0; rcnt < sheet.RowCount; row++, rcnt++ )
	{
	 if ( chkSvnm == null || (typeof(chkSvnm) == "function" && chkSvnm(sheet, row)) || sheet.CellValue(row, chkSvnm) == "1")
	 {
	   var colData = [];
	   for ( var col = 0; col <= sheet.LastCol; col++ )
	   {
	     colData[colData.length] = sheet.CellValue(row, col);
	   }
	   arrData[arrData.length] = colData;
	 }
	}
	grid.arrData = arrData;
	// set interface
	grid.RowCount = arrData.length;
	grid.ColCount = grid.arrColName.length;
	
	return grid;
}

/**
 * 날짜 유효성 체크.(년월일) param : sYmd 입력스트링(YYYYMMDD) return : Boolean true이면 날짜 범위임
 */
function checkDate(sYmd)
{
	// 길이 확인
	if(sYmd.length != 8)
	{
		return false;
	}
	var iYear = parseInt(sYmd.substring(0,4),10);  // 년도 입력(YYYY)
	var iMonth = parseInt(sYmd.substring(4,6),10);   // 월입력(MM)
	var iDay = parseInt(sYmd.substring(6,8),10);     // 일자입력(DD)
	
	if((iMonth < 1) ||(iMonth >12))
	{
		 return false;
	}
	
	// 각 달의 총 날수를 구한다
	var iLastDay = lastDay(sYmd.substring(0,6));  // 해당월의 마지말날 계산
	if((iDay < 1) || (iDay > iLastDay))
	{
		return false;
	}
	return true;
}

/**
 * 년월을 입력받아 마지막 일를 반환한다(년월) param : sYM 입력스트링(YYYYMM) return : String 해당월의 마지막날
 */
function lastDay(sYM)
{
	if(sYM.length != 6)
	{
		return;
	} 
	daysArray = new Array(12);    // 배열을 생성한다.
	for (i=1; i<8; i++)
	{
		daysArray[i] = 30 + (i%2);
	}
	for (i=8; i<13; i++)
	{
		daysArray[i] = 31 - (i%2);
	}  
	var sYear = sYM.substring(0, 4) * 1;
	var sMonth = sYM.substring(4, 6) * 1;
	
	if (((sYear % 4 == 0) && (sYear % 100 != 0)) || (sYear % 400 == 0))
	{
		daysArray[2] = 29;
	}
	else
	{
		daysArray[2] = 28;
	}
	return daysArray[sMonth].toString(); 
}



// == $11
// ======================================================================================================

// 기 능 : 날자 입력받을때 '/' 삽입하기

// 사용 예 : <input type="text" onKeyUp="javascript: toDateFormat(this, '시작일자');">

// <input type="text" onKeyUp="javascript: toDateFormat(this);">

// -------------------------------------------------------------------------------------------------------------

function toDateFormat(inputElement, title){



	var inputDate = inputElement.value;

	var inputDateWithoutMark = "";

	var returnValue = "";

	if( event != null && (event.keyCode == '37' || event.keyCode == '39' || event.keyCode == '27' || event.keyCode == '8' || event.keyCode == '46') ) return;

	if(isNullValue(inputDate)) return;

// '/'를 제거하는 루프문

	for(i=0; i<inputDate.length; i++){

		if (inputDate.substr(i,1) != "/" && inputDate.substr(i,1) != " "){

			inputDateWithoutMark += inputDate.substr(i,1);

		}

	}



	// 숫자체크 : 문자가 입력되었으면 함수 종료하고 input에 포커스

	if (isNaN(inputDateWithoutMark) == true){ // 숫자가 아니면

		if(isNullValue(title)){

			swal("날자 입력에는 숫자만 입력 가능합니다.");

		} else {

			swal("\"" + title + "\" 에는 숫자만 입력 가능합니다.");

		}

		inputElement.value = "";

		inputElement.focus();

		return;

	}

	if( event != null && (event.keyCode == '32') ){

		spaceIndex = inputDate.indexOf(" ");

		inputElement.value = inputDate.substring(0, spaceIndex) + inputDate.substring(spaceIndex +1);

		inputElement.focus();

		return;

	}


	if(inputDateWithoutMark.length > 4 && inputDateWithoutMark.length < 8) {

		firstMonthNo = inputDateWithoutMark.substr(4,1);

		// "월"의 첫자리 숫자가 "1" 이하일 때

		if( inputDate.length > 4 && inputDateWithoutMark.length > 4 && inputDate.indexOf("/") == 4 && inputDateWithoutMark.length < 8 && firstMonthNo >= 2 && firstMonthNo < 10 ){

				inputDateWithoutMark = inputDateWithoutMark.substring(0, 4) + "0" + firstMonthNo;

		}


	}

	if( inputDateWithoutMark.length > 6 && inputDateWithoutMark.length < 9 ) {

		firstDayNo = inputDateWithoutMark.substr(6,1);

		// 일자의 첫자리 숫자가 "3" 이하일 때

		if( inputDate.indexOf("/") == 4 && inputDate.lastIndexOf("/") == 7 && inputDateWithoutMark.length > 6 && inputDateWithoutMark.length < 8 && firstDayNo > 3 && firstDayNo < 10) {

			inputDateWithoutMark = inputDateWithoutMark.substring(0, 6) + "0" + firstDayNo;

		}
	}

	if ((inputDate.indexOf("/") == -1 || inputDate.indexOf("/") > 3) && inputDateWithoutMark.length > 3 && inputDateWithoutMark.length < 9) {

		returnValue = inputDateWithoutMark.substr(0,4) + '/' + inputDateWithoutMark.substr(4,2);

		if (inputDateWithoutMark.substr(5,1) != ""){

			returnValue += '/' +  inputDateWithoutMark.substr(6,2);

		}

		inputElement.value = returnValue;

	}



	// 입력된 값이 8자 일 때 "월일" 을 "/"로 구분해 부고 9자리 이상일때는 잉여자리 삭제한다.

	if (inputDateWithoutMark.length >= 8) {

// swal("8 자리 숫자로 입력하세요.");

		inputElement.value = inputDateWithoutMark.substring(0, 4) + "/" + inputDateWithoutMark.substring(4, 6) + "/" + inputDateWithoutMark.substring(6, 8);

		inputElement.focus();

	}

}



// == $4-1
// =======================================================================================================

// 기 능 : null 체크

// 사용 예 : isNullValue(inputValue)

// -------------------------------------------------------------------------------------------------------------

function isNullValue(inputValue){

	if (inputValue == null || inputValue.length == 0 || inputValue == ""){

		return true;

	}else{

		return false;

	}

}


/**
 * 시작일과 종료일의 유효성을 검사한다. param : sDate 시작일 , eDate 종료일 return : Boolean true이면
 * 유효성 검사 통과
 */
function chkStartEndDate(sDate,eDate)
{
	sDate = sDate.replaceAll("/","");	
	eDate = eDate.replaceAll("/","");	
	
	if(sDate != "" && eDate != "" )
	{
		if(sDate > eDate)
		{
			swal("시작일자가 종료일자보다 클 수 없습니다.");
			return false;
		}
	}
	
	return true;
}

/**
 * 시작일과 종료일의 날짜 차이를 계산한다. param : sDate 시작일 , eDate 종료일 return : 날짜 차이
 */
function chkDiffDate(sDate,eDate)
{
	sDate = sDate.replaceAll("/","");	
	eDate = eDate.replaceAll("/","");		
	var date1 = new Date(sDate.substr(0,4),sDate.substr(4,2)-1,sDate.substr(6,2));
	var date2 = new Date(eDate.substr(0,4),eDate.substr(4,2)-1,eDate.substr(6,2));
		
	var interval = date2 - date1;
	var day = 1000*60*60*24;

	return parseInt(interval/day);
}

/**
 * REPORT EXPRESS관련자료
 */
function ViewerInstall() {
	var ReValue = 1;
		// Viewer Check
		ReValue=window.showModalDialog("/cabsoft/instre/inst.htm","InstallWindow", "dialogWidth:600px;dialogHeight:520px; center:yes; help:no; status:no; scroll:no; resizable:no");

	switch (ReValue){
	case -1:
		ReValue=window.showModalDialog("/cabsoft/instre/cancel.htm","InstallWindow", "dialogWidth:365px;dialogHeight:165px; center:yes; help:no; status:no; scroll:no; resizable:no");
		if (ReValue == -1) 
			history.go(0);
		else {	// Cancel
		        history.go(-1); //
     	} 
	case -2:
		swal ("Microsoft Internet Explorer 5.0", 64, "ReportExpress Viewer")
	default: 
	}
}

function DetectViewerControl() {
	try{
		var xObj = new ActiveXObject("ReportExpress.Viewer");
		if(xObj)
			Installed = true;
		else
			Installed = false;
		}

	catch(ex)
	{
		Installed = false;
	}

	if(Installed == true) {
		var Viewer_Ver = xObj.version;
	}
	if((Installed == true) && (Viewer_Ver == "3.3.0.58")) {
	} else {
		swal("Report Express Viewer Control Install");
		ViewerInstall();
		// window.location.reload();
	}
}


function MM_swapImgRestore() { // v3.0
var i,x,a=document.MM_sr; 
for(i=0;a&&i<a.length&&(x=a[i])&&x.oSrc;i++)
	  x.src=x.oSrc;
}

function MM_preloadImages() { // v3.0
	var d = document;
	if (d.images) {
		if (!d.MM_p) {
			d.MM_p = new Array();
		}
		var i, j = d.MM_p.length, a = MM_preloadImages.arguments;
		for (i = 0; i < a.length; i++) {
			if (a[i].indexOf("#") != 0) {
				d.MM_p[j] = new Image;
				d.MM_p[j++].src = a[i];
			}
		}
	}
}

function MM_findObj(n, d) { // v4.01
var p,i,x;  
if(!d) {
	  d=document;
}
if((p=n.indexOf("?"))>0&&parent.frames.length) {
 d=parent.frames[n.substring(p+1)].document; n=n.substring(0,p);
}
if(!(x=d[n])&&d.all) { 
	  x=d.all[n]; 
}
for (i=0;!x&&i<d.forms.length;i++) { 
	  x=d.forms[i][n];
}
for(i=0;!x&&d.layers&&i<d.layers.length;i++) {
	  x=MM_findObj(n,d.layers[i].document);
}
if(!x && d.getElementById) {
	  x=d.getElementById(n);
}
return x;
}

function MM_swapImage() { // v3.0
	var i, j = 0, x, a = MM_swapImage.arguments;
	document.MM_sr = new Array;
	for (i = 0; i < (a.length - 2); i += 3) {
		if ((x = MM_findObj(a[i])) != null) {
			document.MM_sr[j++] = x;
			if (!x.oSrc) {
				x.oSrc = x.src;
			}
			x.src = a[i + 2];
		}
	}
}







/**
 * check_fgnno('외국인번호13자리'); check_juminno('주민번호13자리');
 * check_busino('사업자번호10자리');
 * 
 */








// 재외국인 번호 체크
function check_fgnno(fgnno) {
     var sum=0;
     var odd=0;
     buf = new Array(13);
     for(i=0; i<13; i++) { buf[i]=parseInt(fgnno.charAt(i)); }
     odd = buf[7]*10 + buf[8];
     if(odd%2 != 0) { return false; }
     if( (buf[11]!=6) && (buf[11]!=7) && (buf[11]!=8) && (buf[11]!=9) ) {
             return false;
     }
     multipliers = [2,3,4,5,6,7,8,9,2,3,4,5];
     for(i=0, sum=0; i<12; i++) { sum += (buf[i] *= multipliers[i]); }
     sum = 11 - (sum%11);
     if(sum >= 10) { sum -= 10; }
     sum += 2;
     if(sum >= 10) { sum -= 10; }
     if(sum != buf[12]) { return false; }
     return true;
}

// 주민번호 체크
function check_juminno(juminno) {
	
     if(juminno=="" || juminno==null || juminno.length!=13) {
             swal("주민등록번호를 올바르게 적어주세요.");
             return false;
     }
     var jumin1 = juminno.substr(0,6);
     var jumin2 = juminno.substr(6,7);
     var yy     = jumin1.substr(0,2);        // 년도
     var mm     = jumin1.substr(2,2);        // 월
     var dd     = jumin1.substr(4,2);        // 일
     var genda  = jumin2.substr(0,1);        // 성별
     var msg, ss, cc;

     // 숫자가 아닌 것을 입력한 경우
     if (!isNumeric(jumin1)) {
             swal("주민등록번호 앞자리를 숫자로 입력하세요.");
             return false;
     }
     // 길이가 6이 아닌 경우
     if (jumin1.length != 6) {
             swal("주민등록번호 앞자리를 다시 입력하세요.");
             return false;
     }
     // 첫번째 자료에서 연월일(YYMMDD) 형식 중 기본 구성 검사
     if (yy < "00" || yy > "99" ||
             mm < "01" || mm > "12" ||
             dd < "01" || dd > "31") {
             swal("주민등록번호 앞자리를 다시 입력하세요.");
             return false;
     }
     // 숫자가 아닌 것을 입력한 경우
     if (!isNumeric(jumin2)) {
             swal("주민등록번호 뒷자리를 숫자로 입력하세요.");
             return false;
     }
     // 길이가 7이 아닌 경우
     if (jumin2.length != 7) {
             swal("주민등록번호 뒷자리를 다시 입력하세요.");
             return false;
     }
     // 성별부분이 1 ~ 4 가 아닌 경우
     if (genda < "1" || genda > "4") {
             swal("주민등록번호 뒷자리를 다시 입력하세요.");
             return false;
     }
     // 연도 계산 - 1 또는 2: 1900년대, 3 또는 4: 2000년대
     cc = (genda == "1" || genda == "2") ? "19" : "20";
     // 첫번째 자료에서 연월일(YYMMDD) 형식 중 날짜 형식 검사
     if (isYYYYMMDD(parseInt(cc+yy), parseInt(mm), parseInt(dd)) == false) {
             swal("주민등록번호 앞자리를 다시 입력하세요.");
             return false;
     }
		
     // Check Digit 검사
     if (!isSSN(jumin1, jumin2)) {
             swal("주민등록번호가 올바르지 않습니다. 다시 입력하세요.");
             return false;
     }
     return true;
}

// 사업자등록번호 체크
function check_busino(vencod){
 var sum = 0;
 var getlist = new Array(10);
 var chkvalue = new Array("1", "3", "7", "1", "3", "7", "1", "3", "5");
 for (var i = 0; i < 10; i++) {
     getlist[i] = vencod.substring(i, i + 1);
 }
 for (var i = 0; i < 9; i++) {
     sum += getlist[i] * chkvalue[i];
 }
 sum = sum + parseInt((getlist[8] * 5) / 10);
 var sidliy = sum % 10;
 var sidchk = 0;
 if (sidliy != 0) {
     sidchk = 10 - sidliy;
 } else {
     sidchk = 0;
 }
	
 if (sidchk != getlist[9]) {
     return false;
 }
 return true;
}
var _keyNo = "DmResort2013";
var _encKey1 = "Imw0P/SyOjPvIKFzEchGYQ==";
var _encKey2 = "f3Krdb8bodcLffHw/5rDkC/3EhuRAxwVNfgQPh27Rrs=";
function isYYYYMMDD(y, m, d) {
     switch (m) {
     case 2:        // 2월의 경우
             if (d > 29) return false;
             if (d == 29) {
                     // 2월 29의 경우 당해가 윤년인지를 확인
                     if ((y % 4 != 0) || (y % 100 == 0) && (y % 400 != 0))
                             return false;
             }
             break;
     case 4:        // 작은 달의 경우
     case 6:
     case 9:
     case 11:
             if (d == 31) return false;
     }
     // 큰 달의 경우
     return true;
}
function isNumeric(s) {
     for (i=0; i<s.length; i++) {
             c = s.substr(i, 1);
             if (c < "0" || c > "9") return false;
     }
     return true;
}
function isLeapYear(y) {
     if (y < 100)
     y = y + 1900;
     if ( (y % 4 == 0) && (y % 100 != 0) || (y % 400 == 0) ) {
             return true;
     } else {
             return false;
     }
}
function getNumberOfDate(yy, mm) {
     month = new Array(29,31,28,31,30,31,30,31,31,30,31,30,31);
     if (mm == 2 && isLeapYear(yy)) mm = 0;
     return month[mm];
}
function isSSN(s1, s2){
 n = 2;
 sum = 0;
 for (i = 0; i < s1.length; i++) 
     sum += parseInt(s1.substr(i, 1)) * n++;
 for (i = 0; i < s2.length - 1; i++) {
     sum += parseInt(s2.substr(i, 1)) * n++;
     if (n == 10) 
         n = 2;
 }
 c = 11 - sum % 11;
 if (c == 11) 
     c = 1;
 if (c == 10) 
     c = 0;
 if (c != parseInt(s2.substr(6, 1))) 
     return false;
 
 else 
     return true;
}

// 만 14세 이하 체크 스크립트
function isBelow14(scn1, scn2){
	var nowDt = new dateUtil();
	if (scn2.substring(0, 1) == '1' || scn2.substring(0, 1) == '2') 
		scn1 = "19" + scn1;
	else 
		scn1 = "20" + scn1;

	var comDt = nowDt.parseDate(scn1);
	var fourteen = nowDt.setDate({year:-14});

	if(comDt > fourteen){
		return true;
	}else{
		return false;
	}
}



/*******************************************************************************
 * 앞/뒤에서 White Space가 제거된 값반환
 ******************************************************************************/
function trim(value) {
return value.replace(/^\s+|\s+$/g,"");
}

/*******************************************************************************
 * 값의 앞에서 White Space를 제거한다.
 ******************************************************************************/
function ltrim(value) {
return value.replace(/^\s+/,"");
}

/*******************************************************************************
 * 값의 뒤에서 White Space를 제거한다.
 ******************************************************************************/
function rtrim(value) {
return value.replace(/\s+$/,"");
} 


/**
 * data id==object.id data copy ind : one row for multy rows, null:single row
 * data1 : 필드명이 다른경우 data1[key] : copy field명, default null data(value)의 key와
 * data1(필드명)의 key는 동일함. target : target id 내에만 적용. default null
 */
function copyValues(data,ind,data1,target){
	for (var key in data){
		var $obj = null;
		var obj1 = null;
		if (data1==null)
			obj1 = getElements(key);
		else
			obj1 = getElements(data1[key]);
		if (target!=null){
			if (getElement(target).contains(obj1[0])==false) continue;
		}
		if (obj1.length < 1) continue;
		if (obj1[0].nodeName=="INPUT" && (obj1[0].type=="radio" || obj1[0].type == 'checkbox')){
			for (var i=0;i<obj1.length;i++){
				if (obj1[i].value == data[key]){
					obj1[i].checked = true;
					break;
				}
			}
			continue;
		}

		if (ind==null)
			$obj = obj1[0];
		else
			$obj = obj1[ind];
		if ($obj==null) continue;

		var vtag = $obj.nodeName;
		if (vtag=="INPUT"){
			if ($obj.type == 'text' || $obj.type == 'hidden') {
				$obj.value = data[key];
			}
		}
		else if (vtag=="SELECT" || vtag=="TEXTAREA"){
			$obj.value = data[key];
		}
		else{
			if (vtag=="SPAN" || vtag=="DIV"){
				$obj.innerHTML = data[key];
			}
		}
	}
	return true;
}

/*
 * 폼전송시 데이터 처리동안 로딩화면 띄우기
 */
function loadFrm(msg, frm, methodtype, url){
	if(msg == "" || msg == null)
		msg = "로딩중입니다..";	

	
    // IE에서 애니메이션 gif가 멈춰있는 현상으로 인하여 setTimeout을 이용하여 Progressbar function 실행
    //$(window).bind("beforeunload", function(){  setTimeout("Progressbar('"+msg+"')", 10);});
	//msg="";
	//loadMask(true, msg);
	$('#'+frm).attr({method:methodtype,action:url, target:"_self"}).submit();
}
/*
 * 폼전송시 데이터 처리동안 로딩화면 띄우기 - Ajax
 */
function loadFrmAjax(msg, frm, methodtype, url){
	
	if(msg == "" || msg == null)
		msg = "로딩중입니다. 잠시만 기다려 주세요.";

	$j("#loadingScreen").modal({
 		overlayId: 'loadingScreen-overlay',
 		containerId: 'loadingScreen-container',            
         close: false,
         closeHTML: '',
 		 onShow: function (dialog) {
 			var modal = this;
 			$('.message', dialog.data[0]).append(msg);					     			
 		}            
     });
	
	$j('#'+frm).attr({method:methodtype,action:url}).submit();	
}
/*
 * 화면이동하기전 로딩화면 띄우기
 */
function loadPage(msg, url){
	
	if(msg == "" || msg == null)
		msg = "로딩중입니다..";

//    $(window).bind("beforeunload", function(){  setTimeout("Progressbar('"+msg+"')", 10);});
	loadMask(true, msg);
	$(location).attr("href", url);	

}

function loadMask(isload, msg, ajax){
	if(isload){
		if(msg == "" || msg == null)
			msg = "로딩중입니다. 잠시만 기다려 주세요.";
		
		msg = "";
		if(BrowserDetect.browser == 'MSIE'){
			if(ajax)
				Progressbar(msg);
			else
				$(window).bind("beforeunload", function(){  setTimeout("Progressbar('"+msg+"')", 10);});
		}else{
			$("#loadingScreen").modal({
		 		overlayId: 'loadingScreen-overlay',
		 		containerId: 'loadingScreen-container',            
		        close: false,
		        closeHTML: '',
		 		onShow: function (dialog){
		 			var modal = this;
		 			$('.message', dialog.data[0]).append(msg);					     			
		 		}            
		     });			
		}
	}else{
		//if(BrowserDetect.browser != 'MSIE')
			$.modal.close();
	}
}


function getAbsolutePath() { 
    var loc = window.location;
    var pathName = loc.pathname.substring(0, loc.pathname.lastIndexOf('/') + 1);
    return loc.href.substring(0, loc.href.length - ((loc.pathname + loc.search + loc.hash).length - pathName.length)); 
} 

function getCurrntPath() { 
    var loc = window.location;
    var path = loc.pathname;
    return path;
 
} 

function map_popup() { 
	window.open('/EgovPageLink.do?link=kipi/intro/map_popup&subMenuNo=4&subPath=1',"v_print","scrollbars=no,toolbar=no,resizable=no,location=no,menubar=0,width=610,height=710"); 
}

function SetCookie(name, value, expire_days){
	if(expire_days == null || expire_days == '' || expire_days == undefined){ // Session Cookie
		$.cookie(name, value, {path:'/'});
	}else{
		$.cookie(name, value, {expires:expire_days, path:'/'});
	}
} 

function GetCookie(name){
	return $.cookie(name);
}

function RemoveCookie(name){
	$.cookie(name, '',{expires:-1, path:'/'});
}

/*
 *  로그아웃(공통)
 */
function fnLogOut(){
	$(location).attr('href','/Admin.Login.Out.dp/proc.go');
}

function closePopup(){
	
	try{
		if(event.clientY < 0) {
			RemoveCookie('popupv');
			window.close();
			window.opener.location.reload();
		}		
	}catch(e){
		
	};
		
}


function centerPopUp(winName, winURL, winWidth, winHeight){
	var winPosLeft = (screen.width - winWidth) / 2;
	var winPosTop = (screen.height - winHeight) / 2;
	var winOpt = "width="+winWidth+",height="+winHeight+",top="+winPosTop+",left="+winPosLeft;
	window.open(winURL, winName, winOpt + ",menubar=no,status=no,scrollbars=no,resizable=no");
	SetCookie('popupv','Y');
}

function cmmPopUp(winName, winURL, winWidth, winHeight){
	var winPosLeft = (screen.width - winWidth) / 2;
	var winPosTop = (screen.height - winHeight) / 2;
	var winOpt = "width="+winWidth+",height="+winHeight+",top="+winPosTop+",left="+winPosLeft;
	window.open(winURL, winName, winOpt + ",menubar=no,status=no,scrollbars=no,resizable=no");
}

function cmmPopUpScroll(winName, winURL, winWidth, winHeight){
	var winPosLeft = (screen.width - winWidth) / 2;
	var winPosTop = (screen.height - winHeight) / 2;
	var winOpt = "width="+winWidth+",height="+winHeight+",top="+winPosTop+",left="+winPosLeft;
	window.open(winURL, winName, winOpt + ",menubar=no,status=no,scrollbars=yes,resizable=no");
}

/*
 * 체크박스 선택으로 전체선택/해제 parent_id : 전체선택 checkbox id child_nm : 선택되어질 checkbox 의
 * name
 */
function fncheckAll(parent_id, child_nm){

	if($("#"+parent_id).is(":checked")){
		$("input[name="+ child_nm +"]").each(function(){
	    	$(this).attr("checked",true);
		});
	}else{
		$("input[name="+child_nm+"]").each(function(){
			$(this).attr("checked",false);			
		});
	}	
}

/*
 * 체크된 아이템의 값을 다른 input box에 배열 형태로 세팅 check_nm (체크된 항목의 name 명) setid : 담아둘
 * input box 의 id 명
 */
function fnIsCheckSetVal(check_nm, setid){
	var val = "";
	$("#"+setid).val("");
	var i=0;
	var len = $("input[name="+ check_nm +"]:checked").length;
	$("input[name="+ check_nm +"]:checked").each(function(){
		val = val + $(this).val();
		if(i < len-1){
			val = val+",";			
		}
		i++;
	});
	$("#"+setid).val(val);
}

function is_allCheck(chkboxname, allcheckboxid){

	var len = $("input[name="+ chkboxname +"]:checked").length;
	var alen = $("input[name="+ chkboxname +"]").length;
	
	if(len == 0){
		$("#"+allcheckboxid).attr("checked", false);
	}
	if(alen == len){
		$("#"+allcheckboxid).attr("checked", true);
	}else{
		$("#"+allcheckboxid).attr("checked", false);
	}
	
}

function fnfrmReset(frmid){
	
	$("#"+frmid).each(function(){
		this.reset();
	});
}

function fncGoAfterErrorPage(){
    history.back(-2);
}

function fnMovePagePost(menuId, isload, params){
	var frm = bindForm("_moveFrm", "post", "/movePage.dp/dmparse.dm");
	frm.appendChild(addInput("menuCd",menuId,"hidden"));
	
	if(params !=null && params !='' && typeof params != 'undefined'){
		for(var param in params){
			frm.appendChild(addInput(param, params[param], "hidden"));
		}		
	}
	
	if(isload == 'Y'){
		loadMask(true, msg);
	}
	
	frm.submit();	
}
/*
 * params -> {key1:'val',key2:'val2'}
 */
function fnMovePage(menuId, isload, params){

	if(menuId == '6550000'){ //회원권분양
		window.open("http://www.dmlc.co.kr/");
		return;
	//}else if(menuId == '5553000'){
	 //	menuId = '5553001';
	}else if(menuId == '5550000'){
		menuId = '5551000';
	}else if(menuId == '5553004'){
		window.open("http://www.daemyungtourmall.com/Html/dmzone/shuttle/default_shuttle.asp?pgcode=bus");
		return;
	}else if(menuId == '5650000'){ //테마여행일경우 워터파크로
		menuId = '5651000';
	}else if(menuId == '5750000'){ // 이벤트 할인일경우 리조트이벤트로 요청 by 이명학대리
		menuId = '5753001';
	}else if(menuId == '5950000'){ // 단체행사일 경우 일반단체로 수정 by 이명학대리
		menuId = '5951000';
	}
	
	var url = "/mv.dp/dmparse.dm";
	if(menuId =='' || typeof menuId == 'undefined'){
		swal('잘못된 경로 입니다..');
		return false;
	}
	var Arparam = new Array();  
	Arparam.push("menuCd="+menuId);
	
	if(params !=null && params !='' && typeof params != 'undefined'){
		for(var param in params){
			Arparam.push(param+"="+params[param]);
		}		
	}
	
	if(isload == 'Y'){
		loadPage('로딩중입니다..',url+"?"+Arparam.join("&"));
	}else{
		$(location).attr('href', url+"?"+Arparam.join("&"));
		//window.location = url+"?"+Arparam.join("&");
	}

	return false;
}

function fnMovePg(menuId, isload, params){

	var url = "/Comm.Move.dp/proc.go";
	if(menuId =='' || typeof menuId == 'undefined'){
		swal('잘못된 경로 입니다..');
		return false;
	}
	
	if(menuId == 'home'){
		$(location).attr('href','${hostHeader}');
		return false;
	}
	
	var Arparam = new Array();  
	Arparam.push("menu_id="+menuId);
	
	if(params !=null && params !='' && typeof params != 'undefined'){
		for(var param in params){
			Arparam.push(param+"="+params[param]);
		}		
	}
	
	if(isload == 'Y'){
		loadPage('로딩중입니다..',url+"?"+Arparam.join("&"));
	}else{
		$(location).attr('href', url+"?"+Arparam.join("&"));
		//window.location = url+"?"+Arparam.join("&");
	}

	return false;
}


function fnDmMovePage(menuId, isload, params){

	if(menuId == '6550000'){ //회원권분양
		window.open("http://www.dmlc.co.kr/");
		return;
	//}else if(menuId == '5553000'){
	 //	menuId = '5553001';
	}else if(menuId == '5550000'){
		menuId = '5551000';
	}else if(menuId == '5553004'){
		window.open("http://www.daemyungtourmall.com/Html/dmzone/shuttle/default_shuttle.asp?pgcode=bus");
		return;
	}else if(menuId == '5650000'){ //테마여행일경우 워터파크로
		menuId = '5651000';
	}else if(menuId == '5750000'){ // 이벤트 할인일경우 리조트이벤트로 요청 by 이명학대리
		menuId = '5753001';
	}else if(menuId == '5950000'){ // 단체행사일 경우 일반단체로 수정 by 이명학대리
		menuId = '5951000';
	}
	
	var url = "http://www.daemyungresort.com/mv.dp/dmparse.dm";
	if(menuId =='' || typeof menuId == 'undefined'){
		swal('잘못된 경로 입니다..');
		return false;
	}
	var Arparam = new Array();  
	Arparam.push("menuCd="+menuId);
	
	if(params !=null && params !='' && typeof params != 'undefined'){
		for(var param in params){
			Arparam.push(param+"="+params[param]);
		}		
	}
	
	var winPosLeft = (screen.width - 1024) / 2;
	var winPosTop = (screen.height - 768) / 2;
	var winOpt = "width=1024,height=768,top="+winPosTop+",left="+winPosLeft;
	window.open(url+"?"+Arparam.join("&"), "dmPop", winOpt + ",menubar=yes,status=yes,scrollbars=yes,resizable=yes");

	return false;
}


function fnSetQuarterTerm(sid, eid, m, type, splitType){
	//swal("fnSetQuarterTerm :: "+splitType);
	var _splitType = '.';
	if(splitType !=null && splitType !='' && typeof splitType != undefined){
		_splitType = splitType;
	}
	
	var istVal = $("#"+sid).val();
	var iedVal = $("#"+eid).val();
	
	if(""==istVal){
		swal("기준 되는 년을 선택하여 주세요");
		$("#yy_"+sid).focus();
		return;
	}
	
	//var toDate = new Date();
	//var last_day = this.lastDay(ymd).format("yyyy"+dtdiv+"MM"+dtdiv+"dd");
	//var curYear = toDate.format("yyyy");
   //var curMonth = toDate.format("MM");
	
	var _year1 = istVal.substr(0,4); // 년도
	var _year2 = iedVal.substr(0,4); // 년도
//	var _lastDay1 = fnDateUtil.lastDay(_year1 + _month2 + "01");
	
/*	var _jan = curYear + ".01"+ ".01";
	var janlastDay = fnDateUtil.lastDay(jan);*/
	
	if(m== '1'){
		if($.find("#dd_"+sid) == ""){
			$("#"+sid).val(_year1 + _splitType +"01"); //1월
			$("#"+eid).val(_year1 + _splitType +"03"); //3월
		}else {
			$("#"+sid).val(_year1 + _splitType +"01"+_splitType+"01"); //1월1일부터
			$("#"+eid).val(_year1 + _splitType+"03" + _splitType + fnDateUtil.lastDay(_year1+"0301").format("dd")); //3월말
		}
	}else if(m == '2'){
		if($.find("#dd_"+sid) == ""){
			$("#"+sid).val(_year1 + _splitType +"04"); //4월
			$("#"+eid).val(_year1 + _splitType +"06"); //6월
		}else {
			$("#"+sid).val(_year1 + _splitType +"04"+_splitType+"01"); //4월1일부터
			$("#"+eid).val(_year1 + _splitType +"06" + _splitType + fnDateUtil.lastDay(_year1+"0601").format("dd")); //6월말
		}
	}else if(m == '3'){
		if($.find("#dd_"+sid) == ""){
			$("#"+sid).val(_year1 + _splitType+"07"); //7월
			$("#"+eid).val(_year1 + _splitType +"09"); //9월
		}else {
			$("#"+sid).val(_year1 + _splitType+"07"+_splitType+"01"); //7월1일부터
			$("#"+eid).val(_year1 + _splitType +"09" + _splitType + fnDateUtil.lastDay(_year1+"0901").format("dd")); //9월말
		}
	}else if(m == '4'){
		if($.find("#dd_"+sid) == ""){
			$("#"+sid).val(_year1 + _splitType+ "10"); //10월
			$("#"+eid).val(_year1 + _splitType +"12"); //12월
		}else {
			$("#"+sid).val(_year1 + _splitType+ "10"+_splitType+"01"); //10월1일부터
			$("#"+eid).val(_year1 + _splitType +"12" + _splitType + fnDateUtil.lastDay(_year1+"1201").format("dd")); //12월말
		}
	}
	
	if(type == 'select'){
		
		var istVal = $("#"+sid).val();
		var iedVal = $("#"+eid).val();
		
		var sYmd = $("#"+sid).val().replaceAll(_splitType,"");
		var eYmd = $("#"+eid).val().replaceAll(_splitType,"");		
		
		if(istVal.length >= 4){
			$("#yy_"+sid).selectOptions(sYmd.substr(0,4), true);
		}
		if(istVal.length >= 6){
			$("#mm_"+sid).selectOptions(sYmd.substr(4,2), true);			
		}
		if(istVal.length >= 8){
			$("#dd_"+sid).selectOptions(sYmd.substr(6,2), true);
			
		}
		
		if(iedVal.length >= 4){
			$("#yy_"+eid).selectOptions(eYmd.substr(0,4), true);			
		}
		if(iedVal.length >= 6){
			$("#mm_"+eid).selectOptions(eYmd.substr(4,2), true);			
		}
		if(iedVal.length >= 8){
			$("#dd_"+eid).selectOptions(eYmd.substr(6,2), true);
		}		
	}
	
}

function fnSetMonthDateTerm(sid, eid, m, type , splitType){
	//swal("fnSetMonthDateTerm > splitType:: "+splitType);
	var _splitType = '.';
	if(splitType !=null && splitType !='' && typeof splitType != undefined){
		_splitType = splitType;
	}
	
	if(type == 'select'){
		var istVal = $("#"+sid).val();
		var iedVal = $("#"+eid).val();
		
		if(istVal.length == 4){
			$("#"+sid).val(istVal + _splitType+"01"+ _splitType +"01");			
		}else if(istVal.length == 6){
			$("#"+sid).val(istVal + _splitType+ "01");
		}
		
		if(iedVal.length == 4){
			$("#"+eid).val(iedVal + _splitType+"01"+ _splitType + "01");			
		}else if(iedVal.length == 6){
			$("#"+eid).val(iedVal + _splitType + "01");
		}
		
		fnDateUtil.TermSetDate(sid, eid,_splitType,{month:m})
		
		var sYmd = $("#"+sid).val().replaceAll(_splitType,"");
		var eYmd = $("#"+eid).val().replaceAll(_splitType,"");
		
		if(istVal.length >= 4){
			$("#yy_"+sid).selectOptions(sYmd.substr(0,4), true);
		}
		if(istVal.length >= 6){
			$("#mm_"+sid).selectOptions(sYmd.substr(4,2), true);			
		}
		if(istVal.length >= 8){
			$("#dd_"+sid).selectOptions(sYmd.substr(6,2), true);
			
		}
		
		if(iedVal.length >= 4){
			$("#yy_"+eid).selectOptions(eYmd.substr(0,4), true);			
		}
		if(iedVal.length >= 6){
			$("#mm_"+eid).selectOptions(eYmd.substr(4,2), true);			
		}
		if(iedVal.length >= 8){
			$("#dd_"+eid).selectOptions(eYmd.substr(6,2), true);
		}
		
	}else{
		fnDateUtil.TermSetDate(sid, eid,_splitType,{month:m});		
	}
}

function fnGetLastDay(ymd){
	var limit_char = /[~!\#$^&*\=+|:;?"<,.>'-]/;
	ymd = ymd.split(limit_char).join("");
	if(ymd.length < 6){
		swal("날짜 형식이 잘못되었습니다.");
		return;
	}
	var _year = ymd.substr(0,4);
	var _month = ymd.substr(4,2);
	var d2;
	d2 = new Date(_year, _month, '0').getDate();
	return d2;	
}

function fnSelectedMonthDay(yearid, monthid, dayid, inputId, splitType ){
	//swal("fnSelectedMonthDay > splitType::"+splitType);
	
	var _splitType = '.';
	if(splitType !=null && splitType !='' && typeof splitType != undefined){
		_splitType = splitType;
	}
	
	var _year = $("#"+yearid).val();
	var _month = $("#"+monthid).val();
	
	if(_year == '' || _year == null || typeof _year == 'undefined'){
		_year = todate.format("yyyy");
	}
	if(_month == '' || _month == null || typeof _month == 'undefined'){
		_month = '01';
	}
	
	var ymd = $("#"+yearid).val()+$("#"+monthid).val();
	if(ymd.length > 6) {
		var dayval = $("#"+dayid).val();
		$("#"+dayid).removeOption(/./);
		var lastday = fnGetLastDay(ymd);
		for(var i=1; i <= lastday; i++){
			var _day;
			if(i < 10){
				_day = '0'+i;
			}else{
				_day = i;
			}
			$("#"+dayid).addOption(_day, _day);
		}
		
		var intDay = parseInt(dayval, 10);
		if(intDay > lastday){
			$("#"+dayid).selectOptions("01", true);		
		}else{
			$("#"+dayid).selectOptions(dayval, true);
		}
	}
	
	
	var _day = $("#" +dayid).val();
	//var todate = new Date();
	
	if(_day == '' || _day == null || typeof _day == 'undefined'){
		_day = '01';
	}
	
	if(inputId != '' && inputId != undefined && inputId != null){
		
		if($.find("#"+dayid) == ""){
			$("#"+inputId).val(_year + _splitType + _month);
		}else {
			$("#"+inputId).val(_year + _splitType + _month + _splitType + _day);
		}
	}
}

function setPickerSelectDate(yearid, monthid, dayid, ymd){

	var _date = fnDateUtil.parseDate(ymd);
	
	$("#"+dayid).removeOption(/./);
	var lastday = fnGetLastDay(ymd);
	for(var i=1; i <= lastday; i++){
		var _day;
		if(i < 10){
			_day = '0'+i;
		}else{
			_day = i;
		}
		$("#"+dayid).addOption(_day, _day);
	}
	
	$("#"+yearid).selectOptions(_date.format("yyyy"),true);
	$("#"+monthid).selectOptions(_date.format("MM"),true);
	$("#"+dayid).selectOptions(_date.format("dd"),true);

}

/*
 * 패스워드 서로 비교
 */
function comparePwd(pwdid1, pwdid2){
	var pwd1 = $("#"+pwdid1).val();
	var pwd2 = $("#"+pwdid2).val();
	
	if(pwdid1 != pwdid2){
		swal("패스워드가 서로 틀립니다.");
		return false;
	}
	
	return true;
}

/*
 * 패스워드에 id 가 포함되어있는지 확인
 */
function pwdIdCheck(frmId, frmPwd){
	var _id = $("#frmId").val().trim();
	var _pwd = $("#frmPwd").val().trim();
	
	if(_pwd.indexOf(_id) > -1){
		swal("ID가 포함된 패스워드는 사용하 실 수 없습니다.");
		return false;
	}
	return true;
}

function HashTable(obj)
{
    this.length = 0;
    this.items = {};
    for (var p in obj) {
        if (obj.hasOwnProperty(p)) {
            this.items[p] = obj[p];
            this.length++;
        }
    }

    this.setItem = function(key, value){
        var previous = undefined;
        if (this.hasItem(key)) {
            previous = this.items[key];
        }
        else {
            this.length++;
        }
        this.items[key] = value;
        return previous;
    };

    this.getItem = function(key){
        return this.hasItem(key) ? this.items[key] : undefined;
    };

    this.hasItem = function(key){
        return this.items.hasOwnProperty(key);
    };
   
    this.removeItem = function(key){
        if (this.hasItem(key)) {
            previous = this.items[key];
            this.length--;
            delete this.items[key];
            return previous;
        }
        else {
            return undefined;
        }
    };

    this.keys = function(){
        var keys = [];
        for (var k in this.items) {
            if (this.hasItem(k)) {
                keys.push(k);
            }
        }
        return keys;
    };

    this.values = function(){
        var values = [];
        for (var k in this.items) {
            if (this.hasItem(k)) {
                values.push(this.items[k]);
            }
        }
        return values;
    };

    this.each = function(fn){
        for (var k in this.items) {
            if (this.hasItem(k)) {
                fn(k, this.items[k]);
            }
        }
    };

    this.clear = function(){
        this.items = {};
        this.length = 0;
    };
}

function ajaxPostList(urlval, paramArray){
	/*
	 * paramArray 작성 예 - 파라메터 var paramArray = new Array(); paramArray['param1'] =
	 * 'param1'; paramArray['param2'] = 'param2';
	 */
	Ext.regModel( "Item", {
		fields:[
			"id", "server", "secret", "farm"
		],
		reader:{
			type:"json"
		}
	});

	
	var store = new Ext.data.Store({
		model:"Item",
		autoLoad:false,
		listeners : {
			datachanged:function(){
				console.info( 'datachanged', store );
			}
		}
	});	
	
	Ext.Ajax.request({
		  url : urlval,
		  method: 'POST',
		  // headers: { 'Content-Type': 'application/json' },
		  params : paramArray,
		  success: function (response, request) {
		         // var jsonResp =
					// Ext.util.JSON.decode(response.responseText);
		         // Ext.Msg.swal("Info","UserName from Server :
					// "+jsonResp.username);
			  		var jsonResp = Ext.util.JSON.decode( response.responseText );
/*
 * "parent":{ "item:"[ { "child1":"value" }, { "child2":"value" } ] }
 */
			  		// store.loadData( jsonResp.parent.item, true ); //리스트성을
					// 저장합니다.
			  
		       },
		  failure: function (response) {
		      var jsonResp = Ext.util.JSON.decode(response.responseText);
		      Ext.Msg.swal("Error",jsonResp.error);
		       }
		 });	
}

/*---------------------------------------------------------------------------------
//함수 설명 :	입력창(값길이 체크 스크립트)
//---------------------------------------------------------------------------------    
//인자 설명 :	적용:<textarea>에서 사용시 onkeyup="gfc_chk_byte(this,적용byte수)"
//				aro_name : Object명 ari_max : 최대길이(byte)		 
--------------------------------------------------------------------------------*/
function gfc_chk_byte(aro_name,ari_max){

	var ls_str = aro_name.value; 				// 이벤트가 일어난 컨트롤의 value 값
	var li_str_len = ls_str.length; 			// 전체길이

	// 변수초기화
	var li_max = ari_max; 						// 제한할 글자수 크기
	var i = 0; 									// for문에 사용
	var li_byte = 0; 							// 한글일경우는 2 그밗에는 1을 더함
	var li_len = 0; 							// substring하기 위해서 사용
	var ls_one_char = ""; 						// 한글자씩 검사한다
	var ls_str2 = ""; 							// 글자수를 초과하면 제한할수 글자전까지만 보여준다.

	for(i=0; i< li_str_len; i++){
		
		// 한글자추출
		/**
		 * @param nm
		 * @param mt
		 * @param at
		 * @param tg
		 * @returns {___f17}
		 */
		ls_one_char = ls_str.charAt(i);

		// 한글이면 2를 더한다.
		if (escape(ls_one_char).length > 4){
			li_byte += 2;
		}else{	// 그밗의 경우는 1을 더한다.
			li_byte++;			
		}

		// 전체 크기가 li_max를 넘지않으면
		if(li_byte <= li_max){
			li_len = i + 1;
		}
	}

	// swal(li_str_len);
	
	// 전체길이를 초과하면
	if(li_byte > li_max){
		swal( "입력 내용이 " + li_max + "Byte를 초과하였습니다.\n 초과내용은 삭제됩니다. " );
		ls_str2 = ls_str.substr(0, li_len);
		aro_name.value = ls_str2;
	}
	aro_name.focus(); 
}

/*******************************************************************************
 * maxlength 체크
 * 
 * @param :
 *            Object
 * @param :
 *            최대 length
 * @desc : object의 최대 length를 초과하지 않도록 제안한다.
 * @사용예 : onkeyup="gfn_onkeylengthMax(this, 2000);"
 ******************************************************************************/
function gfc_chk_byte2(formobj, maxlength) {  
    var li_byte     = 0;  
    var li_len      = 0;  
    for(var i=0; i< formobj.value.length; i++){  
        if (escape(formobj.value.charAt(i)).length > 4){  
            li_byte += 3;  
        } else {  
            li_byte++;  
        }  
        if(li_byte <= maxlength) {  
            li_len = i + 1;  
        }  
    }      
    if(li_byte > maxlength){  
        swal('최대 글자 입력수를 초과 하였습니다.');  
        swal("li_byte::" + li_byte);
        formobj.value = formobj.value.substr(0, li_len);  
    }  
    formobj.focus();  
}  



/*
 * 동적 폼 생성 2013.01 Kim Yunkwan 스크립트 상에서 동적으로 폼을 생성하고 폼전송을 하기 위해 만듬
 */
function bindForm(name,method,url) {
	var f=document.createElement("form");
	f.setAttribute("name",name);
	f.setAttribute("method", method);
	f.setAttribute("action", url);
	document.body.appendChild(f);
	return f;
}

/*
 * 폼을 만든후 그에 따른 인자값을 세팅하기 위한 함수 사용법: var frm =
 * bindForm("frmname","post","http://test.co.kr");
 * frm.appendChild(addInput("inputname","inputvalue","hidden")); frm.submit();
 */
function addInput(name,value,type) {
	var i=document.createElement("input");
	i.setAttribute("type", type);
	i.setAttribute("name", name);
	i.setAttribute("value", value);
	return i;
} 

/* 우편번호 팝업 */
function post_popup_admin(ojbNmOpener, ojbNmZipCode1, ojbNmZipCode2, ojbNmAddress, ojbNmAddress2){	
	//var url = "/common.popup.dmPostPopup_admin.dp/dmparse.dm?"
	var url = "/Common.Popup.PostPopup.dp/proc.go?"		
	url = url + "ojbNmOpener="+ojbNmOpener+"&ojbNmZipCode1="+ojbNmZipCode1+"&ojbNmZipCode2="+ojbNmZipCode2+"&ojbNmAddress="+ojbNmAddress+"&ojbNmAddress2="+ojbNmAddress2;
	window.open(url, '', 'toolbar=no,top=10,left=700,width=393,height=420,scrollbars=no,resizable=no,menubar=no,location=no');
}
function post_popup(ojbNmOpener, ojbNmZipCode1, ojbNmZipCode2, ojbNmAddress, ojbNmAddress2, ssl, objPostNo){	
	//var url = "/common.popup.dmPostPopup.dp/dmparse.dm?"
	var url = "/Common.Popup.PostPopup.dp/proc.go?"
	if(ssl=='Y'){
		url = "/Common.Popup.SSL.PostPopup.dp/proc.go?"
	}
	url = url + "ssl="+ssl+"&ojbNmOpener="+ojbNmOpener+"&ojbNmZipCode1="+ojbNmZipCode1+"&ojbNmZipCode2="+ojbNmZipCode2+"&ojbNmAddress="+ojbNmAddress+"&ojbNmAddress2="+ojbNmAddress2+"&objPostNo="+objPostNo;
	window.open(url, '', 'toolbar=no,top=10,left=700,width=393,height=420,scrollbars=no,resizable=no,menubar=no,location=no');
}

/* 이용약관 팝업 */
function promise_popup(){	
	var url = "/daemyung.common.popup.promise_popup.ds/dmparse.dm";
	window.open(url, '', 'toolbar=no,top=10,left=700,width=640,height=675,resizable=no,menubar=no,location=no');
}

/* 개인정보 취급방침 팝업 */
function privacy_popup(){	
	var url = "/daemyung.common.popup.privacy_popup.ds/dmparse.dm";
	window.open(url, '', 'toolbar=no,top=10,left=700,width=640,height=675,resizable=no,menubar=no,location=no');
}

//각사업장 메인
function goSaupjangMain(sid){
	var url = "";
	if(sid == "30"){
		url = "/bs/";
	}else if(sid == "21"){
		url = "/dy/";
	}else if(sid=="29"){
		url = "/jj/";
	}else if(sid == "20"){
		url = "/gj/";
	}else if(sid == "22"){
		url = "/sb/";
	}else if(sid == "19"){
		url = "/yp/";
	}else if(sid == "32"){
		url = "/go/";
	}
	$(location).attr('href',url);
/*	
	$(location).attr('href',"/saupjang.common.sidemenu.selectSaupjangMain.dp/dmparse.dm?main_code="+sid);
*/	
}


/* ********************************************************************************자에게 누설, 배포하지 않습니다. 단, 전기통신 기본법 등 법률의 규정에 의해 국가기관의 요구가 있는 경우, 범죄에 대한 수사상의 목적이 있거나 정보통신 윤리위원회의 요청이 있는 경우 또는 기타 관계법령에서 정한 절차에 따른 요청이 있는 경우는 그러하지 않습니다.그외 회원의 개인정보에 관한 정책은 개인정보보호정책에 따릅니다.제 8 조 (회원 아이디(ID)와 비밀번호 관리에 관한 의무)아이디(ID)와 비밀번호에 관한 모든 관리책임은 회원에게 있습니다.회원에게 부여된 아이디(ID)와 비밀번호의 관리소홀, 부정사용에 의하여 발생하는 모든 결과에 대한 책임은 회원에게 있습니다.자신의 아이디(ID)가 부정하게 사용된 경우 회원은 반드시 회사에 그 사실을 통보해야 합니다
* selectbox 포커스이동
* @param  : id_from -작성중인 필드의 id
* @param  : id_to - 이동될 필드 id
* @param  : maxSize - 조건 사이즈
* @desc :  
* @사용예 : onkeyup="moveNext('id1','id2',4);"  id1의 갑의 길이가 4가 되면 id2포커스 이동

* *******************************************************************************/
function moveNext(id_from,id_to,maxSize) { 
    var cur = document.getElementById(id_from).value; 
    curSize = cur.length; 
 
    if (curSize == maxSize) { 
        document.getElementById(id_to).focus(); 
    } 
} 

function go_evtpkg(gubun, sid,meunLocation){
	if(gubun=="evt"){
		//$(location).attr('href',"/event.event.eventEventDetail.dp/dmparse.dm?fEvtSid="+sid);   
		$(location).attr('href',"/event.event.eventEventDetail.dp/dmparse.dm?fEvtSid="+sid+"&searchSaupjangGu="+meunLocation);
	}else if(gubun=="pkg"){
		$(location).attr('href',"/reservation.pkg.ReservationPkg.detail.dp/dmparse.dm?sPkgSid="+sid+"&sMenuLocation="+meunLocation);
	}
}

function go_event(evt_sid){
	$(location).attr('href',"/event.event.eventEventDetail.dp/dmparse.dm?fEvtSid="+evt_sid);
}

function go_pkg(pkg_sid,meunLocation){	
	$(location).attr('href',"/reservation.pkg.ReservationPkg.detail.dp/dmparse.dm?sPkgSid="+pkg_sid+"&sMenuLocation="+meunLocation);
}

/*----------------
 * Function name : SNS 글보내기
 * Description		 : SNS 글보내기
 * Parameter		 : msg, url
----------------*/
// 트위터
function sendTwitter(msg, url){
   var href = "http://twitter.com/home?status=" + encodeURIComponent(msg) + " " + encodeURIComponent(url);
   var a = window.open(href, 'twitter', '');
	
   if( a ){
	  a.focus();
   }
}

// 페이스북
function sendFaceBook(msg, url){
   var href = "http://www.facebook.com/sharer.php?u=" + encodeURIComponent(url) + "&t=" + encodeURIComponent(msg);
   var a = window.open(href, 'facebook', '');
	
   if( a ){
	  a.focus();
   }
}

// 미투데이(네이버)   
function sendMe2Day(msg, url, tag){
   var href = "http://me2day.net/posts/new?new_post[body]=" + encodeURIComponent(msg) + " " + encodeURIComponent(url) + "&new_post[tags]=" + encodeURIComponent(tag);
   var a = window.open(href, 'me2Day', '');
	
   if( a ){
	  a.focus();
   }
}

// C로그(네이트)
function sendClog(link, title, thumbnail, summary, writer){
   var href = "http://csp.cyworld.com/bi/bi_recommend_pop.php?url=" + encodeURIComponent(link) + "&thumbnail=" + encodeURIComponent(thumbnail) + "&summary=" + encodeURIComponent(summary) + "&writer=" +  encodeURIComponent(writer);
   var a = window.open(href, 'clog', 'width=400, height=364, scrollbars=no, resizable=no');

   if( a ){
	  a.focus();
   }
}

function CheckMaxLen(maxlen, textid) 
{ 
	var temp; //들어오는 문자값... 
	var msglen; 
	msglen = maxlen*2; 
	var value = $("#"+textid).val();
	l = value.length;
	tmpstr = "" ; 

	if (l == 0) 
	{ 
		value = maxlen*2; 
	} 
	else 
	{ 
		for(k=0; k < l;k++) 
		{ 
			temp =value.charAt(k); 
			
			if (escape(temp).length > 4) 
				msglen -= 2; 
			else 
				msglen--; 
			
			if(msglen < 0) 
			{ 
				swal("한글 " + maxlen + "자 까지 보내실수 있습니다.");
				$("#"+textid).val(tmpstr);
				break; 
			} 
			else 
			{ 
				tmpstr += temp; 
			} 
		} 
	} 
}

function printScreen(){
/*    $("#contents > div").scrollable({interval: 2000}).autoscroll();
    $("#contents").printPreview();*/
}


function fncheckNumVal(obj) {
	for(var i=1; i < obj.length; i++)
	{
		if(!('0' <= obj.charAt(i) && obj.charAt(i) <= '9')){
			return false;
		}
	}
	return true;
}


function storeviewPop(sId,pId){
	openPopup('viewPop', '/daemyung.front.saupjang.common.storeview_pop.ds/dmparse.dm?stViewId='+sId+'&pointId='+pId, '', '', '740', '440', '', '', 'no', 'yes');
}

//공통  부대시설 이동 >
function go_facilty(sid){
	$(location).attr('href',"/saupjang.common.facilit.getFacilit.dp/dmparse.dm?bizp_sid="+sid);		
}


function cusfileCheck(obj){
    var ua = window.navigator.userAgent;
    var filepath = obj;
    var fileInfo = pathToFile(filepath);
    var size;
    var _cnt = parseInt($("#isFileAddCnt").val());
    
    switch (fileInfo.extension.toUpperCase()) {
	    case "GIF" :
	    case "JPG" :
	    case "PNG" :
	    case "BMP" :
	    case "TIF" :
	    case "JPE" :
	    case "ZIP" : 
	    case "DOC" :
	    case "DOCX":
	    case "XLS" :
	    case "XLSX":
	    case "PPT" :
	    case "PDF" :
	    case "PPTX":
	    case "HWP":		
	    	_cnt += 1;
	    	$("#isFileAddCnt").val(""+_cnt);
	    	return true;
	    	break;
	    default:
		    return false;
    }
}

function fn_show_twitq(chk){
	
	if(chk == "Y"){
		swal("실시간 질문창을 이용하세요.");
		return;
	}else{
		if($("#_twit_qst")){
			if($("#_twit_qst").is(":visible")){
				$("#_twit_qst").hide();
				$jj('.t_qst').fadeOut('fast');
				$jj('.t_qst').css('z-index','0');
				$jj('.link_site').css('z-index','12');
				$jj('.util').css('z-index','11');
				$jj('h1').css('z-index','11');
				$jj('.visual').css('z-index','3');
				$jj('#gnb').css('z-index','10');
				$jj('#footer').css('z-index','6');				
			}else{
				$("#_twit_qst").show();
				$jj('.t_qst').fadeIn('fast');
				$jj('.t_qst').css('z-index','400');
				$jj('.link_site').css('z-index','1');
				$jj('.util').css('z-index','-1');
				$jj('h1').css('z-index','-1');
				$jj('.visual').css('z-index','-10');
				$jj('#gnb').css('z-index','-10');
				$jj('#footer').css('z-index','-6');				
			}		
		}		
	}

}

var __g;
function chk_twit_auth(g){
	__g = g;
	var chktwit= GetCookie('twitChk');
	var url = "/sns.twit.chkauth.dp/dmparse.dm";
	
	if($("#twit_qst_"+g).is(":focus")){
		if(chktwit != 'Y'){
			postAjax(url, "", tAuthRes);
		}		
	}

}

function tAuthRes(data){
	//swal(data.resultCode);
	if(data.resultCode == '-1'){
		twitAuth();
	}
}


function twitQuestion(g){
	
	var val = "";
	if(g !='' && g != undefined && g !=null)
		val = $("#twit_qst_"+g).val();

	var dataBody = {"qstValue":val,
					"wType":"U",
					"vType":g
					};
	var url = "/sns.twit.write.dp/dmparse.dm";
	
	postAjax(url,dataBody,twitQfinsh);
}

function twitQfinsh(data){
	if(data.resultCode > 0){
		if(data.resultMsg != ""){
			swal(data.resultMsg);
		}
		
		if(data.resultStrCode == 'L'){//LNB에서 들어온 질문에 대해서만 반응
			fn_show_twitq('N');
		}else if(data.resultStrCode == 'M'){ //리프레쉬
			loadTwit('1','10');
		}
		
	}else{
		swal("트위터로 질문하기가 실패하였습니다.");
	}
}

function twitAuth(){
	var width=600;
	var height=643;
	var sw=screen.width;
	var sh=screen.height;
	var x_pos=(sw/2)-(width/2);
	var y_pos=(sh/2)-(height/2);
	//SetCookie('TwitLogin','Y','');
	$("#btn_twit_"+__g).focus();
	openPopup('sns','/sns.twitauth.dp/dmparse.dm',x_pos,y_pos,width,height,'','','','yes');
}

function tistoryAuth(){
	var width=600;
	var height=643;
	var sw=screen.width;
	var sh=screen.height;
	var x_pos=(sw/2)-(width/2);
	var y_pos=(sh/2)-(height/2);
	
	var client_id="a679b51b30389e7e750b27118a933bb1";
	var redirecUri = "http://localhost/sns.oauth.rtntistory.dp/dmparse.dm";
	var response_type="code";
	//SetCookie('TwitLogin','Y','');
	//$("#btn_twit_"+__g).focus();
	openPopup('sns','https://www.tistory.com/oauth/authorize/?client_id='+client_id+'&redirect_uri='+redirecUri+'&response_type='+response_type,x_pos,y_pos,width,height,'','','','yes');
}


function roomTypeUseChk(strCondoCode, strPyungCode, strDongCode){
	if (strCondoCode == "25" || strCondoCode == "09" || strCondoCode == "13" || strCondoCode == "14" || strCondoCode == "17"
    || (strCondoCode == "18" && strPyungCode == "J")
    || (strCondoCode == "27" && strPyungCode == "AH")
    || (strCondoCode == "03" && (strPyungCode == "A" || strPyungCode == "B"))
    || (strCondoCode == "15" && (strPyungCode == "A" || strPyungCode == "B"))
    || (strCondoCode == "05" && (strPyungCode == "H" || strPyungCode == "I"))
    || (strCondoCode == "22" && (strPyungCode == "H" || strPyungCode == "I" || strPyungCode == "J" || strPyungCode == "AH"))
    || ((strCondoCode == "19" || strCondoCode == "20") && strPyungCode == "AH"
    ||  strCondoCode == "19" && ((strDongCode == "04" || strDongCode == "05") && strPyungCode == "L")
    || (strCondoCode == "19" && (strDongCode == "06" && strPyungCode=="AH"))
    || (strCondoCode == "19" && (strDongCode == "07" && (strPyungCode=="J" || strPyungCode=="K")) ))
       || (strCondoCode == "24" && (strPyungCode == "H" || strPyungCode == "I"))
    || (strCondoCode == "28" && (strPyungCode == "A" || strPyungCode == "B"))
    || (strCondoCode == "29" && (strPyungCode == "H" || strPyungCode == "I"))
	|| (strCondoCode == "06")	) {
		
		return true;
	}else return false;
}

//정의 : 오로지 숫자만 입력
//사용법 : 컨트롤에 onkeydown='onlyNumber()'를 쓰면된다. 
//한글입력안됨..."-"(그레이키쪽과 중앙쪽),엔터와 백스페이스,탭키,스페이스키,delete,insert,home/end/방향키값,그레이키숫자값,키보드위숫자값만 입력가능하게 한다.
function onlyNumber() {

	var ek = event.keyCode

	if (ek != 109 && ek != 190 && ek != 110 && ek != 189 && ek != 13 && ek != 9
			&& ek != 8 && ek != 32 && ek != 46 && ek != 45
			&& (ek < 34 || ek > 40) && (ek < 48 || ek > 57)
			&& (ek < 96 || ek > 105)) {
		// swal(ek);
		event.returnValue = false;
	}
}



function findTagByName(elem, tagName, name) {   
    var tags = elem.getElementsByTagName(tagName.toUpperCase());   
    for (var idx=0; idx<tags.length; idx++) {   
       if (tags[idx].name == name) return tags[idx];   
    }   
    return null;   
}  



/* ********************************************************************************
* 원하는 셀렉트박스에 파라미터로 넘어온 값을 선택
* @param  : Object값
* @param  : 선택되는 값
* @desc : 
* @주의 :
* @사용예 : selectCheck(frm.organTypeCd2, organTypeCd2);
* *******************************************************************************/
function gfn_selectCheck(obj, str){
	var len = obj.length;
	if(str != ''){
		for(i=0; i<len; i++){
			if(obj.options[i].value==str){
				obj.options[i].selected = true;
			}
		}
	}
}



/* ********************************************************
 * input   
 ******************************************************** */	
function gfn_inputReset(inputList){
	var Objs = "";
	for(var idx=0; idx<inputList.length; idx++){
		Objs = document.getElementsByName(inputList[idx]);

		switch(Objs[0].type){
			case "text" :
				Objs[0].value = "";
				break;
			case "radio" :
				for(var idx01=0; idx01<Objs.length; idx01++){
					Objs[idx01].checked = false;
				}
				break;
			case "checkbox" :
				for(var idx01=0; idx01<Objs.length; idx01++){
					Objs[idx01].checked = false;
				}
				break;
			case "hidden" :
				Objs[0].value = "";
				break;
			case "textarea" :
				Objs[0].value = "";
				break;
			case "select-one" :
				for(var i=0;i<Objs[0].options.length;i++){
					if(Objs[0].options[i].value == ''){
						Objs[0].options[i].selected = true;
					}
				}
				break;
		}
	}
}

/* ********************************************************
 * input(구분자가 포함된 Object 초기화)   
 ******************************************************** */	
function gfn_inputResetDiv(inputList, div){
	var Objs = "";
	for(var idx=0; idx<inputList.length; idx++){
		Objs = document.getElementsByName(inputList[idx]+div);

		switch(Objs[0].type){
			case "text" :
				Objs[0].value = "";
				break;
			case "radio" :
				for(var idx01=0; idx01<Objs.length; idx01++){
					Objs[idx01].checked = false;
				}
				break;
			case "checkbox" :
				for(var idx01=0; idx01<Objs.length; idx01++){
					Objs[idx01].checked = false;
				}
				break;
			case "hidden" :
				Objs[0].value = "";
				break;
			case "textarea" :
				Objs[0].value = "";
				break;
			case "select-one" :
				for(var i=0;i<Objs[0].options.length;i++){
					if(Objs[0].options[i].value == ''){
						Objs[0].options[i].selected = true;
					}
				}
				break;
		}
	}
} 


//스크린 사이즈구하기
//from yuki.kim
function wndsize() {
	var w = 0;
	var h = 0;
	// IE
	if (!window.innerWidth) {
		if (!(document.documentElement.clientWidth == 0)) {
			// strict mode
			w = document.documentElement.clientWidth;
			h = document.documentElement.clientHeight;
		} else {
			// quirks mode
			w = document.body.clientWidth;
			h = document.body.clientHeight;
		}
	} else {
		// w3c
		w = window.innerWidth;
		h = window.innerHeight;
	}
	return {
		width : w,
		height : h
	};
}

/* By yuki.kim */
/*
* 스크린 사이즈중에서 중앙 구하기
*/
function wndcent() {
	var hWnd = (arguments[0] != null) ? arguments[0] : {
		width : 0,
		height : 0
	};
	var _x = 0;
	var _y = 0;
	var offsetX = 0;
	var offsetY = 0;
	// IE
	if (!window.pageYOffset) {
		// strict mode
		if (!(document.documentElement.scrollTop == 0)) {
			offsetY = document.documentElement.scrollTop;
			offsetX = document.documentElement.scrollLeft;
		}
		// quirks mode
		else {
			offsetY = document.body.scrollTop;
			offsetX = document.body.scrollLeft;
		}
	}
	// w3c
	else {
		offsetX = window.pageXOffset;
		offsetY = window.pageYOffset;
	}
	_x = ((wndsize().width - hWnd.width) / 2) + offsetX;
	_y = ((wndsize().height - hWnd.height) / 2) + offsetY;
	return {
		x : _x,
		y : _y
	};
}

function numComma(str){
	str = String(str);
	return str.replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
}

function clearNumComma(str){
	str = String(str);
	return str.replace(/[^\d]+/g, '');
}

function inputNumberFormat(obj){
	obj.value = numComma(clearNumComma(obj.value));
}

function phoneType(str){
	str = String(str)
	return str.replace(/(\d{3})(\d{4})(\d{4})/, "$1-$2-$3");	
}

function inputPhoneFormat(obj){
	obj.value = phoneType(phoneType(obj.value));
}

function refresh(href,str)
{
	Query(str).load(href);
}

/*
 * 실명인증
 * */
function fnGetTrCerg(){ 
	var data="";
	var _url = "/identity.authRequest.dp/proc.go";
	
	postLoadigAjax(_url, data, fnSetTrCert,true,"");
}	

function fnSetTrCert(data){
//	console.log(data.tr_cert)
	$("#tr_cert").val(data.tr_cert);
	$("#tr_url").val(data.tr_url);
	
	openKMCISWindow();

}

function openKMCISWindow(){ 
	
	if($("#isAuthCheck").val() == 'Y'){
		swalDialog('이미 실명인증이 처리되었습니다.');
		return;
	}
	
    var UserAgent = navigator.userAgent;
    /* 모바일 접근 체크*/
    // 모바일일 경우 (변동사항 있을경우 추가 필요)
    if (UserAgent.match(/iPhone|iPod|Android|Windows CE|BlackBerry|Symbian|Windows Phone|webOS|Opera Mini|Opera Mobi|POLARIS|IEMobile|lgtelecom|nokia|SonyEricsson/i) != null || UserAgent.match(/LG|SAMSUNG|Samsung/) != null) {
   		 document.reqKMCISForm.target = '';
	  } 
	  // 모바일이 아닐 경우
	  else {
   		KMCIS_window = window.open('', 'KMCISWindow', 'width=425, height=550, resizable=0, scrollbars=no, status=0, titlebar=0, toolbar=0, left=435, top=250' );
   		
   		if(KMCIS_window == null){
  			swal(" ※ 윈도우 XP SP2 또는 인터넷 익스플로러 7 사용자일 경우에는 \n    화면 상단에 있는 팝업 차단 알림줄을 클릭하여 팝업을 허용해 주시기 바랍니다. \n\n※ MSN,야후,구글 팝업 차단 툴바가 설치된 경우 팝업허용을 해주시기 바랍니다.");
   		}
   		document.reqKMCISForm.target = 'KMCISWindow';
	  }
	  
	  document.reqKMCISForm.action = 'https://www.kmcert.com/kmcis/web/kmcisReq.jsp';

	  document.reqKMCISForm.submit();
  }

/*
 * 실명인증 종료
 * */

function loadScript(url,time,callback){
	   var head = document.getElementsByTagName('head')[0];
	   var script = document.createElement('script');
	   var _time = time;
	   //_time = 100;
	   script.type = 'text/javascript';
	   script.src = url;

	   if(callback !=null && callback !='' && typeof callback !='undefined'){
		   script.onreadystatechange = callback;
		   script.onload = callback;	   
	   }
	   //head.appendChild(script);
	   setTimeout(function() {
		   	head.appendChild(script);
			}, _time);
}

//숫자만 가능
function onlyNumber(obj) {
	$(obj).keyup(function(){
		$(this).val($(this).val().replace(/[^0-9]/g,""));
	});
}





