package util.concurrent;

import org.junit.Test;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by chenming on 17/4/12.
 */
public class TimerTest {
    TimerTask timerTask=new TimerTask(){
        @Override
        public void run() {
            System.out.println("timer task exec");
        }
    };
    Timer timer=new Timer();

    public void startTimer(){
        timer.schedule(timerTask,10000);

    }

    public void executeImmediate(){
        timerTask.cancel();
        timer.cancel();
        System.out.println("executeImmediate");
        timerTask.run();
        System.out.println("executeImmediate end");
    }

    @Test
    public void test(){
        startTimer();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executeImmediate();
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
