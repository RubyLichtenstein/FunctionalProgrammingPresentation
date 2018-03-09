What exceptions solved in the first place - instead of returning error code, function can return only one value, exception can pass the error to higher functions

What Is an Exception?

https://docs.oracle.com/javase/tutorial/essential/exceptions/advantages.html
Advantages of Exceptions

Advantage 1: Separating Error-Handling Code from "Regular" Code
Advantage 2: Propagating Errors Up the Call Stack
Advantage 3: Grouping and Differentiating Error Types

2) Unchecked are the exceptions that are not checked at compiled time. In C++, all exceptions are unchecked, so it is not forced by the compiler to either handle or specify the exception. It is up to the programmers to be civilized, and specify or catch the exceptions.
In Java exceptions under Error and RuntimeException classes are unchecked exceptions, everything else under throwable is checked.
Here's the bottom line guideline: If a client can reasonably be expected to recover from an exception, make it a checked exception. If a client cannot do anything to recover from the exception, make it an unchecked exception.


https://kotlinlang.org/docs/reference/exceptions.html#checked-exceptions
Checked Exceptions

Kotlin does not have checked exceptions. There are many reasons for this, but we will provide a simple example.


The problems Exceptions

Exception passed to to higher layers of the program and brick the abstraction (open/close)
Users swallow the exception instead of handling it 
The problems in exception concept
Exception not moving threads since stack trace is per thread (async operations)
Heavy operation
Error hell 

Better error system!
Rx for rescue!

Rx Error mechanized 
https://github.com/ReactiveX/RxJava/wiki/Error-Handling-Operators
There are a variety of operators that you can use to react to or recover from onError notifications from Observables. For example, you might:

swallow the error and switch over to a backup Observable to continue the sequence
swallow the error and emit a default item
swallow the error and immediately try to restart the failed Observable
swallow the error and try to restart the failed Observable after some back-off interval
The following pages explain these operators.

onErrorResumeNext( ) — instructs an Observable to emit a sequence of items if it encounters an error
onErrorReturn( ) — instructs an Observable to emit a particular item when it encounters an error
onExceptionResumeNext( ) — instructs an Observable to continue emitting items after it encounters an exception (but not another variety of throwable)
retry( ) — if a source Observable emits an error, resubscribe to it in the hopes that it will complete without error
retryWhen( ) — if a source Observable emits an error, pass that error to another Observable to determine whether to resubscribe to the source

the problem in functional style 
http://blog.danlew.net/2015/12/08/error-handling-in-rxjava/

fatal exceptions

Checked Exceptions

In other words: often times, instead of calling onError and skipping code, you actually want to call onNext with an error state. It's much easier to handle problems in onNext since you still get to run all your code and the stream isn't terminated.1

https://github.com/ReactiveX/RxJava/wiki/Error-Handling

https://rongi.github.io/kotlin-blog/rxjava/rx/2017/08/01/error-handling-in-rxjava.html
 
Retrofit to demonstrate 
2 resonse to do retry - 1. The error can do again (with condition), the error not going to append again

EIter


 