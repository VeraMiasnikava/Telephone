package telephone.button;


import telephone.device.Telephone;

public class ButtonIncrementSound extends Button {
    public void incrementVolume(Telephone telephone) {
        if (this.getNumberOfClicks() == MAX_NUMBER_OF_CLICKS) {
            System.out.println(String.format("кнопка была нажата %d раз, она сломалась %n", this.getNumberOfClicks()));
            return;
        }
        pressButton();
        if (telephone.getVolume() < Telephone.MAX_VOLUME) {
            telephone.incrementVolume();
            System.out.println(String.format("громкость повышена, ее значение=%d%n", telephone.getVolume()));
        } else {
            System.out.println(String.format("значение громкости=%d, повысить нельзя%n", telephone.getVolume()));
        }
    }


}
