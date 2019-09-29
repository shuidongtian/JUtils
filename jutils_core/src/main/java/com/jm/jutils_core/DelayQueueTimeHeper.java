package com.jm.jutils_core;


import android.os.Build;

import androidx.annotation.RequiresApi;

import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;

public class DelayQueueTimeHeper {

    private volatile boolean isShutDown;

    private Thread thread;
    private DelayQueue<Task> queue = new DelayQueue<>();
    private Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                while (!isShutDown) {
                    Task task = queue.take();
                    if (task.loop()) {
                        queue.add(task);
                    }
                    try {
                        task.task.run();
                    } catch (Exception e) {
                        /* 任务线程抛出异常. */
                    }
                }
            } catch (InterruptedException e) {
                /* 线程被终止.*/
            }
        }
    };

    public DelayQueueTimeHeper() {
        thread = new Thread(runnable);
        thread.start();
    }

    public DelayQueueTimeHeper(ExecutorService executor) {
        executor.execute(runnable);
    }

    public void schedule(Runnable task, int delay) {
        queue.add(new Task(task, delay, TimeUnit.MILLISECONDS));
    }

    public void schedule(Runnable task, int delay, TimeUnit unit) {
        queue.add(new Task(task, delay, unit));
    }

    public void schedule(Runnable task, int delay, int period) {
        queue.add(new Task(task, delay, TimeUnit.MILLISECONDS, period));
    }

    public void schedule(Runnable task, int delay, TimeUnit unit, int period) {
        queue.add(new Task(task, delay, unit, period));
    }

    public void destory() {
        isShutDown = true;
        queue = null;
        if (thread != null) {
            thread.interrupt();
            thread = null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void cancel(final Runnable task) {
        queue.removeIf(new Predicate<Task>() {
            @Override
            public boolean test(Task t) {
                return t.task == task;
            }
        });
    }

    public class Task implements Delayed {
        private Runnable task;
        private long triger;
        private long period;

        public Task(Runnable task, long delay, TimeUnit unit) {
            this(task, delay, unit, 0);
        }

        public Task(Runnable task, long delay, TimeUnit unit, long period) {
            this.task = task;
            this.triger = calculateNextTriger(delay, unit);
            this.period = unit.toNanos(period);
        }

        private long calculateNextTriger(long delay, TimeUnit unit) {
            return System.nanoTime() + unit.toNanos(delay);
        }

        public boolean loop() {
            if (period != 0) {
                this.triger = calculateNextTriger(period, TimeUnit.NANOSECONDS);
                return true;
            }
            return false;
        }

        @Override
        public int compareTo(Delayed delayed) {
            Task another = (Task) delayed;
            return Long.compare(this.triger, another.triger);
        }

        @Override
        public long getDelay(TimeUnit unit) {
            return unit.convert(triger - System.nanoTime(), TimeUnit.NANOSECONDS);
        }
    }
}
