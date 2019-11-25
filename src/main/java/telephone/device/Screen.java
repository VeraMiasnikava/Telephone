package telephone.device;

import telephone.application.Application;
import telephone.application.Shortcut;
import lombok.extern.log4j.Log4j;
//import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

@Log4j
public class Screen extends Device {
    private List<Application> applications = new ArrayList<>();
    private List<Shortcut> shortcuts = new ArrayList<>();
    //  private static Logger log = Logger.getLogger(Screen.class.getName());

    Screen() {
        super();
    }

     String launchApplication(Application application) {
        if (application.isInstalled()) {
            if (!application.isLaunched()) {
                applications.add(application);
                application.markApplicationLaunched();
                return String.format("Приложение %s запущено", application);
            } else {
                return String.format("Приложение %s уже было запущено ранее", application);
            }
        } else {
            return String.format("Приложение %s не установлено на вашем устройстве", application);
        }
    }

    public void closeApplication(Application application) {
        if (application.isInstalled()) {
            if (application.isLaunched()) {
                applications.remove(application);
                application.markApplicationClosed();
                System.out.println(String.format("Приложение %s закрыто", application));
                log.info(String.format("Приложение %s закрыто", application));
            } else {
                System.out.println(String.format("Приложение %s уже было закрыто ранее", application));
                log.info(String.format("Приложение %s уже было закрыто ранее", application));
            }
        } else {
            System.out.println(String.format("Приложение %s не установлено на вашем устройстве", application));
            log.info(String.format("Приложение %s не установлено на вашем устройстве", application));
        }
    }

    public String putShortcutOnScreen(Shortcut shortcut) {
        if (shortcut.getApplication().isInstalled()) {
            if (!shortcuts.contains(shortcut)) {
                shortcuts.add(shortcut);
                shortcut.markShortcutOnScreen();
                return String.format("ярлык для приложения %s помещен на экран", shortcut.getApplication());
            } else {
                return String.format("ярлык для приложения %s уже был помещен на экран", shortcut.getApplication());
            }
        } else {
            return String.format("Приложение %s не установлено на вашем устройстве", shortcut.getApplication());
        }
    }

    public String deleteShortcutFromScreen(Shortcut shortcut) {
        if (shortcuts.contains(shortcut)) {
            shortcuts.remove(shortcut);
            shortcut.markShortcutDeleteFromScreen();
            return "ярлык удален с  экрана";
        } else {
            return "ярлык уже был удален с  экрана";
        }
    }

    String applicationsToString() {
        return applications.toString();
    }

    String shortcutsToString() {
        return shortcuts.toString();
    }

}
