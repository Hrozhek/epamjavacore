package ru.epam.javacore.lesson_11_generics_and_io.lesson.a_4_generics_restrictions;

import java.io.IOException;

public class Restrictions
        //<T>
        extends Exception {

    public static<T extends Throwable> void work(){
        try{
          if (true){
              throw new IOException();
          }
        }
        /*
        catch (T t){

        }*/
        catch (IOException e){
        }
        catch (Exception e){
        }
    }

    private static<TYPE> void demo(){
       // TYPE[] t = new TYPE[2];
        //new TYPE();
    }
}
