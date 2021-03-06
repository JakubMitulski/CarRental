package capgemini.service;

import capgemini.dto.PositionTo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(properties = "spring.profiles.active=hsql")
public class PositionServiceTest {

    @Autowired
    private PositionService positionService;

    @Test
    @Transactional
    public void shouldFindPositionById() {
        //Given
        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        //When
        PositionTo positionById = positionService.findPositionById(testPosition.getId());

        //Then
        assertEquals(testPosition.getName(), positionById.getName());
    }

    @Test
    @Transactional
    public void shouldUpdatePosition() {
        //Given
        PositionTo positionTo = new PositionTo.PositionToBuilder()
                .withName("TestPosition")
                .build();
        PositionTo testPosition = positionService.addNewPosition(positionTo);

        //When
        testPosition.setName("NewPosition");
        PositionTo positionById = positionService.updatePosition(testPosition);

        //Then
        assertEquals(testPosition.getName(), positionById.getName());
    }
}
