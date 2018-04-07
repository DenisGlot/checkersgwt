package com.checkersgwt.web.controller;

import com.checkersgwt.view.ModelBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/app")
public class WebAppController {
    
    @Value("${build.version}")
    private String buildVersion;

    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getApplicationPage() {
        ModelBuilder model = new ModelBuilder("ApplicationPage");

        model.put("buildVersion", buildVersion);

        return model;
    }
}
