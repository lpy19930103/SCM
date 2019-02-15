package com.lpy.scm.param;


/**
 * @author lpy
 * @date 2019/2/15 17:07
 */
public class AddCategoryParam {

    private Long parentId;

    private String parentName;

    private String categoryName;

    private String categoryUnit;

    private String material;

    private Integer status;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getCategoryUnit() {
        return categoryUnit;
    }

    public void setCategoryUnit(String categoryUnit) {
        this.categoryUnit = categoryUnit;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "AddCategoryParam{" +
                "parentId=" + parentId +
                ", parentName='" + parentName + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", categoryUnit='" + categoryUnit + '\'' +
                ", material='" + material + '\'' +
                ", status=" + status +
                '}';
    }
}
