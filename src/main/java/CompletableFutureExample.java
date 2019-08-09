import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

public class CompletableFutureExample {

    @Test
    public void test1() throws ExecutionException, InterruptedException {
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        completableFuture.complete("Future's Result");
        String result = completableFuture.get();
        System.out.println(result);
    }

    /**
     * You might be wondering that - Well, I know that the runAsync() and supplyAsync() methods
     * execute their tasks in a separate thread. But, we never created a thread right?
     * Yes! CompletableFuture executes these tasks in a thread obtained from the global ForkJoinPool.commonPool().
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            // Simulate a long-running Job
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            System.out.println("I'll run in a separate thread than the main thread.");
        });

        // Block and wait for the future to complete
        future.get();
    }

    @Test
    public void test3() throws ExecutionException, InterruptedException {
        // Run a task specified by a Supplier object asynchronously
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        });

        // Block and get the result of the Future
        String result = future.get();
        System.out.println(result);

    }

    @Test
    public void test4() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(10);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of the asynchronous computation";
        }, executor);
    }

    /**
     * The CompletableFuture.get() method is blocking. It waits until the Future is
     * completed and returns the result after its completion.
     * <p>
     * But, that’s not what we want right? For building asynchronous systems we should
     * be able to attach a callback to the CompletableFuture which should automatically get called when the Future completes.
     * <p>
     * That way, we won’t need to wait for the result, and we can write the logic
     * that needs to be executed after the completion of the Future inside our callback function.
     * <p>
     * You can attach a callback to the CompletableFuture using thenApply(), thenAccept() and thenRun() methods
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test5() throws ExecutionException, InterruptedException {
        // Create a CompletableFuture
        CompletableFuture<String> whatsYourNameFuture = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Gunjan";
        });

        // Attach a callback to the Future using thenApply()
        CompletableFuture<String> greetingFuture = whatsYourNameFuture.thenApply(name -> {
            System.out.println("Hello " + name);
            return "Hello " + name;
        });

        // Block and get the result of the future.
        System.out.println(greetingFuture.get()); // Hello Gunjan

    }

    /**
     * You can also write a sequence of transformations on the CompletableFuture by attaching a
     * series of thenApply() callback methods. The result of one thenApply() method is passed
     * to the next in the series
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test6() throws ExecutionException, InterruptedException {
        CompletableFuture<String> welcomeText = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Gunjan";
        }).thenApply(name -> {
            return "Hello " + name;
        }).thenApply(greeting -> {
            return greeting + ", Welcome to java 8 CompletableFuture";
        });

        System.out.println(welcomeText.get());
        // Prints - Hello Gunjan, Welcome to the CalliCoder Blog
    }

    /**
     * If you don’t want to return anything from your callback function and just want to run some piece
     * of code after the completion of the Future, then you can use thenAccept() and thenRun() methods.
     * These methods are consumers and are often used as the last callback in the callback chain.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test7() throws ExecutionException, InterruptedException {
        CompletableFuture.supplyAsync(() -> "Gunjan").thenAccept(name -> {
            System.out.println("Username " + name);
        });
    }

    @Test
    public void test8() {
        CompletableFuture.supplyAsync(() -> "Hello").thenRun(() -> {
            System.out.println("World");
        });
    }

    /**
     * All the callback methods provided by CompletableFuture have two async variants
     *
     * <U> CompletableFuture<U> thenApply(Function<? super T,? extends U> fn)
     * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn)
     * <U> CompletableFuture<U> thenApplyAsync(Function<? super T,? extends U> fn, Executor executor)
     * <p>
     * These async callback variations help you further parallelize your computations by executing the callback tasks in a separate thread.
     */
    @Test
    public void test9() {
        CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Some Result";
        }).thenApply(result -> {
            /*
              Executed in the same thread where the supplyAsync() task is executed
              or in the main thread If the supplyAsync() task completes immediately (Remove sleep() call to verify)
            */
            System.out.println("Thread Name: " + Thread.currentThread().getName());
            return "Processed Result";
        });
    }

    @Test
    public void test10() {
        CompletableFuture.supplyAsync(() -> "Some Result").thenApplyAsync(result -> {
            // Executed in a different thread from ForkJoinPool.commonPool()
            return "Processed Result";
        });
    }

    @Test
    public void test11() {
        Executor executor = Executors.newFixedThreadPool(2);
        CompletableFuture.supplyAsync(() -> {
            return "Some result";
        }).thenApplyAsync(result -> {
            // Executed in a thread obtained from the executor
            return "Processed Result";
        }, executor);
    }

    /**
     * Combine two dependent futures using thenApply
     */
    @Test
    public void test12() throws ExecutionException, InterruptedException {
        CompletableFuture<CompletableFuture<Integer>> result = getX(1)
                .thenApply(x -> sum(x + 2));
        //In earlier examples, the Supplier function passed to thenApply()
        // callback would return a simple value but in this case, it is
        // returning a CompletableFuture. Therefore, the final result in
        // the above case is a nested CompletableFuture.
        System.out.println("SUM = " + result.get().get());
    }

    CompletableFuture<Integer> getX(Integer x) {
        return CompletableFuture.supplyAsync(() -> {
            return x;
        });
    }

    CompletableFuture<Integer> sum(Integer y) {
        return CompletableFuture.supplyAsync(() -> {
            return y;
        });
    }

    /**
     * Combine two dependent futures using thenCompose
     */
    @Test
    public void test13() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> result = getX(1)
                .thenCompose(x -> sum(x + 2));
        //If you want the final result to be a top-level Future, use thenCompose() method instead
        System.out.println("SUM = " + result.get());
    }

    /**
     * Combine two independent futures using thenCombine
     * The callback function passed to thenCombine() will be called when both the Futures are complete.
     */
    @Test
    public void test14() throws ExecutionException, InterruptedException {
        CompletableFuture<Integer> one = CompletableFuture.supplyAsync(() -> 1);
        CompletableFuture<Integer> two = CompletableFuture.supplyAsync(() -> 2);
        CompletableFuture<Integer> three = one.thenCombine(two, (x, y) -> x + y);
        System.out.println(three.get());
    }

    /**
     * CompletableFuture.allOf is used in scenarios when you have a List of independent futures
     * that you want to run in parallel and do something after all of them are complete.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test15() throws ExecutionException, InterruptedException {
        List<Integer> arrayIntegers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        // Download contents of all the web pages asynchronously
        List<CompletableFuture<Integer>> numbersMultiply = arrayIntegers.stream()
                .map(x -> multiply(x, 3))
                .collect(Collectors.toList());


        // Create a combined Future using allOf()
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                numbersMultiply.toArray(new CompletableFuture[numbersMultiply.size()])
        );

        // When all the Futures are completed, call `future.join()` to get their results and collect the results in a list -
        CompletableFuture<List<Integer>> allPageContentsFuture = allFutures.thenApply(v -> numbersMultiply.stream()
                .map(pageContentFuture -> pageContentFuture.join())
                .collect(Collectors.toList()));

        CompletableFuture<Integer> sumFuture = allPageContentsFuture.thenApply(list -> list.stream().reduce(0, (x, y) -> x + y));

        System.out.println(sumFuture.get());

    }

    CompletableFuture<Integer> multiply(Integer x, Integer y) {
        return CompletableFuture.supplyAsync(() -> x * y);
    }

    /**
     * CompletableFuture.anyOf() as the name suggests, returns a new CompletableFuture which is completed
     * when any of the given CompletableFutures complete, with the same result.
     *
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void test16() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 1";
        });

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 2";
        });

        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(3);
            } catch (InterruptedException e) {
                throw new IllegalStateException(e);
            }
            return "Result of Future 3";
        });

        CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

        System.out.println(anyOfFuture.get()); // Result of Future 2
    }
    @Test
    public void test17() throws ExecutionException, InterruptedException
    {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).exceptionally(ex -> {
            System.out.println("Oops! We have an exception - " + ex.getMessage());
            return "Unknown!";
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }

    @Test
    public void test18() throws ExecutionException, InterruptedException
    {
        Integer age = -1;

        CompletableFuture<String> maturityFuture = CompletableFuture.supplyAsync(() -> {
            if(age < 0) {
                throw new IllegalArgumentException("Age can not be negative");
            }
            if(age > 18) {
                return "Adult";
            } else {
                return "Child";
            }
        }).handle((res, ex) -> {
            if(ex != null) {
                System.out.println("Oops! We have an exception - " + ex.getMessage());
                return "Unknown!";
            }
            return res;
        });

        System.out.println("Maturity : " + maturityFuture.get());
    }
}
