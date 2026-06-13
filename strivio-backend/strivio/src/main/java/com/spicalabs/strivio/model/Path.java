package com.spicalabs.strivio.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

import org.geolatte.geom.LineString;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "paths")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Path {

    @Id
    private UUID id;

    @Column(name = "path_name", nullable = false)
    private String pathName;

    @Column(name = "created_at", nullable = false, insertable = false, updatable = false)
    private OffsetDateTime createdAt;

    private String description;
    private BigDecimal distance;

    @Column(name = "route_path", columnDefinition = "geometry(LineString,4326)")
    private LineString<?> routePath;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private StrivioUser creator;

}
