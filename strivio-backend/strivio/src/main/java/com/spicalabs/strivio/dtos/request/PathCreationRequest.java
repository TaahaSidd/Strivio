package com.spicalabs.strivio.dtos.request;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PathCreationRequest {
    
    @NotNull(message = "User ID is required")
    private UUID userId;
    @NotNull(message = "Path Name is required")
    private String pathName;

    private String description;
    private BigDecimal distance;
    
    @NotNull(message = "A path must contain at least two coordinates")
    private List<CoordinateRequest> coordinates;
}
