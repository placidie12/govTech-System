public class Criminal_record extends Governmentservice {

    public Criminal_record() {
        super("Criminal Record", "Police clearance certificate showing criminal history", 30.0);
    }

    @Override
    public void processService() {
        System.out.println("Processing Criminal Record Service...");
    }
}