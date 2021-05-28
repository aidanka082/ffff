package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 900;
    public static int bossDamage = 50;
    public static String bossDefenceType = "";
    public static int[] heroesHealth = {150,70,80,140,200,100};
    public static int[] heroesDamage = {15, 20,25,0,13,0,};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic","Medic","Golem"};



    public static void changeBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length);
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss choose : " + bossDefenceType);
    }

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();

        }

    }



    public static void round() {
        changeBossDefence();
        if (bossHealth > 0) {
            bossHits();
        }
        MedickHels();
        GolemHels();
        Lucky();
        heroesHit();
        printStatistics();
    }




    public static void printStatistics() {
        System.out.println("_____________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesAttackType.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("_____________");
    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesAttackType[i] == bossDefenceType) {
                    Random random = new Random();
                    int coeff = random.nextInt(10) + 2;
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        boolean allHeroesDead = true;
        for (int j : heroesHealth) {

            if (j > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void MedickHels(){
        int index = 0;
        for (String heal : heroesAttackType) {
            if (heal == "Medic"){
                if (heroesHealth[index] > 0){
                    for (int i = 0; i < heroesHealth.length; i++) {
                        if (heroesHealth[i] < 100 && heroesHealth[i] > 0){

                            Random random = new Random();
                            int healMedic = random.nextInt(70);
                            heroesHealth[i] = heroesHealth[i] + healMedic;
                            System.out.println("Медик спас" + heroesAttackType[i] + " " + healMedic);
                            System.out.println( heroesHealth[i]);
                            break;


                        }
                        
                    }
                }
            }

            index++;
        }

    }


   public static void GolemHels(){
        int a = bossDamage / 5;
        int index1 = 0;
        for (String heal : heroesAttackType) {
             if (heal =="Golem"){
                 if (heroesHealth[index1] > 0) {
                     for (int i = 0; i < heroesHealth.length; i++) {
                         heroesHealth[i] += a;
                         heroesHealth[index1] -= a;


                     }
                 }
           }
           index1++;
       }

    }



     public static void Lucky(){
        Random random = new Random();
        boolean las = random.nextBoolean();
        if (las){
            for (String luc: heroesAttackType){
               if (luc == "Lucky");
            }
            }
         System.out.println("Lusky dodged");
    }
}

