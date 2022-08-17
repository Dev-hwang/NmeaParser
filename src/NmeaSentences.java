import java.sql.Types;

public class NmeaSentences {
    private String tableName;
    private Integer attrSize;
    private String[] attrNames;
    private Integer[] attrTypes;

    public NmeaSentences() {

    }

    public NmeaSentences(String tableName, Integer attrSize, String[] attrNames, Integer[] attrTypes) {
        this.tableName = tableName;
        this.attrSize = attrSize;
        this.attrNames = attrNames;
        this.attrTypes = attrTypes;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public Integer getAttrSize() {
        return attrSize;
    }

    public void setAttrSize(Integer attrSize) {
        this.attrSize = attrSize;
    }

    public String[] getAttrNames() {
        return attrNames;
    }

    public void setAttrNames(String[] attrNames) {
        this.attrNames = attrNames;
    }

    public Integer[] getAttrTypes() {
        return attrTypes;
    }

    public void setAttrTypes(Integer[] attrTypes) {
        this.attrTypes = attrTypes;
    }

    //NMEA-0183 Data Sentence ID : DBT (Depth Below Transducer)
    static class DBT {
        private final String tableName = "data_dbt";
        private final Integer attrSize = 6;
        private final String[] attrNames = {"depth_feet", "feet", "depth_meters", "meters", "depth_fathoms", "fathoms"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : DPT (Standard Depth)
    static class DPT {
        private final String tableName = "data_dpt";
        private final Integer attrSize = 3;
        private final String[] attrNames = {"depth", "offset", "max_range_scale"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.FLOAT, Types.FLOAT};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : GGA (Global Positioning System Fix Data)
    static class GGA {
        private final String tableName = "data_gga";
        private final Integer attrSize = 14;
        private final String[] attrNames = {"utc_time_str", "latitude", "ns_indicator", "longitude", "ew_indicator",
                "position_fix", "satelites_used", "hdop", "altitude", "altitude_units", "geoid_seperation",
                "seperation_units", "dgps_age", "dgps_station_id"};
        private final Integer[] attrTypes = {Types.VARCHAR, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR,
                Types.TINYINT, Types.TINYINT, Types.FLOAT, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR,
                Types.FLOAT, Types.INTEGER};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : GLL (Geographic Position - Latitude/Longitude)
    static class GLL {
        private final String tableName = "data_gll";
        private final Integer attrSize = 7;
        private final String[] attrNames = {"latitude", "ns_indicator", "longitude", "ew_indicator", "utc_time_str",
                "status", "mode_indicator"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : HDT (Heading True)
    static class HDT {
        private final String tableName = "data_hdt";
        private final Integer attrSize = 2;
        private final String[] attrNames = {"heading", "data_true"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : MTW(Water Temperature)
    static class MTW {
        private final String tableName = "data_mtw";
        private final Integer attrSize = 2;
        private final String[] attrNames = {"temperature", "units"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : MWV (Wind Speed and Angle)
    static class MWV {
        private final String tableName = "data_mwv";
        private final Integer attrSize = 5;
        private final String[] attrNames = {"wind_angle", "reference", "wind_speed", "wind_speed_units", "status"};
        private final Integer[] attrTypes = {Types.INTEGER, Types.CHAR, Types.FLOAT, Types.CHAR, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : RMC (Recommended Minimum Navigation Information)
    static class RMC {
        private final String tableName = "data_rmc";
        private final Integer attrSize = 12;
        private final String[] attrNames = {"utc_time_str", "status", "latitude", "ns_indicator", "longitude",
                "ew_indicator", "speed_over_ground", "course_over_ground", "utc_date_str", "magnetic_variation",
                "ew_indicator2", "mode_indicator"};
        private final Integer[] attrTypes = {Types.VARCHAR, Types.CHAR, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR,
                Types.FLOAT, Types.FLOAT, Types.INTEGER, Types.VARCHAR, Types.CHAR, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : ROT (Rate Of Turn)
    static class ROT {
        private final String tableName = "data_rot";
        private final Integer attrSize = 2;
        private final String[] attrNames = {"rate_of_turn", "status"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : RSA (Rudder Sensor Angle)
    static class RSA {
        private final String tableName = "data_rsa";
        private final Integer attrSize = 4;
        private final String[] attrNames = {"starboard_rudder", "starboard_status", "port_rudder", "port_status"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : SHR (Motion Sensor Origin Format)
    static class SHR {
        private final String tableName = "data_shr";
        private final Integer attrSize = 11;
        private final String[] attrNames = {"utc_time_str", "heading", "heading_units", "roll", "pitch", "heave",
                "roll_std_deviation", "pitch_std_deviation", "heading_std_deviation", "quality_flag", "status_flag"};
        private final Integer[] attrTypes = {Types.VARCHAR, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.FLOAT,
                Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.TINYINT, Types.TINYINT};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : VTG (Track made good and Ground speed)
    static class VTG {
        private final String tableName = "data_vtg";
        private final Integer attrSize = 8;
        private final String[] attrNames = {"track_degrees_true", "data_true", "track_degrees_magnetic", "magnetic",
                "speed_knots", "knots", "speed_kph", "kph"};
        private final Integer[] attrTypes = {Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR, Types.FLOAT, Types.CHAR,
                Types.FLOAT, Types.CHAR};

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : XDR3 (Transducer Measurements - 3 Repeat)
    static class XDR3 {
        private final String tableName = "data_xdr3";
        private final Integer attrSize = 12;
        private final String[] attrNames = {
                "type1", "value1", "unit1", "id1",
                "type2", "value2", "unit2", "id2",
                "type3", "value3", "unit3", "id3"
        };
        private final Integer[] attrTypes = {
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR
        };

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : XDR4 (Transducer Measurements - 4 Repeat)
    static class XDR4 {
        private final String tableName = "data_xdr4";
        private final Integer attrSize = 16;
        private final String[] attrNames = {
                "type1", "value1", "unit1", "id1",
                "type2", "value2", "unit2", "id2",
                "type3", "value3", "unit3", "id3",
                "type4", "value4", "unit4", "id4"
        };
        private final Integer[] attrTypes = {
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR
        };

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : XDR5 (Transducer Measurements - 5 Repeat)
    static class XDR5 {
        private final String tableName = "data_xdr5";
        private final Integer attrSize = 20;
        private final String[] attrNames = {
                "type1", "value1", "unit1", "id1",
                "type2", "value2", "unit2", "id2",
                "type3", "value3", "unit3", "id3",
                "type4", "value4", "unit4", "id4",
                "type5", "value5", "unit5", "id5"
        };
        private final Integer[] attrTypes = {
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR
        };

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : XDR6 (Transducer Measurements - 6 Repeat)
    static class XDR6 {
        private final String tableName = "data_xdr6";
        private final Integer attrSize = 24;
        private final String[] attrNames = {
                "type1", "value1", "unit1", "id1",
                "type2", "value2", "unit2", "id2",
                "type3", "value3", "unit3", "id3",
                "type4", "value4", "unit4", "id4",
                "type5", "value5", "unit5", "id5",
                "type6", "value6", "unit6", "id6"
        };
        private final Integer[] attrTypes = {
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR,
                Types.CHAR, Types.FLOAT, Types.CHAR, Types.VARCHAR
        };

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }

    //NMEA-0183 Data Sentence ID : PTNVOPT1 (Optipower Data)
    static class PTNVOPT1 {
        private final String tableName = "data_ptnvopt1";
        private final Integer attrSize = 6;
        private final String[] attrNames = {
                "sharft_number", "speed", "torque", "thrust", "power", "rotation_direction"
        };
        private final Integer[] attrTypes = {
                Types.INTEGER, Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.FLOAT, Types.TINYINT
        };

        public String getTableName() {
            return tableName;
        }

        public Integer getAttrSize() {
            return attrSize;
        }

        public String[] getAttrNames() {
            return attrNames;
        }

        public Integer[] getAttrTypes() {
            return attrTypes;
        }
    }
}
