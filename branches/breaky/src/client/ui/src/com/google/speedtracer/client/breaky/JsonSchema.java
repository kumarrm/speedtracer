package com.google.speedtracer.client.breaky;

import com.google.gwt.core.client.JavaScriptObject;
import com.google.speedtracer.client.util.JSOArray;

/**
 * A simple JSO wrapper for JsonSchema objects.
 * 
 * Note that this wrapper does not walk the schema inheritance tree.
 */
public class JsonSchema extends JavaScriptObject {
  
  public static class JsonSchemaResults extends JavaScriptObject {
    protected JsonSchemaResults() {
    }
    
    public final native boolean isValid() /*-{
      return this.valid;
    }-*/;
       
    public final native JSOArray<JsonSchemaError> getErrors() /*-{
     return this.errors || [];
    }-*/;
    
    /**
     * Factory method for manually generating out of band errors.
     */
    public final static native JsonSchemaResults create(String property, String message) /*-{
      return {"valid" : false, 
              "errors" : [
                {"property" : property,
                "message" : message}
              ]};
    }-*/;
    
  }
  
  public static class JsonSchemaError extends JavaScriptObject {
    protected JsonSchemaError() {
    }
    
    public final native String getProperty() /*-{
      return this.property;
    }-*/;
    
    public final native String getMessage() /*-{
      return this.message;
    }-*/;
  }
  
  protected JsonSchema() {
  }
  
  /**
      Union type definition - An array with two or more items which
      indicates a union of type definitions.  Each item in the array may
      be a simple type definition or a schema.  The instance value is
      valid if it is of the same type as one the type definitions in the
      array or if it is valid by one of the schemas in the array.  For
      example to indicate that a string or number is a valid: {"type":
      ["string","number"]}
    
      Simple type definition - A string indicating a primitive or simple
      type.  The following are acceptable strings:
    
         string - Value must be a string.
    
         number - Value must be a number, floating point numbers are
         allowed.
    
         integer - Value must be an integer, no floating point numbers
         are allowed.  This is a subset of the number type.
    
         boolean - Value must be a boolean.
    
         object - Value must be an object.
    
         array - Value must be an array.
    
         null - Value must be null.  Note this is mainly for purpose of
         being able use union types to define nullability.
    
         any - Value may be of any type including null.  If the property
         is not defined or is not in this list, than any type of value
         is acceptable.  Other type values may be used for custom
         purposes, but minimal validators of the specification
         implementation can allow any instance value on unknown type
         values.

   */
  public final native JSOArray<String> getTypes() /*-{
    if(this.type instanceof Array) {
      return this.type;
    } else if(this.hasOwnProperty("type")) {
      return [this.type];
    } else {
      return [];
    }
  }-*/;
  
  /**
   This should be an object type definition, which is an object with
   property definitions that correspond to instance object properties.
   When the instance value is an object, the property values of the
   instance object must conform to the property definitions in this
   object.  In this object, each property definition's value should be a
   schema, and the property's name should be the name of the instance
   property that it defines.

   */
  public final native JavaScriptObject getProperties() /*-{
    return this.properties;
  }-*/;
  

  /**
   This should be a schema or an array of schemas.  When this is an
   object/schema and the instance value is an array, all the items in
   the array must conform to this schema.  When this is an array of
   schemas and the instance value is an array, each position in the
   instance array must conform to the schema in the corresponding
   position for this array.  This called tuple typing.  When tuple
   typing is used, additional items are allowed, disallowed, or
   constrained by the additionalProperties attribute using the same
   rules as extra properties for objects.  The default value is an empty
   schema which allows any value for items in the instance array.
   */
  public final native JavaScriptObject getItems() /*-{
    return this.items;
  }-*/;
  
  /**
   This indicates that the instance property in the instance object is
   optional.  This is false by default.
   */
  public final native boolean getOptional() /*-{
    if(this.hasOwnProperty("optional")) {
      return this.optional;
    } else {
      return false;
    }
  }-*/;
  
  /**
   This provides a default property definition for all properties that
   are not explicitly defined in an object type definition.  The value
   must be a schema.  If false is provided, no additional properties are
   allowed, and the schema can not be extended.  The default value is an
   empty schema which allows any value for additional properties.
   */
  public final native JavaScriptObject getAdditionalProperties() /*-{
    return this.additionalProperties;
  }-*/;
  
  /**
   * additionalProperties is overloaded.
   * This method checks if the schema allows undefined properties in an instance
   * To retrieve the native value, use {@link #getAdditionalProperties()}
   */
  public final native boolean getSchemaClosed() /*-{
    if(this.hasOwnProperty("additionalProperties") && !this.additionalProperties) {
      return false;
    } else {
      return true;
    }
  }-*/;
  
  /**
   This indicates the minimum value for the instance property when the
   type of the instance value is a number.
   */
  public final native int getMinimum() /*-{
    return this.minimum;
  }-*/;
  
  /**
   This indicates the minimum value for the instance property when the
   type of the instance value is a number.
   */
  public final native int getMaximum() /*-{
    return this.maximum;
  }-*/;
  
  /**
   If the minimum is defined, this indicates whether or not the instance
   property value can equal the minimum.

   */
  public final native int getMinimumCanEqual() /*-{
    return this.minimumCanEqual;
  }-*/;
  
