package ru.epam.javacore.lesson_15.lesson.a_immutable.c_immutable;

import java.util.Date;

public class Person {
    private final String name;
    private final Date dateOfBirth;
    private final Passport passport;

    public Person(String name, Date dateOfBirth, Passport passport) {
        this.name = name;
        this.dateOfBirth = new Date(dateOfBirth.getTime());
        this.passport = copyOfPassport(passport);
    }

    private Passport copyOfPassport(Passport passport) {
        return new Passport(passport.getSeria(), passport.getNumber());
    }

    public String getName() {
        return name;
    }

    public Date getDateOfBirth() {
        return new Date(dateOfBirth.getTime());
    }

    public Passport getPassport() {
        return copyOfPassport(this.passport);
    }

    public static void main(String[] args) {
        Passport passport = new Passport(11, 22);

        Person person = new Person("Ivan", new Date(), passport);
        System.out.println(person);

        person.getPassport().setSeria(4444);
        System.out.println(person);
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", passport=" + passport +
                '}';
    }
}
