package ru.epam.javacore.lesson_16_concurrency.a_4_account;

public class A_1_AccountDemo_Problem {

    private static Thread transfer(Account src, Account dest, int money) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                src.setAmount(src.getAmount() - money);
                sleep(30);
                dest.setAmount(dest.getAmount() + money);
            }
        });

        thread.start();
        return thread;
    }


    public static void main(String[] args) throws Exception {
        a_1_With_Concurrency();
    }

    private static void a_1_No_Concurrency() throws Exception {
        Account acc1 = new Account(1, 1100);
        Account acc2 = new Account(2, 900);

        Thread transfer = transfer(acc1, acc2, 100);
        transfer.join();

        System.out.println(acc1);
        System.out.println(acc2);
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
