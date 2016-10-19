(function (factory) {
    if (typeof define === "function" && define.amd) {
        define(["", "jquery-validator"], factory);
    } else {
        factory(jQuery);
    }
}(function ($) {

    /*
     * Translated default messages for the jQuery validation plugin.
     * Locale: ZH (Chinese, 中文 (Zhōngwén), 汉语, 漢語)
     */
    $.extend($.validator.messages, {
        required: "这是必填字段",
        remote: "已被占用",
        email: "请输入有效的电子邮件地址",
        url: "请输入有效的网址",
        date: "请输入有效的日期",
        dateISO: "请输入有效的日期 (YYYY-MM-DD)",
        number: "请输入有效的数字",
        digits: "只能输入数字",
        creditcard: "请输入有效的信用卡号码",
        equalTo: "两次输入不一致",
        extension: "请输入有效的后缀",
        maxlength: $.validator.format("最多可以输入 {0} 个字符"),
        minlength: $.validator.format("最少要输入 {0} 个字符"),
        rangelength: $.validator.format("请输入长度在 {0} 到 {1} 之间的字符串"),
        range: $.validator.format("请输入范围在 {0} 到 {1} 之间的数值"),
        max: $.validator.format("请输入不大于 {0} 的数值"),
        min: $.validator.format("请输入不小于 {0} 的数值")
    });
}));

//下面是自定义校验

$.extend($.validator.addMethod("isPassword", function (value) {
    var str = /^(?![^a-zA-Z]+$)(?!\D+$).{6,32}$/;
    return str.test(value);
}, "请输入长度为 6 至 32 之间的字串，必须包含字母和数字!"));

$.extend($.validator.addMethod("isChineseAndEnglish", function(value) {
    var str =  /^[A-Za-z\u4e00-\u9fa5]+$/;
    return str.test(value);
}, "请输入中文或者英文!"));

$.extend($.validator.addMethod("isMobile", function(value, element) {
    var length = value.length;
    var mobile = /^(0|86|17951)?(13[0-9]|15[012356789]|17[678]|18[0-9]|14[57])[0-9]{8}$/;
    return this.optional(element) || (length == 11 && mobile.test(value));
}, "请正确填写您的手机号码"));