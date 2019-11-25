package telephone.application;


import telephone.device.Screen;
import lombok.extern.log4j.Log4j;
//import org.apache.log4j.Logger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@Log4j
public class ApplicationBank {
    private List<Application> applications = new ArrayList<>();
    private Screen screen;
    // private static Logger log = Logger.getLogger(ApplicationBank.class.getName());

    public ApplicationBank(Screen screen) {
        this.screen = screen;
    }

    public void installApplication(Application application) {

        if (!application.isInstalled()) {
            applications.add(application);
            application.markApplicationInstalled();
            int t = -1;
            Scanner in = new Scanner(System.in);
            do {
                System.out.println(String.format("устанавливается приложение %s%n", application) +
                        " поместить ярлык на экран? " +
                        "введите: 0 -нет, 1 -да ");
                try {
                    t = in.nextInt();
                } catch (InputMismatchException e) {
                    log.error("введено не целое число!");
                    in.next();
                }

            }
            while (t != 0 && t != 1);
            if (t == 1) {
                System.out.println(this.screen.putShortcutOnScreen(application.createShortcut()));
            }
            System.out.println(String.format("Приложение %s успешно установлено", application));
            log.info(String.format("Приложение %s успешно установлено", application));
        } else {
            System.out.println(String.format("Приложение %s уже было установлено ранее", application));
            log.info(String.format("Приложение %s уже было установлено ранее", application));
        }


    }

    public void installApplication(Application application, boolean shortcutOnScreen) {
        if (!application.isInstalled()) {
            applications.add(application);
            application.markApplicationInstalled();
            if (shortcutOnScreen) {
                this.screen.putShortcutOnScreen(application.createShortcut());
            }
        }
    }

    public void uninstallApplication(Application application) {
        if (application.isInstalled()) {
            this.screen.closeApplication(application);
            applications.remove(application);
            application.markApplicationUnInstalled();
            System.out.println(this.screen.deleteShortcutFromScreen(application.getShortcut()));
            System.out.println(String.format("Приложение %s успешно удалено", application));
            log.info(String.format("Приложение %s успешно удалено", application));
        } else {
            System.out.println(String.format("Приложение %s уже было удалено ранее ", application));
            log.info(String.format("Приложение %s уже было удалено ранее ", application));
        }
    }

    @Override
    public String toString() {
        return applications.toString();
    }

}
