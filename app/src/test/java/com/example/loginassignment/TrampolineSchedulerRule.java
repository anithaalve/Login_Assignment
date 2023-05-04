///*
// * TrampolineSchedulerRule.java
// * PDS_Madelyne_Android
// *
// * Created by Mohan on 9/25/2019
// * Copyright © 2019 Eli Lilly and Company. All rights reserved.
// *
// */
//package com.example.loginassignment;
//
//import org.junit.rules.TestRule;
//import org.junit.runner.Description;
//import org.junit.runners.model.Statement;
//
///**
// * Class representing the Sequential evaluation of inputs on trampoline thread, irrespective of the thread it Scheduled on
// *
// * Mainly Usage of class is for managing IO threads i.e.., .subscribeOn(Schedulers.io()) while Unit Testing.
// **/
//public class TrampolineSchedulerRule implements TestRule {
//
//  @Override
//  public Statement apply(Statement base, Description description) {
//    return new MyStatement(base);
//  }
//
//  public static class MyStatement extends Statement {
//    private final Statement base;
//
//    @Override
//    public void evaluate() throws Throwable {
//      try {
//        RxJavaPlugins.setComputationSchedulerHandler(scheduler -> Schedulers.trampoline());
//        RxJavaPlugins.setIoSchedulerHandler(scheduler -> Schedulers.trampoline());
//        RxJavaPlugins.setNewThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
//        RxJavaPlugins.setSingleSchedulerHandler(scheduler -> Schedulers.trampoline());
//        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> Schedulers.trampoline());
//        base.evaluate();
//      } finally {
//        RxJavaPlugins.reset();
//        RxAndroidPlugins.reset();
//      }
//    }
//
//    public MyStatement(Statement base) {
//      this.base = base;
//    }
//  }
//}
