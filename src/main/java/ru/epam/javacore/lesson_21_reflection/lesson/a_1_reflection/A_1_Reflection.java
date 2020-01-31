package ru.epam.javacore.lesson_21_reflection.lesson.a_1_reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;

public class A_1_Reflection {

    public static void main(String[] args) throws Exception {
        Animal animal = new Animal("Tiger", 333);
        // a_1_demoFields(Animal.class.getFields());
        //  a_2_demoMethods(Animal.class.getMethods());
       // a_3_demoAccessToPrivateField(new Animal("Ivan", 33));

        protoType(new Animal("SS", 11));
    }

    private static void a_1_demoFields(Field[] fields) {
        for (Field field : fields) {
            System.out.println("---------");
            System.out.println("Name " + field.getName());
            System.out.println("Modifier " + Modifier.toString(field.getModifiers()));
            System.out.println("Type " + field.getType());
        }
    }

    private static void a_2_demoMethods(Method[] methods) {
        for (Method method : methods) {
            System.out.println("Name " + method.getName());
            System.out.println("Modifier " + Modifier.toString(method.getModifiers()));
            System.out.println("Params count " + method.getParameterCount());

            for (Parameter parameter : method.getParameters()) {
                System.out.println("Name " + parameter.getName() +
                                           " Type " + parameter.getType());
            }

            System.out.println("Return " + method.getReturnType());
            System.out.println("--------------");
        }
    }

    private static void a_3_demoAccessToPrivateField(Animal animal) throws IllegalAccessException {
        try {
            Field fldName = animal.getClass().getDeclaredField("name");
            fldName.setAccessible(true);
            fldName.set(animal, "Petr");

            System.out.println(animal);

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }

    private static <T> T protoType(T src) throws Exception {
        Class<?>[] interfaces = src.getClass().getInterfaces();

        for (Class<?> anInterface : interfaces) {
            if (anInterface.equals(Cloneable.class)) {
                Method method = src.getClass().getDeclaredMethod("clone");
                if (method.getModifiers() == Modifier.PUBLIC) {
                    return (T) method.invoke(src);
                }
            }
        }

        throw new CloneNotSupportedException();
    }

}


