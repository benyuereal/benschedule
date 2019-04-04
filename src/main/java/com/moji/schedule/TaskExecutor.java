package com.moji.schedule;

import com.moji.schedule.entity.Slot;
import com.moji.schedule.config.StandardConfig;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
public class TaskExecutor {

    /**
     * 线程池核心线程数目
     */
    private final int coreSize = 5;
    /**
     * 最大数目
     */
    private final int maxSize = 10;
    /**
     * 线程存活时长
     */
    private final int keepAlive = 30;
    /**
     * 队列长度
     */
    private final int capacity = 15;
    /**
     * 线程休眠时长
     */
    private final int sleepTime = 995;

    private BlockingQueue<Runnable> queue;

    private ThreadPoolExecutor executor;

    private TimeTracks timeTracks;

    private StandardConfig standardConfig;

    /**
     * 任务执行
     */
    private boolean status;

    /**
     * 暂停任务
     */
    private boolean suspend;

    public TaskExecutor(TimeTracks timeTracks, StandardConfig standardConfig) {
        this.status = true;
        this.suspend = false;
        this.timeTracks = timeTracks;
        this.standardConfig = standardConfig;
        this.queue = new ArrayBlockingQueue<>(capacity);
        this.executor = new ThreadPoolExecutor(coreSize, maxSize, keepAlive, TimeUnit.SECONDS, queue);
    }

    private final AtomicBoolean flag = new AtomicBoolean(true);


    public void run() {
        boolean sleep = false;
        while (status) {
            if (!suspend) {
                log.info("扫描任务...");
                /**
                 * 计算偏移位置
                 */
                Slot slot = timeTracks.current();
                while (slot != null) {
                    executor.execute(slot.job);
                    slot = slot.next;
                }

                sleep();
                sleep = true;
            }
            if (!sleep) {
                sleep();
            }

        }

    }

    private void sleep() {
        //休眠一秒
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            log.error(e.getMessage(), "线程休眠出现错误");
        }
    }

    public void suspend() {
        this.suspend = true;
    }

}
