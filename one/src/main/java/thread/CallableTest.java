package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

public class CallableTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		Callable<String> call = new Callable<String>() {

			@Override
			public String call() throws Exception {
				Thread.sleep(1000);
				System.out.println("in callable " + System.currentTimeMillis());
				return "callable return";
			}

		};
		FutureTask<String> ft = new FutureTask<>(call);
		new Thread(ft).start();
		System.out.println("in main thread start" + System.currentTimeMillis());

		boolean isdone = ft.isDone();
		System.out.println("callable is done " + isdone + " " + System.currentTimeMillis());
		boolean iscancel = ft.isCancelled();
		System.out.println("callable is cancel " + iscancel + " " + System.currentTimeMillis());

		
		String rs = ft.get();
		System.out.println("in main get result==" + rs + " " + System.currentTimeMillis());
		
		ft.cancel(false);
		iscancel = ft.isCancelled();
		System.out.println("callable is cancel " + iscancel + " " + System.currentTimeMillis());
		ExecutorService exec = Executors.newFixedThreadPool(3);
	}
}
