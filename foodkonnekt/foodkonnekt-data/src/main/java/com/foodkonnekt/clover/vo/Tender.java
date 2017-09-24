package com.foodkonnekt.clover.vo;

public class Tender {

    private String id;

    private Integer enabled;

    private Integer supportsTipping;

    private Integer visible;

    private Integer opensCashDrawer;

    private Integer editable;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Integer getSupportsTipping() {
        return supportsTipping;
    }

    public void setSupportsTipping(Integer supportsTipping) {
        this.supportsTipping = supportsTipping;
    }

    public Integer getVisible() {
        return visible;
    }

    public void setVisible(Integer visible) {
        this.visible = visible;
    }

    public Integer getOpensCashDrawer() {
        return opensCashDrawer;
    }

    public void setOpensCashDrawer(Integer opensCashDrawer) {
        this.opensCashDrawer = opensCashDrawer;
    }

    public Integer getEditable() {
        return editable;
    }

    public void setEditable(Integer editable) {
        this.editable = editable;
    }

}
