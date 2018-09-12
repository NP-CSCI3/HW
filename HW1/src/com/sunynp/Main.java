package com.sunynp;

import java.io.*;
import java.util.*;

public class Main {
    public Main() {
    }

    // create a double stack
    public static class Stack {
        Double[] array;
        int index;

        public Stack() {
            this.array = new Double[128];
            this.index = 0;
        }

        public boolean empty() {
            return index == 0;
        }

        public void push(Double item) {
            array[index] = item;
            index++;
        }

        public Double pop() {
            index--;
            return array[index];
        }

    }

    // create a print main method
    public static void main(String[] args) throws FileNotFoundException {
        Stack postfixStack = new Stack();

// create a dat file scanner

        //String path = new File("HW1/src/com/sunynp/assign1-in.dat").getAbsolutePath();
        //System.out.println(path);
        Scanner datscan = new Scanner(new File("assign1-in.dat"));
        double[] input = new double[100];
        int i = 0;

        while (datscan.hasNextLine()) {
            String s = datscan.nextLine();
            System.out.print("The value of \"" + s + "\" is ");
            String op = "";


            String[] parts = s.split(" ");
            for (String next : parts) {

                Double a;
                Double b;
                switch (next) {
                    case "+":
                        a = postfixStack.pop();
                        b = postfixStack.pop();
                        // System.out.println(a + b);
                        postfixStack.push(a + b);

                        break;
                    case "-":
                        b = postfixStack.pop();
                        a = postfixStack.pop();
                        // System.out.println(a - b);
                        postfixStack.push(a - b);
                        break;
                    case "*":
                        a = postfixStack.pop();
                        b = postfixStack.pop();
                        //System.out.println(a * b);
                        postfixStack.push(a * b);
                        break;
                    case "/":
                        b = postfixStack.pop();
                        a = postfixStack.pop();
                        // System.out.println(a / b);
                        postfixStack.push(a / b);
                        break;
                    case "_":
                        a = postfixStack.pop();
                        // System.out.println(0 - a);
                        postfixStack.push(0 - a);
                        break;
                    case "#":
                        a = postfixStack.pop();

                        // System.out.println(Math.sqrt(a));
                        postfixStack.push(Math.sqrt(a));
                        break;
                    case "^":
                        b = postfixStack.pop();
                        a = postfixStack.pop();
                        // System.out.println(Math.pow(a, b));
                        postfixStack.push(Math.pow(a, b));
                        break;
                    case " ":
                        //System.out.println("SPACE");
                        break;
                    default:


                        postfixStack.push(Double.valueOf(next));

                }
            }
            System.out.println(postfixStack.pop());
            System.out.println();
        }

        System.out.println("Bye-bye!");
        datscan.close();
    }
}
