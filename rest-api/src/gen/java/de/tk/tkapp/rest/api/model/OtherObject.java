package de.tk.tkapp.rest.api.model;

import java.util.Objects;
import io.swagger.annotations.ApiModelProperty;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;

/**
 * A Reference to another object. 
 */
@ApiModel(description = "A Reference to another object. ")
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.JavaResteasyServerCodegen", date = "2016-10-03T20:17:21.129+02:00")
public class OtherObject   {
  private Long id = null;

  private String name = null;

  public OtherObject id(Long id) {
    this.id = id;
    return this;
  }

   /**
   * Id of OtherObject. 
   * @return id
  **/
  @ApiModelProperty(example = "1", required = true, value = "Id of OtherObject. ")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public OtherObject name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Name of OtherObject. 
   * @return name
  **/
  @ApiModelProperty(example = "aOtherObject", required = true, value = "Name of OtherObject. ")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    OtherObject otherObject = (OtherObject) o;
    return Objects.equals(this.id, otherObject.id) &&
        Objects.equals(this.name, otherObject.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class OtherObject {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
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
  
  public static OtherObject getExample() {
    OtherObject example = new OtherObject();
      example.id = 1L;
      example.name = "aOtherObject";
    return example;
  }
  
  public OtherObject() {
    super();
  }
  
  public static class OtherObjectBuilder {

    private OtherObject internal;

    public OtherObjectBuilder() {
      internal = new OtherObject();
    }

    public OtherObjectBuilder id(Long id) {
      internal.setId(id);
      return this;
    }

    public OtherObjectBuilder name(String name) {
      internal.setName(name);
      return this;
    }


    public OtherObject build()  {
      return internal;
    }
  }
}

