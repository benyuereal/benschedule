package com.moji.schedule.entity;

import lombok.extern.slf4j.Slf4j;

/**
 * 任务执行类
 */
@Slf4j
public class Job implements Runnable {
    private String name;

    public Job() {

    }

    public Job(String name) {
        this.name = name;
    }

    @Override
    public void run() {
        log.info("我是一个定时任务" + name);
    }
}
