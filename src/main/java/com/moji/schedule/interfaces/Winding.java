package com.moji.schedule.interfaces;

import com.moji.schedule.entity.Slot;

import java.util.List;

/**
 * 回环接口
 */
public interface Winding {
    /**
     * 挂载任务插槽
     */
    public void mount(List<Slot> slotArray);

    /**
     * 清理插槽
     */
    public void clear();

    /**
     * 重新挂载插槽
     */
    public void remount(List<Slot> slotArray);

    /**
     * 当前插槽
     */
    public Slot current();


}
