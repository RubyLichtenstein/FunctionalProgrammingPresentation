package com.rubylich.exmple;

import java.io.IOException;

/**
 * Created by rl98880 on 05/03/2018.
 */

public class ProblemsWithCheckd {

    static public void main(String[] arg) {
//        try {
//            throw new OutOfMemoryError();
//            throw new StackOverflowError();
//            throw new ArrayIndexOutOfBoundsException();
//            throw new NullPointerException();
//        } catch (Exception e) {
//            System.out.println(e);
//        }
    }

    /*Many programmers hate checked exceptions because they're
    forced to deal with APIs that overuse
    them or incorrectly specify checked exceptions instead of
    unchecked exceptions as part of their contracts.
    For example, a method that sets a sensor's value is passed an invalid number and throws a checked exception
    instead of an instance of the unchecked java.lang.IllegalArgumentException class.*/

    //Checked exceptions are easy to ignore by
    //rethrowing them as RuntimeException instances,
    //so what's the point of having them?
    public void a() {
        try {
            doStuffThatThrowsCheckedException();
        } catch (AnnoyingCheckedException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData() {
        try {
            saveToCloud();
        } catch (IOException io) {
            try {
                saveToDisk();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveToCloud() throws IOException {

    }

    private void saveToDisk() throws IOException {

    }

    public void doStuff(String argument) {
        try {
            doStuffThatThrowsCheckedException();
        } catch (AnnoyingCheckedException e) {
            throw new RuntimeException(e);
        }
    }

    //Checked exceptions can be ignored by swallowing them,
    // so what's the point of having them?
    public void b() {
        try {
            doStuffThatThrowsCheckedException();
        } catch (AnnoyingCheckedException e) {
            // do nothing
        }
    }

    public void doStuff() {
        try {
            openFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openFile() throws IOException {
        openPrimaryFile_Unrecoverable();
        openSecondaryFile_Recoverable();
    }

    public void openPrimaryFile_Unrecoverable() throws IOException {

    }

    public void openSecondaryFile_Recoverable() throws IOException {

    }

    //Checked exceptions result in multiple throws clause declarations.
    //The thing about checked exceptions is that they are not really exceptions by the usual understanding of the concept.
    // Instead, they are API alternative return values.

    private void doStuffThatThrowsCheckedException() throws AnnoyingCheckedException {

    }

    private class AnnoyingCheckedException extends Exception {
    }
}
