package telephone.application;

import lombok.Getter;

public class Shortcut {
    @Getter
    private Application application;
    @Getter
    private boolean onScreen;

    Shortcut(Application application) {
        this.application = application;
        onScreen = false;
    }

    public void markShortcutOnScreen() {
        onScreen = true;
    }

    public void markShortcutDeleteFromScreen() {
        onScreen = false;
    }

    @Override
    public String toString() {
        return String.format("Ярлык для приложения %s %n", application);
    }
}

