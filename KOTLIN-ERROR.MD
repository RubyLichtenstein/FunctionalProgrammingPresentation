Error hndling in kotlin - The functional way 

In java error handling is with try catch block 
creating error is with check exception added to the methode signetuer 
check exception is controversy subject 

So what is the functional way to do error handling 
lets look at scala ;)

In scala we have 2 object in 
for consuming error - Try
for creating error - Either 

So how we do it? 

We use some API that may throw exception - in kotlin even tho 
we use api that throw check exception kotlin not force as to wrap the method call 
with try, since kotlin decide to throw away checked exception

The basic try operation 

1. Consuming try operation 
Since 
```kotlin
  when (Try { 1 / 0 }) {
            is Try.Failure -> { print("Failure") }
            is Try.Success -> { print("Success") }
        }
```

Try implementation 
invoke operator allow us to use function in the class and drop 
the function name 
Allow us to write cleaner api? 

```kotlin
  inline operator fun <A> invoke(f: () -> A): Try<A> =
                try {
                    Success(f())
                } catch (e: Throwable) {
                    Failure(e)
                }

```

Try.map
Since Try is monad - map allow us to do operation on 
Map allow us to change the return type in success case, in failure it just pass the error 

The map function is inlined its big optimisation since its put the byte code and not 
Create object 
the cross inline on the lambda so we won’t be able to do a return in such lambdas.
Why? 

   /**
     * Maps the given function to the value from this `Success` or returns this if this is a `Failure`.
     */
```kotlin
    private fun fetchDataMap() =
        with(tryDataStore) {
            fetchFromMemory().map { it.toLowerCase() }
        }
```

Try.flatMap maps the value to other try
 
//recover 

//fold 
recover is like flat map that operate on failure case while flat map operate on the success case 


Either is for creating Success error apis 
Lets think about domain use case - login(email, password)

And our system either Success with access token or Failure with some Error
in one of the fallowing - email not valid/ password not valid/

Error is sealed class so we can do patter matching on it 

Lets look on example 

