/**
 * 通用库，服务于SSO, 用户校验用户信息录入
 */
(function() {
    var phonePattern = /^(13[0-9]\d{8}|15[0-35-9]\d{8}|18[0-9]\d{8}|17[0-9]\d{8}|14[57]\d{8})$/;
    var emailPattern = /^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;

    var isPhone = function(phone) {
        return phonePattern.test(phone);
    };

    var isEmail = function(email){
        return emailPattern.test(email);
    };

    var isChina = function(msg) {  // 是否是汉字
        var china=/[\u4E00-\u9FA0]/;
        return china.test(msg);
    };

    var isRealName = function(name) {
    	return !/[\\\/:\*\?\"'<>| ]/.test(name);
    };

    var checkUsername = function(t, $help) {
        if (t === '用户名' || /^[0-9]+/.test(t)) {
            $help.html('用户名不合法');
            return false;
        }
        if (t.length === 0) {
            $help.html('用户名不能为空');
            return false;
        }
        /*
        if (!(5 <= t.length && t.length <= 32)) {
            $help.html('用户名长度为5到32位');
            return false;
        }
        */
        // else if (!(/^(\w){5,32}$/).test(username)) {
        // 过滤特殊字符
        var pat = new RegExp("[`~!@#$^&*()=|{}':;',\\[\\].<>/?~！@#￥……&*（）——|{}【】‘；：”“'。，、？]");
        if (pat.test(t)) {
            $help.html('用户名中不能有特殊字符');
            return false;
        }

        return true;
    };

    // 生成6位短信验证码
    var getSMSCode = function() {
        var code = '';
        var s = '0123456789';
        for (var i = 0; i < 6; i++) {
            code += s.charAt(parseInt(Math.random() * s.length, 10));
        }
        return code;
    };

    var timeCountDown = function(timeCtrlId) {
        $(timeCtrlId).val("60s后重新获取").css({"background":"#ddd","color":"#999","border":"1px solid #eee"}).attr("disabled",true);
        var countdown=59;
        var timer = setInterval(function() {
            if (0 === countdown) {
                $(timeCtrlId).val("免费获得验证码").css({"background":"#286090","color":"#fff","border":"1px solid #2e6da4"}).attr("disabled",false);
                countdown = 59;
                window.clearInterval(timer);
            } else {
                $(timeCtrlId).val(countdown+"s后重新获取").css({"background":"#ddd","color":"#999","border":"1px solid #eee"}).attr("disabled",true);
                countdown--;
            }
        }, 1000);
    };

    var isIDCardNo = function(no) {
        return idCardNoUtil.checkIdCardNo(no);
    };

    var getIDCardInfo = function(no) {
        return idCardNoUtil.getIdCardInfo(no);
    };

    /**
     * 验证身份证号码
     * 入口方法: checkIdCardNo
     */
    var idCardNoUtil = {
        /*省,直辖市代码表*/
        provinceAndCitys: {11:"北京",12:"天津",13:"河北",14:"山西",15:"内蒙古",21:"辽宁",22:"吉林",23:"黑龙江",
            31:"上海",32:"江苏",33:"浙江",34:"安徽",35:"福建",36:"江西",37:"山东",41:"河南",42:"湖北",43:"湖南",44:"广东",
            45:"广西",46:"海南",50:"重庆",51:"四川",52:"贵州",53:"云南",54:"西藏",61:"陕西",62:"甘肃",63:"青海",64:"宁夏",
            65:"新疆",71:"台湾",81:"香港",82:"澳门",91:"国外"},

        /*每位加权因子*/
        powers: ["7","9","10","5","8","4","2","1","6","3","7","9","10","5","8","4","2"],

        /*第18位校检码*/
        parityBit: ["1","0","X","9","8","7","6","5","4","3","2"],

        /*性别*/
        genders: {male:"男",female:"女"},

        /*校验地址码*/
        checkAddressCode: function(addressCode) {
            var check = /^[1-9]\d{5}$/.test(addressCode);
            if(!check) return false;
            if(idCardNoUtil.provinceAndCitys[parseInt(addressCode.substring(0,2))]) {
                return true;
            } else {
                return false;
            }
        },

        /*校验日期码*/
        checkBirthDayCode: function(birDayCode) {
            var check = /^[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))$/.test(birDayCode);
            if (!check) return false;
            var yyyy = parseInt(birDayCode.substring(0, 4), 10);
            var mm = parseInt(birDayCode.substring(4, 6), 10);
            var dd = parseInt(birDayCode.substring(6), 10);
            var xdata = new Date(yyyy, mm - 1, dd);
            if (xdata > new Date()) {
                return false;//生日不能大于当前日期
            }
            return ( xdata.getFullYear() === yyyy ) && ( xdata.getMonth() === mm - 1 ) && ( xdata.getDate() === dd );
        },

        /*计算校检码*/
        getParityBit: function(idCardNo) {
            var id17 = idCardNo.substring(0,17);
            /*加权 */
            var power = 0;
            for (var i=0;i<17;i++) {
                power += parseInt(id17.charAt(i),10) * parseInt(idCardNoUtil.powers[i]);
            }
            /*取模*/
            var mod = power % 11;
            return idCardNoUtil.parityBit[mod];
        },

        /*验证校检码*/
        checkParityBit: function(idCardNo){
            var parityBit = idCardNo.charAt(17).toUpperCase();
            return (idCardNoUtil.getParityBit(idCardNo) === parityBit);
        },

        /*校验15位或18位的身份证号码*/
        checkIdCardNo: function(idCardNo) {
            // 15位和18位身份证号码的基本校验
            var check = /^\d{15}|(\d{17}(\d|x|X))$/.test(idCardNo);
            if(!check) return false;
            // 判断长度为15位或18位
            if(idCardNo.length==15) {
                return idCardNoUtil.check15IdCardNo(idCardNo);
            } else if(idCardNo.length==18) {
                return idCardNoUtil.check18IdCardNo(idCardNo);
            } else {
                return false;
            }
        },

        //校验15位的身份证号码
        check15IdCardNo: function(idCardNo){
            // 15位身份证号码的基本校验
            var check = /^[1-9]\d{7}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}$/.test(idCardNo);
            if(!check) return false;
            // 校验地址码
            var addressCode = idCardNo.substring(0,6);
            check = idCardNoUtil.checkAddressCode(addressCode);
            if(!check) return false;
            var birDayCode = '19' + idCardNo.substring(6,12);
            // 校验日期码
            return idCardNoUtil.checkBirthDayCode(birDayCode);
        },

        //校验18位的身份证号码
        check18IdCardNo: function(idCardNo){
            // 18位身份证号码的基本格式校验
            var check = /^[1-9]\d{5}[1-9]\d{3}((0[1-9])|(1[0-2]))((0[1-9])|([1-2][0-9])|(3[0-1]))\d{3}(\d|x|X)$/.test(idCardNo);
            if(!check) return false;
            // 校验地址码
            var addressCode = idCardNo.substring(0,6);
            check = idCardNoUtil.checkAddressCode(addressCode);
            if(!check) return false;
            // 校验日期码
            var birDayCode = idCardNo.substring(6,14);
            check = idCardNoUtil.checkBirthDayCode(birDayCode);
            if(!check) return false;
            // 验证校检码
            return idCardNoUtil.checkParityBit(idCardNo);
        },

        formateDateCN: function(day){
            var yyyy =day.substring(0,4);
            var mm = day.substring(4,6);
            var dd = day.substring(6);
            return yyyy + '-' + mm +'-' + dd;
        },

        //获取信息
        getIdCardInfo: function(idCardNo){
            var idCardInfo = {
                area: "",
                gender:"",  //性别
                birthday:"" // 出生日期(yyyy-mm-dd)
            };
            var aDay = null;
            if(idCardNo.length==15) {
                aDay = '19' + idCardNo.substring(6,12);
                idCardInfo.birthday=idCardNoUtil.formateDateCN(aDay);
                if(parseInt(idCardNo.charAt(14))%2==0){
                    idCardInfo.gender=idCardNoUtil.genders.female;
                }else{
                    idCardInfo.gender=idCardNoUtil.genders.male;
                }
            } else if(idCardNo.length==18){
                aDay = idCardNo.substring(6,14);
                idCardInfo.birthday=idCardNoUtil.formateDateCN(aDay);
                if(parseInt(idCardNo.charAt(16))%2==0){
                    idCardInfo.gender=idCardNoUtil.genders.female;
                }else{
                    idCardInfo.gender=idCardNoUtil.genders.male;
                }
                idCardInfo.area = idCardNoUtil.provinceAndCitys[idCardNo.substr(0,2)];
            }
            return idCardInfo;
        },

        /*18位转15位*/
        getId15: function(idCardNo){
            if(idCardNo.length==15){
                return idCardNo;
            }else if(idCardNo.length==18){
                return idCardNo.substring(0,6) + idCardNo.substring(8,17);
            }else{
                return null;
            }
        },

        /*15位转18位*/
        getId18: function(idCardNo) {
            if(idCardNo.length==15){
                var id17 = idCardNo.substring(0,6) + '19' + idCardNo.substring(6);
                var parityBit = idCardNoUtil.getParityBit(id17);
                return id17 + parityBit;
            }else if(idCardNo.length==18){
                return idCardNo;
            }else{
                return null;
            }
        }
    };

    return window.TLibSSO = {
        isPhone: isPhone,
        isEmail: isEmail,
        isChina: isChina,
        isRealName: isRealName,
        checkUsername: checkUsername,
        getSMSCode: getSMSCode,
        timeCountDown: timeCountDown,
        isIDCardNo: isIDCardNo,
        getIDCardInfo: getIDCardInfo
    };
})();