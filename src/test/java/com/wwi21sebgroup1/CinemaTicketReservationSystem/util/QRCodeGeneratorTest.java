package com.wwi21sebgroup1.CinemaTicketReservationSystem.util;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.fail;

@ExtendWith(MockitoExtension.class)
public class QRCodeGeneratorTest {
    QRCodeGenerator qrCodeGenerator = new QRCodeGenerator();
    @Nested
    class GenerateQRCode{
        @Test
        public void t01NoErrorsThrown() {
            try{
                qrCodeGenerator.generateQRCode("test,123");
            }catch (Exception e){
                fail(e.toString());
            }
        }
    }
}
