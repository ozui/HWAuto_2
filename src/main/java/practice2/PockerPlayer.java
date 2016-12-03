package practice2;

/**
 * Created by Oksana on 24.11.2016.
 */
public class PockerPlayer {

    private String username;
    private String email;
    private String password;
    private String fname;
    private String lname;
    private String country;
    private String city;
    private String address;
    private String phone;


    public PockerPlayer(String username, String email, String password, String fname, String lname, String country, String city, String address, String phone) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public PockerPlayer(String email, String fname, String lname, String country, String city, String address, String phone) {
        this.email = email;
        this.fname = fname;
        this.lname = lname;
        this.country = country;
        this.city = city;
        this.address = address;
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public String getAddress() {
        return address;
    }

    public String getPhone() {
        return phone;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Username: " + username + "\n" +
                "email: " + email + "\n" +
                "fname: " + fname + "\n" +
                "lname: " + lname + "\n" +
                "country: " + country + "\n" +
                "city: " + city + "\n" +
                "address: " + address + "\n" +
                "phone: " + phone + "\n";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PockerPlayer player = (PockerPlayer) o;

        if (email != null ? !email.equals(player.email) : player.email != null) return false;
        if (fname != null ? !fname.equals(player.fname) : player.fname != null) return false;
        if (lname != null ? !lname.equals(player.lname) : player.lname != null) return false;
        if (country != null ? !country.equals(player.country) : player.country != null) return false;
        if (city != null ? !city.equals(player.city) : player.city != null) return false;
        if (address != null ? !address.equals(player.address) : player.address != null) return false;
        return !(phone != null ? !phone.equals(player.phone) : player.phone != null);

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (fname != null ? fname.hashCode() : 0);
        result = 31 * result + (lname != null ? lname.hashCode() : 0);
        result = 31 * result + (country != null ? country.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        return result;
    }
}
