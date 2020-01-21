package ru.epam.javacore.lesson_16_concurrency.lesson.a_4_account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class A_3_AccountDemo_Probleb_DeadLock_Solution_1 {
    private static Boolean ff = true;
    private static AtomicInteger index = new AtomicInteger(0);

    private static Thread transfer(Account src, Account dest, int money) {
        List<Account> accountList = new ArrayList<>();
        accountList.add(src);
        accountList.add(dest);

        accountList.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                return o2.getId() - o1.getId();
            }
        });
        System.out.println(accountList);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (accountList.get(0)) {
                    System.out.println("Get money from account  " + src.getId());
                    sleep(1000);

                    synchronized (accountList.get(1)) {
                        int i = index.incrementAndGet();
                        System.out.println("Transaction begin " + i);
                        System.out.println("Transfer mony to account " + dest.getId());
                        src.setAmount(src.getAmount() - money);
                        sleep(1000);
                        System.out.println("Transaction body " + i);
                        dest.setAmount(dest.getAmount() + money);
                        System.out.println("Transaction end " + i + "\n\n");
                    }


                }
            }

        });

        thread.start();
        return thread;
    }


    public static void main(String[] args) throws Exception {
        a_1_With_Concurrency();
    }

    private static void a_1_With_Concurrency() throws Exception {
        Account acc1 = new Account(1, 1100);
        Account acc2 = new Account(2, 900);

        Thread transfer = transfer(acc1, acc2, 100);
        Thread transfer2 = transfer(acc2, acc1, 100);

        transfer.join();
        transfer2.join();

        System.out.println(acc1);
        System.out.println(acc2);
    }

    public static void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
