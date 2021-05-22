/**
 *The member class defines fields to take information
 * for each user
 *
 * @Author David Fagan
 */
package models;
import play.db.jpa.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member extends Model {
  private String firstname;
  private String lastname;
  private String email;
  private String password;
  @OneToMany(cascade = CascadeType.ALL)
  public List<Station> stations = new ArrayList<Station>();

  public void setFirstname(String firstname){
    this.firstname = firstname;
  }

  public String getFirstname() {
    return firstname;
  }

  public void setLastname(String lastname)
  {
    this.lastname = lastname;
  }

  public String getLastname() {
    return lastname;
  }

  public void setEmail(String email)
  {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String password)
  {
    this.password = password;
  }
  
  public String getPassword() {
    return password;
  }

  public Member(String firstname, String lastname, String email, String password) {
    this.firstname = firstname;
    this.lastname = lastname;
    this.email = email;
    this.password = password;
  }

  public static Member findByEmail(String email) {
    return find("email", email).first();
  }

  public boolean checkPassword(String password) {
    return this.password.equals(password);
  }
}