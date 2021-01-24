package ru.bruh_industries.haliaven.module3.n3_1;

import java.util.Random;
import java.util.Scanner;


public class Game {
    public static Character player;
    public static Story story;

    public static void main(String[] args) {
        Random r = new Random();
        Scanner scanner = new Scanner(System.in);
        Mood[] mood_list = new Mood[] {new Mood("Обычный", 0, 0, 0),
                                        new Mood("Грустный", -5, -10, -10),
                                        new Mood("Гиперактивный", 0, 20, -10),
                                        new Mood("Бодрый", 10, 10, 5),
                                        new Mood("Усталый", -10, -10, -10),
                                        new Mood("Рисковый", r.nextInt(50), r.nextInt(50), r.nextInt(50))};
        player = new Character(mood_list);
        story = new Story(mood_list);
        while (true) {
            player.mood = story.current_situation.dMood;
            player.completion += story.current_situation.dCompletion;
            player.energy += story.current_situation.dEnergy;
            player.time += story.current_situation.dTime;
            System.out.println("СЕГОДНЯ ВЫ: " + player.mood.name);
            System.out.println("ЭНЕРГИЯ: " + player.energy);
            System.out.println("ВРЕМЕНИ ДО СДАЧИ: " + player.time);
            System.out.println("ВЫ СДЕЛАЛИ ЗАДАНИЕ НА " + player.completion + "/100");
            System.out.println("========" + story.current_situation.title + "========");
            System.out.println(story.current_situation.description);
            System.out.println("=================================");
            if (story.isEnded()) {
                System.out.println("В БУДУЩЕМ ВЫ: " + player.mood.name);
                System.out.println("ЭНЕРГИЯ В БУДУЩЕМ: " + player.energy);
                System.out.println("СВОБОДНОГО ВРЕМЕНИ В БУДУЩЕМ: " + player.time);
                System.out.println("ПРОДУКТИВНОСТЬ В БУДУЩЕМ " + player.completion + "/100");
                System.out.println("===============КОНЕЦ===============");
                return;
            }
            story.current_situation = story.current_situation.direction[scanner.nextInt() - 1];
        }
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
        start_situation = new Situation("Задание про квест",
                "После одного из занятий в Школе Самсунг " +
                "вам рассказали про задание, которое заключается в том, что вам надо соорудить текстовый квест на любую тему " +
                "сроком до следующего понедельника. Вы же решили в тот вечер:\n" +
                        "(1) не делать ничего особенного - расслабиться и пойти спать: утро вечера мудренее\n " +
                        "(2) что раз задали такое объёмное с виду задание, надо бы уже начать его делать\n" +
                        "(3) подумать над темой для другого проекта, который более важен, чем это задание",
                3, 0, 0, 0, mood_list[new Random().nextInt(mood_list.length - 1)]);
        start_situation.direction[0] = new Situation("Следующий день","Ну задали и задали," +
                " че бубнить-то. Поспали вы на славу, но знания с прошлого урока вы не закрепили,\n " +
                "поэтому будет сложнее сделать тот самый квест. Наилучим решением для себя в тот момент вы посчитали:\n" +
                "(1) прочесть материал учебника\n" +
                "(2) пойти поспать, ведь момент уже упущен\n",
                2, -10, -50, 20, mood_list[3]);
        start_situation.direction[0].direction[0] = new Situation("Судный день", "Вы уже не уверены в " +
                "существовании концепции времени\n, может вы читали учебник день, два, три, а может это учебник читал вас?\n" +
                "Все цифры перемешивались у вас на глазах,\n поэтому дату сдачи вы не знаете, остаётся только гадать," +
                " успели вы или нет...", 0, new Random().nextInt(100), -50, -100, mood_list[1]);
        start_situation.direction[0].direction[1] = new Situation("Некоторое время спустя", "Да, не секрет," +
                " что сон - это самое простое и приятное решение,\n но злоупотребление столь великой силы привело вас к тому," +
                "что на этот раз вы заснули вечным сном...", 0, -100, -100, -100, mood_list[mood_list.length - 2]);
        start_situation.direction[1] = new Situation("Следующий день", "Предыдущим вечером вы немного " +
                "увлеклись и сидели довольно долго за этим заданием,\n что с одной стороны продвинуло вас довольно хорошо" +
                "в решении этой задачи, но с другой стороны, вы недостаточно спали и проснулись уставшим, что же дальше?\n" +
                "(1) Ну как что, надо закончить начатое!\n" +
                "(2) Да вот что-то раз устал, надо бы и поспать там, поиргать...\n",
                2, 0, -50, 0, mood_list[4]);
        start_situation.direction[1].direction[0] = new Situation("Спустя 10 лет", "С подобной " +
                "решительностью, молодой человек, вы добились многого,\n начиная от отлично сделанной домашней работы" +
                "и заканчивая успешным основанием\n и управлением невероятно успешной IT-компании. Неплохо!", 0,
                100, 100, 100, mood_list[3]);
        start_situation.direction[1].direction[1] = new Situation("Когда-то в будущем", "Кто же знал, " +
                "что технологии помогут показать подобные изъяны человеческого мозга,\n что мозг привык идти по пути наименьшего сопротивления?\n" +
                "Что же ещё наш мозг скрывает он нас?\n Вы задались подобными вопросами, что позволило вам " +
                "в будущем выйграть нобелевскую премию по биологии", 0, 200, -40, -50, mood_list[mood_list.length - 2]);
        start_situation.direction[2] = new Situation("Следующий день", "Надумались вы вдоволь, " +
                "да вот только ничего складного в голову не пришло,\n поэтому понимание того, что вы просто так потеряли" +
                " целый вечер, который могли посвятить решению этой задачи, сильно вас огорчило, но\n" +
                "(1) отпускать идею с более большим проектом нельзя, надо его додумать, осталось ещё чуть-чуть!\n" +
                "(2) огорчило или нет - квест сам себя не сделает, надо уж приступить к нему",
                2, 0, -50, -10, mood_list[mood_list.length - 1]);
        start_situation.direction[2].direction[0] = new Situation("Пару лет спустя", "Да, может то " +
                "решение не делать домашку один раз и повлияло на общую оценку,\n однако в качестве альтернативы вы " +
                "получили нечто большее - идею,\n да не какую попало, а такую, что инвесторы соревнуются друг с другом," +
                "лишь бы вы согласились с ними сотрудничать", 0, 150, 50, 100, mood_list[mood_list.length - 1]);
        start_situation.direction[2].direction[1] = new Situation("В тот же день", "Вы попытались сделать то,\n" +
                "что пообещали, однако вы ежеминутно отвлекались либо на сообщение,\n отправленное вам вашими знакомцами, либо" +
                "впадали в раздумье о том, что же задали на завтра или просто отвлекались ни на что.\n В итоге домашка была провалена" +
                "на обоих фронтах: общеобразовательно-школьном и it-школьном (если так можно выразиться),\n но вы поняли свою" +
                "ошибку, что значит, что вы на полпути к успеху!", 0, 40, -50, 80, mood_list[3]);

        current_situation = start_situation;
    }

    public void go(int num) {
        if (!isEnded() && num <= current_situation.direction.length && num >= 0)
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
