/*
 *  CME Mobile Library Core
 *  - 만든이 : 민병철
 *  - 개정이력 :
 *  			2018.10.29 	- BK -> CME 시작
 *  						- postSubmit, ajax, isDevice, isAndroid, isIOS 를 제외하고 전부 삭제
 *  						- cme.mobile.const-1.0.0.js 추가
 *
 *  			2018.12.18 	- getMsg 추가
 *
 *
 */


var CME = {};

CME.PAGE_DATA = {};

Object.defineProperties(CME, {
    postSubmit : {
        value : function(url, _list) {
            var list = _list || [];

            var $form = $("<form></form>");

            list.forEach(function(data) {
                data.value = encodeURIComponent(data.value)
                var input = $("<input type='hidden' name='" + data.name + "' value='" + data.value + "'/>");
                $form.append(input);
            });
            $form.attr("method", "post");
            $form.attr("action", url);
            $form.attr("acceptCharset", "UTF-8");

            $("body").append($form);
            $form.submit();
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties( CME, {
    clearNumComma : {
        value : function(str) {
            str = String(str);
            return str.replace(/,/gi, '');
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties( CME, {
    ajax : {
        value : function(pack) {

            $.ajax({
                url : pack.url,
                type : 'POST',
                data : pack.data || {},
                dataType : 'json',
                cache : false,
                timeout : 0,
                success : function(res) {
                    if(res.mErrorCode) {
                        CME.popup({
                            content : CME.getMsg(res.mErrorCode, "mErrorCode")
                        })
                    }
                    else if(pack.success) {
                        pack.success(res)
                    }
                },
                fail : function(res) {
                    if(pack.fail) {
                        pack.fail(res)
                    }
                },
                beforeSend : function() {
                    var onOff = pack.onOffBar || "N";
                    if(onOff != "Y") {
                        CME.loadingBar("Y");
                    }
                },
                complete : function() {
                    CME.loadingBar("N");
                },
                error : function(e) {
                    CME.loadingBar("N");
                }
            });
        },
        editable : false,
        configurable : false,
        enumerable : true
    }

});

Object.defineProperties(CME, {
    logout :
        {
            value : function(onOff, msg) {
                CME.popup({
                    content : CME.getMsg("M0002", "incHeaderTag"),
                    btn_cnt : 2,
                    callback1 : function() {
                        CME.ajax({
                            url : "/m.login.actionLogout.dp/proc.go",
                            success : success
                        });
                        function success(res) {
                            if(res.failCd == "") {
                                if(window.infJs)
                                    window.infJs.cancelAlwaysLogin();

                                CME.goPage("/m.login.loginView.dp/proc.go");
                            }
                        }

                    }
                });
            }
        }
});

Object.defineProperties(CME, {
    loadingBar :
        {
            value : function(onOff, msg) {
                msg = msg == undefined ? "" : msg;
                /*if(window.infJs)
                    window.infJs.loadingBar(onOff, msg)*/
                if(onOff == "Y")
                {
                    $('body').prepend("<div id='ajaxScreen' style=\"position:fixed;width:100%;height:100%;opacity:0.5;z-index:100000;background: url('/images/mobile/global/rolling.svg') no-repeat black 50% 50%;\"></div>");
                }
                else
                {
                    $("#ajaxScreen").remove();
                }
            }
        }
});

Object.defineProperties(CME, {
    isDevice :
        {
            value : function()
            {
                if(CME.isIOS() || CME.isAndroid())
                {
                    return true;
                }
                else
                {
                    return false;
                }
            },
            editable : false,
            configurable : false,
            enumerable : true
        }
});

Object.defineProperties(CME,
    {
        isAndroid :
            {
                value : function()
                {
                    if(window.infJs && !window.infJs.type)
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                },
                editable : false,
                configurable : false,
                enumerable : true
            }
    });

Object.defineProperties(CME,
    {
        isIOS :
            {
                value : function()
                {
                    if(window.infJs && window.infJs.type) {
                        return true;
                    } else {
                        return false;
                    }
                },
                editable : false,
                configurable : false,
                enumerable : true
            }
    });

Object.defineProperties(CME,
    {
        callDeviceFunc :
            {
                value : function(funcName, params)
                {
                    if(!CME.isDevice())
                    {
                        return false;
                    }

                    if(CME.isAndroid())
                    {
                        if(!params)
                        {
                            window.infJs[funcName]();
                        }
                        else
                        {
                            if(params.length == 1)
                            {
                                window.infJs[funcName](params[0]);
                            }
                            else if(params.length == 2)
                            {
                                window.infJs[funcName](params[0], params[1]);
                            }
                            else if(params.length == 3)
                            {
                                window.infJs[funcName](params[0], params[1], params[2]);
                            }
                            else if(params.length == 4)
                            {
                                window.infJs[funcName](params[0], params[1], params[2], params[3]);
                            }
                            else
                            {

                            }
                        }
                    }
                    else
                    {
                        if(!params)
                        {

                            window.webkit.messageHandlers[funcName].postMessage("");
                        }
                        else
                        {
                            if(params.length == 1)
                            {
                                window.webkit.messageHandlers[funcName].postMessage(params[0]);
                            }
                            else if(params.length == 2)
                            {
                                window.webkit.messageHandlers[funcName].postMessage(params[0], params[1]);
                            }
                            else if(params.length == 3)
                            {
                                window.webkit.messageHandlers[funcName].postMessage(params[0], params[1], params[2]);
                            }
                            else if(params.length == 4)
                            {
                                window.webkit.messageHandlers[funcName].postMessage(params[0], params[1], params[2], params[3])
                            }
                            else
                            {

                            }
                        }
                    }
                }
            }
    });

Object.defineProperties( CME, {
    popup : {
        // todo 버튼 2개에 대한 팝업 디자인이 없다...
        value : function popup(option) {
            $("#ajaxScreen").remove();

            var this_pop = $(''
                + '<div class="popup_wrap popNum1" id="bk_dynamic_pop" style="z-index:100000">'
                + '<div class="popup">'
                + '<div class="innerHead">'
                + '<h3 id="bk_pop_title"></h3><a href="#n" class="btn_popClose" id="dynamic_popCloseBtn"><i class="xi-close-thin"></i></a></h3>'
                + '</div>'
                + '<div class="innerMid">'
                + '<p id="bk_pop_content" style="word-break:break-all"></p>'
                + '</div>'
                + '<div class="innerFoot">'
                + '<ul class="col-1">'
                + '<li>'
                + '<a class="btn_submit" id="bk_btn_1"></a>'
                + '<a class="btn_submit" id="bk_btn_2"></a>'
                + '</li>'
                + '</ul>'
                + '</div>' + '</div>' + '</div>'
                + '');

            option = {
                "btn_cnt" : option.btn_cnt || 1,
                "title" : option.title || __MSG[__LANG]["core"]["M0000"] + "<span>" + __MSG[__LANG]["core"]["M0001"] + "</span>",
                "content" : option.content,
                "btn1_txt" : option.btn1_txt || __MSG[__LANG]["core"]["M0002"],
                "btn2_txt" : option.btn2_txt || __MSG[__LANG]["core"]["M0003"],
                "callback1" : option.callback1,
                "callback2" : option.callback2,
                "cancel" : option.cancel
            }

            this_pop.find("#bk_pop_title").html(option.title);
            this_pop.find("#bk_pop_content").html(option.content);
            this_pop.find("#bk_btn_1").html(option.btn1_txt);
            this_pop.find("#bk_btn_2").html(option.btn2_txt);

            this_pop.find("#dynamic_popCloseBtn").off("click").on("click", function(e) {
                e.preventDefault();

                $("#bk_dynamic_pop").remove();

                if(option.cancel != undefined) {
                    option.cancel();
                }

            })
            this_pop.find("#bk_btn_1").off("click").on("click", function(e) {
                e.preventDefault();

                $("#bk_dynamic_pop").remove();
                if (option.callback1 != undefined) {
                    option.callback1();
                }
                $("html, body").css("overflow","visible");
                return false;
            });

            if (option.btn_cnt == 1) {
                this_pop.find("#bk_btn_2").remove();
                //this_pop.find("#bk_btn_2").parent().parent().removeClass("btn2");
                //this_pop.find("#bk_btn_2").parent().parent().addClass("btn1");
            } else {

                this_pop.find("#bk_btn_1").css({"width" : "49%", "float":"left", "margin-right":"5px"})
                this_pop.find("#bk_btn_2").css({"width" : "49%", "float":"left" })
                this_pop.find(".innerFoot").css({"height":"80px"})
                this_pop.find("#bk_btn_2").off("click").on("click", function(e) {
                    e.preventDefault();

                    $("#bk_dynamic_pop").remove();
                    if (option.callback2 != undefined) {
                        option.callback2();
                    }
                    $("html, body").css("overflow", "visible");
                    return false;
                });
            }

            $("#bk_dynamic_pop").remove();
            $("body").append(this_pop);
            $("#bk_dynamic_pop").css("display", "block");
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties(CME, {
    goPage :
        {
            value : function (url)
            {
                location.href = url;
            }

        }
});

Object.defineProperties(CME, {
    fmt : {
        value : function(str) {
            var res = CME.fixed(str, 8);
            return res;
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties(CME, {
    fixed : {
        value : function(str, count) {
            if(count == undefined) {
                count = 8;
            }

            if (!str) return "";

            if ((String(str)).length > 0) {
                return CME.numComma(parseFloat(CME.clearNumComma(str)).toFixed(count));
            } else {
                return "";
            }
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
})

Object.defineProperties(CME, {
    numComma : {
        value : function(str) {
            str = String(str);
            var strArray = str.split(".");
            if (strArray.length >= 2) {
                if(strArray[0] == "") {
                    return "0." + strArray[1];
                } else {
                    return strArray[0].replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,') + "." + strArray[1];
                }
            } else {
                return strArray[0].replace(/(\d)(?=(?:\d{3})+(?!\d))/g, '$1,');
            }
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties(CME, {
    goBack :
        {
            value : function (url)
            {
                if ($("#bk_dynamic_pop").length > 0) {
                    $("#bk_dynamic_pop").remove();
                    return false;
                }

                var url = location.href;

                if(url.includes("loginView"))
                {
                    CME.popup({
                        content : CME.getMsg("M0000", "interface"),
                        btn_cnt : 2,
                        callback1 : function() {
                            CME.callDeviceFunc("appExit");
                        }
                    });
                }
                else if(url.includes("mainView"))
                {
                    //CME.logout();
                    CME.popup({
                        content : CME.getMsg("M0000", "interface"),
                        btn_cnt : 2,
                        callback1 : function() {
                            CME.callDeviceFunc("appExit");
                        }
                    });
                }
                else if(url.includes("joinView_01_2"))
                {
                    CME.goPage("/m.login.loginView.dp/proc.go");
                }
                else if(url.includes("joinView"))
                {
                    CME.popup({
                        content : CME.getMsg("M0001", "common"),
                        btn_cnt : 2,
                        callback1 : function() {
                            CME.goPage("/m.login.loginView.dp/proc.go");
                        }
                    });
                }
                else if(url.includes("historyView"))
                {
                    CME.goPage("/m.main.mainView.dp/proc.go");
                }
                else if(url.includes("mypageView"))
                {
                    CME.goPage("/m.main.mainView.dp/proc.go");
                }
                else
                {
                    window.history.back();
                }
            }

        }
});

CME.PROGRESS_BAR = {};

Object.defineProperties(CME.PROGRESS_BAR, {
    show : {
        value : function(message) {
            $('body').prepend("<div id='ajaxScreen' style=\"position:fixed;width:100%;height:100%;opacity:0.5;z-index:100000;background: url('/images/front/common/rolling.svg') no-repeat black 50% 50%;\"></div>");
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties(CME.PROGRESS_BAR, {
    hide : {
        value : function() {
            /*if (CME.isDevice()) {
                CME.callDeviceFunc("hideProgressBar");
            } else {
                $("#ajaxScreen").remove();
            }*/

            $("#ajaxScreen").remove();

        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

CME.VALIDATOR = {};

Object.defineProperties( CME.VALIDATOR, {
    email : {
        value : function(target) {
            var EMAIL_VALIDATOR = /^[A-Za-z0-9_\.\-]+@[A-Za-z0-9\-]+\.[A-Za-z0-9\-]+/;

            if (target == null || target == '') {
                CME.popup({
                    content : CME.getMsg("M16", "core")
                });
                return false;
            }

            if (!EMAIL_VALIDATOR.test(target)) {
                CME.popup({
                    content : CME.getMsg("M17", "core")
                });
                return false;
            }

            return true;
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties(CME.VALIDATOR, {
    name : {
        value : function(target) {
            if (target == null || target == '') {
                return false;
            }

            return true;
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties( CME.VALIDATOR, {
    password : {
        value : function(target) {
            var pwEtcRules = /^(?=.*[a-zA-Z])(?=.*[~!@\#$%<>^&*\()\-=+_\’])(?=.*[0-9]).{8,12}$/;
            var pwRules = /^[a-zA-Z0-9]{8,12}$/;
            var regEtc =/[~!@\#$%<>^&*\()\-=+_\’]/gi;
            var chkKor = /([가-힣ㄱ-ㅎㅏ-ㅣ\x20])/i; // 한글 정규식

            var chkEngOverlap = /(abc)|(bcd)|(cde)|(def)|(efg)|(fgh)|(ghi)|(hij)|(ijk)|(jkl)|(klm)|(lmn)|(mno)|(nop)|(opq)|(pqr)|(qrs)|(rst)|(stu)|(tuv)|(uvw)|(vwx)|(wxy) |(xyz)/; // 연속된
                                                                                                                                                                                    // 영문자
                                                                                                                                                                                    // 정규식
            var chkNumOverlap = /(012)|(123)|(234)|(345)|(456)|(567)|(678)|(789)|(890)|(098)|(987)|(876)|(765)|(654)|(543)|(432)|(321)|(210)/; // 연속된
            // 숫자
            // 정규식

            var ckNum = target.search(/[0-9]/g);
            var ckEng = target.search(/[a-z]/ig);
            var ckEtc = target.search(regEtc);

            var str = "";
            if (chkKor.test(target)) {
                return CME.getMsg("M19", "core")
            }

            if (!regEtc.test(target)) {

                if (!pwEtcRules.test(target)) {
                    return CME.getMsg("M20", "core")
                }

                if (ckNum < 0 || ckEng < 0 || ckEtc < 0) {
                    return CME.getMsg("M20", "core")
                }
            }

            if(/(\w)\1\1/.test(target)){
                return CME.getMsg("M22", "core")
            }

            if(chkEngOverlap.test(target.toLowerCase())) {
                return CME.getMsg("M23", "core")
            }

            if(chkNumOverlap.test(target)) {
                return CME.getMsg("M24", "core")
            }

            return "";
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties( CME.VALIDATOR, {
    birth : {
        value : function(target) {
            var format = /^(19[0-9][0-9]|20\d{2})(0[0-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])$/;
            if(!format.test(target)){
                return false;
            }

            return true;
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});

Object.defineProperties( CME.VALIDATOR, {
    phone : {
        value : function(target) {
            var format = /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g;
            if(!format.test(target)){
                return false;
            }

            return true;
        },
        editable : false,
        configurable : false,
        enumerable : true
    }
});


// ///////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////
// ///////////////////////////////////////////////////////////////