package com.moji.schedule.config;

import lombok.Data;

/**
 * 定时器标准设置
 */
@Data
public class StandardConfig {
    /**
     * 是否分布式
     */
    private boolean distributed;
    /**
     * 是否持久化
     */
    private boolean persistent;
    /**
     * 是否异步去执行
     */
    /*
    ............................
    * */
    /**
     * 是否采用补偿机制 用一个守护线程来判断任务是否执行
     */
    private boolean reimbursement;





}
