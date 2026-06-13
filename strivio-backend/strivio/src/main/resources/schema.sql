-- Enabling the POSTGIS extension
Create
extension if not exists postgis;

-- Creating Enums
drop type if exists hazard_type cascade;
create type hazard_type as enum(
    'POOR_LIGHTING',
    'BROKEN_SIDEWALK',
    'BLOCKED_PATH',
    'STRAY_ANIMALS',
    'UNSAFE_AREA'
);

drop type if exists severity_level cascade;
create type severity_level as enum(
    'LOW',
    'MEDIUM',
    'HIGH'
);

-- Creating Strivio user Table
create table if not exists strivio_users
(
    id           uuid primary key,
    username     varchar(100) not null unique,
    phone_number varchar(10)  not null
);

-- Creating Paths Table
create table if not exists paths
(
    id          uuid primary key,
    path_name   varchar(255) not null,
    created_at  timestamptz  not null default CURRENT_TIMESTAMP,
    distance    decimal(10, 2),
    description text,
    user_id     uuid         references strivio_users (id) on delete set null,
    route_path  geometry(linestring, 4326)

);

-- Creating Hazard Reports Table
create table if not exists hazard_reports
(
    id                 uuid primary key,
    hazard_type        hazard_type not null,
    severity           severity_level not null,
    path_id            uuid references paths (id) on delete cascade,
    user_id            uuid references strivio_users (id) on delete set null,
    latitude_longitude geometry(point, 4326)
);

-- Creating Spatial Indexes for performance queries
drop index if exists idx_paths_route_path;
create index idx_paths_route_path on paths using gist (route_path);
drop index if exists idx_hazard_lat_long;
create index idx_hazard_lat_long on hazard_reports using gist (latitude_longitude);