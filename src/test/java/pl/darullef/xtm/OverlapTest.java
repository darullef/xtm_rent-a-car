package pl.darullef.xtm;

import org.junit.Assert;
import org.junit.Test;

import pl.darullef.xtm.Service.RentService;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class OverlapTest {

    private final RentService rentService = new RentService();

    @Test
    public void overlapSameDatesShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapSecondEndsInsideFirstShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("15/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("12/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapSecondStartsInsideFirstShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("03/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("04/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapSecondContainsFirstShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapFirstContainsSecondShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapFirstEndIsSameWhatSecondStartBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapSecondStartIsSameWhatFirstEndShouldBeTrue() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("01/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), true);
    }

    @Test
    public void overlapSecondIsAfterFirstShouldBeFalse() throws ParseException {
        Date start1, end1, start2, end2;
        java.util.Date tempStart1, tempEnd1, tempStart2, tempEnd2;
        tempStart1 = new SimpleDateFormat("dd/MM/yyyy").parse("02/01/2020");
        tempEnd1 = new SimpleDateFormat("dd/MM/yyyy").parse("05/01/2020");
        tempStart2 = new SimpleDateFormat("dd/MM/yyyy").parse("07/01/2020");
        tempEnd2 = new SimpleDateFormat("dd/MM/yyyy").parse("10/01/2020");
        start1 = new Date(tempStart1.getTime());
        end1 = new Date(tempEnd1.getTime());
        start2 = new Date(tempStart2.getTime());
        end2 = new Date(tempEnd2.getTime());
        Assert.assertEquals(rentService.isOverlapping(start1, end1, start2, end2), false);
    }
}
