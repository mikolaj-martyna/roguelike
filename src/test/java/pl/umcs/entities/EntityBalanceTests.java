package pl.umcs.entities;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import pl.umcs.entities.monsters.*;

class EntityBalanceTests {
    @Test
    public void PlayerVsInevitableBalance_PlayerFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Inevitable inevitable = new Inevitable();

            while (player.isAlive() && inevitable.isAlive()) {
                player.attack(inevitable);

                if (!inevitable.isAlive()) {
                    wonByPlayer++;
                    break;
                }

                inevitable.attack(player);
            }

            if (!player.isAlive()) wonByMonster++;
        }

        System.out.printf(
                "[Player first]\nWon by player: %d\nWon by inevitable: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsInevitableBalance_MonsterFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Inevitable inevitable = new Inevitable();

            while (player.isAlive() && inevitable.isAlive()) {
                inevitable.attack(player);

                if (!player.isAlive()) {
                    wonByMonster++;
                    break;
                }

                player.attack(inevitable);
            }

            if (!inevitable.isAlive()) wonByPlayer++;
        }

        System.out.printf(
                "[Inevitable first]\nWon by player: %d\nWon by inevitable: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsModronBalance_PlayerFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Modron modron = new Modron();

            while (player.isAlive() && modron.isAlive()) {
                player.attack(modron);

                if (!modron.isAlive()) {
                    wonByPlayer++;
                    break;
                }

                modron.attack(player);
            }

            if (!player.isAlive()) wonByMonster++;
        }

        System.out.printf(
                "[Player first]\nWon by player: %d\nWon by modron: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsModronBalance_MonsterFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Modron modron = new Modron();

            while (player.isAlive() && modron.isAlive()) {
                modron.attack(player);

                if (!player.isAlive()) {
                    wonByMonster++;
                    break;
                }

                player.attack(modron);
            }

            if (!modron.isAlive()) wonByPlayer++;
        }

        System.out.printf(
                "[Modron first]\nWon by player: %d\nWon by modron: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsThoughEaterBalance_PlayerFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            ThoughtEater thoughtEater = new ThoughtEater();

            while (player.isAlive() && thoughtEater.isAlive()) {
                player.attack(thoughtEater);

                if (!thoughtEater.isAlive()) {
                    wonByPlayer++;
                    break;
                }

                thoughtEater.attack(player);
            }

            if (!player.isAlive()) wonByMonster++;
        }

        System.out.printf(
                "[Player first]\nWon by player: %d\nWon by thought eater: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsThoughEaterBalance_MonsterFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            ThoughtEater thoughtEater = new ThoughtEater();

            while (player.isAlive() && thoughtEater.isAlive()) {
                thoughtEater.attack(player);

                if (!player.isAlive()) {
                    wonByMonster++;
                    break;
                }

                player.attack(thoughtEater);
            }

            if (!thoughtEater.isAlive()) wonByPlayer++;
        }

        System.out.printf(
                "[Thought Eater first]\nWon by player: %d\nWon by thought eater: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsClockworkDragonBalance_PlayerFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            ClockworkDragon clockworkDragon = new ClockworkDragon();

            while (player.isAlive() && clockworkDragon.isAlive()) {
                player.attack(clockworkDragon);

                if (!clockworkDragon.isAlive()) {
                    wonByPlayer++;
                    break;
                }

                clockworkDragon.attack(player);
            }

            if (!player.isAlive()) wonByMonster++;
        }

        System.out.printf(
                "[Player first]\nWon by player: %d\nWon by clockwork dragon: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsClockworkDragon_MonsterFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            ClockworkDragon clockworkDragon = new ClockworkDragon();

            while (player.isAlive() && clockworkDragon.isAlive()) {
                clockworkDragon.attack(player);

                if (!player.isAlive()) {
                    wonByMonster++;
                    break;
                }

                player.attack(clockworkDragon);
            }

            if (!clockworkDragon.isAlive()) wonByPlayer++;
        }

        System.out.printf(
                "[Clockwork Dragon first]\nWon by player: %d\nWon by clockwork dragon: %d\n\n",
                wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsMyrBalance_PlayerFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Myr myr = new Myr();

            while (player.isAlive() && myr.isAlive()) {
                player.attack(myr);

                if (!myr.isAlive()) {
                    wonByPlayer++;
                    break;
                }

                myr.attack(player);
            }

            if (!player.isAlive()) wonByMonster++;
        }

        System.out.printf(
                "[Player first]\nWon by player: %d\nWon by myr: %d\n\n", wonByPlayer, wonByMonster);
    }

    @Test
    public void PlayerVsMyr_MonsterFirst() {
        int wonByPlayer = 0;
        int wonByMonster = 0;

        for (int i = 0; i < 1000; i++) {
            Player player = new Player();
            Myr myr = new Myr();

            while (player.isAlive() && myr.isAlive()) {
                myr.attack(player);

                if (!player.isAlive()) {
                    wonByMonster++;
                    break;
                }

                player.attack(myr);
            }

            if (!myr.isAlive()) wonByPlayer++;
        }

        System.out.printf(
                "[Myr first]\nWon by player: %d\nWon by myr: %d\n\n", wonByPlayer, wonByMonster);
    }
}
