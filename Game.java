package ru.bruh_industries.haliaven.module3.n3_1;

import java.util.Random;


public class Game {
    public static void main(String[] args) {
        Random r = new Random();
        Mood[] mood_list = new Mood[] {new Mood("Обычный", 0, 0, 0),
                                        new Mood("Грустный", -5, -10, -10),
                                        new Mood("Гиперактивный", 0, 20, -10),
                                        new Mood("Бодрый", 10, 10, 5),
                                        new Mood("Рисковый", r.nextInt(50), r.nextInt(50), r.nextInt(50))};
    }
}

class Character {
    int time, energy, completion;
    Mood mood;
    public Character(Mood[] mood_list) {
        this.time = 100;
        this.energy = 100;
        this.completion = 0;
        this.mood = mood_list[new Random().nextInt(mood_list.length)];
    }
}

class Mood {
    String name;
    int modif_time, modif_energy, modif_completeion;

    public Mood(String name, int mTime, int mEnergy, int mComp) {
        this.name = name;
        modif_time = mTime;
        modif_energy = mEnergy;
        modif_completeion = mComp;
    }
}

class Story {
    public Situation current_situation;
    private Situation start_situation;

    Story (Mood[] mood_list) {
        start_situation = new Situation("Задание было рассказано",
                "После одного из занятий в Школе Самсунг " +
                "вам рассказали про задание, которое заключается в том, что вас надо соорудить текстовый квест на любую тему " +
                "сроком до следующего полнедельника. Вы же решили в тот вечер:" +
                        "(1) не делать ничего особенного - расслабиться и пойти спать: утро вечере мудренее\n " +
                        "(2) что раз задали такое объёмное с виду задание, надо бы уже начать его делать\n" +
                        "(3) подумать над темой для другого проекта, который более важен, чем это задание",
                3, 0, 0, 0, mood_list[0]);
        start_situation.direction[0] = new Situation("", "",
                3, 0, 0, 0, mood_list[0]);
        start_situation.direction[0] = new Situation("", "",
                3, 0, 0, 0, mood_list[0]);
        start_situation.direction[0] = new Situation("", "",
                3, 0, 0, 0, mood_list[0]);

        current_situation = start_situation;
    }

    public void go(int num) {
        if (!isEnded() && num <= current_situation.direction.length)
            current_situation = current_situation.direction[num - 1];
        else System.out.println("Неверные вводные данные");
    }

    public boolean isEnded() { return current_situation.direction.length == 0; }
}

class Situation {
    Situation[] direction;
    String title, description;
    int dCompletion, dTime, dEnergy;
    Mood dMood;

    public Situation(String title, String description, int amount, int dCompletion, int dTime, int dEnergy, Mood dMood) {
        direction = new Situation[amount];
        this.title = title;
        this.description = description;
        this.dMood = dMood;
        this.dCompletion = dCompletion + dMood.modif_completeion;
        this.dTime = dTime + dMood.modif_time;
        this.dEnergy = dEnergy + dMood.modif_energy;
    }
}
