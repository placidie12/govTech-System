public void generateRevenueReport() {
    double totalRevenue = 0;
    Map<String, Double> revenueByService = new HashMap<>();
    List<ServiceApplication> applications = new ArrayList<>();

    for (ServiceApplication app : applications) {
        if (app.getStatus() == ServiceApplication.Status.APPROVED) {
            double fee = app.getService().getFeeCharged();
            totalRevenue += fee;

            String serviceName = app.getService().getServiceName();
            revenueByService.put(
                    serviceName,
                    revenueByService.getOrDefault(serviceName, 0.0) + fee
            );
        }
    }

    System.out.println("\n=== Revenue Summary ===");
    System.out.println("Total Revenue: $" + totalRevenue);

    try (PrintWriter pw = new PrintWriter(new FileWriter("revenue_report.txt"))) {
        pw.println("=== Revenue Summary ===");
        pw.println("Total Revenue: $" + totalRevenue);
        for (String service : revenueByService.keySet()) {
            pw.println(service + ": $" + revenueByService.get(service));
        }
        System.out.println("Revenue report saved successfully!");
    } catch (IOException e) {
        System.out.println("Error writing revenue report: " + e.getMessage());
    }
}

void main() {
}