package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "pos")
public class Pos {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "pos_id", unique = true, nullable = false)
    private Integer posId;

    @Column(name = "name")
    private String name;

    /**
     * @return the posId
     */
    public Integer getPosId() {
        return posId;
    }

    /**
     * @param posId
     *            the posId to set
     */
    public void setPosId(Integer posId) {
        this.posId = posId;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     *            the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

}
