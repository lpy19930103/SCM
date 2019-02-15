package com.lpy.scm.dataobject;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * @author lpy
 * @date 2019/2/15 16:13
 */
@Table(name = "base_category")
public class CategoryDO {

    @Id
    private Long id;

    @Column(name = "parent_id")
    private Long parentId;

    @Column(name = "parent_name")
    private String parentName;

    @Column(name = "category_name")
    private String categoryName;

    @Column(name = "category_unit")
    private String categoryUnit;

    @Column
    private String material;

    @Column
    private Integer status;

    @Column
    private String creater;

    @Column(name = "create_at")
    private Date createAt;

    @Column(name = "update_at")
    private Date updateAt;

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }
}
