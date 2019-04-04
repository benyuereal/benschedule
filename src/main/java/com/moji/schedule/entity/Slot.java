package com.moji.schedule.entity;

import com.moji.schedule.exception.ExpressionException;
import com.moji.schedule.enums.Expression;

/**
 * 插槽实体类
 * 每个插槽有相应的时间表达式、
 */

public class Slot {


    private String distributeKey;

    private final int factor = 6;

    //对应的时间表达式 例如: * * * * 1 表示在每秒执行
    public String expression;

    //分表表示 年月日时分秒 1是执行 0是不执行 3所有的都执行
    private int[][] timeStep;

    public Runnable job;

    public Slot next;

    public Slot(String expression, Runnable job,String distributeKey) throws ExpressionException {
        this(expression,job);
        this.distributeKey=distributeKey;
    }
    public Slot(Slot slot) {
        next = slot;
    }


     public Slot(String expression, Runnable job) throws ExpressionException {
        timeStep = new int[][]{{0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}, {0, 0}};
        if (expression == null || expression.isEmpty()) {
            throw new NullPointerException();
        }
        if (job == null) {
            throw new NullPointerException();
        }

        /**
         * 时间表达式解析
         */
        String reg = " ";
        String[] expressions = expression.split(reg);
        if (expressions.length != factor) {
            throw new ExpressionException();
        }
        for (int i = 0; i < factor; i++) {
            String value = expressions[i];
            if (value == null || value.isEmpty()) {
                throw new ExpressionException();
            }
            /**
             * 开始研究时间表达式
             */
            switch (i) {
                case 0:
                    setStepByExpression(reg, value, i, null);
                    break;
                case 1:
                    setStepByExpression(reg, value, i, Expression.ALL_MONTH);
                    break;
                case 2:
                    setStepByExpression(reg, value, i, Expression.ALL_DAY);
                    break;
                case 3:
                    setStepByExpression(reg, value, i, Expression.ALL_HOUR);
                    break;
                case 4:
                    setStepByExpression(reg, value, i, Expression.ALL_MINUTE);
                    break;
                case 5:
                    setStepByExpression(reg, value, i, Expression.ALL_SECOND);
                    break;
            }
        }
        //时间表达式设置完毕,设置任务 字符串时间表达式
        this.expression = expression;
        this.job = job;


    }

    private void setStepByExpression(String reg, String value, int i, int[] range) throws ExpressionException {
        if (value.equals(Expression.ALL) || value.equals(Expression.TOTAL)) {
            timeStep[i] = Expression.ALL_TIME;
        } else if (value.contains(Expression.SPACE)) {
            String[] values = value.split(Expression.SPACE);
            if (values.length != 2) {
                throw new ExpressionException();
            }
            int[] rangeValue = new int[2];
            try {
                rangeValue[0] = (Integer.valueOf(values[0]));
                rangeValue[1] = (Integer.valueOf(values[1]));

            } catch (Exception e) {
                e.printStackTrace();
                throw new ExpressionException();
            }
            int[] inner = new int[rangeValue[1] - rangeValue[0] + 1];
            int k = 0;
            for (int j = rangeValue[0]; j < rangeValue[1] + 1; j++, k++) {
                inner[k] = j;
                if (range != null) {
                    if (range[0] > inner[k] || range[1] < inner[k]) {
                        throw new ExpressionException();
                    }
                }
            }
            timeStep[i] = inner;
        } else if (value.contains(",")){
            reg=",";
            setStep(reg, value, i, range);
        }else {
            //如果不是在一个区间内运行 也不是在一个所有的时间段 运行 那就看是不是在指定时间段运行
            setStep(reg, value, i, range);
        }
    }

    private void setStep(String reg, String value, int i, int[] range) throws ExpressionException {
        //如果不是在一个区间内运行 也不是在一个所有的时间段 运行 那就看是不是在指定时间段运行
        String[] values = value.split(reg);
        try {
            int[] inner = new int[values.length];
            int length = values.length;
            for (int j = 0; j < length; j++) {
                inner[j] = Integer.parseInt(values[j]);
                if (range != null) {
                    if (range[0] > inner[j] || range[1] < inner[j]) {
                        throw new ExpressionException();
                    }
                }
            }
            timeStep[i] = inner;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ExpressionException();
        }
    }


    public int[] year() {
        return timeStep[0];
    }

    public int[] month() {
        return timeStep[1];
    }

    public int[] day() {
        return timeStep[2];
    }

    public int[] hour() {
        return timeStep[3];
    }

    public int[] minute() {
        return timeStep[4];
    }

    public int[] second() {
        return timeStep[5];
    }

}
