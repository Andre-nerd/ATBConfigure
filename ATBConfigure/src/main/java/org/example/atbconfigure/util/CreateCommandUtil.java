package org.example.atbconfigure.util;

import javafx.scene.control.RadioButton;

import static org.example.atbconfigure.util.CommonUtils.crc8;

public class CreateCommandUtil {
    /** Reset device command 00*/
    public static byte[] createCommand00(RadioButton rOn, RadioButton rOff){
        if(rOn.isSelected() || rOff.isSelected()) {
            byte mode = (byte) (rOn.isSelected() ? 1 : 0);
            byte[] body = {36, 0, 0, 1, mode};
            byte crc = (byte) crc8(body);
            byte[] message = {36, 0, 0, 1, mode, crc};
            return message;
        }
        return null;
    }

    /** 5.1.2. Navigation management solution command 02*/
//    public static byte[] createCommand01Set(boolean is){
//
//    }
    public static byte[] createCommand02Get(){
        byte[] body = {36, 1, 2, 0};
        byte crc = (byte) crc8(body);
        byte[] message = {36, 1, 2, 0,crc};
        return message;
    }
}
