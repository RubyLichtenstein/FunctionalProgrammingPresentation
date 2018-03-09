//package com.rubylich.exmple;
//
//import java.io.IOException;
//
///**
// * Created by rl98880 on 02/03/2018.
// */
//
//public class ExeptionChecteUncheced {
//    public void checked() throws IOException {
//        throw new IOException();
//    }
//
//    public void unchecked() {
//        throw new NullPointerException();
//    }
//
//    public void consum() {
//        checked();
//
//        unchecked();
//    }
//
//    public void consum1() {
//        try {
//            checked();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        unchecked();
//    }
//}
