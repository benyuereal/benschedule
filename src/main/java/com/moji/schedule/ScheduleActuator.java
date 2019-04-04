package com.moji.schedule;

import com.moji.schedule.entity.Job;
import com.moji.schedule.entity.Slot;
import com.moji.schedule.config.StandardConfig;
import com.moji.schedule.exception.ExpressionException;
import com.moji.schedule.interfaces.Schedule;

import java.util.ArrayList;
import java.util.List;

/**
 * 定时执行器
 */
public class ScheduleActuator implements Schedule {

    /**
     * 任务轨道
     */
    private TimeTracks timeTracks;

    /**
     * 任务执行器
     */
    private final TaskExecutor taskExecutor;

    /**
     * 任务标准设置
     */
    private StandardConfig standardConfig;

    public ScheduleActuator() {
        //装载配置
        this.standardConfig=new StandardConfig();
        standardConfig.setDistributed(true);
        List<Slot> slots = new ArrayList<>();
        Slot slot = null;
        Slot slot1 = null;
        Slot slot2 = null;
        try {
            slot = new Slot("2019 3 4 10 0/41 2,7,8", new Job("第一个任务"));
            slot2 = new Slot("2019 3 4 10 0/32 18,59,0", new Job("第二个任务"));
            slot1 = new Slot("2019 3 4 10 0/13 1/6", new Job("第三个任务"));
        } catch (ExpressionException e) {
            e.printStackTrace();
        }
        slots.add(slot);
        slots.add(slot2);
        slots.add(slot1);
        timeTracks = new TimeTracks();
        timeTracks.mount(slots);
        taskExecutor = new TaskExecutor(timeTracks,standardConfig);
    }

    @Override
    public void start() {
        taskExecutor.run();
    }

    @Override
    public void stop() {
        taskExecutor.suspend();
    }

    /**
     * 测试主方法
     */
    public static void main(String[] args) {
        Schedule scheduleActuator = new ScheduleActuator();
        scheduleActuator.start();
    }
}
