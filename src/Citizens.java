public class Citizens {
    private String fullNames;
    private String email;
    private String NationalID;


    public Citizens(String fullNames, String email, String NationalId) {
        this.fullNames = fullNames;
        this.email = email;

        this.NationalID = NationalId;
    }


    public String getFullNames() {

        return fullNames;
    }

    public String getEmail() {

        return email;
    }

    public String getNationalID() {
        return NationalID;
    }




    @Override
    public String toString() {
        return "Citizen Details:\n" + "Full Names   : " + fullNames + "\n" + "Email : " + email + "\n" + "\n" + "NationalID:" + NationalID;

    }
}