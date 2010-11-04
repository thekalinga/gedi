package info.sargis.gedi.parser;

import info.sargis.gedi.builder.model.una.UNASegment;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * Copyrights 2002-2010 Webb Fontaine
 * This software is the proprietary information of Webb Fontaine.
 * Its use is subject to License terms.
 * User: Sargis Harutyunyan
 * Date: Nov 3, 2010
 */
public class SegmentTagBuilderTest {

    @BeforeMethod
    public void setUp() {
    }

    @Test
    public void testBuildForSimpleSegmentTag() throws Exception {
        SegmentTagBuilder segmentTagBuilder = new SegmentTagBuilder(new UNASegment());
        SegmentTag tag = segmentTagBuilder.buildTag("UTX");

        Assert.assertEquals(tag.getSegmentCode(), "UTX");
        Assert.assertEquals(tag.getNestedElements().size(), 0, "List of nested elements should be empty");
    }

    @Test
    public void testBuildForCompositeSegmentTag() throws Exception {
        SegmentTagBuilder segmentTagBuilder = new SegmentTagBuilder(new UNASegment());
        SegmentTag tag = segmentTagBuilder.buildTag("UTX:1:2");

        Assert.assertEquals(tag.getSegmentCode(), "UTX");
        Assert.assertEquals(tag.getNestedElements(), Arrays.asList("1", "2"));
    }

    @AfterMethod
    public void tearDown() {
    }

}
