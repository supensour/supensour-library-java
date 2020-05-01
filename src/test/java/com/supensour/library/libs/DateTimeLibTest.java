package com.supensour.library.libs;

import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import static com.supensour.library.libs.DateTimeLib.LOCALE_INDONESIA;
import static com.supensour.library.libs.DateTimeLib.LOCALE_SINGAPORE;
import static com.supensour.library.libs.DateTimeLib.TZ_JAKARTA;
import static com.supensour.library.libs.DateTimeLib.TZ_JAYAPURA;
import static com.supensour.library.libs.DateTimeLib.TZ_MAKASSAR;
import static com.supensour.library.libs.DateTimeLib.TZ_SINGAPORE;
import static com.supensour.library.libs.DateTimeLib.TZ_UTC;
import static com.supensour.library.libs.DateTimeLib.currentDate;
import static com.supensour.library.libs.DateTimeLib.currentMillis;
import static com.supensour.library.libs.DateTimeLib.formatDate;
import static com.supensour.library.libs.DateTimeLib.getEndOfDay;
import static com.supensour.library.libs.DateTimeLib.getStartOfDay;
import static org.junit.Assert.*;

/**
 * @author Suprayan Yapura
 * @since 1.0.0
 */
public class DateTimeLibTest {

  private final static String FULL_DATE_TIME_FORMAT = "dd MMMMM YYYY HH:mm:ss.SS z";
  private final static String FULL_TIME_FORMAT = "HH:mm:ss.SS z";

  private final static Date DATE = Date.from(LocalDateTime.now()
      .withYear(2019)
      .withMonth(2)
      .withDayOfMonth(15)
      .withHour(12)
      .withMinute(13)
      .withSecond(14)
      .withNano(15000000)
      .atZone(DateTimeLib.ZI_MAKASSAR)
      .toInstant());

  private final static Long TIME = DATE.getTime();

  @Before
  public void setUp() {
    TimeZone.setDefault(TZ_JAKARTA);
    Locale.setDefault(LOCALE_INDONESIA);
  }

