package com.ds.ce.diy.service;

import com.ds.ce.diy.domain.User;
import net.fortuna.ical4j.model.Calendar;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.Dur;
import net.fortuna.ical4j.model.ParameterList;
import net.fortuna.ical4j.model.PropertyList;
import net.fortuna.ical4j.model.component.VAlarm;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.Cn;
import net.fortuna.ical4j.model.parameter.Role;
import net.fortuna.ical4j.model.parameter.Rsvp;
import net.fortuna.ical4j.model.property.Action;
import net.fortuna.ical4j.model.property.Attendee;
import net.fortuna.ical4j.model.property.Categories;
import net.fortuna.ical4j.model.property.Clazz;
import net.fortuna.ical4j.model.property.Description;
import net.fortuna.ical4j.model.property.Location;
import net.fortuna.ical4j.model.property.Method;
import net.fortuna.ical4j.model.property.Organizer;
import net.fortuna.ical4j.model.property.Priority;
import net.fortuna.ical4j.model.property.ProdId;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Status;
import net.fortuna.ical4j.model.property.Transp;
import net.fortuna.ical4j.model.property.TzId;
import net.fortuna.ical4j.model.property.Version;
import net.fortuna.ical4j.util.UidGenerator;
import org.springframework.stereotype.Service;

import java.net.SocketException;
import java.net.URI;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ICalendarService {

    /**
     * generate a calendar event
     * @param required required participants
     * @param startDate event start date
     * @param duration event duration
     * @param location event location
     * @param summary event summary
     * @param description event description
     * @param organizer event organizer
     * @param optionals (optional) optional participants
     * @param category (optional) event category
     * @return Calendar with an event and an alarm
     * @throws SocketException
     */
    public Calendar getCalendar(List<User> required, LocalDateTime startDate, Duration duration, String location, String summary,
                                String description, String organizer, String category, List<User> optionals) throws SocketException {

        ZoneId zoneId = ZoneId.of("Europe/Paris");

        DateTime eventStartDate = new DateTime(startDate.atZone(zoneId).toInstant().toEpochMilli());
        VEvent vEvent = new VEvent(eventStartDate, new Dur(duration.toString()), summary);

        // Generate a UID for the event
        PropertyList vEventProperties = vEvent.getProperties();
        vEventProperties.add(new UidGenerator("1").generateUid());

        for (User participant : required) {
            addEventAttendee(vEventProperties, participant, true);
        }

        if (optionals != null) {
            for (User optional : optionals) {
                addEventAttendee(vEventProperties, optional, false);
            }
        }

        // use Tzid property instead of use TimeZoneRegistryFactory.getInstance().createRegistry().getTimeZone(zoneId.getId()).getVTimeZone().getTimeZoneId()
        vEventProperties.add(new TzId(zoneId.getId()));
        vEventProperties.add(Transp.OPAQUE);
        vEventProperties.add(Priority.MEDIUM);
        vEventProperties.add(Clazz.PUBLIC);
        vEventProperties.add(Status.VEVENT_CONFIRMED);
        vEventProperties.add(new Location(location));
        vEventProperties.add(new Sequence(0));

        if (organizer != null) {
            vEventProperties.add(new Organizer(URI.create("MAILTO:" + organizer)));
        }

        if (category != null) {
            vEventProperties.add(new Categories(category));
        }

        if (description != null) {
            vEventProperties.add(new Description(description));
        }

        //TODO mettre en configuration / tester en avoir plusieurs (2h avant)

        VAlarm alarmOnDay = new VAlarm(new Dur(-1, 0, 0, 0));
        PropertyList vAlarmProperties = alarmOnDay.getProperties();
        vAlarmProperties.add(Action.DISPLAY);
        vAlarmProperties.add(new Description("Reminder"));
        vEvent.getAlarms().add(alarmOnDay);

        VAlarm alarmTwoHours = new VAlarm(new Dur(0, 2, 0, 0));
        PropertyList alarmProperties = alarmTwoHours.getProperties();
        alarmProperties.add(Action.DISPLAY);
        alarmProperties.add(new Description("Reminder"));
        vEvent.getAlarms().add(alarmTwoHours);

        Calendar calendar = new Calendar();
        PropertyList calendarProperties = calendar.getProperties();
        calendarProperties.add(Version.VERSION_2_0);
        calendarProperties.add(new ProdId("-//Section Bricolage//iCal4j 1.0//FR"));
        calendarProperties.add(Method.REQUEST);
        calendar.getComponents().add(vEvent);

        return calendar;
    }

    private void addEventAttendee(PropertyList vEventProperties,
                                  User user, boolean isRequired) {
        Attendee attendee = new Attendee(URI.create("MAILTO:" + user.getEmail()));
        ParameterList parameters = attendee.getParameters();
        parameters.add(new Cn(user.getFirstname() + " " + user.getLastname()));
        parameters.add(isRequired ? Role.REQ_PARTICIPANT : Role.OPT_PARTICIPANT);
        parameters.add(Rsvp.TRUE);
        vEventProperties.add(attendee);
    }

}
