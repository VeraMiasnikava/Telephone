package telephone.button;

import java.util.Random;

public class ButtonPhoneCall {
    private Random random = new Random();

    public void callToMyFriend() throws InterruptedException {
        int call = random.nextInt(2);
        Thread.sleep(3000);
        if (call == 1) {
            System.out.println("разговор состоялся");
        } else {
            System.out.println("друг не берет трубку, разговор не состоялся");
        }

    }

}
