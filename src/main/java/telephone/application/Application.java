package telephone.application;

import lombok.Getter;

public class Application {
    private String name;
    @Getter
    private Shortcut shortcut;
    @Getter
    private boolean launched;
    @Getter
    private boolean installed;

     public Application(String name) {
        this.name = name;
        launched = false;
        installed = false;
        createShortcut();
    }

    Shortcut createShortcut() {
        shortcut = new Shortcut(this);
        return shortcut;
    }

    public void markApplicationLaunched() {
        launched = true;
    }

    public void markApplicationClosed() {
        launched = false;
    }

    void markApplicationInstalled() {
        installed = true;
    }

    void markApplicationUnInstalled() {
        installed = false;
    }

    @Override
    public String toString() {
        if (shortcut.isOnScreen()) {
            return String.format("Приложение: %s  (его ярлык на экране)", name);
        } else {
            return String.format("Приложение: %s (его ярлык НЕ на экране)", name);
        }

    }
}
