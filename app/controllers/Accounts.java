package controllers;

import models.Member;
import play.Logger;
import play.data.validation.Error;
import play.mvc.Controller;

public class Accounts extends Controller {
  public static void signup() {
    render("signup.html");
  }

  public static void login() {
    render("login.html");
  }

  public static void register(String firstname, String lastname, String email, String password) {
    validation.required(firstname);
    validation.required(lastname);
    validation.required(email);
    validation.required(password);
    if (validation.hasErrors()) {
      for (Error error : validation.errors()) {
        System.out.println(error.message());
        Logger.info("Incorrect values entered");
        params.flash();
        validation.keep();
        signup();
      }
    }
    Logger.info("Registering new user " + email);
    Member member = new Member(firstname, lastname, email, password);
    member.save();
    authenticate(email, password);
  }

  public static void update() {
    render("update.html");
  }

  public static void updateProfile(String firstname, String lastname, String email, String password, Long id) {
    validation.required(firstname);
    validation.required(lastname);
    validation.required(email);
    validation.required(password);
    if (validation.hasErrors()) {
      {
        Logger.info("Incorrect values inserted");
        params.flash();
        validation.keep();
        update();
      }
    }
    Logger.info("Updating user info: " + getLoggedInMember().id);
    Member member = getLoggedInMember();
    member.setFirstname(firstname);
    member.setLastname(lastname);
    member.setEmail(email);
    member.setPassword(password);
    member.save();

    Logger.info("Changing name: " + firstname);
    redirect("/dashboard");
  }

  public static void authenticate(String email, String password) {
    Logger.info("Attempting to authenticate with " + email + ":" + password);

    Member member = Member.findByEmail(email);
    if ((member != null) && (member.checkPassword(password) == true)) {
      Logger.info("Authentication successful");
      session.put("logged_in_Memberid", member.id);
      redirect("/dashboard");
    } else {
      Logger.info("Authentication failed");
      redirect("/login");
    }
  }

  public static void logout() {
    session.clear();
    redirect("/");
  }

  public static Member getLoggedInMember() {
    Member member = null;
    if (session.contains("logged_in_Memberid")) {
      String memberId = session.get("logged_in_Memberid");
      member = Member.findById(Long.parseLong(memberId));
    } else {
      login();
    }
    return member;
  }
}
