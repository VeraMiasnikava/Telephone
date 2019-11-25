package telephone.device;

import telephone.application.ApplicationBank;
import telephone.application.Application;
import telephone.button.ButtonDecrementSound;
import telephone.button.ButtonIncrementSound;
import telephone.button.ButtonPhoneCall;
import telephone.button.ButtonPower;
import lombok.Getter;
import lombok.extern.log4j.Log4j;

import java.util.InputMismatchException;
import java.util.Scanner;

@Log4j
public class Telephone extends Device {
    public static final int MAX_VOLUME = 10;
    private static final int DEFAULT_VOLUME = 5;
    @Getter
    private int volume = DEFAULT_VOLUME;
    private ButtonPower buttonPower;
    private ButtonIncrementSound buttonIncrementSound = new ButtonIncrementSound();
    private ButtonDecrementSound buttonDecrementSound = new ButtonDecrementSound();
    private ButtonPhoneCall buttonPhoneCall = new ButtonPhoneCall();
    @Getter
    private Screen screen = new Screen();
    private ApplicationBank applicationBank = new ApplicationBank(screen);
    private Application a1 = new Application("П1 ");
    private Application a2 = new Application("П2 ");
    private Application a3 = new Application("П3 ");

    public void incrementVolume() {
        volume++;
    }

    public void decrementVolume() {
        volume--;
    }

     public Telephone(ButtonPower buttonPower) {
        super();
        this.buttonPower = buttonPower;
        applicationBank.installApplication(a1, false);
        applicationBank.installApplication(a2, true);
        screen.launchApplication(a1);
    }

    public void onTelephone() {
        onDevice();
        System.out.println("Телефон включен, экран выключен");
        int t = -1;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println(String.format("Сделайте выбор: %n" +
                    "1-включить/выключить экран (нажать кнопку Power) %n" +
                    "2- увеличть звук (нажать кнопку ButtonIncrementSound)%n" +
                    "3- уменьшить звук (нажать кнопку ButtonDencrementSound)%n" +
                    "0-выключить телефон (удержать кнопку Power) %n"));
            try {
                t = in.nextInt();
                switch (t) {
                    case 1:
                        buttonPower.buttonPowerPress(this);
                        if (!this.isWorking()) {
                            return;
                        }
                        break;
                    case 2:
                        buttonIncrementSound.incrementVolume(this);
                        break;
                    case 3:
                        buttonDecrementSound.decrementVolume(this);
                        break;
                    case 0:
                        buttonPower.buttonPowerDown(this);
                        return;
                    default:
                        System.out.println("Введено неверное число");
                        break;
                }
            } catch (InputMismatchException e) {
                log.error("введено не целое число!");
                in.next();
            }
        } while (true);

    }

     public void onScreen() {
        screen.onDevice();
        System.out.println("Экран включен");
        System.out.println(this.toString());
        int t ;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println(String.format("Сделайте выбор: %n" +
                    "1-работа с приложениями %n" +
                    "2-сделать звонок другу( нажать кнопку вызов) %n" +
                    "0-выключить телефон %n" +
                    "10-выключить экран(нажать кнопку Power) %n"));

            try {
                t = in.nextInt();
                switch (t) {
                    case 1:
                        this.workWithApplications();
                        break;
                    case 2:
                        try {
                            buttonPhoneCall.callToMyFriend();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }

                        break;
                    case 10:
                        buttonPower.buttonPowerPress(this);
                        return;
                    case 0:
                        buttonPower.buttonPowerDown(this);
                        return;
                    default:
                        System.out.println("Введено неверное число");
                        break;
                }
            } catch (InputMismatchException e) {
                log.error("введено не целое число!");
                in.next();
            }
        } while (true);
    }

    void workWithApplications() {

        int t;
        Application a;
        Scanner in = new Scanner(System.in);
        do {
            System.out.println(this.toString());
            System.out.println(String.format("Выберите приложение: %n" +
                    "1- приложение П1 %n" +
                    "2- приложение П2 %n" +
                    "3- приложение П3 %n" +
                    "0-закончить работу с приложениями %n"));
            try {
                t = in.nextInt();
                switch (t) {
                    case 1:
                        a = a1;
                        break;
                    case 2:
                        a = a2;
                        break;
                    case 3:
                        a = a3;
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Введено неверное число");
                        continue;
                }
            } catch (InputMismatchException e) {
                log.error("введено не целое число!");
                in.next();
                continue;
            }
            System.out.println(String.format("Выберите действие: %n" +
                    "1- установить приложение %n" +
                    "2- удалить приложение %n" +
                    "3- запустить приложение %n" +
                    "4-закрыть приложение %n" +
                    "5-поместить ярлык для приложенияна экран %n" +
                    "6-удалить ярлык для приложения с экрана %n" +
                    "0-закончить работу с приложениями %n"));
            try {
                t = in.nextInt();
                switch (t) {
                    case 1:
                        applicationBank.installApplication(a);
                        break;
                    case 2:
                        applicationBank.uninstallApplication(a);
                        break;
                    case 3:
                        System.out.println(screen.launchApplication(a));
                        break;
                    case 4:
                        screen.closeApplication(a);
                        break;
                    case 5:
                        System.out.println(screen.putShortcutOnScreen(a.getShortcut()));
                        break;
                    case 6:
                        System.out.println(screen.deleteShortcutFromScreen(a2.getShortcut()));
                        break;
                    case 0:
                        return;
                    default:
                        System.out.println("Введено неверное число");
                        break;
                }
            } catch (InputMismatchException e) {
                log.error("введено не целое число!");
                in.next();
            }


        } while (true);
    }

    @Override
    public String toString() {
        return String.format("На телефоне установлено: %s%n" +
                        "На экране находятся ярлыки: %s%n " +
                        "Запущены приложения: %s%n",
                applicationBank.toString(), screen.shortcutsToString(), screen.applicationsToString());
    }
}
