package dev.maira.vehicle.exception;

public enum ErrorsEnum {
	
	INVALID_REQUEST_PAYLOAD("TRK400",ErrorTypeEnum.BUSINESS, "The request body is malformed/invalid."), 
	INVALID_REQUEST_PAYLOAD_PERSISTENCE("TRK400",ErrorTypeEnum.PERSISTENCE, "The request body is malformed/invalid on persistence context."), 
	NOT_FOUND("TRK404",ErrorTypeEnum.PERSISTENCE, "Entity or Relationship NOT exists."), 
	CONFLICT("TRK409",ErrorTypeEnum.PERSISTENCE, "Entity or Relationship already exists."), 
	GENERIC("TRK999",ErrorTypeEnum.UNKNOWN, "Ops... Unknown Error...See more on logs"); 

	String code;
	ErrorTypeEnum type;
	String message;

	ErrorsEnum(String code, ErrorTypeEnum type, String message) {
		this.code = code;
		this.type = type;
		this.message = message;
	}
}