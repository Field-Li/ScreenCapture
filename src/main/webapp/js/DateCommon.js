/**
 * Created by shi_y on 2016/8/29.
 * 事件操作公用方法
 */
// 增加天
function AddDays(date, value) {
    return date.setDate(date.getDate() + value);
}

// 增加月
function AddMonths(date, value) {
    return date.setMonth(date.getMonth() + value);
}

// 增加年
function AddYears(date, value) {
    return date.setFullYear(date.getFullYear() + value);
}

// 是否为今天
function IsToday(date) {
    return IsDateEquals(date, new Date());
}

// 是否为当月
function IsThisMonth(date) {
    return IsMonthEquals(date, new Date());
}


//比较2个日期大小
function DateCompare(a, b) {
    var starttime = ConvertDate(a);
    var starttimes = starttime.getTime();

    var lktime = ConvertDate(b);
    var lktimes = lktime.getTime();

    if (starttimes > lktimes) {
        return false;
    }
    else
        return true;
}

// 两个日期的年是否相等
function IsMonthEquals(date1, date2) {
    return date1.getMonth() == date2.getMonth() && date1.getFullYear() == date2.getFullYear();
}

// 判断日期是否相等
function IsDateEquals(date1, date2) {
    return date1.getDate() == date2.getDate() && IsMonthEquals(date1, date2);
}

// 返回某个日期对应的月份的天数
function GetMonthDayCount(date) {
    switch (date.getMonth() + 1) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:
            return 31;
        case 4:
        case 6:
        case 9:
        case 11:
            return 30;
    }
//feb:
    date = new Date(date);
    var lastd = 28;
    date.setDate(29);
    while (date.getMonth() == 1) {
        lastd++;
        AddDays(date, 1);
    }
    return lastd;
}

// 返回两位数的年份
function GetHarfYear(date) {
    var v = date.getYear();
    if (v > 9)return v.toString();
    return "0" + v;
}

// 返回月份（修正为两位数）
function GetFullMonth(date) {
    var v = date.getMonth() + 1;
    if (v > 9)return v.toString();
    return "0" + v;
}

// 返回日 （修正为两位数）
function GetFullDate(date) {
    var v = date.getDate();
    if (v > 9)return v.toString();
    return "0" + v;
}

// 替换字符串
function Replace(str, from, to) {
    return str.split(from).join(to);
}

// 格式化日期的表示
function FormatDate(date, str) {
    str = Replace(str, "yyyy", date.getFullYear());
    str = Replace(str, "MM", GetFullMonth(date));
    str = Replace(str, "dd", GetFullDate(date));
    str = Replace(str, "yy", GetHarfYear(date));
    str = Replace(str, "M", date.getMonth() + 1);
    str = Replace(str, "d", date.getDate());
    return str;
}


//获得2个相减 day
function DifferentDay(date1, date2) {

    var dat1 = new Date(date1).getTime();
    var dat2 = new Date(date2).getTime();
    return Math.floor((dat2 - dat1) / 1000 / 60 / 60 / 24);
}


// 统一日期格式
function ConvertDate(str) {
    str = (str + "").replace(/^\s*/g, "").replace(/\s*$/g, ""); // 去除前后的空白
    var d;
    if (/^[0-9]{8}$/.test(str)) // 20040226 -> 2004-02-26
    {
        d = new Date(new Number(str.substr(0, 4)), new Number(str.substr(4, 2)) - 1, new Number(str.substr(6, 2)));
        if (d.getTime())return d;
    }
    d = new Date(str);
    if (d.getTime())return d;
    d = new Date(Replace(str, "-", "/"));
    if (d.getTime())return d;
    return null;
}


//时间转换为数字
function ConvertDateNumber(str) {
    var my_date_ago = ConvertDate(str);
    return (my_date_ago.getFullYear() + "" + GetFullMonth(my_date_ago) + "" + GetFullDate(my_date_ago));
}


// 01.
// js时间差函数
// <SCRIPT LANGUAGE=javascript>
//     alert(addDay(-30,1));
// alert(addDay(-30,2));
// alert(addDay(-30,3));
// alert(addDay(-30,0));

function addDay(days, n, date) {
//函数说明：days日期差，n代表如下含义。
    var my_date_ago = new Date(date - days * 24 * 60 * 60 * 1000 * -1);//days天的日期
    switch (n) {
        case 1:
//返回年
            return (my_date_ago.getFullYear());
            break;
        case 2:
//返回月
            return (my_date_ago.getMonth() + 1);
            break;
        case 3:
//返回日
            return (my_date_ago.getDate());
            break;
        default :
//返回全部
        case 4:
            return (my_date_ago.getFullYear() + "-" + GetFullMonth(my_date_ago) + "-" + GetFullDate(my_date_ago));
            break;
        case 5:
            return my_date_ago;
            break;
    }
}

function isURL(str_url) {// 验证url
    var strRegex = "^((https|http|ftp|rtsp|mms)?://)"
        + "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
        + "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
        + "|" // 允许IP和DOMAIN（域名）
        + "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
        + "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
        + "[a-z]{2,6})" // first level domain- .com or .museum
        + "(:[0-9]{1,4})?" // 端口- :80
        + "((/?)|" // a slash isn't required if there is no file name
        + "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
    var re = new RegExp(strRegex);
    return re.test(str_url);
}

//获得中文的星期
function GetWeek(date) {
    var n = date.getDay();
    var rtnStr = "";
    switch (n) {
        case 0: {
            rtnStr = "日";
            break;
        }
        case 1: {
            rtnStr = "一";
            break;
        }
        case 2: {
            rtnStr = "二";
            break;
        }
        case 3: {
            rtnStr = "三";
            break;
        }
        case 4: {
            rtnStr = "四";
            break;
        }
        case 5: {
            rtnStr = "五";
            break;
        }
        case 6: {
            rtnStr = "六";
            break;
        }
        default: {
            rtnStr = "";
            break;
        }
    }
    return rtnStr;
}

