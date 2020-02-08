package ru.epam.javacore.lesson_21_reflection.lesson.a_1_reflection;

class Animal implements Cloneable {
    public static final String PLANET = "Earth";
    private final String name;
    public int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    //@PrintToCon("ssss")
    private void feed(String food) {

    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return this;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
