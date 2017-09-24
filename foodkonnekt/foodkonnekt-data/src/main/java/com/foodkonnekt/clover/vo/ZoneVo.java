package com.foodkonnekt.clover.vo;

import java.util.Comparator;

import com.foodkonnekt.model.Zone;

public class ZoneVo implements Comparator<Zone> {

    public int compare(Zone o1, Zone o2) {
        return o1.getMinDollarAmount().compareTo(o2.getMinDollarAmount());
    }
}
