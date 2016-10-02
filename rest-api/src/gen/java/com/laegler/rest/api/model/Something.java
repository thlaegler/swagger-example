package com.laegler.rest.api.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.laegler.rest.api.model.OtherObject;
import io.swagger.annotations.ApiModel;

/**
 * A Something object. 
 */
@ApiModel(description = "A Something object. ")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-02T17:28:58.433+02:00")
public class Something   {
  private Long id = null;

  private String name = null;

  private OtherObject otherObject = null;

  public Something id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Id of Something. 
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "Id of Something. ")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Something name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of Something. 
   * @return name
  **/
  @ApiModelProperty(example = "aSomething", required = true, value = "Name of Something. ")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Something otherObject(OtherObject otherObject) {
    this.otherObject = otherObject;
    return this;
  }

   /**
   * Get otherObject
   * @return otherObject
  **/
  @ApiModelProperty(example = "null", value = "")
  public OtherObject getOtherObject() {
    return otherObject;
  }

  public void setOtherObject(OtherObject otherObject) {
    this.otherObject = otherObject;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Something something = (Something) o;
    return Objects.equals(this.id, something.id) &&
        Objects.equals(this.name, something.name) &&
        Objects.equals(this.otherObject, something.otherObject);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, otherObject);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Something {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    otherObject: ").append(toIndentedString(otherObject)).append("\n");
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
  
  public static Something getExample() {
    return new Something(true);
  }
  
  private Something(boolean isExample) {
      this.id = 1L;
      this.name = "aSomething";
  }
  
  public Something() {
    super();
  }
  
  public static class SomethingBuilder {

    private Something internal;

    public SomethingBuilder() {
      internal = new Something();
    }

    public SomethingBuilder id(Long id) {
      internal.setId(id);
      return this;
    }

    public SomethingBuilder name(String name) {
      internal.setName(name);
      return this;
    }

    public SomethingBuilder otherObject(OtherObject otherObject) {
      internal.setOtherObject(otherObject);
      return this;
    }


    public Something build()  {
      return internal;
    }
  }
}

