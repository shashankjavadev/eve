package com.foodkonnekt.model;

import java.util.List;

public class JsonResponse {

    private String href;
    private List<Object> elements;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public List<Object> getElements() {
        return elements;
    }

    public void setElements(List<Object> elements) {
        this.elements = elements;
    }
}
