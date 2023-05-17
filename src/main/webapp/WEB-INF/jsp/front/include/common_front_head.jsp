<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
    String ctxPath = (String) request.getContextPath();
%>
<meta charset="utf-8">
<META http-equiv="Expires" content="-1">
<META http-equiv="Pragma" content="no-cache">
<META http-equiv="Cache-Control" content="No-Cache">
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="format-detection" content="telephone=no"/>
<meta name="viewport" content="width=device-width,initial-scale=1.0,minimum-scale=1.0,maximum-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge, chrome=1">
<meta name="apple-mobile-web-app-title" content="">
<meta name="description" content="RNR">
<meta name="keywords" content="RNR">
<meta property="og:site_name" content="RNR Wallet">
<meta property="og:type" content="website">
<meta property="og:title" content="RNR Wallet">
<meta property="og:description" content="">
<meta property="og:url" content="">
<meta property="og:image" content="">
<meta name="description" content="RNR Wallet">
<link rel="shortcut icon" href="/images/front/favicon.ico">

<link rel="stylesheet" type="text/css" href="/css/layout.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/contents.css?v=<%=System.currentTimeMillis() %>" media="all" />
<link rel="stylesheet" type="text/css" href="/css/flat/blue.css" media="all" />
<link type="text/css" rel="stylesheet" href="/jquery/themes/blue/jquery.ui.all.css">
<link rel="stylesheet" type="text/css" href="/css/responsive.dataTables.min.css" media="all" />
<link rel="stylesheet" type="text/css" href="/css/swiper.min.css" media="all" />


<script type="text/javascript" src="/js/jquery-3.1.0.min.js"></script>
<script type="text/javascript" src="/js/jquery-ui.js"></script>
<!-- <script src= "https://cdn.zingchart.com/zingchart.min.js"></script> -->
<script type="text/javascript" src="/js/cme.mobile.core-1.0.0.js"></script>
<script type="text/javascript" src="/js/qrcode.js"></script>
<script type="text/javascript" src="/js/cl.js"></script>
<script type="text/javascript" src="/js/modernizr.js"></script>
<script type="text/javascript" src="/js/global.js"></script>
<script type="text/javascript" src="/js/icheck.js"></script>
<script type="text/javascript" src="/js/sweetalert.min.js"></script>
<script type="text/javascript" src="/js/semantic.min.js"></script>
<script type="text/javascript" src="/js/main.js"></script>
<script type="text/javascript" src="/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="/js/dataTables.responsive.min.js"></script>
<script type="text/javascript" src="/js/cmePassChk.js"></script>
<script type="text/javascript" src="/js/jquery.cookie.js"></script>
<script type="text/javascript" src="/js/clipboard.min.js"></script>

<script>

    function resAndroidBackButton() {
        swal({
            text : "<spring:message code='TEXT_409'/>",
            buttons: {
                "<spring:message code='TEXT_318'/>": true,
                cancel: "<spring:message code='TEXT_361'/>"
            }
        }).then(function(res) {
            if(res != null) {
                window.infJs.reqExit();
            }
        })
    }


</script>




<%--190604 임승연 추가--%>


<script type="text/javascript" src="/js/dmenc/enc_aes.js"></script>
<script type="text/javascript" src="/js/dmenc/base64.js"></script>
<script type="text/javascript" src="/js/dmenc/jsbn.js"></script>
<script type="text/javascript" src="/js/dmenc/prng4.js"></script>
<script type="text/javascript" src="/js/dmenc/rng.js"></script>
<script type="text/javascript" src="/js/dmenc/rsa.js"></script>
<script type="text/javascript" src="/js/dmenc/tea-block.js"></script>
<script type="text/javascript" src="/js/dmenc/utf8.js"></script>

<script type="text/javascript">
    var MSG_JS = {};

    $(document).ready(function() {
        MSG_JS.TEXT0 = '<spring:message code="TEXT_378" javaScriptEscape="true"/>'
        MSG_JS.TEXT1 = '<spring:message code="TEXT_374" javaScriptEscape="true"/>';
        MSG_JS.TEXT2 = '<spring:message code="TEXT_375" javaScriptEscape="true"/>';
        MSG_JS.TEXT3 = '<spring:message code="TEXT_376" javaScriptEscape="true"/>';
        MSG_JS.TEXT4 = '<spring:message code="TEXT_377" javaScriptEscape="true"/>';
        MSG_JS.TEXT5 = '<spring:message code="TEXT_418" javaScriptEscape="true"/>';
    });
    function dec2hex (dec) { //t_arr : 랜덤숫자1,랜덤숫자2,랜덤숫자3........ 16
        return ('0' + dec.toString(16)).substr(-2) //.toString(16) : 16진수로표현  '0'+랜덤숫자1.toString(16),substr(-2)
    }

    // generateId :: Integer -> String
    function generateId (len) { //32
        var arr = new Uint8Array((len || 40) / 2);
        //부호없는1바이트정수배열
        //var arr1 = new Uint8Array((32 || 40)); //0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0  (32)
        //var arr2 = new Uint8Array((32 || 40) / 2); //0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 (16)

        var cryptoObj = window.crypto || window.msCrypto; //[object Crypto]
        cryptoObj.getRandomValues(arr)
        //랜덤으로 16자리 숫자 생성

        var t_arr = [];
        var text = "";
        for(var i=0; i< arr.length; i++) { //arr.length : 16
            t_arr.push(arr[i]) //t_arr : 랜덤숫자1,랜덤숫자2,랜덤숫자3........ 16
            var token = dec2hex(t_arr[i]); //t_arr 의 0번째 숫자 , 1번째숫자..... 15
            text += token; // 처음에는 text 에 빈값 text = 0 + dec2hex값
        }

        return text;

    }

    //res = EncryptRSA($('#RSAModulus').val(), $('#RSAExponent').val(), randomKey);
    function EncryptRSA(m, e, text){
        var rsa = new RSAKey();
        rsa.setPublic(m, e); //rsa.js 에서 $('#RSAModulus').val(), $('#RSAExponent').val() set해주고!

        return rsa.encrypt(text); //rsa.js 에서 randomKey set해주고 return해준다.
    }

    //enc_pwd = EncryptAES(pw, randomKey);
    function EncryptAES(plain_text, k)
    {
        GibberishAES.size(256);

        var enc =  GibberishAES.aesEncrypt(plain_text, k);
        enc = enc.replace(/&/g,"^26");
        enc = enc.replace(/\+/g,"^2B");
        enc = enc.replace(/\//g,"^47");
        return enc;
    }
</script>
