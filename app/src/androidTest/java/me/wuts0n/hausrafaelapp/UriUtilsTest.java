package me.wuts0n.hausrafaelapp;


import android.support.test.runner.AndroidJUnit4;

import me.wuts0n.hausrafaelapp.utils.UriUtils;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class UriUtilsTest {
    @Test
    public void getAuthorityTest() throws Exception {
        assertEquals("caritas-erlangen.de",
                UriUtils.getAuthority("https://caritas-erlangen.de/index.php/de/hilfe-bei-psychischer-erkrankung/uebergangseinrichtung-haus-rafael/"));
        assertEquals("bisafans.de",
                UriUtils.getAuthority("http://www.bisafans.de/index.php/LUL/"));
        assertEquals("caritas-erlangen.de",
                UriUtils.getAuthority("https://www.caritas-erlangen.de/index.php/de/hilfe-bei-psychischer-erkrankung/uebergangseinrichtung-haus-rafael/"));
        assertEquals("reddit.com",
                UriUtils.getAuthority("https://www.reddit.com/r/leagueoflegends/  "));
        assertEquals("reddit.com",
                UriUtils.getAuthority("https://www.red dit.com/r/leagueoflegends/"));
        // Testing the failing of the method
        assertEquals("asdfghjkl",
                UriUtils.getAuthority("asdfghjkl"));
        assertEquals("......",
                UriUtils.getAuthority("......"));
        assertEquals("https:///index.php/de/hilfe-bei-psychischer-erkrankung/uebergangseinrichtung-haus-rafael/",
                UriUtils.getAuthority("https:///index.php/de/hilfe-bei-psychischer-erkrankung/uebergangseinrichtung-haus-rafael/"));
    }
}
