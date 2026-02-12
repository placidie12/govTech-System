public class Citizens {
    private String fullNames;
    private String email;
    private String Id;
    private String PhoneNumber;
    private String NationalID;


    public Citizens(String fullNames, String email, String Id, String PhoneNumber, String NationalId) {
        this.fullNames = fullNames;
        this.email = email;
        this.Id = Id;
        this.PhoneNumber = PhoneNumber;
        this.NationalID = NationalId;
          }


    public String getFullNames() {
        return fullNames;
             }

    public String getEmail() {

        return email;
                }

    public String getID() {

        return Id;
              }

    public String getPhoneNumber() {

        return PhoneNumber;
                      }


    public void setFullNames(String fullNames) {

        this.fullNames = fullNames;
                     }

    public void setEmail(String email) {

        this.email = email;
              }

    public void setId(String Id) {

        this.Id = Id;
               }

    public void setPhoneNumber(String PhoneNumber) {

        this.PhoneNumber = PhoneNumber;
                           }

    public String getNationalId() {
        return NationalID;
    }

    @Override
    public String toString() {
        return "Citizen Details:\n" + "Full Names   : " + fullNames + "\n" + "Email : " + email + "\n" + "ID   : " + Id + "\n" + "Phone Number : " + PhoneNumber;
    }

}