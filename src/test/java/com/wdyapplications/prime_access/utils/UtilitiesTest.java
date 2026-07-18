package com.wdyapplications.prime_access.utils;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertEquals;

class UtilitiesTest {

	@Test
	void asTimestampParsesFrenchDateTimeFormat() throws Exception {
		Timestamp expected = Timestamp.valueOf("2026-07-20 23:18:53");

		assertEquals(expected, Utilities.asTimestamp("20/07/2026 23:18:53"));
	}
}
