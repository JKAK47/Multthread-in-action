package org.vincent.multthread.volatile01;

/**
 * @author PengRong
 * @package org.vincent.multthread.volatile01
 * @date 2019/2/23 - 10:59
 * @ProjectName Multthread-in-action
 * @Description: TODO
 */
public class MyClass {
    private int years;
    private int months;
    private volatile int days;

    public int totalDays() {
        int total = this.days;
        total += months * 30;
        total += years * 365;
        return total;
    }

    public void update(int years, int months, int days){
        this.years  = years;
        this.months = months;
        this.days   = days;
    }

    public int getYears() {
        return years;
    }

    public void setYears(int years) {
        this.years = years;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public MyClass() {
    }

    @Override
    public String toString() {
        return "MyClass{" +
                "years=" + years +
                ", months=" + months +
                ", days=" + days +
                '}';
    }
}
