-- liquibase formatted sql

-- changeset ASergeenkova:1
    CREATE INDEX studentNameIndex ON student (name);

-- changeset ASergeenkova:2
    CREATE INDEX facultyByNameAndColor ON faculty (name, color);