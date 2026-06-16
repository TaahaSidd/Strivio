package com.spicalabs.strivio.mapper;

import java.util.ArrayList;
import java.util.List;

import org.locationtech.jts.geom.Coordinate;
import org.springframework.stereotype.Component;

import com.spicalabs.strivio.dtos.response.PathResponse;
import com.spicalabs.strivio.dtos.response.PointResponse;
import com.spicalabs.strivio.model.Path;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DtoMapper {

    // Dto for PathResponse
    public PathResponse toPathResponse(Path path) {
        
        // Extract the raw jst coordinates.
        Coordinate[] jtsCoords = path.getRoutePath().getCoordinates();

        // map X/Y directly to Longitude/Latitude
        List<PointResponse> pointResponses = new ArrayList<>();
        for (Coordinate coord : jtsCoords) {
            pointResponses.add(new PointResponse(coord.x, coord.y));
        }

        // extract username
        String username = "anonymous";
        if (path.getCreator() != null) {
            username = path.getCreator().getUsername();
        }

        return PathResponse.builder()
                .id(path.getId())
                .pathName(path.getPathName())
                .description(path.getDescription())
                .distance(path.getDistance())
                .createdAt(path.getCreatedAt())
                .creatorUsername(username)
                .coordinates(pointResponses)
                .build();
    }
}
