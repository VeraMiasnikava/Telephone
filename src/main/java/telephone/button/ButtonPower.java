package telephone.button;

import telephone.device.Telephone;

public class ButtonPower extends Button {

    public void buttonPowerPress(Telephone telephone) {
        pressButton();
        if (!telephone.getScreen().isWorking()) {
            telephone.onScreen();
        } else {
            telephone.getScreen().offDevice();
            System.out.println("Экран выключен ");

        }
    }

    public void buttonPowerDown(Telephone telephone) {
        holdButton();
        if (!telephone.isWorking()) {
            telephone.onTelephone();
        } else {
            telephone.offDevice();
            telephone.getScreen().offDevice();
            System.out.println("Телефон выключен ");
        }
    }
}
