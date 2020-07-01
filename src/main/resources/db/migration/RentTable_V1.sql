CREATE TABLE Rent (
     rent_id UUID NOT NULL PRIMARY KEY,
     rent_start DATE NOT NULL,
     rent_end DATE NOT NULL,
     client_id UUID NOT NULL,
     car_id UUID NOT NULL
)