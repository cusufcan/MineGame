import java.util.Random;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        Scanner scn = new Scanner(System.in);
        Random rnd = new Random();

        System.out.print("Satır: ");
        int r = scn.nextInt();
        System.out.print("Sütun: ");
        int c = scn.nextInt();

        int[][] mineField = new int[r][c];

        System.out.print("Zorluk (20% - 80%): ");
        int difficulty = scn.nextInt();

        if (difficulty < 20)
        {
            difficulty = 20;
            System.out.println("20'den küçük değer girdiniz. Zorluk seviyesi alt limit 20'ye eşitlendi.");
        }
        else if (difficulty > 80)
        {
            difficulty = 80;
            System.out.println("80'den büyük değer girdiniz. Zorluk seviyesi üst limit 80'e eşitlendi.");
        }

        int rndCounter = difficulty * r * c / 100;
        if (rndCounter == 0)
        {
            rndCounter = 1;
        }

        int mineLeft = rndCounter; // clearLeft için tanımladım

        while(rndCounter != 0)
        {
            int rndNumX = rnd.nextInt(0, r);
            int rndNumY = rnd.nextInt(0, c);

            if (mineField[rndNumX][rndNumY] != 1)
            {
                mineField[rndNumX][rndNumY] = 1;
                rndCounter--;
            }
        }

        System.out.println("\nOYUN HAZIRLANDI\n");

        int[][] gMineField = new int[r][c];

        int score = 0;

        int tickLeft = r * c;
        int clearLeft = tickLeft - mineLeft;

        int gR;
        int gC;

        boolean isGameOver = false;

        while(!isGameOver && clearLeft != 0)
        {
            for(gR = 0; gR < r; ++gR)
            {
                for(gC = 0; gC < c; ++gC)
                {
                    switch (gMineField[gR][gC])
                    {
                        case -1:
                            System.out.print("0   ");
                            break;
                        case 0:
                            System.out.print("*   ");
                            break;
                        case 1:
                            System.out.print(gMineField[gR][gC] + "   ");
                            break;
                        default:
                            break;
                    }
                }

                System.out.println();
            }

            System.out.print("Tahmin satır: ");
            gR = scn.nextInt();
            System.out.print("Tahmin sütun: ");
            gC = scn.nextInt();

            if (gMineField[gR - 1][gC - 1] != 1 && gMineField[gR - 1][gC - 1] != -1)
            {
                if (mineField[gR - 1][gC - 1] == 1)
                {
                    gMineField[gR - 1][gC - 1] = 1;
                    System.out.println("Mayına bastın...\n");
                    mineLeft--;
                    isGameOver = true;
                }
                else
                {
                    gMineField[gR - 1][gC - 1] = -1;
                    System.out.println("Temiz...\n");
                    clearLeft--;
                    score += 5;
                }

                tickLeft--;
            }
            else
            {
                System.out.println("Zaten bu satırı denemiştin...\n");
            }
        }

        System.out.println("Oyun bitti...");
        System.out.println("Skor: " + score + "\n");

        for(gR = 0; gR < r; ++gR)
        {
            for(gC = 0; gC < c; ++gC)
            {
                System.out.print(mineField[gR][gC] + "   ");
            }
            System.out.println();
        }
    }
}