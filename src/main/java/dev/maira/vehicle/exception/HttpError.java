package dev.maira.vehicle.exception;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.maira.vehicle.openapi.model.ErrorResponseDto;
import dev.maira.vehicle.openapi.model.ErrorTypeEnumDto;
import org.springframework.http.HttpStatus;

import java.io.Serializable;

public class HttpError implements Serializable {

	private static final long serialVersionUID = -1183795184299420291L;
	@JsonIgnore
	private HttpStatus status;
	private transient ErrorResponseDto errorResponseDto;

	public HttpError(HttpStatus status, ErrorResponseDto errorResponseDto) {
		this.status = status;
		this.errorResponseDto = errorResponseDto;
	}

	public static HttpError create(ErrorsEnum errorEnum, HttpStatus status, String details) {

		ErrorResponseDto responseDto = new ErrorResponseDto();
		responseDto.setCode(errorEnum.code);
		responseDto.setType(ErrorTypeEnumDto.fromValue(errorEnum.type.name()));
		responseDto.setMessage(errorEnum.message);
		responseDto.setDetails(details);

		return new HttpError(status, responseDto);

	}
}