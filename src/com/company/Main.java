package com.company;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        MyCollection<Integer> myCollection = new MyCollection<>();
        myCollection.add(1);
        myCollection.add(3);

        myCollection.add(3);
        myCollection.add(3);
        myCollection.add(2);
        Iterator<Integer> it = myCollection.iterator();

        for (Integer i : myCollection
        ) {
            System.out.print(" "+i);
        }
        it.remove();
        it.next();
        it.remove();
        it.next();
        it.next();
        //it.remove();



        System.out.println();
        Integer[] arr={1};
        //Integer[] result=myCollection.toArray(arr);
        arr=myCollection.toArray(arr);

        ArrayList<Integer> list=new ArrayList<>();


        list.add(1);
        list.add(2);
        list.add(3);




        System.out.println(myCollection.retainAll(list));






        for (Integer i : myCollection
        ) {
            System.out.print(" "+i);
        }







    }
}
