package com.sapling.spiderMans.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("page")
public class BackstagePageController {
    /**
     * 跳转通用页面
     * @param pageName
     * @return
     */
    @RequestMapping("backstage/common/{pageName}")
    public String toCommonPage(@PathVariable("pageName") String pageName) {
        return "backstage/"+pageName;
    } 
    @RequestMapping("backstage/douban/{pageName}")
    public String toDoubanPage(@PathVariable("pageName") String pageName) {
        return "backstage/douban/"+pageName;
    }
}
