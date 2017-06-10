/**
 * @author greenwishing
 * @date 2015-06-10
 */
'use strict';
(function($){
    $.lunar = $.lunar || {};

    var s = {
        data: [
            [0, 2, 9, 21936], [6, 1, 30, 9656], [0, 2, 17, 9584], [0, 2, 6, 21168], [5, 1, 26, 43344],
            [0, 2, 13, 59728], [0, 2, 2, 27296], [3, 1, 22, 44368], [0, 2, 10, 43856], [8, 1, 31, 19304],
            [0, 2, 19, 19168], [0, 2, 8, 42352], [5, 1, 29, 21096], [0, 2, 16, 53856], [0, 2, 4, 55632],
            [4, 1, 25, 27304], [0, 2, 13, 22176], [0, 2, 2, 39632], [2, 1, 22, 19176], [0, 2, 10, 19168],
            [6, 1, 30, 42200], [0, 2, 18, 42192], [0, 2, 6, 53840], [5, 1, 26, 54568], [0, 2, 14, 46400],
            [0, 2, 3, 54944], [2, 1, 23, 38608], [0, 2, 11, 38320], [7, 2, 1, 18872], [0, 2, 20, 18800],
            [0, 2, 8, 42160], [5, 1, 28, 45656], [0, 2, 16, 27216], [0, 2, 5, 27968], [4, 1, 24, 44456],
            [0, 2, 13, 11104], [0, 2, 2, 38256], [2, 1, 23, 18808], [0, 2, 10, 18800], [6, 1, 30, 25776],
            [0, 2, 17, 54432], [0, 2, 6, 59984], [5, 1, 26, 27976], [0, 2, 14, 23248], [0, 2, 4, 11104],
            [3, 1, 24, 37744], [0, 2, 11, 37600], [7, 1, 31, 51560], [0, 2, 19, 51536], [0, 2, 8, 54432],
            [6, 1, 27, 55888], [0, 2, 15, 46416], [0, 2, 5, 22176], [4, 1, 25, 43736], [0, 2, 13, 9680],
            [0, 2, 2, 37584], [2, 1, 22, 51544], [0, 2, 10, 43344], [7, 1, 29, 46248], [0, 2, 17, 27808],
            [0, 2, 6, 46416], [5, 1, 27, 21928], [0, 2, 14, 19872], [0, 2, 3, 42416], [3, 1, 24, 21176],
            [0, 2, 12, 21168], [8, 1, 31, 43344], [0, 2, 18, 59728], [0, 2, 8, 27296], [6, 1, 28, 44368],
            [0, 2, 15, 43856], [0, 2, 5, 19296], [4, 1, 25, 42352], [0, 2, 13, 42352], [0, 2, 2, 21088],
            [3, 1, 21, 59696], [0, 2, 9, 55632], [7, 1, 30, 23208], [0, 2, 17, 22176], [0, 2, 6, 38608],
            [5, 1, 27, 19176], [0, 2, 15, 19152], [0, 2, 3, 42192], [4, 1, 23, 53864], [0, 2, 11, 53840],
            [8, 1, 31, 54568], [0, 2, 18, 46400], [0, 2, 7, 46752], [6, 1, 28, 38608], [0, 2, 16, 38320],
            [0, 2, 5, 18864], [4, 1, 25, 42168], [0, 2, 13, 42160], [10, 2, 2, 45656], [0, 2, 20, 27216],
            [0, 2, 9, 27968], [6, 1, 29, 44448], [0, 2, 17, 43872], [0, 2, 6, 38256], [5, 1, 27, 18808],
            [0, 2, 15, 18800], [0, 2, 4, 25776], [3, 1, 23, 27216], [0, 2, 10, 59984], [8, 1, 31, 27432],
            [0, 2, 19, 23232], [0, 2, 7, 43872], [5, 1, 28, 37736], [0, 2, 16, 37600], [0, 2, 5, 51552],
            [4, 1, 24, 54440], [0, 2, 12, 54432], [0, 2, 1, 55888], [2, 1, 22, 23208], [0, 2, 9, 22176],
            [7, 1, 29, 43736], [0, 2, 18, 9680], [0, 2, 7, 37584], [5, 1, 26, 51544], [0, 2, 14, 43344],
            [0, 2, 3, 46240], [4, 1, 23, 46416], [0, 2, 10, 44368], [9, 1, 31, 21928], [0, 2, 19, 19360],
            [0, 2, 8, 42416], [6, 1, 28, 21176], [0, 2, 16, 21168], [0, 2, 5, 43312], [4, 1, 25, 29864],
            [0, 2, 12, 27296], [0, 2, 1, 44368], [2, 1, 22, 19880], [0, 2, 10, 19296], [6, 1, 29, 42352],
            [0, 2, 17, 42208], [0, 2, 6, 53856], [5, 1, 26, 59696], [0, 2, 13, 54576], [0, 2, 3, 23200],
            [3, 1, 23, 27472], [0, 2, 11, 38608], [11, 1, 31, 19176], [0, 2, 19, 19152], [0, 2, 8, 42192],
            [6, 1, 28, 53848], [0, 2, 15, 53840], [0, 2, 4, 54560], [5, 1, 24, 55968], [0, 2, 12, 46496],
            [0, 2, 1, 22224], [2, 1, 22, 19160], [0, 2, 10, 18864], [7, 1, 30, 42168], [0, 2, 17, 42160],
            [0, 2, 6, 43600], [5, 1, 26, 46376], [0, 2, 14, 27936], [0, 2, 2, 44448], [3, 1, 23, 21936],
            [0, 2, 11, 37744], [8, 2, 1, 18808], [0, 2, 19, 18800], [0, 2, 8, 25776], [6, 1, 28, 27216],
            [0, 2, 15, 59984], [0, 2, 4, 27424], [4, 1, 24, 43872], [0, 2, 12, 43744], [0, 2, 2, 37600],
            [3, 1, 21, 51568], [0, 2, 9, 51552], [7, 1, 29, 54440], [0, 2, 17, 54432], [0, 2, 5, 55888],
            [5, 1, 26, 23208], [0, 2, 14, 22176], [0, 2, 3, 42704], [4, 1, 23, 21224], [0, 2, 11, 21200],
            [8, 1, 31, 43352], [0, 2, 19, 43344], [0, 2, 7, 46240], [6, 1, 27, 46416], [0, 2, 15, 44368],
            [0, 2, 5, 21920], [4, 1, 24, 42448], [0, 2, 12, 42416], [0, 2, 2, 21168], [3, 1, 22, 43320],
            [0, 2, 9, 26928], [7, 1, 29, 29336], [0, 2, 17, 27296], [0, 2, 6, 44368], [5, 1, 26, 19880],
            [0, 2, 14, 19296], [0, 2, 3, 42352], [4, 1, 24, 21104], [0, 2, 10, 53856], [8, 1, 30, 59696],
            [0, 2, 18, 54560], [0, 2, 7, 55968], [6, 1, 27, 27472], [0, 2, 15, 22224], [0, 2, 5, 19168],
            [4, 1, 25, 42216], [0, 2, 12, 42192], [0, 2, 1, 53584], [2, 1, 21, 55592], [0, 2, 9, 54560]
        ],
        dayNames: "日一二三四五六七八九十",
        monthNames: " 正二三四五六七八九十冬腊",
        heavenlyStemsNames: "甲乙丙丁戊己庚辛壬癸",
        earthlyBranchesNames: "子丑寅卯辰巳午未申酉戌亥",
        zodiacNames: "鼠牛虎兔龙蛇马羊猴鸡狗猪",
        minYear: 1891,
        maxYear: 2100,
        oneDay: 24 * 3600000
    };
    $.lunar.settings = s;


    /**
     * 将阳历转换为阴历
     * @param {Date} dateObj 日期对象
     */
    $.lunar.convertSolarToLunar = function(dateObj) {
        var year = dateObj.getFullYear();
        var month = dateObj.getMonth() + 1;
        var date = dateObj.getDate();
        var yearData = s.data[year - s.minYear];
        if (year == s.minYear && month <= 2 && date <= 9) {
            return {year: year, month: 1, date: 1};
        }
        var daysBetweenSolar = $.lunar.getDaysBetweenSolar(year, month, date, yearData[1], yearData[2]);
        return $.lunar.getLunarByBetween(year, daysBetweenSolar);
    };

    /**
     * 将阴历转换为阳历
     * @param {Object} dateObj 自定义日期对象
     *  year  阴历-年
     *  month 阴历-月，闰月处理：例如如果当年闰五月，那么第二个五月就传六月，相当于阴历有13个月，只是有的时候第13个月的天数为0
     *  date  阴历-日
     */
    $.lunar.convertLunarToSolar = function(dateObj) {
        var year = dateObj.year, month = dateObj.month, date = dateObj.date;
        var yearData = s.data[year - s.minYear], solar = new Date(year, yearData[1] - 1, yearData[2]), between = $.lunar.getDaysBetweenLunar(year, month, date);
        solar.setDate(solar.getDate() + between);
        return solar;
    };

    /**
     * 将保存的农历转换成lunar对象
     * @param {String} lunarDateInt lunarIntStr
     * @return {Object} 自定义日期对象
     */
    $.lunar.parseLunar = function(lunarDateInt) {
        if (/^(\d{8})$/.test(lunarDateInt)) {
            var savedMonth = parseInt(lunarDateInt.substring(4, 6));
            var month = savedMonth % 2 == 0 ? savedMonth / 2 : (savedMonth + 1) / 2;
            return {year: parseInt(lunarDateInt.substring(0, 4)), month: month, date: parseInt(lunarDateInt.substring(6, 8))};
        }
        return null;
    };


    $.lunar.fromSolarString = function(solar) {
        if (/^(\d{4}-\d{2}-\d{2})$/.test(solar)) {
            var date = new Date(solar);
            var lunar = $.lunar.convertSolarToLunar(date);
            return $.lunar.toIntValue(lunar);
        }
        return null;
    };

    $.lunar.toIntValue = function(lunar) {
        return lunar.year * 10000 + $.lunar.getSavedMonth(lunar.year, lunar.month) * 100 + lunar.date;
    };

    $.lunar.toSolarString = function(lunarDateInt) {
        var lunar = $.lunar.parseLunar(lunarDateInt);
        if (lunar == null) {
            var defaultLunar = $.lunar.convertSolarToLunar(new Date());
            defaultLunar.year = defaultLunar.year - 20;
            lunar = defaultLunar;
        }
        var solar = $.lunar.convertLunarToSolar(lunar);
        return $.lunar.getSolarName(solar)
    };

    /**
     * 判断是否是闰年
     * @param {Integer} year
     */
    $.lunar.isLeapYear = function(year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    };

    /**
     * 获取阴历月份的天数
     * @param {Integer} year  阴历-年
     * @param {Integer} month 阴历-月，从一月开始
     */
    $.lunar.getLunarMonthDays = function(year, month) {
        var monthData = $.lunar.getLunarMonths(year);
        return monthData[month - 1];
    };

    /**
     * 获取阴历每月的天数的数组
     * @param {Integer} year
     */
    $.lunar.getLunarMonths = function(year) {
        var yearData = s.data[year - s.minYear], leapMonth = yearData[0], bitArray = (yearData[3]).toString(2).split('');
        for (var k = 0, klen = 16 - bitArray.length; k < klen; k++) {
            bitArray.unshift('0');
        }
        bitArray = bitArray.slice(0, (leapMonth == 0 ? 12 : 13));
        for (var i = 0, len = bitArray.length; i < len; i++) {
            bitArray[i] = parseInt(bitArray[i]) + 29;
        }

        return bitArray;
    };

    /**
     * 获取农历每年的天数
     * @param {Integer} year 农历年份
     */
    $.lunar.getLunarYearDays = function(year) {
        var monthArray = $.lunar.getLunarYearMonths(year), len = monthArray.length;
        return (monthArray[len - 1] == 0 ? monthArray[len - 2] : monthArray[len - 1]);
    };

    $.lunar.getLunarYearMonths = function(year) {
        var monthData = $.lunar.getLunarMonths(year), res = [], temp = 0, yearData = s.data[year - s.minYear], len = (yearData[0] == 0 ? 12 : 13);
        for (var i = 0; i < len; i++) {
            temp = 0;
            for (var j = 0; j <= i; j++) {
                temp += monthData[j];
            }
            res.push(temp);
        }
        return res;
    };

    /**
     * 获取闰月
     * @param {Integer} year 阴历年份
     */
    $.lunar.getLeapMonth = function(year) {
        var yearData = s.data[year - s.minYear];
        return yearData[0];
    };

    /**
     * 计算阴历日期与正月初一相隔的天数
     * @param {Integer} year
     * @param {Integer} month
     * @param {Integer} date
     */
    $.lunar.getDaysBetweenLunar = function(year, month, date) {
        var yearMonth = $.lunar.getLunarMonths(year), days = 0;
        for (var i = 1; i < month; i++) {
            days += yearMonth[i - 1];
        }
        days += date - 1;
        return days;
    };

    /**
     * 计算2个阳历日期之前的天数
     * @param {Integer} year 阳历年
     * @param cmonth
     * @param cdate
     * @param dmonth 阴历正月对应的阳历月份
     * @param ddate 阴历初一对应的阳历月份
     */
    $.lunar.getDaysBetweenSolar = function(year, cmonth, cdate, dmonth, ddate) {
        var a = new Date(year, cmonth - 1, cdate), b = new Date(year, dmonth - 1, ddate);
        return Math.ceil((a - b) / s.oneDay);
    };

    /**
     * 根据距离正月初一的天数计算阴历日期
     * @param year  阳历年
     * @param between 天数
     */
    $.lunar.getLunarByBetween = function(year, between) {
        var yearMonth = [], month = 0, date = 0;
        if (between == 0) {
            month = 1;
            date = 1;
        } else {
            year = between > 0 ? year : (year - 1);
            yearMonth = $.lunar.getLunarYearMonths(year);
            between = between > 0 ? between : ($.lunar.getLunarYearDays(year) + between);
            for (var i = 0; i < 13; i++) {
                if (between == yearMonth[i]) {
                    month = i + 2;
                    date = 1;
                    break;
                } else if (between < yearMonth[i]) {
                    month = i + 1;
                    date = between - (yearMonth[i - 1] == undefined ? 0 : yearMonth[i - 1]) + 1;
                    break;
                }
            }
        }

        return {year: year, month: month, date: date};
    };

    /**
     * 中文阴历月份
     * @param year  阴历年
     * @param month 阴历月
     */
    $.lunar.getLunarMonthName = function(year, month) {
        var leapMonth = $.lunar.getLeapMonth(year);
        return ((leapMonth != 0 && month == leapMonth + 1) ? '润' : '') + s.monthNames.charAt((leapMonth != 0 && month > leapMonth) ? month - 1 : month) + '月';
    };

    /**
     * 根据阴历年获取天干
     * @param year
     * @returns {string}
     */
    $.lunar.getHeavenlyStemsName = function(year) {
        return s.heavenlyStemsNames.charAt((year + 6) % 10);
    };

    /**
     * 根据阴历年获取地支
     * @param year
     * @returns {string}
     */
    $.lunar.getEarthlyBranchesName = function(year) {
        return s.earthlyBranchesNames.charAt((year + 8) % 12);
    };

    /**
     * 根据阴历年获取生肖
     * @param year  阴历年
     */
    $.lunar.getZodiacName = function(year) {
        return s.zodiacNames.charAt((year + 8) % 12);
    };

    /**
     * 中文阴历日期
     * @param date 阴历日
     */
    $.lunar.getLunarDateName = function(date) {
        var d1 = date % 10;
        if (date <= 10) {
            return '初' + s.dayNames[date];
        } else if (date > 10 && date < 20) {
            return '十' + s.dayNames[d1];
        } else if (date == 20) {
            return "二十";
        } else if (date > 20 && date < 30) {
            return "廿" + s.dayNames[d1];
        } else if (date == 30) {
            return "三十";
        }
    };

    $.lunar.getLunarYearName = function(year) {
        return year + '年';
    };

    $.lunar.getLunarYearFullName = function(year) {
        return $.lunar.getHeavenlyStemsName(year) + '' + $.lunar.getEarthlyBranchesName(year) + '' + $.lunar.getZodiacName(year) + '(' + year + ')年';
    };

    /**
     * 获取干支纪年
     * @param lunar
     */
    $.lunar.getLunarName = function(lunar) {
        return $.lunar.getLunarYearName(lunar.year) + $.lunar.getLunarMonthName(lunar.year, lunar.month) + $.lunar.getLunarDateName(lunar.date);
    };

    /**
     * 获取阳历
     * @param {Date} solar
     */
    $.lunar.getSolarName = function(solar) {
        var fullYear = solar.getFullYear();
        var month = solar.getMonth() + 1;
        var date = solar.getDate();
        return fullYear + '-' + ((month < 10 ? '0': '') + month) + '-' + ((date < 10 ? '0': '') + date);
    };

    /**
     * 农历月存储格式
     * 1,3,5,7 ... 23 分别表示农历1-12月
     * 2,4,6,8 ... 24 分别表示农历润1-12月
     */
    $.lunar.getSavedMonth = function(year, month) {
        var savedMonth;
        var leapMonth = $.lunar.getLeapMonth(year);
        if (leapMonth > 0) {
            if (month < leapMonth + 1) {
                savedMonth = month * 2 - 1;
            } else if (month == leapMonth + 1) {
                savedMonth = (month - 1) * 2;
            } else {
                savedMonth = (month - 1) * 2 - 1;
            }
        } else {
            savedMonth = month * 2 - 1;
        }
        return savedMonth;
    };

})(jQuery);