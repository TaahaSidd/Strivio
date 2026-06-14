package com.spicalabs.strivio.dtos.response;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PathResponse {

    private UUID id;
    private String pathName;
    private String description;
    private BigDecimal distance;
    private OffsetDateTime createdAt;
    private String creatorUsername;
    List<PointResponse> coordinates;

}
