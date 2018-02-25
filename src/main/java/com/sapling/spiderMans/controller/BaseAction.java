package com.sapling.spiderMans.controller;




import org.apache.log4j.Logger;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class BaseAction{
    protected  final Logger logger = Logger.getLogger(this.getClass());
}

