// TODO: package, any includes, class declaration, constructor
//       and the methods performAction and update.

    public void autoLock(int delayInSeconds) {
        System.out.println("Auto-lock enabled. Door will lock in " + delayInSeconds + " seconds.");
        new Thread(() -> {
            try {
                Thread.sleep(delayInSeconds * 1000L);
                setState(true);
                System.out.println("Door auto-locked.");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
