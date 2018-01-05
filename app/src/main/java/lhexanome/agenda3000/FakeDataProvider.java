package lhexanome.agenda3000;

import android.graphics.Color;

import com.alamkanak.weekview.WeekViewEvent;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Random;


public class FakeDataProvider {

    private static final int NB_EVENT_PER_MONTH = 30;

    private static final String[] LOCATIONS = {
            "Amphi Gaston Berger",
            "501:331",
            "Humas",
            "208",
            "210"
    };

    private static final String[] EVENT_NAMES = {
            "CM Base de Données",
            "CM IHM",
            "TP Fouille de Données",
            "PLD Agile",
            "PLD MARS"
    };

    public static List<WeekViewEvent> getFakeData(int year, int month) {
        ArrayList<WeekViewEvent> events = new ArrayList<>(NB_EVENT_PER_MONTH);

        Calendar initDate = Calendar.getInstance();
        initDate.set(year, month, 0);


        for (int i = 0; i < NB_EVENT_PER_MONTH; i++) {
            Calendar startDate = (Calendar) initDate.clone();

            startDate.set(Calendar.DAY_OF_MONTH, new Random().nextInt(28));
            startDate.set(Calendar.HOUR_OF_DAY, (int) (Math.random() * 10 + 8));

            Calendar endDate = (Calendar) startDate.clone();
            endDate.add(Calendar.HOUR, (int) (Math.random() * 5));

            events.add(getRandomEvent(startDate, endDate));
        }

        return events;
    }

    public static WeekViewEvent getRandomEvent(Calendar start, Calendar end) {
        WeekViewEvent event = new WeekViewEvent();

        event.setStartTime(start);
        event.setAllDay(false);
        event.setColor(Color.BLUE);
        event.setEndTime(end);
        event.setLocation(getRandomLocation());
        event.setName(getRandomName());

        return event;
    }

    private static String getRandomName() {
        return EVENT_NAMES[new Random().nextInt(EVENT_NAMES.length)];

    }


    public static String getRandomLocation() {
        return LOCATIONS[new Random().nextInt(LOCATIONS.length)];
    }
}
