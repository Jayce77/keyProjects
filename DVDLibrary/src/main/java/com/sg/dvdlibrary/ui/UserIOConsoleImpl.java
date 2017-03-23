/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.dvdlibrary.ui;

import java.util.Scanner;

/**
 *
 * @author jayce
 */
public class UserIOConsoleImpl implements UserIO{
        private String strAnswer;
    private int validInt, intMin, intMax;
    private float validFloat, floatMin, floatMax;
    private long validLong, longMin, longMax;
    private double validDouble, doubleMin, doubleMax;
    private boolean isValid, inBounds;
    Scanner sc;
    
    public UserIOConsoleImpl(){
        isValid = false;
        inBounds = false;
        sc = new Scanner(System.in);
    }

    @Override
    public void print(String message) {
        System.out.println(message);
    }
    
    @Override
    public int readInt(String prompt) {
        while(!isValid){
           print(prompt);

            if (sc.hasNextInt())
            {
                isValid = true;
                
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                
                validInt = Integer.parseInt(strAnswer);
            } else {
                
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
            
        }
        isValid = false;
        return validInt;
    }

    @Override
    public int readInt(String prompt, int min, int max) {
        while(!isValid  || !inBounds){
           print(prompt);
            if (sc.hasNextInt()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validInt = Integer.parseInt(strAnswer);
            } else {
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
             if (validInt < min || validInt > max) {
                System.out.println("Can't you count??\nTry that again!");
                inBounds = false; 
            } else {
                inBounds = true;
            }
            
        }
        isValid = false;
        inBounds = false;
        return validInt;
    }

    @Override
    public double readDouble(String prompt) {
         while(!isValid){
           print(prompt);
            if (sc.hasNextDouble()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validDouble = Double.parseDouble(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
        }
        isValid = false;
        return validDouble;
    }

    @Override
    public double readDouble(String prompt, double min, double max) {
        while(!isValid  || !inBounds){
           print(prompt);
            if (sc.hasNextDouble()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validDouble = Double.parseDouble(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
             if (validDouble < min || validDouble > max) {
                System.out.println("Can't you count??\nTry that again!");
                inBounds = false; 
            } else {
                inBounds = true;
            }
            
        }
        isValid = false;
        inBounds = false;
        return validDouble;
    }

    @Override
    public float readFloat(String prompt) {
        while(!isValid){
           print(prompt);
            if (sc.hasNextFloat()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validFloat = Float.parseFloat(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
        }
        isValid = false;
        return validFloat;
    }

    @Override
    public float readFloat(String prompt, float min, float max) {
        while(!isValid  || !inBounds){
           print(prompt);
            if (sc.hasNextFloat()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validFloat = Float.parseFloat(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
             if (validFloat < min || validFloat > max) {
                System.out.println("Can't you count??\nTry that again!");
                inBounds = false; 
            } else {
                inBounds = true;
            }
            
        }
        isValid = false;
        inBounds = false;
        return validFloat;
    }

   @Override
    public long readLong(String prompt) {
        while(!isValid){
           print(prompt);
            if (sc.hasNextLong()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validLong = Long.parseLong(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
        }
        isValid = false;
        return validLong;
    }

    @Override
    public long readLong(String prompt, long min, long max) {
        while(!isValid  || !inBounds){
           print(prompt);
            if (sc.hasNextLong()  == true) {
                isValid = true;
                do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("[\t+,+]","");
                } while ("".equals(strAnswer));
                validLong = Long.parseLong(strAnswer);
            } else {
               do {
                    strAnswer = sc.nextLine();
                    strAnswer = strAnswer.replaceAll("\t+","");
                } while ("".equals(strAnswer));
               System.out.println("What are you doing!?");
               isValid = false;
               continue;
            }
             if (validLong < min || validLong > max) {
                System.out.println("Can't you count??\nTry that again!");
                inBounds = false; 
            } else {
                inBounds = true;
            }
            
        }
        isValid = false;
        inBounds = false;
        return validLong;
    }

    @Override
    public String readString(String prompt) {
        print(prompt);
        return sc.nextLine();
    }
}