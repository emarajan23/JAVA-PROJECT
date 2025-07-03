//package com.inventory.view;
//
//import com.inventory.model.Users;
//
//import java.util.Scanner;
//
//public class ProductionManagerDashboardView {
//
//    public void show(Users manager) {
//        Scanner sc = new Scanner(System.in);
//
//        while (true) {
//            System.out.println("\n🏭 Production Manager Dashboard");
//            System.out.println("1. View Inventory");
//            System.out.println("2. Record Fabric Usage");
//            System.out.println("3. Logout");
//            System.out.print("Enter choice: ");
//
//            int choice = sc.nextInt();
//            sc.nextLine(); // consume newline
//
//            switch (choice) {
//                case 1:
//                    // TODO: Show available inventory
//                    System.out.println("📦 View inventory - (Coming soon)");
//                    break;
//                case 2:
//                    // TODO: Record how much fabric was used
//                    System.out.println("🧾 Record fabric usage - (Coming soon)");
//                    break;
//                case 3:
//                    System.out.println("🔓 Logged out.");
//                    return;
//                default:
//                    System.out.println("❌ Invalid option. Try again.");
//            }
//        }
//    }
//}
