//将后台获取的类型转换 年月日
function yearToDate(date) {
    currentTime();
    //调用时
    var year = new Date(date).Format("yyyy-MM-dd");
    return year;
}
//将后台获取的类型转换 时分秒
function hourMinuteSecond(date) {
    currentTime();
    var hour = new Date(date).Format("HH:mm:ss");
    return hour;
}
//获取当前星期几
function  week() {
    var date = new Date();
    return date.getDate();
}

function currentTime(){
    //日期格式化
    Date.prototype.Format = function(fmt) {
        var o = {
            "M+": this.getMonth() + 1, //月份
            "d+": this.getDate(), //日
            "H+": this.getHours(), //小时
            "m+": this.getMinutes(), //分
            "s+": this.getSeconds(), //秒
            "q+": Math.floor((this.getMonth() + 3) / 3), //季度
            "S": this.getMilliseconds() //毫秒
        };
        if(/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
        for(var k in o)
            if(new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
        return fmt;
    }
}