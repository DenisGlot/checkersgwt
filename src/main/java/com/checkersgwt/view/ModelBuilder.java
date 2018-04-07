package com.checkersgwt.view;

import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ModelBuilder extends ModelAndView {

    private Map<String, Object> model;

    public ModelBuilder() {
        super();
        model = super.getModel();
    }

    public ModelBuilder(String viewName) {
        super(viewName);
        model = super.getModel();
    }

    public ModelBuilder(ModelAndView model) {
        this(model.getModel());
    }

    public ModelBuilder(Model model) {
        this(model.asMap());
    }

    public ModelBuilder(Map<String, Object> map) {
        this.model = map;
    }

    @SuppressWarnings("unchecked")
    public ModelBuilder createCollection(String name) {
        List<Map<String, Object>> collection = (List<Map<String, Object>>)model.get(name);
        if(collection == null) {
            collection = new ArrayList<>();
            model.put(name, collection);
        }

        Map<String, Object> map = new HashMap<>();
        collection.add(map);
        return new ModelBuilder(map);
    }

    public ModelBuilder createModel(String name) {
        Map<String, Object> map = new HashMap<>();
        put(name, map);
        return new ModelBuilder(map);
    }

    public void put(String name, Object object) {
        model.put(name, object);
    }

    @Override
    public Map<String, Object> getModel() {
        return model;
    }

}

