package edu.nyu.cess.gazepoint;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class MessageTest {

    @Test
    public void shouldReturnMessageTag() {
        String[][] tagMsgPairs = {
                {"SET","<SET ID=\"CALIBRATE_SHOW\" STATE=\"1\" />"},
                {"ACK","<ACK ID=\"CALIBRATE_SHOW\" STATE=\"1\" />"},
        };

        for (String[] tagMsgPair : tagMsgPairs) {
            assertTrue(Message.messageToTag(tagMsgPair[1]).equals(tagMsgPair[0]));
        }
    }

    @Test
    public void shouldReturnTrueIfTagFound() {
        String calibrationResult = "<CAL ID=\"CALIB_RESULT\" CALX1=\"0.50000\" CALY1=\"0.50000\" LX1=\"0.50229\" LY1=\"0.50279 LV1=\"1\" RX1=\"0.51467\" RY1=\"0.50870 RV1=\"1\" CALX2=\"0.85000\" CALY2=\"0.15000\" LX2=\"0.84943\" LY2=\"0.14930 LV2=\"1\" RX2=\"0.84600\" RY2=\"0.14763 RV2=\"1\" CALX3=\"0.85000\" CALY3=\"0.85000\" LX3=\"0.84942\" LY3=\"0.84929 LV3=\"1\" RX3=\"0.84627\" RY3=\"0.84779 RV3=\"1\" CALX4=\"0.15000\" CALY4=\"0.85000\" LX4=\"0.14943\" LY4=\"0.84930 LV4=\"1\" RX4=\"0.14616\" RY4=\"0.84772 RV4=\"1\" CALX5=\"0.15000\" CALY5=\"0.15000\" LX5=\"0.14944\" LY5=\"0.14931 LV5=\"1\" RX5=\"0.14689\" RY5=\"0.14815 RV5=\"1\" />";
        assertTrue(Message.hasIdValue(calibrationResult, Calibrate.CALIBRATION_RESULT_ID));
    }
}