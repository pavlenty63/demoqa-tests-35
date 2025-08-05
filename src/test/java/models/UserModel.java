package models;

import lombok.Data;

@Data
public class UserModel {
  String firstName, lastName, job, email, password, updatedAt;
}
