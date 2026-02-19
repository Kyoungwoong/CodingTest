package Daou.groom.test4;

public class Q7 {

    static class Unit {
        int HP = 1000;

        void under_attack(int damage) {
        }
    }

    static class Monster extends Unit {
        int attack_point;

        Monster(int attack_point) {
            super();
            this.attack_point = attack_point;
        }

        void under_attack(int damage) {
            this.HP -= damage;
        }

        int attack() {
            return this.attack_point;
        }
    }

    static class Warrior extends Unit {
        int attack_point;

        Warrior(int attack_point) {
            super();
            this.attack_point = attack_point;
        }

        void under_attack(int damage) {
            this.HP -= damage;
        }

        int attack() {
            return this.attack_point;
        }
    }

    static class Healer extends Unit {
        int healing_point;

        Healer(int healing_point) {
            super();
            this.healing_point = healing_point;
        }

        void under_attack(int damage) {
            this.HP -= damage;
        }

        void healing(Unit unit) {
            unit.HP += this.healing_point;
        }
    }

    public static int[] solution(int monster_attack_point, int warrior_attack_point, int healing_point) {
        Monster monster = new Monster(monster_attack_point);
        Warrior warrior = new Warrior(warrior_attack_point);
        Healer healer = new Healer(healing_point);

        monster.under_attack(warrior.attack());
        warrior.under_attack(monster.attack());
        healer.under_attack(monster.attack());
        healer.healing(warrior);
        healer.healing(monster);

        int[] answer = {monster.HP, warrior.HP, healer.HP};
        return answer;
    }

    public static void main(String[] args) {
        int monster_attack_point = 100;
        int warrior_attack_point = 90;
        int healing_point = 30;

        int[] ret = solution(monster_attack_point, warrior_attack_point, healing_point);

        System.out.println("solution 함수의 반환 값은 [" +
                ret[0] + ", " + ret[1] + ", " + ret[2] + "] 입니다.");
    }
}
