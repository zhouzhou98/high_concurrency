package com.concurrency.currency01.service.impl;

import com.concurrency.currency01.domain.User;
import com.concurrency.currency01.mapper.UserMapper;
import com.concurrency.currency01.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    /**
     * 百万数据插入
     * @return
     */
    @Override
    public String hanlder() {
        ExecutorService pool = Executors.newFixedThreadPool(100);
        int totalPageNo = 150;
        int pageSize = 20000;
        long start = System.currentTimeMillis();
        AtomicInteger atomicInt = new AtomicInteger();
        for(int currentPageNo = 0; currentPageNo < totalPageNo; currentPageNo++){
           int finalCurrentPageNo = currentPageNo;
          Runnable run = new Runnable() {
             @Override
             public void run() {
               List userList=new ArrayList<>();
               for(int i=1;i<=pageSize;i++){
                 int id=i+ finalCurrentPageNo *pageSize;
                 User user =new User();
                 user.setId(id);
                 user.setName("zhouzhou:"+id);
                 userList.add(user);
               }
               atomicInt.addAndGet( userMapper.batchSave(userList));
               if(atomicInt.get() ==(totalPageNo*pageSize)){
                    System.out.println("  sync data to db, it  has spent " +(System.currentTimeMillis()-start)+"  ms");
               }
             }
          };
          try {
              Thread.sleep(5);
          } catch (InterruptedException e) {
              e.printStackTrace();
          }
          pool.execute(run);
        }
        return  "ok";
    }
}


