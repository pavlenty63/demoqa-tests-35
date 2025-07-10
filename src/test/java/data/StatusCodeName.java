package data;

public enum StatusCodeName {
  CREATED("Created"),
  NO_CONTENT("No Content"),
  MOVED("Moved"),
  BAD_REQUEST("Bad Request"),
  UNAUTHORIZED("Unauthorized"),
  FORBIDDEN("Forbidden"),
  NOT_FOUND("Not Found");


  public final String description;

  StatusCodeName(String description) {
    this.description = description;
  }
}
