import java.util.HashMap;
import java.util.Map;

class VendingMachineItem {
    private String name;
    private double price;
    private int quantity;
    private int calories;

    public VendingMachineItem(String name, double price, int quantity, int calories) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.calories = calories;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getCalories() {
        return calories;
    }

    public void decreaseQuantity() {
        quantity--;
    }
}

class VendingMachine {
    private Map<Integer, VendingMachineItem> slots;
    private Map<Integer, Integer> itemQuantities;
    private double totalSales;
    private double insertedAmount;

    public VendingMachine() {
        slots = new HashMap<>();
        itemQuantities = new HashMap<>();
        totalSales = 0.0;
        insertedAmount = 0.0;
    }

    public void addItem(int slotNumber, VendingMachineItem item, int quantity) {
        slots.put(slotNumber, item);
        itemQuantities.put(slotNumber, quantity);
    }

    public void removeItem(int slotNumber) {
        slots.remove(slotNumber);
        itemQuantities.remove(slotNumber);
    }

    public void displayItems() {
        System.out.println("Available Items:");
        for (Map.Entry<Integer, VendingMachineItem> entry : slots.entrySet()) {
            int slotNumber = entry.getKey();
            VendingMachineItem item = entry.getValue();
            System.out.println(slotNumber + ": " + item.getName() + " - $" + item.getPrice());
        }
    }

    public void insertCoin(double amount) {
        insertedAmount += amount;
    }

    public void dispenseItem(int slotNumber) {
        if (slots.containsKey(slotNumber)) {
            VendingMachineItem item = slots.get(slotNumber);
            if (item.getQuantity() > 0) {
                if (item.getPrice() <= insertedAmount) {
                    item.decreaseQuantity();
                    totalSales += item.getPrice();
                    insertedAmount -= item.getPrice();
                    System.out.println("Dispensing item: " + item.getName());
                    produceChange();
                } else {
                    System.out.println("Insufficient funds. Please insert more coins.");
                }
            } else {
                System.out.println("Item out of stock.");
            }
        } else {
            System.out.println("Invalid slot number.");
        }
    }

    public void produceChange() {
        double change = insertedAmount;
        insertedAmount = 0.0;
        System.out.println("Change: $" + change);
    }

    public void printTransactionSummary() {
        System.out.println("Transaction Summary:");
        for (Map.Entry<Integer, VendingMachineItem> entry : slots.entrySet()) {
            int slotNumber = entry.getKey();
            VendingMachineItem item = entry.getValue();
            System.out.println(item.getName() + " - Quantity: " + itemQuantities.get(slotNumber));
        }
        System.out.println("Total Sales: $" + totalSales);
    }
}

public class VendingMachineTest {
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