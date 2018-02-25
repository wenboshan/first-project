/**  
 * @Title: JsonData.java  
 * @Package com.sapling.spiderMans.controller  
 * @Description: TODO 
 * @author superman  
 * @date 2018年2月19日  
 * @version V1.0  
 */
package com.sapling.spiderMans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName: JsonData
 * @Description: TODO
 * @author 文泊山
 * @date 2018年2月19日
 */
@RequestMapping("json")
@Controller
public class JsonData {
    @ResponseBody
    @RequestMapping("data/{dataType}")
    public String getJsonData(@PathVariable("dataType") String dataType) {
        String data = "";
        switch (dataType) {
        case "systemParameter":
            data = "{\"cmsName\": \"layuiCMS后台管理模版\",\r\n" + 
                    "    \"version\": \"v2.0\",\r\n" + 
                    "    \"author\": \"驊驊龔頾\",\r\n" + 
                    "    \"homePage\": \"index.html\",\r\n" + 
                    "    \"server\": \"windows\",\r\n" + 
                    "    \"dataBase\": \"8.00.2039\",\r\n" + 
                    "    \"maxUpload\": \"2M\",\r\n" + 
                    "    \"userRights\": \"超级管理员\",\r\n" + 
                    "    \"description\": \"这是马哥闲来无事做的一套基于layui的cms模版，纯静态页面，不包含数据库\",\r\n" + 
                    "    \"powerby\": \"copyright @2017 驊驊龔頾\",\r\n" + 
                    "    \"record\": \"京ICP备14040xxx号-1\",\r\n" + 
                    "    \"keywords\": \"layuicms,马哥,layuicms2.0,后台模版,请叫我马哥,驊驊龔頾\"\r\n" + 
                    "}";
            break;
        case "newsList":
            data = "{\r\n" + 
                    "    \"code\": 0,\r\n" + 
                    "    \"msg\": \"\",\r\n" + 
                    "    \"count\": 15,\r\n" + 
                    "    \"data\": [\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"1\",\r\n" + 
                    "            \"newsName\": \"css3用transition实现边框动画效果\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"css3用transition实现边框动画效果css3用transition实现边框动画效果\",\r\n" + 
                    "            \"newsStatus\": \"0\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface1.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"css3用transition实现边框动画效果<img src='../../images/userface1.jpg' alt='文章内容图片'>css3用transition实现边框动画效果css3用transition实现边框动画效果\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"2\",\r\n" + 
                    "            \"newsName\": \"自定义的模块名称可以包含/吗\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"自定义的模块名称可以包含/吗自定义的模块名称可以包含/吗\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface2.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"自定义的模块名称可以包含自定义的模块名称可<img src='../../images/userface2.jpg' alt='文章内容图片'>以包含自定义的模块名称可以包含自定义的模块名称可以包含\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"3\",\r\n" + 
                    "            \"newsName\": \"layui.tree如何ajax加载二级菜单\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"layui.tree如何ajax加载二级菜单layui.tree如何ajax加载二级菜单\",\r\n" + 
                    "            \"newsStatus\": \"2\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface3.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui.tree如何ajax加载二级菜单layui.tree如何<img src='../../images/userface3.jpg' alt='文章内容图片'>ajax加载二级菜单layui.tree如何ajax加载二级菜单\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"4\",\r\n" + 
                    "            \"newsName\": \"layui.upload如何带参数？像jq的data:{}那样\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"layui.upload如何带参数？像jq的data:{}那样layui.upload如何带参数？像jq的data:{}那样\",\r\n" + 
                    "            \"newsStatus\": \"0\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface4.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui.upload如何带参数？像jq的data:{}那样layui.upload如何带参数？像jq的data:{}那样layui.upload如何带参数？像jq的data:{}那样\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"5\",\r\n" + 
                    "            \"newsName\": \"表单元素长度应该怎么调整才美观\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"表单元素长度应该怎么调整才美观表单元素长度应该怎么调整才美观\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface5.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"表单元素长度应该怎么调整才美观表单元素长度应该怎么调整才美观表单元素长度应该怎么调整才美观表单元素长度应该怎么调整才美观\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"6\",\r\n" + 
                    "            \"newsName\": \"layui 利用ajax冲获取到json 数据后 怎样进行渲染\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"layui 利用ajax冲获取到json 数据后 怎样进行渲染layui 利用ajax冲获取到json 数据后 怎样进行渲染\",\r\n" + 
                    "            \"newsStatus\": \"0\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface1.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui 利用ajax冲获取到json 数据后 怎样进行渲染layui 利用ajax冲获取到json 数据后 怎样进行渲染layui 利用ajax冲获取到json 数据后 怎样进行渲染\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"7\",\r\n" + 
                    "            \"newsName\": \"微信页面中富文本编辑器LayEdit无法使用\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"微信页面中富文本编辑器LayEdit无法使用微信页面中富文本编辑器LayEdit无法使用\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface2.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"微信页面中富文本编辑器LayEdit无法使用微信页面中富文本编辑器LayEdit无法使用微信页面中富文本编辑器LayEdit无法使用\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"8\",\r\n" + 
                    "            \"newsName\": \"layui 什么时候发布新的版本呀\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"layui 什么时候发布新的版本呀layui 什么时候发布新的版本呀\",\r\n" + 
                    "            \"newsStatus\": \"2\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface3.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui 什么时候发布新的版本呀layui 什么时候发布新的版本呀layui 什么时候发布新的版本呀layui 什么时候发布新的版本呀\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"9\",\r\n" + 
                    "            \"newsName\": \"layui上传组件不支持上传前的图片预览嘛？\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"layui上传组件不支持上传前的图片预览嘛？layui上传组件不支持上传前的图片预览嘛？\",\r\n" + 
                    "            \"newsStatus\": \"2\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface4.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui上传组件不支持上传前的图片预览嘛？layui上传组件不支持上传前的图片预览嘛？layui上传组件不支持上传前的图片预览嘛？\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"10\",\r\n" + 
                    "            \"newsName\": \"关于layer.confirm点击无法关闭的疑惑\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"关于layer.confirm点击无法关闭的疑惑关于layer.confirm点击无法关闭的疑惑\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface5.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"关于layer.confirm点击无法关闭的疑惑关于layer.confirm点击无法关闭的疑惑关于layer.confirm点击无法关闭的疑惑\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"11\",\r\n" + 
                    "            \"newsName\": \"layui form表单提交成功如何拿取返回值\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"layui form表单提交成功如何拿取返回值layui form表单提交成功如何拿取返回值\",\r\n" + 
                    "            \"newsStatus\": \"2\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface1.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layui form表单提交成功如何拿取返回值layui form表单提交成功如何拿取返回值layui form表单提交成功如何拿取返回值\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"12\",\r\n" + 
                    "            \"newsName\": \"layer mobileV2.0 yes回调函数无法用？\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"layer mobileV2.0 yes回调函数无法用？layer mobileV2.0 yes回调函数无法用？\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface2.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"layer mobileV2.0 yes回调函数无法用layer mobileV2.0 yes回调函数无法用layer mobileV2.0 yes回调函数无法用\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"13\",\r\n" + 
                    "            \"newsName\": \"关于layer中自带的btn回调弹层页面的内容\",\r\n" + 
                    "            \"newsAuthor\": \"admin\",\r\n" + 
                    "            \"abstract\": \"关于layer中自带的btn回调弹层页面的内容关于layer中自带的btn回调弹层页面的内容\",\r\n" + 
                    "            \"newsStatus\": \"1\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface3.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"关于layer中自带的btn回调弹层页面的内容关于layer中自带的btn回调弹层页面的内容关于layer中自带的btn回调弹层页面的内容\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"14\",\r\n" + 
                    "            \"newsName\": \"被编辑器 layedit 图片上传搞崩溃了\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"被编辑器 layedit 图片上传搞崩溃了被编辑器 layedit 图片上传搞崩溃了\",\r\n" + 
                    "            \"newsStatus\": \"0\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface4.jpg\",\r\n" + 
                    "            \"newsLook\": \"私密浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"被编辑器 layedit 图片上传搞崩溃了被编辑器 layedit 图片上传搞崩溃了被编辑器 layedit 图片上传搞崩溃了\"\r\n" + 
                    "        },\r\n" + 
                    "        {\r\n" + 
                    "            \"newsId\": \"15\",\r\n" + 
                    "            \"newsName\": \"element.tabChange()方法运行了，但是页面并没有产生效果\",\r\n" + 
                    "            \"newsAuthor\": \"驊驊龔頾\",\r\n" + 
                    "            \"abstract\": \"element.tabChange()方法运行了，但是页面并没有产生效果element.tabChange()方法运行了，但是页面并没有产生效果\",\r\n" + 
                    "            \"newsStatus\": \"2\",\r\n" + 
                    "            \"newsImg\":\"../../images/userface5.jpg\",\r\n" + 
                    "            \"newsLook\": \"开放浏览\",\r\n" + 
                    "            \"newsTop\": \"checked\",\r\n" + 
                    "            \"newsTime\": \"2017-04-14 00:00:00\",\r\n" + 
                    "            \"content\" : \"element.tabChange()方法运行了，但是页面并没有产生效果element.tabChange()方法运行了，但是页面并没有产生效果\"\r\n" + 
                    "        }\r\n" + 
                    "    ]\r\n" + 
                    "}";
            break;
        case "userList":
            data = "{\r\n" + 
                    "  \"code\": 0,\r\n" + 
                    "  \"msg\": \"\",\r\n" + 
                    "  \"count\": 3,\r\n" + 
                    "  \"data\": [\r\n" + 
                    "    {\r\n" + 
                    "      \"usersId\": \"1\",\r\n" + 
                    "      \"userName\": \"驊驊龔頾\",\r\n" + 
                    "      \"userEmail\": \"mage@layui.com\",\r\n" + 
                    "      \"userSex\": \"男\",\r\n" + 
                    "      \"userStatus\": \"0\",\r\n" + 
                    "      \"userGrade\": \"4\",\r\n" + 
                    "      \"userEndTime\": \"2018-01-31 10:00\",\r\n" + 
                    "      \"userDesc\" : \"layuiCMS作者，原名‘请叫我马哥’\"\r\n" + 
                    "    },{\r\n" + 
                    "      \"usersId\": \"2\",\r\n" + 
                    "      \"userName\": \"贤心\",\r\n" + 
                    "      \"userEmail\": \"xianxin@layui.com\",\r\n" + 
                    "      \"userSex\": \"保密\",\r\n" + 
                    "      \"userStatus\": \"0\",\r\n" + 
                    "      \"userGrade\": \"3\",\r\n" + 
                    "      \"userEndTime\": \"2018-01-14 15:35\",\r\n" + 
                    "      \"userDesc\" : \"layui框架作者，性别至今是个谜。。。\"\r\n" + 
                    "    },\r\n" + 
                    "    {\r\n" + 
                    "      \"usersId\": \"3\",\r\n" + 
                    "      \"userName\": \"纸飞机\",\r\n" + 
                    "      \"userEmail\": \"fly@layui.com\",\r\n" + 
                    "      \"userSex\": \"男\",\r\n" + 
                    "      \"userStatus\": \"1\",\r\n" + 
                    "      \"userGrade\": \"2\",\r\n" + 
                    "      \"userEndTime\": \"2018-01-25 16:25\",\r\n" + 
                    "      \"userDesc\" : \"fly社区管理员，据传与layui作者有奸情，故帐号被封。\"\r\n" + 
                    "    }\r\n" + 
                    "  ]\r\n" + 
                    "}";
            break;
        default:
            break;
        }
        return data;
    }
}