  /**
   If the maximum is defined, this indicates whether or not the instance
   property value can equal the maximum.
   */
  public final native int getMaximumCanEqual() /*-{
    return this.maximumCanEqual;
  }-*/;
  
  /**
   This indicates the minimum number of values in an array when an array
   is the instance value.
   */
  public final native int getMinItems() /*-{
    return this.minItems;
  }-*/;
  
  /**
   This indicates the maximum number of values in an array when an array
   is the instance value.

   */
  public final native int getMaxItems() /*-{
    return this.maxItems;
  }-*/;
  
  /**
   This indicates that all the items in an array must be unique (no two
   identical values) within that array when an array is the instance
   value.
   */
  public final native boolean getUniqueItems() /*-{
    return this.uniqueItems;
  }-*/;
  
  /**
   When the instance value is a string, this provides a regular
   expression that a instance string value should match in order to be
   valid.  Regular expressions should follow the regular expression
   specification from ECMA 262/Perl 5
   */
  public final native String getPattern() /*-{
    return this.pattern;
  }-*/;
  
  /**
   This provides an enumeration of possible values that are valid for
   the instance property.  This should be an array, and each item in the
   array represents a possible value for the instance value.  If "enum"
   is included, the instance value must be one of the values in enum
   array in order for the schema to be valid.
   */
  public final native JSOArray<JavaScriptObject> getEnum() /*-{
    return this['enum']; //dictionary access to make Eclipse happy
  }-*/;
  
  /**
   This provides a short description of the instance property.  The
   value must be a string.
   */
  public final native String getTitle() /*-{
    return this.title;
  }-*/;
  
  /**
   This provides a full description of the of purpose the instance
   property.  The value must be a string.
   */
  public final native String getDescription() /*-{
    return this.description;
  }-*/;
  
  /**
   This property indicates the type of data, content type, or
   microformat to be expected in the instance property values.  A format
   attribute may be one of the values listed below, and if so, should
   adhere to the semantics describing for the format.  A format should
   only be used give meaning to primitive types (string, integer,
   number, or boolean).  Validators are not required to validate that
   the instance values conform to a format.  The following formats are
   defined:

      Any valid MIME media type may be used as a format value, in which
      case the instance property value must be a string, representing
      the contents of the MIME file.

      date-time - This should be a date in ISO 8601 format of YYYY-MM-
      DDThh:mm:ssZ in UTC time.  This is the recommended form of date/
      timestamp.

      date - This should be a date in the format of YYYY-MM-DD.  It is
      recommended that you use the "date-time" format instead of "date"
      unless you need to transfer only the date part.

      time - This should be a time in the format of hh:mm:ss.  It is
      recommended that you use the "date-time" format instead of "time"
      unless you need to transfer only the time part.

      utc-millisec - This should be the difference, measured in
      milliseconds, between the specified time and midnight, January 1,
      1970 UTC.  The value should be a number (integer or float).

      regex - A regular expression.

      color - This is a CSS color (like "#FF0000" or "red").

      style - This is a CSS style definition (like "color: red;
      background-color:#FFF").

      phone - This should be a phone number (format may follow E.123).

      uri - This value should be a URI..

      email - This should be an email address.

      ip-address - This should be an ip version 4 address.

      ipv6 - This should be an ip version 6 address.

      street-address - This should be a street address.

      locality - This should be a city or town.

      region - This should be a region (a state in the US, province in
      Canada, etc.)

      postal-code - This should be a postal code (AKA zip code).

      country - This should be the name of a country.

      Additional custom formats may be defined with a URL to a
      definition of the format.
   */
  public final native String getFormat() /*-{
    return this.format;
  }-*/;
  
  /**
   If the instance property value is a string, this indicates that the
   string should be interpreted as binary data and decoded using the
   encoding named by this schema property.  RFC 2045, Sec 6.1 lists
   possible values.
   */
  public final native String getContentEncoding() /*-{
    return this.contentEncoding;
  }-*/;
  
  /**
   This indicates the default for the instance property.
   */
  public final native JavaScriptObject getDefault() /*-{
    return this['default']; //dictionary access to make Eclipse happy
  }-*/;
  
  /**
   This indicates that the instance property value must be divisible by
   the given schema value when the instance property value is a number.
   */
  public final native int getDivisibleBy() /*-{
    return this.divisibleBy;
  }-*/;
  
  /**
   This attribute may take the same values as the "type" attribute,
   however if the instance matches the type or if this value is an array
   and the instance matches any type or schema in the array, than this
   instance is not valid.
   */
  public final native JSOArray<String> getDisallow() /*-{
    return this.disallow;
  }-*/;
  
  /**
   The value of this property should be another schema which will
   provide a base schema which the current schema will inherit from.
   The inheritance rules are such that any instance that is valid
   according to the current schema must be valid according to the
   referenced schema.  This may also be an array, in which case, the
   instance must be valid for all the schemas in the array.
   */
  public final native JsonSchema getExtends() /*-{
    return this['extends']; //Dictionary access to make Eclipse happy
  }-*/;

  public final native JsonSchemaResults validate(JavaScriptObject obj) /*-{
    return results = $wnd.JSONSchema.validate(obj, this);
  }-*/; 
   
}