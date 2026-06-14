package com.spicalabs.strivio.dtos.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CoordinateRequest {

    @NotNull(message = "Longitude is required")
    private Double longitude;
    @NotNull(message = "Latitude is required")
    private Double latitude;
}
