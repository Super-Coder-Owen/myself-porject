package com.vip.chapter2.thread.safe;


public class VolatileDemo2 {
   volatile int i = 0;
   public void incr(){
       i++;
   }

    public static void main(String[] args) {
        new VolatileDemo2().incr();
    }
}
