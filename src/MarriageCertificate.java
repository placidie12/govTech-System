public class MarriageCertificate extends Governmentservice {

    public MarriageCertificate() {
        super("Marriage Certificate", "Police clearance certificate showing criminal history", 50.0);
    }

    @Override
    public void processService() {
        System.out.println("Processing Marriage Certificate Service...");
    }
}