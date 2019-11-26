/**
 * 
 */
package test.service;

import java.time.LocalDate;
import java.time.MonthDay;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

/**
 * @author wanghaoyu
 *
 */
public class TestLocalDate {

    @Test
    public void testBetweenDays(){
        LocalDate now = LocalDate.now();
        LocalDate dateOfBirthday = LocalDate.of(1997, 12, 15);
        LocalDate test1 = LocalDate.of(now.getYear(), dateOfBirthday.getMonth(), dateOfBirthday.getDayOfMonth());
        Long betweenDays = now.until(test1, ChronoUnit.DAYS);
        System.out.println(betweenDays);
    }
}
