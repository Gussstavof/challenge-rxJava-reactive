import rx.Observable;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class Main {

    static void main() throws InterruptedException {
        List<Order> orders = new ArrayList<>(){{
            add(new Order(1, "Joao", 150.0));
            add(new Order(2, "Maria", 80.0));
            add(new Order(3, "Pedro", 300.0));
            add(new Order(4, "Ana", 50.0));
            add(new Order(5, "Carlos", 220.0));
        }};

        Observable.from(orders)
                .filter(order -> order.getPrice() >= 100)
                .map(order -> {
                    System.out.printf("Process order ID: %d\n", order.getId());
                    double price = order.getPrice();
                    order.setPrice(price - (price * 0.1));
                    return order;
                })
                .flatMap(Main::simulationQuery)
                .toBlocking()
                .subscribe(
                        order -> System.out.printf("order %s processed: %s thread: %s\n", order.getId(), order.getPrice(), Thread.currentThread().getName()),
                        Throwable::getStackTrace,
                        () -> System.out.println("Process completed!")
                );
    }

    public static Observable<Order> simulationQuery(Order order) {
        return Observable.just(order)
                .delay(1000, TimeUnit.MILLISECONDS)
                .doOnSubscribe(() -> System.out.println(Thread.currentThread().getName() + ": simulationQuery"))
                .subscribeOn(Schedulers.io());
    }

}
