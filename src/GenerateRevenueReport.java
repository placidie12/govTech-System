public String generateEnhancedRevenueReport() {
    double totalRevenue = 0.0;
    Map<String, Double> revenueByService = new HashMap<>();
    Map<String, Integer> appCountByService = new HashMap<>();
    int totalApprovedApps = 0;

    for (ServiceApplication app : application) {
        if (app.getStatus() == ServiceApplication.Status.APPROVED) {
            double fee = app.getService().getFeeCharged();
            totalRevenue += fee;
            totalApprovedApps++;

            String serviceName = app.getService().getServiceName();
            revenueByService.merge(serviceName, fee, Double::sum);
            appCountByService.merge(serviceName, 1, Integer::sum);
        }
    }

    StringBuilder report = new StringBuilder();
    report.append("=== Enhanced Revenue Report ===\n");
    report.append("Date: ").append(java.time.LocalDate.now()).append("\n");
    report.append("Total Revenue: ").append(totalRevenue).append("\n");
    report.append("Total Approved Applications: ").append(totalApprovedApps).append("\n\n");
    report.append("Revenue by Service:\n");
    report.append(String.format("%-25s %-15s %-15s\n", "Service", "Revenue", "Applications"));

    for (Map.Entry<String, Double> entry : revenueByService.entrySet()) {
        String serviceName = entry.getKey();
        double revenue = entry.getValue();
        int appCount = appCountByService.get(serviceName);

        report.append(String.format("%-25s %-15.2f %-15d\n", serviceName, revenue, appCount));
    }

    return report.toString();
}

private ServiceApplication[] application;

void main() {
}
