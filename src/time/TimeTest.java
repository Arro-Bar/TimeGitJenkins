package time;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class TimeTest {

	// ------------------------------------Seconds-----------------------------------------------------------

	@Test
	public void testGetTotalSecondsGood() {
		int seconds = Time.getTotalSeconds("05:05:05:00");
		assertTrue("The seconds were not calculated properly", seconds == 18305);
	}

	@Test
	public void testGetTotalSecondsBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalSeconds("10:00");
		});
	}

	@ParameterizedTest
	@ValueSource(strings = { "01:00:60:00", "01:01:00:00", "00:60:60:00" })
	void testGetTotalSecondsBoundary(String candidate) {
		int seconds = Time.getTotalSeconds(candidate);
		assertTrue("The seconds were not calculated properly", seconds == 3660);
	}

	// ------------------------------------Minutes-----------------------------------------------------------

	@Test
	public void testGetTotalMinutesGood() {
		int minutes = Time.getTotalMinutes("05:05:05");
		assertTrue("The minutes were not calculated properly", minutes == 5);
	}

	@Test
	public void testGetTotalMinutesBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalMinutes("01");
		});
	}

	@ParameterizedTest
	@ValueSource(strings = { "00:05:00", "05:05:05", "99:05:59" })
	void testGetTotalMinutessBoundary(String candidate) {
		int minutes = Time.getTotalMinutes(candidate);
		assertTrue("The minutes were not calculated properly", minutes == 5);
	}

	// ------------------------------------Hours-----------------------------------------------------------

	@Test
	public void testGetTotalHoursGood() {
		int hours = Time.getTotalHours("05:05:05");
		assertTrue("The hours were not calculated properly", hours == 5);
	}

	@Test
	public void testGetTotalHoursBad() {
		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			Time.getTotalHours("A");
		});
	}

	@ParameterizedTest
	@ValueSource(strings = { "05:00:00", "05:15:15", "05:59:59" })
	void testGetTotalHoursBoundary(String candidate) {
		int hours = Time.getTotalHours(candidate);
		assertTrue("The hours were not calculated properly", hours == 5);
	}

	// ------------------------------------Milliseconds-----------------------------------------------------------

	@Test
    public void testGetTotalMillisecondsGood() {
        int milliseconds = Time.getMilliseconds("01:02:03:04");
        assertEquals(4, milliseconds, "The milliseconds were not calculated properly");
    }

    @Test
    public void testGetTotalMillisecondsBad() {
        assertThrows(StringIndexOutOfBoundsException.class, () -> {
            Time.getMilliseconds("12:00:00:");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = { "00:00:00:50", "50:50:50:50", "12:59:59:50" })
    void testGetTotalMillisecondsBoundary(String candidate) {
        int milliseconds = Time.getMilliseconds(candidate);
        assertEquals(50, milliseconds, "The milliseconds were not calculated properly");
    }
}
