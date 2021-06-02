import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    LocalTime openingTime = LocalTime.parse("10:30:00");;
    LocalTime closingTime = LocalTime.parse("22:00:00");
    @Spy
    Restaurant restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

    @BeforeEach
    public void setUp()
    {

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("baby corn starter",285);

    }

    //REFACTOR ALL THE REPEATED LINES OF CODE

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        Long duration = (Duration.between(openingTime, closingTime).toHours());
        int difference = duration.intValue();
        Random random = new Random();
        int differenceRandom = random.nextInt(difference)+1;


        when(restaurant.getCurrentTime()).thenReturn(openingTime.plusHours(differenceRandom));
        System.out.println("is_restaurant_open_should_return_true:"+restaurant.getCurrentTime());
        assertEquals(true,restaurant.isRestaurantOpen());

    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE


        Long duration = (Duration.between(openingTime, closingTime).toHours());
        int difference = duration.intValue();
        Random random = new Random();
        int differenceRandom = random.nextInt(difference)+1;
       //  restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        when(restaurant.getCurrentTime()).thenReturn(openingTime.minusHours(differenceRandom));
        System.out.println("is_restaurant_open_should_return_false:"+restaurant.getCurrentTime());
        assertEquals(false,restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}