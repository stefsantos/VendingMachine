public class vendingmain {
    public static void main(String[] args) {
        VendingMachine vendingMachine = new VendingMachine();

        // Add items to the vending machine
        vendingMachine.addItem(1, new VendingMachineItem("Item A", 1.50, 10, 100), 10);
        vendingMachine.addItem(2, new VendingMachineItem("Item B", 2.00, 8, 150), 8);
        vendingMachine.addItem(3, new VendingMachineItem("Item C", 1.75, 5, 120), 5);

        // Display available items
        vendingMachine.displayItems();

        // Insert coins and dispense items
        vendingMachine.insertCoin(1.0);
        vendingMachine.insertCoin(0.5);
        vendingMachine.dispenseItem(1);
        vendingMachine.dispenseItem(2);

        // Print transaction summary
        vendingMachine.printTransactionSummary();
    }
}