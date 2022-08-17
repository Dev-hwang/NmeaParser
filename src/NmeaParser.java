import java.sql.Timestamp;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NmeaParser {
    public Map<String, Map<String, Object>> parseToMap(String nmeaData) {
        Map<String, Map<String, Object>> parseMap = new HashMap<>();

        String[] splitedData = nmeaData.split("\r\n");
        for (String tmpData : splitedData) {

            //$로 시작
            String[] data = tmpData.split("\\$");
            if(data.length != 2)
                continue;

            Timestamp timestamp = null;
            if(!data[0].isEmpty())
                timestamp = Timestamp.valueOf(data[0]);

            //Sentence Type Check
            String[] attrs = data[1].split(",");
            if(attrs.length < 1 || attrs[0].length() < 2)
                continue;

            String talkerId = null;
            String sentenceId;
            if(attrs[0].equals("PTNVOPT1")) {
                sentenceId = attrs[0];
            } else {
                talkerId = attrs[0].substring(0, 2);
                sentenceId = attrs[0].substring(2);
            }

            //Checksum Check
            String[] checkSplit = attrs[attrs.length - 1].split("\\*");
            if(checkSplit.length != 2)
                continue;

            if(!checkData(data[1].substring(0, data[1].length() - 3), checkSplit[1]))
                continue;

            //Sentence Type, Checksum Data를 제거한 순수 Attributes 배열 생성
            String[] attrsNew = new String[attrs.length - 1];
            for(int i = 1; i < attrs.length - 1; ++i) {
                attrsNew[i - 1] = attrs[i];
            }

            attrsNew[attrsNew.length - 1] = checkSplit[0];

            //XDR Type 세분화
            if(sentenceId.equals("XDR"))
                sentenceId = convertXdrSentenceId(attrsNew.length);

            Map<String, Object> tmpMap;
            NmeaSentences sentenceClass = getSentenceClass(sentenceId);

            if (sentenceClass.getAttrSize() == null || attrsNew.length != sentenceClass.getAttrSize())
                continue;

            tmpMap = insertAttr(attrsNew, sentenceClass.getAttrNames(), sentenceClass.getAttrTypes());

            if(tmpMap == null || attrsNew.length != tmpMap.size())
                System.out.println("ID : " + sentenceId + " - Insert Attributes Error!");
            else {
                if(timestamp != null)
                    tmpMap.put("timestamp", timestamp);
                tmpMap.put("talker", talkerId);
                parseMap.put(sentenceClass.getTableName(), tmpMap);
            }
        }

        return parseMap;
    }

    public List<Map<String, Object>> parseToList(String nmeaData) {
        List<Map<String, Object>> parseList = new ArrayList<>();

        String[] splitedData = nmeaData.split("\r\n");
        for (String tmpdata : splitedData) {

            //$로 시작
            String[] data = tmpdata.split("\\$");
            if(data.length != 2)
                continue;

            Timestamp timestamp = null;
            if(!data[0].isEmpty())
                timestamp = Timestamp.valueOf(data[0]);

            //Sentence Type Check
            String[] attrs = data[1].split(",");
            if(attrs.length < 1 || attrs[0].length() < 2)
                continue;

            String talkerId = null;
            String sentenceId;
            if(attrs[0].equals("PTNVOPT1")) {
                sentenceId = attrs[0];
            } else {
                talkerId = attrs[0].substring(0, 2);
                sentenceId = attrs[0].substring(2);
            }

            //Checksum Check
            String[] checkSplit = attrs[attrs.length - 1].split("\\*");
            if(checkSplit.length != 2)
                continue;

            if(!checkData(data[1].substring(0, data[1].length() - 3), checkSplit[1]))
                continue;

            //Sentence Type, Checksum Data를 제거한 순수 Attributes 배열 생성
            String[] attrsNew = new String[attrs.length - 1];
            for(int i = 1; i < attrs.length - 1; ++i) {
                attrsNew[i - 1] = attrs[i];
            }

            attrsNew[attrsNew.length - 1] = checkSplit[0];

            //XDR Type 세분화
            if(sentenceId.equals("XDR"))
                sentenceId = convertXdrSentenceId(attrsNew.length);

            Map<String, Object> tmpMap;
            NmeaSentences sentenceClass = getSentenceClass(sentenceId);

            if (sentenceClass.getAttrSize() == null || attrsNew.length != sentenceClass.getAttrSize())
                continue;

            tmpMap = insertAttr(attrsNew, sentenceClass.getAttrNames(), sentenceClass.getAttrTypes());

            if(tmpMap == null || attrsNew.length != tmpMap.size())
                System.out.println("ID : " + sentenceId + " - Insert Attributes Error!");
            else {
                if(timestamp != null)
                    tmpMap.put("timestamp", timestamp.toString());
                tmpMap.put("talker", talkerId);
                tmpMap.put("tableName", sentenceClass.getTableName());
                parseList.add(tmpMap);
            }
        }

        return parseList;
    }

    private String convertXdrSentenceId(Integer xdrSize) {
        switch (xdrSize){
            case 12 :
                return "XDR3";
            case 16 :
                return "XDR4";
            case 20 :
                return "XDR5";
            case 24 :
                return "XDR6";
            default:
                return null;
        }
    }

    private NmeaSentences getSentenceClass(String sentenceId) {

        NmeaSentences sentenceClass = new NmeaSentences();

        //Type별 Class 정보 획득
        switch (sentenceId) {
            case "DBT" :
                NmeaSentences.DBT dbt = new NmeaSentences.DBT();
                sentenceClass.setTableName(dbt.getTableName());
                sentenceClass.setAttrSize(dbt.getAttrSize());
                sentenceClass.setAttrNames(dbt.getAttrNames());
                sentenceClass.setAttrTypes(dbt.getAttrTypes());
                break;
            case "DPT" :
                NmeaSentences.DPT dpt = new NmeaSentences.DPT();
                sentenceClass.setTableName(dpt.getTableName());
                sentenceClass.setAttrSize(dpt.getAttrSize());
                sentenceClass.setAttrNames(dpt.getAttrNames());
                sentenceClass.setAttrTypes(dpt.getAttrTypes());
                break;
            case "GGA" :
                NmeaSentences.GGA gga = new NmeaSentences.GGA();
                sentenceClass.setTableName(gga.getTableName());
                sentenceClass.setAttrSize(gga.getAttrSize());
                sentenceClass.setAttrNames(gga.getAttrNames());
                sentenceClass.setAttrTypes(gga.getAttrTypes());
                break;
            case "GLL" :
                NmeaSentences.GLL gll = new NmeaSentences.GLL();
                sentenceClass.setTableName(gll.getTableName());
                sentenceClass.setAttrSize(gll.getAttrSize());
                sentenceClass.setAttrNames(gll.getAttrNames());
                sentenceClass.setAttrTypes(gll.getAttrTypes());
                break;
            case "HDT" :
                NmeaSentences.HDT hdt = new NmeaSentences.HDT();
                sentenceClass.setTableName(hdt.getTableName());
                sentenceClass.setAttrSize(hdt.getAttrSize());
                sentenceClass.setAttrNames(hdt.getAttrNames());
                sentenceClass.setAttrTypes(hdt.getAttrTypes());
                break;
            case "MTW" :
                NmeaSentences.MTW mtw = new NmeaSentences.MTW();
                sentenceClass.setTableName(mtw.getTableName());
                sentenceClass.setAttrSize(mtw.getAttrSize());
                sentenceClass.setAttrNames(mtw.getAttrNames());
                sentenceClass.setAttrTypes(mtw.getAttrTypes());
                break;
            case "MWV" :
                NmeaSentences.MWV mwv = new NmeaSentences.MWV();
                sentenceClass.setTableName(mwv.getTableName());
                sentenceClass.setAttrSize(mwv.getAttrSize());
                sentenceClass.setAttrNames(mwv.getAttrNames());
                sentenceClass.setAttrTypes(mwv.getAttrTypes());
                break;
            case "RMC" :
                NmeaSentences.RMC rmc = new NmeaSentences.RMC();
                sentenceClass.setTableName(rmc.getTableName());
                sentenceClass.setAttrSize(rmc.getAttrSize());
                sentenceClass.setAttrNames(rmc.getAttrNames());
                sentenceClass.setAttrTypes(rmc.getAttrTypes());
                break;
            case "ROT" :
                NmeaSentences.ROT rot = new NmeaSentences.ROT();
                sentenceClass.setTableName(rot.getTableName());
                sentenceClass.setAttrSize(rot.getAttrSize());
                sentenceClass.setAttrNames(rot.getAttrNames());
                sentenceClass.setAttrTypes(rot.getAttrTypes());
                break;
            case "RSA" :
                NmeaSentences.RSA rsa = new NmeaSentences.RSA();
                sentenceClass.setTableName(rsa.getTableName());
                sentenceClass.setAttrSize(rsa.getAttrSize());
                sentenceClass.setAttrNames(rsa.getAttrNames());
                sentenceClass.setAttrTypes(rsa.getAttrTypes());
                break;
            case "SHR" :
                NmeaSentences.SHR shr = new NmeaSentences.SHR();
                sentenceClass.setTableName(shr.getTableName());
                sentenceClass.setAttrSize(shr.getAttrSize());
                sentenceClass.setAttrNames(shr.getAttrNames());
                sentenceClass.setAttrTypes(shr.getAttrTypes());
                break;
            case "VTG" :
                NmeaSentences.VTG vtg = new NmeaSentences.VTG();
                sentenceClass.setTableName(vtg.getTableName());
                sentenceClass.setAttrSize(vtg.getAttrSize());
                sentenceClass.setAttrNames(vtg.getAttrNames());
                sentenceClass.setAttrTypes(vtg.getAttrTypes());
                break;
            case "XDR3" :
                NmeaSentences.XDR3 xdr3 = new NmeaSentences.XDR3();
                sentenceClass.setTableName(xdr3.getTableName());
                sentenceClass.setAttrSize(xdr3.getAttrSize());
                sentenceClass.setAttrNames(xdr3.getAttrNames());
                sentenceClass.setAttrTypes(xdr3.getAttrTypes());
                break;
            case "XDR4" :
                NmeaSentences.XDR4 xdr4 = new NmeaSentences.XDR4();
                sentenceClass.setTableName(xdr4.getTableName());
                sentenceClass.setAttrSize(xdr4.getAttrSize());
                sentenceClass.setAttrNames(xdr4.getAttrNames());
                sentenceClass.setAttrTypes(xdr4.getAttrTypes());
                break;
            case "XDR5" :
                NmeaSentences.XDR5 xdr5 = new NmeaSentences.XDR5();
                sentenceClass.setTableName(xdr5.getTableName());
                sentenceClass.setAttrSize(xdr5.getAttrSize());
                sentenceClass.setAttrNames(xdr5.getAttrNames());
                sentenceClass.setAttrTypes(xdr5.getAttrTypes());
                break;
            case "XDR6" :
                NmeaSentences.XDR6 xdr6 = new NmeaSentences.XDR6();
                sentenceClass.setTableName(xdr6.getTableName());
                sentenceClass.setAttrSize(xdr6.getAttrSize());
                sentenceClass.setAttrNames(xdr6.getAttrNames());
                sentenceClass.setAttrTypes(xdr6.getAttrTypes());
                break;
            case "PTNVOPT1" :
                NmeaSentences.PTNVOPT1 ptnvopt1 = new NmeaSentences.PTNVOPT1();
                sentenceClass.setTableName(ptnvopt1.getTableName());
                sentenceClass.setAttrSize(ptnvopt1.getAttrSize());
                sentenceClass.setAttrNames(ptnvopt1.getAttrNames());
                sentenceClass.setAttrTypes(ptnvopt1.getAttrTypes());
                break;
            default:
                break;
        }

        return sentenceClass;
    }

    private Map<String, Object> insertAttr(String[] attrs, String[] attrNames, Integer[] attrTypes) {

        if(attrs.length != attrNames.length || attrNames.length != attrTypes.length)
            return null;

        Map<String, Object> temp = new HashMap<>();
        for(int i = 0; i < attrTypes.length; ++i) {
            if (attrs[i].isEmpty()) {
                temp.put(attrNames[i], null);
                continue;
            }

            if(attrTypes[i] == Types.INTEGER || attrTypes[i] == Types.TINYINT) {
                if(isInt(attrs[i]))
                    temp.put(attrNames[i], Integer.parseInt(attrs[i]));
            } else if(attrTypes[i] == Types.FLOAT) {
                if(isFloat(attrs[i]))
                    temp.put(attrNames[i], Float.parseFloat(attrs[i]));
            } else {
                temp.put(attrNames[i], attrs[i]);
            }
        }

        return temp;
    }

    public boolean isInt(String str) {
        try {
            Integer.parseInt(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isFloat(String str) {
        try {
            Float.parseFloat(str);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean checkData(String data, String checksum) {
        char check = 0;
        for (int i = 0; i < data.length(); i++) {
            check = (char) (check ^ data.charAt(i));
        }
        String tmpStr = String.format("%02X", (int) check);
        return tmpStr.equals(checksum);
    }
}
