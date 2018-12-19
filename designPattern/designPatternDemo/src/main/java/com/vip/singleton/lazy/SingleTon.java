package com.vip.singleton.lazy;

/**
 * @author owen
 * @description
 * @since 2018/12/13.
 */
public class SingleTon {
    private SingleTon(){

    }

    public static final SingleTon singleTonInstance(){
        return SingleTonHandle.singleTon;
    }

    private static class SingleTonHandle{
        private static final SingleTon singleTon = new SingleTon();
    }

    public static void main(String[] args) {
        new Thread(()->{
            for(int i=0;i<1000;i++){
                System.out.println(SingleTon.singleTonInstance());
            }
        }).start();

        new Thread(()->{
            for(int i=0;i<1000;i++){
                System.out.println(SingleTon.singleTonInstance());
            }
        }).start();
    }
}
