import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class TmpTest {
    private final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new ArrayBlockingQueue<>(10));

    // 长度尽量为2^n次，方便hash计算
    private final List<ArrayBlockingQueue<String>> arrayBlockingQueueList = Arrays.asList(
            new ArrayBlockingQueue<>(100),
            new ArrayBlockingQueue<>(100),
            new ArrayBlockingQueue<>(100),
            new ArrayBlockingQueue<>(100)
    );

    @Before
    public void before() {
        for (int i = 0; i < arrayBlockingQueueList.size(); i++) {
            final int it = i;
            threadPoolExecutor.execute(() -> {
                try {
                    while (true) {
                        String oj = arrayBlockingQueueList.get(it).take();
                        System.out.println("Thread name " + Thread.currentThread().getName() + "oj = " + oj);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }
    }

    public String randomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(62);
            stringBuilder.append(str.charAt(number));
        }
        return stringBuilder.toString();
    }

    @Test
    public void TestHash() {
        for (int j = 0; j < 10000; j++) {
            String s = randomString(j);
            int t = s.hashCode() & (arrayBlockingQueueList.size() - 1);
            try {
                arrayBlockingQueueList.get(t).put(s);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            TimeUnit.SECONDS.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
