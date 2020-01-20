package ru.epam.javacore.lesson_16_concurrency.a_4_account;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class A_3_AccountDemo_Probleb_DeadLock_Solution_2 {
    private static Boolean ff = true;
    private static AtomicInteger index = new AtomicInteger(0);

    //acc_1
    //acc_2

    //acc_2
    //acc_1
    private static Thread transfer2(Account src, Account dest, int money) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                synchronized (src){

                }

                synchronized (dest){

                }
            }
        });

        thread.start();
        return thread;
    }

    private static Thread transfer(Account src, Account dest, int money) {
        List<Account> accountList = new ArrayList<>();
        accountList.add(src);
        accountList.add(dest);

        AtomicInteger atomic = new AtomicInteger(0);
        accountList.sort(new Comparator<Account>() {
            @Override
            public int compare(Account o1, Account o2) {
                int k = o2.getId() - o1.getId();
                if (k != 0) {
                    atomic.incrementAndGet();
                }
                return k;
            }
        });
        System.out.println(accountList);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                if (atomic.get() != 0) {
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
                } else {
                    synchronized (ff) {
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
        Account acc3 = new Account(1, 1000);

        Thread transfer = transfer(acc1, acc3, 100);
        Thread transfer2 = transfer(acc3, acc1, 100);


        transfer.join();
        transfer2.join();

        sleep(4000);
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
