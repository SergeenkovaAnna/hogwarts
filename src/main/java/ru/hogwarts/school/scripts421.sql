ALTER TABLE student
    ADD CONSTRAINT ageConstraint CHECK (age >= 16);

ALTER TABLE student DROP CONSTRAINT ageConstraint;

ALTER TABLE student
    ADD CONSTRAINT nameConstraintUnique UNIQUE (name);

ALTER TABLE student DROP CONSTRAINT nameConstraintUnique;

ALTER TABLE student
    ALTER name set NOT NULL;

ALTER TABLE faculty
    ADD CONSTRAINT facultyUnique UNIQUE (name, color);

ALTER TABLE faculty DROP CONSTRAINT facultyUnique;

ALTER TABLE student
    ALTER COLUMN age set DEFAULT 20;