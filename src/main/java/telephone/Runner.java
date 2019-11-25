package telephone;

import telephone.button.ButtonPower;
import telephone.device.Telephone;

import java.util.InputMismatchException;
import java.util.Scanner;

import lombok.extern.log4j.Log4j;

@Log4j
public class Runner {
    public static void main(String[] args) {
        ButtonPower buttonPower = new ButtonPower();
        Telephone telephone = new Telephone(buttonPower);

        Scanner in = new Scanner(System.in);
        System.out.println("Телефон выключен ");
        int t = -1;
        do {
            System.out.println("Выберите действие: " +
                    "1-включить/выключить телефон(удержать кнопку Power);  " +
                    "0-закончить работу с программой");
            try {
                t = in.nextInt();
            } catch (InputMismatchException e) {
                log.error("введено не целое число!");
                in.next();
                t = -1;
            }
            switch (t) {
                case 1:
                    buttonPower.buttonPowerDown(telephone);
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Введено неверное число");
                    break;
            }
        } while (t != 0);
        System.out.println("Работа программы закончена");
        in.close();

    }
}
