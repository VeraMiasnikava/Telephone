package telephone.button;

import telephone.device.Telephone;

public class ButtonDecrementSound extends Button {
    public void decrementVolume(Telephone telephone) {
        if (this.getNumberOfClicks() == MAX_NUMBER_OF_CLICKS) {
            System.out.println(String.format("кнопка была нажата %d раз, она сломалась %n", this.getNumberOfClicks()));
            return;
        }
        pressButton();
        if (telephone.getVolume() > 0) {
            telephone.decrementVolume();
            System.out.println(String.format("громкость понижена, ее значение=%d%n", telephone.getVolume()));
        } else {
            System.out.println(String.format("значение громкости=%d, понизить нельзя%n", telephone.getVolume()));
        }
    }
}
