import java.util.Scanner;

public class Main {

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);
        ManagerApplication manager = new ManagerApplication();



        System.out.println("  Welcome to Digital Government Service System  ");


        while (true) {


            System.out.println("\n=== Main Menu ===");
            System.out.println("1. Apply for a Service");
            System.out.println("2. Approve an Application (Admin)");
            System.out.println("3. Reject an Application (Admin)");
            System.out.println("4. Display All Applications");
            System.out.println("5. Generate Revenue Report");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            int choice = -1;


            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number 1-6.");
                continue;
            }

            switch (choice) {

                case 1:

                    System.out.print("Full Name: ");
                    String fullName = scanner.nextLine();



                    System.out.print("National ID: ");
                    String nationalId = scanner.nextLine();

                    System.out.print("Email: ");
                    String email = scanner.nextLine();




                    System.out.println("Available Services:");
                    System.out.println("1. Marriage Certificate");
                    System.out.println("2. Criminal Record");
                    System.out.print("Choose a service: ");
                    int serviceChoice = Integer.parseInt(scanner.nextLine());

                    Citizens citizen = new Citizens(fullName, nationalId, email);
                    Governmentservice service;

                    if (serviceChoice == 1) {
                        service = new MarriageCertificate();
                    } else if (serviceChoice == 2) {
                        service = new Criminal_record();
                    } else {
                        System.out.println("Invalid service choice.");
                        break;
                    }

                    ServiceApplication application = new ServiceApplication(citizen, service);
                    manager.addApplication(application);
                    System.out.println("\nApplication Submitted Successfully!");
                    System.out.println("Application ID: " + application.getApplicationId());
                    System.out.println("Current Status: " + application.getStatus());
                    break;

                case 2:

                    System.out.print("Enter Application ID to approve: ");
                    String approveId = scanner.nextLine();
                    try {
                        manager.approveApplication(approveId);
                        System.out.println("Application approved successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 3:

                    System.out.print("Enter Application ID to reject: ");
                    String rejectId = scanner.nextLine();
                    try {
                        manager.rejectApplication(rejectId);
                        System.out.println("Application rejected successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;

                case 4:


                    manager.displayAllApplications();
                    break;

                case 5:
                    manager.generateRevenueReport();
                    break;

                case 6:

                    System.out.println("Exiting system. Goodbye!");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Enter a number between 1 and 6.");
            }
        }
    }
}