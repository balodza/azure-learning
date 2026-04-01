package com.bob.azure.validation;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

import jakarta.validation.ConstraintValidatorContext;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class CarMakeValidatorTest {

    private CarMakeValidator validator;
    private ConstraintValidatorContext context;

    @BeforeEach
    void setUp() {
        validator = new CarMakeValidator();
        context = mock(ConstraintValidatorContext.class);
    }

    @ParameterizedTest
    @ValueSource(strings = { "Toyota", "BMW", "Ford", "Honda", "Mercedes-Benz", "Volkswagen", "Tesla" })
    void isValid_exactCasing_returnsTrue(String make) {
        assertThat(validator.isValid(make, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "toyota", "bmw", "FORD", "hOnDa", "mercedes-benz", "VOLKSWAGEN", "tesla" })
    void isValid_caseInsensitive_returnsTrue(String make) {
        assertThat(validator.isValid(make, context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "FakeBrand", "NotACar", "XYZ", "Trabant", "DeLorean" })
    void isValid_unknownBrand_returnsFalse(String make) {
        assertThat(validator.isValid(make, context)).isFalse();
    }

    @Test
    void isValid_null_returnsTrue() {
        assertThat(validator.isValid(null, context)).isTrue();
    }

    @Test
    void isValid_blank_returnsTrue() {
        assertThat(validator.isValid("   ", context)).isTrue();
    }

    @Test
    void isValid_emptyString_returnsTrue() {
        assertThat(validator.isValid("", context)).isTrue();
    }

    @ParameterizedTest
    @ValueSource(strings = { "Abarth", "Alfa Romeo", "Aston Martin", "Audi", "Bentley",
            "Bugatti", "Ferrari", "Fiat", "Jaguar", "Lamborghini", "Land Rover",
            "Lancia", "Maserati", "McLaren", "Mini", "Opel", "Peugeot", "Porsche",
            "Renault", "Rolls-Royce", "Saab", "SEAT", "Skoda", "Smart", "Vauxhall", "Volvo",
            "Buick", "Cadillac", "Chevrolet", "Chrysler", "Dodge", "GMC", "Jeep", "Lincoln", "Ram",
            "Acura", "Infiniti", "Lexus", "Mazda", "Mitsubishi", "Nissan", "Subaru", "Suzuki",
            "Genesis", "Hyundai", "Kia",
            "BYD", "Geely", "Great Wall", "Haval", "Dacia", "Isuzu", "Lada", "Mahindra", "Tata" })
    void isValid_allBrandsInAllowlist_returnTrue(String make) {
        assertThat(validator.isValid(make, context)).isTrue();
    }
}
