package com.pape.alarm;

import android.content.Context;

import java.text.DateFormatSymbols;
import java.util.Calendar;
import java.util.HashSet;

import static com.pape.alarm.ConstantKt.ALL_DAYS_SET;
import static com.pape.alarm.ConstantKt.DAYS_IN_A_WEEK;
import static com.pape.alarm.ConstantKt.NO_DAYS_SET;


/*
 * 设计：
 * 1111111：通过移位运算来确定某天是否被选中；
 * 
 * Days of week code as a single int.
 * 0x00（0000000）: no day
 * 0x01（0000001）: Monday
 * 0x02（0000010） ：Tuesday
 * 0x04（0000100）: Wednesday
 * 0x08（0001000）: Thursday
 * 0x10（0010000）: Friday
 * 0x20（0100000）: Saturday
 * 0x40（1000000）: Sunday
 * 0x7f（1111111） ：Everyday
 */
public class DaysOfWeek {

    public int mBitSet;

    public DaysOfWeek(int bitSet) {
        mBitSet = bitSet;
    }

    /**
     * 生效或失效  具体日期
     *
     * @param daysOfWeek Calendar.SUNDAY, Calendar.MONDAY, Calendar.TUESDAY, etc.
     */
    public void setDaysOfWeek(boolean value, int... daysOfWeek) {
        for (int day : daysOfWeek) {
            setBit(convertDayToBitIndex(day), value);
        }
    }

    /**
     * 设置生效日期数据
     */
    public void setBitSet(int bitSet) {
        mBitSet = bitSet;
    }

    /**
     * 设置某一天是否生效
     */
    private void setBit(int bitIndex, boolean set) {
        if (bitIndex > 6) return;
        if (set) {
            mBitSet |= (1 << bitIndex);
        } else {
            mBitSet &= ~(1 << bitIndex);
        }
    }


    public int getBitSet() {
        return mBitSet;
    }


    /**
     * java api  Calendar.DAY_OF_WEEK中星期一是2,这里需要转化为0，依次类推
     */
    private int convertDayToBitIndex(int day) {
        return (day + 5) % DAYS_IN_A_WEEK;
    }

    /**
     * 与convertDayToBitIndex相反
     */
    private int convertBitIndexToDay(int bitIndex) {
        return (bitIndex + 1) % DAYS_IN_A_WEEK;
    }


    public HashSet<Integer> getSetDays() {
        final HashSet<Integer> result = new HashSet<Integer>();
        for (int bitIndex = 0; bitIndex < DAYS_IN_A_WEEK; bitIndex++) {
            if (isBitEnabled(bitIndex)) {
                result.add(convertBitIndexToDay(bitIndex));
            }
        }
        return result;
    }

    public int calculateDaysToNextAlarm(Calendar current) {
        if (!isRepeating()) {
            return -1;
        }

        int dayCount = 0;
        int currentDayBit = convertDayToBitIndex(current.get(Calendar.DAY_OF_WEEK));
        for (; dayCount < DAYS_IN_A_WEEK; dayCount++) {
            int nextAlarmBit = (currentDayBit + dayCount) % DAYS_IN_A_WEEK;
            if (isBitEnabled(nextAlarmBit)) {
                break;
            }
        }
        return dayCount;
    }

    public boolean isRepeating() {
        return mBitSet != NO_DAYS_SET;
    }

    private boolean isBitEnabled(int bitIndex) {
        return ((mBitSet & (1 << bitIndex)) > 0);
    }

    public void clearAllDays() {
        mBitSet = NO_DAYS_SET;
    }

    public String toString(Context context, boolean showNever) {
        return toString(context, showNever, false);
    }

    public String toAccessibilityString(Context context) {
        return toString(context, false, true);
    }

    private String toString(Context context, boolean showNever, boolean forAccessibility) {
        StringBuilder ret = new StringBuilder();

        // no days
        if (mBitSet == NO_DAYS_SET) {
            return  "从不";
        }

        // every day
        if (mBitSet == ALL_DAYS_SET) {
            return "每天";
        }

        // count selected days
        int dayCount = 0;
        int bitSet = mBitSet;
        while (bitSet > 0) {
            if ((bitSet & 1) == 1) dayCount++;
            bitSet >>= 1;
        }

        // short or long form?
        DateFormatSymbols dfs = new DateFormatSymbols();
        String[] dayList = (forAccessibility || dayCount <= 1) ?
                dfs.getWeekdays() :
                dfs.getShortWeekdays();

        // selected days
        for (int bitIndex = 0; bitIndex < DAYS_IN_A_WEEK; bitIndex++) {
            if ((mBitSet & (1 << bitIndex)) != 0) {
                ret.append(dayList[convertBitIndexToDay(bitIndex)]);
                dayCount -= 1;
                if (dayCount > 0) ret.append(", ");
            }
        }
        return ret.toString();
    }

    public String toString() {
        return "DaySOfWeek {" + "mBitSet=" + mBitSet + "}";
    }

}
