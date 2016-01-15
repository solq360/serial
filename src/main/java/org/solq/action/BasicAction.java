package org.solq.action;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 基本动作
 * 
 * @author solq
 */
public class BasicAction implements IAction {
    /** 动作名称 */
    private String name;
    /** 电机值开始点 */
    private double start;
    /** 电机值结束点 */
    private double end;
    /** 电机执行时间 */
    private long time;
    /** 电机执行延时时间 */
    private long timeDelay = 0L;
    /** 是否加速 */
    private double speed = 1.0d;

    ////////////////////// 编译生成//////////////////////////
    /** 周期执行时间 */
    private long rateTime = 0L;
    /** 开始执行时间 */
    private long startTime;
    /** 结束执行时间 */
    private long endTime;
    /** 执行次数 */
    private long runCount;

    private void buildTime() {
	long now = System.currentTimeMillis();
	this.startTime = now + timeDelay;
	this.endTime = this.startTime + time;
	this.rateTime = Double.valueOf((Double.valueOf(time) / (end - start) * speed)).longValue();
	this.runCount = 0;
	System.out.println("动作 ： " + name + " 执行周期 : " + rateTime);
    }

    public void run() {
	this.runCount++;
	printTime();
    }

    void printTime() {
	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");
	System.out.println("动作 ： " + name + " 执行时间 : " + (sdf.format(new Date())) + " : " + this.runCount);
    }

    public boolean checkRun() {
	long now = System.currentTimeMillis();
	boolean flag = startTime <= now && now <= endTime;
	if (!flag) {
	    return false;
	}
	long nextRunTime = startTime + this.rateTime * (this.runCount + 1);
	if (nextRunTime > now) {
	    return false;
	}
	return true;
    }

    public static void main(String[] args) {

	List<BasicAction> task = new ArrayList<>();
	for (int i = 0; i < 3; i++) {
	    BasicAction action = new BasicAction();
	    action.name = " " + i;
	    action.start = 50 + i * 20;// 50度开始
	    action.end = 180;// 180度结束
	    action.time = 5000L;// 执行3秒
	    action.timeDelay = 500L + i * 800;// 延时执行时间
	    action.speed=1.d+i;//加速
	    action.buildTime();
	    action.printTime();
	    task.add(action);
	}

	while (true) {
	    for (BasicAction action : task) {
		if (action.checkRun()) {
		    action.run();
		}
	    }
	}
    }

}
