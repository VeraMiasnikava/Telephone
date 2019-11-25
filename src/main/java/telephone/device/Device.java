package telephone.device;

import lombok.Getter;

abstract class Device {
    @Getter  protected boolean working;

    Device() {
        working = false;
    }

    void onDevice() {
        working = true;
    }

    public void offDevice() {
        working = false;
    }

}
