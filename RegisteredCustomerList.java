public class RegisteredCustomerList {
   private CustomerNode head;
   private CustomerNode tail;

   private class CustomerNode {
      Customer customer;
      CustomerNode next;
      CustomerNode prev;

      public CustomerNode(Customer customer) {
         this.customer = customer;
      }
   }

   // Add Customer
   public void addCustomer(Customer customer) {
      CustomerNode newNode = new CustomerNode(customer);
      if (head == null) {
         head = newNode;
         tail = newNode;
      } else {
         newNode.next = head;
         head.prev = newNode;
         head = newNode;
      }
   }

   // Get Newest Customer
   public Customer getNewestCustomer() {
      if (head == null) {
         return null;
      }
      return head.customer;
   }

   // Get Oldest Customer
   public Customer getOldestCustomer() {
      if (tail == null) {
         return null;
      }
      return tail.customer;
   }

   // Remove Customer
   public void removeCustomer(int customerId) {
      CustomerNode current = head;
      while (current != null) {
         if (current.customer.id == customerId) {
            if (current == head) {
               head = current.next;
               if (head != null) {
                  head.prev = null;
               }
            } else if (current == tail) {
               tail = current.prev;
               if (tail != null) {
                  tail.next = null;
               }
            } else {
               current.prev.next = current.next;
               current.next.prev = current.prev;
            }
            return;
         }
         current = current.next;
      }
   }

   // Display All Customers (Newest to Oldest)
   public void displayAllCustomers() {
      CustomerNode current = head;
      while (current != null) {
         System.out.println(current.customer);
         current = current.next;
      }

   }

   // Main method for demonstration
   public static void main(String[] args) {
      // Example usage of RegisteredCustomerList class
      RegisteredCustomerList customerList = new RegisteredCustomerList();

      // Add some customers
      customerList.addCustomer(new Customer(1, "John", "1234567890", "kavi@example.com", "CityA", 25));
      customerList.addCustomer(new Customer(2, "Jane", "0987654321", "nirman@example.com", "CityB", 30));

      // Display all customers
      System.out.println("All Customers:");
      customerList.displayAllCustomers();
   }
}
