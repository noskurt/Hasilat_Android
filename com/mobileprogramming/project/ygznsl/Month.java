
package com.mobileprogramming.project.ygznsl;

public enum Month {
    JANUARY(1, "Ocak"),
    FEBRUARY(2, "Şubat"),
    MARCH(3, "Mart"),
    APRIL(4, "Nisan"),
    MAY(5, "Mayıs"),
    JUNE(6, "Haziran"),
    JULY(7, "Temmuz"),
    AUGUST(8, "Ağustos"),
    SEPTEMBER(9, "Eylül"),
    OCTOBER(10, "Ekim"),
    NOVEMBER(11, "Kasım"),
    DECEMBER(12, "Aralık");

    private final int value;
    private final String name;
    
    Month(int value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public int value() { return value; }

    public String getName() { return name; }

}
