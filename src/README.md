```java
public class Main {
    public static void main(String[] args) {
        /*
         * Support Sentence
         *
         * DBT (Depth Below Transducer)
         * DPT (Standard Depth)
         * GGA (Global Positioning System Fix Data)
         * GLL (Geographic Position - Latitude/Longitude)
         * HDT (Heading True)
         * MTW(Water Temperature)
         * MWV (Wind Speed and Angle)
         * RMC (Recommended Minimum Navigation Information)
         * ROT (Rate Of Turn)
         * RSA (Rudder Sensor Angle)
         * SHR (Motion Sensor Origin Format)
         * VTG (Track made good and Ground speed)
         * XDR3~6 (Transducer Measurements - n Repeat)
         * PTNVOPT1 (Optipower Data)
         */

        final String nmeaData = "$GPGGA,141113.999,3730.0308,N,12655.2369,E,1,06,1.7,98.9,M,,,,0000*3E\r\n" +
                "$GPGGA,171059.000,3749.9201,N,12228.4985,W,2,09,1.0,-6.1,M,-25.3,M,5.0,0000*6E\r\n" +
                "$SDDBT,3.2,f,0.9,M,0.5,F*0B\r\n" +
                "$TIROT,-0.0,A*16\r\n" +
                "$GPRMC,004952,A,3723.8259,N,12655.3071,E,000.0,088.7,291107,,,A*72\r\n";

        final List<Map<String, Object>> result = new NmeaParser().parseToList(nmeaData);
        System.out.print("result: " + result.toString());
    }
}
```
