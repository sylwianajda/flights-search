package com.FlightSearch.FlightSearch.controller.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class AirportRequest {
    @Schema(example = "WROCLAW", required = true, type = "string")
    @NotBlank
    private String name;
    @Schema(name = "location", example = "WROCLAW", required = true)
    @NotBlank
    private String location;
    @Schema(name = "iataCode", example = "WRO", required = true)
    @NotBlank
    private String iataCode;
    @Schema(example = "POLAND", required = true, type = "string")
    @NotBlank
    private String country;
    @Schema(example = "3.69", required = true, type = "double")
    @NotNull
    private Double latitude;
    @Schema(example = "2.699", required = true, type = "double")
    @NotNull
    private Double longitude;


}
