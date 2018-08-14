package capgemini.service;

import capgemini.dto.CarTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.Year;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarServiceTest {

    @Autowired
    private CarService carService;

    @Test
    @Transactional
    public void shouldFindCarById() {
        //Given
        CarTo carTo = new CarTo.CarToBuilder()
                .withBrand("mazda")
                .withModel("6")
                .withColor("gunmetal")
                .withProductionYear(Year.parse("2015"))
                .withEngineCapacity(2300)
                .withHorsePower(180)
                .withMileage(45000)
                .withCarType("limo")
                .build();
        CarTo testCar = carService.addNewCar(carTo);

        //When
        CarTo carById = carService.findCarById(testCar.getId());

        //Then
        assertEquals(testCar.getBrand(), carById.getBrand());
    }

    @Test
    @Transactional
    public void shouldDeleteCar() {
        //Given
        CarTo carTo = new CarTo.CarToBuilder()
                .withBrand("mazda")
                .withModel("6")
                .withColor("gunmetal")
                .withProductionYear(Year.parse("2015"))
                .withEngineCapacity(2300)
                .withHorsePower(180)
                .withMileage(45000)
                .withCarType("limo")
                .build();
        CarTo testCar = carService.addNewCar(carTo);

        //When
        carService.deleteCar(1L);

        //Then
        assertNull(carService.findCarById(1L));
    }

    @Test
    @Transactional
    public void shouldUpdateCar() {
        //Given
        CarTo carTo = new CarTo.CarToBuilder()
                .withBrand("mazda")
                .withModel("6")
                .withColor("gunmetal")
                .withProductionYear(Year.parse("2015"))
                .withEngineCapacity(2300)
                .withHorsePower(180)
                .withMileage(45000)
                .withCarType("limo")
                .build();
        CarTo testCar = carService.addNewCar(carTo);

        //When
        testCar.setColor("curcumagold");
        CarTo updatedCar = carService.updateCar(testCar);

        //Then
        assertEquals("curcumagold", carService.findCarById(updatedCar.getId()).getColor());
    }
}
