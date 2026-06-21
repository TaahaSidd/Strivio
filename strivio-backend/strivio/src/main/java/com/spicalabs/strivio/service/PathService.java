package com.spicalabs.strivio.service;

import java.util.List;
import java.util.stream.Collectors;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.LineString;
import org.locationtech.jts.geom.PrecisionModel;
import org.springframework.stereotype.Service;

import com.spicalabs.strivio.dtos.request.PathCreationRequest;
import com.spicalabs.strivio.dtos.response.PathResponse;
import com.spicalabs.strivio.mapper.DtoMapper;
import com.spicalabs.strivio.model.Path;
import com.spicalabs.strivio.model.StrivioUser;
import com.spicalabs.strivio.repo.PathRepo;
import com.spicalabs.strivio.repo.UserRepo;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PathService {

    private final PathRepo pathRepo;
    private final UserRepo userRepo;
    private final DtoMapper dtoMapper;
    private final GeometryFactory geometryFactory = new GeometryFactory(new PrecisionModel(), 4326);

    @Transactional
    public PathResponse createPath(PathCreationRequest req) {

        // Getting user id
        StrivioUser user = userRepo.findById(req.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + req.getUserId()));

        Coordinate[] jtsCoords = req.getCoordinates().stream()
                .map(coord -> new Coordinate(coord.getLongitude(), coord.getLatitude()))
                .toArray(Coordinate[]::new);

        LineString lineString = geometryFactory.createLineString(jtsCoords);

        Path path = Path.builder()
                .pathName(req.getPathName())
                .description(req.getDescription())
                .distance(req.getDistance())
                .creator(user)
                .routePath(lineString)
                .build();

        Path savedPath = pathRepo.save(path);
        return dtoMapper.toPathResponse(savedPath);
    }

    public List<PathResponse> getAllPaths() {
        return pathRepo.findAll().stream()
                .map(dtoMapper::toPathResponse)
                .collect(Collectors.toList());
    }
}
