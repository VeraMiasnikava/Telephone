package telephone.button;

import lombok.Getter;

abstract class Button {

    @Getter
    private int numberOfClicks;
    protected static final int MAX_NUMBER_OF_CLICKS = 15;

    Button() {
        numberOfClicks = 0;
    }

    void pressButton() {
        numberOfClicks++;
    }

    void holdButton() {
        numberOfClicks++;
    }

}
