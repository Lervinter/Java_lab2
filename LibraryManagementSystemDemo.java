import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Item {
    private String title;
    private boolean available;

    public Item(String title) {
        this.title = title;
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}

class Client {
    private String name;

    public Client(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Library {
    private List<Item> items;
    private Map<String, Client> borrowedItems;

    public Library() {
        this.items = new ArrayList<>();
        this.borrowedItems = new HashMap<>();
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void issueItem(String title, Client client) {
        Item item = findItemByTitle(title);

        if (item != null && item.isAvailable()) {
            item.setAvailable(false);
            borrowedItems.put(item.getTitle(), client);
            System.out.println("Item issued successfully.");
        } else {
            System.out.println("Item not available.");
        }
    }

    public void returnItem(String title) {
        Item item = findItemByTitle(title);

        if (item != null && !item.isAvailable()) {
            item.setAvailable(true);
            borrowedItems.remove(item.getTitle());
            System.out.println("Item returned successfully.");
        } else {
            System.out.println("Invalid item or already returned.");
        }
    }

    public List<Item> getAvailableItems() {
        List<Item> availableItems = new ArrayList<>();
        for (Item item : items) {
            if (item.isAvailable()) {
                availableItems.add(item);
            }
        }
        return availableItems;
    }

    private Item findItemByTitle(String title) {
        for (Item item : items) {
            if (item.getTitle().equals(title)) {
                return item;
            }
        }
        return null;
    }
}

public class LibraryManagementSystemDemo {
    public static void main(String[] args) {
        Library library = new Library();

        Item book1 = new Item("Book1");
        Item book2 = new Item("Book2");

        library.addItem(book1);
        library.addItem(book2);

        Client john = new Client("John");

        library.issueItem("Book1", john);
        library.returnItem("Book1");

        library.issueItem("Book2", john);
        library.returnItem("Book2");

        System.out.println("Available items: " + library.getAvailableItems().size());
    }
}
