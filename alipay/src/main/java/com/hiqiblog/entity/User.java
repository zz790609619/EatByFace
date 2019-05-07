
package com.hiqiblog.entity;
import java.io.Serializable;
/**
 * user实体
 * @author ww
 */
public class User implements Serializable {
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }
}