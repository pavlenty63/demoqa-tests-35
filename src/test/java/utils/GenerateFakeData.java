package utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public class GenerateFakeData {
  public Faker faker = new Faker(new Locale("en-UK"));

  public String gerRandomFirstName() {
    return faker.name().firstName();
  }

  public String getRandomLastName() {
    return faker.name().lastName();
  }

  public String getRandomEmail() {
    return faker.internet().emailAddress();
  }

  public String getRandomPhoneNumber() {
    return faker.phoneNumber().subscriberNumber(10);
  }

  public String getRandomGender() {
    return faker.options().option("Male", "Female", "Other");
  }

  public String getRandomSubject() {
    return faker.options().option("Maths", "Biology", "English", "Economics", "Physics");
  }

  public String getRandomHobby() {
    return faker.options().option("Sports", "Reading", "Music");
  }

  public String getRandomAddress() {
    return faker.address().fullAddress();
  }

  public String getRandomState() {
    return faker.options().option("NCR", "Uttar Pradesh", "Haryana", "Rajasthan");
  }

  public String getRandomCity(String state) {
    switch (state) {
      case "NCR":
        return faker.options().option("Delhi", "Gurgaon", "Noida");
      case "Uttar Pradesh":
        return faker.options().option("Agra", "Lucknow", "Merrut");
      case "Haryana":
        return faker.options().option("Karnal", "Panipat");
      case "Rajasthan":
        return faker.options().option("Jaipur", "Jaiselmer");
      default:
        return null;
    }
  }

  public String getRandomYear() {
    return String.valueOf(faker.number().numberBetween(1900, 2100));
  }

  public String getRandomMonth() {
    return faker.options().option("January", "February", "March", "April", "May", "June", "July",
            "August", "September", "October", "November", "December");
  }

  public String getRandomDay(String month) {
    if (month.equals("February")) {
      return String.valueOf(faker.number().numberBetween(1, 28));
    } else if (month.equals("April") || month.equals("June") || month.equals("September") || month.equals("November")) {
      return String.valueOf(faker.number().numberBetween(1, 30));
    } else {
      return String.valueOf(faker.number().numberBetween(1, 31));
    }
  }

  public String getRandomPhoto() {
    return faker.options().option("picture.jpg", "image.jpg");
  }
}
