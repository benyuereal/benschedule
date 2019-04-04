package com.moji.schedule;

import com.moji.schedule.entity.Slot;
import com.moji.schedule.enums.Expression;
import com.moji.schedule.interfaces.Winding;
import com.moji.schedule.util.TimeUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时器主类 队列
 */

public class TimeTracks implements Winding {
    //首先会有一个队列 这个队列的长度是 3600 队列上面的每一个对象是一个slot 插槽 对应的一系列同一时段的任务
    private static final int capacity = 3600;
    //默认队列的长度是3600

    private static final List<Slot> SLOTS = new ArrayList<>(capacity);

    public TimeTracks() {
        for (int i = 0; i < capacity; i++) {
            SLOTS.add(null);
        }
    }



    @Override
    public void mount(List<Slot> slots) {


        if (slots == null || slots.size() == 0) {
            return;
        }

        //假如传入的是从数据库里面传入的
        slots.forEach(slot -> {
            //判断小时

            int[] minutes = slot.minute();
            int[] seconds = slot.second();
            int[] minuteRange;
            int[] secondRange = new int[0];

            if (minutes[0] == Expression.ALL_TIME[0]) {
                //说明是全部时间段
                minuteRange = Expression.RANGE_MINUTE_SECOND;
                if (seconds[0] == Expression.ALL_TIME[0]) {
                    secondRange = Expression.RANGE_MINUTE_SECOND;
                } else {
                    secondRange = seconds;
                }
            } else {
                minuteRange = minutes;
                if (seconds[0] == Expression.ALL_TIME[0]) {
                    secondRange = Expression.RANGE_MINUTE_SECOND;
                } else {
                    secondRange = seconds;
                }
            }

            //开始装载
            for (int i = 0; i < minuteRange.length; i++) {
                int minuteOffset = minuteRange[i];
                for (int j = 0; j < secondRange.length; j++) {
                    int secondOffset = secondRange[j];
                    //构造索引位置
                    //计算索引位置 然后放上去
                    int index = minuteOffset * 60 + secondOffset;
                    slot.next = SLOTS.get(index);
                    SLOTS.set(index, slot);

                }
            }
        });
    }

    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++) {
            SLOTS.add(null);
        }
    }

    @Override
    public void remount(List<Slot> slotArray) {
        clear();
        mount(slotArray);
    }

    @Override
    public Slot current() {
        int time = TimeUtil.currentSecond();
        return SLOTS.get(time);
    }


}