  @Test
  public void formatDate_format_millis_locale_timezone() {
    assertEquals("15 Februari 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_INDONESIA, TZ_JAKARTA));
    assertEquals("15 Februari 2019 12:13:14.15 WITA", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_INDONESIA, TZ_MAKASSAR));
    assertEquals("15 Februari 2019 13:13:14.15 WIT", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_INDONESIA, TZ_JAYAPURA));
    assertEquals("15 Februari 2019 12:13:14.15 SGT", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_INDONESIA, TZ_SINGAPORE));

    assertEquals("15 February 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_SINGAPORE, TZ_JAKARTA));
    assertEquals("15 February 2019 12:13:14.15 WITA", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_SINGAPORE, TZ_MAKASSAR));
    assertEquals("15 February 2019 13:13:14.15 WIT", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_SINGAPORE, TZ_JAYAPURA));
    assertEquals("15 February 2019 12:13:14.15 SGT", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_SINGAPORE, TZ_SINGAPORE));
  }

  @Test
  public void formatDate_format_millis_locale_timezone_nullFormat() {
    try {
      formatDate(null, TIME, LOCALE_INDONESIA, TZ_JAKARTA);
    } catch (NullPointerException e) {
      assertEquals("Format must not be null", e.getMessage());
    }
  }

  @Test
  public void formatDate_format_millis_locale_timezone_invalidFormat() {
    try {
      formatDate("'", TIME, LOCALE_INDONESIA, TZ_JAKARTA);
    } catch (IllegalArgumentException e) {
      assertEquals("Unterminated quote", e.getMessage());
    }
  }

  @Test
  public void formatDate_format_millis_locale() {
    assertEquals("15 February 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, TIME, LOCALE_SINGAPORE));
  }

  @Test
  public void formatDate_format_date_locale() {
    assertEquals("15 Februari 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, DATE, LOCALE_INDONESIA));
  }

  @Test
  public void formatDate_format_millis_timezone() {
    assertEquals("15 Februari 2019 13:13:14.15 WIT", formatDate(FULL_DATE_TIME_FORMAT, TIME, TZ_JAYAPURA));
  }

  @Test
  public void formatDate_format_date_timezone() {
    assertEquals("15 Februari 2019 12:13:14.15 SGT", formatDate(FULL_DATE_TIME_FORMAT, DATE, TZ_SINGAPORE));
  }

  @Test
  public void formatDate_format_millis() {
    assertEquals("15 Februari 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, TIME));
  }

  @Test
  public void formatDate_format_date() {
    assertEquals("15 Februari 2019 11:13:14.15 WIB", formatDate(FULL_DATE_TIME_FORMAT, DATE));
  }

  @Test
  public void getStartOfDay_timezone_date() {
    Date jakarta = getStartOfDay(TZ_JAKARTA, DATE);
    Date makassar = getStartOfDay(TZ_MAKASSAR, DATE);
    Date jayapura = getStartOfDay(TZ_JAYAPURA, DATE);
    Date singapore = getStartOfDay(TZ_SINGAPORE, DATE);

    assertEquals("14 Februari 2019 17:00:00.00 UTC", formatDate(FULL_DATE_TIME_FORMAT, jakarta, TZ_UTC));
    assertEquals("15 Februari 2019 00:00:00.00 WIB", formatDate(FULL_DATE_TIME_FORMAT, jakarta, TZ_JAKARTA));
    assertEquals("15 Februari 2019 00:00:00.00 WITA", formatDate(FULL_DATE_TIME_FORMAT, makassar, TZ_MAKASSAR));
    assertEquals("15 Februari 2019 00:00:00.00 WIT", formatDate(FULL_DATE_TIME_FORMAT, jayapura, TZ_JAYAPURA));
    assertEquals("15 Februari 2019 00:00:00.00 SGT", formatDate(FULL_DATE_TIME_FORMAT, singapore, TZ_SINGAPORE));

    assertEquals(makassar.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(1).toMillis());
    assertEquals(jayapura.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(2).toMillis());
    assertEquals(singapore.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(1).toMillis());
  }

  @Test
  public void getStartOfDay_timezone() {
    Date date = getStartOfDay(TZ_JAKARTA);
    assertEquals("00:00:00.00 WIB", formatDate(FULL_TIME_FORMAT, date));
  }

  @Test
  public void getStartOfDay_millis() {
    Date date = getStartOfDay(TIME);
    assertEquals("15 Februari 2019 00:00:00.00 WIB", formatDate(FULL_DATE_TIME_FORMAT, date));
  }

  @Test
  public void getStartOfDay_date() {
    Date date = getStartOfDay(DATE);
    assertEquals("15 Februari 2019 00:00:00.00 WIB", formatDate(FULL_DATE_TIME_FORMAT, date));
  }

  @Test
  public void getStartOfDay_default() {
    Date date = getStartOfDay();
    assertEquals("00:00:00.00 WIB", formatDate(FULL_TIME_FORMAT, date));
  }

  @Test
  public void getEndOfDay_timezone_date() {
    Date jakarta = getEndOfDay(TZ_JAKARTA, DATE);
    Date makassar = getEndOfDay(TZ_MAKASSAR, DATE);
    Date jayapura = getEndOfDay(TZ_JAYAPURA, DATE);
    Date singapore = getEndOfDay(TZ_SINGAPORE, DATE);

    assertEquals("15 Februari 2019 16:59:59.999 UTC", formatDate(FULL_DATE_TIME_FORMAT, jakarta, TZ_UTC));
    assertEquals("15 Februari 2019 23:59:59.999 WIB", formatDate(FULL_DATE_TIME_FORMAT, jakarta, TZ_JAKARTA));
    assertEquals("15 Februari 2019 23:59:59.999 WITA", formatDate(FULL_DATE_TIME_FORMAT, makassar, TZ_MAKASSAR));
    assertEquals("15 Februari 2019 23:59:59.999 WIT", formatDate(FULL_DATE_TIME_FORMAT, jayapura, TZ_JAYAPURA));
    assertEquals("15 Februari 2019 23:59:59.999 SGT", formatDate(FULL_DATE_TIME_FORMAT, singapore, TZ_SINGAPORE));

    assertEquals(makassar.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(1).toMillis());
    assertEquals(jayapura.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(2).toMillis());
    assertEquals(singapore.getTime(), Duration.ofMillis(jakarta.getTime()).minusHours(1).toMillis());
  }

  @Test
  public void getEndOfDay_timezone() {
    Date date = getEndOfDay(TZ_JAKARTA);
    assertEquals("23:59:59.999 WIB", formatDate(FULL_TIME_FORMAT, date));
  }

  @Test
  public void getEndOfDay_millis() {
    Date date = getEndOfDay(TIME);
    assertEquals("15 Februari 2019 23:59:59.999 WIB", formatDate(FULL_DATE_TIME_FORMAT, date));
  }

  @Test
  public void getEndOfDay_date() {
    Date date = getEndOfDay(DATE);
    assertEquals("15 Februari 2019 23:59:59.999 WIB", formatDate(FULL_DATE_TIME_FORMAT, date));
  }

  @Test
  public void getEndOfDay_default() {
    Date date = getEndOfDay();
    assertEquals("23:59:59.999 WIB", formatDate(FULL_TIME_FORMAT, date));
  }

  @Test
  public void currentDate_default() {
    assertNotNull(currentDate());
  }

  @Test
  public void currentMillis_default() {
    assertNotNull(currentMillis());
  }

}
