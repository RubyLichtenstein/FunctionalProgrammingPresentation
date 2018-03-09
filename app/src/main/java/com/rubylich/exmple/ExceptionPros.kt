//package com.rubylich.exmple
//
///**
// * Created by rl98880 on 02/03/2018.
// */
//class ExceptionPros {
//
//    //openFile(path)
//    //readFile(file)
//    //print(content)
//    //closeTheFile(file)
//
//    private val SUCCESSES = 0
//    private val OPEN_FILE_FAILED = -1
//    private val READ_FILE_FAILED = -2
//    private val CLOSE_FILE_FAILED = -3
//
//    fun readFile(path: String): Int {
//        var errorCode = SUCCESSES
//
//        val file = openFile(path)
//        if (fileIsOpen(file)) {
//            val content = readFile(file)
//            if (isReadSuccesed(content)) {
//                print(content)
//                val isClosed = closeTheFile(file)
//                if (isClosed) {
//                    errorCode = SUCCESSES
//                } else {
//                    errorCode = CLOSE_FILE_FAILED
//                }
//            } else {
//                errorCode = READ_FILE_FAILED
//            }
//        } else {
//            errorCode = OPEN_FILE_FAILED
//        }
//
//        return errorCode
//    }
//
//    fun readFileWithException(path: String) {
//        try {
//            val file = openFile(path)
//            val content = readFile(file)
//            print(content)
//            closeTheFile(file)
//        } catch (fileOpenFailed: Exception) {
//            //doSomething
//        } catch (readFailed: Exception) {
//            //doSomething
//        } catch (closeFailed: Exception) {
//            //doSomething
//        }
//    }
//
//    private fun fileIsOpen(file: Any): Boolean {
//        TODO()
//    }
//
//    private fun isReadSuccesed(fileContent: String): Boolean {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private fun closeTheFile(file: Any): Boolean {
//        TODO()
//    }
//
//    private fun readFile(file: Any): String {
//        TODO()
//    }
//
//    private fun allocateThatMuchMemory(size: Any): Any {
//        TODO()
//    }
//
//    private fun determineItsSize(file: Any): Any {
//        TODO()
//    }
//
//    private fun openFile(path: String): Any {
//        TODO()
//    }
//}
//method1 {
//    errorCodeType error;
//    error = call method2;
//    if (error)
//        doErrorProcessing;
//    else
//        proceed;
//}
//
//
//class callStack {
//    fun method1(): Int {
//        var error = method2()
//        if (error != 0)
//            return doErrorProcessing(error)
//        else
//            return proceed()
//    }
//
//    fun method2(): Int {
//        var error = method3()
//        if (error != 0)
//            return doErrorProcessing(error)
//        else
//            return proceed()
//    }
//
//    fun method3(): Int {
//        var error = readFile()
//        if (error != 0)
//            return doErrorProcessing(error)
//        else
//            return proceed()
//    }
//
//    private fun readFile(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//
//    private fun proceed(): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//
//    private fun doErrorProcessing(error: Int): Int {
//        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//    }
//}
//
