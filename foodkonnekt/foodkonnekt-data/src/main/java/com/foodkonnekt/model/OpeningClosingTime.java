package com.foodkonnekt.model;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "opening_closing_time")
public class OpeningClosingTime {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "start_time")
    private String startTime;

    @Column(name = "end_time")
    private String endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "day_id", referencedColumnName = "id",updatable=false)
    private OpeningClosingDay openingClosingDay;

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     *            the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the startTime
     */
    public String getStartTime() {
        return startTime;
    }

    /**
     * @param startTime
     *            the startTime to set
     */
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    /**
     * @return the endTime
     */
    public String getEndTime() {
        return endTime;
    }

    /**
     * @param endTime
     *            the endTime to set
     */
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    /**
     * @return the openingClosingDay
     */
    public OpeningClosingDay getOpeningClosingDay() {
        return openingClosingDay;
    }

    /**
     * @param openingClosingDay
     *            the openingClosingDay to set
     */
    public void setOpeningClosingDay(OpeningClosingDay openingClosingDay) {
        this.openingClosingDay = openingClosingDay;
    }

}
