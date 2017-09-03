package util.concurrent;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by chenming on 17/3/18.
 */
@Slf4j
public class Concurrent {
    //@Test
    public void test_AtomicInteger() {
        AtomicInteger atomicInteger = new AtomicInteger();
        Runnable runnable = () -> {
            atomicInteger.getAndIncrement();
            log.info(String.valueOf(atomicInteger.get()));
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    private int increment(int i) {
        return i + 1;
    }

    int integer = 0;

    //@Test
    public void test_Integer() {
        Runnable runnable = () -> {
            integer = increment(integer);
            log.info(String.valueOf(integer));
        };
        for (int i = 0; i < 10; i++) {
            new Thread(runnable).start();
        }
    }

    @Test
    public void test(){
        SmallProductCache.INSTANCE.addSmallProductsMap("master",1);
        SmallProductCache.INSTANCE.addSmallProductsMap("master",2);
        SmallProductCache.INSTANCE.addSmallProductsMap("master",3);
        SmallProductCache.INSTANCE.addSmallProductsMap("master",4);
        SmallProductCache.INSTANCE.addSmallProductsMap("master",5);

        new Thread(){
            public void run(){
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SmallProductCache.INSTANCE.addSmallProductsMap("master",6);
                System.out.println("add value in another thread");
            }
        }.start();

        List<Integer> list=ImmutableList.copyOf(SmallProductCache.INSTANCE.fetchSmallProducts(2,"master"));
        list.parallelStream().forEach(smallProduct->{
            for(int i=0;i<30;i++){
                System.out.println(smallProduct);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



    }

    public enum SmallProductCache {
        INSTANCE;
        private Map<String, List<Integer>> smallProductsMap = new HashMap<>();

        public synchronized void addSmallProductsMap(String branch,Integer i){
            List<Integer> list=smallProductsMap.get(branch);
            if(list==null){
                list=new ArrayList<>();
                smallProductsMap.put(branch,list);
            }
            list.add(i);
        }

        public synchronized List<Integer> fetchSmallProducts(int size, String branch) {
            assert size >= 0;
            List<Integer> smallProducts = smallProductsMap.get(branch);
            Collections.sort(smallProducts);
            List<Integer> result;
            if (size <= smallProducts.size()) {
                result = smallProducts.subList(0, size);
                smallProducts = smallProducts.subList(size, smallProducts.size());
                smallProductsMap.put(branch, smallProducts);
            } else {
                result = smallProducts;
                smallProductsMap.remove(branch);
            }
            return result;
        }

    }

    @Test
    public void sublist_method_is_a_view_of_source_list(){
        List<Integer> list=new ArrayList<Integer>();
        for(int i=0;i<10;i++){
            list.add(i);
        }

        List<Integer> subList=list.subList(3,5);
        subList.add(11);

        System.out.println(list);//exist 11
    }
}
