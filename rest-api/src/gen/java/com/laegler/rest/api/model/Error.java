package com.laegler.rest.api.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;

/**
 * Generic Error object. 
 */
@ApiModel(description = "Generic Error object. ")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-01T16:15:31.634+02:00")
public class Error   {
  private Long code = null;

  private String title = null;

  private String message = null;

  public Error code(Long code) {
    this.code = code;
    return this;
  }

   /**
   * Error Code of this Error. 
   * @return code
  **/
  @ApiModelProperty(example = "123", required = true, value = "Error Code of this Error. ")
  public Long getCode() {
    return code;
  }

  public void setCode(Long code) {
    this.code = code;
  }

  public Error title(String title) {
    this.title = title;
    return this;
  }

   /**
   * Title of the Error Message. 
   * @return title
  **/
  @ApiModelProperty(example = "Invalid Request", required = true, value = "Title of the Error Message. ")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Error message(String message) {
    this.message = message;
    return this;
  }

   /**
   * Message Body of this Error.
   * @return message
  **/
  @ApiModelProperty(example = "Your Request was somehow invalid.", required = true, value = "Message Body of this Error.")
  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Error error = (Error) o;
    return Objects.equals(this.code, error.code) &&
        Objects.equals(this.title, error.title) &&
        Objects.equals(this.message, error.message);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, title, message);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Error {\n");
    
    sb.append("    code: ").append(toIndentedString(code)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    message: ").append(toIndentedString(message)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
  
  public static Error getExample() {
    return new Error(true);
  }
  
  private Error(boolean isExample) {
      this.code = 123L;
      this.title = "Invalid Request";
      this.message = "Your Request was somehow invalid.";
  }
  
  public Error() {
    super();
  }
  
  public static class ErrorBuilder {

    private Error internal;

    public ErrorBuilder() {
      internal = new Error();
    }

    public ErrorBuilder code(Long code) {
      internal.setCode(code);
      return this;
    }

    public ErrorBuilder title(String title) {
      internal.setTitle(title);
      return this;
    }

    public ErrorBuilder message(String message) {
      internal.setMessage(message);
      return this;
    }


    public Error build()  {
      return internal;
    }
  }
}

