package com.bob.azure.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.Set;

public class CarMakeValidator implements ConstraintValidator<ValidCarMake, String> {

    private static final Set<String> VALID_MAKES = Set.of(
            // European
            "Abarth", "Alfa Romeo", "Aston Martin", "Audi", "Bentley", "BMW",
            "Bugatti", "Ferrari", "Fiat", "Jaguar", "Lamborghini", "Land Rover",
            "Lancia", "Maserati", "McLaren", "Mercedes-Benz", "Mini", "Opel",
            "Peugeot", "Porsche", "Renault", "Rolls-Royce", "Saab", "SEAT",
            "Skoda", "Smart", "Vauxhall", "Volkswagen", "Volvo",
            // American
            "Buick", "Cadillac", "Chevrolet", "Chrysler", "Dodge", "Ford",
            "GMC", "Jeep", "Lincoln", "Ram", "Tesla",
            // Japanese
            "Acura", "Honda", "Infiniti", "Lexus", "Mazda", "Mitsubishi",
            "Nissan", "Subaru", "Suzuki", "Toyota",
            // Korean
            "Genesis", "Hyundai", "Kia",
            // Chinese & others
            "BYD", "Geely", "Great Wall", "Haval", "Dacia", "Isuzu",
            "Lada", "Mahindra", "Tata"
    );

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null || value.isBlank()) {
            return true; // defer to @NotBlank
        }
        return VALID_MAKES.stream().anyMatch(make -> make.equalsIgnoreCase(value));
    }
}
